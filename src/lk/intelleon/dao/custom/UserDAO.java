package lk.intelleon.dao.custom;

import lk.intelleon.dao.CrudDAO;
import lk.intelleon.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Ushan Shanilka <ushanshanilka80@gmail.com>
 * @since 2/3/2022
 **/
public interface UserDAO extends CrudDAO<User,String > {
    List<User> getAllActiveUsers() throws SQLException, ClassNotFoundException;
    ResultSet generateUserId() throws SQLException, ClassNotFoundException;
    User getActiveUsers(String name,String password) throws SQLException, ClassNotFoundException;
}
