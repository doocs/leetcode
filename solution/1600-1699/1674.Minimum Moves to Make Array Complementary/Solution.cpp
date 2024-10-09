class Solution {
public:
    int minMoves(vector<int>& nums, int limit) {
        int n = nums.size();
        int d[limit * 2 + 2];
        memset(d, 0, sizeof(d));
        for (int i = 0; i < n / 2; ++i) {
            int x = nums[i], y = nums[n - i - 1];
            if (x > y) {
                swap(x, y);
            }
            d[2] += 2;
            d[x + 1] -= 2;
            d[x + 1] += 1;
            d[x + y] -= 1;
            d[x + y + 1] += 1;
            d[y + limit + 1] -= 1;
            d[y + limit + 1] += 2;
        }
        int ans = n;
        for (int i = 2, s = 0; i <= limit * 2; ++i) {
            s += d[i];
            ans = min(ans, s);
        }
        return ans;
    }
};