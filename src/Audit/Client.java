/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Audit;

/**
 *
 * @author tce
 */
import java.io.*;
import java.net.*;
import java.util.*;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client 
{
    Scanner kb = new Scanner(System.in);
    private Socket socket = null;
    private InputStream inStream = null;
    private OutputStream outStream = null;
    static String userId = "";
    
    static String hash32( final byte[] data, int length, int seed) 
    {
        // 'm' and 'r' are mixing constants generated offline.
        // They're not really 'magic', they just happen to work well.
        final int m = 0x5bd1e995;
        final int r = 24;
        // Initialize the hash to a random value
        int h = seed^length;
        int length4 = length/4;

        for (int i=0; i<length4; i++) 
        {
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
        switch (length%4) 
        {
            case 3: h ^= (data[(length&~3) +2]&0xff) << 16;
            case 2: h ^= (data[(length&~3) +1]&0xff) << 8;
            case 1: h ^= (data[length&~3]&0xff);
                    h *= m;
        }

        h ^= h >>> 13;
        h *= m;
        h ^= h >>> 15;
        return Integer.toHexString(h);
    }
    static String tree(List<String> l,int no)
    {
        List<String> l1=new ArrayList<String>(l);
        while(true)
        {
            int count =0 ;
            if(l1.size()==1)
                break;
            StringBuilder str=new StringBuilder("");
            List l2=new ArrayList();
            for(String ls : l1 )
            {
                count++;
                str.append(ls);
                if(count%no==0 || count==l1.size())
                {
                    byte[] hash=(str.toString()).getBytes();
                    String s=hash32(hash,hash.length,3);
                    l2.add(s);
                    str.delete(0,str.length());
                }
            }
            l1.clear();
            l1=new ArrayList<String>(l2);
            //System.out.println("Intermediate nodes :"+l1);
        }
        //System.out.println("Final hash value :"+l1);
        return l1.get(0);   
    }

    public static void main(String[] args) throws Exception 
    {
        Socket sock = new Socket("127.0.0.1", 3002);

        userId                 = args[0];
        String fileName        = args[1];
        String no_of_blocks    = args[2];
        
        Scanner kb = new Scanner(System.in);
        long chunkSize=0,fileSize=0;int subfile=0, totalFileSize=0;
        BufferedInputStream in = null;
        String[] blocks = null; int cnt = 0;
        
        System.out.println("***********************************************************");
        System.out.println("\t CLOUD FRAMEWORK - RETRIEVAL");
        System.out.println("***********************************************************");
        System.out.println("User ID          : " + userId);
        System.out.println("Name of the File : " + fileName);
        System.out.println("Number of Blocks : " + no_of_blocks);
        System.out.println("***********************************************************");

        ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
        out.writeObject(userId);
        out.writeObject(fileName);
        out.writeObject(no_of_blocks);
        
        ObjectInputStream ois=new ObjectInputStream(sock.getInputStream());
        String[] retrievedModiMurMurHash  = (String[])ois.readObject();            

        System.out.println("   Retrieved Modified MurMur HASH Values");
        System.out.println("***********************************************************");
        for(int i=1;i<retrievedModiMurMurHash.length;i++)
            System.out.println(retrievedModiMurMurHash[i]);
        System.out.println("***********************************************************");
       
        System.out.println("Final Root Hash Value : ");
        System.out.println("***********************************************************");

        System.out.println("\t INTEGRITY VERIFIED..!");
        System.out.println("***********************************************************");
    }
}