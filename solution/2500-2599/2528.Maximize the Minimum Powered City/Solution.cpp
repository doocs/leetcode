class Solution {
public:
    long long maxPower(vector<int>& stations, int r, int k) {
        int n = stations.size();
        long d[n + 1];
        memset(d, 0, sizeof d);
        for (int i = 0; i < n; ++i) {
            int left = max(0, i - r), right = min(i + r, n - 1);
            d[left] += stations[i];
            d[right + 1] -= stations[i];
        }
        long s[n + 1];
        s[0] = d[0];
        for (int i = 1; i < n + 1; ++i) {
            s[i] = s[i - 1] + d[i];
        }
        auto check = [&](long x, int k) {
            memset(d, 0, sizeof d);
            long t = 0;
            for (int i = 0; i < n; ++i) {
                t += d[i];
                long dist = x - (s[i] + t);
                if (dist > 0) {
                    if (k < dist) {
                        return false;
                    }
                    k -= dist;
                    int j = min(i + r, n - 1);
                    int left = max(0, j - r), right = min(j + r, n - 1);
                    d[left] += dist;
                    d[right + 1] -= dist;
                    t += dist;
                }
            }
            return true;
        };
        long left = 0, right = 1e12;
        while (left < right) {
            long mid = (left + right + 1) >> 1;
            if (check(mid, k)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
};