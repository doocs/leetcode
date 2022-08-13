class Solution {
public:
    vector<vector<long long>> splitPainting(vector<vector<int>>& segments) {
        map<int, long long> d;
        for (auto& e : segments) {
            int l = e[0], r = e[1], c = e[2];
            d[l] += c;
            d[r] -= c;
        }
        vector<vector<long long>> ans;
        long long i, j, cur = 0;
        for (auto& it : d) {
            if (it == *d.begin())
                i = it.first;
            else {
                j = it.first;
                if (cur > 0) ans.push_back({i, j, cur});
                i = j;
            }
            cur += it.second;
        }
        return ans;
    }
};