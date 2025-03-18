---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3485.Longest%20Common%20Prefix%20of%20K%20Strings%20After%20Removal/README_EN.md
tags:
    - Trie
    - Array
    - String
---

<!-- problem:start -->

# [3485. Longest Common Prefix of K Strings After Removal](https://leetcode.com/problems/longest-common-prefix-of-k-strings-after-removal)

[中文文档](/solution/3400-3499/3485.Longest%20Common%20Prefix%20of%20K%20Strings%20After%20Removal/README.md)

## Description

<!-- description:start -->

<p>You are given an array of strings <code>words</code> and an integer <code>k</code>.</p>

<p>For each index <code>i</code> in the range <code>[0, words.length - 1]</code>, find the <strong>length</strong> of the <strong>longest common <span data-keyword="string-prefix">prefix</span></strong> among any <code>k</code> strings (selected at <strong>distinct indices</strong>) from the remaining array after removing the <code>i<sup>th</sup></code> element.</p>

<p>Return an array <code>answer</code>, where <code>answer[i]</code> is the answer for <code>i<sup>th</sup></code> element. If removing the <code>i<sup>th</sup></code> element leaves the array with fewer than <code>k</code> strings, <code>answer[i]</code> is 0.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">words = [&quot;jump&quot;,&quot;run&quot;,&quot;run&quot;,&quot;jump&quot;,&quot;run&quot;], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">[3,4,4,3,4]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Removing index 0 (<code>&quot;jump&quot;</code>):

    <ul>
    	<li><code>words</code> becomes: <code>[&quot;run&quot;, &quot;run&quot;, &quot;jump&quot;, &quot;run&quot;]</code>. <code>&quot;run&quot;</code> occurs 3 times. Choosing any two gives the longest common prefix <code>&quot;run&quot;</code> (length 3).</li>
    </ul>
    </li>
    <li>Removing index 1 (<code>&quot;run&quot;</code>):
    <ul>
    	<li><code>words</code> becomes: <code>[&quot;jump&quot;, &quot;run&quot;, &quot;jump&quot;, &quot;run&quot;]</code>. <code>&quot;jump&quot;</code> occurs twice. Choosing these two gives the longest common prefix <code>&quot;jump&quot;</code> (length 4).</li>
    </ul>
    </li>
    <li>Removing index 2 (<code>&quot;run&quot;</code>):
    <ul>
    	<li><code>words</code> becomes: <code>[&quot;jump&quot;, &quot;run&quot;, &quot;jump&quot;, &quot;run&quot;]</code>. <code>&quot;jump&quot;</code> occurs twice. Choosing these two gives the longest common prefix <code>&quot;jump&quot;</code> (length 4).</li>
    </ul>
    </li>
    <li>Removing index 3 (<code>&quot;jump&quot;</code>):
    <ul>
    	<li><code>words</code> becomes: <code>[&quot;jump&quot;, &quot;run&quot;, &quot;run&quot;, &quot;run&quot;]</code>. <code>&quot;run&quot;</code> occurs 3 times. Choosing any two gives the longest common prefix <code>&quot;run&quot;</code> (length 3).</li>
    </ul>
    </li>
    <li>Removing index 4 (&quot;run&quot;):
    <ul>
    	<li><code>words</code> becomes: <code>[&quot;jump&quot;, &quot;run&quot;, &quot;run&quot;, &quot;jump&quot;]</code>. <code>&quot;jump&quot;</code> occurs twice. Choosing these two gives the longest common prefix <code>&quot;jump&quot;</code> (length 4).</li>
    </ul>
    </li>

</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">words = [&quot;dog&quot;,&quot;racer&quot;,&quot;car&quot;], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">[0,0,0]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Removing any index results in an answer of 0.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= words.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= words[i].length &lt;= 10<sup>4</sup></code></li>
	<li><code>words[i]</code> consists of lowercase English letters.</li>
	<li>The sum of <code>words[i].length</code> is smaller than or equal <code>10<sup>5</sup></code>.</li>
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
