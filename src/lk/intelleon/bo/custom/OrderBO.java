package lk.intelleon.bo.custom;


import lk.intelleon.bo.SuperBO;
import lk.intelleon.dto.OrderDTO;

import java.sql.SQLException;

/**
 * @author Ushan Shanilka <ushanshanilka80@gmail.com>
 * @since 2/3/2022
 **/
public interface OrderBO extends SuperBO {
    boolean purchaseOrder( OrderDTO orders) throws Exception;

    String generateOrderId() throws SQLException, ClassNotFoundException;
}
