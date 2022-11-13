/**
937. Reorder Data in Log Files


You are given an array of logs. Each log is a space-delimited string of words, where the first word is the identifier.
There are two types of logs:

Letter-logs: All words (except the identifier) consist of lowercase English letters.
Digit-logs: All words (except the identifier) consist of digits.
Reorder these logs so that:

1. The letter-logs come before all digit-logs.
2. The letter-logs are sorted lexicographically by their contents. If their contents are the same, then sort them lexicographically by their identifiers.
3. The digit-logs maintain their relative ordering.
Return the final order of the logs.

Example 1:
Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
Explanation:
The letter-log contents are all different, so their ordering is "art can", "art zero", "own kit dig".
The digit-logs have a relative order of "dig1 8 1 5 1", "dig2 3 6".

Example 2:
Input: logs = ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
Output: ["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]

Algrithom:
1. Using Arrays.sort lambda function
2. Substracting the ids and the main in each log
3. Check if they are digit or not by looking at the first character in the main.
4. For lambda function
    由于最后 sort 出来的结果是acending的（从小到大）， 所以-1放在1的前面！
    a > b -> return 1   （最后结果 a 放在 b 后面）
    a = b -> return 0   （最后结果 a 和 b 保持原本的顺序）
    a < b -> return -1  （最后结果 a 放在 b 前面）

Complexity:
Time: O(Nlog(N)) -> Arrays.sort = quick sort
Space: O(Nlog(N)) -> Arrays.sort using stack
*/


class Solution {
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (log1, log2) -> {
            int index1 = log1.indexOf(" ");
            String id1 = log1.substring(0, index1);
            String main1 = log1.substring(index1 + 1);
            
            int index2 = log2.indexOf(" ");
            String id2 = log2.substring(0, index2);
            String main2 = log2.substring(index2 + 1);

            boolean isDigit1 = Character.isDigit(main1.charAt(0));
            boolean isDigit2 = Character.isDigit(main2.charAt(0));

            if (!isDigit1 && !isDigit2) {
                int value = main1.compareTo(main2);
                if (value == 0) {
                    return id1.compareTo(id2);
                }
                return value;
            }

            if (!isDigit1 && isDigit2) {
                return -1;
            } else if (isDigit1 && !isDigit2) {
                return 1;
            } else if (isDigit1 && isDigit2) {
                return 0;
            }

            return 0;
        });
        return logs;
    }
}