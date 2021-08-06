class Solution {
public:
    int findNumbers(vector<int>& nums) {
        int s = 0;
        for (int num : nums) {
            s += (to_string(num).size() & 1) == 0;
        }
        return s;
    }
};