# [2983. Palindrome Rearrangement Queries](https://leetcode.com/problems/palindrome-rearrangement-queries)

[中文文档](/solution/2900-2999/2983.Palindrome%20Rearrangement%20Queries/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> string <code>s</code> having an <strong>even</strong> length <code>n</code>.</p>

<p>You are also given a <strong>0-indexed</strong> 2D integer array, <code>queries</code>, where <code>queries[i] = [a<sub>i</sub>, b<sub>i</sub>, c<sub>i</sub>, d<sub>i</sub>]</code>.</p>

<p>For each query <code>i</code>, you are allowed to perform the following operations:</p>

<ul>
	<li>Rearrange the characters within the <strong>substring</strong> <code>s[a<sub>i</sub>:b<sub>i</sub>]</code>, where <code>0 &lt;= a<sub>i</sub> &lt;= b<sub>i</sub> &lt; n / 2</code>.</li>
	<li>Rearrange the characters within the <strong>substring</strong> <code>s[c<sub>i</sub>:d<sub>i</sub>]</code>, where <code>n / 2 &lt;= c<sub>i</sub> &lt;= d<sub>i</sub> &lt; n</code>.</li>
</ul>

<p>For each query, your task is to determine whether it is possible to make <code>s</code> a <strong>palindrome</strong> by performing the operations.</p>

<p>Each query is answered <strong>independently</strong> of the others.</p>

<p>Return <em>a <strong>0-indexed</strong> array </em><code>answer</code><em>, where </em><code>answer[i] == true</code><em> if it is possible to make </em><code>s</code><em> a palindrome by performing operations specified by the </em><code>i<sup>th</sup></code><em> query, and </em><code>false</code><em> otherwise.</em></p>

<ul>
	<li>A <strong>substring</strong> is a contiguous sequence of characters within a string.</li>
	<li><code>s[x:y]</code> represents the substring consisting of characters from the index <code>x</code> to index <code>y</code> in <code>s</code>, <strong>both inclusive</strong>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcabc&quot;, queries = [[1,1,3,5],[0,2,5,5]]
<strong>Output:</strong> [true,true]
<strong>Explanation:</strong> In this example, there are two queries:
In the first query:
- a<sub>0</sub> = 1, b<sub>0</sub> = 1, c<sub>0</sub> = 3, d<sub>0</sub> = 5.
- So, you are allowed to rearrange s[1:1] =&gt; a<u>b</u>cabc and s[3:5] =&gt; abc<u>abc</u>.
- To make s a palindrome, s[3:5] can be rearranged to become =&gt; abc<u>cba</u>.
- Now, s is a palindrome. So, answer[0] = true.
In the second query:
- a<sub>1</sub> = 0, b<sub>1</sub> = 2, c<sub>1</sub> = 5, d<sub>1</sub> = 5.
- So, you are allowed to rearrange s[0:2] =&gt; <u>abc</u>abc and s[5:5] =&gt; abcab<u>c</u>.
- To make s a palindrome, s[0:2] can be rearranged to become =&gt; <u>cba</u>abc.
- Now, s is a palindrome. So, answer[1] = true.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abbcdecbba&quot;, queries = [[0,2,7,9]]
<strong>Output:</strong> [false]
<strong>Explanation:</strong> In this example, there is only one query.
a<sub>0</sub> = 0, b<sub>0</sub> = 2, c<sub>0</sub> = 7, d<sub>0</sub> = 9.
So, you are allowed to rearrange s[0:2] =&gt; <u>abb</u>cdecbba and s[7:9] =&gt; abbcdec<u>bba</u>.
It is not possible to make s a palindrome by rearranging these substrings because s[3:6] is not a palindrome.
So, answer[0] = false.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;acbcab&quot;, queries = [[1,2,4,5]]
<strong>Output:</strong> [true]
<strong>Explanation: </strong>In this example, there is only one query.
a<sub>0</sub> = 1, b<sub>0</sub> = 2, c<sub>0</sub> = 4, d<sub>0</sub> = 5.
So, you are allowed to rearrange s[1:2] =&gt; a<u>cb</u>cab and s[4:5] =&gt; acbc<u>ab</u>.
To make s a palindrome s[1:2] can be rearranged to become a<u>bc</u>cab.
Then, s[4:5] can be rearranged to become abcc<u>ba</u>.
Now, s is a palindrome. So, answer[0] = true.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n == s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i].length == 4</code></li>
	<li><code>a<sub>i</sub> == queries[i][0], b<sub>i</sub> == queries[i][1]</code></li>
	<li><code>c<sub>i</sub> == queries[i][2], d<sub>i</sub> == queries[i][3]</code></li>
	<li><code>0 &lt;= a<sub>i</sub> &lt;= b<sub>i</sub> &lt; n / 2</code></li>
	<li><code>n / 2 &lt;= c<sub>i</sub> &lt;= d<sub>i</sub> &lt; n </code></li>
	<li><code>n</code> is even.</li>
	<li><code>s</code> consists of only lowercase English letters.</li>
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
