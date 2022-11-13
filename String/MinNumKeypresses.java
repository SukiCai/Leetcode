/**
2268. Minimum Number of Keypresses
You have a keypad with 9 buttons, numbered from 1 to 9, each mapped to lowercase English letters. You can choose which characters each button is matched to as long as:

All 26 lowercase English letters are mapped to.
Each character is mapped to by exactly 1 button.
Each button maps to at most 3 characters.
To type the first character matched to a button, you press the button once. To type the second character, you press the button twice, and so on.

Given a string s, return the minimum number of keypresses needed to type s using your keypad.

Note that the characters mapped to by each button, and the order they are mapped in cannot be changed.

Example 1:
Input: s = "apple"
Output: 5
Explanation: One optimal way to setup your keypad is shown above.
Type 'a' by pressing button 1 once.
Type 'p' by pressing button 6 once.
Type 'p' by pressing button 6 once.
Type 'l' by pressing button 5 once.
Type 'e' by pressing button 3 once.
A total of 5 button presses are needed, so return 5.

Example 2:
Input: s = "abcdefghijkl"
Output: 15
Explanation: One optimal way to setup your keypad is shown above.
The letters 'a' to 'i' can each be typed by pressing a button once.
Type 'j' by pressing button 1 twice.
Type 'k' by pressing button 2 twice.
Type 'l' by pressing button 3 twice.
A total of 15 button presses are needed, so return 15.

Algorithom:
1. 

Complexcity:
Time: O(s)
Space: O(1)
*/

class Solution {
    public int minimumKeypresses(String s) {
        
        
        Integer count[]=new Integer[26];
        Arrays.fill(count,0);
        int ans=0;
        for(int i=0;i<s.length();i++)
            count[s.charAt(i)-'a']++; //freq of character in the given string
        
        Arrays.sort(count,(a,b)->b-a); //sorting freq in descending order
        
        for(int i=0;i<26;i++)
        {
            if(i<9) //character with max frequencies ar accomodated first character of each number
                ans+=count[i];
            else if(i<18) //charatcer with intermediate freq are accomodates second position (that is 2 presses are required)
                ans+=2*count[i];
            else
                ans+=3*count[i]; //similarly least frequent characters will need 3 presses
            
        }
        return ans;
    }
}