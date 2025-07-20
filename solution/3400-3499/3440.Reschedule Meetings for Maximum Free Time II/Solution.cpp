class Solution {
public:
    int maxFreeTime(int eventTime, vector<int>& startTime, vector<int>& endTime) {
        int n = startTime.size();
        vector<int> pre(n), suf(n);

        pre[0] = startTime[0];
        suf[n - 1] = eventTime - endTime[n - 1];

        for (int i = 1; i < n; ++i) {
            pre[i] = max(pre[i - 1], startTime[i] - endTime[i - 1]);
        }

        for (int i = n - 2; i >= 0; --i) {
            suf[i] = max(suf[i + 1], startTime[i + 1] - endTime[i]);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int l = (i == 0) ? 0 : endTime[i - 1];
            int r = (i == n - 1) ? eventTime : startTime[i + 1];
            int w = endTime[i] - startTime[i];
            ans = max(ans, r - l - w);

            if (i > 0 && pre[i - 1] >= w) {
                ans = max(ans, r - l);
            } else if (i + 1 < n && suf[i + 1] >= w) {
                ans = max(ans, r - l);
            }
        }

        return ans;
    }
};
