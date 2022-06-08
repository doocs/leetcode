class Solution {
public:
    int findUnsortedSubarray(vector<int>& nums) {
        vector<int> arr = nums;
        sort(arr.begin(), arr.end());
        int left = 0, right = arr.size() - 1;
        while (left <= right && nums[left] == arr[left]) ++left;
        while (left <= right && nums[right] == arr[right]) --right;
        return right - left + 1;
    }
};