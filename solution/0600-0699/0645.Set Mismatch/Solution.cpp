class Solution {
public:
    vector<int> findErrorNums(vector<int>& nums) {
        int n = nums.size();
        int eor = 0;
        for (int i = 1; i <= n; ++i) {
            eor ^= i ^ nums[i - 1];
        }
        int lb = eor & -eor;
        int a = 0;
        for (int i = 1; i <= n; ++i) {
            if (i & lb) {
                a ^= i;
            }
            if (nums[i - 1] & lb) {
                a ^= nums[i - 1];
            }
        }
        int b = eor ^ a;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == a) {
                return {a, b};
            }
        }
        return {b, a};
    }
};