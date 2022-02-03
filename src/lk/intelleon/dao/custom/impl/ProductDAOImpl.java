package lk.intelleon.dao.custom.impl;

import lk.intelleon.dao.custom.ProductDAO;
import lk.intelleon.entity.Product;
import lk.intelleon.utils.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ushan Shanilka <ushanshanilka80@gmail.com>
 * @since 2/3/2022
 **/
public class ProductDAOImpl implements ProductDAO {
    @Override
    public boolean add(Product product) throws Exception {
        return CrudUtil.execute ( "INSERT INTO Product VALUES (?,?,?,?,?,?,?,?,?,?)",product.getProductId (),product.getName (),
                product.getDescription (),product.getSpec (),
                product.getDisplayName (),product.isAvailability (),
                product.isActiveState (),product.getBrands (),
                product.getQty(),product.getPrice());
    }

    @Override
    public boolean update(Product product) throws Exception {
        return CrudUtil.execute ( "UPDATE Product SET name=?, description=?, specification=?, displayName=?, available=?, activeState=?, availableBrands=?,quantity=?,price=? WHERE id=?",product.getName (),product.getDescription (),product.getSpec (),product.getDisplayName (),product.isAvailability (),product.isActiveState (),product.getBrands (),product.getQty(),product.getPrice(),product.getProductId () );
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.execute ( "DELETE FROM Product WHERE id=?",id );
    }

    @Override
    public Product search(String id) throws Exception {
        ResultSet rst = CrudUtil.execute ( "SELECT * FROM Product WHERE id=?" , id );
        if (rst.next ()){
            return new Product (
                    rst.getString ( 1 ),
                    rst.getString (  2),
                    rst.getString (  3),
                    rst.getString (  4),
                    rst.getString (  5),
                    rst.getBoolean ( 6 ),
                    rst.getBoolean ( 7 ),
                    rst.getString (  8),
                    rst.getInt(9),
                    rst.getBigDecimal(10)
            );
        }
        return null;
    }

    @Override
    public ArrayList<Product> getAll() throws Exception {
        ArrayList< Product > productArrayList = new ArrayList<> ( );
        ResultSet rst = CrudUtil.execute ( "SELECT * FROM Product" );

        while (rst.next ()) {
            productArrayList.add (
                    new Product (
                            rst.getString ( 1 ) , rst.getString ( 2 ) ,
                            rst.getString ( 3 ) , rst.getString ( 4 ) ,
                            rst.getString ( 5 ) , rst.getBoolean ( 6 ) ,
                            rst.getBoolean ( 7 ) , rst.getString ( 8 ),
                            rst.getInt(9),rst.getBigDecimal(10)

                    )
            );
        }
        return productArrayList;
    }

    @Override
    public boolean updateQty(String id, int qty) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Product SET quantity=(quantity-?) WHERE id=?",qty,id);
    }

    @Override
    public List<Product> getAllActiveState() throws SQLException, ClassNotFoundException {
        ArrayList< Product > productArrayList = new ArrayList<> ( );
        ResultSet rst = CrudUtil.execute ( "SELECT * FROM Product WHERE ActiveState=1" );

        while (rst.next ()) {
            productArrayList.add (
                    new Product (
                            rst.getString ( 1 ) , rst.getString ( 2 ) ,
                            rst.getString ( 3 ) , rst.getString ( 4 ) ,
                            rst.getString ( 5 ) , rst.getBoolean ( 6 ) ,
                            rst.getBoolean ( 7 ) , rst.getString ( 8 ),
                            rst.getInt(9),rst.getBigDecimal(10)
                    )
            );
        }
        return productArrayList;
    }

    @Override
    public ResultSet autoGenerateID() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute( "SELECT id FROM product ORDER BY id DESC LIMIT 1" );
        return rst;
    }
}
