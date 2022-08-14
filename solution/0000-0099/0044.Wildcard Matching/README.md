# [44. 通配符匹配](https://leetcode.cn/problems/wildcard-matching)

[English Version](/solution/0000-0099/0044.Wildcard%20Matching/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串&nbsp;(<code>s</code>) 和一个字符模式&nbsp;(<code>p</code>) ，实现一个支持&nbsp;<code>&#39;?&#39;</code>&nbsp;和&nbsp;<code>&#39;*&#39;</code>&nbsp;的通配符匹配。</p>

<pre>&#39;?&#39; 可以匹配任何单个字符。
&#39;*&#39; 可以匹配任意字符串（包括空字符串）。
</pre>

<p>两个字符串<strong>完全匹配</strong>才算匹配成功。</p>

<p><strong>说明:</strong></p>

<ul>
	<li><code>s</code>&nbsp;可能为空，且只包含从&nbsp;<code>a-z</code>&nbsp;的小写字母。</li>
	<li><code>p</code>&nbsp;可能为空，且只包含从&nbsp;<code>a-z</code>&nbsp;的小写字母，以及字符&nbsp;<code>?</code>&nbsp;和&nbsp;<code>*</code>。</li>
</ul>

<p><strong>示例&nbsp;1:</strong></p>

<pre><strong>输入:</strong>
s = &quot;aa&quot;
p = &quot;a&quot;
<strong>输出:</strong> false
<strong>解释:</strong> &quot;a&quot; 无法匹配 &quot;aa&quot; 整个字符串。</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre><strong>输入:</strong>
s = &quot;aa&quot;
p = &quot;*&quot;
<strong>输出:</strong> true
<strong>解释:</strong>&nbsp;&#39;*&#39; 可以匹配任意字符串。
</pre>

<p><strong>示例&nbsp;3:</strong></p>

<pre><strong>输入:</strong>
s = &quot;cb&quot;
p = &quot;?a&quot;
<strong>输出:</strong> false
<strong>解释:</strong>&nbsp;&#39;?&#39; 可以匹配 &#39;c&#39;, 但第二个 &#39;a&#39; 无法匹配 &#39;b&#39;。
</pre>

<p><strong>示例&nbsp;4:</strong></p>

<pre><strong>输入:</strong>
s = &quot;adceb&quot;
p = &quot;*a*b&quot;
<strong>输出:</strong> true
<strong>解释:</strong>&nbsp;第一个 &#39;*&#39; 可以匹配空字符串, 第二个 &#39;*&#39; 可以匹配字符串 &quot;dce&quot;.
</pre>

<p><strong>示例&nbsp;5:</strong></p>

<pre><strong>输入:</strong>
s = &quot;acdcb&quot;
p = &quot;a*c?b&quot;
<strong>输出:</strong> false</pre>

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
