class Solution {
public:
    vector<vector<string>> mostPopularCreator(vector<string>& creators, vector<string>& ids, vector<int>& views) {
        unordered_map<string, long> cnt;
        unordered_map<string, int> d;
        unordered_map<string, string> x;
        int n = ids.size();
        for (int k = 0; k < n; ++k) {
            auto c = creators[k];
            auto i = ids[k];
            int v = views[k];
            cnt[c] += v;
            if (!d.count(c) || d[c] < v || (d[c] == v && x[c] > i)) {
                d[c] = v;
                x[c] = i;
            }
        }
        long pre = -1;
        vector<vector<string>> ans;
        for (auto& [a, b] : cnt) {
            if (b > pre) {
                ans.clear();
                ans.push_back({a, x[a]});
                pre = b;
            } else if (b == pre) {
                ans.push_back({a, x[a]});
            }
        }
        return ans;
    }
};