class Solution {
public:
    long long maxTotal(vector<int>& value, vector<int>& limit) {
        unordered_map<int, vector<int>> g;
        int n = value.size();
        for (int i = 0; i < n; ++i) {
            g[limit[i]].push_back(value[i]);
        }
        long long ans = 0;
        for (auto& [lim, vs] : g) {
            sort(vs.begin(), vs.end(), greater<int>());
            for (int i = 0; i < min(lim, (int) vs.size()); ++i) {
                ans += vs[i];
            }
        }
        return ans;
    }
};
