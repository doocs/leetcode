class Solution {
public:
    int shortestSubarray(vector<int>& nums, int k) {
        int n = nums.size();
        vector<long> s(n + 1);
        for (int i = 0; i < n; ++i) s[i + 1] = s[i] + nums[i];
        deque<int> q;
        int ans = n + 1;
        for (int i = 0; i <= n; ++i) {
            while (!q.empty() && s[i] - s[q.front()] >= k) {
                ans = min(ans, i - q.front());
                q.pop_front();
            }
            while (!q.empty() && s[q.back()] >= s[i]) q.pop_back();
            q.push_back(i);
        }
        return ans > n ? -1 : ans;
    }
};