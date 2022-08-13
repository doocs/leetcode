class Solution {
public:
    vector<int> twoSum(vector<int>& numbers, int target) {
        int left = 1, right = numbers.size();
        while (left < right) {
            int x = numbers[left - 1] + numbers[right - 1];
            if (x == target) return {left, right};
            if (x < target)
                ++left;
            else
                --right;
        }
        return {-1, -1};
    }
};