class Solution {
public:
    vector<string> stringSequence(string target) {
        vector<string> ans;
        for (char c : target) {
            string s = ans.empty() ? "" : ans.back();
            for (char a = 'a'; a <= c; ++a) {
                string t = s + a;
                ans.push_back(t);
            }
        }
        return ans;
    }
};
