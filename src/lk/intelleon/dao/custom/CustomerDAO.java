package lk.intelleon.dao.custom;

import lk.intelleon.dao.CrudDAO;
import lk.intelleon.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Ushan Shanilka <ushanshanilka80@gmail.com>
 * @since 2/3/2022
 **/
public interface CustomerDAO extends CrudDAO<Customer,String> {
    ResultSet generateCustomerId() throws SQLException, ClassNotFoundException;
}
