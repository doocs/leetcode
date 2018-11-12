## 找到所有数组中消失的数字

### 问题描述

给定一个范围在  `1 ≤ a[i] ≤ n ( n = 数组大小 )` 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。

找到所有在 [1, n] 范围之间没有出现在数组中的数字。

您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。

**示例:**
```
输入:
[4,3,2,7,8,2,3,1]

输出:
[5,6]
```

### 解法

题目要求不使用额外空间，所以计数排序的方法不可取；

根据题目的条件，给定的`a[i]`是在`[1,n]`的范围内，所以可以利用这种关系来对数组进行处理，如`a[a[i]] = -a[a[i]`作反标记，最终若`a[i]>0`的话，则证明该下标`i`没有出现过，加入输出数组

```CPP
class Solution {
public:
    vector<int> findDisappearedNumbers(vector<int>& nums) {
        int len = nums.size();
        vector<int> ans;
        if(len == 0)return ans;
        
        int index;
        for(int i = 0;i<len;++i){
            index = abs(nums[i]) - 1;
            
            if(nums[index] > 0)
                nums[index] = -nums[index];   
        }

        
        for(int i = 0;i<len;++i){
            if(nums[i] > 0)
                ans.push_back(i+1);
        }
        
        return ans;
    }
};
```