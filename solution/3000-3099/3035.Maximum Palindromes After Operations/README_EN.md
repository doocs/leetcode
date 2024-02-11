# [3035. Maximum Palindromes After Operations](https://leetcode.com/problems/maximum-palindromes-after-operations)

[中文文档](/solution/3000-3099/3035.Maximum%20Palindromes%20After%20Operations/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> string array <code>words</code> having length <code>n</code> and containing <strong>0-indexed</strong> strings.</p>

<p>You are allowed to perform the following operation <strong>any</strong> number of times (<strong>including</strong> <strong>zero</strong>):</p>

<ul>
	<li>Choose integers <code>i</code>, <code>j</code>, <code>x</code>, and <code>y</code> such that <code>0 &lt;= i, j &lt; n</code>, <code>0 &lt;= x &lt; words[i].length</code>, <code>0 &lt;= y &lt; words[j].length</code>, and <strong>swap</strong> the characters <code>words[i][x]</code> and <code>words[j][y]</code>.</li>
</ul>

<p>Return <em>an integer denoting the <strong>maximum</strong> number of <span data-keyword="palindrome-string">palindromes</span> </em><code>words</code><em> can contain, after performing some operations.</em></p>

<p><strong>Note:</strong> <code>i</code> and <code>j</code> may be equal during an operation.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;abbb&quot;,&quot;ba&quot;,&quot;aa&quot;]
<strong>Output:</strong> 3
<strong>Explanation:</strong> In this example, one way to get the maximum number of palindromes is:
Choose i = 0, j = 1, x = 0, y = 0, so we swap words[0][0] and words[1][0]. words becomes [&quot;bbbb&quot;,&quot;aa&quot;,&quot;aa&quot;].
All strings in words are now palindromes.
Hence, the maximum number of palindromes achievable is 3.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;abc&quot;,&quot;ab&quot;]
<strong>Output:</strong> 2
<strong>Explanation: </strong>In this example, one way to get the maximum number of palindromes is: 
Choose i = 0, j = 1, x = 1, y = 0, so we swap words[0][1] and words[1][0]. words becomes [&quot;aac&quot;,&quot;bb&quot;].
Choose i = 0, j = 0, x = 1, y = 2, so we swap words[0][1] and words[0][2]. words becomes [&quot;aca&quot;,&quot;bb&quot;].
Both strings are now palindromes.
Hence, the maximum number of palindromes achievable is 2.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;cd&quot;,&quot;ef&quot;,&quot;a&quot;]
<strong>Output:</strong> 1
<strong>Explanation:</strong> In this example, there is no need to perform any operation.
There is one palindrome in words &quot;a&quot;.
It can be shown that it is not possible to get more than one palindrome after any number of operations.
Hence, the answer is 1.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 1000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 100</code></li>
	<li><code>words[i]</code> consists only of lowercase English letters.</li>
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
