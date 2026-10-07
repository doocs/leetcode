class Solution {
public:
    long long maximumStrength(vector<int>& nums, int k) {
        int n = nums.size();
        const long long inf = LLONG_MIN / 2;
        vector<long long> f((n + 1LL) * (k + 1) * 2, inf);
        auto at = [&](int i, int j, int t) -> long long& {
            return f[(i * (k + 1) + j) * 2 + t];
        };
        at(0, 0, 0) = 0;
        for (int i = 1; i <= n; i++) {
            int x = nums[i - 1];
            for (int j = 0; j <= k; j++) {
                long long sign = (j & 1) == 1 ? 1 : -1;
                long long val = sign * x * (k - j + 1);
                at(i, j, 0) = max(at(i - 1, j, 0), at(i - 1, j, 1));
                at(i, j, 1) = max(at(i, j, 1), at(i - 1, j, 1) + val);
                if (j > 0) {
                    long long t = max(at(i - 1, j - 1, 0), at(i - 1, j - 1, 1)) + val;
                    at(i, j, 1) = max(at(i, j, 1), t);
                }
            }
        }
        return max(at(n, k, 0), at(n, k, 1));
    }
};
