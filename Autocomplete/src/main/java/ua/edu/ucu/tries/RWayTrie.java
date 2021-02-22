package ua.edu.ucu.tries;

import ua.edu.ucu.immutable.Queue;

public class RWayTrie implements Trie {

    private static final int R = 26; // size of the alphabet
    private static final int OFFSET = 97; // letter a in ascii
    private Node root = new Node();
    private int size = 0;

    @Override
    public void add(Tuple t) {
        // Додає в Trie кортеж - Tuple: слово - term, і його вагу - weight.
        // У якості ваги, використовуйте довжину слова
        root = put(root, t.term, t.weight, 0);
        size++;
    }

    @Override
    public boolean contains(String word) {
        // Чи є слово в Trie
        Node x = get(root, word, 0);
        return x != null && x.val != null;
    }

    @Override
    public boolean delete(String word) {
        // Видаляє слово з Trie
        if (contains(word)) {
            root = delete(root, word, 0);
            size--;
            return true;
        }
        return false;
    }

    @Override
    public Iterable<String> words() {
        // Ітератор по всім словам (обхід дерева в ширину)
        // тут юзати чергу з дз 2
        return wordsWithPrefix("");
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        // Ітератор по всім словам, які починаються з pref
        Queue queue = new Queue();
        Node x = get(root, s, 0);
        collect(x, s, queue);
        return queue::iterator;
    }

    @Override
    public int size() {
        // Кількість слів в Trie
        return this.size;
    }

    private static class Node {
        private Object val;
        private final Node[] next = new Node[R];
    }

    private Node get(Node x, String key, int d) {
        // from the book
        // return node associated with key
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            return x;
        }
        char c = key.charAt(d);
        return get(x.next[c - OFFSET], key, d + 1);
    }

    private Node put(Node x, String key, Object val, int d) {
        // from the book
        // change value associated with
        if (x == null) {
            x = new Node();
        }
        if (d == key.length()) {
            if (x.val != null) {
                size -= 1;
            } else {
                x.val = val;
            }
            return x;
        }
        char c = key.charAt(d);
        x.next[c - OFFSET] = put(x.next[c - OFFSET], key, val, d + 1);
        return x;
    }

    private Node delete(Node x, String key, int d) {
        // from the book
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            x.val = null;
        } else {
            char c = key.charAt(d);
            x.next[c - OFFSET] = delete(x.next[c - OFFSET], key, d + 1);
        }
        if (x.val != null) {
            return x;
        }
        for (char c = OFFSET; c < R + OFFSET; c++) {
            if (x.next[c - OFFSET] != null) {
                return x;
            }
        }
        return null;
    }

    private void collect(Node x, String prefix, Queue queue) {
        // from the book
        if (x == null) {
            return;
        }
        if (x.val != null) {
            queue.enqueue(prefix);
        }
        for (char c = OFFSET; c < R + OFFSET; c++) {
            collect(x.next[c - OFFSET], prefix + c, queue);
        }

    }
}
