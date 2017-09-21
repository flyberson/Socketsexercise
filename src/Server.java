import java.io.*;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


public class Server {
    private static ArrayList<ClientConnection> al;
    private static OutputStream outputStream;
    private static ObjectOutputStream oos;
    private static InputStream is;

    public static InputStream getIs() {
        return is;
    }

    public static void setIs(InputStream is) {
        Server.is = is;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    /*public ObjectOutputStream getOos() {
        return oos;
    }*/

   /* public void setOos(ObjectOutputStream oos) {
        this.oos = oos;
    }*/

    public static ArrayList getArrayList(){

        return al;

    }

    public static void main(String[] args) throws Exception{

        al = new ArrayList();




        try {
            ServerSocket ss = new ServerSocket(8001);
            System.out.println("Server kører...");

            while(true){


                Socket s =ss.accept();
                ClientConnection client= new ClientConnection(s);
                Runnable r = client;
                al.add(client);
                //Runnable r = new ClientConnection(s);


                Thread t = new Thread(r);
                t.start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
