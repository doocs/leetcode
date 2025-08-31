---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3668.Restore%20Finishing%20Order/README.md
---

<!-- problem:start -->

# [3668. 重排完成顺序](https://leetcode.cn/problems/restore-finishing-order)

[English Version](/solution/3600-3699/3668.Restore%20Finishing%20Order/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code> 的整数数组 <code>order</code> 和一个整数数组 <code>friends</code>。</p>

<ul>
	<li><code>order</code> 包含从 1 到 <code>n</code> 的每个整数，且&nbsp;<strong>恰好出现一次&nbsp;</strong>，表示比赛中参赛者按照&nbsp;<strong>完成顺序&nbsp;</strong>的 ID。</li>
	<li><code>friends</code> 包含你朋友们的 ID，按照&nbsp;<strong>严格递增&nbsp;</strong>的顺序排列。<code>friends</code> 中的每个 ID 都保证出现在 <code>order</code> 数组中。</li>
</ul>

<p>请返回一个数组，包含你朋友们的 ID，按照他们的&nbsp;<strong>完成顺序&nbsp;</strong>排列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">order = [3,1,2,5,4], friends = [1,3,4]</span></p>

<p><strong>输出：</strong><span class="example-io">[3,1,4]</span></p>

<p><strong>解释：</strong></p>

<p>完成顺序是 <code>[<u><strong>3</strong></u>, <u><strong>1</strong></u>, 2, 5, <u><strong>4</strong></u>]</code>。因此，你朋友的完成顺序是 <code>[3, 1, 4]</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">order = [1,4,5,3,2], friends = [2,5]</span></p>

<p><strong>输出：</strong><span class="example-io">[5,2]</span></p>

<p><strong>解释：</strong></p>

<p>完成顺序是 <code>[1, 4, <u><strong>5</strong></u>, 3, <u><strong>2</strong></u>]</code>。因此，你朋友的完成顺序是 <code>[5, 2]</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == order.length &lt;= 100</code></li>
	<li><code>order</code> 包含从 1 到 <code>n</code> 的每个整数，且恰好出现一次</li>
	<li><code>1 &lt;= friends.length &lt;= min(8, n)</code></li>
	<li><code>1 &lt;= friends[i] &lt;= n</code></li>
	<li><code>friends</code> 是严格递增的</li>
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
