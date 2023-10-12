class Solution {
public:
    long long maximumSumOfHeights(vector<int>& maxHeights) {
        long long ans = 0;
        int n = maxHeights.size();
        for (int i = 0; i < n; ++i) {
            long long t = maxHeights[i];
            int y = t;
            for (int j = i - 1; ~j; --j) {
                y = min(y, maxHeights[j]);
                t += y;
            }
            y = maxHeights[i];
            for (int j = i + 1; j < n; ++j) {
                y = min(y, maxHeights[j]);
                t += y;
            }
            ans = max(ans, t);
        }
        return ans;
    }
};