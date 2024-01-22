class Solution {
public:
    int minimumArrayLength(vector<int>& nums) {
        int mi = *min_element(nums.begin(), nums.end());
        int cnt = 0;
        for (int x : nums) {
            if (x % mi) {
                return 1;
            }
            cnt += x == mi;
        }
        return (cnt + 1) / 2;
    }
};