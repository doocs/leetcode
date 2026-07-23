class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int mx = 0;
        for (int x : nums) {
            mx = Math.max(mx, x);
        }
        mx <<= 1;

        boolean[] st = new boolean[mx];
        for (int a : nums) {
            for (int b : nums) {
                st[a ^ b] = true;
            }
        }

        int[] s = new int[mx];
        for (int ab = 0; ab < mx; ab++) {
            if (st[ab]) {
                for (int c : nums) {
                    s[ab ^ c] = 1;
                }
            }
        }

        int ans = 0;
        for (int v : s) {
            ans += v;
        }
        return ans;
    }
}