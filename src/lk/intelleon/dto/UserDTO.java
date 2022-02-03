package lk.intelleon.dto;

/**
 * @author Ushan Shanilka <ushanshanilka80@gmail.com>
 * @since 2/3/2022
 **/
public class UserDTO {
    private String userId;
    private String userName;
    private String password;
    private Boolean activeState;
    private String userType;

    public UserDTO() {
    }

    public UserDTO(String userId, String userName, String password, Boolean activeState, String userType) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.activeState = activeState;
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActiveState() {
        return activeState;
    }

    public void setActiveState(Boolean activeState) {
        this.activeState = activeState;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", activeState=" + activeState +
                ", userType='" + userType + '\'' +
                '}';
    }
}
