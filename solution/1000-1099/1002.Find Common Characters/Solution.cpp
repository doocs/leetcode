class Solution {
public:
    vector<string> commonChars(vector<string>& words) {
        int cnt[26];
        memset(cnt, 0x3f, sizeof(cnt));
        for (auto& w : words) {
            int ccnt[26]{};
            for (char& c : w) {
                ++ccnt[c - 'a'];
            }
            for (int i = 0; i < 26; ++i) {
                cnt[i] = min(cnt[i], ccnt[i]);
            }
        }
        vector<string> ans;
        for (int i = 0; i < 26; ++i) {
            while (cnt[i]--) {
                ans.emplace_back(1, i + 'a');
            }
        }
        return ans;
    }
};