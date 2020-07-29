package bo;

import bo.custom.ItemBO;
import bo.custom.OrderBO;
import bo.custom.OrderDetailBO;
import bo.custom.impl.CustomerBOImpl;
import bo.custom.impl.ItemBOImpl;
import bo.custom.impl.OrderBOImpl;
import bo.custom.impl.OrderDetailBOImpl;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory(){

    }

    public static BOFactory getInstance(){

        return (boFactory==null) ? new BOFactory() : boFactory;

    }

    public <T extends SuperBO>  T getBO(BOTypes boTypes){

        switch (boTypes){
            case ITEM:
                return (T) new ItemBOImpl();
            case ORDER:
                return (T) new OrderBOImpl();
            case CUSTOMER:
                return (T) new CustomerBOImpl();
            case ORDERDETAIL:
                return (T) new OrderDetailBOImpl();
            default:
                return null;

        }

    }


}
