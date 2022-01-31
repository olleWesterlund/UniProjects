package iMat.startView;

import iMat.IMatController;
import iMat.Model;
import iMat.ProductCard;
import iMat.shoppingCart.FocusWindow;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.util.*;

public class startController extends AnchorPane implements FocusWindow {
    private IMatController parentController;
    private final Model model = Model.getInstance();
    private Random random = new Random();
    private List<Product> productList = new ArrayList<>();
    private List<Order> orderList;
    private Map<String, ShoppingItem> itemMap = new HashMap<>(20);
    private PriorityQueue<ShoppingItem> priorityQueue = new PriorityQueue<ShoppingItem>(12, Comparator.comparing(ShoppingItem::getAmount).reversed());
    private final int numberOfProductCards = 15;
    @FXML
    private AnchorPane startViewPane;
    @FXML
    private FlowPane popularFlowPane;

    public startController(IMatController parentController) {
        this.parentController = parentController;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("start_View.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.orderList = model.getOrders();
        init();
    }

    /**
     * Initiates the startView with the productCards from the products in the productList.
     */
    private void init() {
        this.popularFlowPane.getChildren().clear();
        if (model.isFirstRun()) {
            generateSpecificProducts();
        } else {
            generatePopularProducts();
            int size = numberOfProductCards - productList.size();
            if (size > 0) {
                generateProduct(size);
            }
        }
        for (Product product : productList) {
            ProductCard productCard = this.parentController.getProductCardMap().get(product.getName());
            productCard.actOnChange();
            this.popularFlowPane.getChildren().add(productCard);
        }
        addSpacer();
    }

    private void addSpacer() {
        AnchorPane spacer = new AnchorPane();
        spacer.setPrefSize(205, 40);
        popularFlowPane.getChildren().add(spacer);
    }

    /**
     * Generates a number of random productCards and adds them to the productList.
     * @param nrOfRandomProducts
     * The number of random generated products that will be generated.
     */
    private void generateProduct(int nrOfRandomProducts) {
        while (productList.size() < nrOfRandomProducts) {
            int productNumber = random.nextInt(154) + 1;
            Product product = model.getProduct(productNumber);
            if (!productList.contains(product)) {
                productList.add(product);
            }
        }
    }

    /**
     * Generates 15 specific products.
     */
    private void generateSpecificProducts() {
        productList.add(model.getProduct(116)); //Potatis
        productList.add(model.getProduct(137)); //Kanelbullar
        productList.add(model.getProduct(141)); //Basilika
        productList.add(model.getProduct(85));  //Ägg
        productList.add(model.getProduct(96));  //Vetemjöl
        productList.add(model.getProduct(30));  //Kaffe
        productList.add(model.getProduct(28));  //Earl grey
        productList.add(model.getProduct(135)); //Choklad
        productList.add(model.getProduct(50));  //Lax
        productList.add(model.getProduct(57));  //Gurka
        productList.add(model.getProduct(150)); //Eko Köttfärs
        productList.add(model.getProduct(123)); //Morot
        productList.add(model.getProduct(113)); //Basmati-ris
        productList.add(model.getProduct(78));  //Filmjölk
        productList.add(model.getProduct(14));  //Vörtlimpa
    }

    /**
     * Generates the most bought products from all previous orders.
     */
    private void generatePopularProducts() {
        if (orderList.size() < 1) {
            generateSpecificProducts();
        } else {
            for (Order order : orderList) {
                List<ShoppingItem> itemList = order.getItems();
                for (ShoppingItem item : itemList) {
                    String name = item.getProduct().getName();
                    if (!itemMap.containsKey(name)) {
                        itemMap.put(name, item);
                    } else {
                        double amount = item.getAmount() + itemMap.get(name).getAmount();
                        ShoppingItem newItem = new ShoppingItem(item.getProduct(), amount);
                        itemMap.replace(name, newItem);
                    }
                }
            }
            Collection<ShoppingItem> collection = itemMap.values();
            for (ShoppingItem item : collection) {
                priorityQueue.add(item);
            }
            while (priorityQueue.size() > 0) {
                ShoppingItem item = priorityQueue.poll();
                Product product = item.getProduct();
                if (!productList.contains(product)) {
                    productList.add(product);
                }
                if (productList.size() == numberOfProductCards) {
                    break;
                }
            }
        }
    }

    @Override
    public void update() {

    }

    @Override
    public String getTitle() {
        return "Populära varor";
    }
}
