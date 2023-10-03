class Solution {
public:
    int minSizeSubarray(vector<int>& nums, int target) {
        long long s = accumulate(nums.begin(), nums.end(), 0LL);
        int n = nums.size();
        int a = 0;
        if (target > s) {
            a = n * (target / s);
            target -= target / s * s;
        }
        if (target == s) {
            return n;
        }
        unordered_map<int, int> pos{{0, -1}};
        long long pre = 0;
        int b = 1 << 30;
        for (int i = 0; i < n; ++i) {
            pre += nums[i];
            if (pos.count(pre - target)) {
                b = min(b, i - pos[pre - target]);
            }
            if (pos.count(pre - (s - target))) {
                b = min(b, n - (i - pos[pre - (s - target)]));
            }
            pos[pre] = i;
        }
        return b == 1 << 30 ? -1 : a + b;
    }
};