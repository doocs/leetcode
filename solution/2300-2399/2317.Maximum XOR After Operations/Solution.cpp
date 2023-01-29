class Solution {
public:
    int maximumXOR(vector<int>& nums) {
        int ans = 0;
        for (int& x : nums) {
            ans |= x;
        }
        return ans;
    }
};