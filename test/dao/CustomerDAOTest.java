package dao;

import dao.custom.impl.CustomerDAOImpl;

public class CustomerDAOTest {

    public static void main(String[] args) {

        CustomerDAOImpl customerDAO=new CustomerDAOImpl();

        assert customerDAO.findAllCustomers().size() == 6;
//        assert CustomerDAO.deleteCustomer("C011"): "Test Failed";
//        boolean result = CustomerDAO.deleteCustomer("C006");
//        List<Customer> customers = CustomerDAO.findAllCustomers();
//        for (Customer customer : customers) {
//            System.out.println(customer);
//        }
/*        boolean result = CustomerDAO.saveCustomer(new Customer("C006", "Appu", "Panadura"));
        System.out.println(result);
        List<Customer> customers = CustomerDAO.findAllCustomers();
        for (Customer customer : customers) {
            System.out.println(customer);
        }*/
    }
}
