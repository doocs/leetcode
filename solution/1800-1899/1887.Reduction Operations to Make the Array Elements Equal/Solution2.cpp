class Solution {
public:
    int reductionOperations(vector<int>& nums) {
        map<int, int> m;
        for (int v : nums) ++m[v];
        int ans = 0, cnt = 0;
        for (auto [_, v] : m) {
            ans += cnt * v;
            ++cnt;
        }
        return ans;
    }
};