class Solution {
public:
    long long maxSum(vector<vector<int>>& grid, vector<int>& limits, int k) {
        priority_queue<int, vector<int>, greater<int>> pq;
        int n = grid.size();

        for (int i = 0; i < n; ++i) {
            vector<int> nums = grid[i];
            int limit = limits[i];
            ranges::sort(nums);

            for (int j = 0; j < limit; ++j) {
                pq.push(nums[nums.size() - j - 1]);
                if (pq.size() > k) {
                    pq.pop();
                }
            }
        }

        long long ans = 0;
        while (!pq.empty()) {
            ans += pq.top();
            pq.pop();
        }

        return ans;
    }
};
