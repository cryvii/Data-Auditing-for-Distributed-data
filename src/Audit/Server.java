package Audit;

import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;

public class Server 
{
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        Socket sock = null;
        String userId = "", fileName = "", no_of_blocks = "", str = "";
        while(true)
        {
            
                try
        {
            ServerSocket sersock = new ServerSocket(3002);
            sock = sersock.accept();  

            System.out.println("***********************************************************");
            System.out.println("\t CLOUD FRAMEWORK - Full Audit");
            System.out.println("***********************************************************");
                        
            ObjectInputStream ois=new ObjectInputStream(sock.getInputStream());
            userId          = (String)ois.readObject();            
            fileName        = (String)ois.readObject();            
            no_of_blocks    = (String)ois.readObject();
            
            String halfPath = "D:\\Cloud\\hash\\";
            long p=System.nanoTime(); 
            File dir1 = new File(halfPath);
            String[] files_dir1 = dir1.list();
            
            /*File dir2 = new File(halfPath+"2");
            String[] files_dir2 = dir2.list();

            File dir3 = new File(halfPath+"3");
            String[] files_dir3 = dir3.list();*/

            String[] files = new String[Integer.parseInt(no_of_blocks)];int cnt = 0;

            for(int i=0;i<files_dir1.length;i++)
            {
                FileInputStream fis = new FileInputStream(halfPath+files_dir1[i]);
                byte[] data = new byte[fis.available()];
                fis.read(data);
                files[cnt++] = new String(data);
            }

            System.out.println("\t Retrieved content of the Files");
            System.out.println("***********************************************************");    
            for(int i=0;i<files.length;i++)
                System.out.println(files[i]);
            System.out.println("***********************************************************");
           
            ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
            out.writeObject(files_dir1);
            out.writeObject(files);
            
        
            //out.writeObject(finalHash);
            //out.writeObject(Long.toHexString(ex1));
            //System.out.println("\t INTEGRITY VERFIFIED..!");
            
            System.out.println("***********************************************************");  
            System.out.println("****************************Error correctness****************************"); 
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