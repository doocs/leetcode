# [770. 基本计算器 IV](https://leetcode-cn.com/problems/basic-calculator-iv)

[English Version](/solution/0700-0799/0770.Basic%20Calculator%20IV/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个表达式&nbsp;<code>expression</code>&nbsp;如&nbsp;<code>expression = &quot;e + 8 - a + 5&quot;</code>&nbsp;和一个求值映射，如&nbsp;<code>{&quot;e&quot;: 1}</code>（给定的形式为&nbsp;<code>evalvars = [&quot;e&quot;]</code> 和&nbsp;<code>evalints = [1]</code>），返回表示简化表达式的标记列表，例如 <code>[&quot;-1*a&quot;,&quot;14&quot;]</code></p>

<ul>
	<li>表达式交替使用块和符号，每个块和符号之间有一个空格。</li>
	<li>块要么是括号中的表达式，要么是变量，要么是非负整数。</li>
	<li>块是括号中的表达式，变量或非负整数。</li>
	<li>变量是一个由小写字母组成的字符串（不包括数字）。请注意，变量可以是多个字母，并注意变量从不具有像&nbsp;<code>&quot;2x&quot;</code>&nbsp;或&nbsp;<code>&quot;-x&quot;</code>&nbsp;这样的前导系数或一元运算符&nbsp;。</li>
</ul>

<p>表达式按通常顺序进行求值：先是括号，然后求乘法，再计算加法和减法。例如，<code>expression = &quot;1 + 2 * 3&quot;</code>&nbsp;的答案是 <code>[&quot;7&quot;]</code>。</p>

<p>输出格式如下：</p>

<ul>
	<li>对于系数非零的每个自变量项，我们按字典排序的顺序将自变量写在一个项中。例如，我们永远不会写像 <code>&ldquo;b*a*c&rdquo;</code> 这样的项，只写 <code>&ldquo;a*b*c&rdquo;</code>。</li>
	<li>项的次数等于被乘的自变量的数目，并计算重复项。(例如，<code>&quot;a*a*b*c&quot;</code> 的次数为 4。)。我们先写出答案的最大次数项，用字典顺序打破关系，此时忽略词的前导系数。</li>
	<li>项的前导系数直接放在左边，用星号将它与变量分隔开(如果存在的话)。前导系数 1 仍然要打印出来。</li>
	<li>格式良好的一个示例答案是&nbsp;<code>[&quot;-2*a*a*a&quot;, &quot;3*a*a*b&quot;, &quot;3*b*b&quot;, &quot;4*a&quot;, &quot;5*c&quot;, &quot;-6&quot;]</code>&nbsp;。</li>
	<li>系数为 0 的项（包括常数项）不包括在内。例如，&ldquo;0&rdquo; 的表达式输出为 []。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>expression = &quot;e + 8 - a + 5&quot;, evalvars = [&quot;e&quot;], evalints = [1]
<strong>输出：</strong>[&quot;-1*a&quot;,&quot;14&quot;]

<strong>输入：</strong>expression = &quot;e - 8 + temperature - pressure&quot;,
evalvars = [&quot;e&quot;, &quot;temperature&quot;], evalints = [1, 12]
<strong>输出：</strong>[&quot;-1*pressure&quot;,&quot;5&quot;]

<strong>输入：</strong>expression = &quot;(e + 8) * (e - 8)&quot;, evalvars = [], evalints = []
<strong>输出：</strong>[&quot;1*e*e&quot;,&quot;-64&quot;]

<strong>输入：</strong>expression = &quot;7 - 7&quot;, evalvars = [], evalints = []
<strong>输出：</strong>[]

<strong>输入：</strong>expression = &quot;a * b * c + b * a * c * 4&quot;, evalvars = [], evalints = []
<strong>输出：</strong>[&quot;5*a*b*c&quot;]

<strong>输入：</strong>expression = &quot;((a - b) * (b - c) + (c - a)) * ((a - b) + (b - c) * (c - a))&quot;,
evalvars = [], evalints = []
<strong>输出：</strong>[&quot;-1*a*a*b*b&quot;,&quot;2*a*a*b*c&quot;,&quot;-1*a*a*c*c&quot;,&quot;1*a*b*b*b&quot;,&quot;-1*a*b*b*c&quot;,&quot;-1*a*b*c*c&quot;,&quot;1*a*c*c*c&quot;,&quot;-1*b*b*b*c&quot;,&quot;2*b*b*c*c&quot;,&quot;-1*b*c*c*c&quot;,&quot;2*a*a*b&quot;,&quot;-2*a*a*c&quot;,&quot;-2*a*b*b&quot;,&quot;2*a*c*c&quot;,&quot;1*b*b*b&quot;,&quot;-1*b*b*c&quot;,&quot;1*b*c*c&quot;,&quot;-1*c*c*c&quot;,&quot;-1*a*a&quot;,&quot;1*a*b&quot;,&quot;1*a*c&quot;,&quot;-1*b*c&quot;]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>expression</code> 的长度在&nbsp;<code>[1, 250]</code>&nbsp;范围内。</li>
	<li><code>evalvars, evalints</code> 在范围&nbsp;<code>[0, 100]</code>&nbsp;内，且长度相同。</li>
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
