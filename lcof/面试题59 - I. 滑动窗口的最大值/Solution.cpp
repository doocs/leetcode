class Solution {
public:
    vector<int> maxSlidingWindow(vector<int>& nums, int k) {
        vector<int> ans;
        deque<int> window;
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            while (!window.empty() && nums[window.back()] <= nums[i]) {
                window.pop_back();
            }
            window.push_back(i);
            if (window.front() == i - k) {
                window.pop_front();
            }
            if (i >= k - 1) {
                ans.push_back(nums[window.front()]);
            }
        }
        return ans;
    }
};
