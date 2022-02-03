package lk.intelleon.bo.custom;

import lk.intelleon.bo.SuperBO;
import lk.intelleon.dto.OrderDTO;
import lk.intelleon.dto.OrderDetailDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ushan Shanilka <ushanshanilka80@gmail.com>
 * @since 2/3/2022
 **/
public interface SystemReportBO extends SuperBO {
    ArrayList<OrderDTO> getAllOrders() throws Exception;
    boolean deleteOrder(String id) throws Exception;
    ArrayList<OrderDTO> getAllOrdersByCusId(String cusId) throws SQLException, ClassNotFoundException;
    OrderDTO getOrder(String id) throws Exception;
    ArrayList<OrderDTO> getAllOrderByDaily(String date) throws SQLException, ClassNotFoundException;
    ArrayList<OrderDTO> getAllOrderByMonthly(String today,String afterMonth) throws SQLException, ClassNotFoundException;
    ArrayList<OrderDetailDTO> getAllOrderDetails() throws Exception;
    ArrayList<OrderDetailDTO> getOrderDetail(String id ) throws Exception;
}
