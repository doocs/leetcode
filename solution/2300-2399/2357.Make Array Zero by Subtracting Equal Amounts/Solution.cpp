class Solution {
public:
    int minimumOperations(vector<int>& nums) {
        bool s[101]{};
        s[0] = true;
        int ans = 0;
        for (int& x : nums) {
            if (!s[x]) {
                ++ans;
                s[x] = true;
            }
        }
        return ans;
    }
};