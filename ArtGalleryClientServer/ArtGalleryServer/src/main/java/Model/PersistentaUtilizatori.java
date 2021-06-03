package Model;


import javax.jws.WebMethod;
import javax.jws.WebService;
import java.io.IOException;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;



public class PersistentaUtilizatori implements Serializable, IPersistente<Utilizator,String> {



    public PersistentaUtilizatori() {

    }


    public boolean salvare(Utilizator utilizator,String n){

        String sql = "insert into Utilizatori values(?,?,?,?);";
        PreparedStatement update = null;

        try {

            Connection conexiune = DBConnection.getConnection();

            update= conexiune.prepareStatement(sql);
            update.setString(1,utilizator.getCont());
            update.setString(2,utilizator.getParola());
            update.setString(3,utilizator.getNume());
            update.setString(4,utilizator.getRol());

            update.executeUpdate();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }


    public boolean actualizare(Utilizator utilizatorVechi, Utilizator utilizatorNou,String n){

        String sql = "update Utilizatori set cont=?,parola=?,nume=?,rol=? where cont = ?";

        PreparedStatement update = null;
        try {
            Connection conexiune = DBConnection.getConnection();
            update = conexiune.prepareStatement(sql);


            update.setString(1, utilizatorNou.getCont());
            update.setString(2, utilizatorNou.getParola());
            update.setString(3, utilizatorNou.getNume());
            update.setString(4, utilizatorNou.getRol());
            update.setString(5,utilizatorVechi.getCont());
            update.executeUpdate();


            return true;
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    public boolean stergere(Utilizator utilizator){

        String sql = "delete from Utilizatori where cont = ?";
        PreparedStatement delete = null;

        try {
            Connection conexiune = DBConnection.getConnection();
            delete = conexiune.prepareStatement(sql);
            delete.setString(1, utilizator.getCont());

            delete.executeUpdate();

            return true;

        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

        return false;


    }


    public Utilizatori citireUtilizatori(){

        ArrayList<Utilizator> utilizatori = new ArrayList<>();
        String sql = "SELECT * FROM Utilizatori";

        PreparedStatement getUtilizatori = null;
        ResultSet users = null;

        try {
            Connection conexiune = DBConnection.getConnection();
            getUtilizatori = conexiune.prepareStatement(sql);
            users = getUtilizatori.executeQuery();

            while(users.next()){
                Utilizator user = new Utilizator(users.getString(3),users.getString(4),users.getString(1),users.getString(2));
                utilizatori.add(user);

            }

            return new Utilizatori(utilizatori);

        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }


    public ArrayList<Utilizator> filtrare(String rol){



        ArrayList<Utilizator> utilizatori = new ArrayList<>();
        String sql = "SELECT * FROM Utilizatori where rol = ?";

        PreparedStatement getUtilizatori = null;
        ResultSet users = null;

        try {
            Connection conexiune = DBConnection.getConnection();
            getUtilizatori = conexiune.prepareStatement(sql);
            getUtilizatori.setString(1,rol);
            users = getUtilizatori.executeQuery();

            while(users.next()){
                Utilizator user = new Utilizator(users.getString(3),users.getString(4),users.getString(1),users.getString(2));
                utilizatori.add(user);

            }

            return utilizatori;

        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

        return null;

    }


    public Utilizator cautareUtilizator(String username,String parola){


        String sql = "SELECT * FROM Utilizatori where cont = ? and parola = ?";

        PreparedStatement getUtilizatori = null;
        ResultSet users = null;

        try {
            Connection conexiune = DBConnection.getConnection();
            getUtilizatori = conexiune.prepareStatement(sql);
            getUtilizatori.setString(1,username);
            getUtilizatori.setString(2,parola);
            users = getUtilizatori.executeQuery();

            if(users.next()){

                Utilizator user = new Utilizator(users.getString(3),users.getString(4),users.getString(1),users.getString(2));
                return user;

            }

        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public Utilizator cautare(String username){


        String sql = "SELECT * FROM Utilizatori where cont = ?";

        PreparedStatement getUtilizatori = null;
        ResultSet users = null;

        try {
            Connection conexiune = DBConnection.getConnection();
            getUtilizatori = conexiune.prepareStatement(sql);
            getUtilizatori.setString(1,username);
            users = getUtilizatori.executeQuery();

            if(users.next()){
                Utilizator user = new Utilizator(users.getString(3),users.getString(4),users.getString(1),users.getString(2));
                return user;

            }

        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }


}


