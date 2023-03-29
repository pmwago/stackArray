package poly;

import java.util.NoSuchElementException;
import java.util.Scanner;
public class StackArray {
    String[] data;          // array to hold items
    int top; // the top most item index
    public StackArray(int N) {
        data = new String[N];
        top = -1;
    }
    public void push(String element) {
        if (!isFull()) {
            top++;
            data[top] = element;
        }
    }
    public String pop() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        } else {
            top--;
            return data[top + 1];
        }
    }
    public boolean isFull() {
        return top == data.length - 1;
    }
    public boolean isEmpty() {
        return top == -1;
    }
    public static class Implementation {
        public static void menu(){
            System.out.println("\n 1- push \n 2- pop \n 3- Print \n 4- Delete middle \n 5- isPalindrome \n 6- exit \n Please enter a digit (1-5):");
        }
        public static void func() {
            Scanner in = new Scanner(System.in);
            int input; // user's input
            String val;
            StringBuilder val1;
            StringBuilder val2;
            StackArray temporaryStack;
            StackArray stack = null;
            int center;
            int i, j = 0, k;
            while (true){
               menu();
                input = in.nextInt();
                switch (input) {
                    case 1:
                        System.out.println("How may pushes you want to do?");
                        i = in.nextInt();
                        System.out.println("Enter " + i + " strings:");
                        if (stack == null) {
                            stack = new StackArray(i);
                            j=i;
                        } else {
                            temporaryStack = new StackArray(j);
                            while (!stack.isEmpty()) {
                                temporaryStack.push(stack.pop());
                            }
                            stack = new StackArray(j + i);
                            j =j+i;
                            while (!temporaryStack.isEmpty()) {
                                stack.push(temporaryStack.pop());
                            }
                        }
                        for (k = 0; k < i; k++) {
                            do {
                                val = in.nextLine();
                            } while (val.trim().equals(""));
                            stack.push(val);
                        }
                        break;
                    case 2:
                        try {
                            assert stack != null;
                            System.out.print("" + stack.pop());
                            j--;
                        } catch (NoSuchElementException e) {
                            System.out.print(" "+e.getMessage());
                        }
                        break;
                    case 3:
                        System.out.println("The content of stack is:");
                        temporaryStack = new StackArray(j);
                        k = 0;
                        while (true) {
                            assert stack != null;
                            if (stack.isEmpty()) break;
                            val = stack.pop();
                            temporaryStack.push(val);
                            System.out.print(val);
                            if (k < j)
                                System.out.print(",");
                            k++;
                        }
                        while (!temporaryStack.isEmpty()) {
                            stack.push(temporaryStack.pop());
                        }
                        break;
                    case 4:
                        if (j % 2 == 0) {
                            System.out.print("There is no middle element as the size of stack is even.");
                        } else {
                            center = j / 2;
                            temporaryStack = new StackArray(j - 1);
                            k = 0;
                            while (!stack.isEmpty()) {
                                val = stack.pop();
                                if (k != center)
                                    temporaryStack.push(val);
                                k++;
                            }
                            stack = new StackArray(j - 1);
                            while (!temporaryStack.isEmpty()) {
                                stack.push(temporaryStack.pop());
                            }
                            j--;
                            System.out.print("The middle element has been deleted ");
                        }
                        break;
                    case 5:
                        val1 = new StringBuilder();
                        val2 = new StringBuilder();
                        if (j==0){
                            System.out.print("Stack is empty");
                        }
                       else if (j % 2 == 0) {
                            System.out.print("There is no middle element as the size of stack is even.");
                        } else {
                            center = j / 2;
                            temporaryStack = new StackArray(j);
                            k = 0;
                            while (!stack.isEmpty()) {
                                val = stack.pop();
                                temporaryStack.push(val);
                                if (k < center)
                                    val1.append(val);
                                else if (k > center)
                                    val2.insert(0, val);
                                k++;
                            }
                            while (!temporaryStack.isEmpty()) {
                                stack.push(temporaryStack.pop());
                            }
                            if (val1.toString().equals(val2.toString()))
                                System.out.print("The stack is palindrome. ");
                            else
                                System.out.print("The stack is not a palindrome. ");
                        }
                        break;
                    case 6:
                        System.out.print("Exiting.... ");
                        System.exit(0);
                }
            }
        }
    }
    public static void main(String[] args) {
        Implementation.func();
    }
}