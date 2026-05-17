package basic_java;

public class Loops {
    public static void main(String[]  args)
    {
        
        System.out.println("Hello from for loop");
        for (int i = 0; i < 5; i++)
        {
            System.out.println(i);
        }

        int j = 0;
        
        System.out.println("Hello from while loop");
        while (j < 5)
        {
            System.out.println(j);
            j++;
        }

        j = 0;
        System.out.println("Hello from do-while loop");
        do
        {
            System.out.println(j);
            j++;
        } while (j < 5);

        System.out.println("Hello from for-each loop");
        int[] arr = {1, 2, 3, 4, 5};

        for (int num : arr)
        {
            System.out.println(num);
        }
    }    
}
