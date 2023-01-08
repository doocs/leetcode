class Solution {
public:
    int xorBeauty(vector<int>& nums) {
        int ans = 0;
        for (auto& x : nums) {
            ans ^= x;
        }
        return ans;
    }
};