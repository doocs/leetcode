# [330. 按要求补齐数组](https://leetcode.cn/problems/patching-array)

[English Version](/solution/0300-0399/0330.Patching%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个已排序的正整数数组 <code>nums</code>&nbsp;<em>，</em>和一个正整数&nbsp;<code>n</code><em> 。</em>从&nbsp;<code>[1, n]</code>&nbsp;区间内选取任意个数字补充到&nbsp;nums&nbsp;中，使得&nbsp;<code>[1, n]</code>&nbsp;区间内的任何数字都可以用&nbsp;nums&nbsp;中某几个数字的和来表示。</p>

<p>请返回 <em>满足上述要求的最少需要补充的数字个数</em>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre>
<strong>输入: </strong>nums = <code>[1,3]</code>, n = <code>6</code>
<strong>输出: </strong>1 
<strong>解释:</strong>
根据 nums&nbsp;里现有的组合&nbsp;<code>[1], [3], [1,3]</code>，可以得出&nbsp;<code>1, 3, 4</code>。
现在如果我们将&nbsp;<code>2</code>&nbsp;添加到&nbsp;nums 中，&nbsp;组合变为: <code>[1], [2], [3], [1,3], [2,3], [1,2,3]</code>。
其和可以表示数字&nbsp;<code>1, 2, 3, 4, 5, 6</code>，能够覆盖&nbsp;<code>[1, 6]</code>&nbsp;区间里所有的数。
所以我们最少需要添加一个数字。</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入: </strong>nums = <code>[1,5,10]</code>, n = <code>20</code>
<strong>输出:</strong> 2
<strong>解释: </strong>我们需要添加&nbsp;<code>[2,4]</code>。
</pre>

<p><strong>示例&nbsp;3:</strong></p>

<pre>
<strong>输入: </strong>nums = <code>[1,2,2]</code>, n = <code>5</code>
<strong>输出:</strong> 0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>nums</code>&nbsp;按 <strong>升序排列</strong></li>
	<li><code>1 &lt;= n &lt;= 2<sup>31</sup>&nbsp;- 1</code></li>
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
