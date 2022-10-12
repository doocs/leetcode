# [722. Remove Comments](https://leetcode.com/problems/remove-comments)

[中文文档](/solution/0700-0799/0722.Remove%20Comments/README.md)

## Description

<p>Given a C++ program, remove comments from it. The program source is an array of strings <code>source</code> where <code>source[i]</code> is the <code>i<sup>th</sup></code> line of the source code. This represents the result of splitting the original source code string by the newline character <code>&#39;\n&#39;</code>.</p>

<p>In C++, there are two types of comments, line comments, and block comments.</p>

<ul>
	<li>The string <code>&quot;//&quot;</code> denotes a line comment, which represents that it and the rest of the characters to the right of it in the same line should be ignored.</li>
	<li>The string <code>&quot;/*&quot;</code> denotes a block comment, which represents that all characters until the next (non-overlapping) occurrence of <code>&quot;*/&quot;</code> should be ignored. (Here, occurrences happen in reading order: line by line from left to right.) To be clear, the string <code>&quot;/*/&quot;</code> does not yet end the block comment, as the ending would be overlapping the beginning.</li>
</ul>

<p>The first effective comment takes precedence over others.</p>

<ul>
	<li>For example, if the string <code>&quot;//&quot;</code> occurs in a block comment, it is ignored.</li>
	<li>Similarly, if the string <code>&quot;/*&quot;</code> occurs in a line or block comment, it is also ignored.</li>
</ul>

<p>If a certain line of code is empty after removing comments, you must not output that line: each string in the answer list will be non-empty.</p>

<p>There will be no control characters, single quote, or double quote characters.</p>

<ul>
	<li>For example, <code>source = &quot;string s = &quot;/* Not a comment. */&quot;;&quot;</code> will not be a test case.</li>
</ul>

<p>Also, nothing else such as defines or macros will interfere with the comments.</p>

<p>It is guaranteed that every open block comment will eventually be closed, so <code>&quot;/*&quot;</code> outside of a line or block comment always starts a new comment.</p>

<p>Finally, implicit newline characters can be deleted by block comments. Please see the examples below for details.</p>

<p>After removing the comments from the source code, return <em>the source code in the same format</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> source = [&quot;/*Test program */&quot;, &quot;int main()&quot;, &quot;{ &quot;, &quot;  // variable declaration &quot;, &quot;int a, b, c;&quot;, &quot;/* This is a test&quot;, &quot;   multiline  &quot;, &quot;   comment for &quot;, &quot;   testing */&quot;, &quot;a = b + c;&quot;, &quot;}&quot;]
<strong>Output:</strong> [&quot;int main()&quot;,&quot;{ &quot;,&quot;  &quot;,&quot;int a, b, c;&quot;,&quot;a = b + c;&quot;,&quot;}&quot;]
<strong>Explanation:</strong> The line by line code is visualized as below:
/*Test program */
int main()
{ 
  // variable declaration 
int a, b, c;
/* This is a test
   multiline  
   comment for 
   testing */
a = b + c;
}
The string /* denotes a block comment, including line 1 and lines 6-9. The string // denotes line 4 as comments.
The line by line output code is visualized as below:
int main()
{ 
  
int a, b, c;
a = b + c;
}
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> source = [&quot;a/*comment&quot;, &quot;line&quot;, &quot;more_comment*/b&quot;]
<strong>Output:</strong> [&quot;ab&quot;]
<strong>Explanation:</strong> The original source string is &quot;a/*comment\nline\nmore_comment*/b&quot;, where we have bolded the newline characters.  After deletion, the implicit newline characters are deleted, leaving the string &quot;ab&quot;, which when delimited by newline characters becomes [&quot;ab&quot;].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= source.length &lt;= 100</code></li>
	<li><code>0 &lt;= source[i].length &lt;= 80</code></li>
	<li><code>source[i]</code> consists of printable <strong>ASCII</strong> characters.</li>
	<li>Every open block comment is eventually closed.</li>
	<li>There are no single-quote or&nbsp;double-quote in the input.</li>
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
