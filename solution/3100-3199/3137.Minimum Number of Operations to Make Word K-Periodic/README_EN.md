# [3137. Minimum Number of Operations to Make Word K-Periodic](https://leetcode.com/problems/minimum-number-of-operations-to-make-word-k-periodic)

[中文文档](/solution/3100-3199/3137.Minimum%20Number%20of%20Operations%20to%20Make%20Word%20K-Periodic/README.md)

<!-- tags: -->

## Description

<p>You are given a string <code>word</code> of size <code>n</code>, and an integer <code>k</code> such that <code>k</code> divides <code>n</code>.</p>

<p>In one operation, you can pick any two indices <code>i</code> and <code>j</code>, that are divisible by <code>k</code>, then replace the <span data-keyword="substring">substring</span> of length <code>k</code> starting at <code>i</code> with the substring of length <code>k</code> starting at <code>j</code>. That is, replace the substring <code>word[i..i + k - 1]</code> with the substring <code>word[j..j + k - 1]</code>.<!-- notionvc: 49ac84f7-0724-452a-ab43-0c5e53f1db33 --></p>

<p>Return <em>the <strong>minimum</strong> number of operations required to make</em> <code>word</code> <em><strong>k-periodic</strong></em>.</p>

<p>We say that <code>word</code> is <strong>k-periodic</strong> if there is some string <code>s</code> of length <code>k</code> such that <code>word</code> can be obtained by concatenating <code>s</code> an arbitrary number of times. For example, if <code>word == &ldquo;ababab&rdquo;</code>, then <code>word</code> is 2-periodic for <code>s = &quot;ab&quot;</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
">word = &quot;leetcodeleet&quot;, k = 4</span></p>

<p><strong>Output:</strong> <span class="example-io" style="
font-family: Menlo,sans-serif;
font-size: 0.85rem;
">1</span></p>

<p><strong>Explanation:</strong></p>

<p>We can obtain a 4-periodic string by picking i = 4 and j = 0. After this operation, word becomes equal to &quot;leetleetleet&quot;.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
">word = &quot;</span>leetcoleet<span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
">&quot;, k = 2</span></p>

<p><strong>Output:</strong> 3</p>

<p><strong>Explanation:</strong></p>

<p>We can obtain a 2-periodic string by applying the operations in the table below.</p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" height="146" style="border-collapse:collapse; text-align: center; vertical-align: middle;">
	<tbody>
		<tr>
			<th>i</th>
			<th>j</th>
			<th>word</th>
		</tr>
		<tr>
			<td style="padding: 5px 15px;">0</td>
			<td style="padding: 5px 15px;">2</td>
			<td style="padding: 5px 15px;">etetcoleet</td>
		</tr>
		<tr>
			<td style="padding: 5px 15px;">4</td>
			<td style="padding: 5px 15px;">0</td>
			<td style="padding: 5px 15px;">etetetleet</td>
		</tr>
		<tr>
			<td style="padding: 5px 15px;">6</td>
			<td style="padding: 5px 15px;">0</td>
			<td style="padding: 5px 15px;">etetetetet</td>
		</tr>
	</tbody>
</table>
</div>

<div id="gtx-trans" style="position: absolute; left: 107px; top: 238.5px;">
<div class="gtx-trans-icon">&nbsp;</div>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == word.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= word.length</code></li>
	<li><code>k</code> divides <code>word.length</code>.</li>
	<li><code>word</code> consists only of lowercase English letters.</li>
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
