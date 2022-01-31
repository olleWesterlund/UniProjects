package iMat.confirmation;

import iMat.IMatController;
import iMat.Model;
import iMat.shoppingCart.FocusWindow;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


public class OrderConfirmationController extends AnchorPane implements FocusWindow {
    private IMatController parentController;
    private Model model;

    @FXML
    private Label addressLabel;
    @FXML
    private Label statusLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label postCodeLabel;
    @FXML
    private Label postAddressLabel;
    @FXML
    private Label totalAmountLabel;
    @FXML
    private Label totalPriceLabel;

    @FXML
    private Button confirmOrderButton;
    @FXML
    private Button regretOrderButton;


    public OrderConfirmationController(IMatController parentController) {
        model = Model.getInstance();
        this.parentController = parentController;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("order_confirmation.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        addressLabel.setText(Model.getInstance().getCustomer().getAddress());
        nameLabel.setText(model.getCustomer().getFirstName() + " " + model.getCustomer().getLastName());
        postCodeLabel.setText(model.getCustomer().getPostCode());
        postAddressLabel.setText(model.getCustomer().getPostAddress());
        totalAmountLabel.setText(Integer.toString(model.getNrOfProductsInShoppingCart()) + " st");
        totalPriceLabel.setText(String.format("%.2f kr", model.getShoppingCartTotal()));

    }

    @FXML
    private void confirmOrder() {
        Model.getInstance().placeOrder();
        statusLabel.setText("Köpet genomfört");
        confirmOrderButton.setDisable(true);
        regretOrderButton.setDisable(true);
        update();


    }
    @FXML
    private void regretOrder() {
        parentController.displayCheckOut();
    }

    @Override
    public void update() {
        parentController.update();
    }

    @Override
    public String getTitle() {
        return "Orderbekräftelse";
    }
}
