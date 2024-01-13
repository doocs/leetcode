class Solution {
public:
    int specialArray(vector<int>& nums) {
        for (int x = 1; x <= nums.size(); ++x) {
            int cnt = 0;
            for (int v : nums) cnt += v >= x;
            if (cnt == x) return x;
        }
        return -1;
    }
};