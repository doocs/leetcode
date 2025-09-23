class Solution {
public:
    int evenNumberBitwiseORs(vector<int>& nums) {
        int ans = 0;
        for (int x : nums) {
            if (x % 2 == 0) {
                ans |= x;
            }
        }
        return ans;
    }
};
