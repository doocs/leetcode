# [2904. Shortest and Lexicographically Smallest Beautiful String](https://leetcode.com/problems/shortest-and-lexicographically-smallest-beautiful-string)

[中文文档](/solution/2900-2999/2904.Shortest%20and%20Lexicographically%20Smallest%20Beautiful%20String/README.md)

## Description

<p>You are given a binary string <code>s</code> and a positive integer <code>k</code>.</p>

<p>A substring of <code>s</code> is <strong>beautiful</strong> if the number of <code>1</code>&#39;s in it is exactly <code>k</code>.</p>

<p>Let <code>len</code> be the length of the <strong>shortest</strong> beautiful substring.</p>

<p>Return <em>the lexicographically <strong>smallest</strong> beautiful substring of string </em><code>s</code><em> with length equal to </em><code>len</code>. If <code>s</code> doesn&#39;t contain a beautiful substring, return <em>an <strong>empty</strong> string</em>.</p>

<p>A string <code>a</code> is lexicographically <strong>larger</strong> than a string <code>b</code> (of the same length) if in the first position where <code>a</code> and <code>b</code> differ, <code>a</code> has a character strictly larger than the corresponding character in <code>b</code>.</p>

<ul>
	<li>For example, <code>&quot;abcd&quot;</code> is lexicographically larger than <code>&quot;abcc&quot;</code> because the first position they differ is at the fourth character, and <code>d</code> is greater than <code>c</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;100011001&quot;, k = 3
<strong>Output:</strong> &quot;11001&quot;
<strong>Explanation:</strong> There are 7 beautiful substrings in this example:
1. The substring &quot;<u>100011</u>001&quot;.
2. The substring &quot;<u>1000110</u>01&quot;.
3. The substring &quot;<u>10001100</u>1&quot;.
4. The substring &quot;1<u>00011001</u>&quot;.
5. The substring &quot;10<u>0011001</u>&quot;.
6. The substring &quot;100<u>011001</u>&quot;.
7. The substring &quot;1000<u>11001</u>&quot;.
The length of the shortest beautiful substring is 5.
The lexicographically smallest beautiful substring with length 5 is the substring &quot;11001&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;1011&quot;, k = 2
<strong>Output:</strong> &quot;11&quot;
<strong>Explanation:</strong> There are 3 beautiful substrings in this example:
1. The substring &quot;<u>101</u>1&quot;.
2. The substring &quot;1<u>011</u>&quot;.
3. The substring &quot;10<u>11</u>&quot;.
The length of the shortest beautiful substring is 2.
The lexicographically smallest beautiful substring with length 2 is the substring &quot;11&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;000&quot;, k = 1
<strong>Output:</strong> &quot;&quot;
<strong>Explanation:</strong> There are no beautiful substrings in this example.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>1 &lt;= k &lt;= s.length</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **C++**

```cpp

```

### **Go**

```go

```

### **...**

```

```

<!-- tabs:end -->
