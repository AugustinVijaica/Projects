package View;

import Application.MainApplication;
import Controller.CAdmin;
import Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class GUIAdmin extends JFrame implements Observer {

    private JPanel Menu;
    private JButton showUsers;
    private JButton logoutButton;
    private JTextField usernameSearchField;
    private JButton searchButton;
    private JLabel SearchLabel;
    private JTable Resultat;
    private JLabel addEmployee;
    private JTextField numeField;
    private JTextField usernameField;
    private JPasswordField parolaField;
    private JLabel parolaLabel;
    private JLabel usernameLabel;
    private JLabel numeLabel;
    private JButton addButton;
    private JLabel stergereLabel;
    private JTextField stergereUsernameField;
    private JButton deleteButton;
    private JLabel stergereUsernameLabel;
    private JLabel usernameSearchLabel;
    private JRadioButton rolRadioButton;
    private JComboBox rolComboBox;
    private JComboBox rolAddComboBox;
    private JLabel rolLabel;

    private ModelUtilizatori mu;
    private CAdmin cad;

    public CAdmin getCad() {
        return cad;
    }

    public void setCad(CAdmin cad) {
        this.cad = cad;
    }

    public ModelUtilizatori getMu() {
        return mu;
    }

    public void setMu(ModelUtilizatori mu) {
        this.mu = mu;
    }

    public GUIAdmin(String title) throws IOException, ClassNotFoundException {

        super(title);
        this.setContentPane(Menu);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.usernameSearchField.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.SearchLabel.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.Resultat.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.Resultat.setCellSelectionEnabled(true);
        this.numeLabel.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.numeField.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.usernameLabel.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.usernameField.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.parolaLabel.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.parolaField.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.addEmployee.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.stergereLabel.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.stergereUsernameField.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.stergereUsernameLabel.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.usernameSearchLabel.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.Resultat.setVisible(false);
        this.rolRadioButton.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.rolComboBox.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.rolAddComboBox.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.rolLabel.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));

        ObjectOutputStream objectOutputStream = MainApplication.os;
        ObjectInputStream objectInputStream = MainApplication.is;

        this.mu = (ModelUtilizatori) objectInputStream.readObject();

        this.mu.adaugare(this);
        this.cad = new CAdmin(this);
        this.mu.adaugare(this.cad);

        HashMap<String,Object> map = (HashMap<String, Object>) mu.getLimba().getInfo().get("Admin");

        this.logoutButton.setText((String) map.get("Logout_button"));

        this.rolRadioButton.setText((String) ((HashMap<String, Object>)map.get("Filter")).get("Role"));

        this.rolComboBox.removeAllItems();
        this.rolComboBox.addItem((String) ((HashMap<String, Object>)map.get("Filter")).get("Role_Employee"));
        this.rolComboBox.addItem((String) ((HashMap<String, Object>)map.get("Filter")).get("Role_Admin"));

        this.showUsers.setText((String) ((HashMap<String, Object>)map.get("Filter")).get("Show_users_button"));

        this.SearchLabel.setText((String) ((HashMap<String, Object>)map.get("Search")).get("Search_header"));
        this.usernameSearchLabel.setText((String) ((HashMap<String, Object>)map.get("Search")).get("Username"));
        this.searchButton.setText((String) ((HashMap<String, Object>)map.get("Search")).get("Search_button"));


        this.rolAddComboBox.removeAllItems();
        this.rolAddComboBox.addItem((String) ((HashMap<String, Object>)map.get("Add_edit_user")).get("Role_Employee"));
        this.rolAddComboBox.addItem((String) ((HashMap<String, Object>)map.get("Add_edit_user")).get("Role_Admin"));

        this.addEmployee.setText((String) ((HashMap<String, Object>)map.get("Add_edit_user")).get("Add_edit_user_header"));
        this.usernameLabel.setText((String) ((HashMap<String, Object>)map.get("Add_edit_user")).get("Username"));
        this.parolaLabel.setText((String) ((HashMap<String, Object>)map.get("Add_edit_user")).get("Password"));
        this.rolLabel.setText((String) ((HashMap<String, Object>)map.get("Add_edit_user")).get("Role"));
        this.numeLabel.setText((String) ((HashMap<String, Object>)map.get("Add_edit_user")).get("Name"));
        this.addButton.setText((String) ((HashMap<String, Object>)map.get("Add_edit_user")).get("Add_edit_user_button"));

        this.stergereLabel.setText((String) ((HashMap<String, Object>)map.get("Delete_user")).get("Delete_user_header"));
        this.stergereUsernameLabel.setText((String) ((HashMap<String, Object>)map.get("Delete_user")).get("Username"));
        this.deleteButton.setText((String) ((HashMap<String, Object>)map.get("Delete_user")).get("Delete_user_button"));




    }

    public JComboBox getRolAddComboBox() {
        return rolAddComboBox;
    }

    public void setRolAddComboBox(JComboBox rolAddComboBox) {
        this.rolAddComboBox = rolAddComboBox;
    }

    public JTable getResultat() {
        return Resultat;
    }

    public JButton getFilterButton() {
        return showUsers;
    }

    public JButton getLoginButton() {
        return logoutButton;
    }

    public JRadioButton getRolRadioButton() {
        return rolRadioButton;
    }

    public JComboBox getRolComboBox() {
        return rolComboBox;
    }

    public JButton getSearchButton() {
        return searchButton;
    }


    public JTextField getStergereField() {
        return stergereUsernameField;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getAddButton() {
        return addButton;
    }


    public JButton getShowUsers() {
        return showUsers;
    }

    public JButton getLogoutButton() {
        return logoutButton;
    }

    public JTextField getUsernameSearchField() {
        return usernameSearchField;
    }

    public JLabel getAddEmployee() {
        return addEmployee;
    }

    public JTextField getNumeField() {
        return numeField;
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JPasswordField getParolaField() {
        return parolaField;
    }

    public JTextField getStergereUsernameField() {
        return stergereUsernameField;
    }

    @Override
    public void update() {
        if (this.mu.getOperatie().toLowerCase().contentEquals("cautare cont")) {

            HashMap<String,Object> map = (HashMap<String, Object>) mu.getLimba().getInfo().get("Guest");

            String username = mu.getInfoCautata();

            Utilizator utilizator = mu.getPf().cautareUtilizator(username);

            if (utilizator == null) {


                JOptionPane.showMessageDialog(null, (String) ((HashMap<String, Object>)map.get("Login")).get("Not_found"));
            } else {

                setTable(utilizator);
            }
        }

        else if(this.mu.getOperatie().toLowerCase().contentEquals("filtrare rol")){

            String rol = mu.getInfoCautata();

            HashMap<String,Object> map = (HashMap<String, Object>) mu.getLimba().getInfo().get("Admin");

            if(rol.contentEquals((String)((HashMap<String, Object>)map.get("Add_edit_user")).get("Role_Employee")))
                rol="Angajat";
            if(rol.contentEquals((String)((HashMap<String, Object>)map.get("Add_edit_user")).get("Role_Admin")))
                rol="Admin";


            Utilizatori utilizatori = new Utilizatori(mu.getPf().filtrareRol(rol));

            setTable(utilizatori);
        }

        else if(this.mu.getOperatie().toLowerCase().contentEquals("listare")){

            Utilizatori utilizatori = mu.getPf().citireUtilizatori();

            setTable(utilizatori);

        }

        else if(this.mu.getOperatie().toLowerCase().contentEquals("adaugare")){

            Utilizatori utilizatori = mu.getPf().citireUtilizatori();

            setTable(utilizatori);

        }

        else if(this.mu.getOperatie().toLowerCase().contentEquals("stergere")){

            Utilizatori utilizatori = mu.getPf().citireUtilizatori();

            setTable(utilizatori);
        }

        else if(this.mu.getOperatie().toLowerCase().contentEquals("limba")){
            HashMap<String,Object> map = (HashMap<String, Object>) mu.getLimba().getInfo().get("Admin");

            this.logoutButton.setText((String) map.get("Logout_button"));

            this.rolRadioButton.setText((String) ((HashMap<String, Object>)map.get("Filter")).get("Role"));

            this.rolComboBox.removeAllItems();
            this.rolComboBox.addItem((String) ((HashMap<String, Object>)map.get("Filter")).get("Role_Employee"));
            this.rolComboBox.addItem((String) ((HashMap<String, Object>)map.get("Filter")).get("Role_Admin"));

            this.showUsers.setText((String) ((HashMap<String, Object>)map.get("Filter")).get("Show_users_button"));

            this.SearchLabel.setText((String) ((HashMap<String, Object>)map.get("Search")).get("Search_header"));
            this.usernameSearchLabel.setText((String) ((HashMap<String, Object>)map.get("Search")).get("Username"));
            this.searchButton.setText((String) ((HashMap<String, Object>)map.get("Search")).get("Search_button"));


            this.rolAddComboBox.removeAllItems();
            this.rolAddComboBox.addItem((String) ((HashMap<String, Object>)map.get("Add_edit_user")).get("Role_Employee"));
            this.rolAddComboBox.addItem((String) ((HashMap<String, Object>)map.get("Add_edit_user")).get("Role_Admin"));

            this.addEmployee.setText((String) ((HashMap<String, Object>)map.get("Add_edit_user")).get("Add_edit_user_header"));
            this.usernameLabel.setText((String) ((HashMap<String, Object>)map.get("Add_edit_user")).get("Username"));
            this.parolaLabel.setText((String) ((HashMap<String, Object>)map.get("Add_edit_user")).get("Password"));
            this.rolLabel.setText((String) ((HashMap<String, Object>)map.get("Add_edit_user")).get("Role"));
            this.numeLabel.setText((String) ((HashMap<String, Object>)map.get("Add_edit_user")).get("Name"));
            this.addButton.setText((String) ((HashMap<String, Object>)map.get("Add_edit_user")).get("Add_edit_user_button"));

            this.stergereLabel.setText((String) ((HashMap<String, Object>)map.get("Delete_user")).get("Delete_user_header"));
            this.stergereUsernameLabel.setText((String) ((HashMap<String, Object>)map.get("Delete_user")).get("Username"));
            this.deleteButton.setText((String) ((HashMap<String, Object>)map.get("Delete_user")).get("Delete_user_button"));
        }
    }

    void setTable(Utilizator utilizator) {

        String nume = utilizator.getNume();
        String rol = utilizator.getRol();
        String username = utilizator.getCont();

        HashMap<String,String> map = (HashMap<String, String>) mu.getLimba().getInfo().get("Table_user");

        String[] col = {map.get("Name"), map.get("Role"), map.get("Username"), map.get("Password")};

        DefaultTableModel tableModel = new DefaultTableModel(col, 0);


        this.Resultat.setModel(tableModel);

        this.Resultat.setVisible(true);

        char[] parolaC = new char[utilizator.getParola().length()];

        for (int j = 0; j < utilizator.getParola().length(); j++) {
            parolaC[j] = '*';
        }

        Object[] date = {nume, rol, username, String.copyValueOf(parolaC)};

        tableModel.addRow(date);
    }

    void setTable(Utilizatori utilizatori){
        HashMap<String,String> map = (HashMap<String, String>) mu.getLimba().getInfo().get("Table_user");

        String[] col = {map.get("Name"), map.get("Role"), map.get("Username"), map.get("Password")};

        DefaultTableModel tableModel = new DefaultTableModel(col, 0);

        this.Resultat.setVisible(true);

        this.Resultat.setModel(tableModel);

        for (int i = 0; i < utilizatori.getListaUtilizatori().size(); i++) {
            String nume = utilizatori.getListaUtilizatori().get(i).getNume();
            String rol = utilizatori.getListaUtilizatori().get(i).getRol();
            String username = utilizatori.getListaUtilizatori().get(i).getCont();
            String parola = utilizatori.getListaUtilizatori().get(i).getParola();
            char[] parolaC = new char[parola.length()];

            for(int j=0; j<parola.length();j++){
                parolaC[j]='*';
            }

            Object[] date = {nume,rol,username,String.copyValueOf(parolaC)};

            tableModel.addRow(date);
        }
    }
}

