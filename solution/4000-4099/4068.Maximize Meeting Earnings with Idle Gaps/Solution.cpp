class Solution {
public:
    long long maxEarnings(vector<vector<int>>& meetings) {
        int n = meetings.size();
        sort(meetings.begin(), meetings.end(), [](auto& a, auto& b) {
            return a[1] < b[1];
        });

        vector<long long> preMax(n + 1, LLONG_MIN / 2);
        long long ans = 0;

        for (int i = 0; i < n; i++) {
            int start = meetings[i][0];
            int end = meetings[i][1];
            int revenue = meetings[i][2];

            long long val = revenue;
            if (start >= meetings[0][1]) {
                int j = upper_bound(meetings.begin(), meetings.begin() + i, start,
                            [](int x, const vector<int>& y) {
                                return x < y[1];
                            })
                    - meetings.begin();
                val += preMax[j] + start;
            }

            ans = max(ans, val);
            preMax[i + 1] = max(preMax[i], val - end);
        }

        return ans;
    }
};