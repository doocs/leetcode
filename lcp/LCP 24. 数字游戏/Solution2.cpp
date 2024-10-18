class Solution {
public:
    vector<int> numsGame(vector<int>& nums) {
        multiset<int> l, r;
        int n = nums.size();
        vector<int> ans(n);
        const int mod = 1e9 + 7;
        long long s = 0, t = 0;
        for (int i = 0; i < n; ++i) {
            int x = nums[i] - i;
            r.insert(x);
            t += x;
            x = *r.begin();
            r.erase(r.begin());
            t -= x;
            l.insert(x);
            s += x;
            while (l.size() - r.size() > 1) {
                x = *l.rbegin();
                l.erase(l.find(x));
                s -= x;
                r.insert(x);
                t += x;
            }
            long long mid = *l.rbegin();
            ans[i] = (mid * l.size() - s + t - mid * r.size()) % mod;
        }
        return ans;
    }
};
