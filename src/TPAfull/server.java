/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TPAfull;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author tce
 */
public class server {
    public static void main(String args[]) throws IOException
    {
       
        Socket sock = null;
        String userId = "", fileName = "", no_of_blocks = "", str = "";
        String hashedfname[];
        while(true)
        {
        try
        {
            ServerSocket sersock = new ServerSocket(3005);
            sock = sersock.accept();  

            System.out.println("***********************************************************");
            System.out.println("\t CLOUD FRAMEWORK - Full Audit");
            System.out.println("***********************************************************");
                        
            ObjectInputStream ois=new ObjectInputStream(sock.getInputStream());
            userId          = (String)ois.readObject();            
            fileName        = (String)ois.readObject();            
            no_of_blocks    = (String)ois.readObject();
            hashedfname     =  (String[])ois.readObject();
            String halfPath = "D:\\Cloud\\hash\\";
            
            ///File dir1 = new File(halfPath);
            //String[] files_dir1 = dir1.list();
            
            /*File dir2 = new File(halfPath+"2");
            String[] files_dir2 = dir2.list();

            File dir3 = new File(halfPath+"3");
            String[] files_dir3 = dir3.list();*/

           String[] files = new String[Integer.parseInt(no_of_blocks)];int cnt = 0;

            for(int i=0;i<hashedfname.length;i++)
            {
                FileInputStream fis = new FileInputStream(halfPath+hashedfname[i]);
                byte[] data = new byte[fis.available()];
                fis.read(data);
                files[cnt++] = new String(data);
            }
            //Socket send=new Socket("127.0.0.1",3005);
            //ObjectOutputStream out1 = new ObjectOutputStream(send.getOutputStream());
           // out1.writeObject(userId);
           // out1.writeObject(fileName);
           // out1.writeObject(no_of_blocks);
          //out1.writeObject(hashedfname);
            System.out.println("\t Retrieved content of the Files to be sent to TPA");
            System.out.println("***********************************************************");    
            for(int i=0;i<files.length;i++)
                System.out.println(files[i]);
            System.out.println("***********************************************************");
            ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
            out.writeObject(files);
            
            System.out.println("***********************************************************");
            String error=(String)ois.readObject();
            if(error.equals("yes"))
            {
            String orgblockname=(String)ois.readObject();
            String orghashvalue=(String)ois.readObject();
            //System.out.println(orgblockname+"yyyyyyyyyyyyyyyyyyyyyyy"+orghashvalue);
            int y=1,c=0,cnt1=0;
            String half[] =new String[3];
            half[0]="D:\\Replicate1\\Cloud\\hash\\";
            half[1] = "D:\\Replicate2\\Cloud\\hash\\";
            half[2] = "D:\\Replicate3\\Cloud\\hash\\";
                try ( //String files1[]=new String[(blknams.length)];
                        FileWriter fw = new FileWriter(halfPath+orgblockname,false)) {
                    fw.write(orghashvalue);
                }
             for(int j=0;j<half.length;j++)
                        {
                            try (FileWriter fw1 = new FileWriter(half[j]+orgblockname,false)) {
                                fw1.write(orghashvalue);
                                fw1.close();
                            }
                        }
            System.out.println("Error correctness is completed.......................................");
            sersock.close();
                out.writeObject("Corrected");
            }
            else
            {
                sersock.close();
                
            }
        }

        catch(Exception ex)
        {
            ex.printStackTrace();
        }        
        }
    }
}
