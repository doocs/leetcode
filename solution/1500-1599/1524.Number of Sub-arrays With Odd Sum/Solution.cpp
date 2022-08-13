class Solution {
public:
    int numOfSubarrays(vector<int>& arr) {
        const int MOD = 1e9 + 7;
        vector<int> counter(2);
        int s = 0, ans = 0;
        for (int& v : arr) {
            s += v;
            ++counter[s % 2];
            if (s % 2 == 1)
                ans = (ans + 1 + counter[0]) % MOD;
            else
                ans = (ans + counter[1]) % MOD;
        }
        return ans;
    }
};