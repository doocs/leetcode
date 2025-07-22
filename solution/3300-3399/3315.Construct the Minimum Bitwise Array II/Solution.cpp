class Solution {
public:
    vector<int> minBitwiseArray(vector<int>& nums) {
        vector<int> ans;
        for (int x : nums) {
            if (x == 2) {
                ans.push_back(-1);
            } else {
                for (int i = 1; i < 32; ++i) {
                    if (x >> i & 1 ^ 1) {
                        ans.push_back(x ^ 1 << (i - 1));
                        break;
                    }
                }
            }
        }
        return ans;
    }
};
