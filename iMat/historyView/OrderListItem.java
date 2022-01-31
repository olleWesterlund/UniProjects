package iMat.historyView;

import iMat.Model;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Order;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class OrderListItem extends AnchorPane {

    private HistoryController parentController;
    private boolean isDelivered;
    private Order order;
    @FXML private Label orderStatusLabel;
    @FXML private Label sumLabel;
    @FXML private Label dateLabel;
    @FXML private Label orderNrLabel;
    @FXML private Button showDetails;

    public OrderListItem(Order order, HistoryController parentController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("orders_listitem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.order = order;
        this.parentController = parentController;
        setStatus();
        this.orderStatusLabel.setText(checkStatus());
        this.sumLabel.setText("Summa: " + Model.getInstance().getPriceTotal(order) + "kr");
        this.dateLabel.setText("Datum: " + order.getDate());
        this.orderNrLabel.setText("Order: " + order.getOrderNumber());
        this.showDetails.setText("Visa detaljer");
    }

    private String checkStatus() {
        String status = "";
        if (isDelivered) {
            status = "Levererad";
        } else {
            status = "Plockas";
        }
        return status;
    }

    private void setStatus() {
        LocalDate today = LocalDate.now();
        LocalDate orderDate = order.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long daysBetween = ChronoUnit.DAYS.between(orderDate, today);
        if (daysBetween > 2) {
            isDelivered = true;
        } else {
            isDelivered = false;
        }
    }

    @FXML
    protected void onClick(Event event) {
        parentController.showDetailedOrder(order);
    }


}
