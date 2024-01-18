class Solution {
public:
    int minArray(vector<int>& numbers) {
        int l = 0, r = numbers.size() - 1;
        while (l < r) {
            if (numbers[l] < numbers[r]) {
                break;
            }
            int mid = (l + r) >> 1;
            if (numbers[mid] > numbers[l]) {
                l = mid + 1;
            } else if (numbers[mid] < numbers[l]) {
                r = mid;
            } else {
                ++l;
            }
        }
        return numbers[l];
    }
};