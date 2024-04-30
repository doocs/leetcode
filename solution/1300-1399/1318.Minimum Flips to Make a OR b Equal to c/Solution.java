class Solution {
    public int minFlips(int a, int b, int c) {
        int ans = 0;
        for (int i = 0; i < 32; ++i) {
            int x = a >> i & 1, y = b >> i & 1, z = c >> i & 1;
            ans += z == 0 ? x + y : (x == 0 && y == 0 ? 1 : 0);
        }
        return ans;
    }
}