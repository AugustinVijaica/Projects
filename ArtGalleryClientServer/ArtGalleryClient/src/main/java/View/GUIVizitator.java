package View;


import Application.MainApplication;
import Controller.CVizitator;
import Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;


public class GUIVizitator extends JFrame implements Observer {



    private JPanel Menu;
    private JButton Filter;
    private JRadioButton institutieRadioButton;
    private JRadioButton artistRadioButton;
    private JRadioButton tipRadioButton;
    private JTextField filtrareDupaTextField;
    private JLabel Filtru;
    private JPasswordField password;
    private JTextField username;
    private JLabel User;
    private JLabel Parola;
    private JButton loginButton;
    private JTextField SearchField;
    private JButton searchButton;
    private JLabel SearchLabel;
    private JTable Resultat;
    private JComboBox Institutii;
    private JRadioButton pieChartRadioButton;
    private JRadioButton barChartRadioButton;
    private JButton generateButton;
    private JLabel graficTip;
    private JComboBox graficComboBox;
    private JButton RomaniaButton;
    private JButton EnglishButton;
    private JButton FrenchButton;
    private JButton GermanButton;


    private ModelOpere mo;
    private ModelUtilizatori mu;
    private CVizitator cv;




    public JTextField getSearchField() {
        return SearchField;
    }

    public JButton getGenerateButton() {
        return generateButton;
    }


    public JTextField getFiltrareDupaTextField() {
        return filtrareDupaTextField;
    }

    public JButton getGermanButton() {
        return GermanButton;
    }

    public void setGermanButton(JButton germanButton) {
        GermanButton = germanButton;
    }

    public ModelUtilizatori getMu() {
        return mu;
    }

    public void setMu(ModelUtilizatori mu) {
        this.mu = mu;
    }

    public GUIVizitator(String title) throws IOException, ClassNotFoundException {

        super(title);
        this.setContentPane(Menu);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
        this.artistRadioButton.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.institutieRadioButton.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.tipRadioButton.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.Filtru.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.User.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.Parola.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.username.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.password.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.SearchField.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.SearchLabel.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.Resultat.setFont(new Font("Times New Roman", Font.CENTER_BASELINE,20));
        this.Institutii.setFont(new Font("Times New Roman", Font.CENTER_BASELINE,20));
        this.pieChartRadioButton.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.barChartRadioButton.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.graficTip.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.Resultat.setCellSelectionEnabled(true);
        this.Institutii.setVisible(false);
        this.graficComboBox.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.EnglishButton.setIcon(new ImageIcon("Images/UK.png"));
        this.RomaniaButton.setIcon(new ImageIcon("Images/Romania.png"));
        this.FrenchButton.setIcon(new ImageIcon("Images/France.png"));
        this.GermanButton.setIcon(new ImageIcon("Images/Germany.png"));


        ObjectOutputStream objectOutputStream = MainApplication.os;
        ObjectInputStream objectInputStream = MainApplication.is;

        this.mo = (ModelOpere) objectInputStream.readObject();
        this.mu = (ModelUtilizatori) objectInputStream.readObject();

        this.cv = new CVizitator(this);
        this.mo.adaugare(this);
        this.mo.adaugare(this.cv);
        this.mu.adaugare(this);

        HashMap<String,Object> map = (HashMap<String, Object>) mo.getLimba().getInfo().get("Guest");


        this.artistRadioButton.setText((String) ((HashMap<String, Object>)map.get("Filter")).get("Artist"));
        this.institutieRadioButton.setText((String) ((HashMap<String, Object>)map.get("Filter")).get("Institution"));
        this.tipRadioButton.setText((String) ((HashMap<String, Object>)map.get("Filter")).get("Type"));
        this.Filtru.setText((String) ((HashMap<String, Object>)map.get("Filter")).get("Filter_by"));
        this.Filter.setText((String) ((HashMap<String, Object>)map.get("Filter")).get("Filter_button"));

        this.SearchLabel.setText((String) ((HashMap<String, Object>)map.get("Search")).get("Search_title"));
        this.searchButton.setText((String) ((HashMap<String, Object>)map.get("Search")).get("Search_button"));

        this.User.setText((String) ((HashMap<String, Object>)map.get("Login")).get("Username"));
        this.Parola.setText((String) ((HashMap<String, Object>)map.get("Login")).get("Password"));
        this.loginButton.setText((String) ((HashMap<String, Object>)map.get("Login")).get("Login_button"));

        this.graficTip.setText((String) ((HashMap<String, Object>)map.get("Graph")).get("Graph_from"));


        this.graficComboBox.removeAllItems();
        this.graficComboBox.addItem((String) ((HashMap<String, Object>)map.get("Graph")).get("Opera_type"));
        this.graficComboBox.addItem((String) ((HashMap<String, Object>)map.get("Graph")).get("Artists"));
        this.graficComboBox.addItem((String) ((HashMap<String, Object>)map.get("Graph")).get("Institutions"));
        this.generateButton.setText((String) ((HashMap<String, Object>)map.get("Graph")).get("Generate_button"));
        this.pieChartRadioButton.setText((String) ((HashMap<String, Object>)map.get("Graph")).get("Pie_chart"));
        this.barChartRadioButton.setText((String) ((HashMap<String, Object>)map.get("Graph")).get("Bar_chart"));
    }

    public JComboBox getInstitutii() {
        return Institutii;
    }

    public JRadioButton getPieChartRadioButton() {
        return pieChartRadioButton;
    }

    public JRadioButton getBarChartRadioButton() {
        return barChartRadioButton;
    }

    public JComboBox getGraficComboBox() {
        return graficComboBox;
    }

    public JTable getResultat() {
        return Resultat;
    }

    public JButton getLoginButton() {
        return loginButton;
    }


    public JButton getSearchButton() {
        return searchButton;
    }

    public JButton getFilter() {
        return Filter;
    }


    public JTextField getUsername() {
        return username;
    }

    public JPasswordField getPassword() {
        return password;
    }

    public JRadioButton getInstitutieRadioButton() {
        return institutieRadioButton;
    }

    public JButton getRomaniaButton() {
        return RomaniaButton;
    }

    public void setRomaniaButton(JButton romaniaButton) {
        RomaniaButton = romaniaButton;
    }

    public JButton getEnglishButton() {
        return EnglishButton;
    }

    public void setEnglishButton(JButton englishButton) {
        EnglishButton = englishButton;
    }

    public JButton getFrenchButton() {
        return FrenchButton;
    }

    public void setFrenchButton(JButton frenchButton) {
        FrenchButton = frenchButton;
    }

    public JRadioButton getArtistRadioButton() {
        return artistRadioButton;
    }


    public JRadioButton getTipRadioButton() {
        return tipRadioButton;
    }


    public ModelOpere getMo() {
        return mo;
    }

    public void setMo(ModelOpere mo) {
        this.mo = mo;
    }

    public CVizitator getCv() {
        return cv;
    }

    public void setCv(CVizitator cv) {
        this.cv = cv;
    }

    @Override
    public void update() {

        if(this.mo.getOperatie().toLowerCase().contentEquals("filtrare artist")){
            String artist = this.mo.getInfoCautata();
            ArrayList<Opera> filtrate = null;
            filtrate = this.mo.getPf().filtrareListaArtist(artist);
            this.setTable(filtrate);
        }

        else if(this.mo.getOperatie().toLowerCase().contentEquals("filtrare tip")){
            String tip = this.mo.getInfoCautata();
            HashMap<String,Object> map = (HashMap<String, Object>) mo.getLimba().getInfo().get("Employee");
            if(tip.contentEquals((String)((HashMap<String, Object>)map.get("Add_edit")).get("Painting")))
                tip="Tablou";
            else if(tip.contentEquals((String)((HashMap<String, Object>)map.get("Add_edit")).get("Sculpture")))
                tip="Sculptura";
            ArrayList<Opera> filtrate = null;
            filtrate = this.mo.getPf().filtrareListaTip(tip);
            this.setTable(filtrate);
        }

        else if(this.mo.getOperatie().toLowerCase().contentEquals("filtrare institutie")){
            String institutie = this.mo.getInfoCautata();
            ArrayList<Opera> filtrate = null;
            filtrate = this.mo.getPf().filtrareListaInstitutie(institutie);
            this.setTable(filtrate);
        }


        else if(this.mo.getOperatie().toLowerCase().contentEquals("cautare titlu")){

            HashMap<String,Object> map = (HashMap<String, Object>) mo.getLimba().getInfo().get("Employee");

            String cautat  = mo.getInfoCautata();

            Opera opera = null;
            opera = mo.getPf().cautareOperaTitlu(cautat);

            if(opera == null){
                JOptionPane.showMessageDialog(null, (String)((HashMap<String, Object>)map.get("Delete")).get("Not_found"));
            }

            else{
                setTable(opera);
            }
        }

        else if(this.mo.getOperatie().toLowerCase().contentEquals("limba")){
            HashMap<String,Object> map = (HashMap<String, Object>) mo.getLimba().getInfo().get("Guest");


            this.artistRadioButton.setText((String) ((HashMap<String, Object>)map.get("Filter")).get("Artist"));
            this.institutieRadioButton.setText((String) ((HashMap<String, Object>)map.get("Filter")).get("Institution"));
            this.tipRadioButton.setText((String) ((HashMap<String, Object>)map.get("Filter")).get("Type"));
            this.Filtru.setText((String) ((HashMap<String, Object>)map.get("Filter")).get("Filter_by"));
            this.Filter.setText((String) ((HashMap<String, Object>)map.get("Filter")).get("Filter_button"));

            this.SearchLabel.setText((String) ((HashMap<String, Object>)map.get("Search")).get("Search_title"));
            this.searchButton.setText((String) ((HashMap<String, Object>)map.get("Search")).get("Search_button"));

            this.User.setText((String) ((HashMap<String, Object>)map.get("Login")).get("Username"));
            this.Parola.setText((String) ((HashMap<String, Object>)map.get("Login")).get("Password"));
            this.loginButton.setText((String) ((HashMap<String, Object>)map.get("Login")).get("Login_button"));

            this.graficTip.setText((String) ((HashMap<String, Object>)map.get("Graph")).get("Graph_from"));
            this.graficComboBox.removeAllItems();
            this.graficComboBox.addItem((String) ((HashMap<String, Object>)map.get("Graph")).get("Opera_type"));
            this.graficComboBox.addItem((String) ((HashMap<String, Object>)map.get("Graph")).get("Artists"));
            this.graficComboBox.addItem((String) ((HashMap<String, Object>)map.get("Graph")).get("Institutions"));
            this.generateButton.setText((String) ((HashMap<String, Object>)map.get("Graph")).get("Generate_button"));
            this.pieChartRadioButton.setText((String) ((HashMap<String, Object>)map.get("Graph")).get("Pie_chart"));
            this.barChartRadioButton.setText((String) ((HashMap<String, Object>)map.get("Graph")).get("Bar_chart"));
        }

    }


    public void setTable(ArrayList<Opera>filtrate){

        HashMap<String,String> map = (HashMap<String, String>) mo.getLimba().getInfo().get("Table_opera");

        String[] col = {map.get("Title"), map.get("Artist"), map.get("Date"), map.get("Genre_Type"),map.get("Technique"), map.get("Image")};

        DefaultTableModel tableModel = new DefaultTableModel(col, 0);

        this.Resultat.setModel(tableModel);

        for (int i = 0; i < filtrate.size(); i++) {
            String titlu = filtrate.get(i).getTitlu();
            String artist = filtrate.get(i).getArtist();
            String data = filtrate.get(i).getData();
            String imagine = filtrate.get(i).getImagine();


            if (filtrate.get(i) instanceof Tablou) {
                String gen = ((Tablou) filtrate.get(i)).getGen();
                String tehnica = ((Tablou) filtrate.get(i)).getTehnica();
                Object[] date = {titlu, artist, data, gen,tehnica,imagine};
                tableModel.addRow(date);
            } else if (filtrate.get(i) instanceof Sculptura) {
                String tip = ((Sculptura) filtrate.get(i)).getTip();
                Object[] date = {titlu, artist, data, tip, "       ------   ", imagine};
                tableModel.addRow(date);
            }
        }
    }

    public void setTable(Opera opera){


        HashMap<String,String> map = (HashMap<String, String>) mo.getLimba().getInfo().get("Table_opera");

        String[] col = {map.get("Title"), map.get("Artist"), map.get("Date"), map.get("Genre_Type"),map.get("Technique"), map.get("Image")};

        DefaultTableModel tableModel = new DefaultTableModel(col, 0);

        this.Resultat.setModel(tableModel);

            String titlu = opera.getTitlu();
            String artist = opera.getArtist();
            String data = opera.getData();
            String imagine = opera.getImagine();


            if (opera instanceof Tablou) {
                String gen = ((Tablou) opera).getGen();
                String tehnica = ((Tablou) opera).getTehnica();
                Object[] date = {titlu, artist, data, gen,tehnica,imagine};
                tableModel.addRow(date);
            } else if (opera instanceof Sculptura) {
                String tip = ((Sculptura) opera).getTip();
                Object[] date = {titlu, artist, data, tip, "       ------   ", imagine};
                tableModel.addRow(date);
            }
    }



}
