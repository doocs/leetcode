class Solution {
public:
    vector<int> findErrorNums(vector<int>& nums) {
        int n = nums.size();
        int s1 = (1 + n) * n / 2;
        int s2 = 0;
        unordered_set<int> set(nums.begin(), nums.end());
        for (int x : set) {
            s2 += x;
        }
        int s = accumulate(nums.begin(), nums.end(), 0);
        return {s - s2, s1 - s2};
    }
};