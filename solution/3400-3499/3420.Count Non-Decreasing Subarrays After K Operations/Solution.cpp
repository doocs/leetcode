class Solution {
public:
    long long countNonDecreasingSubarrays(vector<int>& nums, int k) {
        int n = nums.size();
        deque<int> q;
        long long ans = 0, cost = 0;
        int r = n - 1;
        for (int l = n - 1; l >= 0; --l) {
            int x = nums[l];
            while (!q.empty() && nums[q.back()] <= x) {
                int s = q.back();
                q.pop_back();
                int end = q.empty() ? r + 1 : q.back();
                cost += 1LL * (end - s) * (x - nums[s]);
            }
            q.push_back(l);
            while (cost > k) {
                cost -= nums[q.front()] - nums[r];
                if (q.front() == r) {
                    q.pop_front();
                }
                --r;
            }
            ans += r - l + 1;
        }
        return ans;
    }
};
