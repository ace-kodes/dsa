package basic_java;

public class Methods 
{
    public static void main(String[] args) 
    {
        Methods methods = new Methods();
        int sum = methods.calculateSum(5, 10);
        System.out.println("The sum is: " + sum);
    }

    public int calculateSum(int a, int b)
    {
        return a + b;
    }
}
