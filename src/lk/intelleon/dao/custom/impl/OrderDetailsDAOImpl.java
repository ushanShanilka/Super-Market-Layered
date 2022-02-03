package lk.intelleon.dao.custom.impl;

import lk.intelleon.dao.custom.OrderDetailsDAO;
import lk.intelleon.entity.OrderDetail;
import lk.intelleon.utils.CrudUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ushan Shanilka <ushanshanilka80@gmail.com>
 * @since 2/3/2022
 **/
public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    @Override
    public boolean add(OrderDetail orderDetail) throws Exception {
        return CrudUtil.execute("INSERT INTO Orderdetail VALUES (?,?,?,?,?,?)",
                orderDetail.getQty(),orderDetail.getUnitPrice(),orderDetail.getDisCount(),
                orderDetail.getOrderId(),orderDetail.getProductId(),orderDetail.getRefId());

    }

    @Override
    public boolean update(OrderDetail orderDetail) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public OrderDetail search(String s) throws Exception {
        return CrudUtil.execute( "SELECT * FROM OrderDetail WHERE orderId=?" , s );
    }

    @Override
    public List<OrderDetail> getAll() throws Exception {
        ResultSet resultSet =  CrudUtil.execute( "SELECT * FROM OrderDetail" );

        ArrayList<OrderDetail> orderDetails = new ArrayList<>();

        while (resultSet.next()){
            OrderDetail orderDetail = new OrderDetail(resultSet.getInt(1),
                    resultSet.getDouble(2),
                    resultSet.getDouble(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6));
            orderDetails.add(orderDetail);
        }
        return orderDetails;
    }

    @Override
    public List<OrderDetail> searchByOrderId(String s) throws Exception {
        ResultSet resultSet = CrudUtil.execute( "SELECT * FROM OrderDetail WHERE orderId=?" , s );

        ArrayList<OrderDetail> orderDetails = new ArrayList<>();

        while (resultSet.next()){
            OrderDetail orderDetail = new OrderDetail(resultSet.getInt(1),
                    resultSet.getDouble(2),
                    resultSet.getDouble(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6));
            orderDetails.add(orderDetail);
        }
        return orderDetails;
    }
}
