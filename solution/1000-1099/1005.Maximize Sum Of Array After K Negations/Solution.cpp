class Solution {
public:
    int largestSumAfterKNegations(vector<int>& nums, int k) {
        unordered_map<int, int> counter;
        for (int num : nums) ++counter[num];
        int ans = accumulate(nums.begin(), nums.end(), 0);
        for (int i = -100; i < 0; ++i) {
            if (counter[i]) {
                int ops = min(counter[i], k);
                ans -= (i * ops * 2);
                counter[i] -= ops;
                counter[-i] += ops;
                k -= ops;
                if (k == 0) break;
            }
        }
        if (k > 0 && k % 2 == 1 && !counter[0]) {
            for (int i = 1; i < 101; ++i) {
                if (counter[i]) {
                    ans -= 2 * i;
                    break;
                }
            }
        }
        return ans;
    }
};