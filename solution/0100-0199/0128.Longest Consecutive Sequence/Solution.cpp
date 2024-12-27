class Solution {
public:
    int longestConsecutive(vector<int>& nums) {
        unordered_set<int> s(nums.begin(), nums.end());
        int ans = 0;
        unordered_map<int, int> d;
        for (int x : nums) {
            int y = x;
            while (s.contains(y)) {
                s.erase(y++);
            }
            d[x] = (d.contains(y) ? d[y] : 0) + y - x;
            ans = max(ans, d[x]);
        }
        return ans;
    }
};
