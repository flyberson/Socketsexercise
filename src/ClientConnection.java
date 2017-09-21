
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

    private String name = "Guest";
    static ArrayList<String> stringArray = new ArrayList<>();

    public void requestName(String input){
        name = input;
        System.out.println("[NAME REQUEST SAVED]");
    }

    public void requestPut(String name, String input){
        String save = name + ": " + input;
        stringArray.add(save);
        System.out.println("[PUT REQUEST SAVED TO ARRAY]");
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



    public ClientConnection(Socket s, OutputStream o,ObjectOutputStream oos, InputStream is) throws SocketException,IOException{
        this.s = s;
        this.output = o;
        this.oos = oos;
        this.is = is;
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
                writeToClient("Welcome " + name);

                boolean done = false;

                while (!done && in.hasNextLine()) {
                    String stream = in.nextLine();

                    if (stream.equalsIgnoreCase("Exit")) {
                        done = true;
                    } else if(stream.substring(0, 5).equals("NAME:")) {
                        requestName(stream.substring(5));
                        System.out.println("Name is now: " + name);
                    } else if(stream.substring(0, 4).equals("PUT:")){
                        requestPut(name, stream.substring(4));
                        writeToClient("Saved string: " + stringArray.get(0));
                    } else if(stream.substring(0, 5).equals("COUNT")){
                        writeToClient("COUNT " + Integer.toString(requestCount()));
                    } else if(stream.substring(0, 4).equals("GET:")){
                        writeToClient(requestGetX(Integer.parseInt(stream.substring(4, 5))));
                    }

                    // getClient(stream);
                }
                //oos.close();
            }  finally {

                s.close();
                System.out.println("Connection closed");
            }
            }catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();

        }


    }
}