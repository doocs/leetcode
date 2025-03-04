class Solution {
public:
    bool isConsecutive(vector<int>& nums) {
        unordered_set<int> s;
        int mi = nums[0], mx = 0;
        for (int x : nums) {
            if (s.contains(x)) {
                return false;
            }
            s.insert(x);
            mi = min(mi, x);
            mx = max(mx, x);
        }
        return mx - mi + 1 == nums.size();
    }
};
