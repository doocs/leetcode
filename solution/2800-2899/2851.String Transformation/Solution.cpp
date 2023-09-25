class Solution {
const int M = 1000000007;

int add(int x, int y) {
    if ((x += y) >= M) {
        x -= M;
    }
    return x;
}

int mul(long long x, long long y) {
    return x * y % M;
}
    
    
vector<int> getz(const string& s) {
    const int n = s.length();
    vector<int> z(n);
    for (int i = 1, left = 0, right = 0; i < n; ++i) {
        if (i <= right && z[i - left] <= right - i) {
            z[i] = z[i - left];
        } else {
            for (z[i] = max(0, right - i + 1); i + z[i] < n && s[i + z[i]] == s[z[i]]; ++z[i])
            ;  
        }
        if (i + z[i] - 1 > right) {
            left = i;
            right = i + z[i] - 1;
        }
    }
    return z;
}
    
vector<vector<int>> mul(const vector<vector<int>> &a, const vector<vector<int>> &b) {
    const int m = a.size(), n = a[0].size(), p = b[0].size();
    vector<vector<int>> r(m, vector<int>(p));
    for (int i = 0; i < m; ++i) {
        for (int j = 0; j < n; ++j) {
            for (int k = 0; k < p; ++k) {
                r[i][k] = add(r[i][k], mul(a[i][j], b[j][k]));
            }
        }
    }
    return r;
}
    
vector<vector<int>> pow(const vector<vector<int>> &a, long long y) {
    const int n = a.size();
    vector<vector<int>> r(n, vector<int>(n));
    for (int i = 0; i < n; ++i) {
        r[i][i] = 1;
    }
    auto x = a;
    for (; y; y >>= 1) {
        if (y & 1) {
            r = mul(r, x);
        }
        x = mul(x, x);
    }
    return r;
}
    
public:
    int numberOfWays(string s, string t, long long k) {
        const int n = s.length();
        const auto dp = pow({{0, 1}, {n - 1, n - 2}}, k)[0];
        s.append(t);
        s.append(t);
        const auto z = getz(s);
        const int m = n + n;
        int r = 0;
        for (int i = n; i < m; ++i) {
            if (z[i] >= n) {
                r = add(r, dp[!!(i - n)]);
            }
        }
        return r;
    }
};