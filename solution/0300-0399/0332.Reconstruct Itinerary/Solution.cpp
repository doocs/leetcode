class Solution {
public:
    vector<string> findItinerary(vector<vector<string>>& tickets) {
        sort(tickets.rbegin(), tickets.rend());
        unordered_map<string, vector<string>> g;
        for (const auto& ticket : tickets) {
            g[ticket[0]].push_back(ticket[1]);
        }
        vector<string> ans;
        auto dfs = [&](this auto&& dfs, string& f) -> void {
            while (!g[f].empty()) {
                string t = g[f].back();
                g[f].pop_back();
                dfs(t);
            }
            ans.emplace_back(f);
        };
        string f = "JFK";
        dfs(f);
        reverse(ans.begin(), ans.end());
        return ans;
    }
};
