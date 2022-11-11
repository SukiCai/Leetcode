/**
138. Copy List with Random Pointer

A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.

Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node has its value set to the value of its corresponding original node. Both the next and random pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list and copied list represent the same list state. None of the pointers in the new list should point to nodes in the original list.

For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding two nodes x and y in the copied list, x.random --> y.

Return the head of the copied linked list.

The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:

val: an integer representing Node.val
random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
Your code will only be given the head of the original linked list.


Example 1:
Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]

Example 2:
Input: head = [[1,1],[2,1]]
Output: [[1,1],[2,1]]

Example 3:
Input: head = [[3,null],[3,0],[3,null]]
Output: [[3,null],[3,0],[3,null]]

Algrithom:
1. Copy Node (with value only), 如果不先copy node的话random pointer有可能指到还没有copy出来的node上 ex：只copy到3但是3指到5
    To find the copid node, using a hashmap to store the original node and the corresponding copy node (不然一copy完一个node就再也找不到了)
2. Copy pointer (both random pointer and next pointer)
    Using hashmap to get the current node corresponding copy node, get the value(copy node) by key "current.next" in the hashmap to get the next node of the current copy node
    same as the random pointer.
3. Return the copied head.



*/

class Solution {
    public Node copyRandomList(Node head) {
        HashMap<Node, Node> copy_map = new HashMap<>();
        Node current = head;
        while(current != null) {
            Node copy = new Node(current.val);
            copy_map.put(current, copy);
            current = current.next;
        }
        current = head;
        while(current != null) {
            Node current_copy = copy_map.get(current);
            current_copy.next = copy_map.get(current.next);
            current_copy.random = copy_map.get(current.random);
            current = current.next;
        }
        return copy_map.get(head);
    }
}