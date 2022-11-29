/**
Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.

 

Example 1:

Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
Explanation: 
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
The distinct triplets are [-1,0,1] and [-1,-1,2].
Notice that the order of the output and the order of the triplets does not matter.
Example 2:

Input: nums = [0,1,1]
Output: []
Explanation: The only possible triplet does not sum up to 0.
Example 3:

Input: nums = [0,0,0]
Output: [[0,0,0]]
Explanation: The only possible triplet sums up to 0.

Algorithom:
1. 先从小到大sort array
2. sort之后对于每一个当前的数， 在它之后的所有array中找two sum （如果当前数与前一个数一样，skip）
3. 对于找two sum， 由于已经sort好了， 所以用两个pointer一个left 一个right，如果当前两个指针指的数大于target two sum， right-pointer往右移动，小的话left-pointer左移

Note:
List<List<Integer> 2DList = new LinkedList();  可以直接new linkedList！！
Inner List可以直接用Arrays.asList(a, b, c) -> [[a, b, c]]

Complexity:
1. Time: O(N^2)
2. Space: O(N)
*/
class Solution {
    /**

        [-1, 0,  1,  2,-1,-4]
        [-4, -1, -1, 1, 0, 2]

    */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new LinkedList();

        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i-1])) {
                int left_pointer = i + 1;
                int right_pointer = nums.length - 1;
                int target = 0 - nums[i];

                while(left_pointer < right_pointer) {
                    if (nums[left_pointer] + nums[right_pointer] == target) {
                        result.add(Arrays.asList(nums[i], nums[left_pointer], nums[right_pointer]));

                        while(left_pointer < right_pointer && nums[left_pointer] == nums[left_pointer+1]) {
                            left_pointer++;
                        }
                        while(left_pointer < right_pointer && nums[right_pointer] == nums[right_pointer-1]){
                            right_pointer--;
                        }

                        left_pointer++;
                        right_pointer--;
                        
                    } else if (nums[left_pointer] + nums[right_pointer] > target ) {
                        right_pointer--;
                    } else {
                        left_pointer++;
                    }
                }
            }
        }

        return result;
    }
}