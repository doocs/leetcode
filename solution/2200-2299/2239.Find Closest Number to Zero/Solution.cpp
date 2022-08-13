class Solution {
public:
    int findClosestNumber(vector<int>& nums) {
        int ans = 0, d = 1e6;
        for (int& v : nums) {
            int t = abs(v);
            if (t < d || (t == d && v > ans)) {
                ans = v;
                d = t;
            }
        }
        return ans;
    }
};