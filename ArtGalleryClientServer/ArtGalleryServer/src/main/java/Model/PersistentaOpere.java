package Model;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

public class PersistentaOpere implements Serializable, IPersistente<Opera, String> {


    public PersistentaOpere() {


    }


    public Institutii citireInstitutii() {


        try {
            Connection conexiune = DBConnection.getConnection();
            ArrayList<Institutie> institutii = new ArrayList<>();
            String sql = "SELECT * FROM Institutii";


            PreparedStatement getInstitutii = conexiune.prepareStatement(sql);
            ResultSet institutions = getInstitutii.executeQuery();


            while (institutions.next()) {

                String id = institutions.getString(1);

                String nume = institutions.getString(2);

                ArrayList<Opera> opere = new ArrayList<>();

                String sql2 = "select * from opere where institutie_id = ?";

                PreparedStatement getOpera = conexiune.prepareStatement(sql2);
                getOpera.setString(1, id);

                ResultSet operaAtributes = getOpera.executeQuery();

                while (operaAtributes.next()) {
                    String tip = operaAtributes.getString(2);

                    if (tip.toLowerCase().contentEquals("tablou")) {

                        String titlu = operaAtributes.getString(3);
                        String artist = operaAtributes.getString(4);
                        String data = operaAtributes.getString(5);
                        String imagine = operaAtributes.getString(6);
                        String gen = operaAtributes.getString(7);
                        String tehnica = operaAtributes.getString(8);

                        Tablou tablou = new Tablou(titlu, artist, data, gen, tehnica, imagine);

                        opere.add(tablou);
                    } else if (tip.toLowerCase().contentEquals("sculptura")) {

                        String titlu = operaAtributes.getString(3);
                        String artist = operaAtributes.getString(4);
                        String data = operaAtributes.getString(5);
                        String imagine = operaAtributes.getString(6);
                        String stil = operaAtributes.getString(7);


                        Sculptura sculptura = new Sculptura(titlu, artist, data, stil, imagine);

                        opere.add(sculptura);

                    }
                }

                Institutie institutie = new Institutie(nume, opere);

                institutii.add(institutie);

                operaAtributes.close();
                getOpera.close();

            }

            institutions.close();
            getInstitutii.close();
            conexiune.close();


            return new Institutii(institutii);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }


    public boolean salvare(Opera opera, String institutie) {


        try {
            Connection conexiune = DBConnection.getConnection();

            String sql = "select institutie_id from institutii where nume = ?";
            PreparedStatement getId = null;
            ResultSet institutieId = null;


            getId = conexiune.prepareStatement(sql);
            getId.setString(1, institutie);

            institutieId = getId.executeQuery();

            if (institutieId.next()) {

                int id = institutieId.getInt(1);

                if (opera instanceof Tablou) {


                    String sql2 = "insert into Opere(opera_tip,titlu,artist,`data`,imagine,`gen/stil`,tehnica,institutie_id) values('tablou',?,?,?,?,?,?,?);";

                    PreparedStatement update = conexiune.prepareStatement(sql2);

                    update.setString(1, opera.getTitlu());
                    update.setString(2, opera.getArtist());
                    update.setString(3, opera.getData());
                    update.setString(4, opera.getImagine());
                    update.setString(5, ((Tablou) opera).getGen());
                    update.setString(6, ((Tablou) opera).getTehnica());
                    update.setInt(7, id);

                    update.executeUpdate();

                }

                if (opera instanceof Sculptura) {


                    String sql2 = "insert into Opere(opera_tip,titlu,artist,`data`,imagine,`gen/stil`,tehnica,institutie_id) values('sculptura',?,?,?,?,?,?,?);";

                    PreparedStatement update = conexiune.prepareStatement(sql2);

                    update.setString(1, opera.getTitlu());
                    update.setString(2, opera.getArtist());
                    update.setString(3, opera.getData());
                    update.setString(4, opera.getImagine());
                    update.setString(5, ((Sculptura) opera).getTip());
                    update.setString(6, null);
                    update.setInt(7, id);

                    update.executeUpdate();

                }

                return true;

            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }


    public boolean actualizare(Opera operaVeche, Opera operaNoua, String institutie) {

        try {
            Connection conexiune = DBConnection.getConnection();

            String sql = "select institutie_id from institutii where nume = ?";
            PreparedStatement getId = null;
            ResultSet institutieId = null;


            getId = conexiune.prepareStatement(sql);
            getId.setString(1, institutie);

            institutieId = getId.executeQuery();

            if (institutieId.next()) {

                int id = institutieId.getInt(1);

                String sql2 = "update Opere set artist=?,`data`=?,imagine=?,`gen/stil`=?,tehnica=?,institutie_id=? where titlu = ?";


                PreparedStatement update = conexiune.prepareStatement(sql2);

                if (operaVeche instanceof Tablou) {

                    update.setString(1, operaNoua.getArtist());
                    update.setString(2, operaNoua.getData());
                    update.setString(3, operaNoua.getImagine());
                    update.setString(4, ((Tablou) operaNoua).getGen());
                    update.setString(5, ((Tablou) operaNoua).getTehnica());
                    update.setInt(6, id);

                    update.setString(7, operaVeche.getTitlu());

                    update.executeUpdate();
                }

                if (operaVeche instanceof Sculptura) {

                    update.setString(1, operaNoua.getArtist());
                    update.setString(2, operaNoua.getData());
                    update.setString(3, operaNoua.getImagine());
                    update.setString(4, ((Sculptura) operaNoua).getTip());
                    update.setString(5, null);
                    update.setInt(6, id);

                    update.setString(7, operaVeche.getTitlu());
                    update.executeUpdate();
                }

                return true;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;

    }


    public boolean stergere(Opera opera) {

        try {
            String sql = "delete from Opere where titlu = ?";
            PreparedStatement delete = null;

            Connection conexiune = DBConnection.getConnection();

            delete = conexiune.prepareStatement(sql);
            delete.setString(1, opera.getTitlu());

            delete.executeUpdate();

            return true;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;

    }


    public ArrayList<Opera> filtrareListaArtist(String artist) {

        try {
            ArrayList<Opera> opereFiltrate = new ArrayList<>();

            String sql = "select * from opere where artist = ?";

            PreparedStatement getOpere = null;
            ResultSet opere = null;

            Connection conexiune = DBConnection.getConnection();

            getOpere = conexiune.prepareStatement(sql);

            getOpere.setString(1, artist);

            opere = getOpere.executeQuery();

            while (opere.next()) {
                String type = opere.getString(2);

                if (type.toLowerCase().contentEquals("tablou")) {

                    String titlu = opere.getString(3);
                    String artistC = opere.getString(4);
                    String data = opere.getString(5);
                    String imagine = opere.getString(6);
                    String gen = opere.getString(7);
                    String tehnica = opere.getString(8);

                    Tablou tablou = new Tablou(titlu, artistC, data, gen, tehnica, imagine);

                    opereFiltrate.add(tablou);
                } else if (type.toLowerCase().contentEquals("sculptura")) {

                    String titlu = opere.getString(3);
                    String artistC = opere.getString(4);
                    String data = opere.getString(5);
                    String imagine = opere.getString(6);
                    String stil = opere.getString(7);


                    Sculptura sculptura = new Sculptura(titlu, artistC, data, stil, imagine);

                    opereFiltrate.add(sculptura);

                }
            }


            return opereFiltrate;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }


    public ArrayList<Opera> filtrareListaTip(String tip) {

        try{

        ArrayList<Opera> opereFiltrate = new ArrayList<>();

        String sql = "select * from opere where opera_tip = ?";

        PreparedStatement getOpere = null;
        ResultSet opere = null;

        Connection conexiune = DBConnection.getConnection();

        getOpere = conexiune.prepareStatement(sql);

        getOpere.setString(1, tip);

        opere = getOpere.executeQuery();

        while (opere.next()) {
            String type = opere.getString(2);

            if (type.toLowerCase().contentEquals("tablou")) {

                String titlu = opere.getString(3);
                String artist = opere.getString(4);
                String data = opere.getString(5);
                String imagine = opere.getString(6);
                String gen = opere.getString(7);
                String tehnica = opere.getString(8);

                Tablou tablou = new Tablou(titlu, artist, data, gen, tehnica, imagine);

                opereFiltrate.add(tablou);
            } else if (type.toLowerCase().contentEquals("sculptura")) {

                String titlu = opere.getString(3);
                String artist = opere.getString(4);
                String data = opere.getString(5);
                String imagine = opere.getString(6);
                String stil = opere.getString(7);


                Sculptura sculptura = new Sculptura(titlu, artist, data, stil, imagine);

                opereFiltrate.add(sculptura);

            }
        }


        return opereFiltrate;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;

    }

    public ArrayList<Opera> filtrare(String institutie) {

        try {

            ArrayList<Opera> opereFiltrate = new ArrayList<>();
            String sql = "SELECT institutie_id FROM Institutii WHERE nume= ?";
            PreparedStatement filter = null;
            ResultSet institutieId = null;
            PreparedStatement operas = null;
            ResultSet opere = null;
            PreparedStatement getOperaFromTip = null;
            ResultSet operaFromTip = null;

            Connection conexiune = DBConnection.getConnection();

            filter = conexiune.prepareStatement(sql);
            filter.setString(1, institutie);
            institutieId = filter.executeQuery();
            if (institutieId.next()) {
                int id = institutieId.getInt(1);
                String sql2 = "SELECT * FROM Opere WHERE institutie_id= ?";
                operas = conexiune.prepareStatement(sql2);
                operas.setInt(1, id);
                opere = operas.executeQuery();
                while (opere.next()) {

                    String tip = opere.getString(2);

                    if (tip.toLowerCase().contentEquals("tablou")) {

                        String titlu = opere.getString(3);
                        String artist = opere.getString(4);
                        String data = opere.getString(5);
                        String imagine = opere.getString(6);
                        String gen = opere.getString(7);
                        String tehnica = opere.getString(8);


                        Tablou tablou = new Tablou(titlu, artist, data, gen, tehnica, imagine);
                        opereFiltrate.add(tablou);
                    } else if (tip.toLowerCase().contentEquals("sculptura")) {


                        String titlu = opere.getString(3);
                        String artist = opere.getString(4);
                        String data = opere.getString(5);
                        String imagine = opere.getString(6);
                        String stil = opere.getString(7);


                        Sculptura sculptura = new Sculptura(titlu, artist, data, stil, imagine);
                        opereFiltrate.add(sculptura);
                    }

                }
            }


            return opereFiltrate;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }


    public Opera cautare(String titlu) {

        try {
            String sql = "SELECT * FROM opere WHERE titlu= ?";
            PreparedStatement filter = null;
            ResultSet opera = null;

            Connection conexiune = DBConnection.getConnection();

            filter = conexiune.prepareStatement(sql);
            filter.setString(1, titlu);

            opera = filter.executeQuery();

            if (opera.next()) {
                String tip = opera.getString(2);

                if (tip.toLowerCase().contentEquals("tablou")) {

                    String titluO = opera.getString(3);
                    String artist = opera.getString(4);
                    String data = opera.getString(5);
                    String imagine = opera.getString(6);
                    String gen = opera.getString(7);
                    String tehnica = opera.getString(8);


                    Tablou tablou = new Tablou(titluO, artist, data, gen, tehnica, imagine);

                    return tablou;
                } else if (tip.toLowerCase().contentEquals("sculptura")) {


                    String titluO = opera.getString(3);
                    String artist = opera.getString(4);
                    String data = opera.getString(5);
                    String imagine = opera.getString(6);
                    String stil = opera.getString(7);


                    Sculptura sculptura = new Sculptura(titluO, artist, data, stil, imagine);

                    return sculptura;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public Institutie cautareInstitutieNume(String nume) {

        try{

        String sql = "SELECT * FROM Institutii where nume=?";

        PreparedStatement getInstitutii = null;
        ResultSet institutions = null;


        Connection conexiune = DBConnection.getConnection();

        getInstitutii = conexiune.prepareStatement(sql);
        getInstitutii.setString(1, nume);
        institutions = getInstitutii.executeQuery();


        if (institutions.next()) {

            String id = institutions.getString(1);
            String numeI = institutions.getString(2);

            ArrayList<Opera> opere = new ArrayList<>();

            String sql2 = "select * from opere where institutie_id = ?";

            PreparedStatement getOpera = conexiune.prepareStatement(sql2);
            getOpera.setString(1, id);

            ResultSet operaAtributes = getOpera.executeQuery();

            while (operaAtributes.next()) {
                String tip = operaAtributes.getString(2);

                if (tip.toLowerCase().contentEquals("tablou")) {

                    String titlu = operaAtributes.getString(3);
                    String artist = operaAtributes.getString(4);
                    String data = operaAtributes.getString(5);
                    String imagine = operaAtributes.getString(6);
                    String gen = operaAtributes.getString(7);
                    String tehnica = operaAtributes.getString(8);

                    Tablou tablou = new Tablou(titlu, artist, data, gen, tehnica, imagine);

                    opere.add(tablou);
                } else if (tip.toLowerCase().contentEquals("sculptura")) {

                    String titlu = operaAtributes.getString(3);
                    String artist = operaAtributes.getString(4);
                    String data = operaAtributes.getString(5);
                    String imagine = operaAtributes.getString(6);
                    String stil = operaAtributes.getString(7);


                    Sculptura sculptura = new Sculptura(titlu, artist, data, stil, imagine);

                    opere.add(sculptura);

                }
            }

            Institutie institutie = new Institutie(nume, opere);

            return institutie;

        }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return null;
    }


}

