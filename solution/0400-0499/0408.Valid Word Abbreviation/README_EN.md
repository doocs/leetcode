# [408. Valid Word Abbreviation](https://leetcode.com/problems/valid-word-abbreviation)

[中文文档](/solution/0400-0499/0408.Valid%20Word%20Abbreviation/README.md)

## Description

<p>A string can be <strong>abbreviated</strong> by replacing any number of <strong>non-adjacent</strong>, <strong>non-empty</strong> substrings with their lengths. The lengths <strong>should not</strong> have leading zeros.</p>

<p>For example, a string such as <code>&quot;substitution&quot;</code> could be abbreviated as (but not limited to):</p>

<ul>
	<li><code>&quot;s10n&quot;</code> (<code>&quot;s <u>ubstitutio</u> n&quot;</code>)</li>
	<li><code>&quot;sub4u4&quot;</code> (<code>&quot;sub <u>stit</u> u <u>tion</u>&quot;</code>)</li>
	<li><code>&quot;12&quot;</code> (<code>&quot;<u>substitution</u>&quot;</code>)</li>
	<li><code>&quot;su3i1u2on&quot;</code> (<code>&quot;su <u>bst</u> i <u>t</u> u <u>ti</u> on&quot;</code>)</li>
	<li><code>&quot;substitution&quot;</code> (no substrings replaced)</li>
</ul>

<p>The following are <strong>not valid</strong> abbreviations:</p>

<ul>
	<li><code>&quot;s55n&quot;</code> (<code>&quot;s <u>ubsti</u> <u>tutio</u> n&quot;</code>, the replaced substrings are adjacent)</li>
	<li><code>&quot;s010n&quot;</code> (has leading zeros)</li>
	<li><code>&quot;s0ubstitution&quot;</code> (replaces an empty substring)</li>
</ul>

<p>Given a string <code>word</code> and an abbreviation <code>abbr</code>, return <em>whether the string <strong>matches</strong> the given abbreviation</em>.</p>

<p>A <strong>substring</strong> is a contiguous <strong>non-empty</strong> sequence of characters within a string.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;internationalization&quot;, abbr = &quot;i12iz4n&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> The word &quot;internationalization&quot; can be abbreviated as &quot;i12iz4n&quot; (&quot;i <u>nternational</u> iz <u>atio</u> n&quot;).
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;apple&quot;, abbr = &quot;a2e&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> The word &quot;apple&quot; cannot be abbreviated as &quot;a2e&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 20</code></li>
	<li><code>word</code> consists of only lowercase English letters.</li>
	<li><code>1 &lt;= abbr.length &lt;= 10</code></li>
	<li><code>abbr</code> consists of lowercase English letters and digits.</li>
	<li>All the integers in <code>abbr</code> will fit in a 32-bit integer.</li>
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
