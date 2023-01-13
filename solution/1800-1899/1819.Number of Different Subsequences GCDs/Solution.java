class Solution {
    public int countDifferentSubsequenceGCDs(int[] nums) {
        int mx = Arrays.stream(nums).max().getAsInt();
        boolean[] vis = new boolean[mx + 1];
        for (int x : nums) {
            vis[x] = true;
        }
        int ans = 0;
        for (int x = 1; x <= mx; ++x) {
            int g = 0;
            for (int y = x; y <= mx; y += x) {
                if (vis[y]) {
                    g = gcd(g, y);
                    if (x == g) {
                        ++ans;
                        break;
                    }
                }
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}