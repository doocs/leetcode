# [411. Minimum Unique Word Abbreviation](https://leetcode.com/problems/minimum-unique-word-abbreviation)

[中文文档](/solution/0400-0499/0411.Minimum%20Unique%20Word%20Abbreviation/README.md)

## Description

<p>A string can be <strong>abbreviated</strong> by replacing any number of <strong>non-adjacent</strong> substrings with their lengths. For example, a string such as <code>&quot;substitution&quot;</code> could be abbreviated as (but not limited to):</p>

<ul>
	<li><code>&quot;s10n&quot;</code> (<code>&quot;s <u>ubstitutio</u> n&quot;</code>)</li>
	<li><code>&quot;sub4u4&quot;</code> (<code>&quot;sub <u>stit</u> u <u>tion</u>&quot;</code>)</li>
	<li><code>&quot;12&quot;</code> (<code>&quot;<u>substitution</u>&quot;</code>)</li>
	<li><code>&quot;su3i1u2on&quot;</code> (<code>&quot;su <u>bst</u> i <u>t</u> u <u>ti</u> on&quot;</code>)</li>
	<li><code>&quot;substitution&quot;</code> (no substrings replaced)</li>
</ul>

<p>Note that <code>&quot;s55n&quot;</code> (<code>&quot;s <u>ubsti</u> <u>tutio</u> n&quot;</code>) is not a valid abbreviation of <code>&quot;substitution&quot;</code> because the replaced substrings are adjacent.</p>

<p>The <strong>length</strong> of an abbreviation is the number of letters that were not replaced plus the number of substrings that were replaced. For example, the abbreviation <code>&quot;s10n&quot;</code> has a length of <code>3</code> (<code>2</code> letters + <code>1</code> substring) and <code>&quot;su3i1u2on&quot;</code> has a length of <code>9</code> (<code>6</code> letters + <code>3</code> substrings).</p>

<p>Given a target string <code>target</code> and an array of strings <code>dictionary</code>, return <em>an <strong>abbreviation</strong> of </em><code>target</code><em> with the <strong>shortest possible length</strong> such that it is <strong>not an abbreviation</strong> of <strong>any</strong> string in </em><code>dictionary</code><em>. If there are multiple shortest abbreviations, return any of them</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> target = &quot;apple&quot;, dictionary = [&quot;blade&quot;]
<strong>Output:</strong> &quot;a4&quot;
<strong>Explanation:</strong> The shortest abbreviation of &quot;apple&quot; is &quot;5&quot;, but this is also an abbreviation of &quot;blade&quot;.
The next shortest abbreviations are &quot;a4&quot; and &quot;4e&quot;. &quot;4e&quot; is an abbreviation of blade while &quot;a4&quot; is not.
Hence, return &quot;a4&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> target = &quot;apple&quot;, dictionary = [&quot;blade&quot;,&quot;plain&quot;,&quot;amber&quot;]
<strong>Output:</strong> &quot;1p3&quot;
<strong>Explanation:</strong> &quot;5&quot; is an abbreviation of both &quot;apple&quot; but also every word in the dictionary.
&quot;a4&quot; is an abbreviation of &quot;apple&quot; but also &quot;amber&quot;.
&quot;4e&quot; is an abbreviation of &quot;apple&quot; but also &quot;blade&quot;.
&quot;1p3&quot;, &quot;2p2&quot;, and &quot;3l1&quot; are the next shortest abbreviations of &quot;apple&quot;.
Since none of them are abbreviations of words in the dictionary, returning any of them is correct.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == target.length</code></li>
	<li><code>n == dictionary.length</code></li>
	<li><code>1 &lt;= m &lt;= 21</code></li>
	<li><code>0 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= dictionary[i].length &lt;= 100</code></li>
	<li><code>log<sub>2</sub>(n) + m &lt;= 21</code> if <code>n &gt; 0</code></li>
	<li><code>target</code> and <code>dictionary[i]</code> consist of lowercase English letters.</li>
	<li><code>dictionary</code> does not contain <code>target</code>.</li>
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
