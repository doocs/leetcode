class Solution {
public:
    bool containsNearbyAlmostDuplicate(vector<int>& nums, int k, int t) {
        set<long> s;
        for (int i = 0; i < nums.size(); ++i) {
            auto it = s.lower_bound((long) nums[i] - t);
            if (it != s.end() && *it <= (long) nums[i] + t) return true;
            s.insert((long) nums[i]);
            if (i >= k) s.erase((long) nums[i - k]);
        }
        return false;
    }
};