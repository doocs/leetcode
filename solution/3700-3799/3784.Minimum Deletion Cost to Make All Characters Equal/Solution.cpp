class Solution {
public:
    long long minCost(string s, vector<int>& cost) {
        long long tot = 0;
        unordered_map<char, long long> g;
        for (int i = 0; i < cost.size(); ++i) {
            tot += cost[i];
            g[s[i]] += cost[i];
        }
        long long ans = tot;
        for (auto [_, v] : g) {
            ans = min(ans, tot - v);
        }
        return ans;
    }
};
