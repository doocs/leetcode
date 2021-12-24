# [1541. Minimum Insertions to Balance a Parentheses String](https://leetcode.com/problems/minimum-insertions-to-balance-a-parentheses-string)

[中文文档](/solution/1500-1599/1541.Minimum%20Insertions%20to%20Balance%20a%20Parentheses%20String/README.md)

## Description

<p>Given a parentheses string <code>s</code> containing only the characters <code>&#39;(&#39;</code> and <code>&#39;)&#39;</code>. A parentheses string is <strong>balanced</strong> if:</p>

<ul>
	<li>Any left parenthesis&nbsp;<code>&#39;(&#39;</code>&nbsp;must have a corresponding two consecutive right parenthesis&nbsp;<code>&#39;))&#39;</code>.</li>
	<li>Left parenthesis&nbsp;<code>&#39;(&#39;</code>&nbsp;must go before the corresponding two&nbsp;consecutive right parenthesis&nbsp;<code>&#39;))&#39;</code>.</li>
</ul>

<p>In other words, we treat <code>&#39;(&#39;</code> as openning parenthesis and <code>&#39;))&#39;</code> as closing parenthesis.</p>

<p>For example, <code>&quot;())&quot;</code>, <code>&quot;())(())))&quot;</code> and <code>&quot;(())())))&quot;</code> are&nbsp;balanced, <code>&quot;)()&quot;</code>, <code>&quot;()))&quot;</code> and <code>&quot;(()))&quot;</code> are not balanced.</p>

<p>You can insert the characters <code>&#39;(&#39;</code> and <code>&#39;)&#39;</code> at any position of the string to balance it if needed.</p>

<p>Return <em>the minimum number of insertions</em> needed to make <code>s</code> balanced.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;(()))&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> The second &#39;(&#39; has two matching &#39;))&#39;, but the first &#39;(&#39; has only &#39;)&#39; matching. We need to to add one more &#39;)&#39; at the end of the string to be &quot;(())))&quot; which is balanced.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;())&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> The string is already balanced.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;))())(&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> Add &#39;(&#39; to match the first &#39;))&#39;, Add &#39;))&#39; to match the last &#39;(&#39;.
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;((((((&quot;
<strong>Output:</strong> 12
<strong>Explanation:</strong> Add 12 &#39;)&#39; to balance the string.
</pre>

<p><strong>Example 5:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;)))))))&quot;
<strong>Output:</strong> 5
<strong>Explanation:</strong> Add 4 &#39;(&#39; at the beginning of the string and one &#39;)&#39; at the end. The string becomes &quot;(((())))))))&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10^5</code></li>
	<li><code>s</code> consists of <code>&#39;(&#39;</code> and <code>&#39;)&#39;</code> only.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **...**

```

```

<!-- tabs:end -->
