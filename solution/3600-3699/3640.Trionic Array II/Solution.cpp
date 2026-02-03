class Solution {
public:
    long long maxSumTrionic(vector<int>& nums) {
        int n = nums.size();
        int i = 0;
        long long ans = LLONG_MIN;
        while (i < n) {
            int l = i;
            i += 1;
            while (i < n && nums[i - 1] < nums[i]) {
                i += 1;
            }
            if (i == l + 1) {
                continue;
            }

            int p = i - 1;
            long long s = nums[p - 1] + nums[p];
            while (i < n && nums[i - 1] > nums[i]) {
                s += nums[i];
                i += 1;
            }
            if (i == p + 1 || i == n || nums[i - 1] == nums[i]) {
                continue;
            }

            int q = i - 1;
            s += nums[i];
            i += 1;
            long long mx = 0, t = 0;
            while (i < n && nums[i - 1] < nums[i]) {
                t += nums[i];
                i += 1;
                mx = max(mx, t);
            }
            s += mx;

            mx = 0, t = 0;
            for (int j = p - 2; j >= l; j--) {
                t += nums[j];
                mx = max(mx, t);
            }
            s += mx;

            ans = max(ans, s);
            i = q;
        }
        return ans;
    }
};
