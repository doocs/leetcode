class Solution {
    public int findMaxK(int[] nums) {
        int ans = -1;
        Set<Integer> s = new HashSet<>();
        for (int v : nums) {
            s.add(v);
        }
        for (int v : s) {
            if (s.contains(-v)) {
                ans = Math.max(ans, v);
            }
        }
        return ans;
    }
}