package iMat.myAccount;

import iMat.IMatController;
import iMat.Model;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.User;



public class UserView extends AnchorPane {
    private User user;
    private IMatController parentController;

    @FXML
    private Label usernameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label failedLoginLabel;
    @FXML
    private TextField userNameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button loginButton;


    public UserView() {
        this.user = Model.getInstance().getUser();
        init();
    }

    @FXML
    public void init() {
//        this.usernameLabel.setText("Användarnamn:");
//        this.passwordLabel.setText("Lösenord:");
//        this.loginButton.setText("Logga in");
//        this.failedLoginLabel.setVisible(false);
    }

    @FXML
    public void login() {
        if (checkUser()) {
            Stage loginStage = (Stage) loginButton.getScene().getWindow();
            loginStage.hide();
        } else {
            this.failedLoginLabel.setText("Fel användarnamn eller lösenord");
            this.failedLoginLabel.setVisible(true);
        }
    }

    private Boolean checkUser() {
        String userName = userNameField.getText();
        String password = passwordField.getText();
        if (userName.equals(user.getUserName()) && password.equals(user.getPassword())) {
            return true;
        } else {
            return false;
        }
    }
}
