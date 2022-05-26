class Solution {
public:
    int majorityElement(vector<int>& nums) {
        int votes = 0, x = 0;
        for (int num : nums) {
            if (votes == 0) x = num;
            votes += x == num ? 1 : -1;
        }
        return x;
    }
};
