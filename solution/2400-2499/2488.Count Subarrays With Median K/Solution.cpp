class Solution {
public:
    int countSubarrays(vector<int>& nums, int k) {
        int n = nums.size();
        int i = 0;
        for (int j = 0; j < n; ++j) {
            if (nums[j] == k) {
                i = j;
                break;
            }
        }
        int ans = 1;
        int d[n << 1 | 1];
        memset(d, 0, sizeof d);
        int mi = 0, mx = 0;
        for (int j = i + 1; j < n; ++j) {
            if (nums[j] < k) ++mi;
            else ++mx;
            if (mx - mi >= 0 && mx - mi <= 1) ++ans;
            ++d[mx - mi + n];
        }
        mi = 0, mx = 0;
        for (int j = i - 1; ~j; --j) {
            if (nums[j] < k) ++mi;
            else ++mx;
            if (mx - mi >= 0 && mx - mi <= 1) ++ans;
            ans += d[mi - mx + n] + d[mi - mx + n + 1];
        }
        return ans;
    }
};