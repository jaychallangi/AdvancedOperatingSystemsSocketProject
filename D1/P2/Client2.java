package P2;
import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.Base64;


public class Client2 {

    private Socket socket = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;
    private File f2 = null;
    private File f3 = null;
    private FileOutputStream fw = null;
    private FileInputStream fr = null;

    public Client2(String address, int port)//address and port of server
    {

        //Putting contents of file into byte array for latter use
        f2 = new File("P2/F2");
        byte[] array = new byte[(int) f2.length()];
        try {
            fr = new FileInputStream(f2);
            fr.read(array);
        } catch (IOException a) {
            System.out.println("ERROR reading from F2");
            a.printStackTrace();
        }
        //Connecting to server
        try {
            socket = new Socket(address, port);
            System.out.println("Client2 connected");
            //sending output
            out = new DataOutputStream(socket.getOutputStream());
            for (int i = 1; i <= 3; i++) {
                byte[] temp = Arrays.copyOfRange(array, (i - 1) * 100, i * 100);
                /*for(int j=0; j< temp.length ; j++) {
                    System.out.print(temp[j] +" "); }
                System.out.println("");*/
                out.write(temp);
                out.flush();
            }
        } catch (IOException a) {
            System.out.println("ERROR sending F2");
            a.printStackTrace();
        }

        //Creating f3 file
        try
        {
            f3 = new File("P2/F3");
            if(f3.createNewFile())
            {
                System.out.println("F3 created");
            }
            else
            {
                if(f3.delete())
                {
                    f3 = new File("P2/F3");
                    System.out.println("Existing F3 deleted, new one created");
                }
                else
                {
                    System.out.println("Unable to delete existing F3");
                }
            }
        }
        catch (IOException a)
        {
            System.out.println("ERROR with F3");
            a.printStackTrace();
        }

        try{
            in = new DataInputStream(socket.getInputStream());
            fw = new FileOutputStream(f3,true);
            array = new byte [200];
            for(int i=1; i<=3; i++)
            {
                //System.out.println(i);
                if(in.read(array)<=0)
                {
                    System.out.println("Less than 600 bytes recieved from client2");
                    break;
                }
                else if(Base64.getEncoder().encodeToString(array).equals("CLOSE"))
                {
                    System.out.println("Recieved end of input from client2");
                    break;
                }
                else
                {
                    /*for(int j=0; j< array.length ; j++) {
                        System.out.print(array[j] +" "); }
                    System.out.println("");*/
                    fw.write(array); //Contents being appended at the end
                }
            }

        }
        catch (IOException a)
        {
            System.out.println("ERROR with writing contents from C3 to F3");
            a.printStackTrace();
        }
    }
    public static void main(String args[])
    {
        Client2 client=new Client2("127.0.0.1",5002);
    }
}
