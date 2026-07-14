---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3990.Create%20Grid%20With%20Exactly%20K%20Paths%20II/README.md
---

<!-- problem:start -->

# [3990. 创建一个恰好有 K 条路径的网格图 II 🔒](https://leetcode.cn/problems/create-grid-with-exactly-k-paths-ii)

[English Version](/solution/3900-3999/3990.Create%20Grid%20With%20Exactly%20K%20Paths%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个整数&nbsp;<code>k</code>。</p>

<p>构建一个仅由字符 <code>'.'</code> 和 <code>'#'</code> 组成的网格，其中：</p>

<ul>
	<li><code>'.'</code> 表示一个空闲单元格。</li>
	<li><code>'#'</code> 表示一个障碍单元格。</li>
</ul>

<p>网格 <strong>最多</strong> 包含 25 行和 25 列。</p>

<p><strong>有效路径</strong> 是一系列空闲单元格，满足：</p>

<ul>
	<li>从左上角单元格 <code>(0, 0)</code> 开始。</li>
	<li>终点位于右下角单元格 <code>(m - 1, n - 1)</code>，其中 <code>m</code> 和 <code>n</code> 是你构建的网格的尺寸。</li>
	<li>移动方式只允许：
	<ul>
		<li>向右, 从&nbsp;<code>(i, j)</code> 到&nbsp;<code>(i, j + 1)</code>，或</li>
		<li>向下，从&nbsp;<code>(i, j)</code> 到&nbsp;<code>(i + 1, j)</code>。</li>
	</ul>
	</li>
</ul>

<p>返回任意一个网格，使得从左上角单元格到右下角单元格 <strong>恰好有 <code>k</code> 条有效路径</strong>。如果不存在这样的网格，则返回一个空数组。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>k = 2</span></p>

<p><span class="example-io"><b>输出：</b>["..#","#..","#.."]</span></p>

<p><strong>解释：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3900-3999/3990.Create%20Grid%20With%20Exactly%20K%20Paths%20II/images/screenshot-2026-05-31-at-82224pm.png" style="width: 200px; height: 135px;" /></p>

<p>网格中恰好有两条从 <code>(0, 0)</code> 到 <code>(2, 2)</code> 的有效路径：</p>

<ul>
	<li><code>(0, 0) → (0, 1) → (1, 1) → (1, 2) → (2, 2)</code></li>
	<li><code>(0, 0) → (0, 1) → (1, 1) → (2, 1) → (2, 2)</code></li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>k = 3</span></p>

<p><span class="example-io"><b>输出：</b>["...","#..","#.."]</span></p>

<p><strong>解释：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3900-3999/3990.Create%20Grid%20With%20Exactly%20K%20Paths%20II/images/screenshot-2026-05-31-at-82251pm.png" style="width: 200px; height: 128px;" /></p>

<p>网格中恰好有 3 条从 <code>(0, 0)</code> 到 <code>(2, 2)</code> 的有效路径：</p>

<ul>
	<li><code>(0, 0) → (0, 1) → (0, 2) → (1, 2) → (2, 2)</code></li>
	<li><code>(0, 0) → (0, 1) → (1, 1) → (1, 2) → (2, 2)</code></li>
	<li><code>(0, 0) → (0, 1) → (1, 1) → (2, 1) → (2, 2)</code></li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong>​​​​​​​</p>

<ul>
	<li><code>1 &lt;= k &lt;= 1000</code></li>
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
