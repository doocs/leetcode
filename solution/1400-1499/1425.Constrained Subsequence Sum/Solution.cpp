class Solution {
public:
    int constrainedSubsetSum(vector<int>& nums, int k) {
        deque<int> q = {0};
        int n = nums.size();
        int f[n];
        f[0] = 0;
        int ans = INT_MIN;
        for (int i = 0; i < n; ++i) {
            while (i - q.front() > k) {
                q.pop_front();
            }
            f[i] = max(0, f[q.front()]) + nums[i];
            ans = max(ans, f[i]);
            while (!q.empty() && f[q.back()] <= f[i]) {
                q.pop_back();
            }
            q.push_back(i);
        }
        return ans;
    }
};
