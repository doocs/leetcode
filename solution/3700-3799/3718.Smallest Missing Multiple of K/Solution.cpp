class Solution {
public:
    int missingMultiple(vector<int>& nums, int k) {
        unordered_set<int> s;
        for (int x : nums) {
            s.insert(x);
        }
        for (int i = 1;; ++i) {
            int x = k * i;
            if (!s.contains(x)) {
                return x;
            }
        }
    }
};
