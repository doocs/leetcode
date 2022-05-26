# [1177. Can Make Palindrome from Substring](https://leetcode.com/problems/can-make-palindrome-from-substring)

[中文文档](/solution/1100-1199/1177.Can%20Make%20Palindrome%20from%20Substring/README.md)

## Description

<p>Given a string <code>s</code>, we make queries on substrings of <code>s</code>.</p>

<p>For each query <code>queries[i] = [left, right, k]</code>, we may <strong>rearrange</strong>&nbsp;the substring <code>s[left], ..., s[right]</code>, and then choose <strong>up to</strong> <code>k</code> of them to replace with any lowercase English letter.&nbsp;</p>

<p>If the substring&nbsp;is possible to be a&nbsp;palindrome string after the operations above, the result of the query is <code>true</code>.&nbsp;Otherwise, the result&nbsp;is <code>false</code>.</p>

<p>Return an array <code>answer[]</code>, where <code>answer[i]</code> is the result of the <code>i</code>-th query <code>queries[i]</code>.</p>

<p>Note that: Each letter is counted <strong>individually</strong> for replacement so&nbsp;if for example&nbsp;<code>s[left..right] = &quot;aaa&quot;</code>, and <code>k = 2</code>, we can only replace two of the letters.&nbsp; (Also, note that the initial string <code>s</code>&nbsp;is never modified by any query.)</p>

<p>&nbsp;</p>
<p><strong>Example :</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcda&quot;, queries = [[3,3,0],[1,2,0],[0,3,1],[0,3,2],[0,4,1]]
<strong>Output:</strong> [true,false,false,true,true]
<strong>Explanation:</strong>
queries[0] : substring = &quot;d&quot;, is palidrome.
queries[1] :&nbsp;substring = &quot;bc&quot;, is not palidrome.
queries[2] :&nbsp;substring = &quot;abcd&quot;, is not palidrome after replacing only 1 character.
queries[3] :&nbsp;substring = &quot;abcd&quot;, could be changed to &quot;abba&quot; which is palidrome. Also this can be changed to &quot;baab&quot; first rearrange it &quot;bacd&quot; then replace &quot;cd&quot; with &quot;ab&quot;.
queries[4] :&nbsp;substring = &quot;abcda&quot;,&nbsp;could be changed to &quot;abcba&quot; which is palidrome.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length,&nbsp;queries.length&nbsp;&lt;= 10^5</code></li>
	<li><code>0 &lt;= queries[i][0] &lt;= queries[i][1] &lt;&nbsp;s.length</code></li>
	<li><code>0 &lt;= queries[i][2] &lt;= s.length</code></li>
	<li><code>s</code> only contains lowercase English letters.</li>
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
