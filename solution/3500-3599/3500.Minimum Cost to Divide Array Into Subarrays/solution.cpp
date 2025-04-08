class Solution {
    public:
        long long minimumCost(vector<int>& nums, vector<int>& cost, int K) {
            int n = nums.size();
    
            long long sn[n + 1], sc[n + 1];
            sn[0] = sc[0] = 0;
            for (int i = 1; i <= n; i++) {
                sn[i] = sn[i - 1] + nums[i - 1];
                sc[i] = sc[i - 1] + cost[i - 1];
            }
    
            const long long INF = 1e18;
            long long f[n + 1];
            for (int i = 0; i <= n; i++) f[i] = INF;
            f[0] = 0;
            for (int i = 1; i <= n; i++) for (int j = 0; j < i; j++) {
                long long t = sn[i] * (sc[i] - sc[j]) + K * (sc[n] - sc[j]);
                f[i] = min(f[i], f[j] + t);
            }
    
            return f[n];
        }
    };