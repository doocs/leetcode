class Solution {
public:
    int minCostToEqualizeArray(std::vector<int> &A, int c1, int c2) {
        int ma = *std::max_element(A.begin(), A.end());
        int mi = *std::min_element(A.begin(), A.end());
        int n = A.size(), mod = 1000000007;
        long long su = std::accumulate(A.begin(), A.end(), 0LL);
        long long total = 1LL * ma * n - su;

        if (c1 * 2 <= c2 || n <= 2) {
            return (total * c1) % mod;
        }

        long long op1 = std::max(0LL, (ma - mi) * 2 - total);
        long long op2 = total - op1;
        long long res = (op1 + op2 % 2) * c1 + op2 / 2 * c2;

        total += op1 / (n - 2) * n;
        op1 %= n - 2;
        op2 = total - op1;
        res = std::min(res, (op1 + op2 % 2) * c1 + op2 / 2 * c2);

        for (int i = 0; i < 2; i++) {
            total += n;
            res = std::min(res, total % 2 * c1 + total / 2 * c2);
        }

        return res % mod;
    }
};
