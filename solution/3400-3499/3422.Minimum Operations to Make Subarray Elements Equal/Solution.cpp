class Solution {
public:
    long long minOperations(vector<int>& nums, int k) {
        multiset<int> l, r;
        long long s1 = 0, s2 = 0, ans = 1e18;
        for (int i = 0; i < nums.size(); ++i) {
            l.insert(nums[i]);
            s1 += nums[i];
            int y = *l.rbegin();
            l.erase(l.find(y));
            s1 -= y;
            r.insert(y);
            s2 += y;
            if (r.size() - l.size() > 1) {
                y = *r.begin();
                r.erase(r.find(y));
                s2 -= y;
                l.insert(y);
                s1 += y;
            }
            if (i >= k - 1) {
                long long x = *r.begin();
                ans = min(ans, s2 - x * (int) r.size() + x * (int) l.size() - s1);
                int j = i - k + 1;
                if (r.contains(nums[j])) {
                    r.erase(r.find(nums[j]));
                    s2 -= nums[j];
                } else {
                    l.erase(l.find(nums[j]));
                    s1 -= nums[j];
                }
            }
        }
        return ans;
    }
};
