# [736. Lisp 语法解析](https://leetcode-cn.com/problems/parse-lisp-expression)

[English Version](/solution/0700-0799/0736.Parse%20Lisp%20Expression/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个类似 Lisp 语句的表达式 <code>expression</code>，求出其计算结果。</p>

<p>表达式语法如下所示:</p>

<ul>
	<li>表达式可以为整数，let 语法，add 语法，mult 语法，或赋值的变量。表达式的结果总是一个整数。</li>
	<li>(整数可以是正整数、负整数、0)</li>
	<li><strong>let</strong> 语法表示为&nbsp;<code>(let v1 e1 v2 e2 ... vn en expr)</code>,&nbsp;其中&nbsp;<code>let</code>语法总是以字符串&nbsp;<code>&quot;let&quot;</code>来表示，接下来会跟随一个或多个交替变量或表达式，也就是说，第一个变量&nbsp;<code>v1</code>被分配为表达式&nbsp;<code>e1</code>&nbsp;的值，第二个变量&nbsp;<code>v2</code>&nbsp;被分配为表达式&nbsp;<code>e2</code>&nbsp;的值，<strong>以此类推</strong>；最终 let 语法的值为&nbsp;<code>expr</code>表达式的值。</li>
	<li><strong>add </strong>语法表示为&nbsp;<code>(add e1 e2)</code>，其中&nbsp;<code>add</code>&nbsp;语法总是以字符串&nbsp;<code>&quot;add&quot;</code>来表示，该语法总是有两个表达式<code>e1</code>、<code>e2</code>, 该语法的最终结果是&nbsp;<code>e1</code> 表达式的值与&nbsp;<code>e2</code>&nbsp;表达式的值之<strong>和</strong>。</li>
	<li><strong>mult</strong> 语法表示为&nbsp;<code>(mult e1 e2)</code>&nbsp;，其中&nbsp;<code>mult</code>&nbsp;语法总是以字符串<code>&quot;mult&quot;</code>表示， 该语法总是有两个表达式 <code>e1</code>、<code>e2</code>，该语法的最终结果是&nbsp;<code>e1</code> 表达式的值与&nbsp;<code>e2</code>&nbsp;表达式的值之<strong>积</strong>。</li>
	<li>在该题目中，变量的命名以小写字符开始，之后跟随0个或多个小写字符或数字。为了方便，&quot;add&quot;，&quot;let&quot;，&quot;mult&quot;会被定义为&quot;关键字&quot;，不会在表达式的变量命名中出现。</li>
	<li>最后，要说一下作用域的概念。计算变量名所对应的表达式时，在计算上下文中，首先检查最内层作用域（按括号计），然后按顺序依次检查外部作用域。我们将保证每一个测试的表达式都是合法的。有关作用域的更多详细信息，请参阅示例。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入:</strong> (add 1 2)
<strong>输出:</strong> 3

<strong>输入:</strong> (mult 3 (add 2 3))
<strong>输出:</strong> 15

<strong>输入:</strong> (let x 2 (mult x 5))
<strong>输出:</strong> 10

<strong>输入:</strong> (let x 2 (mult x (let x 3 y 4 (add x y))))
<strong>输出:</strong> 14
<strong>解释:</strong> 
表达式 (add x y), 在获取 x 值时, 我们应当由最内层依次向外计算, 首先遇到了 x=3, 所以此处的 x 值是 3.


<strong>输入:</strong> (let x 3 x 2 x)
<strong>输出:</strong> 2
<strong>解释:</strong> let 语句中的赋值运算按顺序处理即可

<strong>输入:</strong> (let x 1 y 2 x (add x y) (add x y))
<strong>输出:</strong> 5
<strong>解释:</strong> 
第一个 (add x y) 计算结果是 3，并且将此值赋给了 x 。
第二个 (add x y) 计算结果就是 3+2 = 5 。

<strong>输入:</strong> (let x 2 (add (let x 3 (let x 4 x)) x))
<strong>输出:</strong> 6
<strong>解释:</strong> 
(let x 4 x) 中的 x 的作用域仅在()之内。所以最终做加法操作时，x 的值是 2 。

<strong>输入:</strong> (let a1 3 b2 (add a1 1) b2) 
<strong>输出: </strong>4
<strong>解释:</strong> 
变量命名时可以在第一个小写字母后跟随数字.
</pre>

<p>&nbsp;</p>

<p><strong>注意:</strong></p>

<ul>
	<li>我们给定的&nbsp;<code>expression</code>&nbsp;表达式都是格式化后的：表达式前后没有多余的空格，表达式的不同部分(关键字、变量、表达式)之间仅使用一个空格分割，并且在相邻括号之间也没有空格。我们给定的表达式均为合法的且最终结果为整数。</li>
	<li>我们给定的表达式长度最多为 2000&nbsp;(表达式也不会为空，因为那不是一个合法的表达式)。</li>
	<li>最终的结果和中间的计算结果都将是一个 32 位整数。</li>
</ul>

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
