class Solution {
public:
    int preimageSizeFZF(int k) {
        return g(k + 1) - g(k);
    }

    int g(int k) {
        long long left = 0, right = 1ll * 5 * k;
        while (left < right) {
            long long mid = (left + right) >> 1;
            if (f(mid) >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return (int) left;
    }

    int f(long x) {
        int res = 0;
        while (x) {
            x /= 5;
            res += x;
        }
        return res;
    }
};