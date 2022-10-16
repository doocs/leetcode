class Solution {
public:
    int componentValue(vector<int>& nums, vector<vector<int>>& edges) {
        int n = nums.size();
        int s = accumulate(nums.begin(), nums.end(), 0);
        int mx = *max_element(nums.begin(), nums.end());
        int t = 0;
        unordered_map<int, vector<int>> g;
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        function<int(int, int)> dfs = [&](int i, int fa) -> int {
            int x = nums[i];
            for (int j : g[i]) {
                if (j != fa) {
                    int y = dfs(j, i);
                    if (y == -1) return -1;
                    x += y;
                }
            }
            if (x > t) return -1;
            return x < t ? x : 0;
        };
        for (int k = min(n, s / mx); k > 1; --k) {
            if (s % k == 0) {
                t = s / k;
                if (dfs(0, -1) == 0) {
                    return k - 1;
                }
            }
        }
        return 0;
    }
};