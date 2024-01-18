class Solution {
public:
    vector<int> secondGreaterElement(vector<int>& nums) {
        int n = nums.size();
        vector<int> ans(n, -1);
        vector<pair<int, int>> arr(n);
        for (int i = 0; i < n; ++i) {
            arr[i] = {-nums[i], i};
        }
        sort(arr.begin(), arr.end());
        set<int> ts;
        for (auto& [_, i] : arr) {
            auto it = ts.upper_bound(i);
            if (it != ts.end() && ts.upper_bound(*it) != ts.end()) {
                ans[i] = nums[*ts.upper_bound(*it)];
            }
            ts.insert(i);
        }
        return ans;
    }
};