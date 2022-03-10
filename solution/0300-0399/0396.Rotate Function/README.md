# [396. 旋转函数](https://leetcode-cn.com/problems/rotate-function)

[English Version](/solution/0300-0399/0396.Rotate%20Function/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个长度为 <code>n</code> 的整数数组&nbsp;<code>nums</code>&nbsp;。</p>

<p>假设&nbsp;<code>arr<sub>k</sub></code>&nbsp;是数组&nbsp;<code>nums</code>&nbsp;顺时针旋转 <code>k</code> 个位置后的数组，我们定义&nbsp;<code>nums</code>&nbsp;的 <strong>旋转函数</strong>&nbsp;&nbsp;<code>F</code>&nbsp;为：</p>

<ul>
	<li><code>F(k) = 0 * arr<sub>k</sub>[0] + 1 * arr<sub>k</sub>[1] + ... + (n - 1) * arr<sub>k</sub>[n - 1]</code></li>
</ul>

<p>返回&nbsp;<em><code>F(0), F(1), ..., F(n-1)</code>中的最大值&nbsp;</em>。</p>

<p>生成的测试用例让答案符合&nbsp;<strong>32 位</strong> 整数。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = [4,3,2,6]
<strong>输出:</strong> 26
<strong>解释:</strong>
F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26
所以 F(0), F(1), F(2), F(3) 中的最大值是 F(3) = 26 。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = [100]
<strong>输出:</strong> 0
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>-100 &lt;= nums[i] &lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **...**

```

```

<!-- tabs:end -->
