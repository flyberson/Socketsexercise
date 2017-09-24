import	java.io.*;
import	java.net.*;
import java.util.ArrayList;
import	java.util.Scanner;

public	class	ClientConnection	implements	Runnable{
    private Socket s;
    ArrayList <ClientConnection> al;
    OutputStream output;
    InputStream input;
    private static PrintWriter pwriter;
    Writer writer;


    private String name = "Guest";
    static ArrayList<String> stringArray = new ArrayList<>();

    public void requestName(String input){
        name = input;
        System.out.println("[NAME REQUEST SAVED]");


    }

    public void requestPut(String input){
        String save = name + ": " + input;
        stringArray.add(save);
        System.out.println("[PUT REQUEST SAVED TO ARRAY]");

            pwriter.println("hello");

    }

    public int requestCount(){
        System.out.println("[ARRAY SIZE RETURNED]");
        return stringArray.size();
    }

    public String requestGetX(int x){
        String stringX = stringArray.get(x);
        System.out.println("[STRING " + x + " RETURNED]");
        return stringX;

    }


    public ClientConnection(Socket s) throws SocketException,IOException{

        this.s = s;

        output =s.getOutputStream();
        //this.oos= oos;
        input=s.getInputStream();
        //writer = new PrintWriter(output, true);
        writer = new OutputStreamWriter(s.getOutputStream(), "UTF-8");
        pwriter = new PrintWriter(output,true);


    }

    public void writeToClient (String string) throws IOException {
            //try {

               // writer.print(string);
        writer.write(string+"\n");
        writer.flush();
      //      } catch (IOException e) {
        ////  }
    }

    public ClientConnection getClient(String string) throws IOException {

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
                //is = Server.getIs();
                //OutputStream output = s.getOutputStream();
                //ObjectOutputStream oos = new ObjectOutputStream(output);
                // PrintWriter out= new PrintWriter(output,true);
                Scanner in = new Scanner(input);

                // out.println("Velkommen");
                writeToClient("Welcome " + name);

                boolean done = false;

                while (!done && in.hasNextLine()) {
                    String stream = in.nextLine();

                    //if (stream.length() >= 4) {

                        try {

                            if (stream.equalsIgnoreCase("Exit")) {
                                done = true;
                            } else if (stream.substring(0, 5).equals("NAME:")) {
                                requestName(stream.substring(5));
                                System.out.println("Name is now: " + name);
                                writeToClient("Name is now: "+ name);
                            } else if (stream.substring(0, 4).equals("PUT:")) {
                                requestPut(stream.substring(4));
                                writeToClient("Saved string: " + stringArray.get(0));
                            } else if (stream.substring(0, 5).equals("COUNT")) {
                                writeToClient("COUNT " + Integer.toString(requestCount()));
                            } else if (stream.substring(0, 4).equals("GET:")) {
                                writeToClient(requestGetX(Integer.parseInt(stream.substring(4, 5))));
                            } else {
                                writeToClient("[ERROR]");
                            }
                            // getClient(stream);
                        } catch(StringIndexOutOfBoundsException e){
                            writeToClient("[ERROR]");
                            e.printStackTrace();
                        }
                }
                //oos.close();
            }  finally {
                //s.close();
                System.out.println("[CONNECTION CLOSED]");
            }
            }catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();

        }


    }
}