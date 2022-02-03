package lk.intelleon.bo.custom;


import lk.intelleon.bo.SuperBO;
import lk.intelleon.dto.ProductDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ushan Shanilka <ushanshanilka80@gmail.com>
 * @since 2/3/2022
 **/
public interface ProductBO extends SuperBO {
    boolean addProduct( ProductDTO productDTO) throws Exception ;

    boolean deleteProduct(String id) throws Exception ;

    ProductDTO searchProduct(String id) throws Exception ;

    boolean updateProduct(ProductDTO productDTO) throws Exception ;

    ArrayList<ProductDTO> getAllProducts() throws Exception ;

    List<ProductDTO> getAllActiveStateItems() throws SQLException, ClassNotFoundException;

    String generateProductId() throws SQLException, ClassNotFoundException;
}
