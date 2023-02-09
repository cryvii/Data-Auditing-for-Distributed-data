/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retrival;

/**
 *
 * @author tce
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
 * @author tce
 */
import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;

public class Server 
{
    public static void main(String[] args) throws IOException
    {
        while(true)
        {
        Socket sock = null;
        String userId = "", fileName = "", no_of_blocks = "", str = "";
        try
        {
            ServerSocket sersock = new ServerSocket(3001);
            sock = sersock.accept();  

            
            System.out.println("\t\tRETRIEVAL SERVER");
            System.out.println("/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/");
                        
            ObjectInputStream ois=new ObjectInputStream(sock.getInputStream());
            userId  = (String)ois.readObject();            
            fileName = (String)ois.readObject();            
            no_of_blocks = (String)ois.readObject();
            
            String Path = "D:\\Cloud\\"+userId;
            
            File dir1 = new File(Path);
            String[] files_dir1 = dir1.list();
            
              String[] files = new String[Integer.parseInt(no_of_blocks)];int cnt = 0;

            for(int i=0;i<files_dir1.length;i++)
            {
                files[cnt++] = files_dir1[i];
                System.out.println(files_dir1[i]);
            }

            /*for(int i=0;i<files.length;i++)
                System.out.println(files[i]);*/

            ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
            out.writeObject(files);
            str  = (String)ois.readObject(); 
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        System.out.println("/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/");
        System.out.println("\tBLocks are delivered");
        System.out.println("/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/");
        System.out.println("\t "+str);
        System.out.println("/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/");
        }
    }
}