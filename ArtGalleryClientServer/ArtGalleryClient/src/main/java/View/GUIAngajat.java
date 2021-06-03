package View;
import Application.MainApplication;
import Controller.CAngajat;
import Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;


public class GUIAngajat extends JFrame implements Observer {

    private JPanel Menu;
    private JButton Filter;
    private JRadioButton institutieRadioButton;
    private JRadioButton artistRadioButton;
    private JRadioButton tipRadioButton;
    private JLabel Filtru;
    private JButton logoutButton;
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
    private JLabel addOpera;
    private JComboBox tipComboBox;
    private JComboBox institutiiComboBox;
    private JTextField titluField;
    private JTextField artistField;
    private JTextField dataField;
    private JTextField genTipField;
    private JLabel tipGenLabel;
    private JLabel dataLabel;
    private JLabel artistLabel;
    private JLabel titluLabel;
    private JTextField tehnicaField;
    private JLabel imagineLabel;
    private JTextField imagineField;
    private JLabel tehnicaLabel;
    private JLabel institutieLabel;
    private JLabel tipLabel;
    private JButton addButton;
    private JLabel stergereLabel;
    private JTextField stergereField;
    private JButton deleteOperaButton;
    private JLabel stergereTitluLabel;
    private JButton raportJSON;
    private JButton raportCSV;

    private ModelOpere mo;
    private CAngajat ca;


    public JTextField getSearchField() {
        return SearchField;
    }

    public JButton getGenerateButton() {
        return generateButton;
    }

    public JComboBox getInstitutiiComboBox() {
        return institutiiComboBox;
    }

    public JLabel getTipGenLabel() {
        return tipGenLabel;
    }

    public JTextField getTitluField() {
        return titluField;
    }

    public JTextField getArtistField() {
        return artistField;
    }

    public JTextField getDataField() {
        return dataField;
    }

    public JTextField getGenTipField() {
        return genTipField;
    }

    public JTextField getTehnicaField() {
        return tehnicaField;
    }

    public JTextField getImagineField() {
        return imagineField;
    }

    public JLabel getTehnicaLabel() {
        return tehnicaLabel;
    }


    public JButton getFilter() {
        return Filter;
    }

    public JButton getRaportCSV() {
        return raportCSV;
    }

    public JButton getRaportJSON() {
        return raportJSON;
    }

    public ModelOpere getMo() {
        return mo;
    }

    public void setMo(ModelOpere mo) {
        this.mo = mo;
    }

    public CAngajat getCa() {
        return ca;
    }

    public void setCa(CAngajat ca) {
        this.ca = ca;
    }

    public GUIAngajat(String title) throws IOException, ClassNotFoundException, SQLException {

        this.setTitle(title);
        this.setVisible(true);
        this.setContentPane(Menu);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.artistRadioButton.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.institutieRadioButton.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.tipRadioButton.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.Filtru.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.SearchField.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.SearchLabel.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.Resultat.setFont(new Font("Times New Roman", Font.CENTER_BASELINE,20));
        this.Institutii.setFont(new Font("Times New Roman", Font.CENTER_BASELINE,20));
        this.pieChartRadioButton.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.barChartRadioButton.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.graficTip.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.Institutii.setVisible(false);
        this.graficComboBox.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.titluLabel.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.titluField.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.artistLabel.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.artistField.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.dataLabel.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.dataField.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.imagineLabel.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.imagineField.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.tipGenLabel.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.genTipField.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.tehnicaLabel.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.tehnicaField.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.addOpera.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.institutieLabel.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.tipLabel.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.institutiiComboBox.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.tipComboBox.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.stergereLabel.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.stergereField.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.stergereTitluLabel.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
        this.getTipGenLabel().setText("Gen");
        this.Resultat.setCellSelectionEnabled(true);
        this.getTehnicaLabel().setText("Tehnica");

        ObjectOutputStream objectOutputStream = MainApplication.os;
        ObjectInputStream objectInputStream = MainApplication.is;

        this.mo = (ModelOpere) objectInputStream.readObject();

        this.ca = new CAngajat(this);
        this.mo.adaugare(this);
        this.mo.adaugare(this.ca);



        HashMap<String,Object> map = (HashMap<String, Object>) mo.getLimba().getInfo().get("Employee");


        this.artistRadioButton.setText((String) ((HashMap<String, Object>)map.get("Filter")).get("Artist"));
        this.institutieRadioButton.setText((String) ((HashMap<String, Object>)map.get("Filter")).get("Institution"));
        this.tipRadioButton.setText((String) ((HashMap<String, Object>)map.get("Filter")).get("Type"));
        this.Filtru.setText((String) ((HashMap<String, Object>)map.get("Filter")).get("Filter_by"));
        this.Filter.setText((String) ((HashMap<String, Object>)map.get("Filter")).get("Filter_button"));

        this.SearchLabel.setText((String) ((HashMap<String, Object>)map.get("Search")).get("Search_title"));
        this.searchButton.setText((String) ((HashMap<String, Object>)map.get("Search")).get("Search_button"));


        this.graficTip.setText((String) ((HashMap<String, Object>)map.get("Graph")).get("Graph_from"));
        this.graficComboBox.removeAllItems();
        this.graficComboBox.addItem((String) ((HashMap<String, Object>)map.get("Graph")).get("Opera_type"));
        this.graficComboBox.addItem((String) ((HashMap<String, Object>)map.get("Graph")).get("Artists"));
        this.graficComboBox.addItem((String) ((HashMap<String, Object>)map.get("Graph")).get("Institutions"));
        this.generateButton.setText((String) ((HashMap<String, Object>)map.get("Graph")).get("Generate_button"));
        this.pieChartRadioButton.setText((String) ((HashMap<String, Object>)map.get("Graph")).get("Pie_chart"));
        this.barChartRadioButton.setText((String) ((HashMap<String, Object>)map.get("Graph")).get("Bar_chart"));

        this.raportJSON.setText((String)(map.get("Generate_JSON")));
        this.raportCSV.setText((String)(map.get("Generate_CSV")));
        this.logoutButton.setText((String)(map.get("Logout_button")));

        this.tehnicaLabel.setText((String) ((HashMap<String, Object>)map.get("Add_edit")).get("Technique"));
        this.tipGenLabel.setText((String) ((HashMap<String, Object>)map.get("Add_edit")).get("Genre"));
        this.tipLabel.setText((String) ((HashMap<String, Object>)map.get("Add_edit")).get("Type"));
        this.institutieLabel.setText((String) ((HashMap<String, Object>)map.get("Add_edit")).get("Institution"));
        this.titluLabel.setText((String) ((HashMap<String, Object>)map.get("Add_edit")).get("Title"));
        this.artistLabel.setText((String) ((HashMap<String, Object>)map.get("Add_edit")).get("Artist"));
        this.dataLabel.setText((String) ((HashMap<String, Object>)map.get("Add_edit")).get("Date"));
        this.imagineLabel.setText((String) ((HashMap<String, Object>)map.get("Add_edit")).get("Image"));
        this.addOpera.setText((String) ((HashMap<String, Object>)map.get("Add_edit")).get("Add_edit_header"));
        this.addButton.setText((String) ((HashMap<String, Object>)map.get("Add_edit")).get("Add_edit_button"));

        this.stergereTitluLabel.setText((String) ((HashMap<String, Object>)map.get("Delete")).get("Delete_title"));
        this.stergereLabel.setText((String) ((HashMap<String, Object>)map.get("Delete")).get("Delete_header"));
        this.deleteOperaButton.setText((String) ((HashMap<String, Object>)map.get("Delete")).get("Delete_button"));

        this.tipGenLabel.setText((String)((HashMap<String, Object>)map.get("Add_edit")).get("Genre"));
        this.Resultat.setCellSelectionEnabled(true);
        this.tehnicaLabel.setText((String)((HashMap<String, Object>)map.get("Add_edit")).get("Technique"));


        this.tipComboBox.removeAllItems();
        this.tipComboBox.addItem((String) ((HashMap<String, Object>)map.get("Add_edit")).get("Painting"));
        this.tipComboBox.addItem((String) ((HashMap<String, Object>)map.get("Add_edit")).get("Sculpture"));




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
        return logoutButton;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JComboBox getTipComboBox() {
        return tipComboBox;
    }

    public JRadioButton getInstitutieRadioButton() {
        return institutieRadioButton;
    }

    public JTextField getStergereField() {
        return stergereField;
    }

    public JButton getDeleteOperaButton() {
        return deleteOperaButton;
    }

    public JRadioButton getArtistRadioButton() {
        return artistRadioButton;
    }


    public JRadioButton getTipRadioButton() {
        return tipRadioButton;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getLogoutButton() {
        return logoutButton;
    }

    @Override
    public void update() {
        if(this.mo.getOperatie().toLowerCase().contentEquals("selectie")){
            JFrame popup = new JFrame("Imagine");
            popup.add(new JLabel(new ImageIcon("Images\\"+this.Resultat.getValueAt(this.Resultat.getSelectedRow(), 5).toString())));
            popup.pack();
            popup.setVisible(true);
        }

        else if(this.mo.getOperatie().toLowerCase().contentEquals("filtrare artist")){
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

            HashMap<String,Object> map = (HashMap<String, Object>) mo.getLimba().getInfo().get("Employee");

            this.artistRadioButton.setText((String) ((HashMap<String, Object>)map.get("Filter")).get("Artist"));
            this.institutieRadioButton.setText((String) ((HashMap<String, Object>)map.get("Filter")).get("Institution"));
            this.tipRadioButton.setText((String) ((HashMap<String, Object>)map.get("Filter")).get("Type"));
            this.Filtru.setText((String) ((HashMap<String, Object>)map.get("Filter")).get("Filter_by"));
            this.Filter.setText((String) ((HashMap<String, Object>)map.get("Filter")).get("Filter_button"));

            this.SearchLabel.setText((String) ((HashMap<String, Object>)map.get("Search")).get("Search_title"));
            this.searchButton.setText((String) ((HashMap<String, Object>)map.get("Search")).get("Search_button"));


            this.graficTip.setText((String) ((HashMap<String, Object>)map.get("Graph")).get("Graph_from"));
            this.graficComboBox.removeAllItems();
            this.graficComboBox.addItem((String) ((HashMap<String, Object>)map.get("Graph")).get("Opera_type"));
            this.graficComboBox.addItem((String) ((HashMap<String, Object>)map.get("Graph")).get("Artists"));
            this.graficComboBox.addItem((String) ((HashMap<String, Object>)map.get("Graph")).get("Institutions"));
            this.generateButton.setText((String) ((HashMap<String, Object>)map.get("Graph")).get("Generate_button"));
            this.pieChartRadioButton.setText((String) ((HashMap<String, Object>)map.get("Graph")).get("Pie_chart"));
            this.barChartRadioButton.setText((String) ((HashMap<String, Object>)map.get("Graph")).get("Bar_chart"));

            this.raportJSON.setText((String)(map.get("Generate_JSON")));
            this.raportCSV.setText((String)(map.get("Generate_CSV")));
            this.logoutButton.setText((String)(map.get("Logout_button")));

            this.tehnicaLabel.setText((String) ((HashMap<String, Object>)map.get("Add_edit")).get("Technique"));
            this.tipGenLabel.setText((String) ((HashMap<String, Object>)map.get("Add_edit")).get("Genre"));
            this.tipLabel.setText((String) ((HashMap<String, Object>)map.get("Add_edit")).get("Type"));
            this.institutieLabel.setText((String) ((HashMap<String, Object>)map.get("Add_edit")).get("Institution"));
            this.titluLabel.setText((String) ((HashMap<String, Object>)map.get("Add_edit")).get("Title"));
            this.artistLabel.setText((String) ((HashMap<String, Object>)map.get("Add_edit")).get("Artist"));
            this.dataLabel.setText((String) ((HashMap<String, Object>)map.get("Add_edit")).get("Date"));
            this.imagineLabel.setText((String) ((HashMap<String, Object>)map.get("Add_edit")).get("Image"));
            this.addOpera.setText((String) ((HashMap<String, Object>)map.get("Add_edit")).get("Add_edit_header"));
            this.addButton.setText((String) ((HashMap<String, Object>)map.get("Add_edit")).get("Add_edit_button"));

            this.stergereTitluLabel.setText((String) ((HashMap<String, Object>)map.get("Delete")).get("Delete_title"));
            this.stergereLabel.setText((String) ((HashMap<String, Object>)map.get("Delete")).get("Delete_header"));
            this.deleteOperaButton.setText((String) ((HashMap<String, Object>)map.get("Delete")).get("Delete_button"));

            this.tipComboBox.removeAllItems();
            this.tipComboBox.addItem((String) ((HashMap<String, Object>)map.get("Add_edit")).get("Painting"));
            this.tipComboBox.addItem((String) ((HashMap<String, Object>)map.get("Add_edit")).get("Sculpture"));
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
