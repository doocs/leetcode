# [591. Tag Validator](https://leetcode.com/problems/tag-validator)

[中文文档](/solution/0500-0599/0591.Tag%20Validator/README.md)

## Description

<p>Given a string representing a code snippet, implement a tag validator to parse the code and return whether it is valid.</p>

<p>A code snippet is valid if all the following rules hold:</p>

<ol>
	<li>The code must be wrapped in a <b>valid closed tag</b>. Otherwise, the code is invalid.</li>
	<li>A <b>closed tag</b> (not necessarily valid) has exactly the following format : <code>&lt;TAG_NAME&gt;TAG_CONTENT&lt;/TAG_NAME&gt;</code>. Among them, <code>&lt;TAG_NAME&gt;</code> is the start tag, and <code>&lt;/TAG_NAME&gt;</code> is the end tag. The TAG_NAME in start and end tags should be the same. A closed tag is <b>valid</b> if and only if the TAG_NAME and TAG_CONTENT are valid.</li>
	<li>A <b>valid</b> <code>TAG_NAME</code> only contain <b>upper-case letters</b>, and has length in range [1,9]. Otherwise, the <code>TAG_NAME</code> is <b>invalid</b>.</li>
	<li>A <b>valid</b> <code>TAG_CONTENT</code> may contain other <b>valid closed tags</b>, <b>cdata</b> and any characters (see note1) <b>EXCEPT</b> unmatched <code>&lt;</code>, unmatched start and end tag, and unmatched or closed tags with invalid TAG_NAME. Otherwise, the <code>TAG_CONTENT</code> is <b>invalid</b>.</li>
	<li>A start tag is unmatched if no end tag exists with the same TAG_NAME, and vice versa. However, you also need to consider the issue of unbalanced when tags are nested.</li>
	<li>A <code>&lt;</code> is unmatched if you cannot find a subsequent <code>&gt;</code>. And when you find a <code>&lt;</code> or <code>&lt;/</code>, all the subsequent characters until the next <code>&gt;</code> should be parsed as TAG_NAME (not necessarily valid).</li>
	<li>The cdata has the following format : <code>&lt;![CDATA[CDATA_CONTENT]]&gt;</code>. The range of <code>CDATA_CONTENT</code> is defined as the characters between <code>&lt;![CDATA[</code> and the <b>first subsequent</b> <code>]]&gt;</code>.</li>
	<li><code>CDATA_CONTENT</code> may contain <b>any characters</b>. The function of cdata is to forbid the validator to parse <code>CDATA_CONTENT</code>, so even it has some characters that can be parsed as tag (no matter valid or invalid), you should treat it as <b>regular characters</b>.</li>
</ol>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> code = &quot;&lt;DIV&gt;This is the first line &lt;![CDATA[&lt;div&gt;]]&gt;&lt;/DIV&gt;&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> 
The code is wrapped in a closed tag : &lt;DIV&gt; and &lt;/DIV&gt;. 
The TAG_NAME is valid, the TAG_CONTENT consists of some characters and cdata. 
Although CDATA_CONTENT has an unmatched start tag with invalid TAG_NAME, it should be considered as plain text, not parsed as a tag.
So TAG_CONTENT is valid, and then the code is valid. Thus return true.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> code = &quot;&lt;DIV&gt;&gt;&gt;  ![cdata[]] &lt;![CDATA[&lt;div&gt;]&gt;]]&gt;]]&gt;&gt;]&lt;/DIV&gt;&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong>
We first separate the code into : start_tag|tag_content|end_tag.
start_tag -&gt; <b>&quot;&lt;DIV&gt;&quot;</b>
end_tag -&gt; <b>&quot;&lt;/DIV&gt;&quot;</b>
tag_content could also be separated into : text1|cdata|text2.
text1 -&gt; <b>&quot;&gt;&gt;  ![cdata[]] &quot;</b>
cdata -&gt; <b>&quot;&lt;![CDATA[&lt;div&gt;]&gt;]]&gt;&quot;</b>, where the CDATA_CONTENT is <b>&quot;&lt;div&gt;]&gt;&quot;</b>
text2 -&gt; <b>&quot;]]&gt;&gt;]&quot;</b>
The reason why start_tag is NOT <b>&quot;&lt;DIV&gt;&gt;&gt;&quot;</b> is because of the rule 6.
The reason why cdata is NOT <b>&quot;&lt;![CDATA[&lt;div&gt;]&gt;]]&gt;]]&gt;&quot;</b> is because of the rule 7.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> code = &quot;&lt;A&gt;  &lt;B&gt; &lt;/A&gt;   &lt;/B&gt;&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> Unbalanced. If &quot;&lt;A&gt;&quot; is closed, then &quot;&lt;B&gt;&quot; must be unmatched, and vice versa.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= code.length &lt;= 500</code></li>
	<li><code>code</code> consists of English letters, digits, <code>&#39;&lt;&#39;</code>, <code>&#39;&gt;&#39;</code>, <code>&#39;/&#39;</code>, <code>&#39;!&#39;</code>, <code>&#39;[&#39;</code>, <code>&#39;]&#39;</code>, <code>&#39;.&#39;</code>, and <code>&#39; &#39;</code>.</li>
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
