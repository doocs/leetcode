# [213. 打家劫舍 II](https://leetcode-cn.com/problems/house-robber-ii)

[English Version](/solution/0200-0299/0213.House%20Robber%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都<strong>围成一圈，</strong>这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，<strong>如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警</strong>。</p>

<p>给定一个代表每个房屋存放金额的非负整数数组，计算你<strong>在不触动警报装置的情况下，</strong>能够偷窃到的最高金额。</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre><strong>输入:</strong> [2,3,2]
<strong>输出:</strong> 3
<strong>解释:</strong> 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> [1,2,3,1]
<strong>输出:</strong> 4
<strong>解释:</strong> 你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
&nbsp;    偷窃到的最高金额 = 1 + 3 = 4 。</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

环状排列意味着第一个房屋和最后一个房屋中最多只能选择一个偷窃，因此可以把此环状排列房间问题约化为两个单排排列房屋子问题。

- 不偷第一个房屋（那么最后一个房屋能偷），即求：`_rob(nums[1:])`
- 偷第一个房屋（那么最后一个房屋不能偷），即求：`_rob(nums[:-1])`

即 `res = max(_rob(nums[1:]), _rob(nums[:-1]))`

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def rob(self, nums: List[int]) -> int:
        def _rob(nums):
            n = len(nums)
            if n == 0:
                return 0
            if n == 1:
                return nums[0]
            pre, cur = nums[0], max(nums[0], nums[1])
            for i in range(2, n):
                t = max(pre + nums[i], cur)
                pre, cur = cur, t
            return cur

        n = len(nums)
        if n == 1:
            return nums[0]
        return max(_rob(nums[1:]), _rob(nums[:-1]))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int sub1 = robInternal(Arrays.copyOfRange(nums, 0, n - 1));
        int sub2 = robInternal(Arrays.copyOfRange(nums, 1, n));
        return Math.max(sub1, sub2);
    }

    private int robInternal(int[] nums) {
        int n;
        if ((n = nums.length) == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[0];
        }
        int pre = nums[0];
        int cur = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; ++i) {
            int t = Math.max(pre + nums[i], cur);
            pre = cur;
            cur = t;
        }
        return cur;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
