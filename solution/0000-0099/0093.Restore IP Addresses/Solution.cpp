class Solution {
public:
    vector<string> restoreIpAddresses(string s) {
        vector<string> ans;
        vector<string> t;
        dfs(s, t, ans);
        return ans;
    }

    void dfs(string s, vector<string>& t, vector<string>& ans) {
        if (t.size() == 4) {
            if (s == "") {
                string p = "";
                for (auto e : t) p += e + ".";
                p.pop_back();
                ans.push_back(p);
            }
            return;
        }
        for (int i = 1; i < min(4, (int) s.size() + 1); ++i) {
            string c = s.substr(0, i);
            if (check(c)) {
                t.push_back(c);
                dfs(s.substr(i, s.size() - i), t, ans);
                t.pop_back();
            }
        }
    }

    bool check(string s) {
        if (s == "") return false;
        int num = stoi(s);
        if (num > 255) return false;
        if (s[0] == '0' && s.size() > 1) return false;
        return true;
    }
};