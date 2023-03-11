class Solution {
public:
    vector<string> trulyMostPopular(vector<string>& names, vector<string>& synonyms) {
        unordered_map<string, vector<string>> g;
        unordered_map<string, int> cnt;
        for (auto& e : synonyms) {
            int i = e.find(',');
            string a = e.substr(1, i - 1);
            string b = e.substr(i + 1, e.size() - i - 2);
            g[a].emplace_back(b);
            g[b].emplace_back(a);
        }
        unordered_set<string> s;
        for (auto& e : names) {
            int i = e.find('(');
            string name = e.substr(0, i);
            s.insert(name);
            cnt[name] += stoi(e.substr(i + 1, e.size() - i - 2));
        }
        unordered_set<string> vis;
        int freq = 0;

        function<string(string)> dfs = [&](string a) -> string {
            string res = a;
            vis.insert(a);
            freq += cnt[a];
            for (auto& b : g[a]) {
                if (!vis.count(b)) {
                    string t = dfs(b);
                    if (t < res) {
                        res = move(t);
                    }
                }
            }
            return move(res);
        };

        vector<string> ans;
        for (auto& name : s) {
            if (!vis.count(name)) {
                freq = 0;
                string x = dfs(name);
                ans.emplace_back(x + "(" + to_string(freq) + ")");
            }
        }
        return ans;
    }
};