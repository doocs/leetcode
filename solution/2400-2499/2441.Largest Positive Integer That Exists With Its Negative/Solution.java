class Solution {
    public int findMaxK(int[] nums) {
        int ans = -1;
        Set<Integer> s = new HashSet<>();
        for (int x : nums) {
            s.add(x);
        }
        for (int x : s) {
            if (s.contains(-x)) {
                ans = Math.max(ans, x);
            }
        }
        return ans;
    }
}