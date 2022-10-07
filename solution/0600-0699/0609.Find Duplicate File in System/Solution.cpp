class Solution {
public:
    vector<vector<string>> findDuplicate(vector<string>& paths) {
        unordered_map<string, vector<string>> d;
        for (auto& p : paths) {
            auto ps = split(p, ' ');
            for (int i = 1; i < ps.size(); ++i) {
                int j = ps[i].find('(');
                auto content = ps[i].substr(j + 1, ps[i].size() - j - 2);
                auto name = ps[0] + '/' + ps[i].substr(0, j);
                d[content].push_back(name);
            }
        }
        vector<vector<string>> ans;
        for (auto& [_, e] : d) {
            if (e.size() > 1) {
                ans.push_back(e);
            }
        }
        return ans;
    }

    vector<string> split(string& s, char c) {
        vector<string> res;
        stringstream ss(s);
        string t;
        while (getline(ss, t, c)) {
            res.push_back(t);
        }
        return res;
    }
};