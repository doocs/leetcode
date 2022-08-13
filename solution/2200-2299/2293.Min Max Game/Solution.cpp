class Solution {
public:
    int minMaxGame(vector<int>& nums) {
        int n = nums.size();
        if (n == 1) return nums[0];
        vector<int> t(n >> 1);
        for (int i = 0; i < t.size(); ++i) {
            int a = nums[i << 1], b = nums[i << 1 | 1];
            t[i] = (i & 1) == 1 ? max(a, b) : min(a, b);
        }
        return minMaxGame(t);
    }
};