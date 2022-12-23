class Solution {
public:
    vector<string> wordSubsets(vector<string>& words1, vector<string>& words2) {
        int cnt[26] = {0};
        int t[26];
        for (auto& b : words2) {
            memset(t, 0, sizeof t);
            for (auto& c : b) {
                t[c - 'a']++;
            }
            for (int i = 0; i < 26; ++i) {
                cnt[i] = max(cnt[i], t[i]);
            }
        }
        vector<string> ans;
        for (auto& a : words1) {
            memset(t, 0, sizeof t);
            for (auto& c : a) {
                t[c - 'a']++;
            }
            bool ok = true;
            for (int i = 0; i < 26; ++i) {
                if (cnt[i] > t[i]) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                ans.emplace_back(a);
            }
        }
        return ans;
    }
};