package Game.Shop.dao;

import Game.Shop.model.ClientAccount;

import java.util.List;

public interface ClientDAO {

    public void saveOrUpdate(ClientAccount client);

    public void delete(String clientId);

    public ClientAccount get(String clientId);

    public List<ClientAccount> list();

    public ClientAccount getUsernamePassword(String username,String password);
}
