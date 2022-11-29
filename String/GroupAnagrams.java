/**
49. Group Anagrams
Given an array of strings strs, group the anagrams together. You can return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

 

Example 1:

Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
Example 2:

Input: strs = [""]
Output: [[""]]
Example 3:

Input: strs = ["a"]
Output: [["a"]]

Algorithom:
1. Sort 每一个单词作为key
2. 如果sort之后一样加到value list里

*/

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        int[] counter = new int[26];
        Arrays.sort(strs);
        HashMap<String, List<String>> map = new HashMap<>();

        for (String s: strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            if (!map.containsKey(sorted)) {
                List<String> inner = new ArrayList<>();
                inner.add(s);
                map.put(sorted, inner);
            } else {
                map.get(sorted).add(s);
            }
        }

        List<List<String>> result = new ArrayList<List<String>>(map.values());
        return result;



        // Not work because of the feature of java object equal and hash code
        // for (int i = 0; i < strs.length; i++) {
        //     String curr = strs[i];
        //     for (int j = 0; j < curr.length(); j++) {
        //         counter[curr.charAt(j) - 'a']++;
        //     }

        //     System.out.println(counter);

        //     if (map.containsKey(counter)) {
        //         map.get(counter).add(curr);
        //     } else {
        //         List<String> inner = new ArrayList<>();
        //         inner.add(curr);
        //         map.put(counter, inner);
        //     }

        //     counter = new int[26];
        // }
        // List<List<String>> result = new ArrayList<List<String>>(map.values());
        // return result;
    }
        
}