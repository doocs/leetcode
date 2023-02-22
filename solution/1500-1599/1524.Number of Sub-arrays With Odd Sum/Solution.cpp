class Solution {
public:
    int numOfSubarrays(vector<int>& arr) {
        const int mod = 1e9 + 7;
        int cnt[2] = {1, 0};
        int ans = 0, s = 0;
        for (int x : arr) {
            s += x;
            ans = (ans + cnt[s & 1 ^ 1]) % mod;
            ++cnt[s & 1];
        }
        return ans;
    }
};