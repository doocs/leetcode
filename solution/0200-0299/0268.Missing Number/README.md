# [268. 缺失数字](https://leetcode-cn.com/problems/missing-number)

[English Version](/solution/0200-0299/0268.Missing%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定一个包含 <code>0, 1, 2, ..., n</code>&nbsp;中&nbsp;<em>n</em>&nbsp;个数的序列，找出 0 .. <em>n</em>&nbsp;中没有出现在序列中的那个数。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> [3,0,1]
<strong>输出:</strong> 2
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre><strong>输入:</strong> [9,6,4,2,3,5,7,0,1]
<strong>输出:</strong> 8
</pre>

<p><strong>说明:</strong><br>
你的算法应具有线性时间复杂度。你能否仅使用额外常数空间来实现?</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

异或求解。两个相同的数异或的结果为 0。

也可以用数学求解。求出 `[0..n]` 的和，减去数组中所有数的和，就得到了缺失的数字。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def missingNumber(self, nums: List[int]) -> int:
        res = len(nums)
        for i, v in enumerate(nums):
            res ^= (i ^ v)
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

- 异或

```java
class Solution {
    public int missingNumber(int[] nums) {
        int res = nums.length;
        for (int i = 0, n = res; i < n; ++i) {
            res ^= (i ^ nums[i]);
        }
        return res;
    }
}
```

- 数学

```java
class Solution {
    public int missingNumber(int[] nums) {
        int res = nums.length;
        for (int i = 0, n = res; i < n; ++i) {
            res += (i - nums[i]);
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
