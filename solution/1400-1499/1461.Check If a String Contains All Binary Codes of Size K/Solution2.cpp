class Solution {
public:
    bool hasAllCodes(string s, int k) {
        int n = s.size();
        int m = 1 << k;
        if (n - k + 1 < m) {
            return false;
        }
        bool ss[m];
        memset(ss, false, sizeof(ss));
        int x = stoi(s.substr(0, k), nullptr, 2);
        ss[x] = true;
        for (int i = k; i < n; ++i) {
            int a = (s[i - k] - '0') << (k - 1);
            int b = s[i] - '0';
            x = (x - a) << 1 | b;
            ss[x] = true;
        }
        return all_of(ss, ss + m, [](bool v) { return v; });
    }
};
