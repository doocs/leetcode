class Solution {
public:
    int maxWalls(vector<int>& robots, vector<int>& distance, vector<int>& walls) {
        int n = robots.size();
        vector<pair<int, int>> arr(n);
        for (int i = 0; i < n; i++) {
            arr[i] = {robots[i], distance[i]};
        }
        ranges::sort(arr, {}, &pair<int, int>::first);
        ranges::sort(walls);

        vector<vector<int>> f(n, vector<int>(2));
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < 2; ++j) {
                int left = arr[i].first - arr[i].second;
                if (i > 0) {
                    left = max(left, arr[i - 1].first + 1);
                }
                int l = ranges::lower_bound(walls, left) - walls.begin();
                int r = ranges::lower_bound(walls, arr[i].first + 1) - walls.begin();
                int ans = (i ? f[i - 1][0] : 0) + (r - l);

                int right = arr[i].first + arr[i].second;
                if (i + 1 < n) {
                    if (j == 0) {
                        right = min(right, arr[i + 1].first - arr[i + 1].second - 1);
                    } else {
                        right = min(right, arr[i + 1].first - 1);
                    }
                }
                l = ranges::lower_bound(walls, arr[i].first) - walls.begin();
                r = ranges::lower_bound(walls, right + 1) - walls.begin();
                ans = max(ans, (i ? f[i - 1][1] : 0) + (r - l));
                f[i][j] = ans;
            }
        }
        return f[n - 1][1];
    }
};
