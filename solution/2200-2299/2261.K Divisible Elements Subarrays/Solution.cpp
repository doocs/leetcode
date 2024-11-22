class Solution {
public:
    int countDistinct(vector<int>& nums, int k, int p) {
        unordered_set<long long> s;
        int n = nums.size();
        int base1 = 131, base2 = 13331;
        int mod1 = 1e9 + 7, mod2 = 1e9 + 9;
        for (int i = 0; i < n; ++i) {
            long long h1 = 0, h2 = 0;
            int cnt = 0;
            for (int j = i; j < n; ++j) {
                cnt += nums[j] % p == 0;
                if (cnt > k) {
                    break;
                }
                h1 = (h1 * base1 + nums[j]) % mod1;
                h2 = (h2 * base2 + nums[j]) % mod2;
                s.insert(h1 << 32 | h2);
            }
        }
        return s.size();
    }
};
