class Solution {
public:
    vector<string> findRepeatedDnaSequences(string s) {
        map<string, int> cnt;
        int n = s.size() - 10;
        vector<string> ans;
        for (int i = 0; i <= n; ++i) {
            string sub = s.substr(i, 10);
            if (++cnt[sub] == 2) {
                ans.push_back(sub);
            }
        }
        return ans;
    }
};
