class Solution {
    public boolean isConsecutive(int[] nums) {
        int mi = nums[0];
        int mx = nums[0];
        Set<Integer> s = new HashSet<>();
        for (int v : nums) {
            mi = Math.min(mi, v);
            mx = Math.max(mx, v);
            s.add(v);
        }
        int n = nums.length;
        return s.size() == n && mx == mi + n - 1;
    }
}