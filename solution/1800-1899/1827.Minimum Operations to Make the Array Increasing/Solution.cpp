class Solution {
public:
    int minOperations(vector<int>& nums) {
        int ans = 0;
        int mx = 0;
        for (int& v : nums) {
            ans += max(0, mx + 1 - v);
            mx = max(mx + 1, v);
        }
        return ans;
    }
};