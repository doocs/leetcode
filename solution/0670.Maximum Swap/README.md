## 最大交换

### 问题描述

给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。

**示例1:**
```
输入: 2736
输出: 7236
解释: 交换数字2和数字7。
```
**示例2:**
```
输入: 9973
输出: 9973
解释: 不需要交换。
```
**注意:**
- 给定数字的范围是 [0, 10<sup>8</sup>]

### 解法

当成字符串处理。若可以随意排列，可以得到的最大数（即为反字典顺序）val与原来的数字val<sub>0</sub>第一次不相同的位置即为需要交换的位置，同时可知需要交换的数字。再从个位数依次向左查找被交换的位置。
**示例:**
```
输入: 2736
最大数val：7632
交换位置：0
交换数字：2,7
被交换位置：1
输出: 7236
解释: 交换数字2和数字7。
```

```python
class Solution:
    def maximumSwap(self, num):
        # s为能得到的最大数
        s = ''.join(sorted(list(str(num)), reverse=True))
        nums = str(num)
        if s == nums:
            return num
        for i in range(len(s)):
            if s[i] != nums[i]:
                kai = i
                break
        for i in range(len(nums) - 1, -1, -1):
            if nums[i] == s[kai]:
                loc = i
                break
        return int(s[:kai + 1] + nums[kai + 1:loc] + nums[kai] + nums[loc + 1:])

```
