package Controller;

import Application.MainApplication;
import Model.ModelOpere;
import Model.ModelUtilizatori;
import Model.Observer;
import Model.Utilizator;
import View.GUIAdmin;
import View.GUIVizitator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.HashMap;
public class CAdmin implements Observer {

    private GUIAdmin view;
    private ModelUtilizatori mu;

    public void setView(GUIAdmin view) {
        this.view = view;
    }


    public CAdmin(GUIAdmin view) {
        this.view = view;
        this.mu = view.getMu();


        this.view.getFilterButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterAction();
            }
        });


        this.view.getSearchButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchAction();
            }
        });

        this.view.getLoginButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModelOpere mop = null;
                try {
                    mop = new ModelOpere();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                mop.setLimba(mu.getLimba());
                ModelUtilizatori mut = new ModelUtilizatori();
                mut.setLimba(mu.getLimba());
                GUIVizitator gv = null;
                try {
                    ObjectOutputStream os = MainApplication.os;
                    os.writeObject("log in");
                    gv = new GUIVizitator("Home");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
                CVizitator vizitator=gv.getCv();
                view.dispose();
            }
        });

        this.view.getAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               addAction();
            }
        });

        this.view.getDeleteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteAction();
            }
        });

        this.view.getResultat().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                HashMap<String,Object> map = (HashMap<String, Object>) mu.getLimba().getInfo().get("Guest");

                Utilizator utilizator = mu.getPf().cautareUtilizator(view.getResultat().getValueAt(view.getResultat().getSelectedRow(), 2).toString());

                JOptionPane.showMessageDialog(null,(String) ((HashMap<String, Object>)map.get("Login")).get("Password")+": "+utilizator.getParola());
            }
        });
    }


    private void clearFields() {
        this.view.getNumeField().setText("");
        this.view.getUsernameField().setText("");
        this.view.getParolaField().setText("");
        this.view.getStergereUsernameField().setText("");
        this.view.getUsernameSearchField().setText("");
    }

    private void deleteAction(){

        HashMap<String,Object> map = (HashMap<String, Object>) mu.getLimba().getInfo().get("Guest");

        String username = view.getStergereUsernameField().getText();

        Utilizator utilizator = mu.getPf().cautareUtilizator(username);

        if(utilizator == null){
            JOptionPane.showMessageDialog(view,(String) ((HashMap<String, Object>)map.get("Login")).get("Not_found"));
        }

        else{
            mu.getPf().stergereUtilizator(utilizator);
        }

        clearFields();
        mu.setOperatie("stergere");
    }

    private void addAction(){



        String nume =   view.getNumeField().getText();
        String username = view.getUsernameField().getText();
        String parola = String.valueOf(view.getParolaField().getPassword());
        String rol = String.valueOf(view.getRolAddComboBox().getSelectedItem());

        Utilizator utilizator = mu.getPf().cautareUtilizator(username);

        if (utilizator == null) {

            Utilizator utilizatorNou = new Utilizator(nume, rol, username, parola);

            mu.getPf().salvareUtilizator(utilizatorNou);
        } else {

            String numeNou = nume;

            String usernameNou = username;

            String parolaNoua = parola;

            String rolNou = rol;

            if (numeNou.isEmpty()) numeNou = utilizator.getNume();

            if (usernameNou.isEmpty()) usernameNou = utilizator.getCont();

            if (parolaNoua.isEmpty()) parolaNoua = utilizator.getParola();

            if (rolNou.isEmpty()) rolNou = utilizator.getRol();

            Utilizator utilizatorActualizat = new Utilizator(numeNou, rolNou, usernameNou, parolaNoua);

            mu.getPf().actualizareUtilizator(utilizator, utilizatorActualizat);

        }

        clearFields();
        mu.setOperatie("adaugare");
    }

    private void searchAction(){
        String username = view.getUsernameSearchField().getText();

            mu.setInfoCautata(username);
            mu.setOperatie("cautare cont");

    }

    private void filterAction(){

        String rol = (String) view.getRolComboBox().getSelectedItem();
        mu.setInfoCautata(rol);

        if(view.getRolRadioButton().isSelected()){

            mu.setOperatie("filtrare rol");
        }

        else{
            mu.setOperatie("listare");
        }
    }

    @Override
    public void update() {

    }
}
