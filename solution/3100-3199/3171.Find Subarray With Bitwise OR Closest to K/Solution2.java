class Solution {
    public int minimumDifference(int[] nums, int k) {
        int ans = Math.abs(nums[0] - k);
        Set<Integer> pre = new HashSet<>();
        pre.add(nums[0]);
        for (int x : nums) {
            Set<Integer> cur = new HashSet<>();
            for (int y : pre) {
                cur.add(x & y);
            }
            cur.add(x);
            for (int y : cur) {
                ans = Math.min(ans, Math.abs(y - k));
            }
            pre = cur;
        }
        return ans;
    }
}