class Solution {
public:
    int findUnsortedSubarray(vector<int>& nums) {
        vector<int> arr = nums;
        sort(arr.begin(), arr.end());
        int l = 0, r = arr.size() - 1;
        while (l <= r && arr[l] == nums[l]) {
            l++;
        }
        while (l <= r && arr[r] == nums[r]) {
            r--;
        }
        return r - l + 1;
    }
};