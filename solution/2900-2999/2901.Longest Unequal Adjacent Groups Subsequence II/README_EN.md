# [2901. Longest Unequal Adjacent Groups Subsequence II](https://leetcode.com/problems/longest-unequal-adjacent-groups-subsequence-ii)

[中文文档](/solution/2900-2999/2901.Longest%20Unequal%20Adjacent%20Groups%20Subsequence%20II/README.md)

## Description

<p>You are given an integer <code>n</code>, a <strong>0-indexed</strong> string array <code>words</code>, and a <strong>0-indexed</strong> array <code>groups</code>, both arrays having length <code>n</code>.</p>

<p>The <strong>hamming distance</strong> between two strings of equal length is the number of positions at which the corresponding characters are <strong>different</strong>.</p>

<p>You need to select the <strong>longest</strong> <strong>subsequence</strong> from an array of indices <code>[0, 1, ..., n - 1]</code>, such that for the subsequence denoted as <code>[i<sub>0</sub>, i<sub>1</sub>, ..., i<sub>k - 1</sub>]</code> having length <code>k</code>, the following holds:</p>

<ul>
	<li>For <strong>adjacent</strong> indices in the subsequence, their corresponding groups are <strong>unequal</strong>, i.e., <code>groups[i<sub>j</sub>] != groups[i<sub>j + 1</sub>]</code>, for each <code>j</code> where <code>0 &lt; j + 1 &lt; k</code>.</li>
	<li><code>words[i<sub>j</sub>]</code> and <code>words[i<sub>j + 1</sub>]</code> are <strong>equal</strong> in length, and the <strong>hamming distance</strong> between them is <code>1</code>, where <code>0 &lt; j + 1 &lt; k</code>, for all indices in the subsequence.</li>
</ul>

<p>Return <em>a string array containing the words corresponding to the indices <strong>(in order)</strong> in the selected subsequence</em>. If there are multiple answers, return <em>any of them</em>.</p>

<p>A <strong>subsequence</strong> of an array is a new array that is formed from the original array by deleting some (possibly none) of the elements without disturbing the relative positions of the remaining elements.</p>

<p><strong>Note:</strong> strings in <code>words</code> may be <strong>unequal</strong> in length.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 3, words = [&quot;bab&quot;,&quot;dab&quot;,&quot;cab&quot;], groups = [1,2,2]
<strong>Output:</strong> [&quot;bab&quot;,&quot;cab&quot;]
<strong>Explanation:</strong> A subsequence that can be selected is [0,2].
- groups[0] != groups[2]
- words[0].length == words[2].length, and the hamming distance between them is 1.
So, a valid answer is [words[0],words[2]] = [&quot;bab&quot;,&quot;cab&quot;].
Another subsequence that can be selected is [0,1].
- groups[0] != groups[1]
- words[0].length == words[1].length, and the hamming distance between them is 1.
So, another valid answer is [words[0],words[1]] = [&quot;bab&quot;,&quot;dab&quot;].
It can be shown that the length of the longest subsequence of indices that satisfies the conditions is 2.  </pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 4, words = [&quot;a&quot;,&quot;b&quot;,&quot;c&quot;,&quot;d&quot;], groups = [1,2,3,4]
<strong>Output:</strong> [&quot;a&quot;,&quot;b&quot;,&quot;c&quot;,&quot;d&quot;]
<strong>Explanation:</strong> We can select the subsequence [0,1,2,3].
It satisfies both conditions.
Hence, the answer is [words[0],words[1],words[2],words[3]] = [&quot;a&quot;,&quot;b&quot;,&quot;c&quot;,&quot;d&quot;].
It has the longest length among all subsequences of indices that satisfy the conditions.
Hence, it is the only answer.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == words.length == groups.length &lt;= 1000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 10</code></li>
	<li><code>1 &lt;= groups[i] &lt;= n</code></li>
	<li><code>words</code> consists of <strong>distinct</strong> strings.</li>
	<li><code>words[i]</code> consists of lowercase English letters.</li>
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
