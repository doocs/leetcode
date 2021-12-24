# [972. 相等的有理数](https://leetcode-cn.com/problems/equal-rational-numbers)

[English Version](/solution/0900-0999/0972.Equal%20Rational%20Numbers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个字符串 <code>S</code> 和 <code>T</code>，每个字符串代表一个非负有理数，只有当它们表示相同的数字时才返回 <strong>true</strong>；否则，返回 <strong>false</strong>。字符串中可以使用括号来表示有理数的重复部分。</p>

<p>通常，有理数最多可以用三个部分来表示：<em>整数部分</em>&nbsp;<code>&lt;IntegerPart&gt;</code>、<em>小数非重复部分</em>&nbsp;<code>&lt;NonRepeatingPart&gt;</code>&nbsp;和<em>小数重复部分</em>&nbsp;<code>&lt;(&gt;&lt;RepeatingPart&gt;&lt;)&gt;</code>。数字可以用以下三种方法之一来表示：</p>

<ul>
	<li><code>&lt;IntegerPart&gt;</code>（例：0，12，123）</li>
	<li><code>&lt;IntegerPart&gt;&lt;.&gt;&lt;NonRepeatingPart&gt;</code> （例：0.5，2.12，2.0001）</li>
	<li><code>&lt;IntegerPart&gt;&lt;.&gt;&lt;NonRepeatingPart&gt;&lt;(&gt;&lt;RepeatingPart&gt;&lt;)&gt;</code>（例：0.1(6)，0.9(9)，0.00(1212)）</li>
</ul>

<p>十进制展开的重复部分通常在一对圆括号内表示。例如：</p>

<p>1 / 6 = 0.16666666... = 0.1(6) = 0.1666(6) = 0.166(66)</p>

<p>0.1(6) 或&nbsp;0.1666(6) 或&nbsp;0.166(66) 都是&nbsp;1 / 6 的正确表示形式。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>S = &quot;0.(52)&quot;, T = &quot;0.5(25)&quot;
<strong>输出：</strong>true
<strong>解释：</strong>因为 &quot;0.(52)&quot; 代表 0.52525252...，而 &quot;0.5(25)&quot; 代表 0.52525252525.....，则这两个字符串表示相同的数字。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>S = &quot;0.1666(6)&quot;, T = &quot;0.166(66)&quot;
<strong>输出：</strong>true
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>S = &quot;0.9(9)&quot;, T = &quot;1.&quot;
<strong>输出：</strong>true
<strong>解释：
</strong>&quot;0.9(9)&quot; 代表 0.999999999... 永远重复，等于 1 。[<a href="https://baike.baidu.com/item/0.999…/5615429?fr=aladdin" target="_blank">有关说明，请参阅此链接</a>]
&quot;1.&quot; 表示数字 1，其格式正确：(IntegerPart) = &quot;1&quot; 且 (NonRepeatingPart) = &quot;&quot; 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li>每个部分仅由数字组成。</li>
	<li>整数部分&nbsp;<code>&lt;IntegerPart&gt;</code>&nbsp;不会以 2 个或更多的零开头。（对每个部分的数字没有其他限制）。</li>
	<li><code>1 &lt;= &lt;IntegerPart&gt;.length &lt;= 4 </code></li>
	<li><code>0 &lt;= &lt;NonRepeatingPart&gt;.length &lt;= 4 </code></li>
	<li><code>1 &lt;= &lt;RepeatingPart&gt;.length &lt;= 4 </code></li>
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
