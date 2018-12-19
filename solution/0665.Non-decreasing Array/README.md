## 非递减数列

### 问题描述

给定一个长度为 `n` 的整数数组，你的任务是判断在最多改变 `1` 个元素的情况下，该数组能否变成一个非递减数列。

我们是这样定义一个非递减数列的： 对于数组中所有的 `i(1 <= i < n)`，满足 `array[i] <= array[i + 1]`。

**示例1:**
```
输入: [4,2,3]
输出: True
解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
```
**示例2:**
```
输入: [4,2,1]
输出: False
解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
```
**提示:**
-  `n` 的范围为 `[1, 10,000]`。

### 解法

若数组中不存在下降的点，则其为非递减数列；若数组中存在两个下降的点，则一定不能通过改变`1`个元素来变成一个非递减数列；若数组中只存在一个下降的点，其位置为`x`，则其能通过改变`1`个元素来变成非递减数列需要满足下列任一要求：
- `x`为最右的元素；
- `x`为第二个元素；
- `nums[x + 1] >= nums[x - 1]`；
- `nums[i - 2] < nums[i]`。

```python
class Solution:
    def checkPossibility(self, nums):
        if len(nums) < 2:
            return True
        count = 0
        for i in range(1, len(nums)):
            if nums[i] < nums[i - 1]:
                if count == 1:
                    return False
                if not(i + 1 == len(nums) or nums[i + 1] >= nums[i - 1] or i - 2 < 0 or nums[i - 2] < nums[i]):
                    return False
                else:
                    count = 1
        return True

```
