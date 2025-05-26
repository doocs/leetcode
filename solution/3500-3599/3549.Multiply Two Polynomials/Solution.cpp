class Solution {
    using cd = complex<double>;

    void fft(vector<cd>& a, bool invert) {
        int n = a.size();
        for (int i = 1, j = 0; i < n; ++i) {
            int bit = n >> 1;
            for (; j & bit; bit >>= 1) j ^= bit;
            j ^= bit;
            if (i < j) swap(a[i], a[j]);
        }
        for (int len = 2; len <= n; len <<= 1) {
            double ang = 2 * M_PI / len * (invert ? -1 : 1);
            cd wlen(cos(ang), sin(ang));
            for (int i = 0; i < n; i += len) {
                cd w(1, 0);
                int half = len >> 1;
                for (int j = 0; j < half; ++j) {
                    cd u = a[i + j];
                    cd v = a[i + j + half] * w;
                    a[i + j] = u + v;
                    a[i + j + half] = u - v;
                    w *= wlen;
                }
            }
        }
        if (invert)
            for (cd& x : a) x /= n;
    }

public:
    vector<long long> multiply(vector<int>& poly1, vector<int>& poly2) {
        if (poly1.empty() || poly2.empty()) return {};
        int m = poly1.size() + poly2.size() - 1;
        int n = 1;
        while (n < m) n <<= 1;

        vector<cd> fa(n), fb(n);
        for (int i = 0; i < n; ++i) {
            fa[i] = i < poly1.size() ? cd(poly1[i], 0) : cd(0, 0);
            fb[i] = i < poly2.size() ? cd(poly2[i], 0) : cd(0, 0);
        }

        fft(fa, false);
        fft(fb, false);
        for (int i = 0; i < n; ++i) fa[i] *= fb[i];
        fft(fa, true);

        vector<long long> res(m);
        for (int i = 0; i < m; ++i) res[i] = llround(fa[i].real());
        return res;
    }
};
