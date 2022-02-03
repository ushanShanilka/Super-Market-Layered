package lk.intelleon.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

import lk.intelleon.bo.BOFactory;
import lk.intelleon.bo.custom.CustomerBO;
import lk.intelleon.bo.custom.OrderBO;
import lk.intelleon.bo.custom.ProductBO;
import lk.intelleon.bo.custom.RefBO;
import lk.intelleon.db.DBConnection;
import lk.intelleon.dto.*;
import lk.intelleon.view.tm.TempOrderTM;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class CashierFormController {
    public JFXListView list;
    public JFXButton btnCancel;
    public JFXButton btnConfirm;
    public JFXComboBox cmbSelectPropertyId;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQty;
    public JFXTextField txtDiscount;
    public Label lblDate;
    public Label lblTime;
    public JFXTextField txtCusId;
    public JFXTextField txtCusType;
    public JFXTextField txtCusName;
    public JFXTextField txtCusAddress;
    public JFXTextField txtCusCity;
    public JFXTextField txtCusProvince;
    public JFXTextField txtCusContact;
    public JFXTextField txtOrderId;
    public JFXTextField txtProductName;
    public JFXTextField txtOrderQty;
    public TableView tblTempOrder;
    public TableColumn colPropertyId;
    public TableColumn colProductName;
    public TableColumn colUnitPrice;
    public TableColumn colQty;
    public TableColumn colDiscount;
    public TableColumn colTotal;
    public ImageView btnBack;
    public JFXButton btnAdd;
    public Label lblTotal;
    public Label lblCashierId;
    public TableColumn colOption;
    public JFXButton btnPlaceOrder;
    public JFXButton btnNew;
    public JFXComboBox cmbRef;

    static ArrayList< TempDataDTO > temps = new ArrayList<>( );

    static ArrayList< TempTableDTO > tempTableArray = new ArrayList<>( );


    CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOType.CUSTOMER);
    ProductBO productBO = (ProductBO) BOFactory.getInstance().getBO(BOFactory.BOType.PRODUCT);
    OrderBO orderBO = (OrderBO) BOFactory.getInstance().getBO(BOFactory.BOType.ORDER);
    RefBO refBO = (RefBO) BOFactory.getInstance().getBO(BOFactory.BOType.REF);

    public void initialize(){
        colPropertyId.setCellValueFactory( new PropertyValueFactory<>( "propertyId" ) );
        colProductName.setCellValueFactory( new PropertyValueFactory<>( "productName" ) );
        colUnitPrice.setCellValueFactory( new PropertyValueFactory<>( "unitPrice" ) );
        colQty.setCellValueFactory( new PropertyValueFactory<>( "qty" ) );
        colDiscount.setCellValueFactory( new PropertyValueFactory<>( "discount" ) );
        colTotal.setCellValueFactory( new PropertyValueFactory<>( "total" ) );
        colOption.setCellValueFactory( new PropertyValueFactory<>( "btn" ) );

        getAllPropertyId();
        getAllRefs();
        generateDateTime();
        setOrderId();
        setCustomerId();
        lblCashierId.setText(  CashierLoginFormController.userId );
        //setBtnValid(true);
    }

    public void btnAddOnAction ( ActionEvent actionEvent ) {
        if (Pattern.compile( "^(C)[0-9]{1,5}" ).matcher( txtCusId.getText( ) ).matches( )) {
            if (Pattern.compile( "^[A-z]{1,10}" ).matcher( txtCusType.getText( ) ).matches( )) {
                if (Pattern.compile( "^[A-z]{1,20}" ).matcher( txtCusName.getText( ) ).matches( )) {
                    if (Pattern.compile( "^[A-z]{1,20}(, )[A-z]{1,20}" ).matcher( txtCusAddress.getText( ) ).matches( )) {
                        if (Pattern.compile( "^[A-z]{1,20}" ).matcher( txtCusCity.getText( ) ).matches( )) {
                            if (Pattern.compile( "^[A-z]{1,10}" ).matcher( txtCusProvince.getText( ) ).matches( )) {
                                if (Pattern.compile( "^[0-9]{9,10}" ).matcher( txtCusContact.getText( ) ).matches( )) {
//                                    if (Pattern.compile( "^[A-z]{1,20}( )[0-9]{1,20}[A-z]{1,3}" ).matcher( txtProductName.getText( ) ).matches( )) {
                                        if (Pattern.compile( "^[0-9]{1,10}" ).matcher( txtOrderQty.getText( ) ).matches( )) {
                                            String date = lblDate.getText( );
                                            String cashierId = CashierLoginFormController.userId;

                                            int qty = Integer.parseInt( txtOrderQty.getText( ) );
                                            double uniPrice = Double.parseDouble( txtUnitPrice.getText( ) );
                                            double dic = Double.parseDouble( txtDiscount.getText( ) );

                                            double dicTot = (dic * qty);
                                            double subTot = (uniPrice * qty) - (dic * qty);

                                            TempDataDTO tempData = new TempDataDTO(
                                                    txtOrderId.getText( ) ,
                                                    date ,
                                                    txtCusId.getText( ) ,
                                                    txtCusType.getText( ) ,
                                                    txtCusName.getText( ) ,
                                                    txtCusAddress.getText( ) ,
                                                    txtCusCity.getText( ) ,
                                                    txtCusProvince.getText( ) ,
                                                    Integer.parseInt( txtCusContact.getText( ) ) ,
                                                    cashierId ,
                                                    subTot,
                                                    String.valueOf(cmbRef.getValue())
                                            );

                                            int rowNumber1 = isExistsTempData( tempData );

                                            if (rowNumber1==-1) {
                                                temps.add( tempData );
                                                tblTempOrder.refresh( );
                                            }
                                            else {
                                                tblTempOrder.refresh( );
                                            }

                                            TempTableDTO tempTable = new TempTableDTO(
                                                    txtOrderId.getText( ) ,
                                                    String.valueOf( cmbSelectPropertyId.getValue( ) ) ,
                                                    txtProductName.getText( ) ,
                                                    Double.parseDouble( txtUnitPrice.getText( ) ) ,
                                                    Integer.parseInt( txtOrderQty.getText( ) ) ,
                                                    Double.parseDouble( String.valueOf( dicTot ) ) ,
                                                    Double.parseDouble( String.valueOf( subTot ) ),
                                                    String.valueOf(cmbRef.getValue())
                                            );

                                            int rowNumber = isExists( tempTable );
                                            if (rowNumber==-1) {
                                                if (Integer.parseInt( txtQty.getText( ) ) >= Integer.parseInt( txtOrderQty.getText( ) )) {
                                                    tempTableArray.add( tempTable );
                                                    getAllProcessingOrder( );
                                                }
                                                else {
                                                    new Alert( Alert.AlertType.WARNING , "Out of Bounds" ).show( );
                                                }
                                            }
                                            else {
                                                if (Integer.parseInt( txtQty.getText( ) ) >= tempTableArray.get( rowNumber ).getQty( ) + Integer.parseInt( txtOrderQty.getText( ) )) {
                                                    tempTableArray.get( rowNumber ).setQty( tempTableArray.get( rowNumber ).getQty( ) + Integer.parseInt( txtOrderQty.getText( ) ) );
                                                    tempTableArray.get( rowNumber ).setSubTotal( tempTableArray.get( rowNumber ).getSubTotal( ) + subTot );
                                                    tempTableArray.get( rowNumber ).setDiscount( tempTableArray.get( rowNumber ).getDiscount( ) + dicTot );
                                                    tblTempOrder.refresh( );
                                                }else {
                                                    new Alert( Alert.AlertType.WARNING , "Out of Bounds" ).show( );
                                                }
                                            }
                                            getAllProcessingOrder( );
                                            generateTotal( );
                                            txtOrderQty.setText("");
                                            //setBtnValid(false);
                                        }else {
                                            txtOrderQty.setFocusColor( Paint.valueOf( "red" ) );
                                            txtOrderQty.requestFocus( );
                                        }
//                                    }else {
//                                        txtProductName.setFocusColor( Paint.valueOf( "red" ) );
//                                        txtProductName.requestFocus( );
//                                    }
                                }else {
                                    txtCusContact.setFocusColor( Paint.valueOf( "red" ) );
                                    txtCusContact.requestFocus( );
                                }
                            }else {
                                txtCusProvince.setFocusColor( Paint.valueOf( "red" ) );
                                txtCusProvince.requestFocus( );
                            }
                        }else {
                            txtCusCity.setFocusColor( Paint.valueOf( "red" ) );
                            txtCusCity.requestFocus( );
                        }
                    }else {
                        txtCusAddress.setFocusColor( Paint.valueOf( "red" ) );
                        txtCusAddress.requestFocus( );
                    }
                }else {
                    txtCusName.setFocusColor( Paint.valueOf( "red" ) );
                    txtCusName.requestFocus( );
                }
            }else {
                txtCusType.setFocusColor( Paint.valueOf( "red" ) );
                txtCusType.requestFocus( );
            }
        }else {
            txtCusId.setFocusColor( Paint.valueOf( "red" ) );
            txtCusId.requestFocus( );
        }
    }

    public void btnConfirmOnAction ( ActionEvent actionEvent ) {
        try{
            int tempId = Integer.parseInt( txtOrderId.getText().split( "O" )[ 1 ] );
            tempId+=1;
            if (tempId < 10){
                txtOrderId.setText("O00"+tempId);
            }else if (tempId<100){
                txtOrderId.setText("O0"+tempId);
            }

            getAllOrderIdFromArray();
            getAllPropertyId();
            clear();
            tblTempOrder.setItems( null );
            //setBtnValid(true);
        }catch ( Exception e ){

        }
    }

    public void cmbSelectPropertyId ( ActionEvent actionEvent ) {
        try {
            List<ProductDTO> allActiveStateItems = productBO.getAllActiveStateItems();

            for ( ProductDTO product:allActiveStateItems) {
                if (cmbSelectPropertyId.getValue().equals( product.getProductId() )){
                    txtUnitPrice.setText( String.valueOf( product.getPrice() ) );
                    txtQty.setText( String.valueOf( product.getQty() ) );
                    txtProductName.setText( product.getDisplayName() );
                }else {

                }
            }
        } catch (SQLException | ClassNotFoundException throwables ) {
            throwables.printStackTrace( );
        }catch (NullPointerException e){

        }
    }

    public void click ( MouseEvent mouseEvent ) {
        for ( TempDataDTO data:temps ) {
            if (this.list.getSelectionModel().selectedItemProperty().getValue().equals( data.getOrderId() )){
                lblCashierId.setText(data.getCashierId() );
                txtOrderId.setText( data.getOrderId() );
                txtCusId.setText( data.getCusId() );
                txtCusType.setText( data.getCusType() );
                txtCusName.setText(data.getCusName());
                txtCusAddress.setText(data.getCusAddress());
                txtCusCity.setText(data.getCusCity());
                txtCusProvince.setText(data.getCusProvince());
                txtCusContact.setText( String.valueOf( data.getCusContact() ) );
                cmbRef.setValue(String.valueOf(data.getRefId()));
            }
        }
        ObservableList<TempOrderTM> list = FXCollections.observableArrayList( );

        for ( TempTableDTO data:tempTableArray) {
            JFXButton btn = new JFXButton( "Delete" );
            if (this.list.getSelectionModel().selectedItemProperty().getValue().equals( data.getOrderId() )){
                list.add( new TempOrderTM( data.getPropertyId(),data.getProductName(),data.getUnitPrice(),data.getQty(),data.getDiscount(),data.getSubTotal(),btn ) );
            }
            btn.setStyle ( "-fx-background-color: #ff7675;-fx-cursor: hand" );
            btn.setOnAction( (e)->{
                for ( int i = 0; i < tempTableArray.size( ); i++ ) {
                    TempTableDTO table = tempTableArray.get( i );
                    if (txtOrderId.getText( ).equals( table.getOrderId( ) )){
                        if (data.getPropertyId( ).equals( table.getPropertyId( ) )){
                            boolean remove = tempTableArray.remove( table );
                            if (remove){
                                getAllProcessingOrder();
                            }
                        }
                    }
                }
            } );
            lblTotal.setText( String.valueOf( data.getSubTotal() ) );
        }
        tblTempOrder.setItems( list );
    }

    public void btnBackOnAction ( MouseEvent mouseEvent ) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();

        try {
            Scene scene = new Scene ( FXMLLoader.load ( getClass ( ).getResource ( "../view/MainDashBoardForm.fxml" ) ) );
            Stage primaryStage = new Stage( );
            primaryStage.setScene( scene );
            primaryStage.show( );
        } catch ( IOException e ) {
            e.printStackTrace ( );
        }
    }

    public void btnPlaceOrderOnAction ( ActionEvent actionEvent ) {
        CustomerDTO customer = new CustomerDTO(
                txtCusId.getText( ) ,
                txtCusType.getText( ) ,
                txtCusName.getText( ) ,
                txtCusAddress.getText( ) ,
                txtCusCity.getText( ) ,
                txtCusProvince.getText( ) ,
                Integer.parseInt( txtCusContact.getText( ) )
        );
        ArrayList<OrderDTO> orders = new ArrayList<>( );
        OrderDTO order = new OrderDTO( txtOrderId.getText( ) , lblDate.getText(), Double.parseDouble( lblTotal.getText( ) ) , txtCusId.getText( ) , lblCashierId.getText( ) );
        orders.add(order);
        customer.setOrders(orders);


        ArrayList< OrderDetailDTO > details = new ArrayList<>( );
        for ( TempTableDTO tm : tempTableArray) {
            if (txtOrderId.getText().equals( tm.getOrderId() )){
                details.add( new OrderDetailDTO( tm.getQty( ) , tm.getUnitPrice( ),tm.getDiscount() , txtOrderId.getText() , tm.getPropertyId( ), tm.getRefId() ) );
            }
        }
        customer.setDetails(details);

        try {
            if (customerBO.addCustomer(customer)){
                deleteTempData();
                new Alert( Alert.AlertType.CONFIRMATION,"Success ! " ).show();
                getAllOrderIdFromArray();
                getAllProcessingOrder();
                clear();
                showReport();
                tblTempOrder.setItems(null);
            }else {
                new Alert( Alert.AlertType.WARNING,"Fail !" ).show();
            }
        } catch ( SQLException throwables ) {
            throwables.printStackTrace( );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnCancelOnAction ( ActionEvent actionEvent ) {
        deleteTempData();
        getAllOrderIdFromArray();
        getAllProcessingOrder();
        clear();
    }

    public void btnNewOnAction ( ActionEvent actionEvent ) {
        clear();
        setOrderId();
        //setBtnValid(true);
        tblTempOrder.setItems(null);
        txtCusType.requestFocus();
    }

    public void searchCustomerOnAction ( ActionEvent actionEvent ) {
        try {
            CustomerDTO customerDTO = customerBO.searchCustomer(txtCusId.getText());

            if (customerDTO!=null){
                txtCusType.setText( customerDTO.getCusType() );
                txtCusName.setText( customerDTO.getCusName() );
                txtCusAddress.setText( customerDTO.getCusAddress() );
                txtCusCity.setText( customerDTO.getCusCity() );
                txtCusProvince.setText( customerDTO.getCusProvince() );
                txtCusContact.setText( String.valueOf( customerDTO.getCusContact() ) );
            }else {
                new Alert( Alert.AlertType.ERROR,"Empty Customer !" ).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setOrderId(){
        try {
            String s = orderBO.generateOrderId();

            txtOrderId.setText( s );
        } catch ( SQLException throwables ) {
            throwables.printStackTrace( );
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace( );
        }

    }

    private void setCustomerId(){
        try {
            String s = customerBO.generateCustomerId();

            txtCusId.setText( s );
        } catch ( SQLException throwables ) {
            throwables.printStackTrace( );
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace( );
        }
    }

    public void generateDateTime() {
        lblDate.setText( LocalDate.now().toString());

        Timeline timeline = new Timeline( new KeyFrame( Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "hh:mm:ss a");
            lblTime.setText( LocalDateTime.now().format( formatter));
        }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount( Animation.INDEFINITE);
        timeline.play();
    }

    private void deleteTempData(){
        for ( int i = 0; i < temps.size( ); i++ ) {
            TempDataDTO data = temps.get( i );
            if (txtOrderId.getText().equals( data.getOrderId() )){
                boolean remove = temps.remove( data );
                if (remove){
                    for ( int j = 0; j < tempTableArray.size( ); j++ ) {
                        TempTableDTO table = tempTableArray.get( j );
                        if (txtOrderId.getText().equals( table.getOrderId() )){
                            tempTableArray.remove( table );
                        }
                    }
                    list.refresh();
                    tblTempOrder.refresh();
                }
            }
        }
    }

    private int isExistsTempData ( TempDataDTO tempData ) {
        for ( int i = 0; i < temps.size( ); i++ ) {
            if (temps.get( i ).getOrderId().equals( tempData.getOrderId() )){
                return i;
            }
        }
        return -1;
    }

    private int isExists ( TempTableDTO tempTable ) {
        for ( int i = 0; i < tempTableArray.size( ); i++ ) {
            if (tempTableArray.get( i ).getOrderId().equals( txtOrderId.getText() ) && tempTableArray.get( i ).getPropertyId().equals( tempTable.getPropertyId() )){
                return i;
            }
        }
        return -1;
    }

    double totalCost = 0.0;
    int qty = 0;
    public void generateTotal(){
        qty = Integer.parseInt( txtOrderQty.getText( ) );
        double uniPrice = Double.parseDouble(txtUnitPrice.getText( ));
        double dic = Double.parseDouble( txtDiscount.getText( ) );

        double temp = ((uniPrice * qty) - (dic * qty));
        totalCost+=temp;

        lblTotal.setText( String.valueOf( totalCost ) );
    }

    public void getAllProcessingOrder(){
        tblTempOrder.setItems( null );
        ObservableList< TempOrderTM > list = FXCollections.observableArrayList( );

        for ( TempTableDTO data:tempTableArray) {
            JFXButton btn = new JFXButton( "Delete" );
            if (txtOrderId.getText().equals( data.getOrderId() )){
                list.add( new TempOrderTM( data.getPropertyId(),data.getProductName(),
                                           data.getUnitPrice(),data.getQty(),
                                           data.getDiscount(),data.getSubTotal(),btn
                ) );
            }
            btn.setStyle ( "-fx-background-color: #ff7675;-fx-cursor: hand" );
            btn.setOnAction( (e)->{
                for ( int i = 0; i < tempTableArray.size( ); i++ ) {
                    TempTableDTO table = tempTableArray.get( i );
                    if (txtOrderId.getText( ).equals( table.getOrderId( ) )){
                        if (data.getPropertyId( ).equals( table.getPropertyId( ) )){
                            boolean remove = tempTableArray.remove( table );
                            if (remove){
                                getAllProcessingOrder();
                            }
                        }
                    }
                }
            } );
        }
        tblTempOrder.setItems( list );
    }

    public void getAllOrderIdFromArray(){
        ObservableList< String > obs = FXCollections.observableArrayList( );
        for ( TempDataDTO data:temps ) {
            obs.add( data.getOrderId() );
        }
        list.setItems( obs );
    }

    public void getAllPropertyId(){
        try {
            List<ProductDTO> all = productBO.getAllActiveStateItems();

            ObservableList< String > productId = FXCollections.observableArrayList( );
            for ( ProductDTO dto:all) {
                productId.add( dto.getProductId() );
                System.out.println(dto.getProductId());
            }
            cmbSelectPropertyId.setItems( productId );
        } catch (SQLException | ClassNotFoundException throwables ) {
            throwables.printStackTrace();
        }
    }

    public void getAllRefs(){
        try {
            ArrayList<RefDTO> allRefs = refBO.getAllRefs();

            ObservableList< String > refs = FXCollections.observableArrayList( );
            for ( RefDTO dto:allRefs) {
                refs.add( dto.getRefId() );
            }
            cmbRef.setItems( refs );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setBtnValid(boolean x){
        btnPlaceOrder.setDisable(x);
        btnConfirm.setDisable(x);
        btnCancel.setDisable(x);
    }

    private void clear(){
        setCustomerId();
        txtCusType.setText( "" );
        txtCusName.setText( "" );
        txtCusAddress.setText( "" );
        txtCusCity.setText( "" );
        txtCusProvince.setText( "" );
        txtCusContact.setText( "" );
        txtProductName.setText( "" );
        txtUnitPrice.setText( "" );
        txtQty.setText( "" );
        txtDiscount.setText( "" );
        txtOrderQty.setText( "" );
        lblTotal.setText( "" );
    }

    public void showReport(){
        try {
            Connection connection = DBConnection.getInstance().getConnection();

            JasperPrint jp;
            Map param = new HashMap();

            jp = JasperFillManager.fillReport("report/jasperReport.jasper",
                    param,connection);

            JasperViewer viewer = new JasperViewer(jp,false);
            viewer.setTitle("Super-Market");
            viewer.setVisible(true);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (JRException e) {
            e.printStackTrace();
        }

    }

}
