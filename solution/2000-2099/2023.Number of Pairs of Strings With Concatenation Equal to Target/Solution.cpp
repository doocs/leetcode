class Solution {
public:
    int numOfPairs(vector<string>& nums, string target) {
        unordered_map<string, int> cnt;
        for (auto& x : nums) ++cnt[x];
        int ans = 0;
        for (int i = 1; i < target.size(); ++i) {
            string a = target.substr(0, i);
            string b = target.substr(i);
            int x = cnt[a], y = cnt[b];
            if (a != b) {
                ans += x * y;
            } else {
                ans += x * (y - 1);
            }
        }
        return ans;
    }
};