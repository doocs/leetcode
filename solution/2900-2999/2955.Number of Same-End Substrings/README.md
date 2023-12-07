# [2955. Number of Same-End Substrings](https://leetcode.cn/problems/number-of-same-end-substrings)

[English Version](/solution/2900-2999/2955.Number%20of%20Same-End%20Substrings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>You are given a <strong>0-indexed</strong> string <code>s</code>, and a 2D array of integers <code>queries</code>, where <code>queries[i] = [l<sub>i</sub>, r<sub>i</sub>]</code> indicates a substring of <code>s</code> starting from the index <code>l<sub>i</sub></code> and ending at the index <code>r<sub>i</sub></code> (both <strong>inclusive</strong>), i.e. <code>s[l<sub>i</sub>..r<sub>i</sub>]</code>.</p>

<p>Return <em>an array </em><code>ans</code><em> where</em> <code>ans[i]</code> <em>is the number of <strong>same-end</strong> substrings of</em> <code>queries[i]</code>.</p>

<p>A <strong>0-indexed</strong> string <code>t</code> of length <code>n</code> is called <strong>same-end</strong> if it has the same character at both of its ends, i.e., <code>t[0] == t[n - 1]</code>.</p>

<p>A <b>substring</b> is a contiguous non-empty sequence of characters within a string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcaab&quot;, queries = [[0,0],[1,4],[2,5],[0,5]]
<strong>Output:</strong> [1,5,5,10]
<strong>Explanation:</strong> Here is the same-end substrings of each query:
1<sup>st</sup> query: s[0..0] is &quot;a&quot; which has 1 same-end substring: &quot;<strong><u>a</u></strong>&quot;.
2<sup>nd</sup> query: s[1..4] is &quot;bcaa&quot; which has 5 same-end substrings: &quot;<strong><u>b</u></strong>caa&quot;, &quot;b<strong><u>c</u></strong>aa&quot;, &quot;bc<strong><u>a</u></strong>a&quot;, &quot;bca<strong><u>a</u></strong>&quot;, &quot;bc<strong><u>aa</u></strong>&quot;.
3<sup>rd</sup> query: s[2..5] is &quot;caab&quot; which has 5 same-end substrings: &quot;<strong><u>c</u></strong>aab&quot;, &quot;c<strong><u>a</u></strong>ab&quot;, &quot;ca<strong><u>a</u></strong>b&quot;, &quot;caa<strong><u>b</u></strong>&quot;, &quot;c<strong><u>aa</u></strong>b&quot;.
4<sup>th</sup> query: s[0..5] is &quot;abcaab&quot; which has 10 same-end substrings: &quot;<strong><u>a</u></strong>bcaab&quot;, &quot;a<strong><u>b</u></strong>caab&quot;, &quot;ab<strong><u>c</u></strong>aab&quot;, &quot;abc<strong><u>a</u></strong>ab&quot;, &quot;abca<strong><u>a</u></strong>b&quot;, &quot;abcaa<strong><u>b</u></strong>&quot;, &quot;abc<strong><u>aa</u></strong>b&quot;, &quot;<strong><u>abca</u></strong>ab&quot;, &quot;<strong><u>abcaa</u></strong>b&quot;, &quot;a<strong><u>bcaab</u></strong>&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcd&quot;, queries = [[0,3]]
<strong>Output:</strong> [4]
<strong>Explanation:</strong> The only query is s[0..3] which is &quot;abcd&quot;. It has 4 same-end substrings: &quot;<strong><u>a</u></strong>bcd&quot;, &quot;a<strong><u>b</u></strong>cd&quot;, &quot;ab<strong><u>c</u></strong>d&quot;, &quot;abc<strong><u>d</u></strong>&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>s</code> consists only of lowercase English letters.</li>
	<li><code>1 &lt;= queries.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>queries[i].length == 2</code></li>
	<li><code>queries[i] = [l<sub>i</sub>, r<sub>i</sub>]</code></li>
	<li><code>0 &lt;= l<sub>i</sub> &lt;= r<sub>i</sub> &lt; s.length</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
