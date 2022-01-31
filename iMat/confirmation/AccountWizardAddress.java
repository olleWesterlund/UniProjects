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


public class AccountWizardAddress extends AnchorPane implements FocusWindow {
    private IMatController parentController;
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
    private TextField addressTextField;
    @FXML
    private TextField postCodeTextField;
    @FXML
    private TextField postAddressTextField;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private TextField emailTextField;

    @FXML
    private ImageView addressStatusImage;
    @FXML
    private ImageView postCodeStatusImage;
    @FXML
    private ImageView postAddressStatusImage;
    @FXML
    private ImageView phoneNumberStatusImage;
    @FXML
    private ImageView emailStatusImage;

    @FXML
    private Label eMailErrorMessageLabel;
    @FXML
    private Label postCodeErrorMessageLabel;
    @FXML
    private Label phoneNumberMessageLabel;


    public AccountWizardAddress(IMatController parentController) {
        this.parentController = parentController;
        this.customer = Model.getInstance().getCustomer();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("account_wizard_address.fxml"));
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
        addressTextField.setText(customer.getAddress());
        addressTextField.requestFocus();
        postCodeTextField.setText(customer.getPostCode());
        postAddressTextField.setText(customer.getPostAddress());
        phoneNumberTextField.setText(customer.getPhoneNumber());
        emailTextField.setText(customer.getEmail());
    }

    @FXML
    private void continueButtonClicked() {
        updateCustomer();
        parentController.displayAccountWizardPayment();
    }

    @FXML
    private void textFieldChanged() {
        Image okImage = new Image(getClass().getClassLoader().getResourceAsStream("iMat/resources/Ok_icon.png"));
        Image notOkImage = new Image(getClass().getClassLoader().getResourceAsStream("iMat/resources/Not_ok_icon.png"));

        boolean addressOk = completeAddress();
        boolean postCodeOk = completePostCode();
        boolean postAddressOk = completePostAddress();
        boolean phoneNumberOK = completePhoneNumber();
        boolean emailOk = completeEmail();

        if (addressOk) {
            addressStatusImage.setImage(okImage);
        } else {
            addressStatusImage.setImage(notOkImage);
        }

        if (postCodeOk) {
            postCodeStatusImage.setImage(okImage);
        } else {
            postCodeStatusImage.setImage(notOkImage);
        }
        postCodeErrorMessageLabel.setVisible(characterInPostCode());

        if (postAddressOk) {
            postAddressStatusImage.setImage(okImage);
        } else {
            postAddressStatusImage.setImage(notOkImage);
        }

        if (phoneNumberOK) {
            phoneNumberStatusImage.setImage(okImage);
        } else {
            phoneNumberStatusImage.setImage(notOkImage);
        }
        phoneNumberMessageLabel.setVisible(characterInPhoneNumber());

        if (emailOk) {
            emailStatusImage.setImage(okImage);
            eMailErrorMessageLabel.setVisible(false);
        } else {
            emailStatusImage.setImage(notOkImage);
            if (emailTextField.isFocused()) {
                eMailErrorMessageLabel.setVisible(true);
            }
        }


        if(addressOk && postCodeOk && postAddressOk && phoneNumberOK && emailOk) {
            continueButton.setDisable(false);
        } else {
            continueButton.setDisable(true);
        }
    }

    private boolean characterInPhoneNumber() {
        return !isOnlyDigits(phoneNumberTextField.getText());
    }

    private boolean completePhoneNumber() {
        return !phoneNumberTextField.getText().isBlank() && isOnlyDigits(phoneNumberTextField.getText());
    }

    private boolean completePostAddress() {
        return !postAddressTextField.getText().isBlank();
    }

    private boolean characterInPostCode() {
        return !isOnlyDigits(postCodeTextField.getText());

    }

    private boolean isOnlyDigits(String string) {
        for (char ch : string.toCharArray()) {
            if (!Character.isDigit(ch)) {
                return false;
            }
        }
        return true;
    }

    private boolean completePostCode() {
        return !postCodeTextField.getText().isBlank()
                && isOnlyDigits(postCodeTextField.getText()) && postCodeTextField.getText().toCharArray().length == 5;
    }

    private boolean completeAddress() {
        return !addressTextField.getText().isBlank();
    }

    private boolean completeEmail() {
        return !emailTextField.getText().isBlank() && emailTextField.getText().contains("@");
    }

    private void updateCustomer() {
        customer.setAddress(addressTextField.getText());
        customer.setPostCode(postCodeTextField.getText());
        customer.setPostAddress(postAddressTextField.getText());
        customer.setPhoneNumber(phoneNumberTextField.getText());
        customer.setEmail(emailTextField.getText());
    }


    @FXML
    private void backButtonClicked() {
        updateCustomer();
        parentController.displayAccountWizardName();

    }

    @Override
    public void update() {

    }

    @Override
    public String getTitle() {
        return "Mata in Adress";
    }
}
