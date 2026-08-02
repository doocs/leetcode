class Solution {
public:
    int countRatioSubarrays(vector<int>& nums, int a, int b) {
        int n = nums.size();
        long long ans = 0;

        for (int i = 0; i < n; i++) {
            int y = 0;

            for (int j = i; j < n; j++) {
                y += nums[j] % 2;
                int x = j - i + 1 - y;

                if (y > 0 && 1LL * x * b <= 1LL * y * a) {
                    ans++;
                }
            }
        }

        return ans;
    }
};