/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TPAfull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author tce
 */
public class tpa {
    public static void main(String args[]) throws IOException
    {
       
        Socket sock = null;
        String userId = "", fileName = "", no_of_blocks = "", str = "";
        String hashedfname[];
        while(true)
        {
        try
        {
            ServerSocket sersock = new ServerSocket(3004);
            sock = sersock.accept();  

            System.out.println("***********************************************************");
            System.out.println("\t CLOUD FRAMEWORK TPA - Full Audit");
            System.out.println("***********************************************************");
                        
            ObjectInputStream ois=new ObjectInputStream(sock.getInputStream());
            userId          = (String)ois.readObject();            
            fileName        = (String)ois.readObject();            
            no_of_blocks    = (String)ois.readObject();
            hashedfname     =  (String[])ois.readObject();
            String halfPath = "D:\\Cloud\\hash\\";
            
            File dir1 = new File(halfPath);
            String[] files_dir1 = dir1.list();
            
            /*File dir2 = new File(halfPath+"2");
            String[] files_dir2 = dir2.list();

            File dir3 = new File(halfPath+"3");
            String[] files_dir3 = dir3.list();*/

           // String[] files = new String[Integer.parseInt(no_of_blocks)];int cnt = 0;

           // for(int i=0;i<hashedfname.length;i++)
           // {
           //     FileInputStream fis = new FileInputStream(halfPath+hashedfname[i]);
           //     byte[] data = new byte[fis.available()];
           //     fis.read(data);
           //     files[cnt++] = new String(data);
           // }
            Socket send=new Socket("127.0.0.1",3005);
            ObjectOutputStream out1 = new ObjectOutputStream(send.getOutputStream());
            out1.writeObject(userId);
            out1.writeObject(fileName);
            out1.writeObject(no_of_blocks);
            out1.writeObject(hashedfname);
            System.out.println("\t Retrieved content of the Files From cloud:");
            System.out.println("***********************************************************");   
            ObjectInputStream oiss=new ObjectInputStream(send.getInputStream());
            String[] files=(String[])oiss.readObject();
            for(int i=0;i<files.length;i++)
                System.out.println(files[i]);
            System.out.println("***********************************************************");
            ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
            //out.writeObject(files);
            int yu=0;
            long r1,ex1=0;
            System.out.println("***********************************************************");
            while(yu<Integer.parseInt(no_of_blocks))
            {   
            r1=Long.parseLong(files[yu], 16);
            ex1=ex1^r1;
            yu++;
            }
            //out.writeObject(finalHash);
            out.writeObject(Long.toHexString(ex1));
            System.out.println("\t sending the Audit Block to the User");
             System.out.println("***********************************************************");  
             System.out.println("***********************************************************");  
             System.out.println("***********************************************************");  
             System.out.println("***********************************************************");  
             System.out.println("***********************************************************"); 
            System.out.println("\t waiting for Response from user");
             
            System.out.println("..................................................................");
            String yes=(String)ois.readObject();
            if(yes.equals("yes"))
            {
                System.out.println("\t INTEGRITY VERFIFIED..!");
                sersock.close();
            }
            else
            {
                String [] shufconttree=(String[])ois.readObject();
                String [] shufhashtree=(String[])ois.readObject();
                String [] shufcontname=(String[])ois.readObject();
            boolean test=false;
            for(int i=0;i<files.length;)
            {   
            for(int j=0;j<shufconttree.length;)
            {
                if(shufconttree[j].equals(files[i]))
                {
                    test=true;
                    //System.out.append("irku");
                    break;
                }
                else{
                    j++;
                    test=false;
                }
                
            }
            if(test)
                {
                   i++; 
                }
            else
            {
                int k=i;
                String bnam=hashedfname[k];
                System.out.println(bnam);
                String orgvalue="";
                
                for(int h=0;h<shufhashtree.length;)
                {
                    if(shufhashtree[h].equals(bnam))
                    {
                        orgvalue=shufcontname[h];
                        System.out.println("ERROR:"+bnam);
                    //System.out.println("ERROR block name:"+blockn);
                    //System.out.println("Sending the block which is attacked:"+orgvalue);
                    
                    out1.writeObject("yes");
                    out1.writeObject(bnam);
                    out1.writeObject(orgvalue);
                        break;
                    }
                    else
                    {
                        h++;
                        //System.out.println("yes");
                    }
                    //break;
                }
             break;   
             
            }
            //break;
        }
            String result=(String)oiss.readObject();
            if(result.equals("Corrected"))
            {
                System.out.println("Erroe rectified and localised...................");
                out.writeObject("Corrected");
                sersock.close();

            }
            else
            {
                System.out.println("Error found and localised...................");
                sersock.close();
            }
            }
            System.out.println("***********************************************************");        
        }

        catch(Exception ex)
        {
            ex.printStackTrace();
        }        
        
    }
    } 
}
