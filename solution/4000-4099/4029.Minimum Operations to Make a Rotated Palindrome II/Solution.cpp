class Solution {
    using cd = complex<double>;
    const double PI = acos(-1);

    void fft(vector<cd>& a, bool inv) {
        int n = a.size();

        for (int i = 1, j = 0; i < n; i++) {
            int bit = n >> 1;

            while (j & bit) {
                j ^= bit;
                bit >>= 1;
            }

            j ^= bit;

            if (i < j) {
                swap(a[i], a[j]);
            }
        }

        for (int len = 2; len <= n; len <<= 1) {
            double ang = 2.0 * PI / len * (inv ? -1 : 1);
            cd wlen(cos(ang), sin(ang));

            for (int i = 0; i < n; i += len) {
                cd w(1);

                for (int j = 0; j < len / 2; j++) {
                    cd u = a[i + j];
                    cd v = a[i + j + len / 2] * w;

                    a[i + j] = u + v;
                    a[i + j + len / 2] = u - v;

                    w *= wlen;
                }
            }
        }

        if (inv) {
            for (auto& x : a) {
                x /= n;
            }
        }
    }

public:
    int minOperations(string s) {
        int n = s.size();

        int size = 1;
        while (size < 2 * n) {
            size <<= 1;
        }

        vector<int> nums(n);
        for (int i = 0; i < n; i++) {
            nums[i] = s[i] - 'a';
        }

        vector<double> cost(26);

        for (int t = 0; t < 26; t++) {
            for (int z = 0; z < 26; z++) {
                int d = min(z, 26 - z);

                cost[t] += d * cos(-2.0 * PI * t * z / 26);
            }
        }

        vector<double> dp(n);

        vector<cd> a(size);
        vector<cd> b(size);

        for (int t = 0; t < 14; t++) {
            double theta = 2.0 * PI * t / 26;

            for (int i = 0; i < n; i++) {
                double angle = theta * nums[i];
                a[i] = cd(cos(angle), sin(angle));
            }

            for (int i = n; i < size; i++) {
                a[i] = 0;
            }

            fft(a, false);

            for (int i = 0; i < size; i++) {
                cd x = a[i];
                cd y = conj(a[(size - i) & (size - 1)]);

                b[i] = x * y;
                b[i] = conj(b[i]);
            }

            fft(b, false);

            double mult = (t == 0 || t == 13) ? 1.0 : 2.0;
            double factor = mult * cost[t] / size;

            for (int c = 0; c < n; c++) {
                dp[c] += factor * (b[c].real() + b[c + n].real());
            }
        }

        long long ans = LLONG_MAX;

        for (int k = 0; k < n; k++) {
            int c = (2 * k + n - 1) % n;
            long long d = llround(dp[c] / 52.0);

            ans = min(ans, k + d);
        }

        return (int) ans;
    }
};