class Solution {
public:
    int longestSubarray(vector<int>& nums, int limit) {
        deque<int> max_q;
        deque<int> min_q;
        int n = nums.size();
        int l = 0;
        
        for (int r = 0; r < n; ++r) {
            while (!max_q.empty() && nums[max_q.back()] < nums[r]) {
                max_q.pop_back();
            }
            while (!min_q.empty() && nums[min_q.back()] > nums[r]) {
                min_q.pop_back();
            }
            max_q.push_back(r);
            min_q.push_back(r);
            
            if (nums[max_q.front()] - nums[min_q.front()] > limit) {
                ++l;
                if (max_q.front() < l) {
                    max_q.pop_front();
                }
                if (min_q.front() < l) {
                    min_q.pop_front();
                }
            }
        }
        return n - l;
    }
};
