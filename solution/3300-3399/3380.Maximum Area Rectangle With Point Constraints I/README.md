---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3380.Maximum%20Area%20Rectangle%20With%20Point%20Constraints%20I/README.md
---

<!-- problem:start -->

# [3380. 用点构造面积最大的矩形 I](https://leetcode.cn/problems/maximum-area-rectangle-with-point-constraints-i)

[English Version](/solution/3300-3399/3380.Maximum%20Area%20Rectangle%20With%20Point%20Constraints%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个数组 <code>points</code>，其中 <code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> 表示无限平面上一点的坐标。</p>

<p>你的任务是找出满足以下条件的矩形可能的&nbsp;<strong>最大&nbsp;</strong>面积：</p>

<ul>
	<li>矩形的四个顶点必须是数组中的&nbsp;<strong>四个&nbsp;</strong>点。</li>
	<li>矩形的内部或边界上&nbsp;<strong>不能&nbsp;</strong>包含任何其他点。</li>
	<li>矩形的边与坐标轴&nbsp;<strong>平行&nbsp;</strong>。</li>
</ul>

<p>返回可以获得的&nbsp;<strong>最大面积&nbsp;</strong>，如果无法形成这样的矩形，则返回 -1。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">points = [[1,1],[1,3],[3,1],[3,3]]</span></p>

<p><strong>输出：</strong>4</p>

<p><strong>解释：</strong></p>

<p><strong class="example"><img alt="示例 1 图示" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3380.Maximum%20Area%20Rectangle%20With%20Point%20Constraints%20I/images/example1.png" style="width: 229px; height: 228px;" /></strong></p>

<p>我们可以用这 4 个点作为顶点构成一个矩形，并且矩形内部或边界上没有其他点。因此，最大面积为 4 。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">points = [[1,1],[1,3],[3,1],[3,3],[2,2]]</span></p>

<p><strong>输出：</strong>-1</p>

<p><strong>解释：</strong></p>

<p><strong class="example"><img alt="示例 2 图示" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3380.Maximum%20Area%20Rectangle%20With%20Point%20Constraints%20I/images/example2.png" style="width: 229px; height: 228px;" /></strong></p>

<p>唯一一组可能构成矩形的点为 <code>[1,1], [1,3], [3,1]</code> 和 <code>[3,3]</code>，但点 <code>[2,2]</code> 总是位于矩形内部。因此，返回 -1 。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">points = [[1,1],[1,3],[3,1],[3,3],[1,2],[3,2]]</span></p>

<p><strong>输出：</strong>2</p>

<p><strong>解释：</strong></p>

<p><strong class="example"><img alt="示例 3 图示" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3380.Maximum%20Area%20Rectangle%20With%20Point%20Constraints%20I/images/example3.png" style="width: 229px; height: 228px;" /></strong></p>

<p>点 <code>[1,3], [1,2], [3,2], [3,3]</code>&nbsp;可以构成面积最大的矩形，面积为 2。此外，点 <code>[1,1], [1,2], [3,1], [3,2]</code> 也可以构成一个符合题目要求的矩形，面积相同。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= points.length &lt;= 10</code></li>
	<li><code>points[i].length == 2</code></li>
	<li><code>0 &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 100</code></li>
	<li>给定的所有点都是 <strong>唯一</strong> 的。</li>
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
