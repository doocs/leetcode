class Solution {
public:
    long long numberOfSubsequences(vector<int>& nums) {
        int n = nums.size();
        unordered_map<int, int> cnt;

        for (int r = 4; r < n - 2; ++r) {
            int c = nums[r];
            for (int s = r + 2; s < n; ++s) {
                int d = nums[s];
                int g = gcd(c, d);
                cnt[((d / g) << 12) | (c / g)]++;
            }
        }

        long long ans = 0;
        for (int q = 2; q < n - 4; ++q) {
            int b = nums[q];
            for (int p = 0; p < q - 1; ++p) {
                int a = nums[p];
                int g = gcd(a, b);
                ans += cnt[((a / g) << 12) | (b / g)];
            }
            int c = nums[q + 2];
            for (int s = q + 4; s < n; ++s) {
                int d = nums[s];
                int g = gcd(c, d);
                cnt[((d / g) << 12) | (c / g)]--;
            }
        }
        return ans;
    }
};
