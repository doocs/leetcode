class Solution {
public:
    long long maxSubarrays(int n, vector<vector<int>>& conflictingPairs) {
        vector<vector<int>> g(n + 1);
        for (auto& pair : conflictingPairs) {
            int a = pair[0], b = pair[1];
            if (a > b) {
                swap(a, b);
            }
            g[a].push_back(b);
        }

        vector<long long> cnt(n + 2, 0);
        long long ans = 0, add = 0;
        int b1 = n + 1, b2 = n + 1;

        for (int a = n; a > 0; --a) {
            for (int b : g[a]) {
                if (b < b1) {
                    b2 = b1;
                    b1 = b;
                } else if (b < b2) {
                    b2 = b;
                }
            }
            ans += b1 - a;
            cnt[b1] += b2 - b1;
            add = max(add, cnt[b1]);
        }

        ans += add;
        return ans;
    }
};
