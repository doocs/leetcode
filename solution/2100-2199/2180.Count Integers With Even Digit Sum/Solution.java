class Solution {
    public int countEven(int num) {
        int ans = 0;
        for (int i = 1; i <= num; ++i) {
            int j = i, t = 0;
            while (j > 0) {
                t += j % 10;
                j /= 10;
            }
            if (t % 2 == 0) {
                ++ans;
            }
        }
        return ans;
    }
}