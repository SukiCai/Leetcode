class Solution {
    public int lengthOfLongestSubstring(String s) {
        int a_pointer = 0;
        int b_pointer = 0;
        int max = 0;
        
        HashSet<Character> window = new HashSet<Character>();
        
        while (b_pointer < s.length()) {
            if (window.contains(s.charAt(b_pointer)) == false) {
                window.add(s.charAt(b_pointer));
                max = Math.max(max, window.size());
                b_pointer++;
            } else {
                window.remove(s.charAt(a_pointer));
                a_pointer++;
            }
        }
        
        return max;
    }
}