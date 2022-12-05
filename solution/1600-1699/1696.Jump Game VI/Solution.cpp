class Solution {
public:
    int maxResult(vector<int>& nums, int k) {
        int n = nums.size();
        int f[n];
        f[0] = 0;
        deque<int> q = {0};
        for (int i = 0; i < n; ++i) {
            if (i - q.front() > k) q.pop_front();
            f[i] = nums[i] + f[q.front()];
            while (!q.empty() && f[q.back()] <= f[i]) q.pop_back();
            q.push_back(i);
        }
        return f[n - 1];
    }
};