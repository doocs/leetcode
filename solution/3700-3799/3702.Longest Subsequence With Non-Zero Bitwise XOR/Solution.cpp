class Solution {
public:
    int longestSubsequence(vector<int>& nums) {
        int xor_ = 0, cnt0 = 0;
        int n = nums.size();
        for (int x : nums) {
            xor_ ^= x;
            cnt0 += x == 0 ? 1 : 0;
        }
        if (xor_ != 0) {
            return n;
        }
        return cnt0 == n ? 0 : n - 1;
    }
};
