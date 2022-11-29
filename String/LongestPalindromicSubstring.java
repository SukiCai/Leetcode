/**
5. Longest Palindromic Substring

Given a string s, return the longest 
palindromic
 
substring
 in s.

 

Example 1:

Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.
Example 2:

Input: s = "cbbd"
Output: "bb"



*/

class Solution {
    public String longestPalindrome(String s) {
        String result = "";
        int length = 0;
        
        for (int i = 0; i < s.length(); i++) {

            int left_pointer = i;
            int right_pointer = i;
            while(left_pointer >= 0 &&  right_pointer < s.length() && s.charAt(left_pointer) == s.charAt(right_pointer)) {
                if (right_pointer - left_pointer + 1 > length) {
                    length = right_pointer - left_pointer + 1;
                    result = s.substring(left_pointer, right_pointer+1);
                }
                left_pointer--;
                right_pointer++;
            }

            left_pointer = i;
            right_pointer = i+1;
            while(left_pointer >= 0 &&  right_pointer < s.length() && s.charAt(left_pointer) == s.charAt(right_pointer)) {
                if (right_pointer - left_pointer + 1 > length) {
                    length = right_pointer - left_pointer + 1;
                    result = s.substring(left_pointer, right_pointer+1);
                }
                left_pointer--;
                right_pointer++;
            }
        }
        return result;
    }

/**
647. Palindromic Substrings
Given a string s, return the number of palindromic substrings in it.

A string is a palindrome when it reads the same backward as forward.

A substring is a contiguous sequence of characters within the string.

 

Example 1:

Input: s = "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
Example 2:

Input: s = "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".


Algorithom:
1. 分情况讨论，分别找当前char的偶数和奇数的pandrom
2. 当两个指针指向相同的char时 count++；然后两个指针继续向外扩展，直到两个指针指向的char不同时退出循环，此时对于以改char为中心的palindrome全部找完
3，走到下一个char继续数该char的palindrome个数
*/


class Solution {
    /**
    L:   |
        "aaaabba"
    R:      |
    check if left = right
    if right != left
    
    */
    public int countSubstrings(String s) {
        int result = 0;
        int left_pointer = 0;
        int right_pointer = 1;
        for (int i = 0; i < s.length(); i++) {
            left_pointer = i;
            right_pointer = i;
            while(left_pointer >= 0 && right_pointer < s.length() && s.charAt(left_pointer) == s.charAt(right_pointer)) {
                result++;
                left_pointer--;
                right_pointer++;
            }

            left_pointer = i;
            right_pointer = i+1;
            while(left_pointer >= 0 && right_pointer < s.length() && s.charAt(left_pointer) == s.charAt(right_pointer)) {
                result++;
                left_pointer--;
                right_pointer++;
            }
        }
        return result;
    }
}