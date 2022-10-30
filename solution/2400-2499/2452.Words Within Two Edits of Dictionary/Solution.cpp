class Solution {
public:
    vector<string> twoEditWords(vector<string>& queries, vector<string>& dictionary) {
        vector<string> ans;
        for (auto& s : queries) {
            for (auto& t : dictionary) {
                int cnt = 0;
                for (int i = 0; i < s.size(); ++i) cnt += s[i] != t[i];
                if (cnt < 3) {
                    ans.emplace_back(s);
                    break;
                }
            }
        }
        return ans;
    }
};