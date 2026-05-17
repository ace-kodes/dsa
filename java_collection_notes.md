# Java DSA — Complete Reference

---

# 1. Custom Classes

A custom class is a user-defined blueprint that bundles data and behavior. In DSA it's used to model nodes, graph edges, intervals, or any multi-field object passed into collections.

```java
class Node {
    int val;
    Node next;

    Node(int val) {
        this.val = val;
        this.next = null;
    }
}

class Interval {
    int start, end;
    Interval(int s, int e) { start = s; end = e; }
}
```

**DSA use:** Custom classes are passed into `PriorityQueue`, `TreeMap`, or `List` with a custom comparator.

```java
PriorityQueue<Interval> pq = new PriorityQueue<>((a, b) -> a.start - b.start);
pq.offer(new Interval(3, 5));
pq.offer(new Interval(1, 4));
// polls in order: Interval(1,4) → Interval(3,5)
```

---

# 2. Collection Interface

Java's `Collection` interface is the root of the collections framework. It defines core operations like `add()`, `remove()`, `size()`, `contains()`, and `iterator()` that all concrete classes inherit.

```
Collection
├── List         → ordered, index-based, duplicates allowed
├── Set          → no duplicates
└── Queue        → FIFO ordering
```

---

## 2a. List Interface

`List` is an ordered collection that allows duplicates and provides index-based access. Elements maintain insertion order.

```java
List<Integer> list = new ArrayList<>();   // dynamic array
List<Integer> list = new LinkedList<>();  // doubly linked list
```

**Common methods:**

| Method | Description | Time (ArrayList) |
|---|---|---|
| `add(e)` | Append to end | O(1) amortized |
| `add(i, e)` | Insert at index | O(n) |
| `get(i)` | Get by index | O(1) |
| `remove(i)` | Remove at index | O(n) |
| `set(i, e)` | Replace at index | O(1) |
| `size()` | Number of elements | O(1) |
| `contains(e)` | Check existence | O(n) |

---

### i. ArrayList

`ArrayList` is a resizable array. It provides O(1) random access and O(1) amortized appends. The most commonly used `List` in DSA.

```java
// Core Concept
Index:  [0][1][2][3][4]
Array:  [1][3][5][7][9]
         ↑           ↑
       get(0)      get(4)  → O(1)
```

**Declaration & Initialization:**

```java
ArrayList<Integer> list = new ArrayList<>();
ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));
ArrayList<Integer> list = new ArrayList<>(Collections.nCopies(5, 0)); // [0,0,0,0,0]
```

**Key Methods:**

```java
list.add(10);           // append → O(1)
list.add(1, 99);        // insert at index 1 → O(n)
list.get(0);            // → 10
list.set(0, 42);        // replace index 0 → O(1)
list.remove(0);         // remove by index → O(n)
list.remove(Integer.valueOf(99)); // remove by value → O(n)
list.size();
list.contains(42);      // O(n)
list.indexOf(42);       // first occurrence index
Collections.sort(list); // O(n log n)
Collections.reverse(list);
```

**DSA Use Cases:**

```java
// 1. Dynamic result list
public List<Integer> twoSum(int[] nums, int target) {
    List<Integer> result = new ArrayList<>();
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
        int complement = target - nums[i];
        if (map.containsKey(complement)) {
            result.add(map.get(complement));
            result.add(i);
            return result;
        }
        map.put(nums[i], i);
    }
    return result;
}

// 2. Adjacency list for graphs
List<List<Integer>> graph = new ArrayList<>();
for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
graph.get(0).add(1); // edge 0 → 1
graph.get(0).add(2); // edge 0 → 2
```

**Quick Cheat Sheet:**

```
add(e)      → O(1) amortized
add(i, e)   → O(n)
get(i)      → O(1)
set(i, e)   → O(1)
remove(i)   → O(n)
contains(e) → O(n)

⚠️  remove(int i) removes by INDEX
⚠️  remove(Integer.valueOf(x)) removes by VALUE
⚠️  Not thread-safe — use Collections.synchronizedList() if needed
```

---

### ii. LinkedList

`LinkedList` is a doubly linked list implementing both `List` and `Deque`. O(1) insertions/deletions at both ends, O(n) random access. Preferred when frequent insertions/deletions at head/tail are needed.

```java
// Core Concept
null ← [1] ⇄ [2] ⇄ [3] ⇄ [4] → null
        ↑                   ↑
      head               tail
  addFirst()           addLast()
  removeFirst()        removeLast()
```

**Declaration & Initialization:**

```java
LinkedList<Integer> ll = new LinkedList<>();
LinkedList<Integer> ll = new LinkedList<>(Arrays.asList(1, 2, 3));
```

**Key Methods:**

```java
// As List
ll.add(10);          // addLast → O(1)
ll.get(2);           // O(n) — traverse from head
ll.remove(0);        // remove index → O(n)

// As Deque (use these for O(1) head/tail ops)
ll.addFirst(5);      // O(1)
ll.addLast(99);      // O(1)
ll.removeFirst();    // O(1)
ll.removeLast();     // O(1)
ll.peekFirst();      // O(1)
ll.peekLast();       // O(1)
```

**DSA Use Cases:**

```java
// 1. Implement LRU Cache (manual doubly linked list + HashMap)
class LRUCache {
    LinkedList<int[]> list = new LinkedList<>(); // [key, val]
    Map<Integer, int[]> map = new HashMap<>();
    int cap;

    LRUCache(int capacity) { cap = capacity; }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        int[] node = map.get(key);
        list.remove(node);
        list.addFirst(node);
        return node[1];
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) list.remove(map.get(key));
        int[] node = {key, value};
        list.addFirst(node);
        map.put(key, node);
        if (list.size() > cap) {
            int[] last = list.removeLast();
            map.remove(last[0]);
        }
    }
}

// 2. Palindrome check on a linked list
LinkedList<Integer> ll = new LinkedList<>(Arrays.asList(1,2,2,1));
LinkedList<Integer> copy = new LinkedList<>(ll);
Collections.reverse(copy);
// ll.equals(copy) → true
```

**ArrayList vs LinkedList:**

| | `ArrayList` | `LinkedList` |
|---|---|---|
| get(i) | O(1) | O(n) |
| add(end) | O(1) | O(1) |
| add(front) | O(n) | O(1) |
| Memory | Less (no pointers) | More (prev/next pointers) |
| Use for | Random access | Frequent head/tail ops |

**Quick Cheat Sheet:**

```
addFirst / addLast     → O(1)
removeFirst / removeLast → O(1)
get(i)                 → O(n)

⚠️  Prefer ArrayDeque over LinkedList as a Queue/Stack (faster)
⚠️  Use LinkedList only when you need both List + Deque behaviors
```

---

### iii. Stack Class (LIFO)

`Stack` extends `Vector` and provides LIFO (Last-In-First-Out) semantics. **Prefer `ArrayDeque` in DSA** — `Stack` is legacy and synchronized (slow).

```java
// Core Concept (LIFO)
push(1) → push(2) → push(3)
Stack: [1, 2, 3]
              ↑
           top (peek/pop here)
pop() → 3, then 2, then 1
```

**Declaration & Initialization:**

```java
Stack<Integer> stack = new Stack<>();
// Preferred modern alternative:
ArrayDeque<Integer> stack = new ArrayDeque<>();
```

**Key Methods:**

```java
stack.push(10);   // add to top → O(1)
stack.pop();      // remove top → O(1), throws if empty
stack.peek();     // view top   → O(1), throws if empty
stack.isEmpty();  // O(1)
stack.size();     // O(1)
```

**DSA Use Cases:**

```java
// 1. Valid Parentheses
public boolean isValid(String s) {
    ArrayDeque<Character> stack = new ArrayDeque<>();
    for (char c : s.toCharArray()) {
        if (c == '(' || c == '{' || c == '[') stack.push(c);
        else {
            if (stack.isEmpty()) return false;
            char top = stack.pop();
            if (c == ')' && top != '(') return false;
            if (c == '}' && top != '{') return false;
            if (c == ']' && top != '[') return false;
        }
    }
    return stack.isEmpty();
}

// 2. Min Stack
class MinStack {
    ArrayDeque<Integer> stack = new ArrayDeque<>();
    ArrayDeque<Integer> minStack = new ArrayDeque<>();

    public void push(int val) {
        stack.push(val);
        int min = minStack.isEmpty() ? val : Math.min(val, minStack.peek());
        minStack.push(min);
    }
    public void pop()    { stack.pop(); minStack.pop(); }
    public int top()     { return stack.peek(); }
    public int getMin()  { return minStack.peek(); }
}

// 3. Largest Rectangle in Histogram
public int largestRectangleArea(int[] heights) {
    ArrayDeque<Integer> stack = new ArrayDeque<>();
    int max = 0;
    for (int i = 0; i <= heights.length; i++) {
        int h = (i == heights.length) ? 0 : heights[i];
        while (!stack.isEmpty() && h < heights[stack.peek()]) {
            int height = heights[stack.pop()];
            int width  = stack.isEmpty() ? i : i - stack.peek() - 1;
            max = Math.max(max, height * width);
        }
        stack.push(i);
    }
    return max;
}
```

**Quick Cheat Sheet:**

```
push(e)   → O(1)
pop()     → O(1)
peek()    → O(1)

⚠️  Use ArrayDeque instead of Stack (not synchronized, faster)
⚠️  pop() and peek() throw EmptyStackException on empty Stack
⚠️  With ArrayDeque: poll()/peek() return null instead of throwing
```

---

### iv. Vector Class (Thread-Safe)

`Vector` is a thread-safe, resizable array — essentially a synchronized `ArrayList`. Every method is synchronized, making it safe for concurrent access but slower for single-threaded use. **Avoid in DSA** unless thread safety is explicitly required.

```java
Vector<Integer> v = new Vector<>();
v.add(1);
v.add(2);
v.get(0);      // → 1
v.remove(0);
v.size();
```

**Vector vs ArrayList:**

| | `Vector` | `ArrayList` |
|---|---|---|
| Thread-safe | ✅ Yes (synchronized) | ❌ No |
| Speed | ❌ Slower | ✅ Faster |
| Growth | Doubles capacity | 50% increase |
| Use in DSA | ❌ Avoid | ✅ Preferred |

**Quick Cheat Sheet:**

```
Same API as ArrayList.
⚠️  Synchronized → slower than ArrayList for single-threaded DSA
⚠️  Prefer Collections.synchronizedList(new ArrayList<>()) for thread safety
⚠️  Stack extends Vector — both are legacy; use ArrayDeque instead
```

---

## 2b. Set Interface

`Set` is a collection with **no duplicate elements**. It models the mathematical set and does not guarantee any particular order (unless sorted).

```java
Set<Integer> set = new HashSet<>();    // O(1) ops, unordered
Set<Integer> set = new TreeSet<>();    // O(log n) ops, sorted
```

**Common methods:**

| Method | Description |
|---|---|
| `add(e)` | Add element (no-op if duplicate) |
| `remove(e)` | Remove element |
| `contains(e)` | Check existence |
| `size()` | Number of elements |
| `isEmpty()` | Check if empty |

---

### i. HashSet — O(1)

`HashSet` is backed by a `HashMap`. It offers O(1) average time for add, remove, and contains. No ordering guaranteed.

```java
// Core Concept
Elements are hashed to buckets:
"a" → bucket 3
"b" → bucket 7
"c" → bucket 1
Contains/add/remove: compute hash → go to bucket → O(1) avg
```

**Declaration & Initialization:**

```java
HashSet<Integer> set = new HashSet<>();
HashSet<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3));
HashSet<Integer> set = new HashSet<>(list); // from any collection
```

**Key Methods:**

```java
set.add(10);         // O(1)
set.remove(10);      // O(1)
set.contains(10);    // O(1)
set.size();
set.isEmpty();

// Set operations
set1.addAll(set2);        // union
set1.retainAll(set2);     // intersection
set1.removeAll(set2);     // difference
```

**DSA Use Cases:**

```java
// 1. Contains Duplicate
public boolean containsDuplicate(int[] nums) {
    HashSet<Integer> seen = new HashSet<>();
    for (int n : nums) {
        if (!seen.add(n)) return true; // add() returns false if duplicate
    }
    return false;
}

// 2. Longest Consecutive Sequence — O(n)
public int longestConsecutive(int[] nums) {
    HashSet<Integer> set = new HashSet<>();
    for (int n : nums) set.add(n);
    int max = 0;
    for (int n : set) {
        if (!set.contains(n - 1)) { // sequence start
            int len = 1;
            while (set.contains(n + len)) len++;
            max = Math.max(max, len);
        }
    }
    return max;
}

// 3. Intersection of Two Arrays
public int[] intersection(int[] a, int[] b) {
    HashSet<Integer> setA = new HashSet<>();
    for (int n : a) setA.add(n);
    HashSet<Integer> result = new HashSet<>();
    for (int n : b) if (setA.contains(n)) result.add(n);
    return result.stream().mapToInt(Integer::intValue).toArray();
}
```

**Quick Cheat Sheet:**

```
add(e)      → O(1) avg
remove(e)   → O(1) avg
contains(e) → O(1) avg

⚠️  No ordering — don't rely on iteration order
⚠️  Allows one null element
⚠️  add() returns boolean: false if element already existed
```

---

### ii. TreeSet — O(log N)

`TreeSet` is backed by a Red-Black Tree. Elements are kept in **natural sorted order** (or custom comparator). All operations are O(log n).

```java
// Core Concept (Red-Black Tree, sorted)
       5
      / \
     3   8
    / \   \
   1   4   9
In-order traversal: 1, 3, 4, 5, 8, 9 (sorted!)
```

**Declaration & Initialization:**

```java
TreeSet<Integer> ts = new TreeSet<>();                          // natural order
TreeSet<Integer> ts = new TreeSet<>(Collections.reverseOrder()); // descending
TreeSet<Integer> ts = new TreeSet<>(Arrays.asList(5, 3, 8, 1));
```

**Key Methods:**

```java
ts.add(10);          // O(log n)
ts.remove(10);       // O(log n)
ts.contains(10);     // O(log n)
ts.first();          // smallest → O(log n)
ts.last();           // largest  → O(log n)
ts.floor(x);         // largest element ≤ x
ts.ceiling(x);       // smallest element ≥ x
ts.lower(x);         // largest element < x
ts.higher(x);        // smallest element > x
ts.headSet(x);       // elements < x
ts.tailSet(x);       // elements ≥ x
ts.subSet(a, b);     // elements in [a, b)
ts.pollFirst();      // remove and return smallest
ts.pollLast();       // remove and return largest
```

**DSA Use Cases:**

```java
// 1. Find Closest BST Value
public int closestValue(double target, int[] nums) {
    TreeSet<Integer> ts = new TreeSet<>();
    for (int n : nums) ts.add(n);
    Integer floor   = ts.floor((int) target);
    Integer ceiling = ts.ceiling((int) Math.ceil(target));
    if (floor == null)   return ceiling;
    if (ceiling == null) return floor;
    return Math.abs(floor - target) <= Math.abs(ceiling - target) ? floor : ceiling;
}

// 2. Count of Smaller Numbers After Self (using floor/ceiling)
// 3. Sliding Window — track elements in sorted order
public double[] medianSlidingWindow(int[] nums, int k) {
    TreeSet<Integer> lo = new TreeSet<>((a, b) -> nums[a] != nums[b] ? nums[b] - nums[a] : b - a);
    TreeSet<Integer> hi = new TreeSet<>((a, b) -> nums[a] != nums[b] ? nums[a] - nums[b] : a - b);
    // ... (store indices, balance halves)
}
```

**HashSet vs TreeSet:**

| | `HashSet` | `TreeSet` |
|---|---|---|
| Order | None | Sorted |
| add/remove/contains | O(1) avg | O(log n) |
| floor/ceiling | ❌ Not available | ✅ O(log n) |
| Null elements | ✅ One null | ❌ No null |
| Use for | Fast lookup | Range queries, sorted iteration |

**Quick Cheat Sheet:**

```
add/remove/contains → O(log n)
first() / last()    → O(log n)
floor(x) / ceiling(x) → O(log n)  ← most powerful DSA methods

⚠️  No null elements (throws NullPointerException)
⚠️  Custom objects need Comparable or Comparator
⚠️  floor/ceiling return null if no such element exists
```

---

## 2c. Queue Interface (FIFO)

`Queue` is a FIFO (First-In-First-Out) collection. Elements are added at the tail and removed from the head. Core interface for BFS and level-order traversal.

```java
Queue<Integer> q = new ArrayDeque<>();   // preferred
Queue<Integer> q = new LinkedList<>();   // also works
```

**Common methods:**

| Method | Throws on empty | Returns null/false |
|---|---|---|
| Add | `add(e)` | `offer(e)` |
| Remove | `remove()` | `poll()` |
| Peek | `element()` | `peek()` |

---

### i. ArrayDeque

`ArrayDeque` is a resizable circular array implementing `Deque`. It is the **fastest** Stack and Queue in Java — preferred over both `Stack` and `LinkedList`.

```java
// Core Concept
Front ← [A][B][C][D][E] → Back
         ↑               ↑
     addFirst()       addLast()
     pollFirst()      pollLast()
```

**Declaration & Initialization:**

```java
ArrayDeque<Integer> deque = new ArrayDeque<>();
ArrayDeque<Integer> stack = new ArrayDeque<>(); // use as Stack
ArrayDeque<Integer> queue = new ArrayDeque<>(); // use as Queue
```

**Key Methods:**

```java
// As Stack (LIFO)
stack.push(x);      // addFirst → O(1)
stack.pop();        // removeFirst → O(1)
stack.peek();       // peekFirst → O(1)

// As Queue (FIFO)
queue.offer(x);     // addLast → O(1)
queue.poll();       // removeFirst → O(1)
queue.peek();       // peekFirst → O(1)

// As Deque (both ends)
deque.offerFirst(x); deque.offerLast(x);
deque.pollFirst();   deque.pollLast();
deque.peekFirst();   deque.peekLast();
```

**DSA Use Cases:**

```java
// 1. BFS — Level Order Traversal
public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) return result;
    ArrayDeque<TreeNode> queue = new ArrayDeque<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
        int size = queue.size();
        List<Integer> level = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            TreeNode node = queue.poll();
            level.add(node.val);
            if (node.left != null)  queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        result.add(level);
    }
    return result;
}

// 2. Sliding Window Maximum — Monotonic Deque
public int[] maxSlidingWindow(int[] nums, int k) {
    int n = nums.length;
    int[] result = new int[n - k + 1];
    ArrayDeque<Integer> deque = new ArrayDeque<>(); // stores indices
    for (int i = 0; i < n; i++) {
        while (!deque.isEmpty() && deque.peekFirst() < i - k + 1)
            deque.pollFirst();
        while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i])
            deque.pollLast();
        deque.offerLast(i);
        if (i >= k - 1) result[i - k + 1] = nums[deque.peekFirst()];
    }
    return result;
}
```

**Quick Cheat Sheet:**

```
Stack  → push() / pop() / peek()
Queue  → offer() / poll() / peek()
Deque  → offerFirst/Last() / pollFirst/Last() / peekFirst/Last()

⚠️  Does NOT allow null elements
⚠️  Faster than Stack and LinkedList for DSA
⚠️  poll() / peek() return null if empty (safe); pop() throws
```

---

### ii. LinkedList as Queue

`LinkedList` implements both `List` and `Deque`, so it can serve as a Queue. However, it has higher memory overhead than `ArrayDeque` due to node pointers. **Prefer `ArrayDeque`** unless you specifically need `null` elements or the `List` API simultaneously.

```java
Queue<Integer> queue = new LinkedList<>();
queue.offer(1);
queue.offer(2);
queue.peek();   // → 1
queue.poll();   // → 1 (removes)
queue.size();   // → 1
```

**Quick Cheat Sheet:**

```
offer(e)  → addLast → O(1)
poll()    → removeFirst → O(1)
peek()    → peekFirst → O(1)

⚠️  Allows null elements (unlike ArrayDeque)
⚠️  Higher memory than ArrayDeque (node overhead per element)
⚠️  Use when you need null support or simultaneous List + Queue API
```

---

### iii. PriorityQueue (Min Heap)

`PriorityQueue` is a heap-based queue where elements are ordered by priority (smallest first by default). Every `poll()` returns the current minimum. See full PriorityQueue reference above for details.

```java
// Core Concept (Min-Heap)
        1
       / \
      3   2
     / \ / \
    7  4 5  6
poll() → 1 (always the minimum)
```

**Declaration & Initialization:**

```java
PriorityQueue<Integer> minHeap = new PriorityQueue<>();                      // min
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // max
PriorityQueue<int[]>   pq      = new PriorityQueue<>((a, b) -> a[0] - b[0]); // custom
```

**Key Methods:**

```java
pq.offer(x);   // insert → O(log n)
pq.poll();     // remove min → O(log n)
pq.peek();     // view min  → O(1)
pq.size();
pq.isEmpty();
```

**DSA Use Cases:**

```java
// Kth Largest Element
public int findKthLargest(int[] nums, int k) {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    for (int n : nums) {
        minHeap.offer(n);
        if (minHeap.size() > k) minHeap.poll();
    }
    return minHeap.peek();
}

// Dijkstra's Shortest Path
PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]); // [dist, node]
pq.offer(new int[]{0, src});
while (!pq.isEmpty()) {
    int[] curr = pq.poll();
    // relax edges ...
}
```

**Quick Cheat Sheet:**

```
offer(e)  → O(log n)
poll()    → O(log n) — returns smallest (min-heap)
peek()    → O(1)

⚠️  Iteration order is NOT sorted — use poll() in a loop for sorted order
⚠️  (a,b) -> a - b can overflow; use Integer.compare(a, b) for safety
⚠️  Does NOT allow null elements
```

---

# 3. Map Interface

`Map` is a key-value store — each key maps to exactly one value. Keys are unique; values can repeat.

```java
Map<String, Integer> map = new HashMap<>();   // O(1) ops, unordered
Map<String, Integer> map = new TreeMap<>();   // O(log n) ops, sorted by key
```

**Common methods:**

| Method | Description |
|---|---|
| `put(k, v)` | Insert/update key-value |
| `get(k)` | Get value by key (null if missing) |
| `getOrDefault(k, d)` | Get value or default |
| `containsKey(k)` | Check key existence |
| `remove(k)` | Remove entry |
| `keySet()` | Set of all keys |
| `values()` | Collection of all values |
| `entrySet()` | Set of key-value pairs |

---

## 3a. HashMap

`HashMap` is the most widely used `Map`. It's backed by a hash table with O(1) average for get/put/remove. No ordering of keys.

```java
// Core Concept
Key → hash function → bucket index → value
"apple" → hash → bucket 4 → 3
"banana" → hash → bucket 9 → 5
get("apple") → O(1) avg
```

**Declaration & Initialization:**

```java
HashMap<String, Integer> map = new HashMap<>();
HashMap<String, Integer> map = new HashMap<>(Map.of("a", 1, "b", 2));
```

**Key Methods:**

```java
map.put("a", 1);
map.get("a");                       // → 1, null if missing
map.getOrDefault("z", 0);          // → 0
map.containsKey("a");              // → true
map.containsValue(1);              // → true, O(n)
map.remove("a");
map.putIfAbsent("b", 99);          // only puts if key not present
map.merge("a", 1, Integer::sum);   // add 1 to existing, or insert 1
map.getOrDefault("a", 0) + 1;

// Iteration
for (Map.Entry<String, Integer> e : map.entrySet())
    System.out.println(e.getKey() + " → " + e.getValue());
for (String key : map.keySet()) { }
for (int val : map.values()) { }
```

**DSA Use Cases:**

```java
// 1. Two Sum
public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>(); // value → index
    for (int i = 0; i < nums.length; i++) {
        int comp = target - nums[i];
        if (map.containsKey(comp)) return new int[]{map.get(comp), i};
        map.put(nums[i], i);
    }
    return new int[]{};
}

// 2. Frequency Count
public int[] topKFrequent(int[] nums, int k) {
    Map<Integer, Integer> freq = new HashMap<>();
    for (int n : nums) freq.merge(n, 1, Integer::sum);
    PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> freq.get(a) - freq.get(b));
    for (int key : freq.keySet()) {
        pq.offer(key);
        if (pq.size() > k) pq.poll();
    }
    int[] result = new int[k];
    for (int i = k - 1; i >= 0; i--) result[i] = pq.poll();
    return result;
}

// 3. Group Anagrams
public List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> map = new HashMap<>();
    for (String s : strs) {
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        String key = new String(arr);
        map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
    }
    return new ArrayList<>(map.values());
}
```

**Quick Cheat Sheet:**

```
put(k,v)            → O(1) avg
get(k)              → O(1) avg
containsKey(k)      → O(1) avg
remove(k)           → O(1) avg
getOrDefault(k, d)  → O(1) avg
merge(k, 1, fn)     → frequency counting idiom

⚠️  Allows one null key and multiple null values
⚠️  Not thread-safe — use ConcurrentHashMap for concurrency
⚠️  Iteration order is not guaranteed
```

---

## 3b. TreeMap

`TreeMap` is backed by a Red-Black Tree. Keys are kept in **natural sorted order**. All operations are O(log n). Provides powerful range/navigation methods.

```java
// Core Concept (sorted by key)
Keys stored in sorted order (BST):
       "dog"
      /     \
   "cat"   "fox"
            /
         "elk"
Iteration: cat → dog → elk → fox (alphabetical)
```

**Declaration & Initialization:**

```java
TreeMap<String, Integer> tm = new TreeMap<>();                          // natural order
TreeMap<Integer, Integer> tm = new TreeMap<>(Collections.reverseOrder()); // descending keys
```

**Key Methods:**

```java
tm.put("b", 2);
tm.get("b");          // O(log n)
tm.firstKey();        // smallest key
tm.lastKey();         // largest key
tm.floorKey(x);       // largest key ≤ x
tm.ceilingKey(x);     // smallest key ≥ x
tm.lowerKey(x);       // largest key < x
tm.higherKey(x);      // smallest key > x
tm.headMap(x);        // keys < x
tm.tailMap(x);        // keys ≥ x
tm.subMap(a, b);      // keys in [a, b)
tm.pollFirstEntry();  // remove and return smallest key entry
tm.pollLastEntry();   // remove and return largest key entry
```

**DSA Use Cases:**

```java
// 1. Time-Based Key-Value Store
class TimeMap {
    TreeMap<Integer, String>[] map;
    public String get(String key, int timestamp) {
        // floorKey finds latest timestamp ≤ given timestamp
        TreeMap<Integer, String> times = store.get(key);
        Integer t = times.floorKey(timestamp);
        return t == null ? "" : times.get(t);
    }
}

// 2. My Calendar — check no double booking
class MyCalendar {
    TreeMap<Integer, Integer> calendar = new TreeMap<>();
    public boolean book(int start, int end) {
        Integer prev = calendar.floorKey(start);
        Integer next = calendar.ceilingKey(start);
        if ((prev == null || calendar.get(prev) <= start) &&
            (next == null || end <= next)) {
            calendar.put(start, end);
            return true;
        }
        return false;
    }
}
```

**HashMap vs TreeMap:**

| | `HashMap` | `TreeMap` |
|---|---|---|
| Order | None | Sorted by key |
| get/put | O(1) avg | O(log n) |
| floorKey/ceilingKey | ❌ Not available | ✅ O(log n) |
| Null keys | ✅ One null | ❌ No null |
| Use for | Fast lookup | Range queries, sorted keys |

**Quick Cheat Sheet:**

```
put/get/remove      → O(log n)
firstKey/lastKey    → O(log n)
floorKey/ceilingKey → O(log n)  ← most powerful DSA methods

⚠️  No null keys (throws NullPointerException)
⚠️  Custom key objects must implement Comparable or provide Comparator
⚠️  floorKey/ceilingKey return null if no such key exists
```

---

# 4. Iterator

`Iterator` is an interface that allows sequential traversal of any collection without exposing its underlying structure. All collections implement `Iterable`, which provides `iterator()`.

```java
Iterator<Integer> it = list.iterator();
while (it.hasNext()) {
    int val = it.next();
    if (val % 2 == 0) it.remove(); // safe removal during iteration
}
```

**Key Methods:**

| Method | Description |
|---|---|
| `hasNext()` | Returns true if more elements exist |
| `next()` | Returns next element and advances |
| `remove()` | Removes last returned element (safe!) |

**Why use Iterator over for-each?**

```java
// ❌ ConcurrentModificationException
for (int val : list) {
    if (val == 5) list.remove(Integer.valueOf(val)); // UNSAFE
}

// ✅ Safe removal with Iterator
Iterator<Integer> it = list.iterator();
while (it.hasNext()) {
    if (it.next() == 5) it.remove(); // SAFE
}
```

---

## 4a. ListIterator

`ListIterator` extends `Iterator` and is specific to `List`. It supports **bidirectional traversal** and in-place modification.

```java
ListIterator<Integer> lit = list.listIterator();

// Forward
while (lit.hasNext()) {
    int val = lit.next();
    lit.set(val * 2);     // replace current element in place
}

// Backward
while (lit.hasPrevious()) {
    System.out.print(lit.previous() + " ");
}
```

**Key Methods (beyond Iterator):**

| Method | Description |
|---|---|
| `hasPrevious()` | True if can go backward |
| `previous()` | Returns previous element |
| `nextIndex()` | Index of next element |
| `previousIndex()` | Index of previous element |
| `set(e)` | Replace last returned element |
| `add(e)` | Insert before next element |

**Quick Cheat Sheet:**

```
Iterator     → forward only, remove()
ListIterator → forward + backward, set(), add()

⚠️  ListIterator only works on List (ArrayList, LinkedList)
⚠️  Both throw ConcurrentModificationException if collection is modified outside iterator
⚠️  Use it.remove() / lit.set() — never modify the list directly while iterating
```

---

# 5. Custom Comparator

A comparator defines custom ordering for sorting or priority queues. It takes two objects and returns negative (a < b), zero (a == b), or positive (a > b).

```java
// Functional interface
Comparator<T> comp = (a, b) -> /* negative, 0, or positive */;
```

**Patterns:**

```java
// Ascending (natural order)
(a, b) -> a - b                // ⚠️ can overflow for large ints
(a, b) -> Integer.compare(a, b) // ✅ safe

// Descending
(a, b) -> b - a
(a, b) -> Integer.compare(b, a)

// Sort int[] by first element
(a, b) -> a[0] - b[0]

// Sort int[] by second element descending
(a, b) -> b[1] - a[1]

// Sort String by length, then alphabetically
(a, b) -> a.length() != b.length() ? a.length() - b.length() : a.compareTo(b)

// Sort custom object by multiple fields
(a, b) -> a.freq != b.freq ? b.freq - a.freq : a.name.compareTo(b.name)
```

**Usage in DSA:**

```java
// Arrays.sort with comparator
int[][] intervals = {{1,3},{2,6},{8,10}};
Arrays.sort(intervals, (a, b) -> a[0] - b[0]); // sort by start time

// Collections.sort
List<String> words = Arrays.asList("banana","apple","cherry");
Collections.sort(words, (a, b) -> a.length() - b.length());

// PriorityQueue
PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]); // by end time

// TreeMap / TreeSet
TreeMap<int[], Integer> map = new TreeMap<>((a, b) -> a[0] - b[0]);
```

**Quick Cheat Sheet:**

```
(a, b) -> a - b           ascending  (⚠️ overflow risk)
(a, b) -> b - a           descending (⚠️ overflow risk)
Integer.compare(a, b)     ascending  (✅ safe)
Integer.compare(b, a)     descending (✅ safe)
Comparator.naturalOrder()
Comparator.reverseOrder()
comparator.thenComparing() // chain secondary sort

⚠️  Never use subtraction for comparators with unknown int ranges
⚠️  Return exactly negative/zero/positive — don't return Boolean
```

---

# 6. Common Algorithms

Utility methods from `java.util.Collections` and `java.util.Arrays` for instant DSA operations.

---

## Collections Utilities

```java
List<Integer> list = new ArrayList<>(Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6));

// Sort — O(n log n) (TimSort)
Collections.sort(list);
Collections.sort(list, Collections.reverseOrder());
Collections.sort(list, (a, b) -> b - a);

// Min / Max — O(n)
int max = Collections.max(list);  // → 9
int min = Collections.min(list);  // → 1

// Reverse — O(n)
Collections.reverse(list);

// Frequency — O(n)
int count = Collections.frequency(list, 1);  // → 2

// Binary Search — O(log n), list must be SORTED first
Collections.sort(list);
int idx = Collections.binarySearch(list, 5);  // → index of 5, or negative if not found
// If not found: returns -(insertion point) - 1

// Fill / Copy
Collections.fill(list, 0);         // set all to 0
List<Integer> copy = new ArrayList<>(Collections.nCopies(5, -1)); // [-1,-1,-1,-1,-1]

// Shuffle
Collections.shuffle(list);
```

---

## Arrays Utilities

```java
int[] arr = {3, 1, 4, 1, 5, 9};

// Sort — O(n log n)
Arrays.sort(arr);
Arrays.sort(arr, 2, 5);             // sort subarray [2, 5)

// Sort 2D array
int[][] matrix = {{3,1},{1,2},{2,3}};
Arrays.sort(matrix, (a, b) -> a[0] - b[0]); // sort rows by first element

// Binary Search — O(log n), must be sorted
int idx = Arrays.binarySearch(arr, 5);

// Fill — O(n)
Arrays.fill(arr, 0);
Arrays.fill(arr, 2, 5, -1);         // fill indices [2, 5) with -1

// Copy — O(n)
int[] copy  = Arrays.copyOf(arr, arr.length);
int[] slice = Arrays.copyOfRange(arr, 1, 4); // indices [1, 4)

// Equals
Arrays.equals(arr1, arr2);
Arrays.deepEquals(matrix1, matrix2); // for 2D arrays

// Convert to List
List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3)); // Integer[] only
```

---

## Math Utilities

```java
Math.pow(2, 10);          // → 1024.0
Math.sqrt(16);            // → 4.0
Math.abs(-5);             // → 5
Math.max(3, 7);           // → 7
Math.min(3, 7);           // → 3
Math.log(Math.E);         // → 1.0  (natural log)
Math.log(8) / Math.log(2);// → 3.0  (log base 2)
Math.floor(3.7);          // → 3.0
Math.ceil(3.2);           // → 4.0
Math.round(3.5);          // → 4
(int) Math.pow(2, 31) - 1;// → Integer.MAX_VALUE = 2147483647

// Integer limits
Integer.MAX_VALUE;        // 2^31 - 1 =  2147483647
Integer.MIN_VALUE;        // -2^31    = -2147483648
Long.MAX_VALUE;           // 2^63 - 1
```

---

## Quick Reference — All Utilities

```
Collections.sort(list)              → O(n log n)
Collections.sort(list, comparator)  → O(n log n)
Collections.max(list)               → O(n)
Collections.min(list)               → O(n)
Collections.reverse(list)           → O(n)
Collections.frequency(list, elem)   → O(n)
Collections.binarySearch(list, key) → O(log n), list must be sorted

Arrays.sort(arr)                    → O(n log n)
Arrays.sort(arr, from, to)          → O(k log k)
Arrays.binarySearch(arr, key)       → O(log n)
Arrays.fill(arr, val)               → O(n)
Arrays.copyOf(arr, len)             → O(n)
Arrays.copyOfRange(arr, from, to)   → O(k)
Arrays.equals(a, b)                 → O(n)

Math.pow(base, exp)                 → O(1)
Math.abs/max/min/sqrt               → O(1)

⚠️  binarySearch on unsorted array → undefined behavior
⚠️  Collections methods work on List; Arrays methods on primitive arrays
⚠️  Math.pow returns double — cast to int/long if needed
⚠️  Integer overflow: use long or Math.addExact() for safety
```
