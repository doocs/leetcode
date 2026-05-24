class Solution {
public:
    int minOperations(vector<int>& nums, int k) {
        int n = nums.size();

        for (int& v : nums) {
            v %= k;
        }

        int ans = INT_MAX;

        for (int x = 0; x < k; ++x) {
            for (int y = 0; y < k; ++y) {
                if (x != y) {
                    int cnt = 0;

                    for (int i = 0; i < n; ++i) {
                        int target = (i & 1) ? y : x;
                        int diff = abs(target - nums[i]);
                        cnt += min(diff, k - diff);
                    }

                    ans = min(ans, cnt);
                }
            }
        }

        return ans;
    }
};