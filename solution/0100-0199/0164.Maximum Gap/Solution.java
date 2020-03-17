class Solution {
    public int maximumGap(int[] nums) {
        int length = nums.length;
        if(length <2) return 0;
        Arrays.sort(nums);
        int max=0;
        for(int i = 1; i< length; i++) max = Integer.max(nums[i] - nums[i - 1], max);
        return max;
    }
}