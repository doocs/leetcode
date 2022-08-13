class Solution {
public:
    int countBalls(int lowLimit, int highLimit) {
        vector<int> counter(60);
        int ans = 0;
        for (int i = lowLimit; i <= highLimit; ++i) {
            int s = 0, j = i;
            while (j) {
                s += (j % 10);
                j /= 10;
            }
            ++counter[s];
            ans = max(ans, counter[s]);
        }
        return ans;
    }
};