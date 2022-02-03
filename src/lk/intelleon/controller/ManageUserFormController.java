package lk.intelleon.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
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
import lk.intelleon.bo.BOFactory;
import lk.intelleon.bo.custom.UserBO;
import lk.intelleon.dto.UserDTO;
import lk.intelleon.view.tm.UserTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ManageUserFormController {
    public AnchorPane root;
    public JFXTextField txtUserId;
    public Label lblUserId;
    public JFXTextField txtUserName;
    public Label lblUserName;
    public JFXCheckBox chetActiveState;
    public JFXTextField txtUserType;
    public JFXButton btnSave;
    public JFXButton btnClear;
    public JFXButton btnUpdate;
    public TableView tblUser;
    public TableColumn colUserId;
    public TableColumn colUserName;
    public TableColumn colPassword;
    public TableColumn colActiveState;
    public TableColumn colUserType;
    public JFXPasswordField txtPassword;
    public TableColumn colOption;

    UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOType.USER);

    public void initialize(){
        colUserId.setCellValueFactory ( new PropertyValueFactory<> ( "userId" ) );
        colUserName.setCellValueFactory ( new PropertyValueFactory<> ( "userName" ) );
        colPassword.setCellValueFactory ( new PropertyValueFactory<> ( "password" ) );
        colActiveState.setCellValueFactory ( new PropertyValueFactory<> ( "activeState" ) );
        colUserType.setCellValueFactory ( new PropertyValueFactory<> ( "userType" ) );
        colOption.setCellValueFactory ( new PropertyValueFactory<> ( "btn" ) );
        colOption.setStyle ( "-fx-alignment:center" );

        getAllUsers();
        setUserId();

        tblUser.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    setData( ( UserTm ) newValue );
                });
    }

    private void setData ( UserTm newValue ) {
        try {
            txtUserId.setText ( String.valueOf ( newValue.getUserId () ) );
            txtUserName.setText ( newValue.getUserName () );
            txtPassword.setText ( newValue.getPassword () );
            chetActiveState.setSelected ( newValue.getActiveState () );
            txtUserType.setText ( newValue.getUserType () );
        }catch ( NullPointerException e ){

        }
    }

    private void setUserId(){
        try {
            String s = userBO.generateUserId();

            txtUserId.setText( String.valueOf( s ) );
        } catch ( SQLException throwables ) {
            throwables.printStackTrace( );
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace( );
        }
    }

    public void btnSaveOnAction ( ActionEvent actionEvent ) {
        if (Pattern.compile( "^[A-z]{1,10}" ).matcher( txtUserName.getText( ) ).matches( )) {
            if (Pattern.compile( "^[0-9]{1,10}" ).matcher( txtPassword.getText( ) ).matches( )) {
                if (Pattern.compile( "^[A-z]{1,10}" ).matcher( txtUserType.getText( ) ).matches( )) {

                    UserDTO user = new UserDTO(
                            txtUserId.getText( ),
                            txtUserName.getText( ) ,
                            txtPassword.getText( ) ,
                            chetActiveState.isSelected( ) ,
                            txtUserType.getText( )
                    );
                    try {
                        if (userBO.addUser(user)) {
                            new Alert( Alert.AlertType.CONFIRMATION , "User Saved!" ).show( );
                            getAllUsers( );
                            setUserId( );
                        }
                        else {
                            new Alert( Alert.AlertType.ERROR , "Fail!" ).show( );
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else {
                    txtUserType.setFocusColor( Paint.valueOf( "red" ) );
                    txtUserType.requestFocus();
                }
            }else {
                txtPassword.setFocusColor( Paint.valueOf( "red" ) );
                txtPassword.requestFocus();
            }
        }else {
            txtUserName.setFocusColor( Paint.valueOf( "red" ) );
            txtUserName.requestFocus();
        }
    }

    public void searchUserOnAction ( ActionEvent actionEvent ) {
        try {
            UserDTO userDTO = userBO.searchUser(txtUserId.getText());

            if (userDTO!=null){
                txtUserName.setText ( userDTO.getUserName () );
                txtPassword.setText ( userDTO.getPassword () );
                chetActiveState.setSelected ( userDTO.getActiveState () );
                txtUserType.setText ( userDTO.getUserType () );
            }else {
                new Alert ( Alert.AlertType.ERROR,"Empty User !" ).show ();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void btnUpdateOnAction ( ActionEvent actionEvent ) {
        UserDTO user = new UserDTO (
                txtUserId.getText ( ) ,
                txtUserName.getText ( ) ,
                txtPassword.getText ( ) ,
                chetActiveState.isSelected ( ) ,
                txtUserType.getText ( )
        );
        try {
            if (userBO.updateUser(user)){
                new Alert ( Alert.AlertType.CONFIRMATION,"User Update!" ).show ();
                getAllUsers();
                setUserId();
            }else {
                new Alert ( Alert.AlertType.ERROR,"Fail!" ).show ();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getAllUsers(){
        try {
            ArrayList<UserDTO> allUsers = userBO.getAllUsers();

            ObservableList<UserTm> userTms = FXCollections.observableArrayList ( );

            for ( UserDTO user:allUsers) {
                JFXButton delete = new JFXButton ( "DELETE" );
                userTms.add ( new UserTm (
                        user.getUserId (),user.getUserName (),
                        user.getPassword (),user.getActiveState (),
                        user.getUserType (),delete
                ) );
                delete.setStyle ( "-fx-background-color: #ff7675;-fx-cursor: hand" );
                delete.setOnAction ( (e) ->{
                    try {
                        boolean b = userBO.deleteUser(user.getUserId());

                        if (b){
                            new Alert ( Alert.AlertType.CONFIRMATION,"Product Delete!" ).show ();
                            getAllUsers ();
                        }else {
                            new Alert ( Alert.AlertType.WARNING,"Delete Fail!" ).show ();
                        }

                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                } );
            }
            tblUser.setItems ( userTms );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnClearOnAction ( ActionEvent actionEvent ) {
        txtUserId.setText ( "" );
        txtUserName.setText ( "" );
        txtPassword.setText ( "" );
        txtUserType.setText ( "" );
    }

}
