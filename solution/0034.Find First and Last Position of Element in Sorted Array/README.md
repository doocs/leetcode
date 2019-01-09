## 在排序数组中查找元素的第一个和最后一个位置

### 问题描述

给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。

你的算法时间复杂度必须是 O(log n) 级别。

如果数组中不存在目标值，返回 [-1, -1]。

```
示例 1:
输入: nums = [5,7,7,8,8,10], target = 8
输出: [3,4]

示例 2:
输入: nums = [5,7,7,8,8,10], target = 6
输出: [-1,-1]
```

### 思路

二分查找找下标，找到下标对其左右查找是否等于目标值就好了

```CPP

class Solution {
public:
    bool binarySearch(vector<int> &nums,int &target,int &pos,int left,int right){
        int mid = left + ((right - left) >> 1);
        if(nums[left] == target){
            pos = left;
            return true;
        }
        if(nums[right] == target){
            pos = right;
            return true;
        }
        if(nums[mid] == target){
            pos = mid;
            return true;
        }
        
        if(left == mid){
            return false;
        }
        else if(nums[mid] > target){
            return binarySearch(nums,target,pos,left,mid-1);
        }
        else if(nums[mid] < target){
            return binarySearch(nums,target,pos,mid+1,right);
        } 
        
        return false;
    }
    
    vector<int> searchRange(vector<int>& nums, int target) {
        int pos = 0;
        int len = nums.size();
        if(len == 0)return {-1,-1};
        if(binarySearch(nums,target,pos,0,len-1)){
            int left = pos;
            int right = pos;
            while(left>0 && nums[left-1] == nums[pos])left--;
            while(right<len-1 && nums[right+1] == nums[pos])right++;
            return {left,right};
        }
        else{
            return {-1,-1};
        }
    }
};
```