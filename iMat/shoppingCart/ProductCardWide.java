package iMat.shoppingCart;

import iMat.CardListener;
import iMat.Model;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.text.DecimalFormat;

public class ProductCardWide extends AnchorPane implements CardListener {
    private final Model dataHandler = Model.getInstance();
    private final CardWindow parentController;
    private final DecimalFormat df;
    private final String suffix;
    private ShoppingItem shoppingItem;

    @FXML
    private ImageView productImage;
    @FXML
    private Label productLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private Label totalPriceLabel;
    @FXML
    private Label categoryLabel;
    @FXML
    private Label productAmountLabel;
    @FXML
    private Circle minusButton;
    @FXML
    private Circle plusButton;
    @FXML
    private Button deleteButton;

    public ProductCardWide(ShoppingItem item, CardWindow parentController) {
        shoppingItem = item;
        this.parentController = parentController;
        df = new DecimalFormat("#.##");
        suffix = shoppingItem.getProduct().getUnitSuffix().equalsIgnoreCase("kg") ? " kg" : " st";

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("product_card_wide.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        initComponents();
        updateState();
    }

    private void initComponents() {
        Product product = shoppingItem.getProduct();
        double price = product.getPrice();
        productImage.setImage(dataHandler.getFXImage(product));
        productLabel.setText(product.getName());
        priceLabel.setText(df.format(price) + " " + product.getUnit());
        updateLabels();
    }

    private void updateLabels() {
        totalPriceLabel.setText(df.format(shoppingItem.getTotal()) + " kr");
        productAmountLabel.setText(df.format(shoppingItem.getAmount()) + suffix);
    }

    @FXML
    protected void onClick(Event event) {
        parentController.productCardPressed(shoppingItem, this);
    }

    @FXML
    public void incrementProductAmount(Event event) {
        dataHandler.addToShoppingCart(shoppingItem);
        enableCard();
        updateLabels();
        parentController.update();
        event.consume();
    }

    @FXML
    public void decrementProductAmount(Event event) {
        dataHandler.removeFromShoppingCart(shoppingItem);
        if (shoppingItem.getAmount() == 0) {
            disableCard();
        }
        updateLabels();
        parentController.update();
        event.consume();

    }

    @FXML
    public void deleteButtonClicked(Event event) {
        dataHandler.deleteFromShoppingCart(shoppingItem);
        disableCard();
        updateLabels();
        parentController.update();
        event.consume();


    }

    private void enableCard() {
        plusButton.setId("plus-button-active");
        minusButton.setId("minus-button-active");
        deleteButton.setDisable(false);
        this.setOpacity(1);
    }

    private void disableCard() {
        plusButton.setId("plus-button-passive");
        minusButton.setId("minus-button-passive");
        deleteButton.setDisable(true);
        this.setOpacity(0.3);
    }


    @Override
    public void actOnChange() {
        updateLabels();
        updateState();
        parentController.update();

    }

    private void updateState() {
        if (shoppingItem.getAmount() == 0) {
            disableCard();
        } else {
            enableCard();
        }
    }
}
