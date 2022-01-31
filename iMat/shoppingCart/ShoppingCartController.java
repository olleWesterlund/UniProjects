package iMat.shoppingCart;

import iMat.CardListener;
import iMat.IMatController;
import iMat.Model;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.util.List;

public class ShoppingCartController extends AnchorPane implements CardWindow {

    private final IMatController parentController;
    private final Model dataHandler;

    @FXML
    private FlowPane productFlowPane;
    @FXML
    private Label emptyCartLabel;
    @FXML
    private Button emptyCartButton;
    @FXML
    private Button restoreCartButton;

    public ShoppingCartController(IMatController parentController) {
        this.parentController = parentController;
        dataHandler = Model.getInstance();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("shopping_cart.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        populateProductFlowPane(dataHandler.getShoppingCartItems());

    }

    private void populateProductFlowPane(List<ShoppingItem> cartItems) {

        if (cartItems.isEmpty()) {
            if (dataHandler.havePreviousShoppingCart()) {
                setRestoreCartState();
            } else {
                setEmptyCartState();
            }
        } else {
            setNotEmptyCartState();
            addToFlowPane(cartItems);
        }
    }

    private void addToFlowPane(List<ShoppingItem> cartItems) {
        for (ShoppingItem item : cartItems) {
            productFlowPane.getChildren().add(new ProductCardWide(item, this));
        }
        addSpacer();
    }

    private void addSpacer() {
        AnchorPane spacer = new AnchorPane();
        spacer.setPrefSize(300, 40);
        productFlowPane.getChildren().add(spacer);
    }

    @FXML
    private void clearShoppingCart() {
        dataHandler.safeClearShoppingCart();
        productFlowPane.getChildren().clear();
        setRestoreCartState();
        update();
    }
    @FXML
    private void restoreShoppingCart() {
        populateProductFlowPane(dataHandler.restorePreviousShoppingCart());
        update();
    }

    private void setEmptyCartState() {
        emptyCartLabel.toFront();
        emptyCartButton.toBack();
        restoreCartButton.toBack();
    }

    private void setNotEmptyCartState() {
        emptyCartLabel.toBack();
        emptyCartButton.toFront();
        restoreCartButton.toBack();
    }

    private void setRestoreCartState() {
        emptyCartLabel.toFront();
        emptyCartButton.toBack();
        restoreCartButton.toFront();
    }


    @Override
    public void update() {
        parentController.update();
    }

    @Override
    public String getTitle() {
        return "Varukorg";
    }

    @Override
    public void productCardPressed(ShoppingItem shoppingItem, CardListener cardListener) {
        parentController.openDetailedView(shoppingItem, cardListener);

    }
}
