package bo.custom.impl;

import bo.custom.ItemBO;
import dao.DAOFactory;
import dao.DAOTypes;
import dao.custom.impl.ItemDAOImpl;
import entity.Item;
import util.ItemTM;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class ItemBOImpl implements ItemBO {

    ItemDAOImpl itemDAO= DAOFactory.getInstance().getDAO(DAOTypes.ITEM);

    public String getNewItemCode() {
        String lastItemCode = itemDAO.getLastItemCode();
        if (lastItemCode == null) {
            return "I001";
        } else {
            int maxId = Integer.parseInt(lastItemCode.replace("I", ""));
            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "I00" + maxId;
            } else if (maxId < 100) {
                id = "I0" + maxId;
            } else {
                id = "I" + maxId;
            }
            return id;
        }
    }

    public boolean saveItem(String code, String description, int qtyOnHand, double unitPrice) {
        return itemDAO.saveItem(new Item(code, description, BigDecimal.valueOf(unitPrice), qtyOnHand));
    }

    public boolean deleteItem(String itemCode) {
        return itemDAO.deleteItem(itemCode);
    }

    public boolean updateItem(String description, int qtyOnHand, double unitPrice, String itemCode) {
        return itemDAO.updateItem(new Item(itemCode, description,
                BigDecimal.valueOf(unitPrice), qtyOnHand));
    }

    public List<ItemTM> getAllItems() {
        List<Item> allItems = itemDAO.findAllItems();
        List<ItemTM> items = new ArrayList<>();
        for (Item item : allItems) {
            items.add(new ItemTM(item.getCode(), item.getDescription(), item.getQtyOnHand(),
                    item.getUnitPrice().doubleValue()));
        }
        return items;
    }

}
