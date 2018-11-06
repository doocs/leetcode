## 缺失的第一个正数

### 问题描述

给定一个未排序的整数数组，找出其中没有出现的最小的正整数。

```
示例 1:
输入: [1,2,0]
输出: 3

示例 2:
输入: [3,4,-1,1]
输出: 2

示例 3:
输入: [7,8,9,11,12]
输出: 1
```
说明:
你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间。

### 思路

题目的描述一看有点不好理解，其实是把它们排序后，[-1,1,2,4,4,5,6]这里面缺的第一个正整数是3，0不算正整数

1. 对数组排序
2. 过滤小于等于0的部分
3. 从1开始比较，注意过滤重复的元素

```CPP
class Solution {
public:
    int firstMissingPositive(vector<int>& nums) {
        sort(nums.begin(),nums.end());
        int len = nums.size();
        if(len == 0)return 1;
        int i = 0;
        while(nums[i] <= 0 && i < len)i++;
        if(i == len)return 1;
        
        int tmp = 1;
        while(i<len){
            if(nums[i] != tmp)return tmp;
            while(len>i+1 && nums[i] == nums[i+1])i++;//去重
            i++;
            tmp++;
        }
        return tmp;
    }
};
```