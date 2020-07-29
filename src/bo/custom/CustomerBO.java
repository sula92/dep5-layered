package bo.custom;

import bo.SuperBO;
import util.CustomerTM;

import java.util.List;

public interface CustomerBO extends SuperBO {

    public String getNewCustomerId();

    public List<CustomerTM> getAllCustomers();

    public boolean saveCustomer(String id, String name, String address);

    public boolean deleteCustomer(String customerId);

    public boolean updateCustomer(String name, String address, String customerId);

}
