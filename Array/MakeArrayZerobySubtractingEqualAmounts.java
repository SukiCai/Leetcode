/**
2357. Make Array Zero by Subtracting Equal Amounts
You are given a non-negative integer array nums. In one operation, you must:

Choose a positive integer x such that x is less than or equal to the smallest non-zero element in nums.
Subtract x from every positive element in nums.
Return the minimum number of operations to make every element in nums equal to 0.


Input: nums = [1,5,0,3,5]
Output: 3
Explanation:
In the first operation, choose x = 1. Now, nums = [0,4,0,2,4].
In the second operation, choose x = 2. Now, nums = [0,2,0,0,2].
In the third operation, choose x = 2. Now, nums = [0,0,0,0,0].


Input: nums = [0]
Output: 0
Explanation: Each element in nums is already 0 so no operations are needed.

Algorithom:
1. Simplify the question to be identify how many unique "positive" number in the array, 有多少个unique的数就会至少有多少次substraction
2. Use set everytime we saw "unique"
3. Return the set size, which is the total number of unique number in the array.
*/


class Solution {
    public int minimumOperations(int[] nums) {
        HashSet<Integer> result = new HashSet<>();
        for (int num: nums) {
            if (num == 0) {
                continue;
            }
            result.add(num);
        }

        return result.size();
    }
}