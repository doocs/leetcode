class Solution {
public:
    int minOperations(vector<int>& nums) {
        int ans = 0, v = 0;
        for (int x : nums) {
            x ^= v;
            if (x == 0) {
                v ^= 1;
                ++ans;
            }
        }
        return ans;
    }
};