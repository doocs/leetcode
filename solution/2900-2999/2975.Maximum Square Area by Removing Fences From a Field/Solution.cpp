class Solution {
public:
    int maximizeSquareArea(int m, int n, vector<int>& hFences, vector<int>& vFences) {
        auto f = [](vector<int>& nums, int k) {
            nums.push_back(k);
            nums.push_back(1);
            sort(nums.begin(), nums.end());
            unordered_set<int> s;
            for (int i = 0; i < nums.size(); ++i) {
                for (int j = 0; j < i; ++j) {
                    s.insert(nums[i] - nums[j]);
                }
            }
            return s;
        };
        auto hs = f(hFences, m);
        auto vs = f(vFences, n);
        int ans = 0;
        for (int h : hs) {
            if (vs.count(h)) {
                ans = max(ans, h);
            }
        }
        const int mod = 1e9 + 7;
        return ans > 0 ? 1LL * ans * ans % mod : -1;
    }
};