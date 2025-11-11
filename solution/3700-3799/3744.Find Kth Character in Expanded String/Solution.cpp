class Solution {
public:
    char kthCharacter(string s, long long k) {
        stringstream ss(s);
        string w;
        while (ss >> w) {
            long long m = (1 + (long long) w.size()) * (long long) w.size() / 2;
            if (k == m) {
                return ' ';
            }
            if (k > m) {
                k -= m + 1;
            } else {
                long long cur = 0;
                for (int i = 0;; ++i) {
                    cur += i + 1;
                    if (k < cur) {
                        return w[i];
                    }
                }
            }
        }
        return ' ';
    }
};
