class Solution {
public:
    int maximumLength(vector<int>& nums, int k) {
        int n = nums.size();
        vector<int> f(n, 1);
        unordered_map<int, int> last;
        int ans = 1;
        for (int i = 0; i < n; i++) {
            int x = nums[i] % k;
            int y = (k - x) % k;
            if (last.count(y)) {
                int j = last[y];
                f[i] = max(f[i], f[j] + 1);
                ans = max(ans, f[i]);
            }
            last[x] = i;
        }
        return ans;
    }
};