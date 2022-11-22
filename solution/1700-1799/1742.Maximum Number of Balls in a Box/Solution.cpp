class Solution {
public:
    int countBalls(int lowLimit, int highLimit) {
        int cnt[50] = {0};
        int ans = 0;
        for (int i = lowLimit; i <= highLimit; ++i) {
            int x = i, y = 0;
            for (; x; x /= 10) {
                y += x % 10;
            }
            ans = max(ans, ++cnt[y]);
        }
        return ans;
    }
};