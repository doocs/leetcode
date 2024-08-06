class Solution {
public:
    vector<vector<int>> averageHeightOfBuildings(vector<vector<int>>& buildings) {
        unordered_map<int, int> cnt;
        map<int, int> d;

        for (const auto& e : buildings) {
            int start = e[0], end = e[1], height = e[2];
            cnt[start]++;
            cnt[end]--;
            d[start] += height;
            d[end] -= height;
        }

        int s = 0, m = 0;
        int last = -1;
        vector<vector<int>> ans;

        for (const auto& [k, v] : d) {
            if (m > 0) {
                int avg = s / m;
                if (!ans.empty() && ans.back()[2] == avg && ans.back()[1] == last) {
                    ans.back()[1] = k;
                } else {
                    ans.push_back({last, k, avg});
                }
            }
            s += v;
            m += cnt[k];
            last = k;
        }

        return ans;
    }
};
