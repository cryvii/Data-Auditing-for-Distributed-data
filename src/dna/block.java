/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dna;

/**
 *
 * @author tce
 */

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import static retrival.Client.join;

class block
{
    String name[],hashed[],conhash[];
    int n; 
    int blcksiz,j,b=0;
    int rmb,numb; long blc;
    String na = null, s,t,dn;
    String salt="RATHNACHANDRUVIJAY";
    String dir,noffile,userid;
    String[] t1;
    BufferedOutputStream bout;
    BufferedInputStream bin; 
    File f;
    int ex, order[];
    String audit; 
    List<String> solution,content;
    String[] shufhashname;
    String[] shufcontname;
    int[] treeorder;
    ArrayList<Object> solutree;
    ArrayList<Object> conttree;
    String[] shufhashtree;
    String[] shufconttree;
    String[] trelem;
    String rootelem,rep1,rep2,rep3;
    File metaInf1;
    File metaInf2;
    public block(String file, int n123) throws FileNotFoundException, IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the Userid:");
        userid=br.readLine();
        noffile=file;
        metaInf1 = new File(file.substring(0,file.length()-4)+"_meta_file1.csv");
        metaInf2 = new File(file.substring(0,file.length()-4)+"_meta_file2.csv");
        bin= new BufferedInputStream(new FileInputStream(file)); 
        blcksiz=n123;
         f=new File(file);
        blc=f.length()/blcksiz;
            name=new String[(int)blcksiz];
            hashed=new String[(int)blcksiz];
            conhash=new String[(int)blcksiz];
            numb=(int)blcksiz;
            order=new int[(int)blcksiz];
            treeorder=new int[(int)blcksiz];
        //System.out.println(hashed.length);
        t=f.getName();
        
        t1=t.split(".txt");
        System.out.println(Arrays.toString(t1));
        dir="D:"+"\\"+"Cloud"+"\\"+userid;
        
        File d=new File(dir);
        d.mkdirs();
        
    }
    public static String sha512(String passwordToHash, String salt) throws UnsupportedEncodingException
    {
    String generatedPassword = null;
    try {

        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt.getBytes("UTF-8"));
        byte[] bytes = md.digest(passwordToHash.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        generatedPassword = sb.toString();
        } 
    catch (NoSuchAlgorithmException e) 
        {
        }
    return generatedPassword;
    }
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
    public static void close(Closeable stream) {
        try {
            if (stream != null) {
                stream.close();
            }
        } catch(IOException e) {
            //...
        }
    }
    public void blocking() throws UnsupportedEncodingException, FileNotFoundException, IOException
    {
        
        File f1;
        
        for( j=0;j<blcksiz;)
        {
            s=dir+"\\"+userid+t1[0]+"_"+j+"."+"txt";
            dn=userid+t1[0]+"_"+j+"."+"txt";
            na=s;
            name[j]=dn;
            hashed[j]=sha512(name[j], salt);
                System.out.println("././././././././././././././././././././././././././.Block NAME HASHED USING SHA512/./././././././././././././.");

            System.out.println("THE "+j+" BLOCKNAME :"+hashed[j]);
            f1=new File(s);
            bout=new BufferedOutputStream(new FileOutputStream(f1));
            for(b=0;b<blc;b++)
            {
                bout.write(bin.read());
            }
            bout.close();
            s="";
            j=j+1;
        }
            System.out.println("././././././././././././././././././././././././././././././././././././././././.");
           
        if(f.length()!=(b*blcksiz))
        {
           //System.out.println(na);
            
    		
    		File file1 =new File(na);
    		//if file doesnt exists, then create it
    		if(!file1.exists()){
    			file1.createNewFile();
    		}
    		StringBuilder strb=new StringBuilder("");
    		//true = append file
                //bout=new BufferedOutputStream(new FileOutputStream(file1));
                while((rmb = bin.read())!=-1)
                    {
                    char c=(char) rmb;
                    //System.out.println(c);
                    strb.append(c);
                    }
                    String temp=strb.toString();
                    //System.out.println(temp);
                    FileWriter fileWritter = new FileWriter(na,true);
            //out.append(temp,0,temp.length());
            try (BufferedWriter out = new BufferedWriter(fileWritter)) {
                //out.append(temp,0,temp.length());
                fileWritter.append(temp);
                //System.out.println(file1.getName());
            }
        }
    	    
	        System.out.println("Done");
	
    }
    public void hashing() throws FileNotFoundException, IOException
    {
        ex=0;
        System.out.println("Enter the folder");
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String fileopen=br.readLine();
        File diropen = new File(fileopen);

        int fi=0,l=0;
        String fnam[]=new String[numb];
        String dir1="D:"+"\\"+"Cloud"+"\\hash";
        System.out.println("././././././././././././././././././././././././././././././././././././././././.");
        File d1=new File(dir);
        d1.mkdirs();
        System.out.println("Content Hashed With Murmur Hash:");
        if( diropen.exists())
        {   
            for(File f13 : diropen.listFiles())
            {
                fnam[fi]=f13.getName();
                FileInputStream fis = new FileInputStream(f13);
                byte[] data = new byte[fis.available()];
                fis.read(data);
                String text = new String(data);
//System.out.println("a"+text);
                int r=hash32(text);
                ex=ex^r;
                conhash[l]= Integer.toHexString(r);
                System.out.println(conhash[l]);
                /*{
                    FileReader fr = null;
                    FileWriter fw = null;
                    try {
                    fr = new FileReader(dir+"\\"+name[fi]);
                    fw = new FileWriter(dir1+"\\"+hashed[fi]);
                    int c = fr.read();
                    while(c!=-1) {
                        fw.write(c);
                        c = fr.read();
                        }
                        }
                catch(IOException e) {
                }
                finally {
                    close(fr);
                    close(fw);
                }
                }*/
                l++;
                fi++;
            }
           
        }
        
    else { 
            }
    System.out.println("././././././././././././././././././././././././././././././././././././././././.");
    System.out.println("Audit block Value:");
    audit=Integer.toHexString(ex);
    System.out.println(audit);
    //Shuffling order
    n=blcksiz;
    solution = new ArrayList<>();
    content=new ArrayList<>();
     System.out.println(n);
    for (int i = 0; i < n; i++)
    {
       
        solution.add(hashed[i]);
    }
    int y=0;
    Collections.shuffle(solution);
    for(int i=0;i<n;i++)
    {
        for(int y1=0;y1<n;y1++)
        {
        if(solution.get(i).equals(hashed[y1]))
        {
            order[y]=y1;
            content.add(conhash[y1]);
            y++;
            break;
        }
        }        
    }
    //for(int rr: order)
    //{
    //    System.out.println(rr);
    //}
    System.out.println("././././././././././././././././././././././././././././././././././././././././.");
    Object[] objDays = solution.toArray();
    shufhashname = Arrays.copyOf(objDays, objDays.length, String[].class);
    Object[] obj = content.toArray();
    shufcontname = Arrays.copyOf(obj, obj.length, String[].class);
    fi=0;
    int g=0,k=0,g1=0,g2=0,g3=0,k1=0,k2=0,k3=0;
    System.out.println("././././././././././././././././././././././././././././././././././././././././.");
    System.out.println("Shuffling Order for Storage:");
    rep1="D:"+"\\"+"Replicate1"+"\\"+"Cloud"+"\\hash";
    rep2="D:"+"\\"+"Replicate2"+"\\"+"Cloud"+"\\hash";
    rep3="D:"+"\\"+"Replicate3"+"\\"+"Cloud"+"\\hash";
    File d2=new File(rep1);
        d2.mkdirs();
    File d3=new File(rep2);
        d3.mkdirs();
    File d4=new File(rep3);
        d4.mkdirs();
    while(g<numb){
                    //System.out.println("yyy");
                    FileReader fr = null;
                    FileWriter fw = null;
                    fi=order[g];
                    try {
                    fr = new FileReader(dir+"\\"+name[fi]);
                    fw = new FileWriter(dir1+"\\"+shufhashname[k]);
                    System.out.println("File Name: "+shufhashname[k]);
                    int c = fr.read();
                    
                        fw.write(conhash[fi]);
                    System.out.println("Content present in the file:"+conhash[fi]);    
                                
                        }
                catch(IOException e) {
                }
                finally {
                    close(fr);
                    close(fw);
                }
                g++;
                k++;
                }
    System.out.println("././././././././././././././././././././././././././././././././././././././././.");
    while(g1<numb){
                    //System.out.println("yyy");
                    FileReader fr = null;
                    FileWriter fw = null;
                  
                    fi=order[g1];
                   
                    try {
                    fr = new FileReader(dir+"\\"+name[fi]);
                    //File ff=new File();
                    fw = new FileWriter(rep1+"\\"+shufhashname[k1]);
                    //System.out.println(rep1+"\\"+shufhashname[k1]);
                    //System.out.println("File Name: "+shufhashname[k1]);
                    //int c = fr.read();
                    
                        fw.write(conhash[fi]);
                    //System.out.println("Content present in the file:"+conhash[fi]);  
                     //System.out.println("ula:::::::::::::::::::::::::::::::::");
                                
                        }
                catch(IOException e) {
                }
                finally {
                    close(fr);
                    close(fw);
                }
                g1++;
                k1++;
                }
    System.out.println("././././././././././././././././././././././././././././././././././././././././.");
    
    while(g2<numb)
    {
    //System.out.println("yyy");
                    FileReader fr = null;
                    FileWriter fw = null;
                    fi=order[g2];
                    try {
                    fr = new FileReader(dir+"\\"+name[fi]);
                    fw = new FileWriter(rep2+"\\"+shufhashname[k2]);
                    //System.out.println("File Name: "+shufhashname[k2]);
                    int c = fr.read();
                    
                        fw.write(conhash[fi]);
                    //System.out.println("Content present in the file:"+conhash[fi]);    
                                
                        }
                catch(IOException e) {
                }
                finally {
                    close(fr);
                    close(fw);
                }
                g2++;
                k2++;
                }
    System.out.println("././././././././././././././././././././././././././././././././././././././././.");
                    while(g3<numb){//System.out.println("yyy");
                    FileReader fr = null;
                    FileWriter fw = null;
                    fi=order[g3];
                    try {
                    fr = new FileReader(dir+"\\"+name[fi]);
                    fw = new FileWriter(rep3+"\\"+shufhashname[k3]);
                    //System.out.println("File Name: "+shufhashname[k3]);
                    int c = fr.read();
                    
                        fw.write(conhash[fi]);
                    //System.out.println("Content present in the file:"+conhash[fi]);    
                                
                        }
                catch(IOException e) {
                    System.out.println(e);
                }
                finally {
                    close(fr);
                    close(fw);
                }
                g3++;
                k3++;
                }
    System.out.println("././././././././././././././././././././././././././././././././././././././././.");
    //System.out.println(solution);
    //System.out.println(content);
    }
    
    
    public void treeorder()
    {   
    
    solutree = new ArrayList<>();
    conttree=new ArrayList<>();
    // System.out.println(n);
    for (int i = 0; i < n; i++)
    {
       
        solutree.add(hashed[i]);
    }
    Collections.shuffle(solutree);
    int y=0;
    for(int i=0;i<n;i++)
    {
        for(int y1=0;y1<n;y1++)
        {
        if(solutree.get(i).equals(hashed[y1]))
        {
            treeorder[y]=y1;
            conttree.add(conhash[y1]);
            y++;
            break;
        }
        }        
    }
    Object[] objDays = solution.toArray();
    shufhashtree = Arrays.copyOf(objDays, objDays.length, String[].class);
    Object[] obj = content.toArray();
    
    shufconttree = Arrays.copyOf(obj, obj.length, String[].class);
    System.out.println("././././././././././././././././././././././.TREE ORDER/./././././././././././././././././.");
    for(String s: shufhashtree)
    {
        System.out.println("FILENAME: "+s);
    }
    
    
    for(String s: shufconttree)
    {
        System.out.println("Content: "+s);
    }
    
    System.out.println("././././././././././././././././././././././././././././././././././././././././.");
    int u=0;
    int c=(int) Math.sqrt(blcksiz);
    trelem=new String[c];
    StringBuilder s11=new StringBuilder();
    StringBuilder s12=new StringBuilder();
    int i=0,j=0;
    
    while(i<blcksiz)
    {
        while(u<c)
        {
            s11.append(shufconttree[i]);
            i++;
            u++;
            if(u==c)
            {
                u=0;
                trelem[j]=Integer.toHexString(hash32(s11.toString()));
                System.out.println("./././././././././././././././././././././TREE ELEMENTS./././././././././././././././././././.");
                System.out.println(trelem[j]);
                j++;
                break;
            }
        }
    }
    i=0;
    while(i<c)
    {
       s12.append(trelem[i]);
       i++;
    }
     
        System.out.println("././././././././././././././././././././././././././././././././././././././././.");
    rootelem=Integer.toHexString(hash32(s12.toString()));
    System.out.println("ROOT:"+rootelem);
    //System.out.println(order.length);
   String orderSent = "", treeOrder = "";
        
        for(Integer number : order) 
            orderSent = orderSent + Integer.toString(number) + " ";

        for(Integer  number : treeorder) 
            treeOrder = treeOrder + Integer.toString(number) + " ";

    
        String header = "FileName,Number of Blocks,Sending Order,Tree Order\n";
        String metaInfOne =	header + noffile + "," +
        					Long.toString(f.length()/blc) + "," +
        					orderSent + "," +
        					 treeOrder+ "\n";
       	
        try
        {
			FileOutputStream fosRslt = new FileOutputStream(metaInf1);
            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fosRslt))) {
                bw.write(metaInfOne);
            }
		}
		catch(Exception aa)
		{
		}
        header = "BlockNumber, BlockName, SHA512, MurmurHashValue\n";
		String metaInfTwo = header;
		for(int iii=0;iii<hashed.length;iii++)
			metaInfTwo = metaInfTwo + Integer.toString(iii+1) + "," + name[iii] + "," 
						+ hashed[iii]
					    + "," + conhash[iii] +"\n";

		try
        {
			FileOutputStream fosRslt = new FileOutputStream(metaInf2);
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fosRslt))) {
            bw.write(metaInfTwo);
        }
		}
		catch(Exception aa)
		{
		}
    }
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
public void retrieve() throws IOException
{
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Enter the user id:");
    int userid=Integer.parseInt(br.readLine());
    
}
public static void main(String args[])throws Exception
{
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Enter the file to split into blocks");
    String file=br.readLine();
    System.out.println("enter the random square term:");
    int num1=Integer.parseInt(br.readLine());
    block b=new block(file,num1);
    b.blocking();
    b.hashing();
    b.treeorder();
    int n=0;
    while(n!=6)
    {
    System.out.println("***********************************************************");
    System.out.println("1.Retrieval\n2.Full Auditing Without TPA\n3.Partial Auditing Without TPA\n4.Full Auditing with TPA\n5.Partial Auditing With TPA\n6.Exit");
    System.out.println("***********************************************************");
    n=Integer.parseInt(br.readLine());
    switch(n){
        case 1:
            Socket sock = new Socket("127.0.0.1", 3001);
        
        System.out.println("Enter The userId:");
        String userId = br.readLine();
        System.out.println("Enter The filename:");
        String fileName = br.readLine();
        System.out.println("enter the no of blocks:");
        String no_of_blocks = br.readLine();
       
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
            StringBuilder stringBuffer = new StringBuilder();
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
            
            break;
        case 2:
        
            
       
        
        Socket sock1 = new Socket("127.0.0.1", 3002);
        System.out.println("Enter The userId:");
        String userId1 = br.readLine();
        System.out.println("Enter The filename:");
        String fileName1 = br.readLine();
        System.out.println("enter the no of blocks:");
        String no_of_blocks1 = br.readLine();
        String [] treeo1=new String[Integer.parseInt(no_of_blocks1)];
        String[] actualhash1=new String[Integer.parseInt(no_of_blocks1)];
        actualhash1=b.conhash;
        String [] hashes1= actualhash1;
        System.out.println("***********************************************************");
        System.out.println("\t CLOUD FRAMEWORK -FULL AUDIT");
        System.out.println("***********************************************************");
        System.out.println("User ID          : " + userId1);
        System.out.println("Name of the File : " + fileName1);
        System.out.println("Number of Blocks : " + no_of_blocks1);
        System.out.println("***********************************************************");
        
        ObjectOutputStream out1 = new ObjectOutputStream(sock1.getOutputStream());
        out1.writeObject(userId1);
        out1.writeObject(fileName1);
        out1.writeObject(no_of_blocks1);
        
        ObjectInputStream ois1=new ObjectInputStream(sock1.getInputStream());
        String[] retrievedBlockNames  = (String[])ois1.readObject();
        String[] retrievedMurMurHash  = (String[])ois1.readObject();            

        System.out.println("   Retrieved MurMur HASH Values");
        System.out.println("***********************************************************");
        for(int i=1;i<retrievedMurMurHash.length;i++)
            System.out.println(retrievedMurMurHash[i]);
        /*for(int yu=0;yu<a.length;yu++)
        {
            int order=a[yu]-1;
            hashes[order]=elements[yu];
        }*/
        
        /*for(int yg=0;yg<hashes1.length;yg++)
        {
            int gu=b.order[yg];
            //System.out.println(gu);
            treeo1[yg]=hashes1[gu];
            //System.out.println(treeo[yg]);
        }*/
        int yu0=0;
            long r1,ex1=0;
            System.out.println("***********************************************************");
            while(yu0<Integer.parseInt(no_of_blocks1))
            {   
            r1=Long.parseLong(retrievedMurMurHash[yu0], 16);
            ex1=ex1^r1;
            yu0++;
            }
            
        boolean test=false;    
        String full=Long.toHexString(ex1);
        System.out.println("Final Audit Value : "+full);
        System.out.println("***********************************************************");
        if(b.audit.equals(full))
        {
            System.out.println("\t INTEGRITY VERIFIED..!");
            out1.writeObject("no");
            System.out.println("***********************************************************");
        }
        else
        System.out.println("******************************RESPONSIVE CHANGES FROM USER****************************");        
        
        for(int i=0;i<retrievedMurMurHash.length;)
        {   
            for(int j=0;j<b.shufconttree.length;)
            {
                if(b.shufconttree[j].equals(retrievedMurMurHash[i]))
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
                String bnam=retrievedBlockNames[k];
                System.out.println(bnam);
                String orgvalue="";
                for(int h=0;h<b.shufhashtree.length;)
                {
                    if(b.shufhashtree[h].equals(bnam))
                    {
                        orgvalue=b.shufcontname[h];
                            System.out.println("***********************************************************");

                        System.out.println("ERROR Block Name:"+bnam);
                    //System.out.println("ERROR block name:"+blockn);
                    //System.out.println("Sending the block which is attacked:"+orgvalue);
                    
                    out1.writeObject("yes");
                    out1.writeObject(bnam);
                    out1.writeObject(orgvalue);
                        System.out.println("***********************************************************");
    System.out.println("Error Is corrected by server...............................................");

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
        
                        
                            
                        
                    
                    
                    
        
           
        break;
        case 3:
            String[] actualFileNames1,actualhash;
        int g1=0,f1=0;
        Socket sock2 = new Socket("127.0.0.1", 3003);
        System.out.println("Enter The userId:");
        String userId2 = br.readLine();
        System.out.println("Enter The filename:");
        String fileName2 = br.readLine();
        System.out.println("enter the no of blocks:");
        String no_of_blocks2 = br.readLine();
        System.out.println("***********************************************************");
        System.out.println("\t CLOUD FRAMEWORK -Partial AUDIT");
        System.out.println("***********************************************************");
        System.out.println("User ID          : " + userId2);
        System.out.println("Name of the File : " + fileName2);
        System.out.println("Number of Blocks : " + no_of_blocks2);
        System.out.println("***********************************************************");
        System.out.println("Enter no of blocks to be audited:");
        int nu=Integer.parseInt(br.readLine());
        actualFileNames1=new String[Integer.parseInt(no_of_blocks2)];
        actualFileNames1=b.hashed;
        actualhash=new String[Integer.parseInt(no_of_blocks2)];
        actualhash=b.conhash;
        int [] a=new int[nu];
        for (int yo=0;yo<nu;yo++)
        {
            System.out.println("Enter the "+yo+" block number to audited:");
            a[yo]=Integer.parseInt(br.readLine());
        }
        File metaInf2 = new File(fileName2+"_meta_file2.csv");
        /*try
        {
            FileReader fileReader = new FileReader(metaInf2);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuffer = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) 
            {
                String[] lineCSV = line.split(",");
                
                actualFileNames1[g1] = lineCSV[2];
               
                g1++;
               
            }
            fileReader.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }*/
        //File metaInf3 = new File(fileName2+"_meta_file2.csv");
        /*try
        {
            FileReader fileReader = new FileReader(metaInf3);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuffer = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) 
            {
                String[] lineCSV = line.split(",");
                
                actualhash[f1] = lineCSV[3];
               
                g1++;
                
            }
            fileReader.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }*/
        String[] blockNames=new String[nu];
        int cnt1=0;
        int u=0;
        for(int x=0;x<a.length;x++)
        {   
            u=a[x]-1;
            blockNames[cnt1]=actualFileNames1[u];
            //System.out.println(blockNames[cnt1]);
            cnt1++;
        }
        ObjectOutputStream out2 = new ObjectOutputStream(sock2.getOutputStream());
        out2.writeObject(userId2);
        out2.writeObject(fileName2);
        out2.writeObject(no_of_blocks2);
        out2.writeObject(blockNames);
        ObjectInputStream ois2=new ObjectInputStream(sock2.getInputStream());
        String [] elements=(String[])ois2.readObject();
        String [] hashes= actualhash;
        String [] treeo=new String[Integer.parseInt(no_of_blocks2)];
        for(int yu=0;yu<a.length;yu++)
        {
            int order=a[yu]-1;
            hashes[order]=elements[yu];
        }
        
        for(int yg=0;yg<hashes.length;yg++)
        {
            int gu=b.order[yg];
            //System.out.println(gu);
            treeo[yg]=hashes[gu];
            //System.out.println(treeo[yg]);
        }
    int u2=0;
    int c2=(int) Math.sqrt(b.blcksiz);
    String []trelem2=new String[c2];
    StringBuilder s11=new StringBuilder();
    StringBuilder s12=new StringBuilder();
    int i=0,j=0;
        //System.out.println(treeo.length);
    while(i<b.blcksiz)
    {
        while(u2<c2)
        {
            
            s11.append(treeo[i]);
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
    int culprit=0;
    if(rootelem2.equals(b.rootelem))
    {
        System.out.println("..........................VERIFIED......................");
    }
    else
    {
        System.out.println("******************************RESPONSIVE CHANGES FROM USER****************************");
        for(int i2=0;i2<trelem2.length;)
        {
            if(!(b.trelem[i2].equals(trelem2[i2])))
            {
               culprit=i2+1;
               break;
            }
            else
            {
                i2++;
            }
        }
            System.out.println("***********************************************************");

        System.out.println("THE ERROR TREE ELEMENT:"+culprit);
        //int end=(culprit*c2);
        //int start=end-c2;
        //System.out.println("THE start:"+start);
       // System.out.println("THE End:"+end);
        for(int v=0;v<b.shufconttree.length;)
        {
            if((b.shufconttree[v].equals(treeo[v])))
            {
                v++;
            }
            else{
                int h=b.order[v];
                    System.out.println("***********************************************************");

                System.out.println("THE order:"+h);
                String blockn=b.hashed[h];
                    System.out.println("***********************************************************");

                System.out.println("ERROR hash value:"+treeo[v]);
                System.out.println("ERROR block name:"+blockn);
                    System.out.println("***********************************************************");
    System.out.println("***********************************************************");

                System.out.println("Sending the block which is attacked:"+b.shufconttree[v]);
                    System.out.println("***********************************************************");
    System.out.println("***********************************************************");

                out2.writeObject("yes");
                out2.writeObject(blockn);
                out2.writeObject(b.shufconttree[v]);
                System.out.println("Error is corrected by the server Successfully...............................");
                break;
            }
        }
    }
            break;
        case 4:
            Socket sock4 = new Socket("127.0.0.1", 3004);
        System.out.println("Enter The userId:");
        String userId4 = br.readLine();
        System.out.println("Enter The filename:");
        String fileName4 = br.readLine();
        System.out.println("enter the no of blocks:");
        String no_of_blocks4 = br.readLine();
        System.out.println("***********************************************************");
        System.out.println("\t CLOUD FRAMEWORK -FULL AUDIT");
        System.out.println("***********************************************************");
        System.out.println("User ID          : " + userId4);
        System.out.println("Name of the File : " + fileName4);
        System.out.println("Number of Blocks : " + no_of_blocks4);
        System.out.println("***********************************************************");
        
        ObjectOutputStream out3 = new ObjectOutputStream(sock4.getOutputStream());
        out3.writeObject(userId4);
        out3.writeObject(fileName4);
        out3.writeObject(no_of_blocks4);
        out3.writeObject(b.hashed);
        ObjectInputStream ois4=new ObjectInputStream(sock4.getInputStream());
        //String[] retrievedMurMurHash  = (String[])ois4.readObject();            

        System.out.println("   Retrieved audit Value");
        System.out.println("***********************************************************");
        //for(int i=1;i<retrievedMurMurHash.length;i++)
        //    System.out.println(retrievedMurMurHash[i]);
       
        String full1=(String)ois4.readObject();
        System.out.println("Final Audit Value : "+full1);
        System.out.println("***********************************************************");
        if(b.audit.equals(full1))
        {
            System.out.println("\t INTEGRITY VERIFIED..!");
            String yes1="yes";
                    out3.writeObject(yes1);
            System.out.println("***********************************************************");
        }
        else
                    System.out.println("******************************RESPONSIVE CHANGES FROM USER****************************");
                        String yes1="no";
                    out3.writeObject(yes1);
                    out3.writeObject(b.shufconttree);
                    out3.writeObject(b.shufhashtree);
                    out3.writeObject(b.shufcontname);
                    String result=(String)ois4.readObject();
                    System.out.println(".......................................................................................");
                    System.out.println(result+"..............Response from TPA........................Server Accomplished.......");
           
        break;
        case 5:
        String[] actualFileNames5,actualhash5;
        int g5=0,f5=0;
        Socket sock5 = new Socket("127.0.0.1", 3006);
        System.out.println("Enter The userId:");
        String userId5 = br.readLine();
        System.out.println("Enter The filename:");
        String fileName5 = br.readLine();
        System.out.println("enter the no of blocks:");
        String no_of_blocks5 = br.readLine();
        System.out.println("***********************************************************");
        System.out.println("\t CLOUD FRAMEWORK -Partial AUDIT");
        System.out.println("***********************************************************");
        System.out.println("User ID          : " + userId5);
        System.out.println("Name of the File : " + fileName5);
        System.out.println("Number of Blocks : " + no_of_blocks5);
        System.out.println("***********************************************************");
        System.out.println("Enter no of blocks to be audited:");
        int nu5=Integer.parseInt(br.readLine());
        actualFileNames5=new String[Integer.parseInt(no_of_blocks5)];
        actualFileNames5=b.hashed;
        actualhash=new String[Integer.parseInt(no_of_blocks5)];
        actualhash=b.conhash;
        int [] a5=new int[nu5];
        for (int yo=0;yo<nu5;yo++)
        {
            System.out.println("Enter the "+yo+" block number to audited:");
            a5[yo]=Integer.parseInt(br.readLine());
        }
        File metaInf5 = new File(fileName5+"_meta_file2.csv");
        /*try
        {
            FileReader fileReader = new FileReader(metaInf2);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuffer = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) 
            {
                String[] lineCSV = line.split(",");
                
                actualFileNames1[g1] = lineCSV[2];
               
                g1++;
               
            }
            fileReader.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }*/
        //File metaInf3 = new File(fileName2+"_meta_file2.csv");
        /*try
        {
            FileReader fileReader = new FileReader(metaInf3);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuffer = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) 
            {
                String[] lineCSV = line.split(",");
                
                actualhash[f1] = lineCSV[3];
               
                g1++;
                
            }
            fileReader.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }*/
        String[] blockNames5=new String[nu5];
        int cnt5=0;
        int u5=0;
        for(int x=0;x<a5.length;x++)
        {   
            u5=a5[x]-1;
            blockNames5[cnt5]=actualFileNames5[u5];
            //System.out.println(blockNames[cnt1]);
            cnt5++;
        }
        String [] hashes5= actualhash;
        ObjectOutputStream out5 = new ObjectOutputStream(sock5.getOutputStream());
        out5.writeObject(userId5);
        out5.writeObject(fileName5);
        out5.writeObject(no_of_blocks5);
        out5.writeObject(blockNames5);
        out5.writeObject(b.order);
        out5.writeObject(hashes5);
        out5.writeObject(a5);
        ObjectInputStream ois5=new ObjectInputStream(sock5.getInputStream());
        /*String [] elements5=(String[])ois2.readObject();
        String [] treeo5=new String[Integer.parseInt(no_of_blocks2)];
        for(int yu=0;yu<a5.length;yu++)
        {
            int order=a5[yu]-1;
            hashes[order]=elements[yu];
        }
        
        for(int yg=0;yg<hashes.length;yg++)
        {
            int gu=b.order[yg];
            //System.out.println(gu);
            treeo[yg]=hashes[gu];
            //System.out.println(treeo[yg]);
        }
    int u2=0;
    int c2=(int) Math.sqrt(b.blcksiz);
    String []trelem2=new String[c2];
    StringBuilder s11=new StringBuilder();
    StringBuilder s12=new StringBuilder();
    int i=0,j=0;
    //System.out.println(treeo.length);
    while(i<b.blcksiz)
    {
        while(u2<c2)
        {
            s11.append(treeo[i]);
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
    System.out.println("***********************************************************");*/
    String rootelem5=(String)ois5.readObject();
        if(rootelem5.equals(b.rootelem))
    {
        out5.writeObject("yes");
        System.out.println("..........................VERIFIED......................");
    }
    else
    {
        System.out.println("******************************RESPONSIVE CHANGES FROM USER****************************");
        out5.writeObject("no");
        out5.writeObject(b.trelem);
        out5.writeObject(b.shufconttree);
        out5.writeObject(b.order);
        out5.writeObject(b.hashed);
        String result1=(String)ois5.readObject();
        if(result1.equals("Corrected"))
        {
                System.out.println("***********************************************************");

             System.out.println("Error is rectified and localised for a single block.............................");
                 System.out.println("***********************************************************");
    System.out.println("***********************************************************");
    System.out.println("***********************************************************");
    System.out.println("***********************************************************");
    System.out.println("***********************************************************");

             System.out.println("Check for full integrity by again goin thru the process..........................");
        }
        else
        {
             System.out.println("Error is localised.............................");
        }
        
    }
            break;
    }
    }   
}
}



