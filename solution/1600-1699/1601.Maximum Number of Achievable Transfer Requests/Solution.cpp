class Solution {
public:
    int maximumRequests(int n, vector<vector<int>>& requests) {
        int m = requests.size();
        int ans = 0;
        auto check = [&](int mask) -> bool {
            int cnt[n];
            memset(cnt, 0, sizeof(cnt));
            for (int i = 0; i < m; ++i) {
                if (mask >> i & 1) {
                    int f = requests[i][0], t = requests[i][1];
                    --cnt[f];
                    ++cnt[t];
                }
            }
            for (int v : cnt) {
                if (v) {
                    return false;
                }
            }
            return true;
        };
        for (int mask = 0; mask < 1 << m; ++mask) {
            int cnt = __builtin_popcount(mask);
            if (ans < cnt && check(mask)) {
                ans = cnt;
            }
        }
        return ans;
    }
};