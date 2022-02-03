package lk.intelleon.dao.custom.impl;

import lk.intelleon.dao.custom.CustomerDAO;
import lk.intelleon.entity.Customer;
import lk.intelleon.utils.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Ushan Shanilka <ushanshanilka80@gmail.com>
 * @since 2/3/2022
 **/
public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean add(Customer customer) throws Exception {

        Customer search = search(customer.getCusId());
        if (search!=null){
            update(customer);
            return true;
        }else {
            return CrudUtil.execute( "INSERT INTO Customer VALUES (?,?,?,?,?,?,?)",
                    customer.getCusId(),customer.getCusType(),
                    customer.getCusName(),customer.getCusAddress(),
                    customer.getCusCity(),customer.getCusProvince(),
                    customer.getCusContact());
        }
    }

    @Override
    public boolean update(Customer customer) throws Exception {
        return CrudUtil.execute( "UPDATE Customer SET name=?, customerType=?, address=?, city=?, province=?, contact=? WHERE id=?" ,
                customer.getCusName( ) , customer.getCusType( ) ,
                customer.getCusAddress( ) , customer.getCusCity( ) ,
                customer.getCusProvince( ) , customer.getCusContact( ),
                customer.getCusId());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.execute ( "DELETE FROM Customer WHERE id=?",id );
    }

    @Override
    public Customer search(String id) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Customer WHERE id=?", id);

        while (resultSet.next()){
            return new Customer(
                    resultSet.getString( 1 ),
                    resultSet.getString( 2 ),
                    resultSet.getString( 3 ),
                    resultSet.getString( 4 ),
                    resultSet.getString( 5 ),
                    resultSet.getString( 6 ),
                    resultSet.getInt(7 )
            );
        }
        return null;
    }

    @Override
    public ArrayList<Customer> getAll() throws Exception {
        ResultSet rst = CrudUtil.execute( "SELECT * FROM Customer" );
        ArrayList< Customer > list = new ArrayList<>( );

        while (rst.next()){
            list.add( new Customer(
                    rst.getString( 1 ),
                    rst.getString( 2 ),
                    rst.getString( 3 ),
                    rst.getString( 4 ),
                    rst.getString( 5 ),
                    rst.getString( 6 ),
                    rst.getInt( 7 )
            ) );
        }
        return list;
    }

    @Override
    public ResultSet generateCustomerId() throws SQLException, ClassNotFoundException {
        ResultSet rst= CrudUtil.execute( "SELECT id FROM Customer Order By id DESC LIMIT 1" );
        return rst;
    }
}
