package Game.Shop.dao;

import Game.Shop.model.Country;
import Game.Shop.model.Product;

import java.util.List;

public interface CountryDAO {

    public void saveOrUpdate(Country country);

    public void delete(String name);

    public List<Country> list(String productId);

    public Product findRecommandation(String clientID);

}
