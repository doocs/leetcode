---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3527.Find%20the%20Most%20Common%20Response/README_EN.md
tags:
    - Array
    - Hash Table
    - String
    - Counting
---

<!-- problem:start -->

# [3527. Find the Most Common Response](https://leetcode.com/problems/find-the-most-common-response)

[中文文档](/solution/3500-3599/3527.Find%20the%20Most%20Common%20Response/README.md)

## Description

<!-- description:start -->

<p>You are given a 2D string array <code>responses</code> where each <code>responses[i]</code> is an array of strings representing survey responses from the <code>i<sup>th</sup></code> day.</p>

<p>Return the <strong>most common</strong> response across all days after removing <strong>duplicate</strong> responses within each <code>responses[i]</code>. If there is a tie, return the <em><span data-keyword="lexicographically-smaller-string">lexicographically smallest</span></em> response.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">responses = [[&quot;good&quot;,&quot;ok&quot;,&quot;good&quot;,&quot;ok&quot;],[&quot;ok&quot;,&quot;bad&quot;,&quot;good&quot;,&quot;ok&quot;,&quot;ok&quot;],[&quot;good&quot;],[&quot;bad&quot;]]</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;good&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>After removing duplicates within each list, <code>responses = [[&quot;good&quot;, &quot;ok&quot;], [&quot;ok&quot;, &quot;bad&quot;, &quot;good&quot;], [&quot;good&quot;], [&quot;bad&quot;]]</code>.</li>
	<li><code>&quot;good&quot;</code> appears 3 times, <code>&quot;ok&quot;</code> appears 2 times, and <code>&quot;bad&quot;</code> appears 2 times.</li>
	<li>Return <code>&quot;good&quot;</code> because it has the highest frequency.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">responses = [[&quot;good&quot;,&quot;ok&quot;,&quot;good&quot;],[&quot;ok&quot;,&quot;bad&quot;],[&quot;bad&quot;,&quot;notsure&quot;],[&quot;great&quot;,&quot;good&quot;]]</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;bad&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>After removing duplicates within each list we have <code>responses = [[&quot;good&quot;, &quot;ok&quot;], [&quot;ok&quot;, &quot;bad&quot;], [&quot;bad&quot;, &quot;notsure&quot;], [&quot;great&quot;, &quot;good&quot;]]</code>.</li>
	<li><code>&quot;bad&quot;</code>, <code>&quot;good&quot;</code>, and <code>&quot;ok&quot;</code> each occur 2 times.</li>
	<li>The output is <code>&quot;bad&quot;</code> because it is the lexicographically smallest amongst the words with the highest frequency.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= responses.length &lt;= 1000</code></li>
	<li><code>1 &lt;= responses[i].length &lt;= 1000</code></li>
	<li><code>1 &lt;= responses[i][j].length &lt;= 10</code></li>
	<li><code>responses[i][j]</code> consists of only lowercase English letters</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python

```

#### Java

```java

```

#### C++

```cpp

```

#### Go

```go

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
