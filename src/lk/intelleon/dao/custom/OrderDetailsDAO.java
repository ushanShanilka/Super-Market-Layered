package lk.intelleon.dao.custom;

import lk.intelleon.dao.CrudDAO;
import lk.intelleon.entity.OrderDetail;

import java.util.List;

/**
 * @author Ushan Shanilka <ushanshanilka80@gmail.com>
 * @since 2/3/2022
 **/
public interface OrderDetailsDAO extends CrudDAO<OrderDetail,String> {
    List<OrderDetail> searchByOrderId(String s) throws Exception;
}
