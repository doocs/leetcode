class Solution {
public:
    int maxSubarrays(vector<int>& nums) {
        int score = -1, ans = 1;
        for (int num : nums) {
            score &= num;
            if (score == 0) {
                --score;
                ++ans;
            }
        }
        return ans == 1 ? 1 : ans - 1;
    }
};