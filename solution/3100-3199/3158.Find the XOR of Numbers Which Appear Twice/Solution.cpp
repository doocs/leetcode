class Solution {
public:
    int duplicateNumbersXOR(vector<int>& nums) {
        int cnt[51]{};
        int ans = 0;
        for (int x : nums) {
            if (++cnt[x] == 2) {
                ans ^= x;
            }
        }
        return ans;
    }
};