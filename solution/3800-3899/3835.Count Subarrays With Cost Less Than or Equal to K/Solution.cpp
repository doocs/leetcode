class Solution {
public:
    long long countSubarrays(vector<int>& nums, long long k) {
        long long ans = 0;
        deque<int> q1, q2;
        int l = 0;

        for (int r = 0; r < nums.size(); r++) {
            int x = nums[r];

            while (!q1.empty() && nums[q1.back()] <= x) {
                q1.pop_back();
            }
            while (!q2.empty() && nums[q2.back()] >= x) {
                q2.pop_back();
            }
            q1.push_back(r);
            q2.push_back(r);

            while (l < r && (long long) (nums[q1.front()] - nums[q2.front()]) * (r - l + 1) > k) {
                l++;
                if (q1.front() < l) {
                    q1.pop_front();
                }
                if (q2.front() < l) {
                    q2.pop_front();
                }
            }

            ans += r - l + 1;
        }
        return ans;
    }
};
