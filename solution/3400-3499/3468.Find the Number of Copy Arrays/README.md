---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3468.Find%20the%20Number%20of%20Copy%20Arrays/README.md
tags:
    - 数组
    - 数学
---

<!-- problem:start -->

# [3468. 可行数组的数目](https://leetcode.cn/problems/find-the-number-of-copy-arrays)

[English Version](/solution/3400-3499/3468.Find%20the%20Number%20of%20Copy%20Arrays/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code> 的数组 <code>original</code> 和一个长度为 <code>n x 2</code> 的二维数组 <code>bounds</code>，其中 <code>bounds[i] = [u<sub>i</sub>, v<sub>i</sub>]</code>。</p>

<p>你需要找到长度为 <code>n</code>&nbsp;且满足以下条件的&nbsp;<strong>可能的&nbsp;</strong>数组 <code>copy</code> 的数量：</p>

<ol>
	<li>对于 <code>1 &lt;= i &lt;= n - 1</code>&nbsp;，都有&nbsp;<code>(copy[i] - copy[i - 1]) == (original[i] - original[i - 1])</code>&nbsp;。</li>
	<li>对于 <code>0 &lt;= i &lt;= n - 1</code>&nbsp;，都有&nbsp;<code>u<sub>i</sub> &lt;= copy[i] &lt;= v<sub>i</sub></code><sub>&nbsp;</sub>。</li>
</ol>

<p>返回满足这些条件的数组数目。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">original = [1,2,3,4], bounds = [[1,2],[2,3],[3,4],[4,5]]</span></p>

<p><strong>输出：</strong><span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p>可能的数组为：</p>

<ul>
	<li><code>[1, 2, 3, 4]</code></li>
	<li><code>[2, 3, 4, 5]</code></li>
</ul>
</div>

<p><strong class="example">示例 2</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">original = [1,2,3,4], bounds = [[1,10],[2,9],[3,8],[4,7]]</span></p>

<p><strong>输出：</strong><span class="example-io">4</span></p>

<p><strong>解释：</strong></p>

<p>可能的数组为：</p>

<ul>
	<li><code>[1, 2, 3, 4]</code></li>
	<li><code>[2, 3, 4, 5]</code></li>
	<li><code>[3, 4, 5, 6]</code></li>
	<li><code>[4, 5, 6, 7]</code></li>
</ul>
</div>

<p><strong class="example">示例 3</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">original = [1,2,1,2], bounds = [[1,1],[2,3],[3,3],[2,3]]</span></p>

<p><strong>输出：</strong><span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>没有可行的数组。</p>
</div>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>2 &lt;= n == original.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= original[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>bounds.length == n</code></li>
	<li><code>bounds[i].length == 2</code></li>
	<li><code>1 &lt;= bounds[i][0] &lt;= bounds[i][1] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

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
