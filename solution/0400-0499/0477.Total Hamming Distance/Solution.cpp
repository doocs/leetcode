class Solution {
public:
    int totalHammingDistance(vector<int>& nums) {
        int ans = 0;
        for (int i = 0; i < 31; ++i) {
            int a = 0, b = 0;
            for (int& v : nums) {
                int t = (v >> i) & 1;
                a += t;
                b += t ^ 1;
            }
            ans += a * b;
        }
        return ans;
    }
};