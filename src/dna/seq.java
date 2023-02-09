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
public class seq {
    String s[];
    String acid[]=new String[256];
    String s1[];
    int n;
    public seq(int a){
        this.s = new String[]{"P", "S", "T", "C", "L", "P", "H", "A", "I", "T", "L", "S", "U", "A", "G", "T"};
        n=a-1;
        this.s1 = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "!", "@", "#", "$", "%", "^", "&", "(", ")", "_", "=", "{", "[", "}", "]", "|", ":", ";", "+", "-", "<", ">", "?", ".", ",", "\\", "~", "`", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    }
    public void amino()
    {
        int k=n, i=0,c=0;
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
                    c++;
                    System.out.println(acid[i]);
                    i++;
                    k++;
                    if(c==16)
                    {
                        break;
                    }
                    else if(c==32)
                    {
                        break;
                    }
                    else if(c==48)
                        break;
                    else if(c==64) 
                    {
                            c=0;
                            break;
                    }
                    }
             } 
        }
    }       

    void print()
    {
        for(int i=0;i<256;i++)
        {
            System.out.println(acid[i]);
        }
    }
    public static void main(String args[])
    {
        seq ss=new seq(4);
        ss.amino();
        System.out.println("*****************");
        //ss.print();
    }
    
}

