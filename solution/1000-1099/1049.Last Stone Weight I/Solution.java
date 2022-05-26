class Solution {
    public int lastStoneWeightII(int[] stones) {
        int sum = Arrays.stream(stones).sum();
        int n = sum >> 1;
        int[] f = new int[n + 1];
        for (int stone : stones) {
            for (int i = n; i >= stone; --i) {
                f[i] = Math.max(f[i], f[i - stone] + stone);
            }
        }
        return sum - 2 * f[n];
    }
}
