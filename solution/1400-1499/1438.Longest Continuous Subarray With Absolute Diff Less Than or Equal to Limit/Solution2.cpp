class Solution {
public:
    int longestSubarray(vector<int>& nums, int limit) {
        auto check = [&](int k) {
            deque<int> min_q;
            deque<int> max_q;
            for (int i = 0; i < nums.size(); ++i) {
                if (!min_q.empty() && i - min_q.front() + 1 > k) {
                    min_q.pop_front();
                }
                if (!max_q.empty() && i - max_q.front() + 1 > k) {
                    max_q.pop_front();
                }
                while (!min_q.empty() && nums[min_q.back()] >= nums[i]) {
                    min_q.pop_back();
                }
                while (!max_q.empty() && nums[max_q.back()] <= nums[i]) {
                    max_q.pop_back();
                }
                min_q.push_back(i);
                max_q.push_back(i);
                if (i >= k - 1 && nums[max_q.front()] - nums[min_q.front()] <= limit) {
                    return true;
                }
            }
            return false;
        };
        int l = 1, r = nums.size();
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
};