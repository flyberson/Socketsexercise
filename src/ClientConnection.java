
import	java.io.*;
import	java.net.*;
import java.util.ArrayList;
import	java.util.Scanner;

public	class	ClientConnection	implements	Runnable{
    private Socket s;
    ArrayList al;
    OutputStream output;
    ObjectOutputStream oos;


    public ClientConnection(Socket s, OutputStream o,ObjectOutputStream oos) throws SocketException,IOException{

        this.s = s;
        this.output=o;
        this.oos= oos;

    }

    public void writeToClient (String string){
        al = Server.getArrayList();

        for (int i = 0; i <al.size() ; i++) {
            System.out.println("In loop write");

            al.get(i);


            try {
                oos.writeObject(string);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // PrintWriter out = new PrintWriter(output, true);
                //out.println(string);
                //out.println("Print to all");

        }
    }

    @Override
    public void run(){
        try {
            try {
                InputStream input = s.getInputStream();
                //OutputStream output = s.getOutputStream();
                //ObjectOutputStream oos = new ObjectOutputStream(output);
               // PrintWriter out= new PrintWriter(output,true);
                Scanner in = new Scanner(input);

               // out.println("Velkommen");
                writeToClient("Velkommen");

                boolean done = false;

                while (!done && in.hasNextLine()) {
                    String stream = in.nextLine();
                    //out.println("in loop");

                    System.out.println("First");
                    if (stream.equals("luk ned")) {
                        done = true;
                    } else {
                        System.out.println("Second");
                        //out.println("in loop2");
                       // oos.writeObject(stream + stream.length());
                        writeToClient(stream);
                       // oos.writeObject(al.size());
                        //out.println(stream+stream.length());

                    }
                }
                //oos.close();
            }  finally {

                s.close();
                //System.out.println("Forbindelsen lukket");
            }
            }catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();

        }


    }
}
