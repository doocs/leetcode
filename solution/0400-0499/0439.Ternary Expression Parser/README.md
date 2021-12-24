# [439. 三元表达式解析器](https://leetcode-cn.com/problems/ternary-expression-parser)

[English Version](/solution/0400-0499/0439.Ternary%20Expression%20Parser/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个以字符串表示的任意嵌套的三元表达式，计算表达式的值。你可以假定给定的表达式始终都是有效的并且只包含数字 <code>0-9</code>, <code>?</code>, <code>:</code>, <code>T</code> 和&nbsp;<code>F</code> (<code>T</code> 和&nbsp;<code>F</code>&nbsp;分别表示真和假）。</p>

<p><strong>注意：</strong></p>

<ol>
	<li>给定的字符串长度&nbsp;&le; 10000。</li>
	<li>所包含的数字都只有一位数。</li>
	<li>条件表达式从右至左结合（和大多数程序设计语言类似）。</li>
	<li>条件是&nbsp;<code>T</code>&nbsp;和&nbsp;<code>F</code>其一，即条件永远不会是数字。</li>
	<li>表达式的结果是数字&nbsp;<code>0-9</code>, <code>T</code> 或者&nbsp;<code>F</code>。</li>
</ol>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong> &quot;T?2:3&quot;

<strong>输出：</strong> &quot;2&quot;

<strong>解释：</strong> 如果条件为真，结果为 2；否则，结果为 3。
</pre>

<p>&nbsp;</p>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong> &quot;F?1:T?4:5&quot;

<strong>输出：</strong> &quot;4&quot;

<strong>解释：</strong> 条件表达式自右向左结合。使用括号的话，相当于：

             &quot;(F ? 1 : (T ? 4 : 5))&quot;                   &quot;(F ? 1 : (T ? 4 : 5))&quot;
          -&gt; &quot;(F ? 1 : 4)&quot;                 或者     -&gt; &quot;(T ? 4 : 5)&quot;
          -&gt; &quot;4&quot;                                    -&gt; &quot;4&quot;
</pre>

<p>&nbsp;</p>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong> &quot;T?T?F:5:3&quot;

<strong>输出：</strong> &quot;F&quot;

<strong>解释：</strong> 条件表达式自右向左结合。使用括号的话，相当于：

             &quot;(T ? (T ? F : 5) : 3)&quot;                   &quot;(T ? (T ? F : 5) : 3)&quot;
          -&gt; &quot;(T ? F : 3)&quot;                 或者       -&gt; &quot;(T ? F : 5)&quot;
          -&gt; &quot;F&quot;                                     -&gt; &quot;F&quot;
</pre>

<p>&nbsp;</p>

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
