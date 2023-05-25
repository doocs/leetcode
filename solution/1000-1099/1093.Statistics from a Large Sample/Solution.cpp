class Solution {
public:
    vector<double> sampleStats(vector<int>& count) {
        auto find = [&](int i) -> int {
            for (int k = 0, t = 0;; ++k) {
                t += count[k];
                if (t >= i) {
                    return k;
                }
            }
        };
        int mi = 1 << 30, mx = -1;
        long long s = 0;
        int cnt = 0, mode = 0;
        for (int k = 0; k < count.size(); ++k) {
            if (count[k]) {
                mi = min(mi, k);
                mx = max(mx, k);
                s += 1LL * k * count[k];
                cnt += count[k];
                if (count[k] > count[mode]) {
                    mode = k;
                }
            }
        }
        double median = cnt % 2 == 1 ? find(cnt / 2 + 1) : (find(cnt / 2) + find(cnt / 2 + 1)) / 2.0;
        return vector<double>{(double) mi, (double) mx, s * 1.0 / cnt, median, (double) mode};
    }
};