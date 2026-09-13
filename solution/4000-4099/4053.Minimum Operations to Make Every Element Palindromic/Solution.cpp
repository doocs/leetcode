class Solution {
public:
    long long minOperations(vector<int>& nums) {
        static vector<long long> ps[2];
        if (ps[0].empty()) {
            for (long long i = 1; i <= 100000; ++i) {
                string s = to_string(i);
                string t1 = s;
                reverse(t1.begin(), t1.end());
                string t2 = s.substr(0, s.size() - 1);
                reverse(t2.begin(), t2.end());
                long long x = stoll(s + t1);
                ps[x & 1].push_back(x);
                long long y = stoll(s + t2);
                ps[y & 1].push_back(y);
            }
            sort(ps[0].begin(), ps[0].end());
            sort(ps[1].begin(), ps[1].end());
        }
        long long ans = 0;
        for (int x : nums) {
            auto& p = ps[x & 1];
            auto it = lower_bound(p.begin(), p.end(), x);
            long long t = LLONG_MAX;
            if (it != p.end()) {
                t = *it - x;
            }
            if (it != p.begin()) {
                t = min(t, (long long) x - *prev(it));
            }
            ans += t / 2;
        }
        return ans;
    }
};
