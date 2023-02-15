class Solution {
    public int maxSumDivThree(int[] nums) {
        int[] f = new int[3];
        for (int x : nums) {
            int a = f[0] + x, b = f[1] + x, c = f[2] + x;
            f[a % 3] = Math.max(f[a % 3], a);
            f[b % 3] = Math.max(f[b % 3], b);
            f[c % 3] = Math.max(f[c % 3], c);
        }
        return f[0];
    }
}