# [1896. 反转表达式值的最少操作次数](https://leetcode.cn/problems/minimum-cost-to-change-the-final-value-of-expression)

[English Version](/solution/1800-1899/1896.Minimum%20Cost%20to%20Change%20the%20Final%20Value%20of%20Expression/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <strong>有效的</strong> 布尔表达式，用字符串 <code>expression</code> 表示。这个字符串包含字符 <code>'1'</code>，<code>'0'</code>，<code>'&amp;'</code>（按位 <strong>与</strong> 运算），<code>'|'</code>（按位 <strong>或</strong> 运算），<code>'('</code> 和 <code>')'</code> 。</p>

<ul>
	<li>比方说，<code>"()1|1"</code> 和 <code>"(1)&amp;()"</code> <strong>不是有效</strong> 布尔表达式。而 <code>"1"</code>， <code>"(((1))|(0))"</code> 和 <code>"1|(0&amp;(1))"</code> 是 <strong>有效</strong> 布尔表达式。</li>
</ul>

<p>你的目标是将布尔表达式的 <strong>值</strong> <strong>反转 </strong>（也就是将 <code>0</code> 变为 <code>1</code> ，或者将 <code>1</code> 变为 <code>0</code>），请你返回达成目标需要的 <strong>最少操作</strong> 次数。</p>

<ul>
	<li>比方说，如果表达式 <code>expression = "1|1|(0&amp;0)&amp;1"</code> ，它的 <strong>值</strong> 为 <code>1|1|(0&amp;0)&amp;1 = 1|1|0&amp;1 = 1|0&amp;1 = 1&amp;1 = 1</code> 。我们想要执行操作将 <strong>新的</strong> 表达式的值变成 <code>0</code> 。</li>
</ul>

<p>可执行的 <strong>操作</strong> 如下：</p>

<ul>
	<li>将一个 <code>'1'</code> 变成一个 <code>'0'</code> 。</li>
	<li>将一个 <code>'0'</code> 变成一个 <code>'1'</code> 。</li>
	<li>将一个 <code>'&amp;'</code> 变成一个 <code>'|'</code> 。</li>
	<li>将一个 <code>'|'</code> 变成一个 <code>'&amp;'</code> 。</li>
</ul>

<p><strong>注意：</strong><code>'&amp;'</code> 的 <strong>运算优先级</strong> 与 <code>'|'</code> <strong>相同</strong> 。计算表达式时，括号优先级 <strong>最高</strong> ，然后按照 <strong>从左到右</strong> 的顺序运算。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>expression = "1&amp;(0|1)"
<b>输出：</b>1
<b>解释：</b>我们可以将 "1&amp;(0<strong>|</strong>1)" 变成 "1&amp;(0<strong>&amp;</strong>1)" ，执行的操作为将一个 '|' 变成一个 '&amp;' ，执行了 1 次操作。
新表达式的值为 0 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>expression = "(0&amp;0)&amp;(0&amp;0&amp;0)"
<b>输出：</b>3
<b>解释：</b>我们可以将 "(0<strong>&amp;0</strong>)<strong>&amp;</strong>(0&amp;0&amp;0)" 变成 "(0<strong>|1</strong>)<strong>|</strong>(0&amp;0&amp;0)" ，执行了 3 次操作。
新表达式的值为 1 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>expression = "(0|(1|0&amp;1))"
<b>输出：</b>1
<b>解释：</b>我们可以将 "(0|(<strong>1</strong>|0&amp;1))" 变成 "(0|(<strong>0</strong>|0&amp;1))" ，执行了 1 次操作。
新表达式的值为 0 。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= expression.length &lt;= 10<sup>5</sup></code></li>
	<li><code>expression</code> 只包含 <code>'1'</code>，<code>'0'</code>，<code>'&amp;'</code>，<code>'|'</code>，<code>'('</code> 和 <code>')'</code></li>
	<li>所有括号都有与之匹配的对应括号。</li>
	<li>不会有空的括号（也就是说 <code>"()"</code> 不是 <code>expression</code> 的子字符串）。</li>
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
