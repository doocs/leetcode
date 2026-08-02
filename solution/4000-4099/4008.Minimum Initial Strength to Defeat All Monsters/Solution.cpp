class Solution {
public:
    long long minInitialStrength(vector<int>& monsters, vector<vector<int>>& boosts) {
        int n = monsters.size();
        vector<long long> d(n + 1);
        for (auto& b : boosts) {
            d[b[0]] += b[2];
            d[b[1] + 1] -= b[2];
        }

        auto check = [&](long long v) -> bool {
            long long bonus = 0;
            for (int i = 0; i < n; i++) {
                bonus += d[i];
                if (v + bonus < monsters[i]) {
                    return false;
                }
                v -= monsters[i];
                if (v < 0) {
                    v = 0;
                }
            }
            return true;
        };

        long long left = 0, right = 1000000000000000LL;
        while (left < right) {
            long long mid = (left + right) / 2;
            if (check(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
};