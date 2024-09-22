class Solution {
public:
    vector<string> removeAnagrams(vector<string>& words) {
        auto check = [](string& s, string& t) -> bool {
            if (s.size() != t.size()) {
                return true;
            }
            int cnt[26]{};
            for (char& c : s) {
                ++cnt[c - 'a'];
            }
            for (char& c : t) {
                if (--cnt[c - 'a'] < 0) {
                    return true;
                }
            }
            return false;
        };

        vector<string> ans = {words[0]};
        for (int i = 1; i < words.size(); ++i) {
            if (check(words[i - 1], words[i])) {
                ans.emplace_back(words[i]);
            }
        }
        return ans;
    }
};
