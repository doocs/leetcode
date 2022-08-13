class Solution {
public:
    int minSwaps(vector<int>& nums) {
        int cnt = 0;
        for (int& v : nums) cnt += v;
        int n = nums.size();
        vector<int> s((n << 1) + 1);
        for (int i = 0; i < (n << 1); ++i) s[i + 1] = s[i] + nums[i % n];
        int mx = 0;
        for (int i = 0; i < (n << 1); ++i) {
            int j = i + cnt - 1;
            if (j < (n << 1)) mx = max(mx, s[j + 1] - s[i]);
        }
        return cnt - mx;
    }
};