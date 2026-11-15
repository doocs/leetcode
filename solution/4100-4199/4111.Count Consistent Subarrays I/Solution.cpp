class Solution {
public:
    int countConsistent(vector<int>& nums) {
        int n = nums.size();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            int y = x;
            for (int j = i; j < n; j++) {
                x &= nums[j];
                y = min(y, nums[j]);
                if (x == y) {
                    ans++;
                }
            }
        }
        return ans;
    }
};