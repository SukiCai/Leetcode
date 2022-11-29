/**
242. Valid Anagram

Given two strings s and t, return true if t is an anagram of s, and false otherwise.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

 

Example 1:

Input: s = "anagram", t = "nagaram"
Output: true
Example 2:

Input: s = "rat", t = "car"
Output: false

*/

class Solution {
    public boolean isAnagram(String s, String t) {
        int[] counters = new int[26];
        int[] countert = new int[26];
        if (s.length() != t.length()) return false;

        for (Character c: s.toCharArray()) {
            counters[c - 'a']++;
        }
        for (Character c: t.toCharArray()) {
            countert[c - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (counters[i] != countert[i]) {
                return false;
            }
        }
        return true;
    }
}