class Solution {
public:
    int numOfSubarrays(vector<int>& arr) {
        int cnt[2] = {1, 0};
        int ans = 0, s = 0;
        const int mod = 1e9 + 7;
        for (int x : arr) {
            s = (s + x) % 2;
            ans = (ans + cnt[s ^ 1]) % mod;
            ++cnt[s];
        }
        return ans;
    }
};
