class Solution {
public:
    int maximumRequests(int n, vector<vector<int>>& requests) {
        int ans = 0, m = requests.size();
        for (int mask = 0; mask < 1 << m; ++mask) {
            int cnt = __builtin_popcount(mask);
            if (ans < cnt && check(mask, requests)) ans = cnt;
        }
        return ans;
    }

    bool check(int x, vector<vector<int>>& requests) {
        vector<int> d(21);
        for (int i = 0; i < requests.size(); ++i) {
            if ((x >> i) & 1) {
                --d[requests[i][0]];
                ++d[requests[i][1]];
            }
        }
        for (int& v : d)
            if (v) return 0;
        return 1;
    }
};