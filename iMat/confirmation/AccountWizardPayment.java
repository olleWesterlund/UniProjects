package iMat.confirmation;

import iMat.IMatController;
import iMat.Model;
import iMat.shoppingCart.FocusWindow;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.CreditCard;
import se.chalmers.cse.dat216.project.Customer;

import java.io.IOException;


public class AccountWizardPayment extends AnchorPane implements FocusWindow {
    private IMatController parentController;
    private final Customer customer;
    private final CreditCard card;

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
    private ComboBox cardTypeComboBox;
    @FXML
    private TextField cardNrTextField;
    @FXML
    private TextField monthTextField;
    @FXML
    private TextField yearTextField;
    @FXML
    private TextField cvvTextField;

    @FXML
    private ImageView cardNrStatusImage;
    @FXML
    private ImageView dateStatusImage;
    @FXML
    private ImageView cvvStatusImage;

    @FXML
    private Label cardNrErrorMessageLabel;
    @FXML
    private Label validDateErrorMessageLabel;
    @FXML
    private Label cvvErrorMessageLabel;


    public AccountWizardPayment(IMatController parentController) {
        this.parentController = parentController;
        this.customer = Model.getInstance().getCustomer();
        this.card = Model.getInstance().getCreditCard();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("account_wizard_payment.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        if (Model.getInstance().isFirstRun()) {
            firstRun();
        }
        setLabels();
        textFieldChanged();
    }

    private void setLabels() {
        this.cardTypeComboBox.getItems().addAll("iMat-kort", "Visa", "Mastercard");
        cardTypeComboBox.getSelectionModel().select(Model.getInstance().getCreditCard().getCardType());

        cardNrTextField.setText(card.getCardNumber());

        if (card.getValidMonth() != 0) {
            if (card.getValidMonth() < 10) {
                monthTextField.setText("0" + card.getValidMonth());
            } else {
                monthTextField.setText(Integer.toString(card.getValidMonth()));
            }
        }

        if (card.getValidYear() != 0) {
            yearTextField.setText(Integer.toString(card.getValidYear()));
        }

        if (card.getVerificationCode() != 0) {
            cvvTextField.setText(Integer.toString(card.getVerificationCode()));
        }


    }

    @FXML
    private void continueButtonClicked() {
        updateCard();
        parentController.displayOrderConfirmation();
    }

    private void firstRun() {

        cardTypeComboBox.getSelectionModel().select("iMat-kort");
    }

    @FXML
    private void textFieldChanged() {
        Image okImage = new Image(getClass().getClassLoader().getResourceAsStream("iMat/resources/Ok_icon.png"));
        Image notOkImage = new Image(getClass().getClassLoader().getResourceAsStream("iMat/resources/Not_ok_icon.png"));

        boolean cardNrOk = cardNrComplete();
        boolean dateOk = dateComplete();
        boolean cvvOk = cvvComplete();

        if (cardNrOk) {
            cardNrStatusImage.setImage(okImage);
            cardNrErrorMessageLabel.setVisible(false);
        } else {
            cardNrStatusImage.setImage(notOkImage);
            cardNrErrorMessageLabel.setVisible(characterInCardNr());
        }

        if (dateOk) {
            dateStatusImage.setImage(okImage);
            validDateErrorMessageLabel.setVisible(false);
        } else {
            dateStatusImage.setImage(notOkImage);
            validDateErrorMessageLabel.setVisible(characterInDate());
        }


        if (cvvOk) {
            cvvStatusImage.setImage(okImage);
            cvvErrorMessageLabel.setVisible(false);
        } else {
            cvvStatusImage.setImage(notOkImage);
            cvvErrorMessageLabel.setVisible(characterInCVV());
        }


        if (cardNrOk && dateOk && cvvOk) {
            continueButton.setDisable(false);
        } else {
            continueButton.setDisable(true);
        }
    }

    private boolean cardNrComplete() {
        return !cardNrTextField.getText().isBlank() && isOnlyDigits(cardNrTextField.getText());
    }

    private boolean characterInDate() {
        return !isOnlyDigits(monthTextField.getText()) || !isOnlyDigits(yearTextField.getText());
    }

    private boolean characterInCardNr() {
        return !isOnlyDigits(cardNrTextField.getText());
    }

    private boolean characterInCVV() {
        return !isOnlyDigits(cvvTextField.getText());
    }

    private boolean isOnlyDigits(String string) {
        for (char ch : string.toCharArray()) {
            if (!Character.isDigit(ch)) {
                return false;
            }
        }
        return true;
    }

    private boolean dateComplete() {
        return monthComplete() && yearComplete();
    }

    private boolean monthComplete() {
        return !monthTextField.getText().isBlank() && isOnlyDigits(monthTextField.getText()) && monthTextField.getText().toCharArray().length == 2;
    }

    private boolean yearComplete() {
        return !yearTextField.getText().isBlank() && isOnlyDigits(yearTextField.getText()) && yearTextField.getText().toCharArray().length == 2;
    }

    private boolean cvvComplete() {
        return !cvvTextField.getText().isBlank() && isOnlyDigits(cvvTextField.getText()) && cvvTextField.getText().toCharArray().length == 3;
    }

    private void updateCard() {
        card.setCardType(String.valueOf(cardTypeComboBox.getValue()));
        card.setCardNumber(cardNrTextField.getText());
        String month = monthTextField.getText();
        if (!month.equals("")) {
            card.setValidMonth(Integer.parseInt(month));
        }
        String year = yearTextField.getText();
        if (!year.equals("")) {
            card.setValidYear(Integer.parseInt(year));
        }
        card.setVerificationCode(Integer.parseInt(cvvTextField.getText()));
    }

    @FXML
    private void circleIndicatorOneClicked() {
        updateCard();
        parentController.displayAccountWizardName();

    }

    @FXML
    private void circleIndicatorTwoClicked() {
        updateCard();
        parentController.displayAccountWizardAddress();

    }

    @FXML
    private void backButtonClicked() {
        updateCard();
        parentController.displayAccountWizardAddress();

    }

    @Override
    public void update() {

    }

    @Override
    public String getTitle() {
        return "Mata in betalningsmetod";
    }
}
