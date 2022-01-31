package iMat.checkout;

import iMat.IMatController;
import iMat.Model;
import iMat.shoppingCart.FocusWindow;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class CheckOutController extends AnchorPane implements FocusWindow {

    private IMatController parentController;
    private Model model;

    @FXML
    private Label sumTotal2Label;
    @FXML
    private Label priceLabel;
    @FXML
    private Label discountLabel;
    @FXML
    private Label transportCostLabel;
    @FXML
    private RadioButton imatRadioButton;
    @FXML
    private RadioButton visaRadioButton;
    @FXML
    private RadioButton mastercardRadioButton;
    @FXML
    private RadioButton klarnaRadioButton;
    @FXML
    private FlowPane productFlowPane;
    @FXML
    private Button defaultButton;


    public CheckOutController(IMatController parentController) {
        this.parentController = parentController;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CheckOut.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        Model model = Model.getInstance();
        double price = model.getPrice(model.getShoppingCartItems());
        priceLabel.setText(toText(price));
        discountLabel.setText(toText(model.getDiscount(price)));
        transportCostLabel.setText(toText(model.getTransportCost()));
        sumTotal2Label.setText(toText(model.getPriceTotal()));

        Boolean light = false;
        for (ShoppingItem item : Model.getInstance().getShoppingCartItems()) {
            productFlowPane.getChildren().add(new ProductCard(item, CardStyle.Low_Wide,  light));
            light = !light;
        }
    }
    private String toText(double amount){
        return String.format("%s", amount);
    }
    public void testDefaultButton(){
        System.out.println("Tryckt på default-knappen i kassan.");
    }

    @Override
    public void update() {

    }

    @Override
    public String getTitle() {
        return "Kassa";
    }
}
