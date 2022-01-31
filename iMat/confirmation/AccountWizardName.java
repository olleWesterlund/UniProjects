package iMat.confirmation;

import iMat.IMatController;
import iMat.Model;
import iMat.shoppingCart.FocusWindow;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import se.chalmers.cse.dat216.project.Customer;

import java.io.IOException;


public class AccountWizardName extends AnchorPane implements FocusWindow {
    private final IMatController parentController;
    private final Customer customer;

    @FXML
    private Label circleIndicatorOneTitle;
    @FXML
    private Label circleIndicatorOneLabel;
    @FXML
    private Circle circleIndicatorOne;

    @FXML
    private Label circleIndicatorTwoTitle;
    @FXML
    private Label circleIndicatorTwoLabel;
    @FXML
    private Circle circleIndicatorTwo;

    @FXML
    private Label circleIndicatorThreeTitle;
    @FXML
    private Label circleIndicatorThreeLabel;
    @FXML
    private Circle circleIndicatorThree;

    @FXML
    private Button continueButton;

    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;

    @FXML
    private ImageView firstNameStatusImage;
    @FXML
    private ImageView lastNameStatusImage;


    public AccountWizardName(IMatController parentController) {
        this.parentController = parentController;
        this.customer = Model.getInstance().getCustomer();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("account_wizard_name.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        setLabels();
        textFieldChanged();

    }

    private void setLabels() {
        firstNameTextField.setText(customer.getFirstName());
        firstNameTextField.requestFocus();
        lastNameTextField.setText(customer.getLastName());
    }

    @FXML
    private void continueButtonClicked() {
        updateCustomer();
        parentController.displayAccountWizardAddress();

    }

    @FXML
    private void backButtonClicked() {
        updateCustomer();
        parentController.displayCheckOut();

    }

    @FXML
    private void textFieldChanged() {

        Image okImage = new Image(getClass().getClassLoader().getResourceAsStream("iMat/resources/Ok_icon.png"));
        Image notOkImage = new Image(getClass().getClassLoader().getResourceAsStream("iMat/resources/Not_ok_icon.png"));

        if (completeFirstName()) {
            firstNameStatusImage.setImage(okImage);
        } else {
            firstNameStatusImage.setImage(notOkImage);
        }

        if (completeLastName()) {
            lastNameStatusImage.setImage(okImage);
        } else {
            lastNameStatusImage.setImage(notOkImage);
        }


        if (completeFirstName() && completeLastName()) {
            continueButton.setDisable(false);
        } else {
            continueButton.setDisable(true);
        }

    }

    private boolean completeFirstName(){
        return !firstNameTextField.getText().isBlank();
    }

    private boolean completeLastName(){
        return !lastNameTextField.getText().isBlank();
    }



    private void updateCustomer() {
        customer.setFirstName(firstNameTextField.getText());
        customer.setLastName(lastNameTextField.getText());
    }

    @Override
    public void update() {

    }

    @Override
    public String getTitle() {
        return "Mata in Namn";
    }
}
