class Solution {
public:
    vector<bool> checkArithmeticSubarrays(vector<int>& nums, vector<int>& l, vector<int>& r) {
        vector<bool> res;
        for (int i = 0; i < l.size(); ++i) {
            res.push_back(check(nums, l[i], r[i]));
        }
        return res;
    }

    bool check(vector<int>& nums, int l, int r) {
        if (r - l < 2) return true;
        unordered_set<int> s;
        int mx = -100010;
        int mi = 100010;
        for (int i = l; i <= r; ++i) {
            s.insert(nums[i]);
            mx = max(mx, nums[i]);
            mi = min(mi, nums[i]);
        }
        if ((mx - mi) % (r - l) != 0) return false;
        int delta = (mx - mi) / (r - l);
        for (int i = 1; i <= r - l; ++i) {
            if (!s.count(mi + delta * i)) return false;
        }
        return true;
    }
};