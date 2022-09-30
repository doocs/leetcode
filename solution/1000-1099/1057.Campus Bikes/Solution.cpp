class Solution {
public:
    vector<int> assignBikes(vector<vector<int>>& workers, vector<vector<int>>& bikes) {
        int n = workers.size(), m = bikes.size();
        vector<tuple<int, int, int>> arr(n * m);
        for (int i = 0, k = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                int dist = abs(workers[i][0] - bikes[j][0]) + abs(workers[i][1] - bikes[j][1]);
                arr[k++] = {dist, i, j};
            }
        }
        sort(arr.begin(), arr.end());
        vector<bool> vis1(n), vis2(m);
        vector<int> ans(n);
        for (auto& [_, i, j] : arr) {
            if (!vis1[i] && !vis2[j]) {
                vis1[i] = true;
                vis2[j] = true;
                ans[i] = j;
            }
        }
        return ans;
    }
};