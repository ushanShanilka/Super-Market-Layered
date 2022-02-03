package lk.intelleon.dao.custom;

import lk.intelleon.dao.CrudDAO;
import lk.intelleon.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Ushan Shanilka <ushanshanilka80@gmail.com>
 * @since 2/3/2022
 **/
public interface ProductDAO extends CrudDAO<Product,String > {
    boolean updateQty(String id,int qty) throws SQLException, ClassNotFoundException;
    List<Product> getAllActiveState() throws SQLException, ClassNotFoundException;
    ResultSet autoGenerateID() throws SQLException, ClassNotFoundException;
}
