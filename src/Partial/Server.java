/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Partial;

import java.io.File;
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
public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        Socket sock = null;
        String userId = "", fileName = "", no_of_blocks = "", str = "";
        while(true)
        {
        try
        {
            ServerSocket sersock = new ServerSocket(3003);
            sock = sersock.accept();  

            System.out.println("***********************************************************");
            System.out.println("\t CLOUD FRAMEWORK - Partial Audit");
            System.out.println("***********************************************************");
                        
            ObjectInputStream ois=new ObjectInputStream(sock.getInputStream());
            userId          = (String)ois.readObject();            
            fileName        = (String)ois.readObject();            
            no_of_blocks    = (String)ois.readObject();
            String blknams[]= (String[])ois.readObject();
            String halfPath = "D:\\Cloud\\hash\\";
            for(int u=0;u<blknams.length;u++)
            {
                System.out.println(blknams[u]);
            }
            //File dir1 = new File(halfPath);
            //String[] files_dir1 = dir1.list();
            
            /*File dir2 = new File(halfPath+"2");
            String[] files_dir2 = dir2.list();

            File dir3 = new File(halfPath+"3");
            String[] files_dir3 = dir3.list();*/

            String[] files = new String[(blknams.length)];int cnt = 0;

            for(int i=0;i<blknams.length;i++)
            {
                FileInputStream fis = new FileInputStream(halfPath+blknams[i]);
                byte[] data = new byte[fis.available()];
                fis.read(data);
                files[cnt] = new String(data);
                System.out.println(files[cnt]);
                cnt++;
            }
            System.out.println("\t Retrieved content of the Files");
            System.out.println("***********************************************************");    
            //for(int i=0;i<files.length;i++)
                //System.out.println(files[i]);
            System.out.println("***********************************************************");

            System.out.println("    Sending all Murmur Hash Values");
            System.out.println("***********************************************************");

            ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
            out.writeObject(files);
            
            System.out.println("***********************************************************"); 
            
            System.out.println("****************************Error correctness****************************"); 
            String error=(String)ois.readObject();
            if(error.equals("yes"))
            {
            String orgblockname=(String)ois.readObject();
            String orghashvalue=(String)ois.readObject();
            System.out.println(orgblockname+"yyyyyyyyyyyyyyyyyyyyyyy"+orghashvalue);
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
            /*while(y==1)
            {
                for(int i=0;i<blknams.length;i++)
                {
                FileInputStream fis1 = new FileInputStream(half[c]+blknams[i]);
                byte[] data = new byte[fis1.available()];
                fis1.read(data);
                files1[cnt1] = new String(data);
                //System.out.println(files[cnt]);
                cnt1++;
                }
                cnt1=0;
                for(int i=0;i<blknams.length;)
                {
                    if(orghashvalue.equals(files1[i]))
                    {
                        try (FileWriter fw = new FileWriter(halfPath+orgblockname,false)) {
                            System.out.println(halfPath+orgblockname);
                            fw.write(orghashvalue);
                            fw.close();
                         break; 
                        }
                    }
                    else
                    {
                    i++;    
                    }
                    c++;
                     for(int j=0;j<half.length;j++)
                        {
                            try (FileWriter fw1 = new FileWriter(half[j]+orgblockname,false)) {
                                fw1.write(orghashvalue);
                                fw1.close();
                            }
                        }
                    if(c==3)
                    {
                        y=2;
                    }
                }
            }
            }
            else{}
        }*/ sersock.close();
            }
            else{
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
