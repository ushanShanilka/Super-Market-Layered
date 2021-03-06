package lk.intelleon.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import lk.intelleon.bo.BOFactory;
import lk.intelleon.bo.SuperBO;
import lk.intelleon.bo.custom.ProductBO;
import lk.intelleon.dto.ProductDTO;
import lk.intelleon.entity.Product;
import lk.intelleon.view.tm.ProductTM;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class ManageItemFormController {
    public JFXTextField txtPropertyId;
    public JFXTextField txtBatch;
    public JFXTextField txtPrice;
    public JFXTextField txtDiscount;
    public JFXCheckBox setDiscount;
    public JFXCheckBox setActiveState;
    public JFXTextField txtQty;
    public JFXComboBox cmbProductId;
    public JFXButton btnClear;
    public JFXButton btnSave;
    public AnchorPane root;
    public Label lblDate;
    public Label lblTime;
    public JFXButton btnUpdate;
    public TableView tblItem;
    public TableColumn colPropertyId;
    public TableColumn colBatch;
    public TableColumn colPrice;
    public TableColumn colDiscount;
    public TableColumn colActivateState;
    public TableColumn colQty;
    public TableColumn colProductID;
    public TableColumn colOption;

    ProductBO productBO = (ProductBO) BOFactory.getInstance().getBO(BOFactory.BOType.PRODUCT);

    public void initialize(){
        colPropertyId.setCellValueFactory ( new PropertyValueFactory<> ( "propertyId" ) );
        colBatch.setCellValueFactory ( new PropertyValueFactory<> ( "batch" ) );
        colPrice.setCellValueFactory ( new PropertyValueFactory<> ( "price" ) );
        colDiscount.setCellValueFactory ( new PropertyValueFactory<> ( "discount" ) );
        colActivateState.setCellValueFactory ( new PropertyValueFactory<> ( "activeState" ) );
        colQty.setCellValueFactory ( new PropertyValueFactory<> ( "qty" ) );
        colProductID.setCellValueFactory ( new PropertyValueFactory<> ( "productId" ) );
        colOption.setCellValueFactory ( new PropertyValueFactory<> ( "btn" ) );
        colOption.setStyle ( "-fx-alignment:center" );

        getAllItems();
        generateDateTime();
        getAllProductId();
        txtDiscount.setDisable ( true );
        generateId();

        tblItem.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    setData( ( ProductTM ) newValue );
                });
    }

    private void setData ( ProductTM tm ) {
        try {
        txtPropertyId.setText( tm.getProductId() );
        txtPrice.setText( String.valueOf( tm.getPrice() ) );
        txtDiscount.setText( "1000" );
        setActiveState.setSelected( tm.isActiveState() );
        txtQty.setText( String.valueOf( tm.getQty() ) );
        cmbProductId.setValue( tm.getProductId() );
        }catch ( NullPointerException e ){

        }
    }

    public void generateDateTime() {
        lblDate.setText( LocalDate.now().toString());

        Timeline timeline = new Timeline( new KeyFrame ( Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "hh:mm:ss a");
            lblTime.setText( LocalDateTime.now().format( formatter));
        }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount( Animation.INDEFINITE);
        timeline.play();
    }

    public void getAllProductId(){
        try {

            List<ProductDTO> allActiveStateItems = productBO.getAllActiveStateItems();

            ObservableList< String >  productObservableList = FXCollections.observableArrayList ( );

            for ( ProductDTO product:allActiveStateItems) {
                productObservableList.add ( product.getProductId () );
            }
            cmbProductId.setItems ( productObservableList );

        } catch ( SQLException throwables ) {
            throwables.printStackTrace ( );
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace ( );
        }
    }

    public void btnSaveOnAction ( ActionEvent actionEvent ) {
//        if (Pattern.compile( "^(B)[0-9]{1,5}" ).matcher( txtBatch.getText( ) ).matches( )) {
//            if (Pattern.compile( "^[0-9]{1,9}$" ).matcher( txtPrice.getText( ) ).matches( )) {
//                if (Pattern.compile( "^[0-9]{1,9}$" ).matcher( txtDiscount.getText( ) ).matches( )) {
//                    if (Pattern.compile( "^[0-9]{1,5}$" ).matcher( txtQty.getText( ) ).matches( )) {
////
////                        SimpleDateFormat formatter = new SimpleDateFormat( "dd/MM/yyyy HH:mm" );
////                        Date date = new Date( );
//
//                        ProductDTO dto = new ProductDTO(
//                                txt
//                                Integer.parseInt(txtQty.getText()),
//                                BigDecimal.valueOf( Long.parseLong( txtPrice.getText( ) ) )
//                        );
//
//                        try {
//                            if (new ItemController( ).saveBatch( item )) {
//                                new Alert( Alert.AlertType.CONFIRMATION , "Saved" ).show( );
//                                getAllItems( );
//                                generateId( );
//                            }
//                            else {
//                                new Alert( Alert.AlertType.CONFIRMATION , "Fail" ).show( );
//                            }
//
//                        } catch ( SQLException throwables ) {
//                            throwables.printStackTrace( );
//                        } catch ( ClassNotFoundException e ) {
//                            e.printStackTrace( );
//                        }
//                    }else {
//                        txtQty.setFocusColor( Paint.valueOf( "red" ) );
//                        txtQty.requestFocus();
//                    }
//                }else {
//                    txtDiscount.setFocusColor( Paint.valueOf( "red" ) );
//                    txtDiscount.requestFocus();
//                }
//            }else {
//                txtPrice.setFocusColor( Paint.valueOf( "red" ) );
//                txtPrice.requestFocus();
//            }
//        }else {
//            txtBatch.setFocusColor( Paint.valueOf( "red" ) );
//            txtBatch.requestFocus();
//        }
    }

    public void getAllItems(){
//        try {
//            List< Item > allItems = new ItemController ( ).getAllItems ( );
//
//            ObservableList< ItemTM > tms = FXCollections.observableArrayList ( );
//
//            for ( Item item:allItems ) {
//                JFXButton delete = new JFXButton ( "DELETE" );
//                tms.add ( new ItemTM ( item.getPropertyId (), item.getBatch (), item.getPrice (),
//                                       item.isDiscountState (), item.getDiscount (), item.isActiveState (),
//                                       item.getQty (), item.getDateTime (), ( String ) item.getProductId () , delete) );
//                delete.setStyle ( "-fx-background-color: #ff7675;-fx-cursor: hand" );
//                delete.setOnAction ( (e) ->{
//                    try {
//                        boolean b = new ItemController ( ).deleteItem ( item.getPropertyId () );
//                        if (b){
//                            new Alert ( Alert.AlertType.CONFIRMATION,"Batch Delete!" ).show ();
//                            getAllItems ();
//                        }else {
//                            new Alert ( Alert.AlertType.WARNING,"Delete Fail!" ).show ();
//                        }
//                    } catch ( SQLException throwables ) {
//                        throwables.printStackTrace ( );
//                    } catch ( ClassNotFoundException notFoundException ) {
//                        notFoundException.printStackTrace ( );
//                    }
//                } );
//            }
//            tblItem.setItems ( tms );
//
//        } catch ( SQLException throwables ) {
//            throwables.printStackTrace ( );
//        } catch ( ClassNotFoundException e ) {
//            e.printStackTrace ( );
//        }
    }

    public void setDiscountOnAction ( ActionEvent actionEvent ) {
        if (setDiscount.isSelected ()){
            txtDiscount.setDisable ( false );
            txtDiscount.setText ( "0" );
        }else {
            txtDiscount.setDisable ( true );
        }
    }

    public void btnUpdateOnAction ( ActionEvent actionEvent ) {
//        SimpleDateFormat formatter=new SimpleDateFormat ( "dd/MM/yyyy HH:mm" );
//        Date date=new Date ( );
//
//        Item item = new Item (
//                txtPropertyId.getText ( ) ,
//                txtBatch.getText ( ) ,
//                BigDecimal.valueOf ( Double.parseDouble ( txtPrice.getText ( ) ) ),
//                setDiscount.isSelected (),
//                BigDecimal.valueOf ( Double.parseDouble ( txtDiscount.getText ( ) )) ,
//                setActiveState.isSelected ( ) ,
//                Integer.parseInt ( txtQty.getText ( ) ),
//                formatter.format ( date ),
//                String.valueOf ( cmbProductId.getValue ( ) )
//        );
//        try {
//            if (new ItemController ().updateItem ( item )){
//                new Alert(Alert.AlertType.CONFIRMATION, "Updated...").show();
//                getAllItems();
//            }else {
//                new Alert(Alert.AlertType.WARNING, "Try Again...").show();
//            }
//        } catch ( SQLException throwables ) {
//            throwables.printStackTrace ( );
//        } catch ( ClassNotFoundException e ) {
//            e.printStackTrace ( );
//        }
    }

    public void btnClearOnAction ( ActionEvent actionEvent ) {
        clearTextFields();
    }

    public void clearTextFields(){
        txtPropertyId.setText ( "");
        txtBatch.setText ( "");
        txtPrice.setText ("" );
        txtDiscount.setText ("" );
        txtQty.setText ("" );
        cmbProductId.setValue (null );
    }

    public void searchPropertyOnAction ( ActionEvent actionEvent ) {
//        try {
//            Item item = new ItemController ( ).searchItem ( txtPropertyId.getText ( ) );
//            if (item!=null){
//                txtBatch.setText ( item.getBatch () );
//                txtPrice.setText ( String.valueOf ( item.getPrice () ) );
//                setDiscount.setSelected ( item.isDiscountState () );
//                txtDiscount.setText ( String.valueOf ( item.getDiscount () ) );
//                setActiveState.setSelected ( item.isActiveState () );
//                txtQty.setText ( String.valueOf ( item.getQty () ) );
//                cmbProductId.setValue ( item.getProductId () );
//            }else {
//                new Alert ( Alert.AlertType.ERROR,"Empty" ).show ();
//            }
//        } catch ( SQLException throwables ) {
//            throwables.printStackTrace ( );
//        } catch ( ClassNotFoundException e ) {
//            e.printStackTrace ( );
//        }
    }

    public void generateId(){
//        try {
//            ResultSet resultSet = new ItemController( ).autoGenerateID( );
//            if (resultSet.next()){
//                String oldId = resultSet.getString( 1 );
//                String substring = oldId.substring( 5 , 7 );
//                int intId = Integer.parseInt( substring );
//
//                intId = intId + 1;
//                if (intId<10){
//                    txtPropertyId.setText( "P00-00"+intId );
//                }else if (intId<100){
//                    txtPropertyId.setText( "P00-0"+intId );
//                }else if (intId<1000){
//                    txtPropertyId.setText( "P00-"+intId );
//                }
//            }else {
//                txtPropertyId.setText( "P00-001" );
//            }
//        } catch ( SQLException throwables ) {
//            throwables.printStackTrace( );
//        } catch ( ClassNotFoundException e ) {
//            e.printStackTrace( );
//        }

    }
}
