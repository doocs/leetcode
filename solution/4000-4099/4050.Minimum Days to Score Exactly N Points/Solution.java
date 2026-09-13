class Solution {
    private static final int MX = 100001;
    private static final int[] f = new int[MX];

    static {
        Arrays.fill(f, Integer.MAX_VALUE);
        f[0] = -1;

        for (int i = 1; i < MX; i++) {
            for (int j = 1; j * (j + 1) / 2 <= i; j++) {
                int s = j * (j + 1) / 2;
                f[i] = Math.min(f[i], f[i - s] + j + 1);
            }
        }
    }

    public int minDays(int n) {
        return f[n];
    }
}