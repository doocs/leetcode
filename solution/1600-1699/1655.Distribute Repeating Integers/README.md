# [1655. 分配重复整数](https://leetcode.cn/problems/distribute-repeating-integers)

[English Version](/solution/1600-1699/1655.Distribute%20Repeating%20Integers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为&nbsp;<code>n</code>&nbsp;的整数数组&nbsp;<code>nums</code>&nbsp;，这个数组中至多有&nbsp;<code>50</code>&nbsp;个不同的值。同时你有 <code>m</code>&nbsp;个顾客的订单 <code>quantity</code>&nbsp;，其中，整数&nbsp;<code>quantity[i]</code>&nbsp;是第&nbsp;<code>i</code>&nbsp;位顾客订单的数目。请你判断是否能将 <code>nums</code>&nbsp;中的整数分配给这些顾客，且满足：</p>

<ul>
	<li>第&nbsp;<code>i</code>&nbsp;位顾客 <strong>恰好&nbsp;</strong>有&nbsp;<code>quantity[i]</code>&nbsp;个整数。</li>
	<li>第&nbsp;<code>i</code>&nbsp;位顾客拿到的整数都是 <strong>相同的</strong>&nbsp;。</li>
	<li>每位顾客都满足上述两个要求。</li>
</ul>

<p>如果你可以分配 <code>nums</code>&nbsp;中的整数满足上面的要求，那么请返回&nbsp;<code>true</code>&nbsp;，否则返回 <code>false</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,3,4], quantity = [2]
<b>输出：</b>false
<strong>解释：</strong>第 0 位顾客没办法得到两个相同的整数。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,3,3], quantity = [2]
<b>输出：</b>true
<b>解释：</b>第 0 位顾客得到 [3,3] 。整数 [1,2] 都没有被使用。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [1,1,2,2], quantity = [2,2]
<b>输出：</b>true
<b>解释：</b>第 0 位顾客得到 [1,1] ，第 1 位顾客得到 [2,2] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>m == quantity.length</code></li>
	<li><code>1 &lt;= m &lt;= 10</code></li>
	<li><code>1 &lt;= quantity[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>nums</code>&nbsp;中至多有&nbsp;<code>50</code>&nbsp;个不同的数字。</li>
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
