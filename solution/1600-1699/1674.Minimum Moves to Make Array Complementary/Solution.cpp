class Solution {
public:
    int minMoves(vector<int>& nums, int limit) {
        int n = nums.size();
        vector<int> d(limit * 2 + 2);
        for (int i = 0; i<n> > 1; ++i) {
            int a = min(nums[i], nums[n - i - 1]);
            int b = max(nums[i], nums[n - i - 1]);

            d[2] += 2;
            d[limit * 2 + 1] -= 2;

            d[a + 1] -= 1;
            d[b + limit + 1] += 1;

            d[a + b] -= 1;
            d[a + b + 1] += 1;
        }
        int ans = n, s = 0;
        for (int i = 2; i <= limit * 2; ++i) {
            s += d[i];
            if (ans > s) {
                ans = s;
            }
        }
        return ans;
    }
};