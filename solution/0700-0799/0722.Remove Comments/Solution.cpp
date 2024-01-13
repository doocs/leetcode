class Solution {
public:
    vector<string> removeComments(vector<string>& source) {
        vector<string> ans;
        string t;
        bool blockComment = false;
        for (auto& s : source) {
            int m = s.size();
            for (int i = 0; i < m; ++i) {
                if (blockComment) {
                    if (i + 1 < m && s[i] == '*' && s[i + 1] == '/') {
                        blockComment = false;
                        ++i;
                    }
                } else {
                    if (i + 1 < m && s[i] == '/' && s[i + 1] == '*') {
                        blockComment = true;
                        ++i;
                    } else if (i + 1 < m && s[i] == '/' && s[i + 1] == '/') {
                        break;
                    } else {
                        t.push_back(s[i]);
                    }
                }
            }
            if (!blockComment && !t.empty()) {
                ans.emplace_back(t);
                t.clear();
            }
        }
        return ans;
    }
};