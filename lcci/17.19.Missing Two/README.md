# [面试题 17.19. 消失的两个数字](https://leetcode.cn/problems/missing-two-lcci)

[English Version](/lcci/17.19.Missing%20Two/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定一个数组，包含从 1 到 N 所有的整数，但其中缺了两个数字。你能在 O(N) 时间内只用 O(1) 的空间找到它们吗？</p>

<p>以任意顺序返回这两个数字均可。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> [1]
<strong>输出: </strong>[2,3]</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> [2,3]
<strong>输出: </strong>[1,4]</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>nums.length &lt;=&nbsp;30000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

异或运算。与[面试题 56 - I. 数组中数字出现的次数](/lcof/面试题56%20-%20I.%20数组中数字出现的次数/README.md) 类似。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def missingTwo(self, nums: List[int]) -> List[int]:
        res, n = 0, len(nums)
        for i in range(n):
            res ^= nums[i]
            res ^= i + 1
        res ^= n + 1
        res ^= n + 2
        pos = 0
        while (res & 1) == 0:
            pos += 1
            res >>= 1

        a = b = 0
        for num in nums:
            t = num >> pos
            if (t & 1) == 0:
                a ^= num
            else:
                b ^= num

        for i in range(1, n + 3):
            t = i >> pos
            if (t & 1) == 0:
                a ^= i
            else:
                b ^= i
        return [a, b]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] missingTwo(int[] nums) {
        int res = 0, n = nums.length;
        for (int i = 0; i < n; ++i) {
            res ^= nums[i];
            res ^= (i + 1);
        }
        res ^= (n + 1);
        res ^= (n + 2);

        int pos = 0;
        while ((res & 1) == 0) {
            pos += 1;
            res >>= 1;
        }

        int a = 0, b = 0;
        for (int num : nums) {
            int t = num >> pos;
            if ((t & 1) == 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }
        for (int i = 1; i <= n + 2; ++i) {
            int t = i >> pos;
            if ((t & 1) == 0) {
                a ^= i;
            } else {
                b ^= i;
            }
        }
        return new int[]{a, b};
    }
}
```

### **...**

```

```

<!-- tabs:end -->
