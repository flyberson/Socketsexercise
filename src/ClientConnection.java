
import	java.io.*;
import	java.net.*;
import java.util.ArrayList;
import	java.util.Scanner;

public	class	ClientConnection	implements	Runnable{
    private Socket s;

    public ClientConnection(Socket s) throws SocketException,IOException{
        this.s = s;
    }

    @Override
    public void run(){
        try {
            try {
                InputStream input = s.getInputStream();
                OutputStream output = s.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(output);
                PrintWriter out= new PrintWriter(output,true);
                Scanner in = new Scanner(input);

                out.println("Velkommen");

                boolean done = false;

                while (!done && in.hasNextLine()) {
                    String stream = in.nextLine();
                    //out.println("in loop");


                    if (stream.equals("luk ned")) {
                        done = true;
                    } else {

                         ArrayList al = Server.getArrayList();
                        for (int i = 0; i <10 ; i++) {
                            al.get(i);

                        }

                            oos.writeObject(stream + stream.length());

                        //out.println("in loop2");
                        //oos.writeObject(stream + stream.length());
                       // out.println(stream+stream.length());

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
