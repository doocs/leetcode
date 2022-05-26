# [1354. 多次求和构造目标数组](https://leetcode.cn/problems/construct-target-array-with-multiple-sums)

[English Version](/solution/1300-1399/1354.Construct%20Target%20Array%20With%20Multiple%20Sums/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>target</code> 。一开始，你有一个数组&nbsp;<code>A</code> ，它的所有元素均为 1 ，你可以执行以下操作：</p>

<ul>
	<li>令&nbsp;<code>x</code>&nbsp;为你数组里所有元素的和</li>
	<li>选择满足&nbsp;<code>0 &lt;= i &lt; target.size</code>&nbsp;的任意下标&nbsp;<code>i</code>&nbsp;，并让&nbsp;<code>A</code>&nbsp;数组里下标为&nbsp;<code>i</code>&nbsp;处的值为&nbsp;<code>x</code>&nbsp;。</li>
	<li>你可以重复该过程任意次</li>
</ul>

<p>如果能从&nbsp;<code>A</code>&nbsp;开始构造出目标数组&nbsp;<code>target</code>&nbsp;，请你返回 True ，否则返回 False 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>target = [9,3,5]
<strong>输出：</strong>true
<strong>解释：</strong>从 [1, 1, 1] 开始
[1, 1, 1], 和为 3 ，选择下标 1
[1, 3, 1], 和为 5， 选择下标 2
[1, 3, 5], 和为 9， 选择下标 0
[9, 3, 5] 完成
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>target = [1,1,1,2]
<strong>输出：</strong>false
<strong>解释：</strong>不可能从 [1,1,1,1] 出发构造目标数组。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>target = [8,5]
<strong>输出：</strong>true
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>N == target.length</code></li>
	<li><code>1 &lt;= target.length&nbsp;&lt;= 5 * 10^4</code></li>
	<li><code>1 &lt;= target[i] &lt;= 10^9</code></li>
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
