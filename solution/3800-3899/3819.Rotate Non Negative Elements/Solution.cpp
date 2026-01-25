class Solution {
public:
    vector<int> rotateElements(vector<int>& nums, int k) {
        vector<int> t;
        for (int x : nums) {
            if (x >= 0) {
                t.push_back(x);
            }
        }
        int m = t.size();
        vector<int> d(m);
        for (int i = 0; i < m; i++) {
            d[((i - k) % m + m) % m] = t[i];
        }
        int j = 0;
        for (int i = 0; i < nums.size(); i++) {
            if (nums[i] >= 0) {
                nums[i] = d[j++];
            }
        }
        return nums;
    }
};
