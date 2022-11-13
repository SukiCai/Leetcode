/**
Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

Implement the LRUCache class:

LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
int get(int key) Return the value of the key if the key exists, otherwise return -1.
void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
The functions get and put must each run in O(1) average time complexity.


Example 1:

Input
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, null, -1, 3, 4]

Explanation
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // cache is {1=1}
lRUCache.put(2, 2); // cache is {1=1, 2=2}
lRUCache.get(1);    // return 1
lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
lRUCache.get(2);    // returns -1 (not found)
lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
lRUCache.get(1);    // return -1 (not found)
lRUCache.get(3);    // return 3
lRUCache.get(4);    // return 4


Algorithom:
1. To acheive O(1), using hashmap for the put and get, using double linkedlist to record the order of the stored data.
Prepare for the double linkedlist:
2. Create a Node class containing: Key, Value, Prev, Next
3. Create dummy head and dummy tails
4. Create add() and remove() function for adding new node and removing node, notice that when we add new node we put it to the head.
Prepare for the hashmap:
3. Create a hashmap: key is the input key, value is the Node with input key and input value -> Node (key, value)
Ready to use:
4. Constructor: Construct the hashmap with the given capacity, and link the head and tail
5. Put: when putting the new node into the cache, 
    firstly check if the node already exist in the cache, if exist then remove the node and put the node to the front(head) so that it is the most recently used.
    If the node not in the cache, firstly check whether the capacity is enough to put a new node in, if not, remove the node in the tail (least recently used)
    Then add the new node to the front (head)
6. Get: when getting the node from the cache,
    Check if the node exsit in the cache, if no, return -1
    if yes, return the value of the node, and move the node to the front (head)
*/


class LRUCache {
    int capacity;
    HashMap<Integer, Node> cache;
    Node head = new Node(); // Dummy head pointer
    Node tail = new Node(); // Dummy tail pointer

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>(capacity);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        int result = -1;
        Node node = cache.get(key);
        if (node != null) {
            result = node.val;
            remove(node);
            add(node);
        }
        return result;
    }
    
    public void put(int key, int value) {
        Node node = cache.get(key);
        if (node != null) {
            remove(node);
            node.val = value;
            add(node); 
        } else {
            if (this.capacity == cache.size()) {
                cache.remove(tail.prev.key); // 这两行不能换位置，不然就出bug
                remove(tail.prev); //
            }
            Node new_node = new Node();
            new_node.key = key;
            new_node.val = value;

            cache.put(key, new_node);
            add(new_node);
        }
        
    }

    public void remove(Node node) {
        Node prev_node = node.prev;
        Node next_node = node.next;

        next_node.prev = prev_node;
        prev_node.next = next_node;
       
    }

    public void add(Node node) {
        Node head_next = head.next;
        node.next = head_next;
        node.prev = head;
        head.next = node;
        head_next.prev = node;
        
    }

    class Node {
        int key;
        int val;
        Node prev;
        Node next;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */