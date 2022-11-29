/**
3. Longest Substring Without Repeating Characters

You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.

Return the length of the longest substring containing the same letter you can get after performing the above operations.

Example 1:
Input: s = "ABAB", k = 2
Output: 4
Explanation: Replace the two 'A's with two 'B's or vice versa.

Example 2:
Input: s = "AABABBA", k = 1
Output: 4
Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.

Constraints:
1 <= s.length <= 105
s consists of only uppercase English letters.
0 <= k <= s.length


Algorithm: Sliding window
    1. define left window pointer as 0, right window pointer as 0
    2. define an array to count the frequency of 26 letters to find the letter that has the most frequency
    3. increasing the right window pointer and the newly added letter should be counted as +1 in the corresponding count_char array
    4. find the letter that with the most frequncy within the count_char array
    5. until the window size - frequncy of the most counted letter > k (largest replacement allow), increase the left window pointer by 1 and pop up the letter in the previous left window by decreasing the count in the count_char array
    

Complexity:
- Time: O(n)
- Space: O(1)

*/
class Solution {
       
    public int characterReplacement(String s, int k) {
        int left_pointer = 0;
        int right_pointer = 0;
        int[] count_char = new int[26];
        int most_char_frequncy = 0;
        int max_length = 0;
        
        while(right_pointer < s.length()) {
            count_char[s.charAt(right_pointer) - 'A']++;
            most_char_frequncy = Math.max(most_char_frequncy, count_char[s.charAt(right_pointer) - 'A']);
            
            if (right_pointer - left_pointer + 1 - most_char_frequncy > k) {
                count_char[s.charAt(left_pointer) - 'A']--;
                left_pointer++;
            }
            
            max_length = right_pointer - left_pointer + 1;
            right_pointer++;
        }
        
        return max_length;
        }
//https://www.youtube.com/watch?v=SLAKjysDODM
//     public int characterReplacement(String s, int k) {
//         int len = s.length();
//         if (len < 2) {
//             return len;
//         }
//         char[] charArray = s.toCharArray();
//         int[] freq = new int[26];
//         int maxCount = 0;
//         int left = 0;
//         int right = 0;
//         while (right < len) {
//             freq[charArray[right] - 'A']++;
//             maxCount = Math.max(maxCount, freq[charArray[right] - 'A']);
//             right++;
            
//             if(right - left > maxCount + k) {
//                 freq[charArray[left] - 'A']--;
//                 left++;
//             }
//         }
//         return right - left;
//     }
 
        

//     public int characterReplacement(String s, int k) {
//         int left_pointer = 0;
//         int right_pointer = 0;
//         int max_count = 0;
//         int max_length = 0;
//         int[] char_counts = new int[26];
        
//         while(right_pointer < s.length()) {
//             char_counts[(s.charAt(right_pointer)) - 'A']++;
//             max_count = Math.max(max_count, char_counts[(s.charAt(right_pointer)) - 'A']);
            
//             while (right_pointer - left_pointer - max_count + 1 > k) {
//                 char_counts[(s.charAt(left_pointer)) - 'A']--;
//                 left_pointer++;
//             }
//             max_length = Math.max(max_length, right_pointer - left_pointer + 1);
//             right_pointer++;
//         }
//         return max_length;
//     }
}