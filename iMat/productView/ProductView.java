package iMat.productView;

import iMat.Category;
import iMat.IMatController;
import iMat.ProductCard;
import iMat.shoppingCart.FocusWindow;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductView extends AnchorPane implements FocusWindow {
    private final IMatController parentController;
    private List<ProductCard> productCards = new ArrayList<>();
    private String categoryName;
    @FXML
    private FlowPane productsFlowPane;

    public ProductView(ProductCategory productCategory, IMatController parentController) {
        this.parentController = parentController;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("productview.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        initComponents(productCategory);
    }

    public ProductView(Category category, IMatController parentController) {
        this.parentController = parentController;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("productview.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        initComponents(category);
    }

    public ProductView(List<Product> products, IMatController parentController) {
        this.parentController = parentController;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("productview.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        fillProducts(products);
    }

    private void initComponents(ProductCategory productCategory) {
        productsFlowPane.getChildren().clear();
        switch (productCategory) {
            case VEGETABLE_FRUIT:
                categoryName = "Vegetable";
                fillVegetables();
                break;
            case FRUIT:
                fillFruitsAndBerries();
                categoryName = "Fruit";
                break;
            case PASTA:
                categoryName = "Pasta";
                fillProducts(productCategory);
                break;
            case POTATO_RICE:
                categoryName = "PR";
                fillProducts(productCategory);
                break;
            case MEAT:
                categoryName = "Meat";
                fillProducts(productCategory);
                break;
            case FISH:
                categoryName = "Fish";
                fillProducts(productCategory);
                break;
            case DAIRIES:
                categoryName = "Dairies";
                fillProducts(productCategory);
                break;
            case BREAD:
                categoryName = "Bread";
                fillProducts(productCategory);
                break;
            case SWEET:
                categoryName = "Sweet";
                fillProducts(productCategory);
                break;
            default:
                fillProducts(productCategory);
                categoryName = "search";
                break;
        }
        addSpacer();
    }

    private void initComponents(Category category) {
        productsFlowPane.getChildren().clear();
        switch (category) {
            case ECOLOGICAL:
                categoryName = "Eco";
                fillEco();
                break;
            case MEAT_FISH:
                fillMeatFish();
                break;
            case DRINKS:
                categoryName = "Drinks";
                fillDrinks();
                break;
            case PANTRY:
                categoryName = "Pantry";
                fillPantry();
                break;
            default:
                categoryName = "search";
                break;
        }
    }

    private void addSpacer() {
        AnchorPane spacer = new AnchorPane();
        spacer.setPrefSize(205, 40);
        productsFlowPane.getChildren().add(spacer);
    }

    private void fillEco() {
        for (Product product : parentController.getProducts()) {
            if (product.isEcological()) {
                addProductToFlowPane(product);
            }
        }
    }

    private void fillPantry() {
        productsFlowPane.getChildren().clear();
        ProductCategory productCategory;
        for (Product product : parentController.getProducts()) {
            productCategory = product.getCategory();
            if (productCategory.equals(ProductCategory.FLOUR_SUGAR_SALT) || productCategory.equals(ProductCategory.NUTS_AND_SEEDS) || productCategory.equals(ProductCategory.POD) || product.getName().equals("Cacao")) {
                addProductToFlowPane(product);
            }
        }
    }

    private void fillDrinks() {
        productsFlowPane.getChildren().clear();
        ProductCategory productCategory;
        for (Product product : parentController.getProducts()) {
            productCategory = product.getCategory();
            if (productCategory.equals(ProductCategory.COLD_DRINKS) || productCategory.equals(ProductCategory.HOT_DRINKS) && !product.getName().equals("Cacao")) {
                addProductToFlowPane(product);
            }
        }
    }

    private void fillMeatFish() {
        productsFlowPane.getChildren().clear();
        ProductCategory productCategory;
        for (Product product : parentController.getProducts()) {
            productCategory = product.getCategory();
            if (productCategory.equals(ProductCategory.MEAT) || productCategory.equals(ProductCategory.FISH)) {
                addProductToFlowPane(product);
            }
        }
    }

    private void fillProducts(ProductCategory category) {
        productsFlowPane.getChildren().clear();
        ProductCategory productCategory;
        for (Product product : parentController.getProducts()) {
            productCategory = product.getCategory();
            if (productCategory.equals(category)) {
                addProductToFlowPane(product);
            }
        }
    }

    private void fillProducts(List<Product> products) {
        productsFlowPane.getChildren().clear();
        ProductCategory productCategory;
        categoryName = "search";
        for (Product product : products) {
            addProductToFlowPane(product);
        }
    }

    private void fillFruitsAndBerries() {
        productsFlowPane.getChildren().clear();
        ProductCategory productCategory;
        for (Product product : parentController.getProducts()) {
            productCategory = product.getCategory();
            if (productCategory.equals(ProductCategory.FRUIT) || productCategory.equals(ProductCategory.CITRUS_FRUIT) || productCategory.equals(ProductCategory.EXOTIC_FRUIT) || productCategory.equals(ProductCategory.MELONS) || productCategory.equals(ProductCategory.BERRY)) {
                addProductToFlowPane(product);
            }
        }
    }

    private void fillVegetables() {
        productsFlowPane.getChildren().clear();
        ProductCategory productCategory;
        for (Product product : parentController.getProducts()) {
            productCategory = product.getCategory();
            if (productCategory.equals(ProductCategory.VEGETABLE_FRUIT) || productCategory.equals(ProductCategory.CABBAGE) || productCategory.equals(ProductCategory.ROOT_VEGETABLE) || productCategory.equals(ProductCategory.HERB)) {
                addProductToFlowPane(product);
            }
        }
    }

    private void addProductToFlowPane(Product product) {
        ProductCard productCard = parentController.getProductCardMap().get(product.getName());
        productsFlowPane.getChildren().add(productCard);
        productCards.add(productCard);
    }

    @Override
    public void update() {
        for (ProductCard productCard : productCards) {
            productCard.actOnChange();
        }
    }

    @Override
    public String getTitle() {
        switch (categoryName) {
            case "Eco":
                return "Ekologiskt";
            case "Meat":
                return "Kött";
            case "Fish":
                return "Fisk & Skaldjur";
            case "Drinks":
                return "Drycker";
            case "Pantry":
                return "Skafferi";
            case "Fruit":
                return "Frukt & Bär";
            case "Pasta":
                return "Pasta";
            case "PR":
                return "Potatis & Ris";
            case "Dairies":
                return "Mejeri, Ost & Ägg";
            case "Bread":
                return "Bröd";
            case "Sweet":
                return "Fika, Godis & Snacks";
            case "Vegetable":
                return "Grönsaker";
            case "search":
                return "Resultat av sökning";
            default:
                return "Alla Varor";

        }
    }
}
