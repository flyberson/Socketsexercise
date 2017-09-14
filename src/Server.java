import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;



public class Server {
    private static ArrayList<ClientConnection> al;


    public static ArrayList getArrayList(){

        return al;

    }

    public static void main(String[] args){
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
                /*
                System.out.println("Klient forbundet");
                InputStream input = s.getInputStream();
                OutputStream output = s.getOutputStream();
                ObjectOutputStream oos= new ObjectOutputStream(output);


                Scanner in = new Scanner(input);

                PrintWriter out = new PrintWriter(output,true);

                out.println("Velkommen");

                boolean done= false;
                while(!done&& in.hasNextLine()){
                    String stream = in.nextLine();
                    //out.println("in loop");


                    if(stream.equals("luk ned")){
                        done=true;
                    }
                    else{
                        //out.println("in loop2");
                        oos.writeObject(stream+stream.length());
                        //out.println(stream+stream.length());

                    }
                }
                oos.close();
                s.close();
                System.out.println("Forbindelsen lukket");*/
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
