class Solution {
public:
    int sumOfEncryptedInt(vector<int>& nums) {
        auto encrypt = [&](int x) {
            int mx = 0, p = 0;
            for (; x; x /= 10) {
                mx = max(mx, x % 10);
                p = p * 10 + 1;
            }
            return mx * p;
        };
        int ans = 0;
        for (int x : nums) {
            ans += encrypt(x);
        }
        return ans;
    }
};