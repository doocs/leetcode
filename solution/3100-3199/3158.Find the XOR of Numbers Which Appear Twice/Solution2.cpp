class Solution {
public:
    int duplicateNumbersXOR(vector<int>& nums) {
        int ans = 0;
        long long mask = 0;
        for (int x : nums) {
            if (mask >> x & 1) {
                ans ^= x;
            } else {
                mask |= 1LL << x;
            }
        }
        return ans;
    }
};
