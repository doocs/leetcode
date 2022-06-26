class Solution {
public:
    int maximumXOR(vector<int>& nums) {
        int ans = 0;
        for (int& v : nums) ans |= v;
        return ans;
    }
};