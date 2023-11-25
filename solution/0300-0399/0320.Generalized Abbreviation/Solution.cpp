class Solution {
public:
    vector<string> generateAbbreviations(string word) {
        int n = word.size();
        vector<string> ans;
        for (int i = 0; i < 1 << n; ++i) {
            string s;
            int cnt = 0;
            for (int j = 0; j < n; ++j) {
                if (i >> j & 1) {
                    ++cnt;
                } else {
                    if (cnt) {
                        s += to_string(cnt);
                        cnt = 0;
                    }
                    s.push_back(word[j]);
                }
            }
            if (cnt) {
                s += to_string(cnt);
            }
            ans.push_back(s);
        }
        return ans;
    }
};