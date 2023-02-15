class Solution {
public:
    int maxSumDivThree(vector<int>& nums) {
        int f[3]{};
        for (int x : nums) {
            int a = f[0] + x, b = f[1] + x, c = f[2] + x;
            f[a % 3] = max(f[a % 3], a);
            f[b % 3] = max(f[b % 3], b);
            f[c % 3] = max(f[c % 3], c);
        }
        return f[0];
    }
};