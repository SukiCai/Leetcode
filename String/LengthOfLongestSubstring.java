/**
1268. Search Suggestions System

根据searchkey，每打一个字母出最多3个结果，直到所有的searchkey被打完为止

You are given an array of strings products and a string searchWord.
Design a system that suggests at most three product names from products after each character of searchWord is typed. Suggested products should have common prefix with searchWord. If there are more than three products with a common prefix return the three lexicographically minimums products.
Return a list of lists of the suggested products after each character of searchWord is typed. 

Example 1:
Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
Output: [["mobile","moneypot","monitor"],["mobile","moneypot","monitor"],["mouse","mousepad"],["mouse","mousepad"],["mouse","mousepad"]]
Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"].
After typing m and mo all products match and we show user ["mobile","moneypot","monitor"].
After typing mou, mous and mouse the system suggests ["mouse","mousepad"].

Example 2:
Input: products = ["havana"], searchWord = "havana"
Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
Explanation: The only word "havana" will be always suggested while typing the search word.
 

Constraints:
1 <= products.length <= 1000
1 <= products[i].length <= 3000
1 <= sum(products[i].length) <= 2 * 104
All the strings of products are unique.
products[i] consists of lowercase English letters.
1 <= searchWord.length <= 1000
searchWord consists of lowercase English letters.



Algrithom:
1. 先sort当前的array按照字母表顺序
2. 用left pointer记录符合当前searchkey的最前位置
3. 用right pointer记录符合当前searchkey的最后位置
4. 在left pointer和right pointer中间的所有product都是符合要求的，取出来作为matched result
5. 把matched list加到result list里

Complexity：
Running:O(N*M)
Space: O(N*M)

*/
class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> result = new ArrayList<>();
        Arrays.sort(products);
        int left_pointer = 0;
        int right_pointer = products.length - 1;

        for (int i = 0; i < searchWord.length(); i++) {
            while((left_pointer <= right_pointer) && (products[left_pointer].length() <= i || searchWord.charAt(i) != products[left_pointer].charAt(i))) {
                left_pointer++;
            }

            while((left_pointer <= right_pointer) && (products[right_pointer].length() <= i || searchWord.charAt(i) != products[right_pointer].charAt(i) )) {
                right_pointer--;
            }
            
            int length = Math.min(left_pointer + 3, right_pointer + 1); 
            // or 
            // int length = Math.min(2, right_pointer - left_pointer)
            // int index = left_pointer + length
            // for (int j = left_pointer; j <= length; j++)
            List<String> matched = new ArrayList<>();

            for (int j = left_pointer; j < length; j++) {
                matched.add(products[j]);
            }
            result.add(matched);
        }

        return result;
    }
}
