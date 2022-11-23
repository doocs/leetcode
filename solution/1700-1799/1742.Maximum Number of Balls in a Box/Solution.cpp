class Solution {
public:
    int countBalls(int lowLimit, int highLimit) {
        int cnt[50] = {0};
        int ans = 0;
        for (int i = lowLimit; i <= highLimit; ++i) {
            int y = 0;
            for (int x = i; x; x /= 10) {
                y += x % 10;
            }
            ans = max(ans, ++cnt[y]);
        }
        return ans;
    }
};