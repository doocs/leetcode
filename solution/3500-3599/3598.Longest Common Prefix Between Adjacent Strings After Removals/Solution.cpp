class Solution {
public:
    vector<int> longestCommonPrefix(vector<string>& words) {
        multiset<int> ms;
        int n = words.size();
        auto calc = [&](const string& s, const string& t) {
            int m = min(s.size(), t.size());
            for (int k = 0; k < m; ++k) {
                if (s[k] != t[k]) {
                    return k;
                }
            }
            return m;
        };
        for (int i = 0; i + 1 < n; ++i) {
            ms.insert(calc(words[i], words[i + 1]));
        }
        vector<int> ans(n);
        auto add = [&](int i, int j) {
            if (i >= 0 && i < n && j >= 0 && j < n) {
                ms.insert(calc(words[i], words[j]));
            }
        };
        auto remove = [&](int i, int j) {
            if (i >= 0 && i < n && j >= 0 && j < n) {
                int x = calc(words[i], words[j]);
                auto it = ms.find(x);
                if (it != ms.end()) {
                    ms.erase(it);
                }
            }
        };
        for (int i = 0; i < n; ++i) {
            remove(i, i + 1);
            remove(i - 1, i);
            add(i - 1, i + 1);
            ans[i] = ms.empty() ? 0 : *ms.rbegin();
            remove(i - 1, i + 1);
            add(i - 1, i);
            add(i, i + 1);
        }
        return ans;
    }
};
