class Solution {
public:
    long long minimumCost(vector<int>& nums, int k, int dist) {
        --k;
        multiset<int> l(nums.begin() + 1, nums.begin() + dist + 2), r;
        long long s = accumulate(nums.begin(), nums.begin() + dist + 2, 0LL);
        while (l.size() > k) {
            int x = *l.rbegin();
            l.erase(l.find(x));
            s -= x;
            r.insert(x);
        }
        long long ans = s;
        for (int i = dist + 2; i < nums.size(); ++i) {
            int x = nums[i - dist - 1];
            auto it = l.find(x);
            if (it != l.end()) {
                l.erase(it);
                s -= x;
            } else {
                r.erase(r.find(x));
            }
            int y = nums[i];
            if (y < *l.rbegin()) {
                l.insert(y);
                s += y;
            } else {
                r.insert(y);
            }
            while (l.size() == k - 1) {
                int x = *r.begin();
                r.erase(r.find(x));
                l.insert(x);
                s += x;
            }
            while (l.size() == k + 1) {
                int x = *l.rbegin();
                l.erase(l.find(x));
                s -= x;
                r.insert(x);
            }
            ans = min(ans, s);
        }
        return ans;
    }
};
