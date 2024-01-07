class Solution {
public:
    int missingInteger(vector<int>& nums) {
        int s = nums[0], j = 1;
        while (j < nums.size() && nums[j] == nums[j - 1] + 1) {
            s += nums[j++];
        }
        bool vis[51]{};
        for (int x : nums) {
            vis[x] = true;
        }
        for (int x = s;; ++x) {
            if (x >= 51 || !vis[x]) {
                return x;
            }
        }
    }
};