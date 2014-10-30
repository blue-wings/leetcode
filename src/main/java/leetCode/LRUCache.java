package leetCode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * User: FR
 * Time: 10/20/14 4:02 PM
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 */
public class LRUCache {

    private Map<Integer, Node> map;
    private Integer capacity;
    private Node head;
    private Node tail;

    private class Node {
        public Integer key;
        public Node parent;
        public Node next;
        public Integer value;

        private Node(Integer key, Node parent, Node next, Integer value) {
            this.key = key;
            this.parent = parent;
            this.next = next;
            this.value = value;
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<Integer, Node>(capacity, 1);
        head = new Node(null, null, null, null);
        tail = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        if(map.size() == 1){
            return node.value;
        }
        node.parent.next = node.next;
        if(node.next !=null){
            node.next.parent = node.parent;
        }else {
            tail = node.parent;
        }
        tail.next = node;
        node.parent = tail;
        tail = node;
        tail.next=null;
        return node.value;
    }

    public void set(int key, int value) {
        Node toDelNode = null;
        if (map.containsKey(key)) {
            toDelNode = map.get(key);
        } else if (map.size() == capacity) {
            toDelNode = head.next;
        }
        if(toDelNode != null){
            toDelNode.parent.next = toDelNode.next;
            if(toDelNode.next != null){
                toDelNode.next.parent = toDelNode.parent;
            }else{
                tail = toDelNode.parent;
            }
            map.remove(toDelNode.key);
            if(map.isEmpty()){
                tail = head;
            }
        }

        if(map.size() < capacity){
            Node node = new Node(key, tail, null, value);
            tail.next = node;
            tail = node;
            tail.next=null;
            map.put(key, node);
        }
    }

    public void print() {
        StringBuilder sb = new StringBuilder(capacity);
        Node p = head.next;
        while (p!=null){
            sb.append("[" + p.key + ":" + p.value + "]");
            p = p.next;
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.set(2, 1);
        cache.set(3, 2);
        System.out.println(cache.get(3));
        System.out.println(cache.get(2));
        cache.set(4,3);
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
        cache.print();
    }
}
