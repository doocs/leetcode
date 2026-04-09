#include <vector>
#include <cmath>

using namespace std;

class Solution {
    const int MOD = 1e9 + 7;

    long long power(long long x, long long n) {
        long long res = 1;
        while (n > 0) {
            if (n & 1) res = (res * x) % MOD;
            x = (x * x) % MOD;
            n >>= 1;
        }
        return res;
    }

public:
    int xorAfterQueries(vector<int>& nums, vector<vector<int>>& queries) {
        int n = nums.size();
        int t = sqrt(n) + 1;

        vector<vector<vector<int>>> groupedQueries(t);

        for (const auto& q : queries) {
            int k = q[2];
            if (k < t) {
                groupedQueries[k].push_back(q);
            } else {
                int l = q[0], r = q[1], v = q[3];
                for (int i = l; i <= r; i += k) {
                    nums[i] = (1LL * nums[i] * v) % MOD;
                }
            }
        }

        vector<long long> dif(n + t, 1);
        for (int k = 1; k < t; k++) {
            if (groupedQueries[k].empty()) continue;

            fill(dif.begin(), dif.end(), 1);

            for (const auto& q : groupedQueries[k]) {
                int l = q[0], r = q[1], v = q[3];
                dif[l] = (dif[l] * v) % MOD;

                int nextBound = l + ((r - l) / k + 1) * k;
                if (nextBound < n + t) { 
                    dif[nextBound] = (dif[nextBound] * power(v, MOD - 2)) % MOD;
                }
            }

            for (int i = k; i < n; i++) {
                dif[i] = (dif[i] * dif[i - k]) % MOD;
            }

            for (int i = 0; i < n; i++) {
                if (dif[i] != 1) {
                    nums[i] = (1LL * nums[i] * dif[i]) % MOD;
                }
            }
        }

        int res = 0;
        for (int x : nums) {
            res ^= x;
        }

        return res;
    }
};
