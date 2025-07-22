class Solution {
public:
    int minElement(vector<int>& nums) {
        int ans = 100;
        for (int x : nums) {
            int y = 0;
            for (; x > 0; x /= 10) {
                y += x % 10;
            }
            ans = min(ans, y);
        }
        return ans;
    }
};
