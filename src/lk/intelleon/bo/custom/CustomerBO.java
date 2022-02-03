package lk.intelleon.bo.custom;

import lk.intelleon.bo.SuperBO;
import lk.intelleon.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Ushan Shanilka <ushanshanilka80@gmail.com>
 * @since 2/3/2022
 **/
public interface CustomerBO extends SuperBO {
    boolean addCustomer( CustomerDTO customer) throws Exception ;

    boolean deleteCustomer(String id) throws Exception ;

    CustomerDTO searchCustomer(String id) throws Exception ;

    boolean updateCustomer(CustomerDTO customer) throws Exception ;

    ArrayList<CustomerDTO> getAllCustomer() throws Exception ;

    String generateCustomerId() throws SQLException, ClassNotFoundException;
}
