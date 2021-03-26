# [628. 三个数的最大乘积](https://leetcode-cn.com/problems/maximum-product-of-three-numbers)

[English Version](/solution/0600-0699/0628.Maximum%20Product%20of%20Three%20Numbers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积。</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> [1,2,3]
<strong>输出:</strong> 6
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> [1,2,3,4]
<strong>输出:</strong> 24
</pre>

<p><strong>注意:</strong></p>

<ol>
	<li>给定的整型数组长度范围是[3,10<sup>4</sup>]，数组中所有的元素范围是[-1000, 1000]。</li>
	<li>输入的数组中任意三个数的乘积不会超出32位有符号整数的范围。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximumProduct(self, nums: List[int]) -> int:
        n = len(nums)
        nums.sort()
        # 全负 0 1 n-1
        # 全正 n-1 n-2 n-3
        # 有正有负 max([0 1 n-1], [n-1 n-2 n-3])
        return max(nums[0] * nums[1] * nums[n - 1], nums[n - 1] * nums[n - 2] * nums[n - 3])
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        // 全负 0 1 n-1
        // 全正 n-1 n-2 n-3
        // 有正有负 max([0 1 n-1], [n-1 n-2 n-3])
        return Math.max(nums[0] * nums[1] * nums[n - 1], nums[n - 1] * nums[n - 2] * nums[n - 3]);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
