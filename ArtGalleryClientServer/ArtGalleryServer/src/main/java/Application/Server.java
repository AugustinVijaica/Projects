//TCPServer.java
package Application;
import Model.ModelOpere;
import Model.ModelUtilizatori;

import java.io.*;
import java.net.*;
import java.sql.SQLException;

class Server {

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {


        ServerSocket ss = new ServerSocket(5000);
        System.out.println("ServerSocket awaiting connections...");
        Socket socket = ss.accept();
        System.out.println("Connection from " + socket + "!");

        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

        ModelOpere mo = new ModelOpere();
        ModelUtilizatori mu = new ModelUtilizatori();

        while (true) {
            String msg = (String) objectInputStream.readObject();
            System.out.println(msg);

            if (msg.equals("log in")) {


                System.out.println("Sending messages to the ServerSocket");
                objectOutputStream.writeObject(mo);


                System.out.println("Sending messages to the ServerSocket");
                objectOutputStream.writeObject(mu);


            }

            else if(msg.equals("angajat")){

                System.out.println("Sending messages to the ServerSocket");
                objectOutputStream.writeObject(mo);
            }

            else if(msg.equals("admin")){

                System.out.println("Sending messages to the ServerSocket");
                objectOutputStream.writeObject(mu);
            }

        }
    }
}