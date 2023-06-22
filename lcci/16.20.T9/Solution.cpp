class Solution {
public:
    vector<string> getValidT9Words(string num, vector<string>& words) {
        string s = "22233344455566677778889999";
        int d[26];
        for (int i = 0; i < 26; ++i) {
            d[i] = s[i];
        }
        vector<string> ans;
        int n = num.size();
        for (auto& w : words) {
            bool ok = true;
            for (int i = 0; i < n; ++i) {
                if (d[w[i] - 'a'] != num[i]) {
                    ok = false;
                }
            }
            if (ok) {
                ans.emplace_back(w);
            }
        }
        return ans;
    }
};