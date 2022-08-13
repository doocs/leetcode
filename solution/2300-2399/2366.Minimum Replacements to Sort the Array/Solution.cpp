class Solution {
public:
    long long minimumReplacement(vector<int>& nums) {
        long long ans = 0;
        int n = nums.size();
        int mi = nums[n - 1];
        for (int i = n - 2; ~i; --i) {
            int v = nums[i];
            if (v <= mi) {
                mi = v;
                continue;
            }
            int k = (v + mi - 1) / mi;
            ans += k - 1;
            mi = v / k;
        }
        return ans;
    }
};