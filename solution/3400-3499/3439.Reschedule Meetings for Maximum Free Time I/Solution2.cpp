class Solution {
public:
    int maxFreeTime(int eventTime, int k, vector<int>& startTime, vector<int>& endTime) {
        int n = endTime.size();
        auto f = [&](int i) -> int {
            if (i == 0) {
                return startTime[0];
            }
            if (i == n) {
                return eventTime - endTime[n - 1];
            }
            return startTime[i] - endTime[i - 1];
        };
        int ans = 0, s = 0;
        for (int i = 0; i <= n; ++i) {
            s += f(i);
            if (i >= k) {
                ans = max(ans, s);
                s -= f(i - k);
            }
        }
        return ans;
    }
};
