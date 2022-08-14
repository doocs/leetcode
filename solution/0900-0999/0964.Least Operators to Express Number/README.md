# [964. 表示数字的最少运算符](https://leetcode.cn/problems/least-operators-to-express-number)

[English Version](/solution/0900-0999/0964.Least%20Operators%20to%20Express%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个正整数 <code>x</code>，我们将会写出一个形如&nbsp;<code>x (op1) x (op2) x (op3) x ...</code>&nbsp;的表达式，其中每个运算符&nbsp;<code>op1</code>，<code>op2</code>，… 可以是加、减、乘、除（<code>+</code>，<code>-</code>，<code>*</code>，或是&nbsp;<code>/</code>）之一。例如，对于&nbsp;<code>x = 3</code>，我们可以写出表达式&nbsp;<code>3 * 3 / 3 + 3 - 3</code>，该式的值为 3 。</p>

<p>在写这样的表达式时，我们需要遵守下面的惯例：</p>

<ul>
	<li>除运算符（<code>/</code>）返回有理数。</li>
	<li>任何地方都没有括号。</li>
	<li>我们使用通常的操作顺序：乘法和除法发生在加法和减法之前。</li>
	<li>不允许使用一元否定运算符（<code>-</code>）。例如，“<code>x - x</code>” 是一个有效的表达式，因为它只使用减法，但是 “<code>-x + x</code>” 不是，因为它使用了否定运算符。&nbsp;</li>
</ul>

<p>我们希望编写一个能使表达式等于给定的目标值 <code>target</code> 且运算符最少的表达式。返回所用运算符的最少数量。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>x = 3, target = 19
<strong>输出：</strong>5
<strong>解释：</strong>3 * 3 + 3 * 3 + 3 / 3 。表达式包含 5 个运算符。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>x = 5, target = 501
<strong>输出：</strong>8
<strong>解释：</strong>5 * 5 * 5 * 5 - 5 * 5 * 5 + 5 / 5 。表达式包含 8 个运算符。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>x = 100, target = 100000000
<strong>输出：</strong>3
<strong>解释：</strong>100 * 100 * 100 * 100 。表达式包含 3 个运算符。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= x &lt;= 100</code></li>
	<li><code>1 &lt;= target &lt;= 2 * 10<sup>8</sup></code></li>
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
