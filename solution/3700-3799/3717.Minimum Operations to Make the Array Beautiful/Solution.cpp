class Solution {
public:
    int minOperations(vector<int>& nums) {
        unordered_map<int, int> f;
        f[nums[0]] = 0;

        for (int i = 1; i < nums.size(); i++) {
            int x = nums[i];
            unordered_map<int, int> g;
            for (auto [pre, s] : f) {
                int cur = (x + pre - 1) / pre * pre;
                while (cur <= 100) {
                    int val = s + (cur - x);
                    auto jt = g.find(cur);
                    if (jt == g.end() || jt->second > val) {
                        g[cur] = val;
                    }
                    cur += pre;
                }
            }
            f = move(g);
        }

        int ans = INT_MAX;
        for (auto& it : f) {
            ans = min(ans, it.second);
        }
        return ans;
    }
};
