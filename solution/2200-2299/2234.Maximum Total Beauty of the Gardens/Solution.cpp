class Solution {
public:
    long long maximumBeauty(vector<int>& flowers, long long newFlowers, int target, int full, int partial) {
        sort(flowers.begin(), flowers.end());
        int n = flowers.size();
        long long s[n + 1];
        s[0] = 0;
        for (int i = 1; i <= n; ++i) {
            s[i] = s[i - 1] + flowers[i - 1];
        }
        long long ans = 0;
        int i = flowers.end() - lower_bound(flowers.begin(), flowers.end(), target);
        for (int x = i; x <= n; ++x) {
            newFlowers -= (x == 0 ? 0 : max(target - flowers[n - x], 0));
            if (newFlowers < 0) {
                break;
            }
            int l = 0, r = n - x - 1;
            while (l < r) {
                int mid = (l + r + 1) >> 1;
                if (1LL * flowers[mid] * (mid + 1) - s[mid + 1] <= newFlowers) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            int y = 0;
            if (r != -1) {
                long long cost = 1LL * flowers[l] * (l + 1) - s[l + 1];
                long long mx = flowers[l] + (newFlowers - cost) / (l + 1);
                long long threshold = target - 1;
                y = min(mx, threshold);
            }
            ans = max(ans, 1LL * x * full + 1LL * y * partial);
        }
        return ans;
    }
};