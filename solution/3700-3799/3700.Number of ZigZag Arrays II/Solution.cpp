class Solution {
public:
    int zigZagArrays(int n, int l, int r) {
        const int mod = 1e9 + 7;
        int m = r - l + 1;
        int size = 2 * m;
        vector<vector<long long>> trans(size, vector<long long>(size));
        for (int x = 0; x < m; ++x) {
            for (int y = 0; y < x; ++y) {
                trans[y][m + x] = 1;
            }
        }
        for (int x = 0; x < m; ++x) {
            for (int y = x + 1; y < m; ++y) {
                trans[m + y][x] = 1;
            }
        }
        auto power = matrixPow(trans, n - 1, mod);
        vector<long long> init(size, 1);
        auto result = multiply(power, init, mod);
        long long ans = 0;
        for (long long v : result) {
            ans = (ans + v) % mod;
        }
        return ans;
    }

private:
    vector<long long> multiply(vector<vector<long long>>& mat, vector<long long>& vec, int mod) {
        int n = mat.size();
        vector<long long> res(n);
        for (int i = 0; i < n; ++i) {
            long long sum = 0;
            for (int j = 0; j < n; ++j) {
                sum = (sum + mat[i][j] * vec[j]) % mod;
            }
            res[i] = sum;
        }
        return res;
    }

    vector<vector<long long>> multiply(
        vector<vector<long long>>& a, vector<vector<long long>>& b, int mod) {
        int n = a.size();
        vector<vector<long long>> res(n, vector<long long>(n));
        for (int i = 0; i < n; ++i) {
            for (int k = 0; k < n; ++k) {
                if (a[i][k] == 0) {
                    continue;
                }
                long long aik = a[i][k];
                for (int j = 0; j < n; ++j) {
                    if (b[k][j] == 0) {
                        continue;
                    }
                    res[i][j] = (res[i][j] + aik * b[k][j]) % mod;
                }
            }
        }
        return res;
    }

    vector<vector<long long>> matrixPow(vector<vector<long long>>& mat, long long exp, int mod) {
        int n = mat.size();
        vector<vector<long long>> res(n, vector<long long>(n));
        for (int i = 0; i < n; ++i) {
            res[i][i] = 1;
        }
        while (exp > 0) {
            if (exp & 1) {
                res = multiply(res, mat, mod);
            }
            mat = multiply(mat, mat, mod);
            exp >>= 1;
        }
        return res;
    }
};
