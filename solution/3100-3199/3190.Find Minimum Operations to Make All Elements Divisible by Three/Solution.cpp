class Solution {
public:
    int minimumOperations(vector<int>& nums) {
        int ans = 0;
        for (int x : nums) {
            ans += x % 3 != 0 ? 1 : 0;
        }
        return ans;
    }
};
