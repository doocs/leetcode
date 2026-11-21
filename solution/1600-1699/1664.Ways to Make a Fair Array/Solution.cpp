class Solution {
public:
    int waysToMakeFair(vector<int>& nums) {
        int n = nums.size();
        int s = 0, r0 = 0;
        for (int i = 0; i < n; ++i) {
            s += nums[i];
            if (i % 2 == 0) {
                r0 += nums[i];
            }
        }
        int r[2] = {r0, s - r0};
        int l[2] = {0, 0};
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int x = nums[i];
            r[i % 2] -= x;
            if (l[0] + r[1] == l[1] + r[0]) {
                ++ans;
            }
            l[i % 2] += x;
        }
        return ans;
    }
};
