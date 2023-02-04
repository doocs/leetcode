class Solution {
public:
    int singleNumber(vector<int>& nums) {
        int cnt[32]{};
        for (int& x : nums) {
            for (int i = 0; i < 32; ++i) {
                cnt[i] += x & 1;
                x >>= 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; ++i) {
            if (cnt[i] % 3) {
                ans |= 1 << i;
            }
        }
        return ans;
    }
};