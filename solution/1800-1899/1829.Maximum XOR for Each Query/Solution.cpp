class Solution {
public:
    vector<int> getMaximumXor(vector<int>& nums, int maximumBit) {
        int n = nums.size();
        vector<int> s(n + 1);
        for (int i = 0; i < n; ++i) s[i + 1] = s[i] ^ nums[i];
        vector<int> ans;
        for (int i = n; i > 0; --i) {
            int t = 0, x = s[i];
            for (int j = 0; j < maximumBit; ++j) {
                if (((x >> j) & 1) == 0) t |= (1 << j);
            }
            ans.push_back(t);
        }
        return ans;
    }
};