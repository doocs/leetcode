# [439. 三元表达式解析器](https://leetcode.cn/problems/ternary-expression-parser)

[English Version](/solution/0400-0499/0439.Ternary%20Expression%20Parser/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个表示任意嵌套三元表达式的字符串&nbsp;<code>expression</code>&nbsp;，求值并返回其结果。</p>

<p>你可以总是假设给定的表达式是有效的，并且只包含数字，&nbsp;<code>'?'</code>&nbsp;，&nbsp;&nbsp;<code>':'</code>&nbsp;，&nbsp;&nbsp;<code>'T'</code>&nbsp;和 <code>'F'</code> ，其中 <code>'T'</code> 为真， <code>'F'</code> 为假。表达式中的所有数字都是 <strong>一位</strong> 数(即在 <strong>[0,9] </strong>范围内)。</p>

<p>条件表达式从右到左分组(大多数语言中都是这样)，表达式的结果总是为数字 <code>'T'</code> 或 <code>'F'</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong> expression = "T?2:3"
<strong>输出：</strong> "2"
<strong>解释：</strong> 如果条件为真，结果为 2；否则，结果为 3。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong> expression = "F?1:T?4:5"
<strong>输出：</strong> "4"
<strong>解释：</strong> 条件表达式自右向左结合。使用括号的话，相当于：
 "(F ? 1 : (T ? 4 : 5))" --&gt; "(F ? 1 : 4)" --&gt; "4"
or "(F ? 1 : (T ? 4 : 5))" --&gt; "(T ? 4 : 5)" --&gt; "4"
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong> expression = "T?T?F:5:3"
<strong>输出：</strong> "F"
<strong>解释：</strong> 条件表达式自右向左结合。使用括号的话，相当于：
"(T ? (T ? F : 5) : 3)" --&gt; "(T ? F : 3)" --&gt; "F"
"(T ? (T ? F : 5) : 3)" --&gt; "(T ? F : 5)" --&gt; "F"</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>5 &lt;= expression.length &lt;= 10<sup>4</sup></code></li>
	<li><code>expression</code>&nbsp;由数字,&nbsp;<code>'T'</code>,&nbsp;<code>'F'</code>,&nbsp;<code>'?'</code>&nbsp;和&nbsp;<code>':'</code>&nbsp;组成</li>
	<li><strong>保证&nbsp;</strong>了表达式是一个有效的三元表达式，并且每个数字都是 <strong>一位数</strong>&nbsp;</li>
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
