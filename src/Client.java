import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * 13-09-2017
 *
 * @author Mads Heilberg
 *         mads.heilberg@gmail.com
 *         DAT - EASJ Næstved
 */
public class Client {

    public static void main(String[] args){
        try{
            Socket s = new Socket("127.0.0.1", 8001);

            while(true){
                //socket streams
                InputStream input = s.getInputStream();
                OutputStream output = s.getOutputStream();

                //input scanner
                Scanner scan = new Scanner(input);
                Scanner scan2 = new Scanner(System.in);
                PrintWriter out = new PrintWriter(output, true);

                String welcome = scan.nextLine();
                System.out.println(welcome);

                out.print(scan2.nextLine());

                s.close();
                System.out.println("[Connection Closed]");
            }
        }   catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
