class Solution {
public:
    int specialTriplets(vector<int>& nums) {
        unordered_map<int, int> left, right;
        for (int x : nums) {
            right[x]++;
        }
        long long ans = 0;
        const int mod = 1e9 + 7;
        for (int x : nums) {
            right[x]--;
            ans = (ans + 1LL * left[x * 2] * right[x * 2] % mod) % mod;
            left[x]++;
        }
        return (int) ans;
    }
};
