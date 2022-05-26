class Solution {
    public int tribonacci(int n) {
        int[] f = new int[]{0, 1, 1, 2};
        for (int i = 4; i <= n; ++i) {
            f[i % 4] = f[(i - 1) % 4] + f[(i - 2) % 4] + f[(i - 3) % 4];
        }
        return f[n % 4];
    }
}
