/**
409. Longest Palindrome

Given a string s which consists of lowercase or uppercase letters, return the length of the longest palindrome that can be built with those letters.

Letters are case sensitive, for example, "Aa" is not considered a palindrome here.

 

Example 1:

Input: s = "abccccdd"
Output: 7
Explanation: One longest palindrome that can be built is "dccaccd", whose length is 7.
Example 2:

Input: s = "a"
Output: 1
Explanation: The longest palindrome that can be built is "a", whose length is 1.
*/

class Solution {
    public int longestPalindrome(String s) {
        int[] counter = new int[128];
        int result = 0;
        for (Character c: s.toCharArray()) {
            counter[c]++;
        }

        for (int i: counter) {
            result += i/2 *2;
        }

        if (result % 2 == 0 && result < s.length()) return result + 1;

        return result;
    }
}