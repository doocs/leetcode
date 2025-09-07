class Solution {
public:
    long long minOperations(vector<vector<int>>& queries) {
        auto f = [&](long long x) {
            long long res = 0;
            long long p = 1;
            int i = 1;
            while (p <= x) {
                long long cnt = min(p * 4 - 1, x) - p + 1;
                res += cnt * i;
                i++;
                p *= 4;
            }
            return res;
        };

        long long ans = 0;
        for (auto& q : queries) {
            int l = q[0], r = q[1];
            long long s = f(r) - f(l - 1);
            long long mx = f(r) - f(r - 1);
            ans += max((s + 1) / 2, mx);
        }
        return ans;
    }
};
