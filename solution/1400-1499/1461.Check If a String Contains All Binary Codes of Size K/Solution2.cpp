class Solution {
public:
    bool hasAllCodes(string s, int k) {
        int n = s.size();
        if (n - k + 1 < (1 << k)) return false;
        vector<bool> vis(1 << k);
        int num = stoi(s.substr(0, k), nullptr, 2);
        vis[num] = true;
        for (int i = k; i < n; ++i) {
            int a = (s[i - k] - '0') << (k - 1);
            int b = s[i] - '0';
            num = (num - a) << 1 | b;
            vis[num] = true;
        }
        for (bool v : vis)
            if (!v) return false;
        return true;
    }
};