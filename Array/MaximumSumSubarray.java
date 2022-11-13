/*
2461. Maximum Sum of Distinct Subarrays With Length K

You are given an integer array nums and an integer k. Find the maximum subarray sum of all the subarrays of nums that meet the following conditions:
The length of the subarray is k, and
All the elements of the subarray are distinct.
Return the maximum subarray sum of all the subarrays that meet the conditions. If no subarray meets the conditions, return 0.
A subarray is a contiguous non-empty sequence of elements within an array.

Example 1:
Input: nums = [1,5,4,2,9,9,9], k = 3
Output: 15
Explanation: The subarrays of nums with length 3 are:
- [1,5,4] which meets the requirements and has a sum of 10.
- [5,4,2] which meets the requirements and has a sum of 11.
- [4,2,9] which meets the requirements and has a sum of 15.
- [2,9,9] which does not meet the requirements because the element 9 is repeated.
- [9,9,9] which does not meet the requirements because the element 9 is repeated.
We return 15 because it is the maximum subarray sum of all the subarrays that meet the conditions

Example 2:
Input: nums = [4,4,4], k = 3
Output: 0
Explanation: The subarrays of nums with length 3 are:
- [4,4,4] which does not meet the requirements because the element 4 is repeated.
We return 0 because no subarrays meet the conditions.

Algorithom: Sliding window + HashMap
Approach is to store the elements in a map and using sliding window technique
check if the size of map is equal to k on each window. The window which has map size equal to k
is having distict elements.

1. Store first k elements in the map.
2. Then keep traversing through the array and adding the new one to the map and 
removing the old one(i-k th) element form the map if its count is zero.
3. Do same for the total sum, add ith value and remove i-k th value.
4. Maximize ans by taking max sum value. 


Complexcity:
Time - O(N)
Space - O(N)
*/

// 总结：
// 1. "Distinct" "Unique" -> HashSet
// 2. "Frequency" + "Distinct" -> HashMap (因为HashMap的key不能重复)
// 3. “Continouse" + "Sub" -> Sliding Window


class Solution {
    public long maximumSubarraySum(int[] A, int k) {
    HashMap<Integer, Integer> map = new HashMap<>();
    long max = 0;
    long sum = 0;

    for (int i = 0; i < A.length; i++){
        // 一直加
        sum +=A[i];
        // key: 在index i 对应的数字， value: 该数字的frequancy
        map.put(A[i], map.getOrDefault(A[i],0) + 1);

        // 当 index 超过 k 时， k = 3, i = 0, 1
        if (i >= k - 1){
            //如果map的size为k
            if (map.size() == k) {
                // max 为 当前 sum 和 max 的最大值
                max = Math.max(max, sum);
            }
            //如果map的size不为k, k = 3, i = 4, A[2], 相当于window的start，减去原来的第一个
            sum -= A[i - k + 1];
            // map对应被减去的数的 frequency - 1
            map.put(A[i - k + 1], map.get(A[i - k + 1]) - 1);
            // 当map里的 被减去的数的frequency为0时， 把这个数remove掉
            if (map.get(A[i - k + 1]) == 0) {
                map.remove(A[i - k + 1]);
            } 
        }
    }
    return max;

    //     if (nums.length < k) {
    //         return 0;
    //     }
        
    //     int max = 0;
    //     int counter = 0;
    //     int left_pointer = 0;
    //     int right_pointer = 0;
    //     int current_sum = 0;
    //     Set<Integer> window = new HashSet<>();
        
    //      while(right_pointer < nums.length) {
    //         int current_left = nums[left_pointer];
    //         int current_right = nums[right_pointer];
             
    //         if (!window.contains(current_right)) {
    //             window.add(current_right);
    //         } else {
    //             current_sum -= current_right;
    //             current_right = 0;
    //             if (nums.length == k) {
    //                 return 0;
    //             }
    //         }
             
    //         if (right_pointer - left_pointer < k) {
    //             current_sum += current_right;
    //             right_pointer++;
    //         } else {
    //             current_sum -= current_left;
    //             left_pointer++;
    //             current_sum += current_right;
    //             right_pointer++;
    //         }
             
    //         System.out.println(current_sum);
    //         max = Math.max(max, current_sum);
    //     }
        
    //     return (long) max;
    }
}