# [770. 基本计算器 IV](https://leetcode.cn/problems/basic-calculator-iv)

[English Version](/solution/0700-0799/0770.Basic%20Calculator%20IV/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个表达式如&nbsp;<code>expression = "e + 8 - a + 5"</code>&nbsp;和一个求值映射，如&nbsp;<code>{"e": 1}</code>（给定的形式为&nbsp;<code>evalvars = ["e"]</code> 和&nbsp;<code>evalints = [1]</code>），返回表示简化表达式的标记列表，例如 <code>["-1*a","14"]</code></p>

<ul>
	<li>表达式交替使用块和符号，每个块和符号之间有一个空格。</li>
	<li>块要么是括号中的表达式，要么是变量，要么是非负整数。</li>
	<li>变量是一个由小写字母组成的字符串（不包括数字）。请注意，变量可以是多个字母，并注意变量从不具有像&nbsp;<code>"2x"</code>&nbsp;或&nbsp;<code>"-x"</code>&nbsp;这样的前导系数或一元运算符&nbsp;。</li>
</ul>

<p>表达式按通常顺序进行求值：先是括号，然后求乘法，再计算加法和减法。</p>

<ul>
	<li>例如，<code>expression = "1 + 2 * 3"</code>&nbsp;的答案是 <code>["7"]</code>。</li>
</ul>

<p>输出格式如下：</p>

<ul>
	<li>对于系数非零的每个自变量项，我们按字典排序的顺序将自变量写在一个项中。
	<ul>
		<li>例如，我们永远不会写像 <code>“b*a*c”</code> 这样的项，只写 <code>“a*b*c”</code>。</li>
	</ul>
	</li>
	<li>项的次数等于被乘的自变量的数目，并计算重复项。我们先写出答案的最大次数项，用字典顺序打破关系，此时忽略词的前导系数。
	<ul>
		<li>例如，<code>"a*a*b*c"</code> 的次数为 4。</li>
	</ul>
	</li>
	<li>项的前导系数直接放在左边，用星号将它与变量分隔开(如果存在的话)。前导系数 1 仍然要打印出来。</li>
	<li>格式良好的一个示例答案是&nbsp;<code>["-2*a*a*a", "3*a*a*b", "3*b*b", "4*a", "5*c", "-6"]</code>&nbsp;。</li>
	<li>系数为 <code>0</code> 的项（包括常数项）不包括在内。
	<ul>
		<li>例如，<code>“0”</code> 的表达式输出为&nbsp;<code>[]</code>&nbsp;。</li>
	</ul>
	</li>
</ul>

<p><strong>注意：</strong>你可以假设给定的表达式均有效。所有中间结果都在区间 <code>[-2<sup>31</sup>, 2<sup>31</sup> - 1]</code> 内。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>expression = "e + 8 - a + 5", evalvars = ["e"], evalints = [1]
<strong>输出：</strong>["-1*a","14"]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>expression = "e - 8 + temperature - pressure",
evalvars = ["e", "temperature"], evalints = [1, 12]
<strong>输出：</strong>["-1*pressure","5"]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>expression = "(e + 8) * (e - 8)", evalvars = [], evalints = []
<strong>输出：</strong>["1*e*e","-64"]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= expression.length &lt;= 250</code></li>
	<li><code>expression</code>&nbsp;由小写英文字母，数字&nbsp;<code>'+'</code>,&nbsp;<code>'-'</code>,&nbsp;<code>'*'</code>,&nbsp;<code>'('</code>,&nbsp;<code>')'</code>,&nbsp;<code>' '</code>&nbsp;组成</li>
	<li><code>expression</code>&nbsp;不包含任何前空格或后空格</li>
	<li><code>expression</code>&nbsp;中的所有符号都用一个空格隔开</li>
	<li><code>0 &lt;= evalvars.length &lt;= 100</code></li>
	<li><code>1 &lt;= evalvars[i].length &lt;= 20</code></li>
	<li><code>evalvars[i]</code>&nbsp;由小写英文字母组成</li>
	<li><code>evalints.length == evalvars.length</code></li>
	<li><code>-100 &lt;= evalints[i] &lt;= 100</code></li>
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
