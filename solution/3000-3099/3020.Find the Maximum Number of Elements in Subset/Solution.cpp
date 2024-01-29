class Solution {
public:
    int maximumLength(vector<int>& nums) {
        unordered_map<long long, int> cnt;
        for (int x : nums) {
            ++cnt[x];
        }
        int ans = cnt[1] - (cnt[1] % 2 ^ 1);
        cnt.erase(1);
        for (auto [v, _] : cnt) {
            int t = 0;
            long long x = v;
            while (cnt.count(x) && cnt[x] > 1) {
                x = x * x;
                t += 2;
            }
            t += cnt.count(x) ? 1 : -1;
            ans = max(ans, t);
        }
        return ans;
    }
};
