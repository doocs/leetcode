class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        int[] preSum = new int[n];
        preSum[0] = stones[0];
        for (int i = 1; i < n; ++i) {
            preSum[i] = preSum[i - 1] + stones[i];
        }
        int f = preSum[n - 1];
        for (int i = n - 2; i > 0; --i) {
            f = Math.max(f, preSum[i] - f);
        }
        return f;
    }
}