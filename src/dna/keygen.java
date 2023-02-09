/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dna;
import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
/**
 *
 * @author tce
 */
public class keygen {
    char array[];
    int arr;
     StringBuilder strn = new StringBuilder();
     String sn,sxor,dsxor,dsxor1,dnatext,decr;
    String str,left,right,dec;
    String s[];
    String acid[]=new String[256];
    String s1[];
    char result[]=new char[8];
    char rev[]=new char[8];
    char a[]=new char[4];
    char b[]=new char[4];
    char seq1c[]=new char[4];
    char seq2c[]=new char[4];
    String c[]=new String[16];
    String d[];
    //A-00 C-01 G-10 T-11
    String seq1;
    String seq2;
    String cipher,d1,d2;
    String seq1bin;
    String seq2bin;
    String seq1bin1[]=new String[4];
    String seq2bin1[]=new String[4];
    String seqcon;
    boolean xnorarray[],xnora[], xnorarray1[],xnoraa[];
    int n;
    BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
    public keygen(String s1, String s2,int a)
    {   
        this.s = new String[]{"P", "S", "T", "C", "L", "P", "H", "A", "I", "T", "L", "S", "U", "A", "G", "T"};
        n=a-1;
        this.s1 = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "!", "@", "#", "$", "%", "^", "&", "(", ")", "_", "=", "{", "[", "}", "]", "|", ":", ";", "+", "-", "<", ">", "?", ".", ",", "\\", "~", "`", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        this.d = new String[256];
        seq1=s1;
        seq1c=s1.toCharArray();
        seq2=s2;
        seq2c=s2.toCharArray();
        str = s1.concat(s2);
        String reverse = new StringBuffer(str).reverse().toString();
        rev=reverse.toCharArray();
    }
    public void transc()
    {
        for(int i=0;i<8;i++)
        {
            if(rev[i]=='T')
                rev[i]='U';
        }
    }
    public void transl()
    {
        for(int i=0; i<8;i++)
        {
            if(rev[i]=='A')
                rev[i]='U';
            else if(rev[i]=='U')
                rev[i]='A';
            else if(rev[i]=='G')
                rev[i]='C';
            else if(rev[i]=='C')
                rev[i]='G';
        }
    }
    public void four()
    {
        for(int j=0,i=0; i<8;i++)
        {
            if(i<4)
                a[i]=rev[i];
            else
            {
                b[j]=rev[i];
                j++;
                if(j>3)
                    break;
            }
        }
        int i=0;
               for(int j=0;j<4;j++)
               {
                   for(int k=0;k<4;k++)
                   {
                        StringBuilder sb = new StringBuilder();
                        sb.append(a[j]);
                        sb.append(b[k]);
                        c[i]= sb.toString();
                        i++;
                   }
               }
    }
    public void sixteen()
    {
        int i=0;
               for(int j=0;j<16;j++)
               {
                   for(int k=0;k<16;k++)
                   {
                        d[i]= c[j]+(c[k]);
                        //System.out.println(i);
                        //System.out.println(d[i]);
                        i++;
                   }
                }
            //   System.out.println(i);
    }
    public void amino()
    {
        int k=n, i=0,c1;
        c1 = 0;
        while(i<256)
        {
            for(int j=0;j<16;j++)
            {
                 while(k<=64)
                    {
                    if(k==64)
                    {
                        k=0;
                    }
                    acid[i]=s[j].concat(s1[k]);
                    c1++;
                    
                    //System.out.println(i);
                    //System.out.println(acid[i]);
                    i++;
                    k++;
                    if(c1==16)
                    {
                        break;
                    }
                    else if(c1==32)
                    {
                        break;
                    }
                    else if(c1==48)
                        break;
                    else if(c1==64) 
                    {
                            c1=0;
                            break;
                    }
                    }
             } 
        }
    }
    boolean xnor(int a, int b) 
    {
        return a==b;
    }
     boolean xor(int a, int b) 
    {
        return !(a==b);
    }
    public void ReadFile (String fname)throws Exception
    {
        for(int i=0;i<4;i++)
        {
            if(seq1c[i]=='A')
            {
                seq1bin1[i]="00";
            }
            else if(seq1c[i]=='C')
                    {
                         seq1bin1[i]="01";
                    }
             else if(seq1c[i]=='G')
             {
                  seq1bin1[i]="10";
             }
            else if(seq1c[i]=='T')
            {
                 seq1bin1[i]="11";
            }
        }
        StringBuilder sbf = new StringBuilder();
         if(seq1bin1.length > 0){
                       
                        sbf.append(seq1bin1[0]);
                        for(int i=1; i < seq1bin1.length; i++){
                                sbf.append(seq1bin1[i]);
                        }
                       
                }
         seq1bin=sbf.toString();
        for(int i=0;i<4;i++)
        {
            if(seq2c[i]=='A')
            {
                seq2bin1[i]="00";
            }
            else if(seq2c[i]=='C')
                    {
                         seq2bin1[i]="01";
                    }
             else if(seq2c[i]=='G')
             {
                  seq2bin1[i]="10";
             }
            else if(seq2c[i]=='T')
            {
                 seq2bin1[i]="11";
            }
        }
        StringBuilder sbf1 = new StringBuilder();
         if(seq2bin1.length > 0){
                       
                        sbf1.append(seq2bin1[0]);
                        for(int i=1; i < seq2bin1.length; i++){
                                sbf1.append(seq2bin1[i]);
                        }
                       
                }
         seq2bin=sbf1.toString();
        seqcon=seq1bin.concat(seq2bin);
        FileReader fileReader = new FileReader(fname);
        String fileContents = "";
        int i ;
        while((i =  fileReader.read())!=-1)
        {
            char ch = (char)i;
            fileContents = fileContents + ch; 
        }
        array=fileContents.toCharArray();
        int arr1[]=new int[array.length];
        String a1[]=new String[array.length];
        for(i=0;i<array.length;i++)
        {
            arr1[i]=(int)array[i];
            //a[i]= format.format(arr1[i]);
          //  System.out.println(arr1[i]);
            a1[i] = new BigInteger(Integer.toString(arr1[i])).toString(2);
            switch(8-a1[i].length())
            {
                case 0 :    break;
                case 1 : a1[i] = "0" + a1[i];break;
                case 2 : a1[i] = "00" + a1[i];break;
                case 3 : a1[i] = "000" + a1[i];break;
                case 4 : a1[i] = "0000" + a1[i];break;
                case 5 : a1[i] = "00000" + a1[i];break;
                case 6 : a1[i] = "000000" + a1[i];break;
                case 7 : a1[i] = "0000000" + a1[i];break;
            }
         //System.out.println(a1[i]);
         strn.append(a1[i]);
        }
        sn=strn.toString();
        //System.out.println("string");
       // System.out.println(sn);
        int n1=sn.length();
        //System.out.println("n"+n1);
        xnorarray=new boolean[n1];
        int o;
        int r,y2;
        o=0;
        if(n1%16==0)
        {
            for(int t=0; t<n1; t++)
            {
                //System.out.println("inside mod");
                r=sn.charAt(t);
                y2=seqcon.charAt(o);                
                xnorarray[t]=xnor(r,y2);
                //System.out.println("xnor");
                //System.out.println(xnorarray[t]);
                o++;
                if(o==16)
                {
                    o=0;
                }
                
            }
            StringBuilder sn12=new StringBuilder();
        
     for(int y=0;y<xnorarray.length;y++)
     {
         if(xnorarray[y]==true)
         {
             sn12.append("1");
         }
         else
         {
             sn12.append("0");
         }
         sn=sn12.toString();
     }
     //System.out.println("enc xnor ");
     //System.out.println(sn);
        }
        else
        {
            int m=n1%16;
            switch(m)
            {
                case 0 :    break;
                case 15 : sn = sn+"0";break;
                case 14 : sn = sn+"00" ;break;
                case 13 : sn = sn+"000" ;break;
                case 12 : sn = sn+"0000" ;break;
                case 11 : sn = sn+"00000" ;break;
                case 10 : sn = sn+"000000" ;break;
                case 9 : sn =sn+ "0000000" ;break;
                case 8 : sn =sn+ "00000000" ;break;
                case 7 : sn =sn+ "000000000" ;break;
                case 6 : sn =sn+ "0000000000" ;break;
                case 5 : sn =sn+ "00000000000";break;
                case 4 : sn = sn+"000000000000" ;break;
                case 3 : sn =sn+ "0000000000000" ;break;
                case 2 : sn =sn+ "00000000000000";break;
                case 1 : sn= sn+"000000000000000"; break;
            }
           // System.out.println("string app: "+sn);
            int n2=sn.length();
            xnora=new boolean[n2];
            for(int t=0; t<n2; t++)
            {
                r=sn.charAt(t);
                y2=seqcon.charAt(o);
                
                xnora[t]=xnor(r,y2);
                //System.out.println("xnor");
                //System.out.println(xnorarray[t]);
                o++;
                if(o==16)
                {
                    o=0;
                }
            }
        
            StringBuilder sn12=new StringBuilder();
        
     for(int y=0;y<xnora.length;y++)
     {
         if(xnora[y]==true)
         {
             sn12.append("1");
         }
         else
         {
             sn12.append("0");
         }
         sn=sn12.toString();
     }
     //System.out.println("enc xnor ");
     //System.out.println(sn);
        }
     
    }
    void half()
    {
        int n3=(sn.length()/2)-1;
        boolean xorleft[]=new boolean[n3+1];
        boolean xorright[]=new boolean[n3+1];
        int f=0,z=0;
        //System.out.println(seq1bin);
        //System.out.println(seq2bin);
        for(int u=n3;u>=0;u--)
        {
            xorleft[z]=xor(seq1bin.charAt(f),sn.charAt(u));
            //System.out.println("left");
            //System.out.println(xorleft[z]);
            z++;
            f++;
            if(f==8)
            {
                f=0;
            }
        }
         StringBuilder sn123=new StringBuilder();
        
     for(int y=0;y<xorleft.length;y++)
     {
         if(xorleft[y]==true)
         {
             sn123.append("1");
         }
         else
         {
             sn123.append("0");
         }
         
     }
        z=0;
         for(int u=sn.length()-1;u>n3;u--)
        {
            xorright[z]=xor(seq2bin.charAt(f),sn.charAt(u));
            //System.out.println("right");
            //System.out.println(xorright[z]);
            z++;
            f++;
            if(f==8)
            {
                f=0;
            }
        }
          for(int y=0;y<xorright.length;y++)
     {
         if(xorright[y]==true)
         {
             sn123.append("1");
         }
         else
         {
             sn123.append("0");
         }
         sxor=sn123.toString();
     }
       //System.out.println("enc xor");
       //System.out.println(sxor);
    }
    void mrna()
    {
        int t=2;
        String temp;
        StringBuilder dnas = new StringBuilder();
        for(int i=0;i<sxor.length();)
        {
            
            temp=sxor.substring(i, t);
            //System.out.println(temp);
            if(null != temp)
            switch (temp) {
                case "00":
                    dnas.append("A");
                    break;
                case "01":
                    dnas.append("C");
                    break;
                case "10":
                    dnas.append("G");
                    break;
                case "11":
                    dnas.append("U");
                    break;
                   
            }
        i=i+2;
        t=t+2;
        }
        dnatext=dnas.toString();
        //System.out.println(dnatext);
        
    }
    void lastpro() throws FileNotFoundException
    {
        //long l=System.nanoTime();
        int t=4;
        String temp;
        StringBuilder dnas = new StringBuilder();
        for(int i=0;i<dnatext.length();)
        {
            temp=dnatext.substring(i, t);
            //System.out.println(temp);
            //d.stream().filter(d -> d.getTags().contains(temp)).findFirst();
            int index = Arrays.asList(d).indexOf(temp);
            dnas.append(acid[index]);
            /*for(int j=0;j<256;j++)
            {
            if(temp.equals(d[j]))
            {
                dnas.append(acid[j]);
            }
            }*/
        t+=4;
        i+=4;
        }
        //long p=System.nanoTime();
        //System.out.println(p-l);
        cipher=dnas.toString();
        //System.out.println(cipher);
//         System.out.println("Enter file name");
//        String decrypt=br.readLine();
        PrintWriter writer = new PrintWriter("test/encrypt.txt");
         writer.println(cipher);
         writer.close();
    }
     void decrypt()
    {
          StringBuilder dnas = new StringBuilder();
       int t=2;
       String temp;
       for(int i=0;i<cipher.length();)
       { 
           temp=cipher.substring(i, t); 
           //System.out.println(temp);
           int index = Arrays.asList(acid).indexOf(temp);
           dnas.append(d[index]);
           /*for(int j=0;j<256;j++)
            {
            if(temp.equals(acid[j]))
            {
                dnas.append(d[j]);
            }
            }*/
        t=t+2;
        i=i+2;
        }
         d1=dnas.toString();
       // System.out.println(d1);
        
       }
        void mrnatodna()   
          { 
                      
               d1=d1.replace('U', 'T');
               //System.out.println(d1);
                   }
        void dnatobin()
    {
        char temp;
        StringBuilder dnas = new StringBuilder();
        for(int i=0;i<d1.length();i++)
        {
            
            temp=d1.charAt(i);
            //System.out.println(temp);
            if(temp=='A') {
                    dnas.append("00");
            }
               else if(temp=='C')
                    dnas.append("01");
               else if(temp=='G')
                    dnas.append("10");
               else if(temp=='T')
                    dnas.append("11");
                   
            }
    
        d2=dnas.toString();
        //System.out.println(d2);
        
    }
     void sephalf()
     {
        int n3=(d2.length()/2)-1;
        boolean xorleft[]=new boolean[n3+1];
        boolean xorright[]=new boolean[n3+1];
        int f=7,z=0;
        //System.out.println(seq1bin);
        //System.out.println(seq2bin);
        for(int u=n3;u>=0;u--)
        {
            xorleft[z]=xor(seq1bin.charAt(f),d2.charAt(u));
            //System.out.println("left");
            //System.out.println(xorleft[z]);
            z++;
            f--;
            if(f==-1)
            {
                f=7;
            }
        }
         StringBuilder sn123=new StringBuilder();
        
     for(int y=0;y<xorleft.length;y++)
     {
         if(xorleft[y]==true)
         {
             sn123.append("1");
         }
         else
         {
             sn123.append("0");
         }
         
     }
        z=0;
         for(int u=d2.length()-1;u>n3;u--)
        {
            xorright[z]=xor(seq2bin.charAt(f),d2.charAt(u));
            //System.out.println("right");
            //System.out.println(xorright[z]);
            z++;
            f--;
            if(f==-1)
            {
                f=7;
            }
        }
          for(int y=0;y<xorright.length;y++)
     {
         if(xorright[y]==true)
         {
             sn123.append("1");
         }
         else
         {
             sn123.append("0");
         }
         dsxor=sn123.toString();
     }
          //System.out.println("dec xor:");
       //System.out.println(dsxor); 
     }
     void dxnor()
     {
         int n1=dsxor.length();
        xnorarray1=new boolean[n1];
        int o;
        int r,y2;
        o=0;
        if(n1%16==0)
        {
            for(int t=0; t<n1; t++)
            {
                r=dsxor.charAt(t);
                y2=seqcon.charAt(o);                
                xnorarray1[t]=xnor(r,y2);
                //System.out.println("xnor");
                //System.out.println(xnorarray[t]);
                o++;
                if(o==16)
                {
                    o=0;
                }
                
            }
            StringBuilder sn12=new StringBuilder();
        
     for(int y=0;y<xnorarray1.length;y++)
     {
         if(xnorarray1[y]==true)
         {
             sn12.append("1");
         }
         else
         {
             sn12.append("0");
         }
       dsxor1=sn12.toString();
     }
     //System.out.println("dec xnor:");
     //System.out.println(dsxor1);
        }
        else
        {
            int m=n1%16;
            switch(m)
            {
                case 0 :    break;
                case 15 : dsxor = dsxor+"0";break;
                case 14 : dsxor = dsxor+"00" ;break;
                case 13 : dsxor= dsxor+"000" ;break;
                case 12 : dsxor= dsxor+"0000" ;break;
                case 11 : dsxor = dsxor+"00000" ;break;
                case 10 : dsxor = dsxor+"000000" ;break;
                case 9 : dsxor=dsxor+ "0000000" ;break;
                case 8 : dsxor =dsxor+ "00000000" ;break;
                case 7 : dsxor =dsxor+ "000000000" ;break;
                case 6 :dsxor =dsxor+ "0000000000" ;break;
                case 5 : dsxor =dsxor+ "00000000000";break;
                case 4 : dsxor = dsxor+"000000000000" ;break;
                case 3 : dsxor =dsxor+ "0000000000000" ;break;
                case 2 : dsxor =dsxor+ "00000000000000";break;
                case 1 : dsxor= dsxor+"000000000000000"; break;
            }
            int n2=dsxor.length();
            xnoraa=new boolean[n2];
            for(int t=0; t<n2; t++)
            {
                r=dsxor.charAt(t);
                y2=seqcon.charAt(o);
                
                xnorarray[t]=xor(r,y2);
                //System.out.println("xnor");
                //System.out.println(xnorarray[t]);
                o++;
                if(o==16)
                {
                    o=0;
                }
            }
        StringBuilder sn12=new StringBuilder();
        
     for(int y=0;y<xnoraa.length;y++)
     {
         if(xnoraa[y]==true)
         {
             sn12.append("1");
         }
         else
         {
             sn12.append("0");
         }
       dsxor1=sn12.toString();
     }
    // System.out.println("dec xnor:");
     //System.out.println(dsxor1);
    }
     int count=0;
     int n3;
    for(int i=dsxor1.length()-1; i>0;i--)
    {
        
        if(dsxor1.charAt(i)=='0')
        {
            count++;
            if(count==8)
            {
                n3=dsxor1.length()-8;
                decr=dsxor1.substring(0, n3);
            }                
        }    
    }

    //System.out.println(decr);
    }
    void lastprde() throws FileNotFoundException, UnsupportedEncodingException, IOException
    {
        int e=((decr.length())/8);
        //System.out.println("e:"+e);
        int t=8;
        String temp;
        int a[]=new int[e+1];
        int j=0;
        for(int i=0;i<decr.length();)
        {
            temp=decr.substring(i, t);
            //System.out.println(temp);
            a[j]=Integer.parseInt(temp,2);
            //System.out.println(a[j]);
        j++;    
        t=t+8;
        i=i+8;
        }
        String c;
       StringBuilder sss=new StringBuilder();
        for(int i: a){
            c = Character.toString((char)i);
            //System.out.println(c);
            sss.append(c);
        }
        dec=sss.toString();
        //System.out.println(dec);
       PrintWriter writer = new PrintWriter("test/decrypt.txt");
         writer.println(dec);
         writer.close();

    }
    
    void print()
       {
           for(int i=0;i<256;i++)
           {
               System.out.println(d[i]);
           }
           System.out.println("acid table");
           for(int i=0;i<256;i++)
           {
               System.out.println(acid[i]);
           }
           
       }
      
    
public static void main(String args[])throws IOException, Exception
{
    
    BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
    System.out.println("enter the first");
    String s1=br.readLine();
    System.out.println("enter the second seq");
    String s2=br.readLine();
    System.out.println("enter the uniq colliding seq:");
    int a=Integer.parseInt(br.readLine());
    keygen k=new keygen(s1,s2,a);
    
    k.transc();
    k.transl();
    k.four();
    k.sixteen(); 
    k.amino();
    //k.print();
    
    k.ReadFile("test/a.txt");
    k.half();
    k.mrna();
    
    k.lastpro();
    
    
    k.decrypt();
    k.mrnatodna();
    k.dnatobin();
    k.sephalf();
    
    k.dxnor();
    
    k.lastprde();
    
   
}
}
