class Solution {
    public boolean containsDuplicate(int[] nums) {
        // // Solution 1
        // if (nums.length == 0) {
        //     return false;
        // }
        // for (int i = 0; i< nums.length; i++) {
        //     for (int j = i+1; j < nums.length; j++) {
        //          if (nums[i] == nums[j]) {
        //              return true;
        //          }
        //     }
        // }
        // return false;
        // Solution 2
        Set<Integer> h = new HashSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            h.add(nums[i]);
        }
        return h.size() != nums.length;
        
    }
}