/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elgamal_algo;
//my commit
//meghna commit
import javax.swing.JOptionPane;

/**
 *
 * @author Meghna
 */
public class ElGamal_Algo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        long n, p, a, b;
        
        n = 1201;
        p = 83; // some random number between 1 and n-1
        a = 325; //randomly generated secret key
        b = fastexp (p, a, n);
        
        System.out.println("B makes N (prime), p(primative root) and b public: " + n + " " + p + " " + b );
                
        long msg= Integer.parseInt(JOptionPane.showInputDialog("Enter the message to be sent: "));
        
        long k = 654;
        
        long r = fastexp(n, k, p);
        long t = (fastexp(b,k,p)*msg)%p;
        
        System.out.println("A calculates r and t and sends (r,t) to B:  " + r + " " + t);
        
        long dec = (t * fastexp(r, -a, p))%p;
        
        System.out.println("Decrypted Msg  " + msg);
    }
    
    static long fastexp(long m, long e, long n)
    {
        long c=1;
        long a=1;
        long[] expVal=new long[100];
        
        long prevVal=0;
        long currentExp=0;
        long currentVal=0;
        long finalVal=1;
        
        long[] values=new long[100];
        int count=0;
        
        while(currentExp<=e)
        {
            if(count==0||count==1)
            {
                currentVal=(long)Math.pow(m, currentExp)%n;
                
            }
            else
            {
                currentVal=(long)(prevVal*prevVal)%n;
            }
            prevVal=currentVal;
            values[count]=currentVal;
            count++;
            if(currentExp==0)
                currentExp=1;
            else
            currentExp=2*currentExp;
            
        }
        
        while(e>Math.pow(2, a))
        {
            a++;
        }
        
        if(e!=Math.pow(2, a))
            a--;
        
        count=0;
        
        for(long i=e;i>0;count++)
        {
           expVal[count] = a;
           i-=Math.pow(2, a);
           if(i==0)
               break;
           a--;
           while(i<Math.pow(2, a))
               a--;
        }
        
        for(int i=0;i<=count;i++)
        {
            finalVal*=values[(int)(expVal[i]+1)];
        }
        
        c=finalVal%n;
        
        return c;
    }
    
    
}
