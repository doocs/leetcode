class Solution {
public:
    vector<string> findAllRecipes(vector<string>& recipes, vector<vector<string>>& ingredients, vector<string>& supplies) {
        unordered_map<string, vector<string>> g;
        unordered_map<string, int> indeg;
        for (int i = 0; i < recipes.size(); ++i) {
            for (auto& v : ingredients[i]) {
                g[v].push_back(recipes[i]);
            }
            indeg[recipes[i]] = ingredients[i].size();
        }
        queue<string> q;
        for (auto& s : supplies) {
            q.push(s);
        }
        vector<string> ans;
        while (!q.empty()) {
            for (int n = q.size(); n; --n) {
                auto i = q.front();
                q.pop();
                for (auto j : g[i]) {
                    if (--indeg[j] == 0) {
                        ans.push_back(j);
                        q.push(j);
                    }
                }
            }
        }
        return ans;
    }
};