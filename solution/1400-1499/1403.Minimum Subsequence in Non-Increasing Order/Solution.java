class Solution {
    public List<Integer> minSubsequence(int[] nums) {
        Arrays.sort(nums);
        List<Integer> ans = new ArrayList<>();
        int s = 0;
        for (int v : nums) {
            s += v;
        }
        int t = 0;
        for (int i = nums.length - 1; i >= 0; --i) {
            t += nums[i];
            ans.add(nums[i]);
            if (t > s - t) {
                break;
            }
        }
        return ans;
    }
}