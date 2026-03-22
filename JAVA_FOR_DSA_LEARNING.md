# Java for DSA - Learning Guide (C# Developer Edition)

> This guide is designed for C# developers who need to learn Java for DSA. Expected duration: 2-3 days

---

## Part 1: Understanding Your First Java Code

### The Code Breakdown

```java
package basic_java;

public class Basics {
    public static void main(String[] args) {
        System.out.prin    s1.equals(s2);   // Always true (checks content)
  ```
  - ☐ **Integer vs int**: `int` is primitive, `Integer` is wrapper class
    ```java
    List<Integer> list = new ArrayList<>();  // Use Integer, not int
    ```
  - ☐ **Collections.max() and Collections.min()**
    ```java
    int max = Collections.max(list);
    int min = Collections.min(list);
    ```

- ☐ **Lambda Expressions (Java 8+) - Optional but useful**orld!");
    }
}
```

**In Layman's Terms:**

1. **`package basic_java;`** - This is like organizing your code into folders. It groups related classes together to avoid naming conflicts.
   - *C# Equivalent*: `namespace basic_java { }`

2. **`public class Basics {`** - This declares a public class named "Basics". Java requires each file to have a public class (usually one public class per file).
   - *C# Equivalent*: `public class Basics { }`

3. **`public static void main(String[] args)`** - This is the **entry point** of your Java program. When you run the program, Java looks for this method and executes it first.
   - `public` = accessible from anywhere
   - `static` = belongs to the class, not an instance
   - `void` = returns nothing
   - `String[] args` = array of command-line arguments
   - *C# Equivalent*: `static void Main(string[] args)`

4. **`System.out.println("Hello, World!");`** - Prints text to the console and adds a newline.
   - *C# Equivalent*: `Console.WriteLine("Hello, World!");`

**What happens when you run it?** Java starts, looks for the `main` method, and prints "Hello, World!" to the screen.

---

## Part 2: 2-3 Day Java Learning Plan for DSA

### **Day 1: Java Fundamentals & Syntax Basics**

#### Morning (2-3 hours)

- ☐ **Java Environment Setup**
  - ☐ Understand JDK (Java Development Kit) vs JRE (Java Runtime Environment)
  - ☐ Know that Java is compiled to bytecode (.class files) then interpreted by JVM
  - ☐ Compare to C#: JVM is like .NET CLR, bytecode is like IL (Intermediate Language)

- ☐ **Primitive Data Types**
  ```java
  byte b = 127;          // 8-bit signed integer (-128 to 127)
  short s = 32000;       // 16-bit signed integer
  int i = 2147483647;    // 32-bit signed integer (default for numbers)
  long l = 9223372036854775807L;  // 64-bit (use L suffix)
  
  float f = 3.14f;       // 32-bit floating point (use f suffix)
  double d = 3.14159;    // 64-bit floating point (default for decimals)
  
  boolean flag = true;   // true or false
  char ch = 'A';         // single character (16-bit Unicode)
  ```
  - **C# Comparison**: Similar types but different names (int, float, double, bool, char)

- ☐ **Variable Declaration & Naming**
  - ☐ Naming conventions: camelCase for variables (e.g., `myVariable`)
  - ☐ Constants: use `static final` (C# equivalent: `const` or `readonly`)

- ☐ **Type Casting**
  ```java
  int x = 10;
  double y = (double) x;  // Explicit casting (widening)
  int z = (int) 3.14;     // Explicit casting (narrowing) - loses decimal part
  ```

#### Afternoon (2-3 hours)

- ☐ **Control Flow Statements**
  - ☐ `if-else` statements (same as C#)
  - ☐ `switch` statements (similar to C#, but Java doesn't have switch expressions until Java 12)
  ```java
  switch(day) {
      case 1:
          System.out.println("Monday");
          break;
      case 2:
          System.out.println("Tuesday");
          break;
      default:
          System.out.println("Invalid");
  }
  ```

- ☐ **Loops**
  - ☐ `for` loop: `for(int i = 0; i < 10; i++) { }`
  - ☐ `while` loop: `while(condition) { }`
  - ☐ `do-while` loop: `do { } while(condition);`
  - ☐ **Enhanced for loop** (for-each): `for(int num : array) { }`
    - *C# Equivalent*: `foreach(int num in array) { }`

- ☐ **Methods/Functions**
  ```java
  public static int add(int a, int b) {
      return a + b;
  }
  ```
  - **C# Comparison**: Same concept, but Java uses `static` for class methods explicitly

- ☐ **Key Differences from C#**
  - ☐ No implicit `using` directives; import what you need: `import java.util.*;`
  - ☐ No properties with getters/setters shorthand (until Java 14+ records)
  - ☐ No async/await built-in (threading is different)

---

### **Day 2: Collections & Object-Oriented Programming**

#### Morning (2-3 hours)

- ☐ **Arrays**
  ```java
  int[] arr = new int[5];           // Size 5, all initialized to 0
  int[] arr2 = {1, 2, 3, 4, 5};     // Literal initialization
  String[] names = new String[3];   // Size 3, all initialized to null
  
  arr[0] = 10;                      // Access element
  int length = arr.length;          // Get length (property, not method!)
  ```
  - **Important**: Arrays have fixed size. Use Collections for dynamic sizes.

- ☐ **Collections Framework (Key for DSA!)**
  ```java
  import java.util.*;
  
  // List (ordered, allows duplicates)
  List<Integer> list = new ArrayList<>();  // Dynamic array
  list.add(1);
  list.add(2);
  list.get(0);      // Access element
  list.remove(0);   // Remove element
  list.size();      // Get size
  
  // Stack (LIFO - Last In First Out)
  Stack<Integer> stack = new Stack<>();
  stack.push(1);
  stack.pop();
  stack.peek();
  
  // Queue (FIFO - First In First Out)
  Queue<Integer> queue = new LinkedList<>();
  queue.add(1);
  queue.poll();
  queue.peek();
  
  // Set (unique elements, no duplicates)
  Set<Integer> set = new HashSet<>();
  set.add(1);
  set.contains(1);
  
  // Map (key-value pairs)
  Map<String, Integer> map = new HashMap<>();
  map.put("age", 25);
  map.get("age");
  map.remove("age");
  ```
  - **C# Equivalents**: 
    - `ArrayList` → `List<T>`
    - `Stack<T>` → `Stack<T>`
    - `Queue<T>` → `Queue<T>`
    - `HashSet<T>` → `HashSet<T>`
    - `HashMap` → `Dictionary<K, V>`

- ☐ **Generics** (Type Parameters)
  ```java
  List<String> names = new ArrayList<>();  // <String> = type parameter
  names.add("Alice");
  // String name = names.get(0);  // No casting needed
  
  // Creating generic class
  public class Box<T> {
      private T value;
      public void set(T val) { value = val; }
      public T get() { return value; }
  }
  ```
  - **C# Comparison**: Same concept as C# generics `<T>`

#### Afternoon (2-3 hours)

- ☐ **Classes and Objects**
  ```java
  public class Person {
      private String name;      // Private field
      private int age;
      
      // Constructor
      public Person(String name, int age) {
          this.name = name;
          this.age = age;
      }
      
      // Getter
      public String getName() {
          return name;
      }
      
      // Setter
      public void setAge(int age) {
          this.age = age;
      }
      
      // Method
      public void display() {
          System.out.println("Name: " + name + ", Age: " + age);
      }
  }
  
  // Usage
  Person p = new Person("Alice", 25);
  p.display();
  p.setAge(26);
  ```

- ☐ **Inheritance**
  ```java
  public class Animal {
      public void eat() {
          System.out.println("Animal eating");
      }
  }
  
  public class Dog extends Animal {  // Dog inherits from Animal
      public void bark() {
          System.out.println("Woof!");
      }
  }
  
  Dog dog = new Dog();
  dog.eat();   // Inherited method
  dog.bark();  // Own method
  ```
  - **C# Comparison**: Uses `extends` instead of `:`

- ☐ **Polymorphism & Method Overriding**
  ```java
  public class Animal {
      public void sound() {
          System.out.println("Generic sound");
      }
  }
  
  public class Dog extends Animal {
      @Override  // Annotation (optional but recommended)
      public void sound() {
          System.out.println("Woof!");
      }
  }
  ```

- ☐ **Interfaces**
  ```java
  public interface Drawable {
      void draw();
  }
  
  public class Circle implements Drawable {
      @Override
      public void draw() {
          System.out.println("Drawing circle");
      }
  }
  ```
  - **C# Comparison**: Same as C# interfaces

- ☐ **Abstract Classes**
  ```java
  public abstract class Shape {
      abstract public void area();  // Abstract method
      
      public void display() {       // Concrete method
          System.out.println("Shape");
      }
  }
  
  public class Circle extends Shape {
      @Override
      public void area() {
          System.out.println("Circle area");
      }
  }
  ```

---

### **Day 3: DSA-Specific Patterns & Practice**

#### Morning (2-3 hours)

- ☐ **Strings (Important for DSA!)**
  ```java
  String s = "Hello";
  
  // Important methods
  int len = s.length();           // Get length
  char ch = s.charAt(0);          // Get character at index
  String sub = s.substring(1, 3); // Get substring
  int index = s.indexOf("l");     // Find index of character
  boolean contains = s.contains("ell");  // Check if contains
  String[] parts = s.split(",");  // Split by delimiter
  String upper = s.toUpperCase(); // Convert to uppercase
  boolean equals = s.equals("Hello");  // Compare strings
  
  // String is IMMUTABLE in Java!
  // Every modification creates a new String object
  
  // For mutable strings, use StringBuilder
  StringBuilder sb = new StringBuilder("Hello");
  sb.append(" World");
  sb.reverse();
  String result = sb.toString();
  ```
  - **C# Comparison**: `String` is also immutable; use `StringBuilder` for building strings

- ☐ **Common DSA Patterns**
  ```java
  // 1. Two Pointer Technique
  int[] arr = {1, 2, 3, 4, 5};
  int left = 0, right = arr.length - 1;
  while(left < right) {
      int temp = arr[left];
      arr[left] = arr[right];
      arr[right] = temp;
      left++;
      right--;
  }
  
  // 2. Sliding Window
  int[] arr = {1, 2, 3, 4, 5};
  int windowSize = 3;
  for(int i = 0; i <= arr.length - windowSize; i++) {
      // Process window arr[i] to arr[i + windowSize - 1]
  }
  
  // 3. Sorting
  import java.util.Arrays;
  Arrays.sort(arr);                    // Sort array
  Collections.sort(list);              // Sort list
  Collections.sort(list, Collections.reverseOrder()); // Reverse sort
  
  // 4. HashMap for quick lookups (O(1) average)
  Map<Integer, Integer> map = new HashMap<>();
  for(int num : arr) {
      map.put(num, map.getOrDefault(num, 0) + 1);
  }
  ```

#### Afternoon (2-3 hours)

- ☐ **Common Gotchas & Tips for DSA**
  - ☐ **Array vs List**: Arrays have fixed size; Lists are dynamic
  - ☐ **Null vs 0**: Use null for objects, 0 for primitives
  - ☐ **String comparison**: Use `.equals()`, NOT `==` (unless checking references)
    ```java
    String s1 = "hello";
    String s2 = "hello";
    s1 == s2;        // May be true or false (checks reference)
    s1.equals(s2);   // Always true (checks content)
    ```
  - [ ] **Integer vs int**: `int` is primitive, `Integer` is wrapper class
    ```java
    List<Integer> list = new ArrayList<>();  // Use Integer, not int
    ```
  - [ ] **Collections.max() and Collections.min()**
    ```java
    int max = Collections.max(list);
    int min = Collections.min(list);
    ```

- [ ] **Lambda Expressions (Java 8+) - Optional but useful**
  ```java
  // Traditional way
  Collections.sort(list, new Comparator<Integer>() {
      public int compare(Integer a, Integer b) {
          return a - b;
      }
  });
  
  // Lambda way (more concise)
  Collections.sort(list, (a, b) -> a - b);
  
  // Using forEach with lambda
  list.forEach(num -> System.out.println(num));
  ```

- ☐ **Streams API (Java 8+) - Optional but powerful for DSA**
  ```java
  import java.util.stream.Collectors;
  
  List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
  
  // Filter
  List<Integer> evenNumbers = numbers.stream()
      .filter(n -> n % 2 == 0)
      .collect(Collectors.toList());
  
  // Map
  List<Integer> doubled = numbers.stream()
      .map(n -> n * 2)
      .collect(Collectors.toList());
  
  // Reduce
  int sum = numbers.stream()
      .reduce(0, (a, b) -> a + b);
  ```

- ☐ **Quick Reference: Collections Performance**
  | Operation | ArrayList | LinkedList | HashSet | HashMap |
  |-----------|-----------|-----------|---------|---------|
  | Add       | O(1)*     | O(1)      | O(1)    | O(1)    |
  | Remove    | O(n)      | O(1)      | O(n)    | O(1)    |
  | Get       | O(1)      | O(n)      | -       | O(1)    |
  | Search    | O(n)      | O(n)      | O(1)    | O(1)    |
  
  *Amortized time (might be O(n) if capacity needs resizing)

---

## **Quick Cheat Sheet: C# to Java Mapping**

| C# | Java | Notes |
|----|------|-------|
| `using` | `import` | Include libraries |
| `namespace` | `package` | Organize code |
| `class` | `class` | Define class |
| `public` | `public` | Access modifier |
| `private` | `private` | Access modifier |
| `static` | `static` | Class member |
| `void` | `void` | Return type |
| `string` | `String` | Text type (Capital S!) |
| `int, float, bool` | `int, float, boolean` | Primitive types |
| `List<T>` | `List<T>` | Dynamic array |
| `Dictionary<K,V>` | `Map<K,V>` | Key-value pairs |
| `for(var x in array)` | `for(int x : array)` | Enhanced for loop |
| `Array.Sort()` | `Arrays.sort()` | Sort array |
| `Console.WriteLine()` | `System.out.println()` | Print output |
| `:` (inheritance) | `extends` | Inheritance |
| `interface` | `interface` | Interface |
| `abstract class` | `abstract class` | Abstract class |

---

## **Next Steps**

1. **Day 1**: Focus on syntax and getting comfortable with the basics. Write simple programs to test what you've learned.
2. **Day 2**: Dive into Collections (the most important for DSA). Practice creating and manipulating Lists, Sets, and Maps.
3. **Day 3**: Review DSA-specific patterns and start implementing simple algorithms (sorting, searching, etc.).
4. **Post-Learning**: Practice with LeetCode, HackerRank, or other DSA platforms using Java.

---

## **Pro Tips for C# Developers**

- Java is more verbose than C#. Get used to writing more code for the same functionality.
- Always use `final` keyword for constants (similar to C#'s `const`).
- Prefer `ArrayList` over arrays for DSA problems (flexibility is key).
- Use IDE shortcuts: Generate getters/setters, constructors automatically.
- Remember: `String` is immutable; use `StringBuilder` for frequent modifications.

---

**Good luck with your DSA journey in Java!** 🚀
