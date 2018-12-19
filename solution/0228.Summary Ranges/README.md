## 汇总区间

### 问题描述

给定一个无重复元素的有序整数数组，返回数组区间范围的汇总。

```
示例 1:
输入: [0,1,2,4,5,7]
输出: ["0->2","4->5","7"]
解释: 0,1,2 可组成一个连续的区间; 4,5 可组成一个连续的区间。

示例 2:
输入: [0,2,3,4,6,8,9]
输出: ["0","2->4","6","8->9"]
解释: 2,3,4 可组成一个连续的区间; 8,9 可组成一个连续的区间。
```
------
### 思路

1. 设置count计数有多少个连续数字
2. 当不连续时，得出一个区间加入答案，更改下标`idx += (count+1)`,且count重新置0



```CPP
class Solution {
public:
    vector<string> summaryRanges(vector<int>& nums) {
        int len = nums.size();
        if(len == 0)return {};
        vector<string> ans;
        int count = 0;
        int idx = 0;
        while((idx + count) < len-1){
            if(nums[idx+count] == nums[idx+count+1]-1)count++;
            else{
                string str;
                if(count == 0){
                    str = to_string(nums[idx]);
                }
                else{
                    str = to_string(nums[idx])+"->"+to_string(nums[idx+count]);
                }
                ans.push_back(str);
                idx += (count+1);
                count = 0;
            }
        }
        
        //末尾处理
        string str;
        if(count > 0)
            str = to_string(nums[idx])+"->"+to_string(nums[idx+count]);
        else
            str = to_string(nums[idx]);
            
        ans.push_back(str);
        
        return ans;
        
    }
};

```