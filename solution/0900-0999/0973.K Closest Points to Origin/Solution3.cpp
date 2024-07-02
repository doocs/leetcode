class Solution {
public:
    vector<vector<int>> kClosest(vector<vector<int>>& points, int k) {
        int n = points.size();
        int dist[n];
        int r = 0;
        for (int i = 0; i < n; ++i) {
            int x = points[i][0], y = points[i][1];
            dist[i] = x * x + y * y;
            r = max(r, dist[i]);
        }
        int l = 0;
        while (l < r) {
            int mid = (l + r) >> 1;
            int cnt = 0;
            for (int d : dist) {
                cnt += d <= mid;
            }
            if (cnt >= k) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        vector<vector<int>> ans;
        for (int i = 0; i < n; ++i) {
            if (dist[i] <= l) {
                ans.emplace_back(points[i]);
            }
        }
        return ans;
    }
};
