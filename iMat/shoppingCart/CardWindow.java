package iMat.shoppingCart;

import iMat.CardListener;
import se.chalmers.cse.dat216.project.ShoppingItem;

public interface CardWindow extends FocusWindow {

    void productCardPressed(ShoppingItem shoppingItem, CardListener cardListener);

}
