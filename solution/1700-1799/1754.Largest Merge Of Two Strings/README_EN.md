# [1754. Largest Merge Of Two Strings](https://leetcode.com/problems/largest-merge-of-two-strings)

[中文文档](/solution/1700-1799/1754.Largest%20Merge%20Of%20Two%20Strings/README.md)

## Description

<p>You are given two strings <code>word1</code> and <code>word2</code>. You want to construct a string <code>merge</code> in the following way: while either <code>word1</code> or <code>word2</code> are non-empty, choose <strong>one</strong> of the following options:</p>

<ul>
	<li>If <code>word1</code> is non-empty, append the <strong>first</strong> character in <code>word1</code> to <code>merge</code> and delete it from <code>word1</code>.
    <ul>
    	<li>For example, if <code>word1 = &quot;abc&quot; </code>and <code>merge = &quot;dv&quot;</code>, then after choosing this operation, <code>word1 = &quot;bc&quot;</code> and <code>merge = &quot;dva&quot;</code>.</li>
    </ul>
    </li>
    <li>If <code>word2</code> is non-empty, append the <strong>first</strong> character in <code>word2</code> to <code>merge</code> and delete it from <code>word2</code>.
    <ul>
    	<li>For example, if <code>word2 = &quot;abc&quot; </code>and <code>merge = &quot;&quot;</code>, then after choosing this operation, <code>word2 = &quot;bc&quot;</code> and <code>merge = &quot;a&quot;</code>.</li>
    </ul>
    </li>
</ul>

<p>Return <em>the lexicographically <strong>largest</strong> </em><code>merge</code><em> you can construct</em>.</p>

<p>A string <code>a</code> is lexicographically larger than a string <code>b</code> (of the same length) if in the first position where <code>a</code> and <code>b</code> differ, <code>a</code> has a character strictly larger than the corresponding character in <code>b</code>. For example, <code>&quot;abcd&quot;</code> is lexicographically larger than <code>&quot;abcc&quot;</code> because the first position they differ is at the fourth character, and <code>d</code> is greater than <code>c</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> word1 = &quot;cabaa&quot;, word2 = &quot;bcaaa&quot;
<strong>Output:</strong> &quot;cbcabaaaaa&quot;
<strong>Explanation:</strong> One way to get the lexicographically largest merge is:
- Take from word1: merge = &quot;c&quot;, word1 = &quot;abaa&quot;, word2 = &quot;bcaaa&quot;
- Take from word2: merge = &quot;cb&quot;, word1 = &quot;abaa&quot;, word2 = &quot;caaa&quot;
- Take from word2: merge = &quot;cbc&quot;, word1 = &quot;abaa&quot;, word2 = &quot;aaa&quot;
- Take from word1: merge = &quot;cbca&quot;, word1 = &quot;baa&quot;, word2 = &quot;aaa&quot;
- Take from word1: merge = &quot;cbcab&quot;, word1 = &quot;aa&quot;, word2 = &quot;aaa&quot;
- Append the remaining 5 a&#39;s from word1 and word2 at the end of merge.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> word1 = &quot;abcabc&quot;, word2 = &quot;abdcaba&quot;
<strong>Output:</strong> &quot;abdcabcabcaba&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word1.length, word2.length &lt;= 3000</code></li>
	<li><code>word1</code> and <code>word2</code> consist only of lowercase English letters.</li>
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
