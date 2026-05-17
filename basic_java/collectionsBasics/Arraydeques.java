package basic_java.collectionsBasics;

import java.util.*;

public class Arraydeques {
    public static void main(String[] args){
        Deque<Integer> deq = new ArrayDeque<>();
        // for stack
        deq.push(1);
        deq.pop();
        
        //queue
        deq.add(2);
        deq.poll();
        deq.peek();
    }    
}
