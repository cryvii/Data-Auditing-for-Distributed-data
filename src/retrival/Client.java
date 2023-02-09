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
import java.io.*;
import java.util.*;
import java.net.Socket;

public class Client 
{
    private static String fileName;
    private static String no_of_blocks;
    Scanner kb = new Scanner(System.in);
    private Socket socket = null;
    private InputStream inStream = null;
    private OutputStream outStream = null;
    static String userId = "";


    public static void join(String fileName, String[] filenames, int no_of_blocks) throws IOException
    {
        
        String[] fileNames  = filenames;int cnt1 = 1;
        int no_of_block = no_of_blocks;
        for(String s: fileNames)
        {
            System.out.println(s);
        }
        String halfPath = "D:\\Cloud\\" + fileName+"\\";

        FileWriter out = new FileWriter(fileName+"_Recovered.txt");
        StringBuilder str=new StringBuilder("");
        String temp1;
        try
        {
            for (int part = 1; part <= no_of_block; part++)
            {   
                int b;String filePath = "";
                char c=0;
                
                    filePath = halfPath+fileNames[cnt1++];
                System.out.println(filePath);
                //BufferedInputStream in = new BufferedInputStream(new FileInputStream(filePath));
                try ( 
                        FileReader in = new FileReader(filePath)) {
                    //BufferedInputStream in = new BufferedInputStream(new FileInputStream(filePath));
                    while ( (b = in.read()) != -1 )
                    {
                        c=(char)b;
                        str.append(c);
                        
                    }
                }
            }
            temp1=str.toString();
            out.write(temp1);
            System.out.println(temp1);
            out.close();
        }
        
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception 
    {
        Socket sock = new Socket("127.0.0.1", 3001);
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter The userId:");
        userId=br.readLine();
        System.out.println("Enter The filename:");
        fileName=br.readLine();
        System.out.println("enter the no of blocks:");
        no_of_blocks=br.readLine();
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
        String[] retrievedFiles  = (String[])ois.readObject();            

        System.out.println("\t Retrieved File Blocks ");
        System.out.println("***********************************************************");
        for(int i=0;i<retrievedFiles.length;i++)
            System.out.println(retrievedFiles[i]/*+" "+(i+1)+" "+((i+1)%3)*/);
        
        System.out.println("***********************************************************");
        System.out.println("Number of Blocks received : "+retrievedFiles.length);
        System.out.println("***********************************************************");
        System.out.println("All Blocks Received from Server..!");
        System.out.println("***********************************************************");

        String[] actualFileNames = new String[retrievedFiles.length+1];int g = 0;
        File metaInf1 = new File(fileName+"_meta_file2.csv");

        try
        {
            FileReader fileReader = new FileReader(metaInf1);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) 
            {
                String[] lineCSV = line.split(",");
                actualFileNames[g++] = lineCSV[1];
            }
            fileReader.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        join(userId,actualFileNames,Integer.parseInt(no_of_blocks));

        out.writeObject("File Recovered Successfully..!");
        
        System.out.println("The Required File has been Recovered Successfully..!");
        System.out.println("***********************************************************");        
    }
}