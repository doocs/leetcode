class Solution {
public:
    int maxFrequencyScore(vector<int>& nums, long long k) {
        sort(nums.begin(), nums.end());
        int n = nums.size();
        vector<long long> s(n + 1, 0);
        for (int i = 1; i <= n; i++) {
            s[i] = s[i - 1] + nums[i - 1];
        }

        int l = 0, r = n;
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            bool ok = false;

            for (int i = 0; i <= n - mid; i++) {
                int j = i + mid;
                int x = nums[(i + j) / 2];
                long long left = ((i + j) / 2 - i) * (long long) x - (s[(i + j) / 2] - s[i]);
                long long right = (s[j] - s[(i + j) / 2]) - ((j - (i + j) / 2) * (long long) x);

                if (left + right <= k) {
                    ok = true;
                    break;
                }
            }

            if (ok) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        return l;
    }
};