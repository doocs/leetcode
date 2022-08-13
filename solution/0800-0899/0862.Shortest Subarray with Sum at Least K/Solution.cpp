class Solution {
public:
    int shortestSubarray(vector<int>& nums, int k) {
        int n = nums.size();
        vector<long long> s(n + 1);
        for (int i = 0; i < n; ++i) s[i + 1] = s[i] + nums[i];
        deque<int> q {{0}};
        int ans = INT_MAX;
        for (int i = 1; i <= n; ++i) {
            while (!q.empty() && s[i] - s[q.front()] >= k) {
                ans = min(ans, i - q.front());
                q.pop_front();
            }
            while (!q.empty() && s[i] <= s[q.back()]) q.pop_back();
            q.push_back(i);
        }
        return ans == INT_MAX ? -1 : ans;
    }
};