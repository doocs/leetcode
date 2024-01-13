class Solution {
public:
    int minAbsDifference(vector<int>& nums, int goal) {
        int n = nums.size();
        vector<int> left;
        vector<int> right;
        dfs(nums, left, 0, n >> 1, 0);
        dfs(nums, right, n >> 1, n, 0);
        sort(right.begin(), right.end());
        int ans = INT_MAX;
        for (int x : left) {
            int y = goal - x;
            int idx = lower_bound(right.begin(), right.end(), y) - right.begin();
            if (idx < right.size()) ans = min(ans, abs(y - right[idx]));
            if (idx) ans = min(ans, abs(y - right[idx - 1]));
        }
        return ans;
    }

private:
    void dfs(vector<int>& arr, vector<int>& res, int i, int n, int s) {
        if (i == n) {
            res.emplace_back(s);
            return;
        }
        dfs(arr, res, i + 1, n, s);
        dfs(arr, res, i + 1, n, s + arr[i]);
    }
};