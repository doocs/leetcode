class Solution {
public:
    int findShortestSubArray(vector<int>& nums) {
        unordered_map<int, int> cnt;
        unordered_map<int, int> left;
        unordered_map<int, int> right;
        int degree = 0;
        for (int i = 0; i < nums.size(); ++i) {
            int v = nums[i];
            degree = max(degree, ++cnt[v]);
            if (!left.count(v)) {
                left[v] = i;
            }
            right[v] = i;
        }
        int ans = 1e6;
        for (int v : nums) {
            if (cnt[v] == degree) {
                int t = right[v] - left[v] + 1;
                if (ans > t) {
                    ans = t;
                }
            }
        }
        return ans;
    }
};