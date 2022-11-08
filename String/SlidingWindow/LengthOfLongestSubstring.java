/**
3. Longest Substring Without Repeating Characters

Given a string s, find the length of the longest substring without repeating characters.

Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 

Constraints:

0 <= s.length <= 5 * 104
s consists of English letters, digits, symbols and spaces.


Algorithm: Sliding window
    1. define left window pointer to be 0, right window pointer to be 0
    2. define hashset as window, storing scaned character
    3. increasing the window size
    4. until the newly added char causes duplicated within the window, pop up first element inside window, increase left point by one
    

Complexity:
- Time: O(n) , looping the whole string s once with length n
- Space: O(n), Hashset has space complexity of O(n)

*/

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int left_pointer = 0;
        int right_pointer = 0;
        int max_length = 0;
        
        HashSet<Character> window = new HashSet<Character>();
        
        while (right_pointer < s.length()) {
            if (!window.contains(s.charAt(right_pointer))) {
                window.add(s.charAt(right_pointer));
                max_length = Math.max(max_length, window.size());
                right_pointer++;
            } else {
                window.remove(s.charAt(left_pointer));
                left_pointer++;
            }
        }
        
        return max_length;
    }
}