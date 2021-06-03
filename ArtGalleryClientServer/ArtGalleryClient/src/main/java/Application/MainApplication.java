package Application;

import Controller.CVizitator;
import View.GUIVizitator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MainApplication {

    public static ObjectOutputStream os;
    public static ObjectInputStream is;

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Socket socket = new Socket("localhost", 5000);
        System.out.println("Connected!");

        os = new ObjectOutputStream(socket.getOutputStream());
        is = new ObjectInputStream(socket.getInputStream());

        os.writeObject("log in");

        GUIVizitator gv = new GUIVizitator("Home");
        CVizitator cv = gv.getCv();



    }
}
