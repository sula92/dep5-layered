package bo.custom.impl;

import bo.custom.OrderBO;
import dao.DAOFactory;
import dao.DAOTypes;
import dao.custom.impl.ItemDAOImpl;
import dao.custom.impl.OrderDAOImpl;
import dao.custom.impl.OrderDetailDAOImpl;
import db.DBConnection;
import entity.Item;
import entity.Order;
import entity.OrderDetail;
import util.OrderDetailTM;
import util.OrderTM;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class OrderBOImpl implements OrderBO {

    ItemDAOImpl itemDAO= DAOFactory.getInstance().getDAO(DAOTypes.ORDER);

    public static String getNewOrderId() {
        String lastOrderId = OrderDAOImpl.getLastOrderId();
        if (lastOrderId == null) {
            return "OD001";
        } else {
            int maxId = Integer.parseInt(lastOrderId.replace("O", ""));
            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "O00" + maxId;
            } else if (maxId < 100) {
                id = "O0" + maxId;
            } else {
                id = "O" + maxId;
            }
            return id;
        }
    }



    public boolean placeOrder(OrderTM order, List<OrderDetailTM> orderDetails) {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            boolean result = OrderDAOImpl.saveOrder(new Order(order.getOrderId(),
                    Date.valueOf(order.getOrderDate()),
                    order.getCustomerId()));
            if (!result) {
                connection.rollback();
                return false;
            }
            for (OrderDetailTM orderDetail : orderDetails) {
                result = OrderDetailDAOImpl.saveOrderDetail(new OrderDetail(
                        order.getOrderId(), orderDetail.getCode(),
                        orderDetail.getQty(), BigDecimal.valueOf(orderDetail.getUnitPrice())
                ));
                if (!result){
                    connection.rollback();
                    return false;
                }
                Item item = itemDAO.findItem(orderDetail.getCode());
                item.setQtyOnHand(item.getQtyOnHand() - orderDetail.getQty());
                result = itemDAO.updateItem(item);
                if (!result){
                    connection.rollback();
                    return false;
                }
            }
            connection.commit();
            return true;
        } catch (Throwable throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
