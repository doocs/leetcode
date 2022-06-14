class Solution {
    vector<string> split(const string& s, char delim) {
        vector<string> result;
        stringstream ss(s);
        string item;
        while (getline(ss, item, delim)) {
            result.push_back(item);
        }
        return result;
    }

public:
    vector<vector<string>> findDuplicate(vector<string>& paths) {
        unordered_map<string, vector<string>> m;
        for (auto& path : paths) {
            auto a = split(path, ' ');
            for (int i = 1; i < a.size(); ++i) {
                int j = a[i].find('(');
                auto content = a[i].substr(j + 1, a[i].size() - j - 2);
                auto name = a[0] + '/' + a[i].substr(0, j);
                if (m.find(content) == m.end()) {
                    m[content] = vector<string>();
                }
                m[content].emplace_back(name);
            }
        }

        vector<vector<string>> ans;
        for (auto& [_, names] : m) {
            if (names.size() > 1) ans.emplace_back(names);
        }
        return ans;
    }
};
