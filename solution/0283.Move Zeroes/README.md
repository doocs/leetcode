## 移动0

### 问题描述

给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

```
示例:
输入: [0,1,0,3,12]
输出: [1,3,12,0,0]
```
说明:

1. 必须在原数组上操作，不能拷贝额外的数组。
2. 尽量减少操作次数。

### 思路

两种思路，分别是

1. 快慢指针，慢指针找0，快指针找慢指针之后的非0元素和慢指针交换，没有找到就直接结束
2. 也可以通过对非0元素遍历来实现（更好）

```CPP
class Solution {
public:
    void moveZeroes(vector<int>& nums) {
        int len = nums.size();
        if(len == 0)return;
       
        int slow = 0;
        int fast;
        
        while(slow < len){
            if(nums[slow] == 0){
                fast = slow+1;
                while(fast < len){
                    if(nums[fast] == 0)fast++;
                    else break;
                }
                
                if(fast == len)return;
                
                swap(nums[slow],nums[fast]);
            }
            slow++;
        }
        
    }
};
```

-----------------------
```CPP
class Solution {
public:
    void moveZeroes(vector<int>& nums) {
        int len = nums.size();
        if(len == 0)return;
       
        int idx = 0;
        for(int i = 0;i<len;i++){
            if(nums[i] != 0){
                nums[idx] = nums[i];
                idx++;
            }
        }
        
        for(int i = idx;i<len;i++){
            nums[i] = 0;
        }
    }
};
```