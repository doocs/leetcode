class Solution {
public:
    vector<double> medianSlidingWindow(vector<int>& nums, int k) {
        multiset<int> l, r;
        int n = nums.size();
        vector<double> ans;
        for (int i = 0; i < n; ++i) {
            r.insert(nums[i]);
            l.insert(*r.begin());
            r.erase(r.begin());
            while (l.size() - r.size() > 1) {
                r.insert(*l.rbegin());
                l.erase(prev(l.end()));
            }
            int j = i - k + 1;
            if (j >= 0) {
                ans.push_back(k % 2 ? *l.rbegin() : ((double) *l.rbegin() + *r.begin()) / 2);
                auto it = l.find(nums[j]);
                if (it != l.end()) {
                    l.erase(it);
                } else {
                    r.erase(r.find(nums[j]));
                }
            }
        }
        return ans;
    }
};
