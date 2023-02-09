/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Partial;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author tce
 */
public class Client {
     /*public static void main(String args[]) throws IOException, ClassNotFoundException
    {
        String[] actualFileNames,actualhash;;
        int g=0,f=0;
        Socket sock2 = new Socket("127.0.0.1", 3003);
        System.out.println("Enter The userId:");
        String userId2 = br.readLine();
        System.out.println("Enter The filename:");
        String fileName2 = br.readLine();
        System.out.println("enter the no of blocks:");
        String no_of_blocks2 = br.readLine();
        System.out.println("***********************************************************");
        System.out.println("\t CLOUD FRAMEWORK -FULL AUDIT");
        System.out.println("***********************************************************");
        System.out.println("User ID          : " + userId2);
        System.out.println("Name of the File : " + fileName2);
        System.out.println("Number of Blocks : " + no_of_blocks2);
        System.out.println("***********************************************************");
        System.out.println("Enter no of blocks to be audited:");
        int nu=Integer.parseInt(br.readLine());
        int [] a=new int[nu];
        for (int yo=0;yo<nu;yo++)
        {
            System.out.println("Enter the "+yo+" block number to audited:");
            a[yo]=Integer.parseInt(br.readLine());
        }
        File metaInf1 = new File(fileName+"_meta_file2.csv");
        try
        {
            FileReader fileReader = new FileReader(metaInf1);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuffer = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) 
            {
                String[] lineCSV = line.split(",");
                String[] linecsv= line.split(",");
                actualFileNames[g++] = lineCSV[2];
                actualhash[f++]=linecsv[3];
            }
            fileReader.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        String[] blockNames=new String[nu];
        int cnt=0;
        for(int x=0;x<a.length;x++)
        {   
            int u=a[x]-1;
            blockNames[cnt]=actualFileNames[u];
            cnt++;
        }
        ObjectOutputStream out2 = new ObjectOutputStream(sock2.getOutputStream());
        out2.writeObject(userId);
        out2.writeObject(fileName);
        out2.writeObject(no_of_blocks);
        out2.writeObject(blockNames);
        ObjectInputStream ois2=new ObjectInputStream(sock2.getInputStream());
        String [] elements=(String[])ois2.readObject();
        String [] hashes= actualhash;
        for(int yy=0;yy<nu;yy++)
        {
            hashes[a[yy]-1]=elements[yy];
        }
            }*/
}
