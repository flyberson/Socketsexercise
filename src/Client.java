import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
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

                PrintWriter out = new PrintWriter(output, true);

                //scanner starts, modtag velkomst
                String welcome = scan.nextLine();
                System.out.println(welcome);

                //modtag velkomst
                System.out.println(scan.nextLine());

                System.out.println(scan.nextLine());

                System.out.println(scan.nextLine());

                s.close();
                System.out.println("Forbindelsen lukket.");
            }
        }   catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
