package P3;
import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.Base64;
public class Server
{
    private ServerSocket server = null;
    private Socket socket1 = null;
    private Socket socket2 = null;
    private DataInputStream in1 = null;
    private DataInputStream in2 = null;
    private DataOutputStream out1 = null;
    private DataOutputStream out2 = null;
    private File f3 = null;
    private FileOutputStream fw = null;
    private FileInputStream fr = null;

    private void ConnectAndReadFromClient(Socket c, DataInputStream in)
    {
        //Connection to Client
        try
        {
            fw = new FileOutputStream(f3,true); //Opened in append mode
            byte[] array = new byte [100];
            for (int i=1; i<=3;i++) //Server recieves 3 100 byte messages from client (Change Later)
            {
                if(in.read(array)<=0)
                {
                    System.out.println("Less than 300 bytes recieved from client1");
                    break;
                }
                else if(Base64.getEncoder().encodeToString(array).equals("CLOSE"))
                {
                    System.out.println("Recieved end of input from client1");
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
            System.out.println("ERROR with writing contents from C1 to F3");
            a.printStackTrace();
        }

    }

    public Server(int port)
    {
        try{
            server = new ServerSocket(port);
            System.out.println("Server started");
            //Creating File
            try
            {
                f3 = new File("P3/F3");
                if(f3.createNewFile())
                {
                    System.out.println("F3 created");
                }
                else
                {
                    if(f3.delete())
                    {
                        f3 = new File("P3/F3");
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
            //Connection to Client1
            try{
                socket1=server.accept();
                in1 = new DataInputStream(socket1.getInputStream());
            }
            catch (IOException a)
            {
                System.out.println("ERROR with connecting to C1");
                a.printStackTrace();
            }
            ConnectAndReadFromClient(socket1, in1);
            //Connect to Client2
            try{
                socket2=server.accept();
                in2 = new DataInputStream(socket2.getInputStream());
            }
            catch (IOException a)
            {
                System.out.println("ERROR with connecting to C2");
                a.printStackTrace();
            }
            ConnectAndReadFromClient(socket2, in2);

        }
        catch (IOException a)
        {
            System.out.println("ERROR setting up socket");
            a.printStackTrace();
        }

        //Writing f3 to clients
        byte[]array = new byte[(int) f3.length()];
        try {
            fr = new FileInputStream(f3);
            fr.read(array);
            out1 = new DataOutputStream(socket1.getOutputStream());
            out2 = new DataOutputStream(socket2.getOutputStream());
            for(int i=1;i<=3;i++)
            {
                //System.out.println(i);
                byte[] temp = Arrays.copyOfRange(array, (i - 1) * 200, i * 200);
                /*for(int j=0; j< temp.length ; j++) {
                    System.out.print(temp[j] +" "); }
                System.out.println("");*/
                out1.write(temp);
                out2.write(temp);
                out1.flush();
                out2.flush();

                //close The server
            }
            in1.close();
            in2.close();
            out1.close();
            out2.close();
            socket1.close();
            socket2.close();
            server.close();
        }
        catch (IOException a)
        {
            System.out.println("ERROR reading from F2");
            a.printStackTrace();
        }

    }
    public static void main(String args[]) {

        Server server = new Server(5002);
    }
}
