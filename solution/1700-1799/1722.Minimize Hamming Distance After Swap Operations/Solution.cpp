class Solution {
public:
    int minimumHammingDistance(vector<int>& source, vector<int>& target, vector<vector<int>>& allowedSwaps) {
        int n = source.size();
        vector<int> p(n);
        iota(p.begin(), p.end(), 0);
        function<int(int)> find = [&](int x) {
            return x == p[x] ? x : p[x] = find(p[x]);
        };
        for (auto& a : allowedSwaps) {
            p[find(a[0])] = find(a[1]);
        }
        unordered_map<int, unordered_map<int, int>> cnt;
        for (int i = 0; i < n; ++i) {
            ++cnt[find(i)][source[i]];
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (--cnt[find(i)][target[i]] < 0) {
                ++ans;
            }
        }
        return ans;
    }
};