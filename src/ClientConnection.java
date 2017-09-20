
import	java.io.*;
import	java.net.*;
import java.util.ArrayList;
import	java.util.Scanner;

public	class	ClientConnection	implements	Runnable{
    private Socket s;
    ArrayList <ClientConnection> al;
    OutputStream output;
    ObjectOutputStream oos;
    InputStream is;


    public ClientConnection(Socket s, OutputStream o,ObjectOutputStream oos, InputStream is) throws SocketException,IOException{

        this.s = s;
        this.output=o;
        this.oos= oos;
        this.is=is;

    }

    public void writeToClient (String string){


            try {
                oos.writeObject(string.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }



    }
    public ClientConnection getClient(String string){

        al = Server.getArrayList();
        ClientConnection c = null;

        for (int i = 0; i <al.size() ; i++) {
            System.out.println("In loop write");

            c = al.get(i);
            c.writeToClient("Writing to client"+string);


        }
        return c;
    }

    @Override
    public void run(){
        try {
            try {
                is = Server.getIs();
                //OutputStream output = s.getOutputStream();
                //ObjectOutputStream oos = new ObjectOutputStream(output);
               // PrintWriter out= new PrintWriter(output,true);
                Scanner in = new Scanner(is);

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

                        getClient(stream);

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
