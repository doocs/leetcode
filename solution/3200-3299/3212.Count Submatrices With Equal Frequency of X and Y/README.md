---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3212.Count%20Submatrices%20With%20Equal%20Frequency%20of%20X%20and%20Y/README.md
---

<!-- problem:start -->

# [3212. 统计 X 和 Y 频数相等的子矩阵数量](https://leetcode.cn/problems/count-submatrices-with-equal-frequency-of-x-and-y)

[English Version](/solution/3200-3299/3212.Count%20Submatrices%20With%20Equal%20Frequency%20of%20X%20and%20Y/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个二维字符矩阵 <code>grid</code>，其中 <code>grid[i][j]</code> 可能是 <code>'X'</code>、<code>'Y'</code> 或 <code>'.'</code>，返回满足以下条件的<span data-keyword="submatrix">子矩阵</span>数量：</p>

<ul>
	<li>包含 <code>grid[0][0]</code></li>
	<li><code>'X'</code> 和 <code>'Y'</code> 的频数相等。</li>
	<li>至少包含一个 <code>'X'</code>。</li>
</ul>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">grid = [["X","Y","."],["Y",".","."]]</span></p>

<p><strong>输出：</strong> <span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3212.Count%20Submatrices%20With%20Equal%20Frequency%20of%20X%20and%20Y/images/examplems.png" style="padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; width: 175px; height: 350px;" /></strong></p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">grid = [["X","X"],["X","Y"]]</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>不存在满足 <code>'X'</code> 和 <code>'Y'</code> 频数相等的子矩阵。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">grid = [[".","."],[".","."]]</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>不存在满足至少包含一个 <code>'X'</code> 的子矩阵。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= grid.length, grid[i].length &lt;= 1000</code></li>
	<li><code>grid[i][j]</code> 可能是 <code>'X'</code>、<code>'Y'</code> 或 <code>'.'</code>.</li>
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
