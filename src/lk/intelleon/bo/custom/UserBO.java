package lk.intelleon.bo.custom;

import lk.intelleon.bo.SuperBO;
import lk.intelleon.dto.ProductDTO;
import lk.intelleon.dto.UserDTO;
import lk.intelleon.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Ushan Shanilka <ushanshanilka80@gmail.com>
 * @since 2/3/2022
 **/
public interface UserBO extends SuperBO {
    boolean addUser( UserDTO user) throws Exception ;

    boolean deleteUser(String id) throws Exception ;

    UserDTO searchUser(String id) throws Exception ;

    boolean updateUser(UserDTO user) throws Exception ;

    ArrayList<UserDTO> getAllUsers() throws Exception ;

    User getActiveUsers(String name, String password) throws SQLException, ClassNotFoundException;

    ArrayList<UserDTO> getActiveUsers() throws SQLException, ClassNotFoundException;

    String generateUserId() throws SQLException, ClassNotFoundException;
}
