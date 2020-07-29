package bo.custom.impl;

import bo.custom.CustomerBO;
import dao.DAOFactory;
import dao.DAOTypes;
import dao.custom.impl.CustomerDAOImpl;
import entity.Customer;
import util.CustomerTM;

import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAOImpl customerDAO= DAOFactory.getInstance().getDAO(DAOTypes.CUSTOMER);

    public String getNewCustomerId() {
        String lastCustomerId = customerDAO.getLastCustomerId();
        if (lastCustomerId == null) {
            return "C001";
        } else {
            int maxId = Integer.parseInt(lastCustomerId.replace("C", ""));
            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "C00" + maxId;
            } else if (maxId < 100) {
                id = "C0" + maxId;
            } else {
                id = "C" + maxId;
            }
            return id;
        }
    }


    public List<CustomerTM> getAllCustomers() {
        List<Customer> allCustomers = customerDAO.findAllCustomers();
        List<CustomerTM> customers = new ArrayList<>();
        for (Customer customer : allCustomers) {
            customers.add(new CustomerTM(customer.getId(), customer.getName(), customer.getAddress()));
        }
        return customers;
    }

    public boolean saveCustomer(String id, String name, String address) {
        return customerDAO.saveCustomer(new Customer(id, name, address));
    }

    public boolean deleteCustomer(String customerId) {
        return customerDAO.deleteCustomer(customerId);
    }

    public boolean updateCustomer(String name, String address, String customerId) {
        return customerDAO.updateCustomer(new Customer(customerId, name, address));
    }

}
