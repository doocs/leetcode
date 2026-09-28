class Solution {
public:
    int maxSubarray(vector<int>& nums) {
        int mx = ranges::max(nums);

        vector<int> cntS((mx << 1) | 1);
        vector<int> cntD(mx + 1);

        int ans = 0;
        int l = 0;

        for (int r = 0; r < nums.size(); r++) {
            int x = nums[r];

            while (cntS[x] > 0 || cntD[x] > 0) {
                int y = nums[l++];

                for (int i = l; i < r; i++) {
                    int z = nums[i];
                    cntS[y + z]--;
                    cntD[abs(y - z)]--;
                }
            }

            for (int i = l; i < r; i++) {
                int y = nums[i];
                cntS[x + y]++;
                cntD[abs(x - y)]++;
            }

            ans = max(ans, r - l + 1);
        }

        return ans;
    }
};