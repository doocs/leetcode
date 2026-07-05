---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3981.Count%20Distinct%20Ways%20to%20Form%20Target%20from%20Two%20Strings/README_EN.md
---

<!-- problem:start -->

# [3981. Count Distinct Ways to Form Target from Two Strings](https://leetcode.com/problems/count-distinct-ways-to-form-target-from-two-strings)

[中文文档](/solution/3900-3999/3981.Count%20Distinct%20Ways%20to%20Form%20Target%20from%20Two%20Strings/README.md)

## Description

<!-- description:start -->

<p>You are given three strings <code>word1</code>, <code>word2</code>, and <code>target</code>.</p>

<p>Your task is to count the number of ways to form <code>target</code> by choosing characters from <code>word1</code> and <code>word2</code> under the following conditions:</p>

<ul>
	<li>For each character of <code>target</code>, choose one matching character from either <code>word1</code> or <code>word2</code>.</li>
	<li>The chosen indices from <code>word1</code> must be <strong>strictly</strong> increasing.</li>
	<li>The chosen indices from <code>word2</code> must be <strong>strictly</strong> increasing.</li>
	<li><strong>At least</strong> one character must be chosen from <strong>both</strong> <code>word1</code> and <code>word2</code>.</li>
</ul>

<p>Two ways are considered different if, for <strong>at least</strong> one position in <code>target</code>, the chosen character comes from a different string or a different index.</p>

<p>Return the number of ways. Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">word1 = &quot;abc&quot;, word2 = &quot;bac&quot;, target = &quot;abc&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p>There are 5 ways to form <code>target</code>:</p>

<ul>
	<li><code>word1[0] = &#39;a&#39;</code>, <code>word1[1] = &#39;b&#39;</code>, <code>word2[2] = &#39;c&#39;</code></li>
	<li><code>word1[0] = &#39;a&#39;</code>, <code>word2[0] = &#39;b&#39;</code>, <code>word1[2] = &#39;c&#39;</code></li>
	<li><code>word1[0] = &#39;a&#39;</code>, <code>word2[0] = &#39;b&#39;</code>, <code>word2[2] = &#39;c&#39;</code></li>
	<li><code>word2[1] = &#39;a&#39;</code>, <code>word1[1] = &#39;b&#39;</code>, <code>word1[2] = &#39;c&#39;</code></li>
	<li><code>word2[1] = &#39;a&#39;</code>, <code>word1[1] = &#39;b&#39;</code>, <code>word2[2] = &#39;c&#39;</code></li>
</ul>

<p>All ways preserve the increasing index order inside each string and choose at least one character from each string.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">word1 = &quot;cd&quot;, word2 = &quot;cd&quot;, target = &quot;ccd&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>There are 4 ways to form <code>target</code>:</p>

<ul>
	<li><code>word1[0] = &#39;c&#39;</code>, <code>word2[0] = &#39;c&#39;</code>, <code>word1[1] = &#39;d&#39;</code></li>
	<li><code>word1[0] = &#39;c&#39;</code>, <code>word2[0] = &#39;c&#39;</code>, <code>word2[1] = &#39;d&#39;</code></li>
	<li><code>word2[0] = &#39;c&#39;</code>, <code>word1[0] = &#39;c&#39;</code>, <code>word1[1] = &#39;d&#39;</code></li>
	<li><code>word2[0] = &#39;c&#39;</code>, <code>word1[0] = &#39;c&#39;</code>, <code>word2[1] = &#39;d&#39;</code></li>
</ul>

<p>The first two <code>&#39;c&#39;</code> characters in <code>target</code> must come one from each string. The final <code>&#39;d&#39;</code> can be chosen from either string.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">word1 = &quot;xy&quot;, word2 = &quot;xy&quot;, target = &quot;xyxy&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>There are 2 ways to form <code>target</code>:</p>

<ul>
	<li><code>word1[0] = &#39;x&#39;</code>, <code>word1[1] = &#39;y&#39;</code>, <code>word2[0] = &#39;x&#39;</code>, <code>word2[1] = &#39;y&#39;</code></li>
	<li><code>word2[0] = &#39;x&#39;</code>, <code>word2[1] = &#39;y&#39;</code>, <code>word1[0] = &#39;x&#39;</code>, <code>word1[1] = &#39;y&#39;</code></li>
</ul>

<p>Each <code>&quot;xy&quot;</code> part in <code>target</code> comes entirely from one string.</p>
</div>

<p><strong class="example">Example 4:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">word1 = &quot;ab&quot;, word2 = &quot;cde&quot;, target = &quot;ace&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>The only way is to choose <code>word1[0] = &#39;a&#39;</code>, <code>word2[0] = &#39;c&#39;</code>, and <code>word2[2] = &#39;e&#39;</code>. Thus, the answer is 1.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word1.length, word2.length, target.length &lt;= 100</code></li>
	<li><code>word1</code>, <code>word2</code>, and <code>target</code> consist of lowercase English letters only.</li>
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
