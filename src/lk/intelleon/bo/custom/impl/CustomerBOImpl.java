package lk.intelleon.bo.custom.impl;

import lk.intelleon.bo.custom.CustomerBO;
import lk.intelleon.controller.CashierFormController;
import lk.intelleon.dao.DAOFactory;
import lk.intelleon.dao.custom.CustomerDAO;
import lk.intelleon.dao.custom.OrderDAO;
import lk.intelleon.dao.custom.OrderDetailsDAO;
import lk.intelleon.dao.custom.ProductDAO;
import lk.intelleon.db.DBConnection;
import lk.intelleon.dto.CustomerDTO;
import lk.intelleon.dto.OrderDTO;
import lk.intelleon.dto.OrderDetailDTO;
import lk.intelleon.entity.Customer;
import lk.intelleon.entity.Order;
import lk.intelleon.entity.OrderDetail;
import lk.intelleon.entity.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Ushan Shanilka <ushanshanilka80@gmail.com>
 * @since 2/3/2022
 **/
public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance ( ).getSuperDAO ( DAOFactory.DAOType.CUSTOMER );
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance ( ).getSuperDAO ( DAOFactory.DAOType.ORDER );
    OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getInstance ( ).getSuperDAO ( DAOFactory.DAOType.ORDERDETAIL );
    ProductDAO productDAO = (ProductDAO) DAOFactory.getInstance ( ).getSuperDAO ( DAOFactory.DAOType.PRODUCT );


    @Override
    public boolean addCustomer(CustomerDTO customer) throws Exception {

        Connection connection =null;

        try {
            connection = DBConnection.getInstance().getConnection();

            connection.setAutoCommit(false);

            boolean b1 = customerDAO.add(new Customer ( customer.getCusId(),customer.getCusType(),
                    customer.getCusName(),customer.getCusAddress(),
                    customer.getCusCity(),customer.getCusProvince(),
                    customer.getCusContact()));

            if (!b1){
                connection.rollback();
                return false;
            }else {
                for (OrderDTO orderDTO : customer.getOrders()){
                    boolean b2 = orderDAO.add(new Order(orderDTO.getOrderId(), orderDTO.getDateTime(), orderDTO.getTotal(), orderDTO.getCusId(), orderDTO.getUserId()));

                    if (!b2) {
                        connection.rollback();
                        return false;
                    } else {
                        for (OrderDetailDTO orderDetailDTO : customer.getDetails()){
                            boolean b3 = orderDetailsDAO.add(new OrderDetail(orderDetailDTO.getQty(), orderDetailDTO.getUnitPrice(), orderDetailDTO.getDisCount(), orderDetailDTO.getOrderId(), orderDetailDTO.getProductId(),orderDetailDTO.getRefId()));

                            if (!b3){
                                connection.rollback();
                                return false;
                            }else {
                                int qtyOnHand = 0;

                                Product product = productDAO.search(orderDetailDTO.getProductId());

                                if (product != null){
                                    qtyOnHand = product.getQty();
                                }

                                boolean b4 = productDAO.updateQty(orderDetailDTO.getProductId(), orderDetailDTO.getQty());
                                System.out.println(b4);

                                if (!b4){
                                    connection.rollback();
                                    return false;
                                }

                            }

                        }
                    }
                }
                connection.commit();
            }

        }catch ( SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger( CashierFormController.class.getName()).log( Level.SEVERE, null, ex1);
            }
            Logger.getLogger(CashierFormController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            e.printStackTrace ( );
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(CashierFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;

    }

    @Override
    public boolean deleteCustomer(String id) throws Exception {
        return customerDAO.delete(id);
    }

    @Override
    public CustomerDTO searchCustomer(String id) throws Exception {
        Customer search = customerDAO.search(id);
        return new CustomerDTO(search.getCusId(),search.getCusType(),search.getCusName(),search.getCusAddress(),search.getCusCity(),search.getCusProvince(),search.getCusContact());
    }

    @Override
    public boolean updateCustomer(CustomerDTO customer) throws Exception {
        return customerDAO.update(new Customer ( customer.getCusId(),customer.getCusType(),
                customer.getCusName(),customer.getCusAddress(),
                customer.getCusCity(),customer.getCusProvince(),
                customer.getCusContact()));
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws Exception {
        List<Customer> all = customerDAO.getAll();
        ArrayList<CustomerDTO>  customerDTOS = new ArrayList<>();

        for (Customer customer: all){
            customerDTOS.add(new CustomerDTO(customer.getCusId(),customer.getCusType(),customer.getCusName(),customer.getCusAddress(),customer.getCusCity(),customer.getCusProvince(),customer.getCusContact()));
        }
        return customerDTOS;
    }

    @Override
    public String generateCustomerId() throws SQLException, ClassNotFoundException {
        ResultSet rst = customerDAO.generateCustomerId();

        if (rst.next()){
            int tempId = Integer.parseInt( rst.getString( 1 ).split( "C" )[ 1 ] );
            tempId+=1;
            if (tempId < 99){
                return "C00"+tempId;
            }else {
                return "O0"+tempId;
            }
        }
        return "C001";
    }
}
