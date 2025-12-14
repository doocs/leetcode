class Solution {
public:
    long long minMoves(vector<int>& balance) {
        long long sum = 0;
        for (int b : balance) {
            sum += b;
        }
        if (sum < 0) {
            return -1;
        }

        int n = balance.size();
        int mn = balance[0];
        int idx = 0;
        for (int i = 1; i < n; i++) {
            if (balance[i] < mn) {
                mn = balance[i];
                idx = i;
            }
        }

        if (mn >= 0) {
            return 0;
        }

        int need = -mn;
        long long ans = 0;

        for (int j = 1; j < n; j++) {
            int a = balance[(idx - j + n) % n];
            int b = balance[(idx + j) % n];

            int c1 = min(a, need);
            need -= c1;
            ans += 1LL * c1 * j;

            int c2 = min(b, need);
            need -= c2;
            ans += 1LL * c2 * j;
        }

        return ans;
    }
};
