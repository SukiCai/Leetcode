/**
141. Linked List Cycle

Given head, the head of a linked list, determine if the linked list has a cycle in it.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.

Return true if there is a cycle in the linked list. Otherwise, return false.

Example 1:


Input: head = [3,2,0,-4], pos = 1
Output: true
Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).
Example 2:


Input: head = [1,2], pos = 0
Output: true
Explanation: There is a cycle in the linked list, where the tail connects to the 0th node.
Example 3:


Input: head = [1], pos = -1
Output: false
Explanation: There is no cycle in the linked list.

Algrithom:
1. 一个fast pointer,  一个slow pointer
2. fast pointer 一次走两步，如果 == null了证明没有cycle
3. 如果fast pointer == slow pointer证明它走回来了

Complexcity:
1. Time: O(N)
2. Space: O(1)
*/


/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode fast = head.next;
        ListNode slow = head;

        while(slow != fast){
            if (fast == null || fast.next == null) {
                return false;
            }

            slow = slow.next;
            fast = fast.next.next;
        }

        return true;



        // HashSet<Integer> set = new HashSet<>();
        // ListNode current = head;
        
        // while(current != null){
        //     if (set.contains(current.val)) {
        //         return true;
        //     } else {
        //         set.add(current.val);
        //     }
        //     current = current.next;
        // }
        // return false;
    }
}