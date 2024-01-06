# [10034. 统计强大整数的数目](https://leetcode.cn/problems/count-the-number-of-powerful-integers)

[English Version](/solution/10000-10099/10034.Count%20the%20Number%20of%20Powerful%20Integers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你三个整数&nbsp;<code>start</code>&nbsp;，<code>finish</code>&nbsp;和&nbsp;<code>limit</code>&nbsp;。同时给你一个下标从&nbsp;<strong>0</strong>&nbsp;开始的字符串&nbsp;<code>s</code>&nbsp;，表示一个 <strong>正</strong>&nbsp;整数。</p>

<p>如果一个 <strong>正</strong>&nbsp;整数&nbsp;<code>x</code> 末尾部分是&nbsp;<code>s</code>&nbsp;（换句话说，<code>s</code>&nbsp;是 <code>x</code>&nbsp;的 <strong>后缀</strong>），且 <code>x</code>&nbsp;中的每个数位至多是 <code>limit</code>&nbsp;，那么我们称 <code>x</code>&nbsp;是 <strong>强大的</strong>&nbsp;。</p>

<p>请你返回区间&nbsp;<code>[start..finish]</code>&nbsp;内强大整数的&nbsp;<strong>总数目</strong>&nbsp;。</p>

<p>如果一个字符串 <code>x</code>&nbsp;是 <code>y</code>&nbsp;中某个下标开始（<strong>包括</strong>&nbsp;<code>0</code>&nbsp;），到下标为&nbsp;<code>y.length - 1</code>&nbsp;结束的子字符串，那么我们称&nbsp;<code>x</code>&nbsp;是&nbsp;<code>y</code>&nbsp;的一个后缀。比方说，<code>25</code>&nbsp;是&nbsp;<code>5125</code>&nbsp;的一个后缀，但不是&nbsp;<code>512</code>&nbsp;的后缀。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>start = 1, finish = 6000, limit = 4, s = "124"
<b>输出：</b>5
<b>解释：</b>区间 [1..6000] 内的强大数字为 124 ，1124 ，2124 ，3124 和 4124 。这些整数的各个数位都 &lt;= 4 且 "124" 是它们的后缀。注意 5124 不是强大整数，因为第一个数位 5 大于 4 。
这个区间内总共只有这 5 个强大整数。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>start = 15, finish = 215, limit = 6, s = "10"
<b>输出：</b>2
<b>解释：</b>区间 [15..215] 内的强大整数为 110 和 210 。这些整数的各个数位都 &lt;= 6 且 "10" 是它们的后缀。
这个区间总共只有这 2 个强大整数。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>start = 1000, finish = 2000, limit = 4, s = "3000"
<b>输出：</b>0
<b>解释：</b>区间 [1000..2000] 内的整数都小于 3000 ，所以 "3000" 不可能是这个区间内任何整数的后缀。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= start &lt;= finish &lt;= 10<sup>15</sup></code></li>
	<li><code>1 &lt;= limit &lt;= 9</code></li>
	<li><code>1 &lt;= s.length &lt;= floor(log<sub>10</sub>(finish)) + 1</code></li>
	<li><code>s</code>&nbsp;数位中每个数字都小于等于&nbsp;<code>limit</code>&nbsp;。</li>
	<li><code>s</code>&nbsp;不包含任何前导 0 。</li>
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

### **C++**

```cpp

```

### **Go**

```go

```

### **...**

```

```

<!-- tabs:end -->
