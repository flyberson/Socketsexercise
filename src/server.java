import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class server {
    public static void main(String[] args){

        try {
            ServerSocket ss = new ServerSocket(8001);
            System.out.println("Server kører...");

            while(true){
                Socket s =ss.accept();
                System.out.println("Klient forbundet");
                InputStream input = s.getInputStream();
                OutputStream output = s.getOutputStream();


                Scanner in = new Scanner(input);


                out.println("Velkommen");

                boolean done= false;
                while(!done&&in.hasNextLine()){
                       String stream = in.nextLine();
        if(stream.equals("luk ned")){
            done=true;
        }
        else{
            out.println(stream);
        }

                }
                s.close();
                System.out.println("Forbindelsen lukket");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
