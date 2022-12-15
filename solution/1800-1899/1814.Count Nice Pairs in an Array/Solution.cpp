class Solution {
public:
    const int mod = 1e9 + 7;

    int countNicePairs(vector<int>& nums) {
        unordered_map<int, int> cnt;
        for (int& x : nums) {
            int y = x - rev(x);
            cnt[y]++;
        }
        long long ans = 0;
        for (auto& [_, v] : cnt) {
            ans = (ans + 1ll * v * (v - 1) / 2) % mod;
        }
        return ans;
    }

    int rev(int x) {
        int y = 0;
        while (x) {
            y = y * 10 + x % 10;
            x /= 10;
        }
        return y;
    }
};