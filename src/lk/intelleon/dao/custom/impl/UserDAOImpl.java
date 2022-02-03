package lk.intelleon.dao.custom.impl;

import lk.intelleon.dao.custom.UserDAO;
import lk.intelleon.entity.User;
import lk.intelleon.utils.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ushan Shanilka <ushanshanilka80@gmail.com>
 * @since 2/3/2022
 **/
public class UserDAOImpl implements UserDAO {
    @Override
    public boolean add(User user) throws Exception {
        return CrudUtil.execute ( "INSERT INTO User VALUES (?,?,?,?,?)",user.getUserId (),user.getUserName (),user.getPassword (),user.getActiveState (),user.getUserType () );
    }

    @Override
    public boolean update(User user) throws Exception {
        return CrudUtil.execute ( "UPDATE User SET name=?, password=?, active_state=?,userType=? WHERE id=?" , user.getUserName ( ) , user.getPassword ( ) , user.getActiveState ( ) , user.getUserType ( ) , user.getUserId ( ) );
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.execute ( "DELETE FROM User WHERE id=?",id );
    }

    @Override
    public User search(String id) throws Exception {
        ResultSet rst = CrudUtil.execute ( "SELECT * FROM User WHERE id=?" , id );
        if (rst.next ()){
            return new User (
                    rst.getString ( 1 ),
                    rst.getString ( 2 ),
                    rst.getString ( 3 ),
                    rst.getBoolean ( 4 ),
                    rst.getString ( 5 )
            );
        }
        return null;
    }

    @Override
    public ArrayList<User> getAll() throws Exception {
        ResultSet rst = CrudUtil.execute ( "SELECT * FROM User" );
        ArrayList< User >  users = new ArrayList<> ( );
        while (rst.next ()){
            users.add (
                    new User (
                            rst.getString ( 1 ),rst.getString ( 2 ),
                            rst.getString ( 3 ),rst.getBoolean ( 4 ),
                            rst.getString ( 5 )
                    )
            );
        }
        return users;
    }

    @Override
    public List<User> getAllActiveUsers() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute ( "SELECT * FROM User WHERE active_state=1" );
        ArrayList< User > activeUsersList = new ArrayList<> ( );

        while (rst.next ()){
            activeUsersList.add (
                    new User (
                            rst.getString ( 1 ),rst.getString ( 2 ),
                            rst.getString ( 3 ),rst.getBoolean ( 4 ),
                            rst.getString ( 5 )
                    )
            );
        }
        return activeUsersList;
    }

    @Override
    public ResultSet generateUserId() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute( "SELECT id FROM User ORDER BY id DESC LIMIT 1" );
    }

    @Override
    public User getActiveUsers(String name, String password) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute ( "SELECT * FROM User WHERE id=? AND  password=? AND active_state=1",name,password );
        if (rst.next ()){
            return new User (
                    rst.getString ( 1 ),rst.getString ( 2 ),
                    rst.getString ( 3 ),rst.getBoolean ( 4 ),
                    rst.getString ( 5 )
            );
        }
        return null;
    }
}
