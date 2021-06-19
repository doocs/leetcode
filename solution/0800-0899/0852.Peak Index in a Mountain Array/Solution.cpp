class Solution {
public:
    int peakIndexInMountainArray(vector<int>& arr) {
        int left = 1, right = arr.size() - 2;
        while (left < right) {
            int mid = left + right >> 1;
            if (arr[mid] < arr[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }
};