/**
2055. Plates Between Candles

There is a long table with a line of plates and candles arranged on top of it. You are given a 0-indexed string s consisting of characters '*' and '|' only, where a '*' represents a plate and a '|' represents a candle.

You are also given a 0-indexed 2D integer array queries where queries[i] = [lefti, righti] denotes the substring s[lefti...righti] (inclusive). For each query, you need to find the number of plates between candles that are in the substring. A plate is considered between candles if there is at least one candle to its left and at least one candle to its right in the substring.

For example, s = "||**||**|*", and a query [3, 8] denotes the substring "*||**|". The number of plates between candles in this substring is 2, as each of the two plates has at least one candle in the substring to its left and right.
Return an integer array answer where answer[i] is the answer to the ith query.


Example 1:
Input: s = "**|**|***|", queries = [[2,5],[5,9]]
Output: [2,3]
Explanation:
- queries[0] has two plates between candles.
- queries[1] has three plates between candles.

Example 2:
Input: s = "***|**|*****|**||**|*", queries = [[1,17],[4,5],[14,17],[5,11],[15,16]]
Output: [9,0,0,0,0]
Explanation:
- queries[0] has nine plates between candles.
- The other queries have zero plates between candles.

Algorithm:
// index            0   1   2   3   4   5   6   7   8   9  10
//                  *   *   |   *   |   *   *   *   *   |   *
// pre_sum          1   2   2   3   3   4   5   6   7   7   8   (记录在当前index上，到目前为止在该index之前一共出现了多少个“*”)
// left_candle     -1  -1   2   2   4   4   4   4   4   9   9   (记录在当前index上，它 左边 最近的 蜡烛 出现的位置在哪，如果左边没有蜡烛则 -1)
// right_candle     2   2   2   4   4   9   9   9   9   9   11  (记录在当前index上，它 右边 最近的 蜡烛 出现的位置在哪，如果右边没有蜡烛则 设为length)

例：如果query是 [3, 10]:
对于3， 我们找到离3最近的 右边 蜡烛的位置 (right_candle index)
对于10， 我们找到离10最近的 左边 蜡烛的位置（left_candle index)

得到了两个bound蜡烛的index之后，在pre_sum里：
找到在 左边 蜡烛的位置之前所有出现的“*”总数
找到在 右边 蜡烛的位置之前所有出现的“*”总数
相减 -> 得到在两个蜡烛之间所有 ”*“ 的数量

Corner case：例： 当[5, 8]时， 5的右边是9， 8的左边是4， 此时left_index > right_index， 代表没有蜡烛在这个query中间，此时返回0


*/ 

class Solution {
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int[] prefix_sum = new int[s.length()];
        int[] left_candel = new int[s.length()];
        int[] right_candel = new int[s.length()];
        int[] result = new int[queries.length];

        prefix_sum[0] = s.charAt(0) == '|' ? 0 : 1;
        left_candel[0] = s.charAt(0) == '|' ? 0 : -1;

        for (int i = 1; i < s.length(); i++) {
            prefix_sum[i] = prefix_sum[i - 1] + (s.charAt(i) == '|' ? 0 : 1);
            left_candel[i] = s.charAt(i) == '*' ? left_candel[i - 1]: i;
        }

        right_candel[s.length() - 1] = s.charAt(s.length() - 1) == '|' ? s.length() - 1 : s.length();

        for (int i = s.length() - 2; i >= 0; i--) {
            right_candel[i] = s.charAt(i) == '|' ? i : right_candel[i + 1];
        }

        for (int i = 0; i < queries.length; i++) {
            int left_index = right_candel[queries[i][0]];
            int right_index = left_candel[queries[i][1]];
            result[i] = left_index >= right_index ? 0 : prefix_sum[right_index] - prefix_sum[left_index];
        }
        return result;
    }
}