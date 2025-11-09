class Solution {
    public int minMoves(int[] nums) {
        int n = nums.length;
        int mx = 0;
        int s = 0;
        for (int x : nums) {
            mx = Math.max(mx, x);
            s += x;
        }
        return mx * n - s;
    }
}
