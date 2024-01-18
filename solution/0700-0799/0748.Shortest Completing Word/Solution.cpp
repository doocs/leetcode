class Solution {
public:
    string shortestCompletingWord(string licensePlate, vector<string>& words) {
        int cnt[26]{};
        for (char& c : licensePlate) {
            if (isalpha(c)) {
                ++cnt[tolower(c) - 'a'];
            }
        }
        string ans;
        for (auto& w : words) {
            if (ans.size() && ans.size() <= w.size()) {
                continue;
            }
            int t[26]{};
            for (char& c : w) {
                ++t[c - 'a'];
            }
            bool ok = true;
            for (int i = 0; i < 26; ++i) {
                if (cnt[i] > t[i]) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                ans = w;
            }
        }
        return ans;
    }
};