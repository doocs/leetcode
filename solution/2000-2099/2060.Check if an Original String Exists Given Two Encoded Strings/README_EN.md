# [2060. Check if an Original String Exists Given Two Encoded Strings](https://leetcode.com/problems/check-if-an-original-string-exists-given-two-encoded-strings)

[中文文档](/solution/2000-2099/2060.Check%20if%20an%20Original%20String%20Exists%20Given%20Two%20Encoded%20Strings/README.md)

## Description

<p>An original string, consisting of lowercase English letters, can be encoded by the following steps:</p>

<ul>
	<li>Arbitrarily <strong>split</strong> it into a <strong>sequence</strong> of some number of <strong>non-empty</strong> substrings.</li>
	<li>Arbitrarily choose some elements (possibly none) of the sequence, and <strong>replace</strong> each with <strong>its length</strong> (as a numeric string).</li>
	<li><strong>Concatenate</strong> the sequence as the encoded string.</li>
</ul>

<p>For example, <strong>one way</strong> to encode an original string <code>&quot;abcdefghijklmnop&quot;</code> might be:</p>

<ul>
	<li>Split it as a sequence: <code>[&quot;ab&quot;, &quot;cdefghijklmn&quot;, &quot;o&quot;, &quot;p&quot;]</code>.</li>
	<li>Choose the second and third elements to be replaced by their lengths, respectively. The sequence becomes <code>[&quot;ab&quot;, &quot;12&quot;, &quot;1&quot;, &quot;p&quot;]</code>.</li>
	<li>Concatenate the elements of the sequence to get the encoded string: <code>&quot;ab121p&quot;</code>.</li>
</ul>

<p>Given two encoded strings <code>s1</code> and <code>s2</code>, consisting of lowercase English letters and digits <code>1-9</code> (inclusive), return <code>true</code><em> if there exists an original string that could be encoded as <strong>both</strong> </em><code>s1</code><em> and </em><code>s2</code><em>. Otherwise, return </em><code>false</code>.</p>

<p><strong>Note</strong>: The test cases are generated such that the number of consecutive digits in <code>s1</code> and <code>s2</code> does not exceed <code>3</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;internationalization&quot;, s2 = &quot;i18n&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> It is possible that &quot;internationalization&quot; was the original string.
- &quot;internationalization&quot; 
  -&gt; Split:       [&quot;internationalization&quot;]
  -&gt; Do not replace any element
  -&gt; Concatenate:  &quot;internationalization&quot;, which is s1.
- &quot;internationalization&quot;
  -&gt; Split:       [&quot;i&quot;, &quot;nternationalizatio&quot;, &quot;n&quot;]
  -&gt; Replace:     [&quot;i&quot;, &quot;18&quot;,                 &quot;n&quot;]
  -&gt; Concatenate:  &quot;i18n&quot;, which is s2
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;l123e&quot;, s2 = &quot;44&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> It is possible that &quot;leetcode&quot; was the original string.
- &quot;leetcode&quot; 
  -&gt; Split:      [&quot;l&quot;, &quot;e&quot;, &quot;et&quot;, &quot;cod&quot;, &quot;e&quot;]
  -&gt; Replace:    [&quot;l&quot;, &quot;1&quot;, &quot;2&quot;,  &quot;3&quot;,   &quot;e&quot;]
  -&gt; Concatenate: &quot;l123e&quot;, which is s1.
- &quot;leetcode&quot; 
  -&gt; Split:      [&quot;leet&quot;, &quot;code&quot;]
  -&gt; Replace:    [&quot;4&quot;,    &quot;4&quot;]
  -&gt; Concatenate: &quot;44&quot;, which is s2.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;a5b&quot;, s2 = &quot;c5b&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> It is impossible.
- The original string encoded as s1 must start with the letter &#39;a&#39;.
- The original string encoded as s2 must start with the letter &#39;c&#39;.
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;112s&quot;, s2 = &quot;g841&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> It is possible that &quot;gaaaaaaaaaaaas&quot; was the original string
- &quot;gaaaaaaaaaaaas&quot;
  -&gt; Split:      [&quot;g&quot;, &quot;aaaaaaaaaaaa&quot;, &quot;s&quot;]
  -&gt; Replace:    [&quot;1&quot;, &quot;12&quot;,           &quot;s&quot;]
  -&gt; Concatenate: &quot;112s&quot;, which is s1.
- &quot;gaaaaaaaaaaaas&quot;
  -&gt; Split:      [&quot;g&quot;, &quot;aaaaaaaa&quot;, &quot;aaaa&quot;, &quot;s&quot;]
  -&gt; Replace:    [&quot;g&quot;, &quot;8&quot;,        &quot;4&quot;,    &quot;1&quot;]
  -&gt; Concatenate: &quot;g841&quot;, which is s2.
</pre>

<p><strong>Example 5:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;ab&quot;, s2 = &quot;a2&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> It is impossible.
- The original string encoded as s1 has two letters.
- The original string encoded as s2 has three letters.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s1.length, s2.length &lt;= 40</code></li>
	<li><code>s1</code> and <code>s2</code> consist of digits <code>1-9</code> (inclusive), and lowercase English letters only.</li>
	<li>The number of consecutive digits in <code>s1</code> and <code>s2</code> does not exceed <code>3</code>.</li>
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
