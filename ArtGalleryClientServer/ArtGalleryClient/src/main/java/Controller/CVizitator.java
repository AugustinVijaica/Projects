package Controller;

import Application.MainApplication;
import Model.*;
import View.GUIAdmin;
import View.GUIAngajat;
import View.GUIVizitator;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class CVizitator implements  Observer{

    private GUIVizitator view;
    private ModelOpere mo;
    private ModelUtilizatori mu;



    public CVizitator(GUIVizitator view) {


        this.view = view;
       this.mo = view.getMo();
       this.mu = view.getMu();

        this.view.getFilter().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterAction();
            }
        });

        this.view.getArtistRadioButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    selectArtist();
                } catch (SQLException | IOException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        this.view.getTipRadioButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectType();
            }
        });

        this.view.getInstitutieRadioButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    selectInstitution();
                } catch (SQLException | IOException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        this.view.getSearchButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchAction();
                mo.setOperatie("cautare titlu");
            }
        });

        this.view.getLoginButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loginAction();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

        });
        this.view.getResultat().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JFrame popup = new JFrame("Imagine");
                popup.add(new JLabel(new ImageIcon("Images\\"+view.getResultat().getValueAt(view.getResultat().getSelectedRow(), 5).toString())));
                popup.pack();
                popup.setVisible(true);
            }
        });

        this.view.getPieChartRadioButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.getBarChartRadioButton().setSelected(false);
            }
        });

        this.view.getBarChartRadioButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.getPieChartRadioButton().setSelected(false);
            }
        });

        this.view.getGenerateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    generateGraphicAction();
                } catch (SQLException | IOException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        this.view.getRomaniaButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                romanian();
            }
        });

        this.view.getFrenchButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                french();
            }
        });

        this.view.getEnglishButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                english();
            }
        });

        this.view.getGermanButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                german();
            }
        });

    }

    private void generateGraphicAction() throws SQLException, IOException {


        HashMap<String,Object> map = (HashMap<String, Object>) mo.getLimba().getInfo().get("Guest");

        String graficPentru = (String) view.getGraficComboBox().getSelectedItem();
        if (graficPentru.toLowerCase().contentEquals(((String)((HashMap<String, Object>)map.get("Graph")).get("Opera_type")).toLowerCase())){
            int nrTablouri = 0;
            int nrSculpturi = 0;
            Institutii institutii = mo.getPf().citireInstitutii();
            Set<Opera> opere = new HashSet<>();
            for (Institutie i : institutii.getListaInstitutii()
            ) {
                opere.addAll(i.getListaOpere());
            }
            for (Opera o : opere
            ) {
                if (o instanceof Tablou) {
                    nrTablouri++;
                } else if (o instanceof Sculptura) {
                    nrSculpturi++;
                }
            }

            if (view.getPieChartRadioButton().isSelected()) {
                createPieChart("Operas", new String[]{(String) ((HashMap<String, Object>)map.get("Graph")).get("Paintings"), (String) ((HashMap<String, Object>)map.get("Graph")).get("Sculptures")}, new Double[]{(double) nrTablouri, (double) nrSculpturi});
            }
            if (view.getBarChartRadioButton().isSelected()) {
                createBarChart("Operas", new String[]{(String) ((HashMap<String, Object>)map.get("Graph")).get("Paintings"), (String) ((HashMap<String, Object>)map.get("Graph")).get("Sculptures")}, new Double[]{(double) nrTablouri, (double) nrSculpturi});
            }
        } else if (graficPentru.toLowerCase().contentEquals(((String) ((HashMap<String, Object>)map.get("Graph")).get("Artists")).toLowerCase())) {
            Institutii institutii = mo.getPf().citireInstitutii();
            Set<Opera> opere = new HashSet<>();
            for (Institutie i : institutii.getListaInstitutii()
            ) {
                opere.addAll(i.getListaOpere());
            }
            Set<String> artisti = new HashSet<>();
            for (Opera o : opere
            ) {
                artisti.add(o.getArtist());
            }
            Double[] values = new Double[artisti.size()];

            for (int i = 0; i < artisti.size(); i++) {
                values[i] = 0.0;
            }

            String[] artists = new String[artisti.size()];
            artisti.toArray(artists);

            for (Opera o : opere
            ) {
                for (int i = 0; i < artisti.size(); i++) {
                    if (artists[i].toLowerCase().contentEquals(o.getArtist().toLowerCase())) {
                        values[i]++;
                    }
                }
            }

            if (view.getPieChartRadioButton().isSelected()) {
                createPieChart((String) ((HashMap<String, Object>)map.get("Graph")).get("Artists"), artists, values);
            }
            if (view.getBarChartRadioButton().isSelected()) {
                createBarChart((String) ((HashMap<String, Object>)map.get("Graph")).get("Artists"), artists, values);
            }
        } else if (graficPentru.toLowerCase().contentEquals(((String)((HashMap<String, Object>)map.get("Graph")).get("Institutions")).toLowerCase())) {

            Institutii institutii = mo.getPf().citireInstitutii();
            String[] institutions = new String[institutii.getListaInstitutii().size()];
            Double[] values = new Double[institutii.getListaInstitutii().size()];
            for (int i = 0; i < institutii.getListaInstitutii().size(); i++) {
                institutions[i] = institutii.getListaInstitutii().get(i).getNume();
                values[i] = (double) institutii.getListaInstitutii().get(i).getListaOpere().size();
            }

            if (view.getPieChartRadioButton().isSelected()) {
                createPieChart((String) ((HashMap<String, Object>)map.get("Graph")).get("Institutions"), institutions, values);
            }
            if (view.getBarChartRadioButton().isSelected()) {
                createBarChart((String) ((HashMap<String, Object>)map.get("Graph")).get("Institutions"), institutions, values);
            }

        }
    }

    private void loginAction() throws IOException, ClassNotFoundException, SQLException {


        String username = view.getUsername().getText();
        String password = String.valueOf(view.getPassword().getPassword());

        HashMap<String,Object> map = (HashMap<String, Object>) mo.getLimba().getInfo().get("Guest");

        if (username.contentEquals("") || password.contentEquals("")) {
            JOptionPane.showMessageDialog(view, (String) ((HashMap<String, Object>)map.get("Login")).get("Empty_field"));
            emptyField();
        } else {

            Utilizator utilizator = mu.getPf().cautareUtilizator(username, password);

            if (utilizator == null) {
                JOptionPane.showMessageDialog(null, (String) ((HashMap<String, Object>)map.get("Login")).get("Not_found"));
                emptyField();
            } else {


                if (utilizator.getRol().toLowerCase().contentEquals("angajat")) {

                    HashMap<String,Object> map2 = (HashMap<String, Object>) mo.getLimba().getInfo().get("Employee");
                    ObjectOutputStream objectOutputStream = MainApplication.os;
                    objectOutputStream.writeObject("angajat");
                    GUIAngajat ga = new GUIAngajat((String)map2.get("Title"));
                    CAngajat angajat = ga.getCa();
                    angajat.user=utilizator.getCont();
                    view.dispose();
                }
                else if(utilizator.getRol().toLowerCase().contentEquals("admin")){
                    HashMap<String,Object> map2 = (HashMap<String, Object>) mo.getLimba().getInfo().get("Admin");
                    ObjectOutputStream objectOutputStream = MainApplication.os;
                    objectOutputStream.writeObject("admin");
                    GUIAdmin gad = new GUIAdmin((String)map2.get("Title"));
                    CAdmin admin = gad.getCad();
                    this.view.dispose();
                }
            }
        }
    }
    private void searchAction() {

        String cautat = view.getSearchField().getText();

        mo.setInfoCautata(cautat);

        emptyField();

    }

    private void selectInstitution() throws SQLException, IOException {
        view.getArtistRadioButton().setSelected(false);
        view.getTipRadioButton().setSelected(false);
        view.getInstitutii().removeAllItems();
        view.getInstitutii().setVisible(true);
        String[] options = getInstitutions();
        for(int i=0;i<options.length;i++){
            view.getInstitutii().addItem(options[i]);
        }
    }

    private void selectArtist() throws SQLException, IOException {
        view.getInstitutieRadioButton().setSelected(false);
        view.getTipRadioButton().setSelected(false);
        view.getInstitutii().removeAllItems();
        view.getInstitutii().setVisible(true);
        String[] options = getArtists();
        for(int i=0;i<options.length;i++){
            view.getInstitutii().addItem(options[i]);
        }
    }

    private void selectType(){

        HashMap<String,Object> map = (HashMap<String, Object>) mo.getLimba().getInfo().get("Employee");

        view.getInstitutieRadioButton().setSelected(false);
        view.getArtistRadioButton().setSelected(false);
        view.getInstitutii().removeAllItems();
        view.getInstitutii().setVisible(true);
        String[] options = new String[]{(String)((HashMap<String, Object>)map.get("Add_edit")).get("Painting"), (String)((HashMap<String, Object>)map.get("Add_edit")).get("Sculpture")};
        for(int i=0;i<options.length;i++){
            view.getInstitutii().addItem(options[i]);
        }
    }

    private String[] getInstitutions() throws SQLException, IOException {

        System.out.println(this.mo.getPf());
        Institutii institutii = this.mo.getPf().citireInstitutii();
        String[] names = new String[institutii.getListaInstitutii().size()];
        for (Institutie i : institutii.getListaInstitutii()
        ) {
            names[institutii.getListaInstitutii().indexOf(i)] = i.getNume();
        }


        return names;

    }

    private String[] getArtists() throws SQLException, IOException {

        Set<String> filtrate = new HashSet<String>();

        Institutii institutii = this.mo.getPf().citireInstitutii();

        for (Institutie i : institutii.getListaInstitutii()
        ) {
            for (Opera o : i.getListaOpere()) {
                filtrate.add(o.getArtist());
            }
        }
        ArrayList<String> opereFiltrate = new ArrayList<String>(filtrate);

        String[] artisti = new String[opereFiltrate.size()];

        for (int i = 0; i < opereFiltrate.size(); i++) {
            artisti[i] = opereFiltrate.get(i);
        }
        return artisti;

    }

    private void emptyField() {
        view.getSearchField().setText("");
        view.getUsername().setText("");
        view.getPassword().setText("");
    }



    private void filterAction(){
        String filtru = (String) view.getInstitutii().getSelectedItem();
        mo.setInfoCautata(filtru);

        if (view.getArtistRadioButton().isSelected()) {
            mo.setOperatie("filtrare artist");
        } else if (view.getTipRadioButton().isSelected()) {
            mo.setOperatie("filtrare tip");
        } else if (view.getInstitutieRadioButton().isSelected()) {
            mo.setOperatie("filtrare institutie");
        }

    }


    private void createPieChart(String title,String[] keys, Double[] values){
        DefaultPieDataset dataset = new DefaultPieDataset( );
        for(int i=0;i< keys.length; i++){
            dataset.setValue(keys[i],values[i]);
        }


        JFreeChart chart = ChartFactory.createPieChart(
                title,
                dataset,
                true,
                true,
                false);

        JFrame f = new JFrame();

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        f.add(chartPanel);

        f.pack();
        f.setTitle("Pie chart");
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    private void createBarChart(String title,String[] keys, Double[] values){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );

        for(int i=0;i< keys.length; i++){
            dataset.addValue(values[i],keys[i],"Opera");
        }


        JFreeChart chart = ChartFactory.createBarChart(
                title,
                "Category",
                "Score",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);

        JFrame f = new JFrame();

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        f.add(chartPanel);

        f.pack();
        f.setTitle("Pie chart");
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    private void romanian(){

        this.mo.setLimba(new Limba("Romanian"));
        this.mu.setLimba(new Limba("Romanian"));
        this.mo.setOperatie("limba");
        this.mu.setOperatie("limba");
    }

    private void english(){

        this.mo.setLimba(new Limba("English"));
        this.mu.setLimba(new Limba("English"));
        this.mo.setOperatie("limba");
        this.mu.setOperatie("limba");
    }

    private void french(){

        this.mo.setLimba(new Limba("French"));
        this.mu.setLimba(new Limba("French"));
        this.mo.setOperatie("limba");
        this.mu.setOperatie("limba");
    }

    private void german(){

        this.mo.setLimba(new Limba("German"));
        this.mu.setLimba(new Limba("German"));
        this.mo.setOperatie("limba");
        this.mu.setOperatie("limba");
    }

    @Override
    public void update() {

    }

}
