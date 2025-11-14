package edu.hw8;

import java.util.NoSuchElementException;

public class B08_01 {

    public static class RecursiveStack<T> {

        private static class Node<T> {
            final T value;
            final Node<T> next;

            Node(T value, Node<T> next) {
                this.value = value;
                this.next = next;
            }
        }

        private Node<T> top;
        private int size;

        public void push(T value) {
            top = new Node<>(value, top);
            size++;
        }

        public T pop() {
            if (isEmpty()) {
                throw new NoSuchElementException("Stack is empty");
            }
            T result = top.value;
            top = top.next;
            size--;
            return result;
        }

        public T peek() {
            if (isEmpty()) {
                throw new NoSuchElementException("Stack is empty");
            }
            return top.value;
        }

        public boolean isEmpty() {
            return top == null;
        }

        public int size() {
            return size;
        }

        public void clear() {
            top = null;
            size = 0;
        }

        @Override
        public String toString() {
            return "[" + toString(top) + "]";
        }

        private String toString(Node<T> node) {
            if (node == null) {
                return "";
            }
            if (node.next == null) {
                return String.valueOf(node.value);
            }
            return node.value + ", " + toString(node.next);
        }
    }

    public static void main(String[] args) {
        RecursiveStack<Integer> stack = new RecursiveStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack.peek());
    }
}
