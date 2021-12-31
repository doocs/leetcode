class Solution {
    public int maxNonOverlapping(int[] nums, int target) {
        int i = 0, n = nums.length;
        int ans = 0;
        while (i < n) {
            int s = 0;
            Set<Integer> seen = new HashSet<>();
            seen.add(0);
            while (i < n) {
                s += nums[i];
                if (seen.contains(s - target)) {
                    ++ans;
                    break;
                }
                ++i;
                seen.add(s);
            }
            ++i;
        }
        return ans;
    }
}