class Solution {
public:
    int minSubarray(vector<int>& nums, int p) {
        int k = 0;
        for (int& x : nums) {
            k = (k + x) % p;
        }
        if (k == 0) {
            return 0;
        }
        unordered_map<int, int> last;
        last[0] = -1;
        int n = nums.size();
        int ans = n;
        int cur = 0;
        for (int i = 0; i < n; ++i) {
            cur = (cur + nums[i]) % p;
            int target = (cur - k + p) % p;
            if (last.count(target)) {
                ans = min(ans, i - last[target]);
            }
            last[cur] = i;
        }
        return ans == n ? -1 : ans;
    }
};