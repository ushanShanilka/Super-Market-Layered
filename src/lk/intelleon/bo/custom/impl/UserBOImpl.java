package lk.intelleon.bo.custom.impl;

import lk.intelleon.bo.custom.UserBO;
import lk.intelleon.dao.DAOFactory;
import lk.intelleon.dao.custom.UserDAO;
import lk.intelleon.dto.UserDTO;
import lk.intelleon.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ushan Shanilka <ushanshanilka80@gmail.com>
 * @since 2/3/2022
 **/
public class UserBOImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getSuperDAO(DAOFactory.DAOType.USER);

    @Override
    public boolean addUser(UserDTO user) throws Exception {
        return userDAO.add(new User(user.getUserId(),user.getUserName(),user.getPassword(),user.getActiveState(),user.getUserType()));
    }

    @Override
    public boolean deleteUser(String id) throws Exception {
        return userDAO.delete(id);
    }

    @Override
    public UserDTO searchUser(String id) throws Exception {
        User search = userDAO.search(id);
        return new UserDTO(search.getUserId(),search.getUserName(),search.getPassword(),search.getActiveState(),search.getUserType());
    }

    @Override
    public boolean updateUser(UserDTO user) throws Exception {
        return userDAO.update(new User(user.getUserId(),user.getUserName(),user.getPassword(),user.getActiveState(),user.getUserType()));
    }

    @Override
    public ArrayList<UserDTO> getAllUsers() throws Exception {
        List<User> all = userDAO.getAll();
        ArrayList<UserDTO> userDTOS = new ArrayList<>();

        for (User user: all){
            userDTOS.add(new UserDTO(user.getUserId(),user.getUserName(),user.getPassword(),user.getActiveState(),user.getUserType()));
        }
        return userDTOS;
    }

    @Override
    public User getActiveUsers(String name, String password) throws SQLException, ClassNotFoundException {
        return userDAO.getActiveUsers(name, password);
    }

    @Override
    public ArrayList<UserDTO> getActiveUsers() throws SQLException, ClassNotFoundException {
        List<User> allActiveUsers = userDAO.getAllActiveUsers();
        ArrayList<UserDTO> activeStateUsers = new ArrayList<>();

        for (User user : allActiveUsers){
            activeStateUsers.add(new UserDTO(user.getUserId(),user.getUserName(),user.getPassword(),user.getActiveState(),user.getUserType()));
        }
        return activeStateUsers;
    }

    @Override
    public String generateUserId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = userDAO.generateUserId();

        if (resultSet.next()) {
            String oldId = resultSet.getString(1);
            String substring = oldId.substring(1, 4);
            int intId = Integer.parseInt(substring);

            intId = intId + 1;
            if (intId < 10) {
                return "U00" + intId;
            } else if (intId < 100) {
                return "U0" + intId;
            } else if (intId < 1000) {
                return "U" + intId;
            }
        }
        return "U001";
    }
}
