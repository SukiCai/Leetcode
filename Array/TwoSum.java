/** 
Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

Example 1:

Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].

Example 2:

Input: nums = [3,2,4], target = 6
Output: [1,2]

Example 3:

Input: nums = [3,3], target = 6
Output: [0,1]


Algorithm: Sliding window
    1. Build a hashmap to store the current value and the index of the current value
    2. A for loop to traverse the whole nums array, if the (target - current value) is contains in the map keylist return the corresponding index and current index
    3. If the (target - current value) is not contains in the keylist, we put the current value and its index into the map then continue

Complexity:
- Time: O(n)
- Space: O(n)
*/

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> num_map = new HashMap<>();
        int[] result = new int[2];

        for (int i = 0; i < nums.length; i++) {
            if (num_map.containsKey(target - nums[i])) {
                result[0] = num_map.get(target-nums[i]);
                result[1] = i;
                return result;
            }
            num_map.put(nums[i], i);

        }
        return result;
    }
}