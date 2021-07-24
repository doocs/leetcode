class Solution {
public:
    vector<int> twoSum(vector<int>& numbers, int target) {
        int left = 0, right = numbers.size() - 1;
        while (left < right) {
            if (numbers[left] + numbers[right] == target) {
                return {left + 1, right + 1};
            }
            if (numbers[left] + numbers[right] < target) ++left;
            else --right;
        }
        return {-1, -1};
    }
};