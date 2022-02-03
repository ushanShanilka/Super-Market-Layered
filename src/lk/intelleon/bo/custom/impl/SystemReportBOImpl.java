package lk.intelleon.bo.custom.impl;

import lk.intelleon.bo.custom.SystemReportBO;
import lk.intelleon.dao.DAOFactory;
import lk.intelleon.dao.custom.OrderDAO;
import lk.intelleon.dao.custom.OrderDetailsDAO;
import lk.intelleon.dto.OrderDTO;
import lk.intelleon.dto.OrderDetailDTO;
import lk.intelleon.entity.Order;
import lk.intelleon.entity.OrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ushan Shanilka <ushanshanilka80@gmail.com>
 * @since 2/3/2022
 **/
public class SystemReportBOImpl implements SystemReportBO {

    OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().getSuperDAO(DAOFactory.DAOType.ORDER);
    OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getInstance().getSuperDAO(DAOFactory.DAOType.ORDERDETAIL);

    @Override
    public ArrayList<OrderDTO> getAllOrders() throws Exception {
        List<Order> all = orderDAO.getAll();

        ArrayList<OrderDTO> orderDTOS = new ArrayList<>();

        for (Order order:all){
            orderDTOS.add(new OrderDTO(order.getOrderId(),order.getDateTime(),order.getTotal(),order.getCusId(),order.getUserId()));
        }
        return orderDTOS;
    }

    @Override
    public boolean deleteOrder(String id) throws Exception {
        return orderDAO.delete(id);
    }

    @Override
    public ArrayList<OrderDTO> getAllOrdersByCusId(String cusId) throws SQLException, ClassNotFoundException {
        List<Order> allOrdersByCusId = orderDAO.getAllOrdersByCusId(cusId);
        ArrayList<OrderDTO> orderDTOS = new ArrayList<>();
        for (Order order:allOrdersByCusId){
            orderDTOS.add(new OrderDTO(order.getOrderId(),order.getDateTime(),order.getTotal(),order.getCusId(),order.getUserId()));
        }
        return orderDTOS;
    }

    @Override
    public OrderDTO getOrder(String id) throws Exception {
        Order order = orderDAO.search(id);
        return new OrderDTO(order.getOrderId(),order.getDateTime(),order.getTotal(),order.getCusId(),order.getUserId());
    }

    @Override
    public ArrayList<OrderDTO> getAllOrderByDaily(String date) throws SQLException, ClassNotFoundException {
        List<Order> allOrderByDaily = orderDAO.getAllOrderByDaily(date);
        ArrayList<OrderDTO> orderDTOS = new ArrayList<>();

        for (Order order: allOrderByDaily){
            orderDTOS.add(new OrderDTO(order.getOrderId(),order.getDateTime(),order.getTotal(),order.getCusId(),order.getUserId()));
        }
        return orderDTOS;
    }

    @Override
    public ArrayList<OrderDTO> getAllOrderByMonthly(String today, String afterMonth) throws SQLException, ClassNotFoundException {
        List<Order> allOrderByMonthly = orderDAO.getAllOrderByMonthly(today, afterMonth);
        ArrayList<OrderDTO> orderDTOS = new ArrayList<>();

        for (Order order: allOrderByMonthly){
            orderDTOS.add(new OrderDTO(order.getOrderId(),order.getDateTime(),order.getTotal(),order.getCusId(),order.getUserId()));
        }
        return orderDTOS;
    }

    @Override
    public ArrayList<OrderDetailDTO> getAllOrderDetails() throws Exception {
        List<OrderDetail> all = orderDetailsDAO.getAll();
        ArrayList<OrderDetailDTO> orderDetailDTOS = new ArrayList<>();

        for (OrderDetail orderDetail: all){
            orderDetailDTOS.add(new OrderDetailDTO(orderDetail.getQty(),orderDetail.getUnitPrice(),orderDetail.getDisCount(),orderDetail.getOrderId(),orderDetail.getProductId(),orderDetail.getRefId()));
        }
        return orderDetailDTOS;
    }

    @Override
    public ArrayList<OrderDetailDTO> getOrderDetail(String id) throws Exception {
        List<OrderDetail> orderDetails = orderDetailsDAO.searchByOrderId(id);
        ArrayList<OrderDetailDTO> orderDetailDTOS = new ArrayList<>();

        for (OrderDetail orderDetail:orderDetails){
            orderDetailDTOS.add(new OrderDetailDTO(orderDetail.getQty(),orderDetail.getUnitPrice(),orderDetail.getDisCount(),orderDetail.getOrderId(),orderDetail.getProductId(),orderDetail.getRefId()));
        }
        return orderDetailDTOS;
    }
}
