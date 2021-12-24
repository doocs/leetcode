# [548. 将数组分割成和相等的子数组](https://leetcode-cn.com/problems/split-array-with-equal-sum)

[English Version](/solution/0500-0599/0548.Split%20Array%20with%20Equal%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个有 n 个整数的数组，你需要找到满足以下条件的三元组 (i, j, k) ：</p>

<ol>
	<li>0 &lt; i, i + 1 &lt; j, j + 1 &lt; k &lt; n - 1</li>
	<li>子数组 (0, i - 1)，(i + 1, j - 1)，(j + 1, k - 1)，(k + 1, n - 1) 的和应该相等。</li>
</ol>

<p>这里我们定义子数组 (L, R) 表示原数组从索引为L的元素开始至索引为R的元素。</p>

<p>&nbsp;</p>

<p><strong>示例:</strong></p>

<pre><strong>输入:</strong> [1,2,1,2,1,2,1]
<strong>输出:</strong> True
<strong>解释:</strong>
i = 1, j = 3, k = 5. 
sum(0, i - 1) = sum(0, 0) = 1
sum(i + 1, j - 1) = sum(2, 2) = 1
sum(j + 1, k - 1) = sum(4, 4) = 1
sum(k + 1, n - 1) = sum(6, 6) = 1
</pre>

<p>&nbsp;</p>

<p><strong>注意:</strong></p>

<ol>
	<li>1 &lt;= n &lt;= 2000。</li>
	<li>给定数组中的元素会在 [-1,000,000, 1,000,000] 范围内。</li>
</ol>

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
