import java.util.*;
public class HammingCode {

    public static void main(String args[]){

        int a[]=new int[8];
        int r[]=new int[8];
        int c1,c2,c3,c;
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the 4 bits");

        a[7]=sc.nextInt();
        a[6]=sc.nextInt();
        a[5]=sc.nextInt();
        a[3]=sc.nextInt();

        a[1]=a[3]^a[5]^a[7];
        a[2]=a[3]^a[6]^a[7];
        a[4]=a[5]^a[6]^a[7];


        System.out.println("data to send");
        for(int i=1;i<a.length;i++){
            System.out.println(a[i]);
        }
        System.out.println("data received :");
        for(int i=1;i<a.length;i++){
            r[i]=sc.nextInt();
        }

        c1=r[1]^r[3]^r[5]^r[7];
        c2=r[2]^r[3]^r[6]^r[7];
        c3=r[4]^r[5]^r[6]^r[7];

        c=c3*4+c2*2+c1;

        if(c==0){
            System.out.println("no error detected");
        }else{
            System.out.println("error deteced at position :"+c);
            if(r[c]==0){
                r[c]=1;
            }
            else{
                r[c]=0;
            }
            for(int i=1;i<r.length;i++){
                System.out.println(r[i]);
            }
        }
        




    }
    
}
