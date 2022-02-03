package lk.intelleon.view.tm;

import com.jfoenix.controls.JFXButton;

/**
 * @author Ushan Shanilka <ushanshanilka80@gmail.com>
 * @since 2/4/2022
 **/
public class RefTM {
    private String refId;
    private String name;
    private String idNumber;
    private int tel;
    private String address;
    private JFXButton btn;

    public RefTM() {
    }

    public RefTM(String refId, String name, String idNumber, int tel, String address, JFXButton btn) {
        this.refId = refId;
        this.name = name;
        this.idNumber = idNumber;
        this.tel = tel;
        this.address = address;
        this.btn = btn;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public JFXButton getBtn() {
        return btn;
    }

    public void setBtn(JFXButton btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "RefTM{" +
                "refId='" + refId + '\'' +
                ", name='" + name + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", tel=" + tel +
                ", address='" + address + '\'' +
                ", btn=" + btn +
                '}';
    }
}
