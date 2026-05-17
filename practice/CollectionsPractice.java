package practice;

import java.util.*;

public class CollectionsPractice {
    public static void main(String[] args){
        //arraylist
        
        // List<String> list = new ArrayList<>();
        // list.add("hello");
        // list.add("world");
        // System.out.println(list);
        // list.add(2, "java");
        // System.out.println(list);
        // System.out.println(list.contains("hello"));
        // var removed = list.remove(1);
        // System.out.println(removed);
        // System.out.println(list.size());

        //linkedlist
        List<String> linkedList = new LinkedList<>();
        linkedList.add("hello");
        System.out.println(linkedList);
        linkedList.remove(0);
        System.out.println(linkedList);
        linkedList.add(0, "world");
        System.out.println(linkedList);

        Set<String> set = new HashSet<>();
        set.add("hello");
        set.remove("hello");
        System.out.println(set);

        
    }
}
