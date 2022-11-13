/**
767. Reorganize String
Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.

Return any possible rearrangement of s or return "" if not possible.

Example 1:
Input: s = "aab"
Output: "aba"

Example 2:
Input: s = "aaab"
Output: ""


Algorithom:
1. 用一个array记录26个字母分别对应的出现频率
2. 当最大频率大于String本身的长度时代表一定会有相邻字母是一样的
3. 岔开放：
        We construct the resulting string in sequence: at position 0, 2, 4, ... and then 1, 3, 5, ...
        In this way, we can make sure there is always a gap between the same characters

        Consider this example: "aaabbbcdd", we will construct the string in this way:

        a _ a _ a _ _ _ _ // fill in "a" at position 0, 2, 4
        a b a _ a _ b _ b // fill in "b" at position 6, 8, 1
        a b a c a _ b _ b // fill in "c" at position 3
        a b a c a d b d b // fill in "d" at position 5, 7

        put the letter into even index numbe (0, 2, 4 ...) char array
        put the rest into the array


Complexity:
Time O(N): fill hash[] + find the letter + write results into char array
Space O(N + 26): result + hash[]
*/

class Solution {
    public String reorganizeString(String S) {
        int[] counter = new int[26];
        // Count frequncy
        for (char c: S.toCharArray()) {
            counter[c - 'a']++;
        }

        // Find max frequncy
        int max_freqency = 0;
        int max_freqency_char_index = 0;
        for (int i = 0; i < counter.length; i++) {
            if (counter[i] > max_freqency) {
                max_freqency = counter[i];
                max_freqency_char_index = i;
            }
        }

        if (max_freqency > (S.length() + 1)/2) {
            return "";
        }

        char[] result = new char[S.length()];
        int index = 0;
        while(counter[max_freqency_char_index] != 0) {
            result[index] = (char) (max_freqency_char_index + 'a');
            counter[max_freqency_char_index]--;
            index += 2;
        }

        // 当走到这的时候最大的频率对应的letter已经count减为0了，所以不会出现重复的情况
        for (int i = 0; i < counter.length; i++) {
            while(counter[i] > 0) {
                if (index >= result.length) { //由于等于length的时候也out of bound所以要加上“=”
                    index = 1;
                }
                result[index] = (char) (i + 'a');
                index += 2;
                counter[i]--;
            }
        }
        return String.valueOf(result);
    }
}