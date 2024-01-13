class Solution {
public:
    const int mod = 1e9 + 7;

    int countPairs(vector<int>& deliciousness) {
        int mx = *max_element(deliciousness.begin(), deliciousness.end()) << 1;
        unordered_map<int, int> cnt;
        int ans = 0;
        for (auto& d : deliciousness) {
            for (int s = 1; s <= mx; s <<= 1) {
                ans = (ans + cnt[s - d]) % mod;
            }
            ++cnt[d];
        }
        return ans;
    }
};