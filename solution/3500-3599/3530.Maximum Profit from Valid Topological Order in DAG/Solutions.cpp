class Solution {
    public:
        int maxProfit(int n, vector<vector<int>>& edges, vector<int>& score) {
            vector<int> prereq(n, 0);
            for (auto &e : edges) {
                int u = e[0], v = e[1];
                prereq[v] |= (1 << u);
            }
            
            int N = 1 << n;
            vector<int> dp(N, INT_MIN);
            dp[0] = 0;
            
            for (int mask = 0; mask < N; ++mask) {
                if (dp[mask] < 0) continue;             
                int pos = __builtin_popcount(mask) + 1; 
                int free = (~mask) & (N - 1);
                for (int i = 0; i < n; ++i) {
                    if ((free & (1 << i)) 
                        && (mask & prereq[i]) == prereq[i]) {
                        int m2 = mask | (1 << i);
                        dp[m2] = max(dp[m2], dp[mask] + score[i] * pos);
                    }
                }
            }
            
            return dp[N - 1];
        }
    };