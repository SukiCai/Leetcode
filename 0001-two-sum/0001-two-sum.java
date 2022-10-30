class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        
        if (nums.length <= 1){
            return new int[] {0, 0};
        }
        
        
        for (int i = 0; i < nums.length; i++) {
            int left = target - nums[i];
            if (map.containsKey(left)) {
                return new int[] {i, map.get(left)};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No matched founded");
    }
}