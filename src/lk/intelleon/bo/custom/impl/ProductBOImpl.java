package lk.intelleon.bo.custom.impl;

import lk.intelleon.bo.custom.ProductBO;
import lk.intelleon.dao.DAOFactory;
import lk.intelleon.dao.SuperDAO;
import lk.intelleon.dao.custom.ProductDAO;
import lk.intelleon.dto.ProductDTO;
import lk.intelleon.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ushan Shanilka <ushanshanilka80@gmail.com>
 * @since 2/3/2022
 **/
public class ProductBOImpl implements ProductBO {

    ProductDAO productDAO = (ProductDAO) DAOFactory.getInstance().getSuperDAO(DAOFactory.DAOType.PRODUCT);


    @Override
    public boolean addProduct(ProductDTO productDTO) throws Exception {
        return productDAO.add(new Product(productDTO.getProductId(),productDTO.getName(),productDTO.getDescription(),productDTO.getSpec(),productDTO.getDisplayName(),productDTO.isAvailability(),productDTO.isActiveState(),productDTO.getBrands(),productDTO.getQty(),productDTO.getPrice()));
    }

    @Override
    public boolean deleteProduct(String id) throws Exception {
        return productDAO.delete(id);
    }

    @Override
    public ProductDTO searchProduct(String id) throws Exception {
        Product product = productDAO.search(id);
        return new ProductDTO(product.getProductId(),product.getName(),product.getDescription(),product.getSpec(),product.getDisplayName(),product.isAvailability(),product.isActiveState(),product.getBrands(),product.getQty(),product.getPrice());
    }

    @Override
    public boolean updateProduct(ProductDTO productDTO) throws Exception {
        return productDAO.update(new Product(productDTO.getProductId(),productDTO.getName(),productDTO.getDescription(),productDTO.getSpec(),productDTO.getDisplayName(),productDTO.isAvailability(),productDTO.isActiveState(),productDTO.getBrands(),productDTO.getQty(),productDTO.getPrice()));
    }

    @Override
    public ArrayList<ProductDTO> getAllProducts() throws Exception {
        List<Product> all = productDAO.getAll();
        ArrayList<ProductDTO> productDTOS = new ArrayList<>();

        for (Product product: all){
            productDTOS.add(new ProductDTO(product.getProductId(),product.getName(),product.getDescription(),product.getSpec(),product.getDisplayName(),product.isAvailability(),product.isActiveState(),product.getBrands(),product.getQty(),product.getPrice()));
        }
        return productDTOS;
    }

    @Override
    public List<ProductDTO> getAllActiveStateItems() throws SQLException, ClassNotFoundException {
        List<Product> allActiveState = productDAO.getAllActiveState();
        ArrayList<ProductDTO> products = new ArrayList<>();

        for (Product product :allActiveState){
            products.add(new ProductDTO(product.getProductId(),product.getName(),product.getDescription(),product.getSpec(),product.getDisplayName(),product.isAvailability(),product.isActiveState(),product.getBrands(),product.getQty(),product.getPrice()));
        }
        return products;
    }

    @Override
    public String generateProductId() throws SQLException, ClassNotFoundException {
        ResultSet rst = productDAO.autoGenerateID();


        if (rst.next()){
            int tempId = Integer.parseInt( rst.getString( 1 ).split( "P" )[ 1 ] );
            tempId+=1;
            if (tempId < 10){
                return "P00"+tempId;
            }else if (tempId<100){
                return "P0"+tempId;
            }
        }
        return "P001";

    }
}
