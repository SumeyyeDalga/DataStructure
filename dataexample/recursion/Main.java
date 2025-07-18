public class Main{
    public static int gcdRec(int m,int n){
        if(m%n==0){
            return n;
        }
        else if(m<n){
            return gcdRec(n,m);
        }
        else{
            return gcdRec(n,m%n);
        }


    }
    public static void main(String[] args) {
        int result=gcdRec(15,10);
        System.out.print(result);
    }
}