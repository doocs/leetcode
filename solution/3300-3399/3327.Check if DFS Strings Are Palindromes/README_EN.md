---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3327.Check%20if%20DFS%20Strings%20Are%20Palindromes/README_EN.md
---

<!-- problem:start -->

# [3327. Check if DFS Strings Are Palindromes](https://leetcode.com/problems/check-if-dfs-strings-are-palindromes)

[中文文档](/solution/3300-3399/3327.Check%20if%20DFS%20Strings%20Are%20Palindromes/README.md)

## Description

<!-- description:start -->

<p>You are given a tree rooted at node 0, consisting of <code>n</code> nodes numbered from <code>0</code> to <code>n - 1</code>. The tree is represented by an array <code>parent</code> of size <code>n</code>, where <code>parent[i]</code> is the parent of node <code>i</code>. Since node 0 is the root, <code>parent[0] == -1</code>.</p>

<p>You are also given a string <code>s</code> of length <code>n</code>, where <code>s[i]</code> is the character assigned to node <code>i</code>.</p>

<p>Consider an empty string <code>dfsStr</code>, and define a recursive function <code>dfs(int x)</code> that takes a node <code>x</code> as a parameter and performs the following steps in order:</p>

<ul>
	<li>Iterate over each child <code>y</code> of <code>x</code> <strong>in increasing order of their numbers</strong>, and call <code>dfs(y)</code>.</li>
	<li>Add the character <code>s[x]</code> to the end of the string <code>dfsStr</code>.</li>
</ul>

<p><strong>Note</strong> that <code>dfsStr</code> is shared across all recursive calls of <code>dfs</code>.</p>

<p>You need to find a boolean array <code>answer</code> of size <code>n</code>, where for each index <code>i</code> from <code>0</code> to <code>n - 1</code>, you do the following:</p>

<ul>
	<li>Empty the string <code>dfsStr</code> and call <code>dfs(i)</code>.</li>
	<li>If the resulting string <code>dfsStr</code> is a <strong>palindrome</strong>, then set <code>answer[i]</code> to <code>true</code>. Otherwise, set <code>answer[i]</code> to <code>false</code>.</li>
</ul>

<p>Return the array <code>answer</code>.</p>

<p>A <strong>palindrome</strong> is a string that reads the same forward and backward.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3327.Check%20if%20DFS%20Strings%20Are%20Palindromes/images/tree1drawio.png" style="width: 240px; height: 256px;" />
<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">parent = [-1,0,0,1,1,2], s = &quot;aababa&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">[true,true,false,true,true,true]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Calling <code>dfs(0)</code> results in the string <code>dfsStr = &quot;abaaba&quot;</code>, which is a palindrome.</li>
	<li>Calling <code>dfs(1)</code> results in the string <code>dfsStr = &quot;aba&quot;</code>, which is a palindrome.</li>
	<li>Calling <code>dfs(2)</code> results in the string <code>dfsStr = &quot;ab&quot;</code>, which is <strong>not</strong> a palindrome.</li>
	<li>Calling <code>dfs(3)</code> results in the string <code>dfsStr = &quot;a&quot;</code>, which is a palindrome.</li>
	<li>Calling <code>dfs(4)</code> results in the string <code>dfsStr = &quot;b&quot;</code>, which is a palindrome.</li>
	<li>Calling <code>dfs(5)</code> results in the string <code>dfsStr = &quot;a&quot;</code>, which is a palindrome.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3327.Check%20if%20DFS%20Strings%20Are%20Palindromes/images/tree2drawio-1.png" style="width: 260px; height: 167px;" />
<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">parent = [-1,0,0,0,0], s = &quot;aabcb&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">[true,true,true,true,true]</span></p>

<p><strong>Explanation:</strong></p>

<p>Every call on <code>dfs(x)</code> results in a palindrome string.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == parent.length == s.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= parent[i] &lt;= n - 1</code> for all <code>i &gt;= 1</code>.</li>
	<li><code>parent[0] == -1</code></li>
	<li><code>parent</code> represents a valid tree.</li>
	<li><code>s</code> consists only of lowercase English letters.</li>
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
