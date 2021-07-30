class Solution {
public:
    int findPoisonedDuration(vector<int>& timeSeries, int duration) {
        int n = timeSeries.size(), res = duration;
        for (int i = 0; i < n - 1; ++i) {
            res += min(duration, timeSeries[i + 1] - timeSeries[i]);
        }
        return res;
    }
};