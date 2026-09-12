---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3656.Determine%20if%20a%20Simple%20Graph%20Exists/README.md
tags:
    - 图
    - 数组
    - 二分查找
    - 前缀和
    - 排序
---

<!-- problem:start -->

# [3656. 判断是否存在简单图 🔒](https://leetcode.cn/problems/determine-if-a-simple-graph-exists)

[English Version](/solution/3600-3699/3656.Determine%20if%20a%20Simple%20Graph%20Exists/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个整数数组&nbsp;<code>degrees</code>，其中&nbsp;<code>degrees[i]</code>&nbsp;表示第 <code>i</code> 个顶点的期望度数。</p>

<p>你的任务是确定是否存在一个&nbsp;<strong>恰好</strong> 具有这些顶点度数的 <strong>无向简单</strong> 图。</p>

<p>一个 <strong>简单</strong> 图没有自环或同一对顶点之间的平行边。</p>

<p>如果存在这样的图，返回&nbsp;<code>true</code>，否则返回&nbsp;<code>false</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>degrees = [3,1,2,2]</span></p>

<p><span class="example-io"><b>输出：</b>true</span></p>

<p><strong>解释：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3656.Determine%20if%20a%20Simple%20Graph%20Exists/images/screenshot-2025-08-13-at-24347-am.png" style="width: 200px; height: 132px;" /></p>

<p>一个可能的无向简单图是：</p>

<ul>
	<li>边：<code>(0, 1), (0, 2), (0, 3), (2, 3)</code></li>
	<li>度数：<code>deg(0) = 3</code>，<code>deg(1) = 1</code>，<code>deg(2) = 2</code>，<code>deg(3) = 2</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>degrees = [1,3,3,1]</span></p>

<p><span class="example-io"><b>输出：</b>false</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>degrees[1] = 3</code> 和&nbsp;<code>degrees[2] = 3</code> 意味着它们必须连接到所有其他顶点。</li>
	<li>这需要&nbsp;<code>degrees[0]</code> 和&nbsp;<code>degrees[3]</code>&nbsp;至少是 2，但它们都等于 1，这违反了需求。</li>
	<li>因此，答案是&nbsp;<code>false</code>。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == degrees.length &lt;= 10<sup>​​​​​​​5</sup></code></li>
	<li><code>0 &lt;= degrees[i] &lt;= n - 1</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 给定度数序列，判断是否存在简单无向图。Havel–Hakimi 反复将最大度 $d$ 与其后 $d$ 个顶点连边，但 $n\le 10^5$ 时每次排序过慢。
>
> Erdős–Gállai 定理把判定写成若干前缀不等式，再加度数和为偶数。排序后用前缀和在 $O(n)$ 内验证。
>
> 先检查 $\sum \deg$ 为偶数且每个度数不超过 $n-1$，再按定理比较第 $k$ 大度之和与右边的截断和。

<!-- thinking:end -->


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
