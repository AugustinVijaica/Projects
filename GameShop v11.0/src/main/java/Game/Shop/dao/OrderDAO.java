package Game.Shop.dao;

import Game.Shop.model.OrderItem;
import Game.Shop.model.Product;

import java.util.List;

public interface OrderDAO {

    public List<OrderItem> list(String ClientId);

    public void addOrder(String clientID,String produsID,int QuantityToBuy,int rating);

    public void delete(int orderId);

    public void deleteFinal(int orderId);

    public List<Product> findRecommandation(String clientId);

}
