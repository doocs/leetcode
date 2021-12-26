# [2121. 相同元素的间隔之和](https://leetcode-cn.com/problems/intervals-between-identical-elements)

[English Version](/solution/2100-2199/2121.Intervals%20Between%20Identical%20Elements/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始、由 <code>n</code> 个整数组成的数组 <code>arr</code> 。</p>

<p><code>arr</code> 中两个元素的 <strong>间隔</strong> 定义为它们下标之间的 <strong>绝对差</strong> 。更正式地，<code>arr[i]</code> 和 <code>arr[j]</code> 之间的间隔是 <code>|i - j|</code> 。</p>

<p>返回一个长度为 <code>n</code> 的数组&nbsp;<code>intervals</code> ，其中 <code>intervals[i]</code> 是<em> </em><code>arr[i]</code><em> </em>和<em> </em><code>arr</code><em> </em>中每个相同元素（与 <code>arr[i]</code> 的值相同）的 <strong>间隔之和</strong> <em>。</em></p>

<p><strong>注意：</strong><code>|x|</code> 是 <code>x</code> 的绝对值。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>arr = [2,1,3,1,2,3,3]
<strong>输出：</strong>[4,2,7,2,4,4,5]
<strong>解释：</strong>
- 下标 0 ：另一个 2 在下标 4 ，|0 - 4| = 4
- 下标 1 ：另一个 1 在下标 3 ，|1 - 3| = 2
- 下标 2 ：另两个 3 在下标 5 和 6 ，|2 - 5| + |2 - 6| = 7
- 下标 3 ：另一个 1 在下标 1 ，|3 - 1| = 2
- 下标 4 ：另一个 2 在下标 0 ，|4 - 0| = 4
- 下标 5 ：另两个 3 在下标 2 和 6 ，|5 - 2| + |5 - 6| = 4
- 下标 6 ：另两个 3 在下标 2 和 5 ，|6 - 2| + |6 - 5| = 5
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>arr = [10,5,10,10]
<strong>输出：</strong>[5,0,3,4]
<strong>解释：</strong>
- 下标 0 ：另两个 10 在下标 2 和 3 ，|0 - 2| + |0 - 3| = 5
- 下标 1 ：只有这一个 5 在数组中，所以到相同元素的间隔之和是 0
- 下标 2 ：另两个 10 在下标 0 和 3 ，|2 - 0| + |2 - 3| = 3
- 下标 3 ：另两个 10 在下标 0 和 2 ，|3 - 0| + |3 - 2| = 4
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == arr.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= arr[i] &lt;= 10<sup>5</sup></code></li>
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

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts

```

### **...**

```

```

<!-- tabs:end -->
