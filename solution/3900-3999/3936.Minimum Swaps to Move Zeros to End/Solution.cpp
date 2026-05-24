class Solution {
public:
    int minimumSwaps(vector<int>& nums) {
        int ans = 0;
        int n = nums.size();
        for (int i = 0, j = n - 1; i < j; ++i, --j) {
            while (i < n && nums[i] != 0) {
                ++i;
            }

            while (j > 0 && nums[j] == 0) {
                --j;
            }

            if (i >= j) {
                break;
            }

            ++ans;
        }

        return ans;
    }
};