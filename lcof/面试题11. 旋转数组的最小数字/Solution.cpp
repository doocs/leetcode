class Solution {
public:
    int minArray(vector<int>& numbers) {
        int left = 0, right = numbers.size() - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (numbers[mid] > numbers[right]) {
                left = mid;
            } else if (numbers[mid] < numbers[right]) {
                right = mid;
            } else {
                --right;
            }
        }
        return min(numbers[left], numbers[right]);
    }
};
