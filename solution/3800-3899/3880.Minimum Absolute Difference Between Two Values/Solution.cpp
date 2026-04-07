class Solution {
public:
    int minAbsoluteDifference(vector<int>& nums) {
        int n = nums.size();
        int ans = n + 1;
        vector<int> last(3, -(n + 1));

        for (int i = 0; i < n; ++i) {
            int x = nums[i];
            if (x != 0) {
                ans = min(ans, i - last[3 - x]);
                last[x] = i;
            }
        }
        return ans > n ? -1 : ans;
    }
};
