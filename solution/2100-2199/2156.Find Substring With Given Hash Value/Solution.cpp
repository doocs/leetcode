class Solution {
public:
    string subStrHash(string s, int power, int modulo, int k, int hashValue) {
        long long h = 0, p = 1;
        int n = s.size();
        for (int i = n - 1; i >= n - k; --i) {
            int val = s[i] - 'a' + 1;
            h = ((h * power % modulo) + val) % modulo;
            if (i != n - k) {
                p = p * power % modulo;
            }
        }
        int j = n - k;
        for (int i = n - k - 1; i >= 0; --i) {
            int pre = s[i + k] - 'a' + 1;
            int cur = s[i] - 'a' + 1;
            h = ((h - pre * p % modulo + modulo) * power % modulo + cur) % modulo;
            if (h == hashValue) {
                j = i;
            }
        }
        return s.substr(j, k);
    }
};