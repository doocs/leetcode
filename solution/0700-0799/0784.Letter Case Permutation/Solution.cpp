class Solution {
public:
    vector<string> ans;
    string s;

    vector<string> letterCasePermutation(string s) {
        this->s = s;
        string t = "";
        dfs(0, t);
        return ans;
    }

    void dfs(int i, string t) {
        if (i == s.size()) {
            ans.push_back(t);
            return;
        }
        if (isalpha(s[i])) {
            char c1 = toupper(s[i]);
            char c2 = tolower(s[i]);
            dfs(i + 1, t + c1);
            dfs(i + 1, t + c2);
        } else {
            dfs(i + 1, t + s[i]);
        }
    }
};