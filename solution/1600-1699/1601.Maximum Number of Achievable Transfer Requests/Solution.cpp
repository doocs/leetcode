class Solution {
public:
    int maximumRequests(int n, vector<vector<int>>& requests) {
        int ans = 0, m = requests.size();
        for (int mask = 0; mask < (1 << m); ++mask)
        {
            int cnt = __builtin_popcount(mask);
            if (cnt <= ans) continue;
            if (check(mask, m, n, requests)) ans = cnt;
        }
        return ans;
    }

    bool check(int x, int m, int n, vector<vector<int>>& requests) {
        vector<int> delta(n);
        for (int i = 0; i < m; ++i)
        {
            if ((x >> i) & 1)
            {
                --delta[requests[i][0]];
                ++delta[requests[i][1]];
            }
        }
        for (int i = 0; i < n; ++i)
            if (delta[i]) return 0;
        return 1;
    }
};