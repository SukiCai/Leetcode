/**
125. Valid Palindrome

A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.

Given a string s, return true if it is a palindrome, or false otherwise.

 

Example 1:

Input: s = "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama" is a palindrome.
Example 2:

Input: s = "race a car"
Output: false
Explanation: "raceacar" is not a palindrome.
Example 3:

Input: s = " "
Output: true
Explanation: s is an empty string "" after removing non-alphanumeric characters.
Since an empty string reads the same forward and backward, it is a palindrome.

"bbb aaa ba"
 111 222 34

"aaa bbb ba"
 111 222 23


*/
class Solution {
    public boolean isPalindrome(String s) {
        s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        int left_pointer = 0;
        int right_pointer = s.length() - 1;

        while (left_pointer < right_pointer) {
            if(s.charAt(left_pointer) != s.charAt(right_pointer)) {
                return false;
            }
            left_pointer++;
            right_pointer--;
        }
        return true;
    }
}