class Solution {
public:
    bool containsNearbyAlmostDuplicate(vector<int>& nums, int indexDiff, int valueDiff) {
        set<long> s;
        for (int i = 0; i < nums.size(); ++i) {
            auto it = s.lower_bound((long) nums[i] - valueDiff);
            if (it != s.end() && *it <= (long) nums[i] + valueDiff) return true;
            s.insert((long) nums[i]);
            if (i >= indexDiff) s.erase((long) nums[i - indexDiff]);
        }
        return false;
    }
};