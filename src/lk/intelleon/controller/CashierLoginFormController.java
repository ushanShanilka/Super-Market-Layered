package lk.intelleon.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import lk.intelleon.bo.BOFactory;
import lk.intelleon.bo.SuperBO;
import lk.intelleon.bo.custom.UserBO;
import lk.intelleon.entity.User;


import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class CashierLoginFormController {
    public JFXPasswordField txtPassword;
    public JFXButton btnLogin;
    public JFXButton btnCancel;
    public JFXTextField txtUserId;
    public static String userId;

    UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOType.USER);

    public void loginOnAction ( ActionEvent actionEvent ) {

        try {
            User activeUsers = userBO.getActiveUsers(txtUserId.getText(), txtPassword.getText());
            userId=txtUserId.getText();
            if (activeUsers!=null){
                Stage stage = (Stage) btnCancel.getScene().getWindow();
                stage.close();

                Scene scene = new Scene( FXMLLoader.load( getClass( ).getResource( "../view/CashierForm.fxml" ) ) );
                Stage ps = new Stage( );
                ps.setScene( scene );
                ps.show();

            }else {
                new Alert( Alert.AlertType.WARNING,"Something went Wrong.Please Contact Admin !  " ).show();
            }
        } catch (SQLException | ClassNotFoundException | IOException throwables ) {
            throwables.printStackTrace ( );
        }
    }

    public void cancelOnActon ( ActionEvent actionEvent ) {
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

    public void txtUserNameKeyReleasedOnAction ( KeyEvent keyEvent ) {
        if ( Pattern.compile( "^[1,9]{1,5}$").matcher( txtUserId.getText()).matches()) {
            btnLogin.setDisable ( false );
            txtUserId.setFocusColor( Paint.valueOf( "blue"));
        }else {
            txtUserId.setFocusColor(Paint.valueOf("red"));
            btnLogin.setDisable ( true );
        }
    }

    public void txtPwKeyReleasedOnAction ( KeyEvent keyEvent ) {
        if (Pattern.compile( "^[0-9]{1,4}$").matcher( txtPassword.getText()).matches()) {
            btnLogin.setDisable ( false );
            txtPassword.setFocusColor( Paint.valueOf( "blue"));
        }else {
            txtPassword.setFocusColor( Paint.valueOf( "red"));
            btnLogin.setDisable ( true );
        }
    }
}
