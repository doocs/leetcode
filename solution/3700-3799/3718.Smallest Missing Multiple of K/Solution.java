class Solution {
    public int missingMultiple(int[] nums, int k) {
        boolean[] s = new boolean[101];
        for (int x : nums) {
            s[x] = true;
        }
        for (int i = 1;; ++i) {
            int x = k * i;
            if (x >= s.length || !s[x]) {
                return x;
            }
        }
    }
}
