package basic_java.collectionsBasics;

import java.util.*;

public class Hashmaps {
    public static void main(String[] args)
    {
        Map<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(1, 2); // put(key, value)
        hashMap.get(1);
        hashMap.containsKey(1);
        hashMap.remove(1); 
    }
}
