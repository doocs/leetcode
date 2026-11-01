class Solution {
    public int minMoves(int[] nums) {
        long s = 0;
        int mi = nums[0];
        for (int x : nums) {
            s += x;
            mi = Math.min(mi, x);
        }
        return (int) (s - (long) mi * nums.length);
    }
}