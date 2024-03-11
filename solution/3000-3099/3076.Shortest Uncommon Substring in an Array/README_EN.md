# [3076. Shortest Uncommon Substring in an Array](https://leetcode.com/problems/shortest-uncommon-substring-in-an-array)

[中文文档](/solution/3000-3099/3076.Shortest%20Uncommon%20Substring%20in%20an%20Array/README.md)

<!-- tags: -->

## Description

<p>You are given an array <code>arr</code> of size <code>n</code> consisting of <strong>non-empty</strong> strings.</p>

<p>Find a string array <code>answer</code> of size <code>n</code> such that:</p>

<ul>
	<li><code>answer[i]</code> is the <strong>shortest</strong> <span data-keyword="substring">substring</span> of <code>arr[i]</code> that does <strong>not</strong> occur as a substring in any other string in <code>arr</code>. If multiple such substrings exist, <code>answer[i]</code> should be the <span data-keyword="lexicographically-smaller-string">lexicographically smallest</span>. And if no such substring exists, <code>answer[i]</code> should be an empty string.</li>
</ul>

<p>Return <em>the array </em><code>answer</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [&quot;cab&quot;,&quot;ad&quot;,&quot;bad&quot;,&quot;c&quot;]
<strong>Output:</strong> [&quot;ab&quot;,&quot;&quot;,&quot;ba&quot;,&quot;&quot;]
<strong>Explanation:</strong> We have the following:
- For the string &quot;cab&quot;, the shortest substring that does not occur in any other string is either &quot;ca&quot; or &quot;ab&quot;, we choose the lexicographically smaller substring, which is &quot;ab&quot;.
- For the string &quot;ad&quot;, there is no substring that does not occur in any other string.
- For the string &quot;bad&quot;, the shortest substring that does not occur in any other string is &quot;ba&quot;.
- For the string &quot;c&quot;, there is no substring that does not occur in any other string.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [&quot;abc&quot;,&quot;bcd&quot;,&quot;abcd&quot;]
<strong>Output:</strong> [&quot;&quot;,&quot;&quot;,&quot;abcd&quot;]
<strong>Explanation:</strong> We have the following:
- For the string &quot;abc&quot;, there is no substring that does not occur in any other string.
- For the string &quot;bcd&quot;, there is no substring that does not occur in any other string.
- For the string &quot;abcd&quot;, the shortest substring that does not occur in any other string is &quot;abcd&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == arr.length</code></li>
	<li><code>2 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= arr[i].length &lt;= 20</code></li>
	<li><code>arr[i]</code> consists only of lowercase English letters.</li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python

```

```java

```

```cpp

```

```go

```

<!-- tabs:end -->

<!-- end -->
