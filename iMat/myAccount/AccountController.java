package iMat.myAccount;

import iMat.IMatController;
import iMat.Model;
import iMat.shoppingCart.FocusWindow;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.CreditCard;
import se.chalmers.cse.dat216.project.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AccountController extends AnchorPane implements FocusWindow {
    private IMatController parentController;
    private Customer customer;
    private CreditCard card;
    private List<TextField> fields = new ArrayList<>();

    @FXML private Label firstNameLabel;
    @FXML private Label surnameLabel;
    @FXML private Label addressLabel;
    @FXML private Label postCodeLabel;
    @FXML private Label cityLabel;
    @FXML private Label phoneLabel;
    @FXML private Label cardLabel;
    @FXML private Label cardNrLabel;
    @FXML private Label validDateLabel;
    @FXML private Label cvvLabel;
    @FXML private TextField firstNameField;
    @FXML private TextField surnameField;
    @FXML private TextField addressField;
    @FXML private TextField postCodeField;
    @FXML private TextField cityField;
    @FXML private TextField phoneField;
    @FXML private TextField cardNrField;
    @FXML private TextField cvvField;
    @FXML private Button editButton;
    @FXML private ComboBox cardComboBox;
    @FXML private TextField validMonthField;
    @FXML private TextField validYearField;
    @FXML private Label emailLabel;
    @FXML private TextField emailTextField;
    @FXML private ImageView firstNameStatus;
    @FXML private ImageView surnameStatus;
    @FXML private ImageView addressStatus;
    @FXML private ImageView postCodeStatus;
    @FXML private ImageView cityStatus;
    @FXML private ImageView phoneStatus;
    @FXML private ImageView emailStatus;
    @FXML private ImageView cardNrStatus;
    @FXML private ImageView validDateStatus;
    @FXML private ImageView cvvStatus;
    @FXML private Label postCodeErrorMessageLabel;
    @FXML private Label phoneErrorMassageLabel;
    @FXML private Label cardNrErrorMassageLabel;
    @FXML private Label validDateErrorMassageLabel;
    @FXML private Label cvvErrorMassageLabel;

    public AccountController(IMatController parentController) {
        this.parentController = parentController;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("account.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.customer = Model.getInstance().getCustomer();
        this.card = Model.getInstance().getCreditCard();
        this.firstNameLabel.setText("Förnamn:");
        this.surnameLabel.setText("Efternamn:");
        this.addressLabel.setText("Gatuadress:");
        this.postCodeLabel.setText("Postnummer:");
        this.cityLabel.setText("Postort:");
        this.phoneLabel.setText("Telefonnummer:");
        this.cardLabel.setText("Kort:");
        this.cardNrLabel.setText("Kortnummer:");
        this.validDateLabel.setText("Giltighetsdatum");
        this.cvvLabel.setText("CVV");
        this.editButton.setText("Ändra");
        this.cardComboBox.getItems().addAll("iMat-kort", "Visa", "Mastercard");
        this.emailLabel.setText("E-post");
        addTextFields();
        present();
        if (Model.getInstance().isFirstRun()) {
            firstRun();
        }
    }

    private void editInformation() {
        editButton.setText("Spara");
        for (TextField field : fields) {
            field.setDisable(false);
        }
        cardComboBox.setDisable(false);
    }

    private void saveInformation() {
        editButton.setText("Ändra");
        for (TextField field : fields) {
            field.setDisable(true);
        }
        cardComboBox.setDisable(true);
        save();
    }

    @FXML
    public void editMyInformation() {
        if (editButton.getText().equals("Ändra")) {
//            try {
//                openLoginScreen();
//            }  catch (IOException e) {
//                e.printStackTrace();
//            }
            editInformation();
        } else {
            saveInformation();
        }
    }

    @FXML
    protected void openLoginScreen() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("user_View.fxml"));
        Stage loginStage = new Stage();
        loginStage.setScene(new Scene(root));
        loginStage.setTitle("Login Screen");
        loginStage.initModality(Modality.APPLICATION_MODAL);
        loginStage.setResizable(false);
        loginStage.show();
    }

    private void present() {
        firstNameField.setText(customer.getFirstName());
        surnameField.setText(customer.getLastName());
        addressField.setText(customer.getAddress());
        postCodeField.setText(customer.getPostCode());
        cityField.setText(customer.getPostAddress());
        phoneField.setText(customer.getPhoneNumber());
        cardComboBox.getSelectionModel().select(Model.getInstance().getCreditCard().getCardType());
        cardNrField.setText(card.getCardNumber());
        emailTextField.setText(customer.getEmail());

        if (card.getValidMonth() != 0) {
            if (card.getValidMonth() < 10) {
                validMonthField.setText("0" + card.getValidMonth());
            } else {
                validMonthField.setText(Integer.toString(card.getValidMonth()));
            }
        }

        if (card.getValidYear() != 0) {
            validYearField.setText(Integer.toString(card.getValidYear()));
        }

        if (card.getVerificationCode() != 0) {
            cvvField.setText(Integer.toString(card.getVerificationCode()));
        }



    }

    private void save() {
        customer.setFirstName(firstNameField.getText());
        customer.setLastName(surnameField.getText());
        customer.setAddress(addressField.getText());
        customer.setPostCode(postCodeField.getText());
        customer.setPostAddress(cityField.getText());
        customer.setPhoneNumber(phoneField.getText());
        card.setCardType(String.valueOf(cardComboBox.getValue()));
        card.setCardNumber(cardNrField.getText());
        String month = validMonthField.getText();
        if (!month.equals("")) {
            card.setValidMonth(Integer.parseInt(month));
        }
        String year = validYearField.getText();
        if (!year.equals("")) {
            card.setValidYear(Integer.parseInt(year));
        }
        card.setVerificationCode(Integer.valueOf(cvvField.getText()));
    }

    private void firstRun() {
        cardComboBox.getSelectionModel().select("iMat-kort");
    }

    private void addTextFields() {
        fields.add(firstNameField);
        fields.add(surnameField);
        fields.add(addressField);
        fields.add(postCodeField);
        fields.add(cityField);
        fields.add(phoneField);
        fields.add(cardNrField);
        fields.add(cvvField);
        fields.add(validMonthField);
        fields.add(validYearField);
        fields.add(emailTextField);
    }

    private boolean allFieldsOk() {
        return (checkFirstName() && checkSurname() && checkAddress() && checkCity()
                && checkPhone() && checkMail() && checkCardNr() && checkValidMonth()
                && checkValidYear() && checkCVV() && checkPostCode());
    }

    private boolean checkFirstName() {
        return !firstNameField.getText().isBlank();
    }

    private boolean checkSurname() {
        return !surnameField.getText().isBlank();
    }

    private boolean checkAddress() {
        return !addressField.getText().isBlank();
    }

    private boolean checkCity() {
        return !cityField.getText().isBlank();
    }

    private boolean checkPhone() {
        return isOnlyDigits(phoneField.getText()) && !phoneField.getText().isBlank();
    }

    private boolean checkMail() {
        return !emailTextField.getText().isBlank() && emailTextField.getText().contains("@");
    }

    private boolean checkCardNr() {
        return !cardNrField.getText().isBlank() && isOnlyDigits(cardNrField.getText());
    }

    private boolean checkValidMonth() {
        return !validMonthField.getText().isBlank() && isOnlyDigits(validMonthField.getText()) && (validMonthField.getText().toCharArray().length == 2);
    }

    private boolean checkValidYear() {
        return !validYearField.getText().isBlank() && isOnlyDigits(validYearField.getText()) && (validYearField.getText().toCharArray().length == 2);
    }

    private boolean checkCVV() {
        return !cvvField.getText().isBlank() && isOnlyDigits(cvvField.getText()) && cvvField.getText().toCharArray().length == 3;
    }

    private boolean checkPostCode() {
        return isOnlyDigits(postCodeField.getText()) && postCodeField.getText().toCharArray().length == 5;
    }

    private boolean isOnlyDigits(String text) {
        for (char ch : text.toCharArray()) {
            if (!Character.isDigit(ch)) {
                return false;
            }
        }
        return true;
    }

    @FXML
    public void firstNameChanged() {
        textFieldChanged(checkFirstName(), firstNameStatus);
    }

    @FXML
    public void surnameChanged() {
        textFieldChanged(checkSurname(), surnameStatus);
    }

    @FXML
    public void addressChanged() {
        textFieldChanged(checkAddress(), addressStatus);
    }

    @FXML
    public void postCodeChanged() {
        boolean isValidPostCode = checkPostCode();
        textFieldChanged(isValidPostCode, postCodeStatus);
        if (!isValidPostCode) {
            postCodeErrorMessageLabel.setText("Endast siffror 0-9");
            postCodeErrorMessageLabel.setVisible(true);
        } else {
            postCodeErrorMessageLabel.setVisible(false);
        }
    }

    @FXML
    public void cityChanged() {
        textFieldChanged(checkCity(), cityStatus);
    }

    @FXML
    public void phoneChanged() {
        boolean isValidPhoneNr = checkPhone();
        textFieldChanged(isValidPhoneNr, phoneStatus);
        if (!isValidPhoneNr) {
            phoneErrorMassageLabel.setText("Endast siffror 0-9");
            phoneErrorMassageLabel.setVisible(true);
        } else {
            phoneErrorMassageLabel.setVisible(false);
        }
    }

    @FXML
    public void eMailChanged() {
        textFieldChanged(checkMail(), emailStatus);
    }

    @FXML
    public void cardNrChanged() {
        boolean isValidNumber = checkCardNr();
        textFieldChanged(isValidNumber, cardNrStatus);
        if (!isValidNumber) {
            cardNrErrorMassageLabel.setText("Endast siffror 0-9");
            cardNrErrorMassageLabel.setVisible(true);
        } else {
            cardNrErrorMassageLabel.setVisible(false);
        }
    }

    @FXML
    public void validMonthChanged() {
        boolean isValidMonth = checkValidMonth();
        textFieldChanged(isValidMonth, validDateStatus);
        if (!isValidMonth) {
            validDateErrorMassageLabel.setText("Endast siffror 0-9");
            validDateErrorMassageLabel.setVisible(true);
        } else {
            validDateErrorMassageLabel.setVisible(false);
        }
    }

    @FXML
    public void validYearChanged() {
        boolean isValidYear = checkValidYear();
        textFieldChanged(isValidYear, validDateStatus);
        if (!isValidYear) {
            validDateErrorMassageLabel.setText("Endast siffror 0-9");
            validDateErrorMassageLabel.setVisible(true);
        } else {
            validDateErrorMassageLabel.setVisible(false);
        }
    }

    @FXML
    public void cvvChanged() {
        boolean isValidCVV = checkCVV();
        textFieldChanged(isValidCVV, cvvStatus);
        if (!isValidCVV) {
            cvvErrorMassageLabel.setText("Endast siffror 0-9");
            cvvErrorMassageLabel.setVisible(true);
        } else {
            cvvErrorMassageLabel.setVisible(false);
        }
    }

    private void textFieldChanged(boolean isTextFieldOk, ImageView image) {
        image.setVisible(true);
        Image okImage = new Image(getClass().getClassLoader().getResourceAsStream("iMat/resources/Ok_icon.png"));
        Image notOkImage = new Image(getClass().getClassLoader().getResourceAsStream("iMat/resources/Not_ok_icon.png"));
        if (isTextFieldOk) {
            image.setImage(okImage);
            editButton.setDisable(false);
        } else {
            image.setImage(notOkImage);
            editButton.setDisable(true);
        }
    }

    @Override
    public void update() {

    }

    @Override
    public String getTitle() {
        return "Mina Uppgifter";
    }
}
