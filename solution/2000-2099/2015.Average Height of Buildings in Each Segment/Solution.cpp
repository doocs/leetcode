class Solution {
public:
    vector<vector<int>> averageHeightOfBuildings(vector<vector<int>>& buildings) {
        map<int, int> height, cnt;
        for (auto& v : buildings) {
            int s = v[0], e = v[1], h = v[2];
            cnt[s]++, cnt[e]--;
            height[s] += h, height[e] -= h;
        }
        vector<vector<int>> ans;
        int i = 0, h = 0, n = 0;
        for (auto& [j, _] : cnt) {
            if (n) {
                vector<int> t = {i, j, h / n};
                if (ans.size() && ans.back()[1] == i && ans.back()[2] == t[2]) {
                    ans.back()[1] = j;
                } else {
                    ans.push_back(t);
                }
            }
            i = j;
            h += height[j];
            n += cnt[j];
        }
        return ans;
    }
};