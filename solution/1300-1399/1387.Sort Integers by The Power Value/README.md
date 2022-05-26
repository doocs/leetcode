# [1387. 将整数按权重排序](https://leetcode.cn/problems/sort-integers-by-the-power-value)

[English Version](/solution/1300-1399/1387.Sort%20Integers%20by%20The%20Power%20Value/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>我们将整数 <code>x</code>&nbsp;的 <strong>权重</strong> 定义为按照下述规则将 <code>x</code>&nbsp;变成 <code>1</code>&nbsp;所需要的步数：</p>

<ul>
	<li>如果&nbsp;<code>x</code>&nbsp;是偶数，那么&nbsp;<code>x = x / 2</code></li>
	<li>如果&nbsp;<code>x</code>&nbsp;是奇数，那么&nbsp;<code>x = 3 * x + 1</code></li>
</ul>

<p>比方说，x=3 的权重为 7 。因为 3 需要 7 步变成 1 （3 --&gt; 10 --&gt; 5 --&gt; 16 --&gt; 8 --&gt; 4 --&gt; 2 --&gt; 1）。</p>

<p>给你三个整数&nbsp;<code>lo</code>，&nbsp;<code>hi</code> 和&nbsp;<code>k</code>&nbsp;。你的任务是将区间&nbsp;<code>[lo, hi]</code>&nbsp;之间的整数按照它们的权重&nbsp;<strong>升序排序&nbsp;</strong>，如果大于等于 2 个整数有&nbsp;<strong>相同</strong>&nbsp;的权重，那么按照数字自身的数值&nbsp;<strong>升序排序</strong>&nbsp;。</p>

<p>请你返回区间&nbsp;<code>[lo, hi]</code>&nbsp;之间的整数按权重排序后的第&nbsp;<code>k</code>&nbsp;个数。</p>

<p>注意，题目保证对于任意整数&nbsp;<code>x</code>&nbsp;<code>（lo &lt;= x &lt;= hi）</code>&nbsp;，它变成&nbsp;<code>1</code> 所需要的步数是一个 32 位有符号整数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>lo = 12, hi = 15, k = 2
<strong>输出：</strong>13
<strong>解释：</strong>12 的权重为 9（12 --&gt; 6 --&gt; 3 --&gt; 10 --&gt; 5 --&gt; 16 --&gt; 8 --&gt; 4 --&gt; 2 --&gt; 1）
13 的权重为 9
14 的权重为 17
15 的权重为 17
区间内的数按权重排序以后的结果为 [12,13,14,15] 。对于 k = 2 ，答案是第二个整数也就是 13 。
注意，12 和 13 有相同的权重，所以我们按照它们本身升序排序。14 和 15 同理。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>lo = 7, hi = 11, k = 4
<strong>输出：</strong>7
<strong>解释：</strong>区间内整数 [7, 8, 9, 10, 11] 对应的权重为 [16, 3, 19, 6, 14] 。
按权重排序后得到的结果为 [8, 10, 11, 7, 9] 。
排序后数组中第 4 个数字为 7 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= lo &lt;= hi &lt;= 1000</code></li>
	<li><code>1 &lt;= k &lt;= hi - lo + 1</code></li>
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
