# [2743. Count Substrings Without Repeating Character](https://leetcode.cn/problems/count-substrings-without-repeating-character)

[English Version](/solution/2700-2799/2743.Count%20Substrings%20Without%20Repeating%20Character/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>You are given a string <code>s</code> consisting only of lowercase English letters. We call a substring <b>special</b> if it contains no character which has occurred at least twice (in other words, it does not contain a repeating character). Your task is to count the number of <b>special</b> substrings. For example, in the string <code>&quot;pop&quot;</code>, the substring <code>&quot;po&quot;</code> is a <strong>special</strong> substring, however, <code>&quot;pop&quot;</code> is not <strong>special</strong> (since <code>&#39;p&#39;</code> has occurred twice).</p>

<p>Return <em>the number of <b>special</b> substrings.</em></p>

<p>A <strong>substring</strong> is a contiguous sequence of characters within a string. For example, <code>&quot;abc&quot;</code> is a substring of <code>&quot;abcd&quot;</code>, but <code>&quot;acd&quot;</code> is not.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcd&quot;
<strong>Output:</strong> 10
<strong>Explanation:</strong> Since each character occurs once, every substring is a special substring. We have 4 substrings of length one, 3 of length two, 2 of length three, and 1 substring of length four. So overall there are 4 + 3 + 2 + 1 = 10 special substrings.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;ooo&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> Any substring with a length of at least two contains a repeating character. So we have to count the number of substrings of length one, which is 3.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abab&quot;
<strong>Output:</strong> 7
<strong>Explanation:</strong> Special substrings are as follows (sorted by their start positions):
Special substrings of length 1: &quot;a&quot;, &quot;b&quot;, &quot;a&quot;, &quot;b&quot;
Special substrings of length 2: &quot;ab&quot;, &quot;ba&quot;, &quot;ab&quot;
And it can be shown that there are no special substrings with a length of at least three. So the answer would be 4 + 3 = 7.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of lowercase English letters</li>
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
