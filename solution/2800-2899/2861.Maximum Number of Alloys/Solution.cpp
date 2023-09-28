class Solution {
public:
    int maxNumberOfAlloys(int n, int k, int budget, vector<vector<int>>& composition, vector<int>& stock, vector<int>& cost) {
        auto isValid = [&](long long target) {
            for (int i = 0; i < k; i++) {
                long long remain = budget;
                auto currMachine = composition[i];
                for (int j = 0; j < n && remain >= 0; j++) {
                    long long need = max(0LL, target * currMachine[j] - stock[j]);
                    remain -= need * cost[j];
                }
                if (remain >= 0) {
                    return true;
                }
            }
            return false;
        };
        long long l = 0, r = budget + stock[0];
        while (l < r) {
            long long mid = (l + r + 1) >> 1;
            if (isValid(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
};