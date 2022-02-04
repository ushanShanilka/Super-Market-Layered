package lk.intelleon.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.intelleon.bo.BOFactory;
import lk.intelleon.bo.SuperBO;
import lk.intelleon.bo.custom.RefBO;
import lk.intelleon.dto.RefDTO;
import lk.intelleon.view.tm.RefTM;
import lk.intelleon.view.tm.UserTm;

import java.util.ArrayList;

/**
 * @author Ushan Shanilka <ushanshanilka80@gmail.com>
 * @since 2/4/2022
 **/
public class ManageRefFormController {
    public AnchorPane root;
    public JFXTextField txtRefId;
    public JFXTextField txtRefName;
    public JFXTextField txtIdNumber;
    public JFXTextField txtTel;
    public JFXButton btnClear;
    public JFXButton btnUpdate;
    public TableView tblRef;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colIdNumber;
    public TableColumn colTel;
    public TableColumn colAddress;
    public JFXTextField txtAddress;
    public JFXButton btnSave;
    public TableColumn colOption;

    RefBO refBO = (RefBO) BOFactory.getInstance().getBO(BOFactory.BOType.REF);

    public void initialize(){
        colId.setCellValueFactory ( new PropertyValueFactory<>( "refId" ) );
        colName.setCellValueFactory ( new PropertyValueFactory<>( "name" ) );
        colIdNumber.setCellValueFactory ( new PropertyValueFactory<>( "idNumber" ) );
        colTel.setCellValueFactory ( new PropertyValueFactory<>( "tel" ) );
        colAddress.setCellValueFactory ( new PropertyValueFactory<>( "address" ) );
        colOption.setCellValueFactory ( new PropertyValueFactory<>( "btn" ) );
        colOption.setStyle ( "-fx-alignment:center" );

        getALlRefs();

        tblRef.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    setData( ( RefTM ) newValue );
                });
    }

    private void setData ( RefTM newValue ) {
        try {
            txtRefId.setText ( String.valueOf ( newValue.getRefId () ) );
            txtRefName.setText ( newValue.getName () );
            txtIdNumber.setText ( newValue.getIdNumber () );
            txtTel.setText (String.valueOf(newValue.getTel ()));
            txtAddress.setText ( newValue.getAddress () );
        }catch ( NullPointerException e ){

        }
    }

    public void txtRefIdOnAction(ActionEvent actionEvent) {
        try {
            RefDTO refDTO = refBO.searchRef(txtRefId.getText());

            if (refDTO !=null){
                txtRefName.setText(refDTO.getName());
                txtIdNumber.setText(refDTO.getIdNumber());
                txtTel.setText(String.valueOf(refDTO.getTel()));
                txtAddress.setText(refDTO.getAddress());
            }
        } catch (Exception e) {
            new Alert ( Alert.AlertType.WARNING,"Empty Ref!" ).show ();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtRefId.setText("");
        txtTel.setText("");
        txtRefName.setText("");
        txtAddress.setText("");
        txtIdNumber.setText("");
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

        RefDTO refDTO = new RefDTO(
                txtRefId.getText(),
                txtRefName.getText(),
                txtIdNumber.getText(),
                Integer.parseInt(txtTel.getText()),
                txtAddress.getText()
        );

        try {
            if (refBO.updateRef(refDTO)){
                new Alert( Alert.AlertType.CONFIRMATION , "Ref Update!" ).show( );
                getALlRefs();
            }else {
                new Alert( Alert.AlertType.ERROR , "Fail!" ).show( );
            }
        } catch (Exception e) {
            new Alert( Alert.AlertType.ERROR , "Try Again!" ).show( );
        }

    }

    public void getALlRefs(){
        try {
            ArrayList<RefDTO> allRefs = refBO.getAllRefs();

            ObservableList<RefTM> refTMS = FXCollections.observableArrayList();

            for (RefDTO refDTO : allRefs){
                JFXButton delete = new JFXButton ( "DELETE" );
                refTMS.add(new RefTM(
                        refDTO.getRefId(),refDTO.getName(),
                        refDTO.getIdNumber(),refDTO.getTel(),
                        refDTO.getAddress(),delete
                ));
                delete.setStyle ( "-fx-background-color: #ff7675;-fx-cursor: hand" );
                delete.setOnAction ( (e) ->{
                    try {
                        boolean b = refBO.deleteRef(refDTO.getRefId());

                        if (b){
                            new Alert ( Alert.AlertType.CONFIRMATION,"Ref Delete!" ).show ();
                            getALlRefs();
                        }else {
                            new Alert ( Alert.AlertType.WARNING,"Delete Fail!" ).show ();
                        }

                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                });
            }
            tblRef.setItems(refTMS);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {

        RefDTO refDTO = new RefDTO(
                txtRefId.getText(),
                txtRefName.getText(),
                txtIdNumber.getText(),
                Integer.parseInt(txtTel.getText()),
                txtAddress.getText()
        );

        try {
            if (refBO.addRef(refDTO)){
                new Alert( Alert.AlertType.CONFIRMATION , "Ref Saved!" ).show( );
                getALlRefs();
            }else {
                new Alert( Alert.AlertType.ERROR , "Fail!" ).show( );
            }
        } catch (Exception e) {
            new Alert( Alert.AlertType.ERROR , "Try Again!" ).show( );
        }

    }
}
