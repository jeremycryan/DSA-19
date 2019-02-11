import java.util.*;

public class Problems {

    public static class Node {
        int val;
        Node next;

        Node(int d) {
            this.val = d;
            next = null;
        }
    }

    public static LinkedList<Integer> removeKDigits(int[] A, int k) {

        LinkedList<Integer> l = new LinkedList<>();
        Stack<Integer> good_digits = new Stack<>();
        int last_digit = -1;
        int i = 0;

        while (i < A.length) {

            if (good_digits.isEmpty()) {

                // Push any elements while stack is empty
                good_digits.push(A[i]);
                i++;
                continue;

            }

            if ((A[i] >= good_digits.peek()) || (k <= 0)) {

                // Push elements that increase
                good_digits.push(A[i]);

            } else {

                // Get rid of elements before a decrease
                i--;
                k--;
                good_digits.pop();

            }

            i++;
        }

        // Remove extra digits from the end
        while (k > 0) {

            k--;
            good_digits.pop();

        }

        // Copy elements from stack to linked list to return
        while (!good_digits.isEmpty()) l.addFirst(good_digits.pop());

        return l;

    }

    public static boolean isPalindrome(Node n) {

        // Empty lists are palindromes
        if (n == null) return true;

        Node cur_node = n;

        // Determine the length of the list
        int length = 1;
        while (cur_node.next != null) {

            System.out.print(cur_node.val);
            length++;
            cur_node = cur_node.next;

        }

        // Lists of length 1 are palindromes
        if (length == 1) return true;

        cur_node = n;
        Node prev_node = null;
        Node next_node = cur_node.next;
        int in = 1;
        int left = length;

        // Reverse the linked list up to the halfway point
        while (in + 1 < left) {

            next_node = cur_node.next;
            cur_node.next = prev_node;
            prev_node = cur_node;
            cur_node = next_node;

            in++;
            left--;

        }

        // Do some shenanigans so I have two nodes, centered in the list,
        // but pointed opposite directions.
        Node left_start = cur_node;
        Node right_start = cur_node.next;
        if (length%2 == 1) {

            right_start = new Node(cur_node.val);
            right_start.next = cur_node.next;

        }
        left_start.next = prev_node;

        // Check if both halves have the same values
        while (in > 0) {

            if (left_start == null || right_start == null) return false;
            System.out.println("R: " + right_start.val + " L: " + left_start.val);
            if (left_start.val != right_start.val) return false;

            left_start = left_start.next;
            right_start = right_start.next;

            in --;
        }

        // If you get this far, it's a palindrome.
        return true;

    }

    public static String infixToPostfix(String s) {

        // Initialize variables
        Stack<String> numbers = new Stack<>();
        Stack<Character> operands = new Stack<>();
        String return_string = "";
        int i = 0;
        int length = s.length();
        String num_0;
        String num_1;
        char operand;

        while (i < length) {

            // Grab characters one at a time
            char this_char = s.charAt(i);
            switch (this_char) {

                case ' ':
                    break;

                // Parens go into numbers stack... because
                case '(':
                    numbers.push(String.valueOf(this_char));
                    break;

                // Close parens perform the operation, and remove the open paren
                case ')':
                    System.out.println(numbers);
                    num_1 = numbers.pop();
                    num_0 = numbers.pop();
                    numbers.pop();
                    operand = operands.pop();

                    String addition =  num_0 + " " + num_1 + " " + operand;
                    numbers.push(addition);
                    break;

                // Operands go into their own stack
                case '*': case '+': case '-': case '/':
                    operands.push(this_char);
                    break;

                // Numbers go into the numbers stack
                case '0': case '1': case '2': case '3': case '4':
                case '5': case '6': case '7': case '8': case '9':

                    numbers.push(String.valueOf(this_char));
                    break;

            }

            i++;

        }

        // By now, the return string should be already glued together as the only element of numbers
        return_string = numbers.pop();
        System.out.println(return_string);

        return return_string;
    }

}
