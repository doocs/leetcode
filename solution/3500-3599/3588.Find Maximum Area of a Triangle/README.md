---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3588.Find%20Maximum%20Area%20of%20a%20Triangle/README.md
rating: 1818
source: 第 159 场双周赛 Q2
tags:
    - 贪心
    - 几何
    - 数组
    - 哈希表
    - 数学
    - 枚举
---

<!-- problem:start -->

# [3588. 找到最大三角形面积](https://leetcode.cn/problems/find-maximum-area-of-a-triangle)

[English Version](/solution/3500-3599/3588.Find%20Maximum%20Area%20of%20a%20Triangle/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个二维数组 <code>coords</code>，大小为 <code>n x 2</code>，表示一个无限笛卡尔平面上 <code>n</code> 个点的坐标。</p>

<p>找出一个 <strong>最大</strong>&nbsp;三角形的 <strong>两倍&nbsp;</strong>面积，其中三角形的三个顶点来自 <code>coords</code> 中的任意三个点，并且该三角形至少有一条边与 x 轴或 y 轴平行。严格地说，如果该三角形的最大面积为 <code>A</code>，则返回 <code>2 * A</code>。</p>

<p>如果不存在这样的三角形，返回 -1。</p>

<p><strong>注意</strong>，三角形的面积 <strong>不能</strong> 为零。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">coords = [[1,1],[1,2],[3,2],[3,3]]</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3588.Find%20Maximum%20Area%20of%20a%20Triangle/images/image-20250420010047-1.png" style="width: 300px; height: 289px;" /></p>

<p>图中的三角形的底边为 1，高为 2。因此，它的面积为 <code>1/2 * 底边 * 高 = 1</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">coords = [[1,1],[2,2],[3,3]]</span></p>

<p><strong>输出：</strong> <span class="example-io">-1</span></p>

<p><strong>解释：</strong></p>

<p>唯一可能的三角形的顶点是 <code>(1, 1)</code>、<code>(2, 2)</code> 和 <code>(3, 3)</code>。它的任意边都不与 x 轴或 y 轴平行。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == coords.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= coords[i][0], coords[i][1] &lt;= 10<sup>6</sup></code></li>
	<li>所有 <code>coords[i]</code> 都是 <strong>唯一</strong> 的。</li>
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
