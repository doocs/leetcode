class Solution {
public:
    vector<int> goodIndices(vector<int>& nums, int k) {
        int n = nums.size();
        vector<int> decr(n, 1);
        vector<int> incr(n, 1);
        for (int i = 2; i < n; ++i) {
            if (nums[i - 1] <= nums[i - 2]) {
                decr[i] = decr[i - 1] + 1;
            }
        }
        for (int i = n - 3; ~i; --i) {
            if (nums[i + 1] <= nums[i + 2]) {
                incr[i] = incr[i + 1] + 1;
            }
        }
        vector<int> ans;
        for (int i = k; i < n - k; ++i) {
            if (decr[i] >= k && incr[i] >= k) {
                ans.push_back(i);
            }
        }
        return ans;
    }
};