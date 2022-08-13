class Solution {
public:
    int bestRotation(vector<int>& nums) {
        int n = nums.size();
        int mx = -1, ans = n;
        vector<int> d(n);
        for (int i = 0; i < n; ++i) {
            int l = (i + 1) % n;
            int r = (n + i + 1 - nums[i]) % n;
            ++d[l];
            --d[r];
        }
        int s = 0;
        for (int k = 0; k < n; ++k) {
            s += d[k];
            if (s > mx) {
                mx = s;
                ans = k;
            }
        }
        return ans;
    }
};