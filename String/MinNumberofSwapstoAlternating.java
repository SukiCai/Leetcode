/**
1864. Minimum Number of Swaps to Make the Binary String Alternating


Example 1:
Input: s = "111000"
Output: 1
Explanation: Swap positions 1 and 4: "111000" -> "101010"
The string is now alternating.

Example 2:
Input: s = "010"
Output: 0
Explanation: The string is already alternating, no swaps are needed.

Example 3:
Input: s = "1110"
Output: -1

Algorithom:
1和0交替出现，如果不满足就-1，1比0多把1放在第一个，查和原本string有多少位区别再除以2

*/

class Solution {
    public int minSwaps(String s) {
        int counter_0 = 0;
        int counter_1 = 0;

        if (s.length() == 1) {
            return 0;
        } 

        for (Character c: s.toCharArray()) {
            if (c == '0') {
                counter_0 ++;
            } else {
                counter_1 ++;
            }
        }

        String[] alter = new String[s.length()];
        int value = counter_0 - counter_1;

        if (Math.abs(value) > 1) {
            return -1;
        } else if (value <= 1 && value > 0) {
            return counter(this.build_alter("0", alter), s)/2;
        } else if (value < 0 && value >= -1) {
            return counter(this.build_alter("1", alter), s)/2;
        } else if (value == 0) {
            return Math.min(counter(build_alter("0", alter), s)/2, counter(build_alter("1", alter), s)/2);
        }
        return -1;

    }
    private int counter(String[] alter, String s){
        int counter = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != alter[i].charAt(0)) {
                counter++;
            }
        }
        return counter;
    }

    private String[] build_alter(String input, String[] alter) {
        int index = 0;
        for (int i = 0; i < alter.length; i++) {
            if (index >= alter.length) {
                if (input == "1") {
                    input = "0";
                } else {
                    input = "1";
                }
                index = 1;
            }
            alter[index] = input;
            index += 2;
        }
        return alter;
    }
}