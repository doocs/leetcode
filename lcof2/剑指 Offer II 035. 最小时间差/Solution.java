class Solution {
    public int findMinDifference(List<String> timePoints) {
        if (timePoints.size() > 1440) {
            return 0;
        }
        int n = timePoints.size();
        int[] nums = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            String[] t = timePoints.get(i).split(":");
            nums[i] = Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
        }
        Arrays.sort(nums, 0, n);
        nums[n] = nums[0] + 1440;
        int ans = 1 << 30;
        for (int i = 1; i <= n; ++i) {
            ans = Math.min(ans, nums[i] - nums[i - 1]);
        }
        return ans;
    }
}