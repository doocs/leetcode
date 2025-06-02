---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3567.Minimum%20Absolute%20Difference%20in%20Sliding%20Submatrix/README.md
---

<!-- problem:start -->

# [3567. 子矩阵的最小绝对差](https://leetcode.cn/problems/minimum-absolute-difference-in-sliding-submatrix)

[English Version](/solution/3500-3599/3567.Minimum%20Absolute%20Difference%20in%20Sliding%20Submatrix/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个 <code>m x n</code> 的整数矩阵 <code>grid</code> 和一个整数 <code>k</code>。</p>

<p>对于矩阵 <code>grid</code> 中的每个连续的 <code>k x k</code> <strong>子矩阵</strong>，计算其中任意两个&nbsp;<strong>不同</strong>值 之间的&nbsp;<strong>最小绝对差&nbsp;</strong>。</p>

<p>返回一个大小为 <code>(m - k + 1) x (n - k + 1)</code> 的二维数组 <code>ans</code>，其中 <code>ans[i][j]</code> 表示以 <code>grid</code> 中坐标 <code>(i, j)</code> 为左上角的子矩阵的最小绝对差。</p>

<p><strong>注意</strong>：如果子矩阵中的所有元素都相同，则答案为 0。</p>

<p>子矩阵 <code>(x1, y1, x2, y2)</code> 是一个由选择矩阵中所有满足 <code>x1 &lt;= x &lt;= x2</code> 且 <code>y1 &lt;= y &lt;= y2</code> 的单元格 <code>matrix[x][y]</code> 组成的矩阵。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">grid = [[1,8],[3,-2]], k = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">[[2]]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>只有一个可能的 <code>k x k</code> 子矩阵：<code><span class="example-io">[[1, 8], [3, -2]]</span></code>。</li>
	<li>子矩阵中的不同值为 <code>[1, 8, 3, -2]</code>。</li>
	<li>子矩阵中的最小绝对差为 <code>|1 - 3| = 2</code>。因此，答案为 <code>[[2]]</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">grid = [[3,-1]], k = 1</span></p>

<p><strong>输出：</strong> <span class="example-io">[[0,0]]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>每个 <code>k x k</code> 子矩阵中只有一个不同的元素。</li>
	<li>因此，答案为 <code>[[0, 0]]</code>。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">grid = [[1,-2,3],[2,3,5]], k = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">[[1,2]]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>有两个可能的 <code>k × k</code> 子矩阵：

    <ul>
    	<li>以 <code>(0, 0)</code> 为起点的子矩阵：<code>[[1, -2], [2, 3]]</code>。

    	<ul>
    		<li>子矩阵中的不同值为 <code>[1, -2, 2, 3]</code>。</li>
    		<li>子矩阵中的最小绝对差为 <code>|1 - 2| = 1</code>。</li>
    	</ul>
    	</li>
    	<li>以 <code>(0, 1)</code> 为起点的子矩阵：<code>[[-2, 3], [3, 5]]</code>。
    	<ul>
    		<li>子矩阵中的不同值为 <code>[-2, 3, 5]</code>。</li>
    		<li>子矩阵中的最小绝对差为 <code>|3 - 5| = 2</code>。</li>
    	</ul>
    	</li>
    </ul>
    </li>
    <li>因此，答案为 <code>[[1, 2]]</code>。</li>

</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= m == grid.length &lt;= 30</code></li>
	<li><code>1 &lt;= n == grid[i].length &lt;= 30</code></li>
	<li><code>-10<sup>5</sup> &lt;= grid[i][j] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= min(m, n)</code></li>
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
