package basic_java.collectionsBasics;

public class Arrays {
    
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int[] fixedSizeArray = new int[5]; //initialized with zeroes if nothing provided
        
        for (int i = 0; i < arr.length; i++)
        {
            System.out.println(arr[i]);
        }

        for (int i = 0; i < fixedSizeArray.length; i++)
        {
            System.out.println(fixedSizeArray[i]);
        }
    }
}
