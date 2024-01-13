class Solution {
    public int minMoves(int[] nums) {
        int s = 0;
        int mi = 1 << 30;
        for (int x : nums) {
            s += x;
            mi = Math.min(mi, x);
        }
        return s - mi * nums.length;
    }
}