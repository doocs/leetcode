class Solution {
public:
    int countDominantElements(vector<int>& nums) {
        int ans = 0;
        for (int i = 0; i < nums.size(); i++) {
            int s = 0, cnt = 0;
            if (i > 0) {
                s += nums[i - 1];
                cnt++;
            }
            if (i < nums.size() - 1) {
                s += nums[i + 1];
                cnt++;
            }
            if (cnt == 0 || nums[i] * cnt >= s) {
                ans++;
            }
        }
        return ans;
    }
};