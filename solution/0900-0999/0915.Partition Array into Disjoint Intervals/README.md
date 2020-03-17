## 分割数组

### 问题描述

给定一个数组 A，将其划分为两个不相交（没有公共元素）的连续子数组 left 和 right， 使得：

- left 中的每个元素都小于或等于 right 中的每个元素。
- left 和 right 都是非空的。
- left 要尽可能小。

在完成这样的分组后返回 left 的长度。可以保证存在这样的划分方法。

**示例1:**
```
输入：[5,0,3,8,6]
输出：3
解释：left = [5,0,3]，right = [8,6]
```
**示例2:**
```
输入：[1,1,1,0,6,12]
输出：4
解释：left = [1,1,1,0]，right = [6,12]
```
**提示:**
- 2 <= A.length <= 30000
- 0 <= A[i] <= 10^6
- 可以保证至少有一种方法能够按题目所描述的那样对 A 进行划分。

### 解法
从左到右遍历数组，维持三个标志，即left的结束位置`loc`、left中最大的值`vmx`、数组的第`0`位与访问位置之间最大的值`mx`。每次访问一个位置，若其值大于`mx`，则应将其值赋予`mx`，若其值小于`vmx`，则应将其位置赋予`loc`、将`mx`赋予`vmx`。
```python
class Solution:
    def partitionDisjoint(self, A):
        loc = 0
        vmx = A[0]
        mx = A[0]
        for i, el in enumerate(A):
            if el > mx:
                mx = el
            if el < vmx:
                loc = i
                vmx = mx
        return loc + 1
```
