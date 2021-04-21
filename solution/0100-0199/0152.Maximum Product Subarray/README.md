# [152. 乘积最大子数组](https://leetcode-cn.com/problems/maximum-product-subarray)

[English Version](/solution/0100-0199/0152.Maximum%20Product%20Subarray/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code>&nbsp;，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> [2,3,-2,4]
<strong>输出:</strong> <code>6</code>
<strong>解释:</strong>&nbsp;子数组 [2,3] 有最大乘积 6。
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> [-2,0,-1]
<strong>输出:</strong> 0
<strong>解释:</strong>&nbsp;结果不能为 2, 因为 [-2,-1] 不是子数组。</pre>


## 解法

<!-- 这里可写通用的实现逻辑 -->

考虑当前位置 i：

- 如果是一个负数的话，那么我们希望以它前一个位置结尾的某个段的积也是个负数，这样可以负负得正，并且我们希望这个积尽可能「负得更多」，即尽可能小。
- 如果是一个正数的话，我们更希望以它前一个位置结尾的某个段的积也是个正数，并且希望它尽可能地大。

因此，分别维护 fmax 和 fmin。

- `fmax(i) = max(nums[i], fmax(i - 1) * nums[i], fmin(i - 1) * nums[i])`
- `fmin(i) = min(nums[i], fmax(i - 1) * nums[i], fmin(i - 1) * nums[i])`
- `res = max(fmax(i)), i∈[0, n)`

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxProduct(self, nums: List[int]) -> int:
        maxf = minf = nums[0]
        res, n = nums[0], len(nums)
        for i in range(1, n):
            p, q = maxf, minf
            maxf = max(nums[i], p * nums[i], q * nums[i])
            minf = min(nums[i], p * nums[i], q * nums[i])
            res = max(res, maxf)
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxProduct(int[] nums) {
        int maxf = nums[0], minf = nums[0];
        int res = nums[0], n = nums.length;
        for (int i = 1; i < n; ++i) {
            int p = maxf, q = minf;
            maxf = Math.max(nums[i], Math.max(p * nums[i], q * nums[i]));
            minf = Math.min(nums[i], Math.min(p * nums[i], q * nums[i]));
            res = Math.max(res, maxf);
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
