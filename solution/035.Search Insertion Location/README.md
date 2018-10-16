## 搜索位置描述
### 题目描述


给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。

你可以假设数组中无重复元素。

示例 1:

输入: [1,3,5,6], 5
输出: 2
示例 2:

输入: [1,3,5,6], 2
输出: 1
示例 3:

输入: [1,3,5,6], 7
输出: 4
示例 4:

输入: [1,3,5,6], 0
输出: 0

### 解法
首先判断传入的数组为0,1这样的长度

因为是一个给定的排序数组，在循环时就可以判断是否存在的同时判断大小，有相同的则直接返回索引，
不存在则判断大小，只要相较于当前索引的元素较小，则可以认为该目标数在数组中无对应元素，直接返回索引即可

除此之外还可用二分法做解

```java
class Solution {
    public int searchInsert(int[] nums, int target) {
        if(nums.length == 0){
            return 0;
        }
        if(nums.length == 1){
            if(nums[0] < target){
                return 1;
            } else {
                return 0;
            }
        }
        for(int i = 0;i < nums.length;i++){
            if(nums[i] == target){
                return i;
            } else {
                int s = Math.min(nums[i],target);
                if(s == target){
                    return i;
                }
            }
        }
        return nums.length;
    }
}
```