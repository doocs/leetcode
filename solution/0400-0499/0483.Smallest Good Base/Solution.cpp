class Solution {
public:
    string smallestGoodBase(string n) {
        long v = stol(n);
        int mx = floor(log(v) / log(2));
        for (int m = mx; m > 1; --m) {
            int k = pow(v, 1.0 / m);
            long mul = 1, s = 1;
            for (int i = 0; i < m; ++i) {
                mul *= k;
                s += mul;
            }
            if (s == v) {
                return to_string(k);
            }
        }
        return to_string(v - 1);
    }
};