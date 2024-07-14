class Solution {
public:
    int findKthLargest(vector<int>& nums, int k) {
        unordered_map<int, int> cnt;
        int m = INT_MIN;
        for (int x : nums) {
            ++cnt[x];
            m = max(m, x);
        }
        for (int i = m;; --i) {
            k -= cnt[i];
            if (k <= 0) {
                return i;
            }
        }
    }
};
