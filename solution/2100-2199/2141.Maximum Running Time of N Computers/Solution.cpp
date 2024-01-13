class Solution {
public:
    long long maxRunTime(int n, vector<int>& batteries) {
        long long l = 0, r = 0;
        for (int x : batteries) {
            r += x;
        }
        while (l < r) {
            long long mid = (l + r + 1) >> 1;
            long long s = 0;
            for (int x : batteries) {
                s += min(1LL * x, mid);
            }
            if (s >= n * mid) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
};