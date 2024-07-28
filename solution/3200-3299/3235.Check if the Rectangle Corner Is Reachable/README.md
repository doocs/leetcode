---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3235.Check%20if%20the%20Rectangle%20Corner%20Is%20Reachable/README.md
---

<!-- problem:start -->

# [3235. 判断矩形的两个角落是否可达](https://leetcode.cn/problems/check-if-the-rectangle-corner-is-reachable)

[English Version](/solution/3200-3299/3235.Check%20if%20the%20Rectangle%20Corner%20Is%20Reachable/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个正整数&nbsp;<code>X</code> 和&nbsp;<code>Y</code>&nbsp;和一个二维整数数组&nbsp;<code>circles</code>&nbsp;，其中&nbsp;<code>circles[i] = [x<sub>i</sub>, y<sub>i</sub>, r<sub>i</sub>]</code>&nbsp;表示一个圆心在&nbsp;<code>(x<sub>i</sub>, y<sub>i</sub>)</code>&nbsp;半径为&nbsp;<code>r<sub>i</sub></code>&nbsp;的圆。</p>

<p>坐标平面内有一个左下角在原点，右上角在&nbsp;<code>(X, Y)</code>&nbsp;的矩形。你需要判断是否存在一条从左下角到右上角的路径满足：路径&nbsp;<strong>完全</strong>&nbsp;在矩形内部，<strong>不会</strong>&nbsp;触碰或者经过 <strong>任何</strong>&nbsp;圆的内部和边界，同时&nbsp;<strong>只</strong> 在起点和终点接触到矩形。</p>

<p>如果存在这样的路径，请你返回&nbsp;<code>true</code>&nbsp;，否则返回&nbsp;<code>false</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>X = 3, Y = 4, circles = [[2,1,1]]</span></p>

<p><span class="example-io"><b>输出：</b>true</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3235.Check%20if%20the%20Rectangle%20Corner%20Is%20Reachable/images/example2circle1.png" style="width: 346px; height: 264px;" /></p>

<p>黑色曲线表示一条从&nbsp;<code>(0, 0)</code>&nbsp;到&nbsp;<code>(3, 4)</code>&nbsp;的路径。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>X = 3, Y = 3, circles = [[1,1,2]]</span></p>

<p><span class="example-io"><b>输出：</b>false</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3235.Check%20if%20the%20Rectangle%20Corner%20Is%20Reachable/images/example1circle.png" style="width: 346px; height: 264px;" /></p>

<p>不存在从&nbsp;<code>(0, 0)</code> 到&nbsp;<code>(3, 3)</code>&nbsp;的路径。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>X = 3, Y = 3, circles = [[2,1,1],[1,2,1]]</span></p>

<p><span class="example-io"><b>输出：</b>false</span></p>

<p><b>解释：</b></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3235.Check%20if%20the%20Rectangle%20Corner%20Is%20Reachable/images/example0circle.png" style="width: 346px; height: 264px;" /></p>

<p>不存在从&nbsp;<code>(0, 0)</code>&nbsp;到&nbsp;<code>(3, 3)</code>&nbsp;的路径。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= X, Y &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= circles.length &lt;= 1000</code></li>
	<li><code>circles[i].length == 3</code></li>
	<li><code>1 &lt;= x<sub>i</sub>, y<sub>i</sub>, r<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
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
