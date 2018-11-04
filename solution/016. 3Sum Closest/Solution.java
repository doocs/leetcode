class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int result = nums[0]+nums[1]+nums[2];
        Arrays.sort(nums);
        for(int i = 0;i<nums.length-2;i++){
            int start = i+1,end=nums.length-1;
            while(start<end){
                int cache = nums[i]+nums[start]+nums[end];
                if(Math.abs(cache-target)<Math.abs(result-target)) result = cache;
                if(cache < target ) start++;
                else if(cache > target) end--;
                else return result;
            }
        }
        return result;
    }
}
