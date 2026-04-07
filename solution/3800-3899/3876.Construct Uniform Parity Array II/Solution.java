class Solution {
    public boolean uniformArray(int[] nums1) {
        final int inf = Integer.MAX_VALUE;
        int mn = inf;
        for (int x : nums1) {
            if (x % 2 == 1) {
                mn = Math.min(mn, x);
            }
        }
        for (int x : nums1) {
            if (x % 2 == 0 && mn != inf && x < mn) {
                return false;
            }
        }
        return true;
    }
}
