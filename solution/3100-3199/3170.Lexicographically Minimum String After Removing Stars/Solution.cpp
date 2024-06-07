class Solution {
public:
    string clearStars(string s) {
        stack<int> g[26];
        int n = s.length();
        vector<bool> rem(n);
        for (int i = 0; i < n; ++i) {
            if (s[i] == '*') {
                rem[i] = true;
                for (int j = 0; j < 26; ++j) {
                    if (!g[j].empty()) {
                        rem[g[j].top()] = true;
                        g[j].pop();
                        break;
                    }
                }
            } else {
                g[s[i] - 'a'].push(i);
            }
        }
        string ans;
        for (int i = 0; i < n; ++i) {
            if (!rem[i]) {
                ans.push_back(s[i]);
            }
        }
        return ans;
    }
};