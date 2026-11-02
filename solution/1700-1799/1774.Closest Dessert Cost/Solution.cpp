class Solution {
public:
    const int inf = INT_MAX;
    int closestCost(vector<int>& baseCosts, vector<int>& toppingCosts, int target) {
        vector<int> arr;
        function<void(int, int)> dfs = [&](int i, int t) {
            if (i >= toppingCosts.size()) {
                arr.push_back(t);
                return;
            }
            dfs(i + 1, t);
            dfs(i + 1, t + toppingCosts[i]);
        };
        dfs(0, 0);
        sort(arr.begin(), arr.end());
        int d = inf, ans = inf;
        for (int x : baseCosts) {
            for (int y : arr) {
                int i = lower_bound(arr.begin(), arr.end(), target - x - y) - arr.begin();
                for (int j = i - 1; j < i + 1; ++j) {
                    if (j >= 0 && j < arr.size()) {
                        int t = abs(x + y + arr[j] - target);
                        if (d > t || (d == t && ans > x + y + arr[j])) {
                            d = t;
                            ans = x + y + arr[j];
                        }
                    }
                }
            }
        }
        return ans;
    }
};