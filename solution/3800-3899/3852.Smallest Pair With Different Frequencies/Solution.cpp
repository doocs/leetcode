class Solution {
public:
    vector<int> minDistinctFreqPair(vector<int>& nums) {
        const int inf = INT_MAX;
        unordered_map<int, int> cnt;
        int x = inf;

        for (int v : nums) {
            cnt[v]++;
            x = min(x, v);
        }

        int minY = inf;
        for (auto& [y, _] : cnt) {
            if (y < minY && cnt[x] != cnt[y]) {
                minY = y;
            }
        }

        if (minY == inf) {
            return {-1, -1};
        }
        return {x, minY};
    }
};
