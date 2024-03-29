class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        if (nums.length == 0) {
            return result;
        }
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (target - nums[i] == nums[j]) {
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        return result;
    }
}

// class Solution {
//     public int[] twoSum(int[] nums, int target) {
//         int[] result = new int[2];
        
//         for (int i = 0; i < nums.length - 1; i++) {
//             int left = target - nums[i];
//             for (int j = i + 1; j < nums.length; j++) {
//                 if (left == nums[j]){
//                     result[0] = i;
//                     result[1] = j;
//                 }
//             }
//         }
//         return result;
//     }
// }