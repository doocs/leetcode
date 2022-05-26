class Solution {
    public int minFlips(int a, int b, int c) {
        int ans = 0;
        for (int i = 0; i < 31; ++i) {
            int x = (a >> i) & 1, y = (b >> i) & 1, z = (c >> i) & 1;
            if ((x | y) == z) {
                continue;
            }
            if (x == 1 && y == 1 && z == 0) {
                ++ans;
            }
            ++ans;
        }
        return ans;
    }
}