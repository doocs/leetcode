---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4036.Lexicographically%20Largest%20String%20After%20Pair%20Transformations/README_EN.md
---

<!-- problem:start -->

# [4036. Lexicographically Largest String After Pair Transformations](https://leetcode.com/problems/lexicographically-largest-string-after-pair-transformations)

[中文文档](/solution/4000-4099/4036.Lexicographically%20Largest%20String%20After%20Pair%20Transformations/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>For each integer <code>x</code> in <code>nums</code>, start with a string consisting of exactly <code>x</code> lowercase <code>&#39;a&#39;</code> characters.</p>

<p>You may perform the following operation any number of times (including zero):</p>

<ul>
	<li>Choose two <strong>adjacent equal</strong> letters and replace them with the next letter in the alphabet.</li>
</ul>

<p>For example, <code>&quot;aa&quot;</code> can be replaced with <code>&quot;b&quot;</code>, and <code>&quot;bb&quot;</code> can be replaced with <code>&quot;c&quot;</code>. The pair <code>&quot;zz&quot;</code> cannot be replaced.</p>

<p>For each <code>x</code>, determine the <strong>lexicographically largest</strong> string that can be obtained.</p>

<p>Return an array of strings where the <code>i<sup>th</sup></code> string is the answer for <code>nums[i]</code>.</p>

<p>A string <code>a</code> is <strong>lexicographically larger</strong> than a string <code>b</code> if, at the first position where they differ, <code>a</code> contains a letter that appears later in the alphabet than the corresponding letter in <code>b</code>. If the first <code>min(a.length, b.length)</code> characters are equal, the longer string is lexicographically larger.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,5,7]</span></p>

<p><strong>Output:</strong> <span class="example-io">[&quot;b&quot;,&quot;ca&quot;,&quot;cba&quot;]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>nums[0] = 2</code>: <code>&quot;aa&quot;</code> &rarr; <code>&quot;b&quot;</code>.</li>
	<li><code>nums[1] = 5</code>: <code>&quot;aaaaa&quot;</code> &rarr; <code>&quot;baaa&quot;</code> &rarr; <code>&quot;bba&quot;</code> &rarr; <code>&quot;ca&quot;</code>.</li>
	<li><code>nums[2] = 7</code>: <code>&quot;aaaaaaa&quot;</code> &rarr; <code>&quot;baaaaa&quot;</code> &rarr; <code>&quot;bbaaa&quot;</code> &rarr; <code>&quot;bbba&quot;</code> &rarr; <code>&quot;cba&quot;</code>.</li>
	<li>Therefore, <code>ans = [&quot;b&quot;, &quot;ca&quot;, &quot;cba&quot;]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,9,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">[&quot;ba&quot;,&quot;da&quot;,&quot;a&quot;]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>nums[0] = 3</code>: <code>&quot;aaa&quot;</code> &rarr; <code>&quot;ba&quot;</code>.</li>
	<li><code>nums[1] = 9</code>: <code>&quot;aaaaaaaaa&quot;</code> &rarr; <code>&quot;baaaaaaa&quot;</code> &rarr; <code>&quot;bbaaaaa&quot;</code> &rarr; <code>&quot;bbbaaa&quot;</code> &rarr; <code>&quot;bbbba&quot;</code> &rarr; <code>&quot;cbba&quot;</code> &rarr; <code>&quot;cca&quot;</code> &rarr; <code>&quot;da&quot;</code>.</li>
	<li><code>nums[2] = 1</code>: No transformation can be applied, so the result is <code>&quot;a&quot;</code>.</li>
	<li>Therefore, <code>ans = [&quot;ba&quot;, &quot;da&quot;, &quot;a&quot;]</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>8</sup></code></li>
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
