package iMat;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.text.DecimalFormat;

public class ProductCard extends AnchorPane implements CardListener {
    private final Model dataHandler;
    private ShoppingItem shoppingItem;
    private final DecimalFormat df;
    private IMatController parentController;

    @FXML
    private ImageView productImage;
    @FXML
    private Label productLabel;
    @FXML
    private Label totalPriceLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private TextField productAmountField;
    @FXML
    private Label productSuffixLabel;
    @FXML
    private Circle minusButton;
    @FXML
    private Circle plusButton;
    @FXML
    private AnchorPane ecoPane;

    public ProductCard(ShoppingItem item, IMatController parentController) {
        dataHandler = Model.getInstance();
        shoppingItem = item;
        this.parentController = parentController;
        df = new DecimalFormat("#.##");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("product_card.fxml"));
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
        priceLabel.setText("Jfr-pris: " + df.format(price) + " " + product.getUnit());
        totalPriceLabel.setText(df.format(price) + " kr");
        productAmountField.setText(df.format(shoppingItem.getAmount()));
        productSuffixLabel.setText(product.getUnitSuffix());
        if (!product.isEcological()) {
            ecoPane.setVisible(false);
        }
    }

    @FXML
    public void keyTypedAmountLabel(KeyEvent event) {
        String character = event.getCharacter();
        if (character.matches("\\r")) {
            if (!productAmountField.getText().matches("0")) {
                if (!amountLimit()) {
                    String amount = productAmountField.getText().substring(0, 2);
                    productAmountField.setText(amount);
                } else {
                    productAmountField.setText(productAmountField.getText());
                }
                shoppingItem.setAmount(Double.parseDouble(productAmountField.getText()) - 1);
                dataHandler.addToShoppingCart(shoppingItem);
            } else {
                shoppingItem.setAmount(0);
                dataHandler.removeFromShoppingCart(shoppingItem);
            }
            actOnChange();
            this.requestFocus();
        }
    }

    private boolean amountLimit() {
        if (productAmountField.getText().length() > 2) return false;
        return true;
    }

    @FXML
    protected void onClick(Event event) {
        parentController.openDetailedView(shoppingItem, this);
    }

    public ShoppingItem getShoppingItem() {
        return this.shoppingItem;
    }

    private void updateProduct() {
        productAmountField.setText(df.format(shoppingItem.getAmount()));
    }

    @FXML
    private void incrementProductAmount(Event event) {
        dataHandler.addToShoppingCart(shoppingItem);
        actOnChange();
        event.consume();
    }

    @FXML
    private void decrementProductAmount(Event event) {
        dataHandler.removeFromShoppingCart(shoppingItem);
        actOnChange();
        event.consume();
    }

    @Override
    public void actOnChange() {
        updateProduct();
        updateState();
        parentController.updateShoppingCartLabels();
    }

    private void updateState() {
        if (shoppingItem.getAmount() == 0) {
            plusButton.setId("plus-button-passive");
            minusButton.setId("minus-button-passive");
        } else {
            plusButton.setId("plus-button-active");
            minusButton.setId("minus-button-active");
        }
    }
}
