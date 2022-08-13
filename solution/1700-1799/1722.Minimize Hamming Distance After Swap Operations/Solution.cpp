class Solution {
public:
    vector<int> p;

    int minimumHammingDistance(vector<int>& source, vector<int>& target, vector<vector<int>>& allowedSwaps) {
        int n = source.size();
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        for (auto e : allowedSwaps) p[find(e[0])] = find(e[1]);
        unordered_map<int, unordered_map<int, int>> mp;
        for (int i = 0; i < n; ++i) ++mp[find(i)][source[i]];
        int res = 0;
        for (int i = 0; i < n; ++i) {
            if (mp[find(i)][target[i]] > 0)
                --mp[find(i)][target[i]];
            else
                ++res;
        }
        return res;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};