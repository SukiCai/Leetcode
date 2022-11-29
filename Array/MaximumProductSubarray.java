/**
152. Maximum Product Subarray

Given an integer array nums, find a 
subarray
 that has the largest product, and return the product.

The test cases are generated so that the answer will fit in a 32-bit integer.

 

Example 1:

Input: nums = [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: nums = [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.

Algorithom: 
记录max和min
在current， current * max， current * min 里寻找最大值 （决定是否include current进product里
同时记录当前三个数中的最小值，（也是记录当前最小值所包含的 int[]）
注意用temp记录max因为max一直在update
*/

class Solution {
    /**
    [-2, 3, -4, -1, -2]
    current = -2
    max = -2
    min = -2

    current = 3 
    max = (3, -6, -6) = 3 [3]
    min = -6 [-2, 3]

    current = -4 
    max = (-4, -12, 24) = 24 [-2, 3, -4]
    min = -12 [3, -4]

    current = -1
    max = (-1, -24, 12) = 12 [3, -4, -1]
    min = -24 = [-2, 3, -4, -1]

    current = -2
    max = (-2, -24 ,48) = 48 [-2, 3, -4, -1]
    min = -24 [3, -4, -1, -12]

    */

    public int maxProduct(int[] nums) {
        int max = nums[0];
        int min = nums[0];
        int result = max;

        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];
            int preMax = max;
            max = Math.max(current, Math.max(current*max, current*min));
            min = Math.min(current, Math.min(current*preMax, current*min));
            result = Math.max(max, result);
        }
        return result;
    }
}