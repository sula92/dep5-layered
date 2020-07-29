package bo.custom;

import bo.SuperBO;
import util.ItemTM;

import java.util.List;

public interface ItemBO extends SuperBO {

    public String getNewItemCode();

    public boolean saveItem(String code, String description, int qtyOnHand, double unitPrice);

    public boolean deleteItem(String itemCode);

    public boolean updateItem(String description, int qtyOnHand, double unitPrice, String itemCode);

    public List<ItemTM> getAllItems();

}
