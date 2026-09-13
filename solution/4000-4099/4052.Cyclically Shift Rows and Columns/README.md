---
comments: true
difficulty: 简单
---

<!-- problem:start -->

# [4052. 行列循环移位](https://leetcode.cn/problems/cyclically-shift-rows-and-columns)

[English Version](/solution/4000-4099/4052.Cyclically%20Shift%20Rows%20and%20Columns/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数 <code>n</code>、一个大小为 <code>n x n</code> 的二维整数数组 <code>grid</code>，以及两个长度均为 <code>n</code> 的整数数组 <code>rowShift</code> 和 <code>colShift</code>，其中：</p>

<ul>
	<li><code>rowShift[i]</code> 表示将 <code>grid</code> 的第 <code>i</code>&nbsp;行向左<strong>&nbsp;循环移位</strong>&nbsp;的位数。</li>
	<li><code>colShift[j]</code> 表示将 <code>grid</code> 的第 <code>j</code>&nbsp;列向上&nbsp;<strong>循环移位&nbsp;</strong>的位数。</li>
</ul>

<p>首先按照 <code>rowShift</code> 对每一行进行循环移位，然后按照 <code>colShift</code> 对每一列进行循环移位。</p>

<p>返回完成所有移位操作后的网格。</p>

<p>将第 <code>i</code>&nbsp;行向左<strong>&nbsp;循环移位</strong> <code>k</code> 位时，只移动该行。原本位于第 <code>j</code> 列的元素会移动到第 <code>(j - k + n) % n</code> 列，其余各行保持不变。</p>

<p>将第 <code>j</code>&nbsp;列向上<strong>&nbsp;循环移位</strong> <code>k</code> 位时，只移动该列。原本位于第 <code>i</code> 行的元素会移动到第 <code>(i - k + n) % n</code> 行，其余各列保持不变。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 2, <code>grid</code> = [[1,2],[3,4]], rowShift = [1,0], colShift = [0,1]</span></p>

<p><strong>输出：</strong> <span class="example-io">[[2,4],[3,1]]</span></p>

<p><strong>解释：</strong></p>

<p><code>grid</code> 的变化过程如下：</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4000-4099/4052.Cyclically%20Shift%20Rows%20and%20Columns/images/4743-1.png" style="width: 760px; height: 92px;" /></p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 3, <code>grid</code> = [[1,2,3],[4,5,6],[7,8,9]], rowShift = [1,2,0], colShift = [2,2,1]</span></p>

<p><strong>输出：</strong> <span class="example-io">[[7,8,5],[2,3,9],[6,4,1]]</span></p>

<p><strong>解释：</strong></p>

<p><code>grid</code> 的变化过程如下：</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4000-4099/4052.Cyclically%20Shift%20Rows%20and%20Columns/images/4743-2.png" style="width: 750px; height: 349px;" /></p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == grid.length == grid[i].length &lt;= 10</code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= 100</code></li>
	<li><code>rowShift.length == colShift.length == n</code></li>
	<li><code>0 &lt;= rowShift[i], colShift[i] &lt; n</code></li>
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
