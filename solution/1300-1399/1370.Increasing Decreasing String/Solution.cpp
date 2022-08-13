class Solution {
public:
    string sortString(string s) {
        vector<int> counter(26);
        for (char c : s) ++counter[c - 'a'];
        string ans = "";
        while (ans.size() < s.size()) {
            for (int i = 0; i < 26; ++i) {
                if (counter[i]) {
                    ans += (i + 'a');
                    --counter[i];
                }
            }
            for (int i = 25; i >= 0; --i) {
                if (counter[i]) {
                    ans += (i + 'a');
                    --counter[i];
                }
            }
        }
        return ans;
    }
};