class Solution {
public:
    int countElements(vector<int>& nums) {
        int mi = 1e6, mx = -1e6;
        for (int num : nums) {
            mi = min(mi, num);
            mx = max(mx, num);
        }
        int ans = 0;
        for (int num : nums)
            if (mi < num && num < mx)
                ++ans;
        return ans;
    }
};