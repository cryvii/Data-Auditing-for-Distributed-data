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
/*Then compute hash value for each block using the following hash algorithm and store the hash value using hashed liked tree*/

/*HASHED LINKED TREE WITH MURMUR HASH ALGORITHM*/

import java.io.*;
import java.math.BigInteger;
import java.util.*;
class murmur{

public static int h=0;
public static LinkedList l1 = new LinkedList();
 public static LinkedList l2 = new LinkedList();
public static LinkedList  l3 = new LinkedList();
public static LinkedList l4 = new LinkedList(); 
public static void addele(String[] arr, LinkedList l,int size)
{
BigInteger i1= new BigInteger("000000", 16);
for(int i=0;i<size;i++)
{
l.add(arr[h]);
BigInteger i2 = new BigInteger(arr[h], 16);
i1 = i1.xor(i2);
h++;
    } 
String hash=i1.toString(16);
l.addFirst(hash);
}
	
	public static int hash32( String s) {
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

		return h;
	}


public static void main(String ar[])throws Exception{
int ex=0;
String str="";
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
System.out.println("Enter the folder");
String file=br.readLine();
File dir = new File(file);
String f1;
int fi=0;
String fnam[]=new String[10000];
if(dir.exists())
{ 
 for(File f : dir.listFiles())
  {
    fnam[fi]=f.getName();
      FileInputStream fis = new FileInputStream(f);
      byte[] data = new byte[fis.available()];
      fis.read(data);
      String text = new String(data);
//System.out.println("a"+text);
      int r=hash32(text);
      ex=ex^r;
 str+= Integer.toHexString(r);
fi++;
}
}
System.out.println(str.length());
System.out.println("Enter the file for audit");
f1=br.readLine();
f1=dir+"\\"+f1;
File ff=new File(f1);
FileWriter fw=new FileWriter(ff);
BufferedWriter bw=new BufferedWriter(fw);


//Hashed Linked Tree
  System.out.println("Enter the number of blocks to be hashed");
	int blc=Integer.parseInt(br.readLine());
	int rem=blc/4;
	int rem1=blc%4;
	int z=0;
String arr[]=new String[blc];
for(int i=0;i<blc;)
{
arr[i]=str.substring(z,z+8);
System.out.println(arr[i]);
z=z+8;
i++;
} 
String hash=Integer.toHexString(ex);
bw.write(hash);
bw.close();
fw.close();
if(rem1==0)
{
addele(arr,l1,rem);
addele(arr,l2,rem);
addele(arr,l3,rem);
addele(arr,l4,rem);
}
if(rem1==1)
{
addele(arr,l1,(rem+1));
addele(arr,l2,rem);
addele(arr,l3,rem);
addele(arr,l4,rem);
}
if(rem1==2)
{
addele(arr,l1,(rem+1));
addele(arr,l2,(rem+1));
addele(arr,l3,rem);
addele(arr,l4,rem);
}
if(rem1==3)
{
addele(arr,l1,(rem+1));
addele(arr,l2,(rem+1));
addele(arr,l3,(rem+1));
addele(arr,l4,rem);
}
//System.out.println("list 1\n");
 
Iterator ir=l1.iterator();
int ffi=0;
while(ir.hasNext())
{
System.out.print(ir.next());
System.out.print("->");

if(ffi!=l1.size()-1)

{

System.out.print(fnam[ffi]);
System.out.print("->"+" ");

ffi++;

}
}
System.out.print("\n\n");
//System.out.println("list 2\n");
Iterator ir1=l2.iterator();
while(ir1.hasNext())
{
System.out.print(ir1.next());
System.out.print("->"+" ");

if(ffi!=l1.size()+l2.size()-2)

{

System.out.print(fnam[ffi]);
System.out.print("->"+" ");

ffi++;

}

}
System.out.print("\n\n");
//System.out.println("list 3\n");
Iterator ir2=l3.iterator();
while(ir2.hasNext())
{
System.out.print(ir2.next());
System.out.print("->"+" ");

if(ffi!=l1.size()+l2.size()+l3.size()-3)

{

System.out.print(fnam[ffi]);
System.out.print("->"+" ");

ffi++;

}

}
System.out.print("\n\n");
//System.out.println("list 4\n");
Iterator ir3=l4.iterator();
while(ir3.hasNext())
{
System.out.print(ir3.next());
System.out.print("->"+" ");

if(ffi!=l1.size()+l2.size()+l3.size()+l4.size()-4)

{

System.out.print(fnam[ffi]);
System.out.print("->"+" ");

ffi++;

}
}
}
}