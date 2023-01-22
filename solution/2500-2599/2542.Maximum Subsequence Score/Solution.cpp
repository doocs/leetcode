class Solution {
public:
    long long maxScore(vector<int>& nums1, vector<int>& nums2, int k) {
        int n = nums1.size();
        vector<pair<int, int>> nums(n);
        for (int i = 0; i < n; ++i) {
            nums[i] = {-nums2[i], nums1[i]};
        }
        sort(nums.begin(), nums.end());
        priority_queue<int, vector<int>, greater<int>> q;
        long long ans = 0, s = 0;
        for (auto& [a, b] : nums) {
            s += b;
            q.push(b);
            if (q.size() == k) {
                ans = max(ans, s * -a);
                s -= q.top();
                q.pop();
            }
        }
        return ans;
    }
};