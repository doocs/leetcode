class Solution {
    public int minOperations(int[] nums, int sum) {
        int inf = Integer.MAX_VALUE / 2;
        int[] f = new int[sum + 1];
        Arrays.fill(f, inf);
        f[0] = 0;

        for (int x : nums) {
            for (int w = sum; w >= 0; --w) {
                int i = 0, y = x;
                while (y <= w) {
                    f[w] = Math.min(f[w], f[w - y] + i);
                    ++i;
                    y <<= 1;
                }

                i = 1;
                y = x >> 1;
                while (y > 0) {
                    if (y <= w) {
                        f[w] = Math.min(f[w], f[w - y] + i);
                    }
                    ++i;
                    y >>= 1;
                }
            }
        }

        return f[sum] == inf ? -1 : f[sum];
    }
}
