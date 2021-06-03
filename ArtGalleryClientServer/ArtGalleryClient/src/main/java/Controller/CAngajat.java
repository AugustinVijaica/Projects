package Controller;

import Application.MainApplication;
import Model.Observer;
import Model.*;
import View.GUIAngajat;
import View.GUIVizitator;
import com.opencsv.CSVWriter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.*;

public class CAngajat implements Observer {

    public String user;
    private GUIAngajat view;
    private ModelOpere mo;

    public CAngajat(GUIAngajat view) throws IOException, SQLException {
        this.view = view;
        this.mo = view.getMo();

        this.view.getInstitutiiComboBox().removeAllItems();
        for (int i = 0; i < getInstitutions().length; i++) {
            this.view.getInstitutiiComboBox().addItem(getInstitutions()[i]);
        }

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
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (SQLException throwables) {
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
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        this.view.getSearchButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchAction();
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
                    createGraphicAction();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        this.view.getTipComboBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeFieldsAction();
            }
        });

        this.view.getAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addEditAction();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });


        this.view.getDeleteOperaButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    deleteOperaAction();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        this.view.getRaportJSON().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                raportJSONAction();
            }
        });

        this.view.getRaportCSV().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                raportCSVAction();
            }
        });

        this.view.getLogoutButton().addActionListener(new ActionListener() {
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
                mop.setLimba(mo.getLimba());
                ModelUtilizatori mut = new ModelUtilizatori();
                mut.setLimba(mo.getLimba());
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

    }

    public void setView(GUIAngajat view) {
        this.view = view;
    }

    private String[] getInstitutions() throws IOException, SQLException {

        Institutii institutii = mo.getPf().citireInstitutii();
        String[] names = new String[institutii.getListaInstitutii().size()];
        for (Institutie i : institutii.getListaInstitutii()
        ) {
            names[institutii.getListaInstitutii().indexOf(i)] = i.getNume();
        }


        return names;

    }


    private String[] getArtists() throws IOException, SQLException {

        Set<String> filtrate = new HashSet<String>();

        Institutii institutii = mo.getPf().citireInstitutii();

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
        view.getTitluField().setText("");
        view.getArtistField().setText("");
        view.getDataField().setText("");
        view.getImagineField().setText("");
        view.getGenTipField().setText("");
        view.getTehnicaField().setText("");
        view.getStergereField().setText("");
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

    private void filterAction(){
        ArrayList<Opera> filtrate = new ArrayList<Opera>();
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

    private void raportCSVAction(){
        ArrayList<Opera> opere = new ArrayList<>();

        for(int i=0;i<view.getResultat().getRowCount();i++){

            String t = view.getResultat().getValueAt(i,4).toString();

            if(t.contentEquals("       ------   ")){
                String titlu = view.getResultat().getValueAt(i,0).toString();
                String artist = view.getResultat().getValueAt(i,1).toString();
                String data = view.getResultat().getValueAt(i,2).toString();
                String tip = view.getResultat().getValueAt(i,3).toString();
                String imagine = view.getResultat().getValueAt(i,5).toString();


                Sculptura sculptura = new Sculptura(titlu,artist,data,tip,imagine);

                opere.add(sculptura);


            }

            else{

                String titlu = view.getResultat().getValueAt(i,0).toString();
                String artist = view.getResultat().getValueAt(i,1).toString();
                String data = view.getResultat().getValueAt(i,2).toString();
                String gen = view.getResultat().getValueAt(i,3).toString();
                String tehnica = view.getResultat().getValueAt(i,4).toString();
                String imagine = view.getResultat().getValueAt(i,5).toString();

                Tablou tablou = new Tablou(titlu,artist,data,gen,tehnica,imagine);

                opere.add(tablou);
            }

        }

        ArrayList<String[]> output = new ArrayList<>();

        HashMap<String,String> map = (HashMap<String, String>) mo.getLimba().getInfo().get("Table_opera");

        String[] col = {map.get("Title"), map.get("Artist"), map.get("Date"), map.get("Genre_Type"),map.get("Technique"), map.get("Image")};

        output.add(col);

        for (Opera o :opere
        ) {
            JSONObject obj = new JSONObject();
            String[] date = new String[6];
            date[0]=o.getTitlu();
            date[1]=o.getArtist();
            date[2]=o.getData();
            if(o instanceof Tablou){
                date[3] = ((Tablou) o).getGen();
                date[4] = ((Tablou) o).getTehnica();
            }
            else if(o instanceof Sculptura){
                date[3] = ((Sculptura) o).getTip();
                date[4] = "-";
            }
            date[5] = o.getImagine();

            output.add(date);
        }

        try {
            CSVWriter writer = new CSVWriter(new FileWriter(new File("Reports\\ReportUser_"+ user +"_"+ UUID.randomUUID() +".csv")));

            writer.writeAll(output);

            writer.close();

            HashMap<String,String> map2 = (HashMap<String, String>) mo.getLimba().getInfo().get("Employee");

            JOptionPane.showMessageDialog(view, map2.get("Report_Success"));

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void raportJSONAction(){
        ArrayList<Opera> opere = new ArrayList<>();

        for(int i=0;i<view.getResultat().getRowCount();i++){

            String t = view.getResultat().getValueAt(i,4).toString();

            if(t.contentEquals("       ------   ")){
                String titlu = view.getResultat().getValueAt(i,0).toString();
                String artist = view.getResultat().getValueAt(i,1).toString();
                String data = view.getResultat().getValueAt(i,2).toString();
                String tip = view.getResultat().getValueAt(i,3).toString();
                String imagine = view.getResultat().getValueAt(i,5).toString();


                Sculptura sculptura = new Sculptura(titlu,artist,data,tip,imagine);

                opere.add(sculptura);


            }

            else{

                String titlu = view.getResultat().getValueAt(i,0).toString();
                String artist = view.getResultat().getValueAt(i,1).toString();
                String data = view.getResultat().getValueAt(i,2).toString();
                String gen = view.getResultat().getValueAt(i,3).toString();
                String tehnica = view.getResultat().getValueAt(i,4).toString();
                String imagine = view.getResultat().getValueAt(i,5).toString();

                Tablou tablou = new Tablou(titlu,artist,data,gen,tehnica,imagine);

                opere.add(tablou);
            }

        }

        JSONArray jsonArray = new JSONArray();

        HashMap<String,String> map = (HashMap<String, String>) mo.getLimba().getInfo().get("Table_opera");

        for (Opera o :opere
        ) {
            JSONObject obj = new JSONObject();
            obj.put(map.get("Title"),o.getTitlu());
            obj.put(map.get("Artist"),o.getArtist());
            obj.put(map.get("Date"),o.getData());
            if(o instanceof Tablou){
                obj.put(map.get("Genre_Type"),((Tablou) o).getGen());
                obj.put(map.get("Technique"),((Tablou) o).getTehnica());
            }
            else if(o instanceof Sculptura){
                obj.put(map.get("Genre_type"),((Sculptura) o).getTip());
            }
            obj.put(map.get("Image"),o.getImagine());

            jsonArray.add(obj);
        }


        try {
            File f = new File("Reports\\ReportUser_"+ user +"_"+ UUID.randomUUID() +".json");
            f.createNewFile();
            FileWriter writer = new FileWriter(f);
            writer.write(jsonArray.toJSONString().replaceAll(",",",\n"));
            writer.close();

            HashMap<String,String> map2 = (HashMap<String, String>) mo.getLimba().getInfo().get("Employee");

            JOptionPane.showMessageDialog(view, map2.get("Report_Success"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void deleteOperaAction() throws IOException, SQLException {
        String titlu = view.getStergereField().getText();

        HashMap<String,Object> map = (HashMap<String, Object>) mo.getLimba().getInfo().get("Employee");

        Opera opera = mo.getPf().cautareOperaTitlu(titlu);

        if(opera == null){
            JOptionPane.showMessageDialog(view,(String)((HashMap<String, Object>)map.get("Delete")).get("Not_found"));
        }

        else{
            mo.getPf().stergereOpera(opera);
        }

        emptyField();
    }

    private void addEditAction() throws IOException, SQLException {
        String institutie = (String) view.getInstitutiiComboBox().getSelectedItem();


        String tip = (String) view.getTipComboBox().getSelectedItem();

        String titlu = view.getTitluField().getText();

        Opera opera = mo.getPf().cautareOperaTitlu(titlu);

        Institutie institution = mo.getPf().cautareInstitutieNume(institutie);

        System.out.println(institution.getNume());


        HashMap<String,Object> map = (HashMap<String, Object>) mo.getLimba().getInfo().get("Employee");



        if(opera==null){
            if(tip.contentEquals((String)((HashMap<String, Object>)map.get("Add_edit")).get("Painting"))){

                String titluNou = view.getTitluField().getText();
                String artistNou = view.getArtistField().getText();
                String dataNoua = view.getDataField().getText();
                String genNou = view.getGenTipField().getText();
                String tehnicaNoua = view.getTehnicaField().getText();
                String imagineNoua = view.getImagineField().getText();


                Tablou tablou = new Tablou(titluNou,artistNou,dataNoua,genNou,tehnicaNoua,imagineNoua);

                mo.getPf().salvareOpera(tablou,institutie);
            }

            else if(tip.contentEquals((String)((HashMap<String, Object>)map.get("Add_edit")).get("Sculpture"))){


                String titluNou = view.getTitluField().getText();
                String artistNou = view.getArtistField().getText();
                String dataNoua = view.getDataField().getText();
                String tipNou = view.getGenTipField().getText();
                String imagineNoua = view.getImagineField().getText();

                Sculptura sculptura = new Sculptura(titluNou,artistNou,dataNoua,tipNou,imagineNoua);

                mo.getPf().salvareOpera(sculptura,institutie);
            }
        }

        else{
            if(institution.getListaOpere().contains(opera)){
                if(tip.contentEquals((String)((HashMap<String, Object>)map.get("Add_edit")).get("Painting"))){

                    String titluNou = view.getTitluField().getText();
                    String artistNou = view.getArtistField().getText();
                    String dataNoua = view.getDataField().getText();
                    String genNou = view.getGenTipField().getText();
                    String tehnicaNoua = view.getTehnicaField().getText();
                    String imagineNoua = view.getImagineField().getText();

                    if(artistNou.isEmpty()) artistNou = opera.getArtist();

                    if(dataNoua.isEmpty()) dataNoua = opera.getData();


                    if(genNou.isEmpty()) genNou = ((Tablou)opera).getGen();

                    if(tehnicaNoua.isEmpty()) tehnicaNoua = ((Tablou)opera).getTehnica();

                    if(imagineNoua.isEmpty()) imagineNoua = opera.getImagine();


                    Tablou tablou = new Tablou(titluNou,artistNou,dataNoua,genNou,tehnicaNoua,imagineNoua);


                    mo.getPf().actualizareOpera(opera,tablou,institutie);
                }

                else if(tip.toLowerCase().contentEquals((String)((HashMap<String, Object>)map.get("Add_edit")).get("Sculpture"))){

                    String titluNou = view.getTitluField().getText();
                    String artistNou = view.getArtistField().getText();
                    String dataNoua = view.getDataField().getText();
                    String tipNou = view.getGenTipField().getText();
                    String imagineNoua = view.getImagineField().getText();


                    if(artistNou.isEmpty()) artistNou = opera.getArtist();

                    if(dataNoua.isEmpty()) dataNoua = opera.getData();


                    if(tipNou.isEmpty()) tipNou = ((Sculptura)opera).getTip();

                    if(imagineNoua.isEmpty()) imagineNoua = opera.getImagine();

                    Sculptura sculptura = new Sculptura(titluNou,artistNou,dataNoua,tipNou,imagineNoua);


                    mo.getPf().actualizareOpera(opera,sculptura,institutie);
                }
            }
            else{
                JOptionPane.showMessageDialog(view,(String)((HashMap<String, Object>)map.get("Add_edit")).get("Wrong_institution"));
            }


        }

        emptyField();
    }

    private void changeFieldsAction(){
        String tip = (String) view.getTipComboBox().getSelectedItem();


        HashMap<String,Object> map = (HashMap<String, Object>) mo.getLimba().getInfo().get("Employee");

        if(tip.contentEquals((String)((HashMap<String, Object>)map.get("Add_edit")).get("Painting"))){
            view.getTehnicaField().setVisible(true);
            view.getTehnicaLabel().setVisible(true);
            view.getGenTipField().setVisible(true);
            view.getTipGenLabel().setVisible(true);
            view.getTipGenLabel().setText((String)((HashMap<String, Object>)map.get("Add_edit")).get("Genre"));
            view.getTehnicaLabel().setText((String)((HashMap<String, Object>)map.get("Add_edit")).get("Technique"));
        }
        else if(tip.contentEquals((String)((HashMap<String, Object>)map.get("Add_edit")).get("Sculpture"))){
            view.getTehnicaField().setVisible(false);
            view.getTehnicaLabel().setVisible(false);
            view.getGenTipField().setVisible(true);
            view.getTipGenLabel().setVisible(true);
            view.getTipGenLabel().setText((String)((HashMap<String, Object>)map.get("Add_edit")).get("Type_sculpture"));
        }
    }

    private void createGraphicAction() throws IOException, SQLException {


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

    private void searchAction() {

        String cautat = view.getSearchField().getText();

        mo.setInfoCautata(cautat);

        mo.setOperatie("cautare titlu");

        emptyField();

    }

    private void selectInstitution() throws IOException, SQLException {
        view.getArtistRadioButton().setSelected(false);
        view.getTipRadioButton().setSelected(false);
        view.getInstitutii().removeAllItems();
        view.getInstitutii().setVisible(true);
        String[] options = getInstitutions();
        for(int i=0;i<options.length;i++){
            view.getInstitutii().addItem(options[i]);
        }
    }

    private void selectArtist() throws IOException, SQLException {
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

    @Override
    public void update() {

    }
}
