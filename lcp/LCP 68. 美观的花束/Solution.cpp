class Solution {
public:
    int beautifulBouquet(vector<int>& flowers, int cnt) {
        int mx = *max_element(flowers.begin(), flowers.end());
        int d[mx + 1];
        memset(d, 0, sizeof(d));
        long long ans = 0;
        const int mod = 1e9 + 7;
        for (int i = 0, j = 0; i < flowers.size(); ++i) {
            ++d[flowers[i]];
            while (d[flowers[i]] > cnt) {
                --d[flowers[j++]];
            }
            ans = (ans + i - j + 1) % mod;
        }
        return ans;
    }
};