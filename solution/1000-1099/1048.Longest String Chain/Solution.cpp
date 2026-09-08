class Solution {
public:
    int longestStrChain(vector<string>& words) {
        ranges::sort(words, [](const string& a, const string& b) { return a.size() < b.size(); });
        int n = words.size();
        int f[n];
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            f[i] = 1;
            for (int j = 0; j < i; ++j) {
                if (check(words[j], words[i])) {
                    f[i] = max(f[i], f[j] + 1);
                }
            }
            ans = max(ans, f[i]);
        }
        return ans;
    }

    bool check(const string& a, const string& b) {
        if (a.size() + 1 != b.size()) {
            return false;
        }
        int i = 0;
        for (char c : b) {
            if (i < a.size() && a[i] == c) {
                ++i;
            }
        }
        return i == a.size();
    }
};
