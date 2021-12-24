# [1058. 最小化舍入误差以满足目标](https://leetcode-cn.com/problems/minimize-rounding-error-to-meet-target)

[English Version](/solution/1000-1099/1058.Minimize%20Rounding%20Error%20to%20Meet%20Target/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一系列价格&nbsp;<code>[p<sub>1</sub>,p<sub>2</sub>...,p<sub>n</sub>]</code>&nbsp;和一个目标&nbsp;<code>target</code>，将每个价格&nbsp;<code>p<sub>i</sub></code>&nbsp;舍入为&nbsp;<code>Round<sub>i</sub>(p<sub>i</sub>)</code>&nbsp;以使得舍入数组&nbsp;<code>[Round<sub>1</sub>(p<sub>1</sub>),Round<sub>2</sub>(p<sub>2</sub>)...,Round<sub>n</sub>(p<sub>n</sub>)]</code>&nbsp;之和达到给定的目标值&nbsp;<code>target</code>。每次舍入操作&nbsp;<code>Round<sub>i</sub>(p<sub>i</sub>)</code>&nbsp;可以是向下舍&nbsp;<code>Floor(p<sub>i</sub>)</code>&nbsp;也可以是向上入&nbsp;<code>Ceil(p<sub>i</sub>)</code>。</p>

<p>如果舍入数组之和无论如何都无法达到目标值&nbsp;<code>target</code>，就返回 <code>-1</code>。否则，以保留到小数点后三位的字符串格式返回最小的舍入误差，其定义为 &Sigma; |Round<sub>i</sub>(p<sub>i</sub>) - (p<sub>i</sub>)|（&nbsp;i 从 1 到 n ）。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>prices = [&quot;0.700&quot;,&quot;2.800&quot;,&quot;4.900&quot;], target = 8
<strong>输出：</strong>&quot;1.000&quot;
<strong>解释： </strong>
使用 Floor，Ceil 和 Ceil 操作得到 (0.7 - 0) + (3 - 2.8) + (5 - 4.9) = 0.7 + 0.2 + 0.1 = 1.0 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>prices = [&quot;1.500&quot;,&quot;2.500&quot;,&quot;3.500&quot;], target = 10
<strong>输出：</strong>&quot;-1&quot;
<strong>解释：</strong>
达到目标是不可能的。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= prices.length &lt;= 500</code></li>
	<li>表示价格的每个字符串&nbsp;<code>prices[i]</code>&nbsp;都代表一个介于 0 和 1000&nbsp;之间的实数，并且正好有 3 个小数位。</li>
	<li><code>target</code>&nbsp;介于&nbsp;0 和 1000000&nbsp;之间。</li>
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
