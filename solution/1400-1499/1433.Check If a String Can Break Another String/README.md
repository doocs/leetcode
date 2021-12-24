# [1433. 检查一个字符串是否可以打破另一个字符串](https://leetcode-cn.com/problems/check-if-a-string-can-break-another-string)

[English Version](/solution/1400-1499/1433.Check%20If%20a%20String%20Can%20Break%20Another%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个字符串&nbsp;<code>s1</code>&nbsp;和&nbsp;<code>s2</code>&nbsp;，它们长度相等，请你检查是否存在一个&nbsp;<code>s1</code>&nbsp; 的排列可以打破 <code>s2</code>&nbsp;的一个排列，或者是否存在一个&nbsp;<code>s2</code>&nbsp;的排列可以打破 <code>s1</code> 的一个排列。</p>

<p>字符串&nbsp;<code>x</code>&nbsp;可以打破字符串&nbsp;<code>y</code>&nbsp;（两者长度都为&nbsp;<code>n</code>&nbsp;）需满足对于所有&nbsp;<code>i</code>（在&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n - 1</code>&nbsp;之间）都有&nbsp;<code>x[i] &gt;= y[i]</code>（字典序意义下的顺序）。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s1 = &quot;abc&quot;, s2 = &quot;xya&quot;
<strong>输出：</strong>true
<strong>解释：</strong>&quot;ayx&quot; 是 s2=&quot;xya&quot; 的一个排列，&quot;abc&quot; 是字符串 s1=&quot;abc&quot; 的一个排列，且 &quot;ayx&quot; 可以打破 &quot;abc&quot; 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s1 = &quot;abe&quot;, s2 = &quot;acd&quot;
<strong>输出：</strong>false 
<strong>解释：</strong>s1=&quot;abe&quot; 的所有排列包括：&quot;abe&quot;，&quot;aeb&quot;，&quot;bae&quot;，&quot;bea&quot;，&quot;eab&quot; 和 &quot;eba&quot; ，s2=&quot;acd&quot; 的所有排列包括：&quot;acd&quot;，&quot;adc&quot;，&quot;cad&quot;，&quot;cda&quot;，&quot;dac&quot; 和 &quot;dca&quot;。然而没有任何 s1 的排列可以打破 s2 的排列。也没有 s2 的排列能打破 s1 的排列。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>s1 = &quot;leetcodee&quot;, s2 = &quot;interview&quot;
<strong>输出：</strong>true
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>s1.length == n</code></li>
	<li><code>s2.length == n</code></li>
	<li><code>1 &lt;= n &lt;= 10^5</code></li>
	<li>所有字符串都只包含小写英文字母。</li>
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
