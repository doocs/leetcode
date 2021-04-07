/**
 *  Author: Moriarty12138
 */
 class Solution {
 public:
     bool search(vector<int>& nums, int target) {
         if(nums.size() < 1) {
             return false;
         }
         if(nums.size() == 1) {
             return nums[0] == target;
         }
         int n = nums.size();
         int l = 0, r = n - 1;
         while(l <= r) {
             int mid = (l + r) / 2;
             if(nums[mid] == target) {
                 return true;
             }
             if(nums[l] == nums[mid] && nums[mid] == nums[r]) {
                 l++;
                 r--;
             }else if(nums[l] <= nums[mid]) {
                 //
                 if(nums[l] <= target && target < nums[mid]) {
                     r = mid - 1;
                 }else {
                     l = mid + 1;
                 }
             }else {
                 //
                 if(nums[mid] < target && target <= nums[r]) {
                     l = mid + 1;
                 }else {
                     r = mid - 1;
                 }
             }
         }
         return false;
     }
 };
