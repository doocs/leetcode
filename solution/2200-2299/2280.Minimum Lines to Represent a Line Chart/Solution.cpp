class Solution {
public:
    int minimumLines(vector<vector<int>>& stockPrices) {
        sort(stockPrices.begin(), stockPrices.end());
        int dx = 0, dy = 1;
        int ans = 0;
        for (int i = 1; i < stockPrices.size(); ++i) {
            int x = stockPrices[i - 1][0], y = stockPrices[i - 1][1];
            int x1 = stockPrices[i][0], y1 = stockPrices[i][1];
            int dx1 = x1 - x, dy1 = y1 - y;
            if ((long long) dy * dx1 != (long long) dx * dy1) ++ans;
            dx = dx1;
            dy = dy1;
        }
        return ans;
    }
};