class Solution {
public:
    int minimumOperations(vector<int>& nums) {
        int ans = 0;
        for (int x : nums) {
            int mod = x % 3;
            if (mod) {
                ans += min(mod, 3 - mod);
            }
        }
        return ans;
    }
};