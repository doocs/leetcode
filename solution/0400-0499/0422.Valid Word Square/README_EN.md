# [422. Valid Word Square](https://leetcode.com/problems/valid-word-square)

[中文文档](/solution/0400-0499/0422.Valid%20Word%20Square/README.md)

## Description

<p>Given an array of strings <code>words</code>, return <code>true</code> <em>if it forms a valid <strong>word square</strong></em>.</p>

<p>A sequence of strings forms a valid <strong>word square</strong> if the <code>k<sup>th</sup></code> row and column read the same string, where <code>0 &lt;= k &lt; max(numRows, numColumns)</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0422.Valid%20Word%20Square/images/validsq1-grid.jpg" style="width: 333px; height: 333px;" />
<pre>
<strong>Input:</strong> words = [&quot;abcd&quot;,&quot;bnrt&quot;,&quot;crmy&quot;,&quot;dtye&quot;]
<strong>Output:</strong> true
<strong>Explanation:</strong>
The 1<sup>st</sup> row and 1<sup>st</sup> column both read &quot;abcd&quot;.
The 2<sup>nd</sup> row and 2<sup>nd</sup> column both read &quot;bnrt&quot;.
The 3<sup>rd</sup> row and 3<sup>rd</sup> column both read &quot;crmy&quot;.
The 4<sup>th</sup> row and 4<sup>th</sup> column both read &quot;dtye&quot;.
Therefore, it is a valid word square.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0422.Valid%20Word%20Square/images/validsq2-grid.jpg" style="width: 333px; height: 333px;" />
<pre>
<strong>Input:</strong> words = [&quot;abcd&quot;,&quot;bnrt&quot;,&quot;crm&quot;,&quot;dt&quot;]
<strong>Output:</strong> true
<strong>Explanation:</strong>
The 1<sup>st</sup> row and 1<sup>st</sup> column both read &quot;abcd&quot;.
The 2<sup>nd</sup> row and 2<sup>nd</sup> column both read &quot;bnrt&quot;.
The 3<sup>rd</sup> row and 3<sup>rd</sup> column both read &quot;crm&quot;.
The 4<sup>th</sup> row and 4<sup>th</sup> column both read &quot;dt&quot;.
Therefore, it is a valid word square.
</pre>

<p><strong>Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0422.Valid%20Word%20Square/images/validsq3-grid.jpg" style="width: 333px; height: 333px;" />
<pre>
<strong>Input:</strong> words = [&quot;ball&quot;,&quot;area&quot;,&quot;read&quot;,&quot;lady&quot;]
<strong>Output:</strong> false
<strong>Explanation:</strong>
The 3<sup>rd</sup> row reads &quot;read&quot; while the 3<sup>rd</sup> column reads &quot;lead&quot;.
Therefore, it is NOT a valid word square.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 500</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 500</code></li>
	<li><code>words[i]</code> consists of only lowercase English letters.</li>
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
