class Solution {
public:
    int countStaircase(vector<int>& nums) {
        int n = nums.size();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            unordered_map<int, int> orders, cnt;
            int ok = 0;
            for (int j = i; j < n; ++j) {
                int x = nums[j];
                if (!orders.contains(x)) {
                    orders[x] = orders.size() + 1;
                }
                int count = ++cnt[x];
                int order = orders[x];
                if (count > order) {
                    break;
                }
                if (count == order && ++ok == (int) cnt.size()) {
                    ++ans;
                }
            }
        }
        return ans;
    }
};