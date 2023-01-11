class Solution {
public:
    int visibleMountains(vector<vector<int>>& peaks) {
        vector<pair<int, int>> arr;
        for (auto& e : peaks) {
            int x = e[0], y = e[1];
            arr.emplace_back(x - y, -(x + y));
        }
        sort(arr.begin(), arr.end());
        int n = arr.size();
        int ans = 0, cur = INT_MIN;
        for (int i = 0; i < n; ++i) {
            int l = arr[i].first, r = -arr[i].second;
            if (r <= cur) {
                continue;
            }
            cur = r;
            ans += i == n - 1 || (i < n - 1 && arr[i] != arr[i + 1]);
        }
        return ans;
    }
};