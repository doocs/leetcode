# [1987. 不同的好子序列数目](https://leetcode.cn/problems/number-of-unique-good-subsequences)

[English Version](/solution/1900-1999/1987.Number%20of%20Unique%20Good%20Subsequences/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二进制字符串&nbsp;<code>binary</code>&nbsp;。&nbsp;<code>binary</code>&nbsp;的一个 <strong>子序列</strong>&nbsp;如果是 <strong>非空</strong>&nbsp;的且没有 <b>前导</b>&nbsp;<strong>0</strong>&nbsp;（除非数字是 <code>"0"</code>&nbsp;本身），那么它就是一个 <strong>好</strong>&nbsp;的子序列。</p>

<p>请你找到&nbsp;<code>binary</code>&nbsp;<strong>不同好子序列</strong>&nbsp;的数目。</p>

<ul>
	<li>比方说，如果&nbsp;<code>binary = "001"</code>&nbsp;，那么所有 <strong>好</strong>&nbsp;子序列为&nbsp;<code>["0", "0", "1"]</code>&nbsp;，所以 <b>不同</b>&nbsp;的好子序列为&nbsp;<code>"0"</code> 和&nbsp;<code>"1"</code>&nbsp;。 注意，子序列&nbsp;<code>"00"</code>&nbsp;，<code>"01"</code>&nbsp;和&nbsp;<code>"001"</code>&nbsp;不是好的，因为它们有前导 0 。</li>
</ul>

<p>请你返回&nbsp;<code>binary</code>&nbsp;中&nbsp;<strong>不同好子序列</strong>&nbsp;的数目。由于答案可能很大，请将它对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;<strong>取余</strong> 后返回。</p>

<p>一个 <strong>子序列</strong>&nbsp;指的是从原数组中删除若干个（可以一个也不删除）元素后，不改变剩余元素顺序得到的序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>binary = "001"
<b>输出：</b>2
<b>解释：</b>好的二进制子序列为 ["0", "0", "1"] 。
不同的好子序列为 "0" 和 "1" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>binary = "11"
<b>输出：</b>2
<b>解释：</b>好的二进制子序列为 ["1", "1", "11"] 。
不同的好子序列为 "1" 和 "11" 。</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>binary = "101"
<b>输出：</b>5
<b>解释：</b>好的二进制子序列为 ["1", "0", "1", "10", "11", "101"] 。
不同的好子序列为 "0" ，"1" ，"10" ，"11" 和 "101" 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= binary.length &lt;= 10<sup>5</sup></code></li>
	<li><code>binary</code>&nbsp;只含有&nbsp;<code>'0'</code>&nbsp;和&nbsp;<code>'1'</code> 。</li>
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
