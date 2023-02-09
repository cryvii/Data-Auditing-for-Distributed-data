/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TPApartial;

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
    public static int hash32( String s) 
    {
	byte data[]=s.getBytes();
	final int m = 0x5bd1e995;
        final int r = 24;
	int seed=0x9747b28c;
		// Initialize the hash to a random value
		int l=(data.length)-1;
		int h = seed^l;
		int length4 = l/4;

		for (int i=0; i<length4; i++) {
			final int i4 = i*4;
			int k = (data[i4+0]&0xff) +((data[i4+1]&0xff)<<8)
					+((data[i4+2]&0xff)<<16) +((data[i4+3]&0xff)<<24);

			k *= m;
			k ^= k >>> r;
		                   k *= m;
			h *= m;
			h ^= k;

		}
		
		// Handle the last few bytes of the input array
		switch (l%4) {
		case 3: h ^= (data[(l&~3)+2]&0xff) << 16;
		case 2: h ^= (data[(l&~3) +1]&0xff) << 8;
		case 1: h ^= (data[l&~3]&0xff);
				h *= m;
		}

		h ^= h >>> 13;
		h *= m;
		h ^= h >>> 15;
                //System.out.print(h+"\\");
		return h;
	}
    public static void main(String[] args) throws IOException
    {
        Socket sock = null;
        String userId = "", fileName = "", no_of_blocks = "", str = "";
       while(true)
       {
        try
        {
            ServerSocket sersock = new ServerSocket(3006);
            sock = sersock.accept();  

            System.out.println("***********************************************************");
            System.out.println("CLOUD FRAMEWORK TPA - Partial Audit");
            System.out.println("***********************************************************");
                        
            ObjectInputStream ois=new ObjectInputStream(sock.getInputStream());
            userId          = (String)ois.readObject();            
            fileName        = (String)ois.readObject();            
            no_of_blocks    = (String)ois.readObject();
            String blknams[]= (String[])ois.readObject();
            int treeorder[]     = (int[])ois.readObject();
            String hashes[]= (String[])ois.readObject();
            int a[]     = (int[])ois.readObject();
            //String halfPath = "D:\\Cloud\\hash\\";
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

            //String[] files = new String[(blknams.length)];int cnt = 0;

            //for(int i=0;i<blknams.length;i++)
            //{
            //    FileInputStream fis = new FileInputStream(halfPath+blknams[i]);
            //    byte[] data = new byte[fis.available()];
            //    fis.read(data);
            //    files[cnt] = new String(data);
             //   System.out.println(files[cnt]);
             //   cnt++;
            //}
            Socket send=new Socket("127.0.0.1",3007);
            ObjectOutputStream out1 = new ObjectOutputStream(send.getOutputStream());
            out1.writeObject(userId);
            out1.writeObject(fileName);
            out1.writeObject(no_of_blocks);
            out1.writeObject(blknams);
            ObjectInputStream oiss=new ObjectInputStream(send.getInputStream());
            String[] files=(String[])oiss.readObject();
            System.out.println("***********************************************************");
            System.out.println("\t Retrieved content of the Files from cloud server");
            for(int i=0;i<files.length;i++)
                System.out.println(files[i]);
           
            System.out.println("***********************************************************");    
            //for(int i=0;i<files.length;i++)
            //String [] elements5=(String[])ois2.readObject();
            String [] treeo5=new String[Integer.parseInt(no_of_blocks)];
            for(int yu=0;yu<a.length;yu++)
            {   
            int order=a[yu]-1;
            hashes[order]=files[yu];
            }
        
        for(int yg=0;yg<hashes.length;yg++)
        {
            int gu=treeorder[yg];
            //System.out.println(gu);
            treeo5[yg]=hashes[gu];
            //System.out.println(treeo[yg]);
        }
    int u2=0;
    int c2=(int) Math.sqrt(Integer.parseInt(no_of_blocks));
    String []trelem2=new String[c2];
    StringBuilder s11=new StringBuilder();
    StringBuilder s12=new StringBuilder();
    int i=0,j=0;
    //System.out.println(treeo.length);
    while(i<Integer.parseInt(no_of_blocks))
    {
        while(u2<c2)
        {
            s11.append(treeo5[i]);
            i++;
            u2++;
            if(u2==c2)
            {
                u2=0;
                trelem2[j]=Integer.toHexString(hash32(s11.toString()));
                //System.out.println(trelem2[j]);
                j++;
                break;
            }
        }
    }
    i=0;
    while(i<c2)
    {
       s12.append(trelem2[i]);
       i++;
    }
    
    String rootelem2=Integer.toHexString(hash32(s12.toString()));
    System.out.println("***********************************************************");
    System.out.println("ROOT:"+rootelem2);
    System.out.println("***********************************************************");
                //System.out.println(files[i]);
            System.out.println("***********************************************************");
            
            System.out.println("    Sending ROOT VALUE to the user....");
            System.out.println("***********************************************************");

            ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
            out.writeObject(rootelem2);
            
            String yes=(String)ois.readObject();
            if(yes.equals("yes"))
            {
                System.out.println("\t INTEGRITY VERFIFIED..!");
                sersock.close();
            }
            else
            {
                
        System.out.println("******************************RESPONSIVE CHANGES FROM USER****************************");
        int culprit=0;
        String trelem[]=(String[])ois.readObject();
        String shufconttree[]=(String[])ois.readObject();
        int order[]=(int[])ois.readObject();
        String hashed[]=(String[])ois.readObject();
        for(int i2=0;i2<trelem2.length;)
        {
            if(!(trelem[i2].equals(trelem2[i2])))
            {
               culprit=i2+1;
               break;
            }
            else
            {
                i2++;
            }
        }
        System.out.println("THE ERROR TREE ELEMENT:"+culprit);
        //int end=(culprit*c2);
        //int start=end-c2;
        //System.out.println("THE start:"+start);
       // System.out.println("THE End:"+end);
        for(int v=0;v<shufconttree.length;)
        {
            if((shufconttree[v].equals(treeo5[v])))
            {
                v++;
            }
            else{
                int h=order[v];
                System.out.println("THE order:"+h);
                String blockn=hashed[h];
                System.out.println("ERROR:"+treeo5[v]);
                System.out.println("ERROR block name:"+blockn);
                System.out.println("Sending the block which is attacked:"+shufconttree[v]);
                out1.writeObject("yes");
                out1.writeObject(blockn);
                out1.writeObject(shufconttree[v]);
                break;
            }
        }
            }
            String result=(String)oiss.readObject();
            if(result.equals("Corrected"))
            {
                out.writeObject("Corrected");
                System.out.println("Error is rectified and localised for a single block.............................");
                System.out.println(".................................................................................");
                System.out.println("Run The process again to check for full integrity................................");

                sersock.close();
            }
            else
            {
                 System.out.println("Error is localised.............................");
                 sersock.close();
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
