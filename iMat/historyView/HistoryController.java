package iMat.historyView;

import iMat.IMatController;
import iMat.Model;
import iMat.checkout.CardStyle;
import iMat.checkout.ProductCard;
import iMat.shoppingCart.FocusWindow;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.util.*;

public class HistoryController extends AnchorPane implements FocusWindow {

    private IMatController parentController;
    private List<Order> orders;
    private Map<Integer, OrderListItem> orderMap = new HashMap<Integer, OrderListItem>();
    private PriorityQueue<Order> pq = new PriorityQueue<Order>(5,Comparator.comparing(Order::getDate).reversed());

    @FXML private FlowPane orderHistoryFlowPane;
    @FXML private AnchorPane detailedOrderPane;
    @FXML private Button backButton;
    @FXML private FlowPane detailedFlowPane;
    @FXML private AnchorPane transparentPane;
    @FXML private ScrollPane orderScrollPane;

    public HistoryController(IMatController parentController) {
        this.parentController = parentController;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("history.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.orders = Model.getInstance().getOrders();
        this.backButton.setVisible(false);
        sortOrders();
        updateOrderHistory();
    }

    /**
     * Updates the order history when a new order has been placed.
     */
    public void updateOrders() {
        orders = Model.getInstance().getOrders();
        sortOrders();
        updateOrderHistory();
    }

    /**
     * Adds all orders to the flow pane in order of the priority queue.
     */
    private void updateOrderHistory() {
        orderHistoryFlowPane.getChildren().clear();
        orderHistoryFlowPane.getChildren().removeAll();
        while (pq.size() > 0) {
            Order order = pq.poll();
            orderHistoryFlowPane.getChildren().add(orderMap.get(order.getOrderNumber()));
        }
    }

    /**
     * Puts all orderListItems in the priority queue
     */
    private void sortOrders() {
        for (Order order : orders) {
            OrderListItem orderListItem = new OrderListItem(order, this);
            orderMap.put(order.getOrderNumber(), orderListItem);
            pq.add(order);
        }
    }

    @FXML
    public void showDetailedOrder(Order order) {
        List<ShoppingItem> itemList = order.getItems();
        backButton.setVisible(true);
        detailedFlowPane.getChildren().clear();
        transparentPane.toFront();
        backButton.setText("Tillbaka");
        Boolean light = false;
        for (ShoppingItem item : itemList) {
            ProductCard pc = new ProductCard(item, CardStyle.Low_Wide, light);
            pc.setPrefWidth(950);
            detailedFlowPane.getChildren().add(pc);
            light = !light;
        }
    }

    @FXML
    public void closeDetailedView() {
        backButton.setVisible(false);
        orderScrollPane.toFront();
    }

    private String setOrderSizeText() {
        if (orders.size() > 1) {
            return " ordrar";
        } else {
            return " order";
        }
    }

    @Override
    public void update() {

    }

    @Override
    public String getTitle() {
        return "Mina Ordrar";
    }
}
