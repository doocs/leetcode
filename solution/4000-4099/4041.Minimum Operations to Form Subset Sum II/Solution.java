class Solution {
    public int minOperations(int[] nums, int sum) {
        int inf = (int) 1e9;
        int[] f = new int[sum + 1];
        Arrays.fill(f, inf);
        f[0] = 0;

        for (int x : nums) {
            for (int w = sum; w >= 0; --w) {
                int i = 0, y = x;
                while (y <= w) {
                    f[w] = Math.min(f[w], f[w - y] + i);
                    ++i;
                    y *= 2;
                }

                i = 1;
                y = x / 2;
                while (y > 0) {
                    int j = 0, z = y;
                    while (z <= w) {
                        f[w] = Math.min(f[w], f[w - z] + i + j);
                        ++j;
                        z *= 2;
                    }
                    ++i;
                    y /= 2;
                }
            }
        }

        return f[sum] == inf ? -1 : f[sum];
    }
}
