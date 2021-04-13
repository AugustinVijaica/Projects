package Game.Shop.dao;

import Game.Shop.model.GenreRecommandation;
import Game.Shop.model.Product;

import java.util.List;

public interface GenreDAO {

    public List<Product> findRecommandation(String clientID);

}
