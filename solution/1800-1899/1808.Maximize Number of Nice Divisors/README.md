# [1808. 好因子的最大数目](https://leetcode.cn/problems/maximize-number-of-nice-divisors)

[English Version](/solution/1800-1899/1808.Maximize%20Number%20of%20Nice%20Divisors/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个正整数 <code>primeFactors</code> 。你需要构造一个正整数 <code>n</code> ，它满足以下条件：</p>

<ul>
	<li><code>n</code> 质因数（质因数需要考虑重复的情况）的数目 <strong>不超过 </strong><code>primeFactors</code> 个。</li>
	<li><code>n</code> 好因子的数目最大化。如果 <code>n</code> 的一个因子可以被 <code>n</code> 的每一个质因数整除，我们称这个因子是 <strong>好因子</strong> 。比方说，如果 <code>n = 12</code> ，那么它的质因数为 <code>[2,2,3]</code> ，那么 <code>6</code> 和 <code>12</code> 是好因子，但 <code>3</code> 和 <code>4</code> 不是。</li>
</ul>

<p>请你返回 <code>n</code> 的好因子的数目。由于答案可能会很大，请返回答案对 <code>10<sup>9</sup> + 7</code> <b>取余</b> 的结果。</p>

<p>请注意，一个质数的定义是大于 <code>1</code> ，且不能被分解为两个小于该数的自然数相乘。一个数 <code>n</code> 的质因子是将 <code>n</code> 分解为若干个质因子，且它们的乘积为 <code>n</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>primeFactors = 5
<strong>输出：</strong>6
<b>解释：</b>200 是一个可行的 n 。
它有 5 个质因子：[2,2,2,5,5] ，且有 6 个好因子：[10,20,40,50,100,200] 。
不存在别的 n 有至多 5 个质因子，且同时有更多的好因子。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>primeFactors = 8
<b>输出：</b>18
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= primeFactors <= 10<sup>9</sup></code></li>
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
