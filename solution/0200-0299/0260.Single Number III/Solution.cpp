class Solution {
public:
    vector<int> singleNumber(vector<int>& nums) {
        long long eor = 0;
        for (int x : nums) eor ^= x;
        int lowbit = eor & (-eor);
        vector<int> ans(2);
        for (int x : nums)
            if ((x & lowbit) == 0) ans[0] ^= x;
        ans[1] = eor ^ ans[0];
        return ans;
    }
};