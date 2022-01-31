package iMat;

import iMat.checkout.CheckOutController;
import iMat.confirmation.AccountWizardAddress;
import iMat.confirmation.AccountWizardName;
import iMat.confirmation.AccountWizardPayment;
import iMat.confirmation.OrderConfirmationController;
import iMat.contactUs.ContactController;
import iMat.historyView.HistoryController;
import iMat.myAccount.AccountController;
import iMat.productView.ProductView;
import iMat.searchList.searchListController;
import iMat.shoppingCart.FocusWindow;
import iMat.shoppingCart.ShoppingCartController;
import iMat.startView.startController;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import se.chalmers.cse.dat216.project.*;

import java.net.URL;
import java.util.*;

public class IMatController implements Initializable, ShoppingCartListener {
    private final Model dataHandler = Model.getInstance();
    private Map<String, ProductCard> productCardMap = new HashMap<>();
    private boolean shoppingCartIsEmpty;

    @FXML
    private AnchorPane mainView;
    @FXML
    private TextField searchBar;
    @FXML
    private ImageView searchIconImage;
    @FXML
    private ListView startList;
    @FXML
    private ListView categoriesList;
    @FXML
    private Button toCheckout;
    @FXML
    private Button toPaymentButton;
    @FXML
    private AnchorPane transparentPane;
    @FXML
    private AnchorPane welcomePane;
    @FXML
    private AnchorPane firstInstructionPane;
    @FXML
    private AnchorPane secondInstructionPane;
    @FXML
    private AnchorPane thirdInstructionPane;
    @FXML
    private AnchorPane startViewPane;
    @FXML
    private AnchorPane accountPane;
    @FXML
    private AnchorPane searchListPane;
    @FXML
    private AnchorPane SearchTransparentPane;
    @FXML
    private ImageView closeImageView;
    @FXML
    private AnchorPane detailedProductView;
    @FXML
    private Label productNameDetailView;
    @FXML
    private ImageView productPictureDetailView;
    @FXML
    private ImageView flagDetailView;
    @FXML
    private Button closeDetailedButton;
    @FXML
    private ImageView detailedXButton;
    @FXML
    private ImageView firstCloseImage;
    @FXML
    private ImageView secondCloseImage;
    @FXML
    private ImageView thirdCloseImage;
    @FXML
    private Label detailedPriceLabel;
    @FXML
    private Circle plusButton;
    @FXML
    private Circle minusButton;
    @FXML
    private Label productAmountLabel;
    @FXML
    private TextArea textAreaDetail;
    @FXML
    private Label iMatLabel;
    @FXML
    private Label shoppingCartPrice;
    @FXML
    private Text nrOfItemsText;
    @FXML
    private AnchorPane nrOFItemsPane;
    @FXML
    private Label titleLabel;
    @FXML
    private ImageView shoppingCart;

    @FXML
    private void displayStartView() {
        changeView(new startController(this));
    }

    @FXML
    private void displayHistoryView() {
        changeView(new HistoryController(this));
    }

    @FXML
    public void displayCheckOut() {
        if (!shoppingCartIsEmpty) {
            changeView(new CheckOutController(this));
            toPaymentButton.toFront();
            toPaymentButton.setVisible(true);
            toCheckout.toBack();
        }
    }

    @FXML
    private void displayShoppingCart() {
        changeView(new ShoppingCartController(this));
    }

    @FXML
    private void displayMyDetails() {
        changeView(new AccountController(this));
    }

    @FXML
    private void displayContactView() {
        changeView(new ContactController(this));
    }

    @FXML
    private void displayEcoProducts() {
        displayCategory(Category.ECOLOGICAL);
    }

    @FXML
    private void displayFruitsBerries() {
        displayProductCategory(ProductCategory.FRUIT);
    }

    @FXML
    private void displayVegetables() {
        displayProductCategory(ProductCategory.VEGETABLE_FRUIT);
    }

    @FXML
    private void displayDairies() {
        displayProductCategory(ProductCategory.DAIRIES);
    }

    @FXML
    private void displayBread() {
        displayProductCategory(ProductCategory.BREAD);
    }

    @FXML
    private void displayMeat() {
        displayProductCategory(ProductCategory.MEAT);
    }

    @FXML
    private void displayFish() {
        displayProductCategory(ProductCategory.FISH);
    }

    @FXML
    private void displayPasta() {
        displayProductCategory(ProductCategory.PASTA);
    }

    @FXML
    private void displayPotatoRice() {
        displayProductCategory(ProductCategory.POTATO_RICE);
    }

    @FXML
    private void displayDrinks() {
        displayCategory(Category.DRINKS);
    }

    @FXML
    private void displaySweet() {
        displayProductCategory(ProductCategory.SWEET);
    }

    @FXML
    private void displayPantry() {
        displayCategory(Category.PANTRY);
    }

    public void displayProductCategory(ProductCategory productCategory) {
        changeView(new ProductView(productCategory, this));
    }

    public void displayProductList(List<Product> products) {
        changeView(new ProductView(products, this));
    }

    @FXML
    public void displayOrderConfirmation() {
        if (incompleteNameFields()) {
            displayAccountWizardName();
        } else if (incompleteAddressFields()) {
            displayAccountWizardAddress();
        } else {
            changeView(new OrderConfirmationController(this));
        }

        toCheckout.toBack();

    }

    private boolean incompleteAddressFields() {
        return dataHandler.getCustomer().getAddress().isBlank()
                || dataHandler.getCustomer().getPostCode().isBlank()
                || dataHandler.getCustomer().getPostAddress().isBlank()
                || dataHandler.getCustomer().getPhoneNumber().isBlank();
    }

    private boolean incompleteNameFields() {
        return dataHandler.getCustomer().getFirstName().isBlank() || dataHandler.getCustomer().getLastName().isBlank();
    }

    public void displayAccountWizardName() {
        changeView(new AccountWizardName(this));
        toCheckout.toBack();

    }

    public void displayAccountWizardAddress() {
        changeView(new AccountWizardAddress(this));
        toCheckout.toBack();

    }

    public void displayAccountWizardPayment() {
        changeView(new AccountWizardPayment(this));
        toCheckout.toBack();

    }

    private void displayCategory(Category category) {
        changeView(new ProductView(category, this));
    }

    @FXML
    private void displaySearchList(String searchText) {
        AnchorPane searchList = new searchListController(this, searchText);

        searchListPane.getChildren().clear();
        searchListPane.getChildren().add(searchList);
        searchListPane.toFront();
        SearchTransparentPane.toFront();
    }

    @FXML
    public void closeSearchList() {
        SearchTransparentPane.toBack();
        searchListPane.getChildren().clear();
    }

    @FXML
    public void keyTypedSearchField(KeyEvent evt) {
        String character = evt.getCharacter();

        if (character.matches("\\r")) {
            // The user typed return. Start a new search.
            displaySearchList(searchBar.getText());
        } else if (character.matches("\\e")) {
            // The user typed ESC. Close popup-search-view.
            closeSearchList();
        }
    }

    private void updateProductList() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        checkFirstRunStartUp();
        setStartList();
        setCategoriesList();
        initSearchBarListener();
        generateProductCards();
        displayStartView();
        updateShoppingCartLabels();
        dataHandler.getShoppingCart().addShoppingCartListener(this);
        shoppingCartIsEmpty = dataHandler.shoppingCartIsEmpty();
        updateToCheckoutButtonState();
    }

    private void generateProductCards() {
        ProductCard productCard;
        String productName;
        for (ShoppingItem shoppingItem : dataHandler.getShoppingItems()) {
            productName = shoppingItem.getProduct().getName();
            productCard = new ProductCard(shoppingItem, this);
            productCardMap.put(productName, productCard);
        }
        productCardMap.get("Körsbär").getShoppingItem().getProduct().setCategory(ProductCategory.BERRY);
    }

    public List<Product> getProducts() {
        return dataHandler.getProducts();
    }

    public Map<String, ProductCard> getProductCardMap() {
        return productCardMap;
    }

    /**
     * Add listener for the search bar. Starts a search in the database with a delay of one second.
     * When the user stops typing in the search bar, the trigger waits for one second, then starts the
     * database search.
     */
    private void initSearchBarListener() {
        PauseTransition pause = new PauseTransition(Duration.millis(500));
        searchBar.textProperty().addListener(
                (observable, oldValue, newValue) -> {
                    pause.setOnFinished(event -> displaySearchList(newValue));
                    pause.playFromStart();
                }
        );
    }

    private void changeView(FocusWindow window) {
        mainView.getChildren().clear();
        window.update();
        mainView.getChildren().add((AnchorPane) window);
        toCheckout.toFront();
        toPaymentButton.setVisible(false);
        titleLabel.setText(window.getTitle());
    }

    public void openWelcomeView() {
        transparentPane.toFront();
        welcomePane.toFront();
    }

    public void openFirstInstrView() {
        transparentPane.toFront();
        firstInstructionPane.toFront();
    }

    public void openSecondInstrView() {
        transparentPane.toFront();
        secondInstructionPane.toFront();
    }

    public void openThirdInstrView() {
        transparentPane.toFront();
        thirdInstructionPane.toFront();
    }

    public void closeInstructionView() {
        transparentPane.toBack();
    }

    private void checkFirstRunStartUp() {
        if (Model.getInstance().isFirstRun()) {
            openWelcomeView();
        } else {
            closeInstructionView();
        }
    }

    @FXML
    public void closeButtonMouseEntered() {
        Image image = new Image(getClass().getClassLoader().getResourceAsStream("iMat/resources/icon_close_hover.png"));
        closeImageView.setImage(image);
        detailedXButton.setImage(image);
        firstCloseImage.setImage(image);
        secondCloseImage.setImage(image);
        thirdCloseImage.setImage(image);
    }

    @FXML
    public void closeButtonMousePressed() {
        Image image = new Image(getClass().getClassLoader().getResourceAsStream("iMat/resources/icon_close_pressed.png"));
        closeImageView.setImage(image);
        detailedXButton.setImage(image);
        firstCloseImage.setImage(image);
        secondCloseImage.setImage(image);
        thirdCloseImage.setImage(image);
    }

    @FXML
    public void closeButtonMouseExited() {
        Image image = new Image(getClass().getClassLoader().getResourceAsStream("iMat/resources/icon_close.png"));
        closeImageView.setImage(image);
        detailedXButton.setImage(image);
        firstCloseImage.setImage(image);
        secondCloseImage.setImage(image);
        thirdCloseImage.setImage(image);
    }

    @FXML
    public void closeButtonMouseReleased() {
        closeInstructionView();
    }

    @FXML
    public void shoppingCartMouseEntered() {
        shoppingCart.setOpacity(0.5);
    }

    @FXML
    public void shoppingCartMousePressed() {
        shoppingCart.setOpacity(1);
    }

    @FXML
    public void shoppingCartMouseExited() {
        shoppingCart.setOpacity(1);
    }

    @FXML
    public void mouseTrap(Event event) {
        event.consume();
    }

    public void openDetailedView(ShoppingItem item, CardListener cardListener) {
        populateDetailedView(item, cardListener);
        detailedProductView.toFront();
    }

    private void populateDetailedView(ShoppingItem item, CardListener cardListener) {
        String text = getIpsum();
        Product product = item.getProduct();
        String unit = "1" + product.getUnitSuffix();
        productNameDetailView.setText(product.getName() + " " + unit);
        productPictureDetailView.setImage(dataHandler.getFXImage(product));
        detailedPriceLabel.setText(product.getPrice() + " kr");
        textAreaDetail.setText(product.getName() + " med ursprung från Sverige. \n \n" + text);
        updateProduct(item);

        plusButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                addProduct(item);
                cardListener.actOnChange();
            }
        });

        minusButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                removeProduct(item);
                cardListener.actOnChange();
            }
        });
    }

    private void updateProduct(ShoppingItem item) {
        productAmountLabel.setText((int) item.getAmount() + " st");
        if (item.getAmount() == 0) {
            plusButton.setId("plus-button-passive");
            minusButton.setId("minus-button-passive");
        } else {
            plusButton.setId("plus-button-active");
            minusButton.setId("minus-button-active");
        }
        updateShoppingCartLabels();
    }

    public void addProduct(ShoppingItem item) {
        dataHandler.addToShoppingCart(item);
        updateProduct(item);
    }

    public void removeProduct(ShoppingItem item) {
        dataHandler.removeFromShoppingCart(item);
        updateProduct(item);
    }

    protected void updateShoppingCartLabels() {
        if (dataHandler.getShoppingCartTotal() > 0) {
            shoppingCartPrice.setVisible(true);
            shoppingCartPrice.setText(String.format("%.2f", dataHandler.getShoppingCartTotal()) + "kr");
            nrOFItemsPane.setVisible(true);
            nrOfItemsText.setText(Integer.toString(dataHandler.getNrOfProductsInShoppingCart()));
        } else {
            shoppingCartPrice.setVisible(false);
            nrOFItemsPane.setVisible(false);
        }
    }

    private String getIpsum() {
        String ipsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\n" +
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.\n" +
                "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.\n" +
                "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
        return ipsum;
    }

    public void update() {
        updateShoppingCartLabels();
    }

    @FXML
    private void closeDetailedView() {
        detailedProductView.toBack();
        mainView.toFront();
        toCheckout.toFront();
    }

    //------------------------------------------------------------------------------------------------------------------
    private void setStartList() {
        ObservableList<String> list = FXCollections.<String>observableArrayList(
                "Start", "Mina uppgifter", "Mina ordrar", "Kontakta oss");
        startList.setItems(list);
        startList.setOrientation(Orientation.VERTICAL);
        startList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                switch (startList.getSelectionModel().getSelectedItem().toString()) {
                    case "Start":
                        displayStartView();
                        break;
                    case "Mina uppgifter":
                        displayMyDetails();
                        break;
                    case "Mina ordrar":
                        displayHistoryView();
                        break;
                    case "Kontakta oss":
                        displayContactView();
                        break;
                    default:
                        System.out.println("null");
                }
            }
        });
    }

    private void setCategoriesList() {
        ObservableList<String> list = FXCollections.<String>observableArrayList(
                "Ekologiska varor", "Bröd", "Frukt & Bär", "Grönsaker", "Mejeri, Ost & Ägg", "Kött", "Fisk & Skaldjur", "Skafferi", "Pasta", "Potatis & Ris", "Fika, Godis & Snacks", "Dryck");
        categoriesList.setItems(list);
        categoriesList.setOrientation(Orientation.VERTICAL);
        categoriesList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                switch (categoriesList.getSelectionModel().getSelectedItem().toString()) {
                    case "Ekologiska varor":
                        displayEcoProducts();
                        break;
                    case "Frukt & Bär":
                        displayFruitsBerries();
                        break;
                    case "Grönsaker":
                        displayVegetables();
                        break;
                    case "Mejeri, Ost & Ägg":
                        displayDairies();
                        break;
                    case "Bröd":
                        displayBread();
                        break;
                    case "Kött":
                        displayMeat();
                        break;
                    case "Fisk & Skaldjur":
                        displayFish();
                        break;
                    case "Pasta":
                        displayPasta();
                        break;
                    case "Potatis & Ris":
                        displayPotatoRice();
                        break;
                    case "Dryck":
                        displayDrinks();
                        break;
                    case "Fika, Godis & Snacks":
                        displaySweet();
                        break;
                    case "Skafferi":
                        displayPantry();
                        break;
                    default:
                        System.out.println("null");
                        break;
                }
            }
        });
    }

    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        shoppingCartIsEmpty = dataHandler.shoppingCartIsEmpty();
        updateToCheckoutButtonState();
    }

    private void updateToCheckoutButtonState() {
        if (shoppingCartIsEmpty) {
            toCheckout.setId("prominent-button-disabled");
        } else {
            toCheckout.setId("");
        }
    }

}
