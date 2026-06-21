---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3963.Create%20Grid%20With%20Exactly%20One%20Path/README.md
---

<!-- problem:start -->

# [3963. 构造恰好一条路径的网格](https://leetcode.cn/problems/create-grid-with-exactly-one-path)

[English Version](/solution/3900-3999/3963.Create%20Grid%20With%20Exactly%20One%20Path/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个整数 <code>m</code> 和 <code>n</code>，分别表示网格的行数和列数。</p>

<p>请你构造 <strong>任意</strong> 一个只包含字符 <code>'.'</code> 和 <code>'#'</code> 的 <code>m x n</code> 网格，其中：</p>

<ul>
	<li><code>'.'</code> 表示空单元格。</li>
	<li><code>'#'</code> 表示障碍物单元格。</li>
</ul>

<p><strong>有效路径</strong> 是满足以下条件的空单元格序列：</p>

<ul>
	<li>从左上角单元格 <code>(0, 0)</code> 开始。</li>
	<li>在右下角单元格 <code>(m - 1, n - 1)</code> 结束。</li>
	<li>只能向：
	<ul>
		<li>右移动，从 <code>(i, j)</code> 到 <code>(i, j + 1)</code>，或者</li>
		<li>下移动，从 <code>(i, j)</code> 到 <code>(i + 1, j)</code>。</li>
	</ul>
	</li>
</ul>

<p>返回任意一个从左上角到右下角 <strong>恰好只有一条有效路径</strong> 的网格。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">m = 2, n = 3</span></p>

<p><strong>输出：</strong> <span class="example-io">["..#","#.."]</span></p>

<p><strong>解释：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3900-3999/3963.Create%20Grid%20With%20Exactly%20One%20Path/images/screenshot-2026-05-26-at-61005pm.png" style="width: 200px; height: 95px;" /></p>

<p>唯一的有效路径是：<code>(0,0) → (0,1) → (1,1) → (1,2)</code></p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">m = 3, n = 3</span></p>

<p><strong>输出：</strong> <span class="example-io">["..#","#..","##."]</span></p>

<p><strong>解释：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3900-3999/3963.Create%20Grid%20With%20Exactly%20One%20Path/images/screenshot-2026-05-26-at-61129pm.png" style="width: 220px; height: 150px;" /></p>

<p>唯一的有效路径是：<code>(0,0) → (0,1) → (1,1) → (1,2) → (2,2)</code></p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">m = 1, n = 4</span></p>

<p><strong>输出：</strong> <span class="example-io">["...."]</span></p>

<p><strong>解释：</strong></p>

<p>唯一的有效路径是：<code>(0,0) → (0,1) → (0,2) → (0,3)</code></p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= m, n &lt;= 25</code></li>
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
