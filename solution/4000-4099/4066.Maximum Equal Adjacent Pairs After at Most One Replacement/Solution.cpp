class Solution {
public:
    int maxEqualAdjacentPairs(vector<int>& nums) {
        unordered_map<long long, int> cnt;
        int ans = 0, mx = 0;

        for (int i = 0; i + 1 < nums.size(); i++) {
            int x = nums[i], y = nums[i + 1];
            if (x == y) {
                ans++;
            } else {
                if (x > y) {
                    swap(x, y);
                }
                long long key = ((long long) x << 30) | y;
                mx = max(mx, ++cnt[key]);
            }
        }
        ans += mx;
        return ans;
    }
};