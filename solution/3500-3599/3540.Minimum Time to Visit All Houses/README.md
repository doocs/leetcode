---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3540.Minimum%20Time%20to%20Visit%20All%20Houses/README.md
tags:
    - 数组
    - 前缀和
---

<!-- problem:start -->

# [3540. 访问所有房屋的最短时间 🔒](https://leetcode.cn/problems/minimum-time-to-visit-all-houses)

[English Version](/solution/3500-3599/3540.Minimum%20Time%20to%20Visit%20All%20Houses/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定两个整数数组&nbsp;<code>forward</code> 和&nbsp;<code>backward</code>，长度都为&nbsp;<code>n</code>。同时给定另一个整数数组&nbsp;<code>queries</code>。</p>

<p>有&nbsp;<code>n</code>&nbsp;个排列为环形的房屋。房屋通过道路以特殊方式相连：</p>

<ul>
	<li>对于所有的&nbsp;<code>0 &lt;= i &lt;= n - 2</code>，房屋&nbsp;<code>i</code>&nbsp;通过一条长度为&nbsp;<code>forward[i]</code>&nbsp;米的道路连接到房屋&nbsp;<code>i + 1</code>。另外，房屋&nbsp;<code>n - 1</code>&nbsp;通过一条长度为&nbsp;<code>forward[n - 1]</code>&nbsp;米的道路连接回房屋 0，形成一个环。</li>
	<li>对于所有的 <code>1 &lt;= i &lt;= n - 1</code>，房屋&nbsp;<code>i</code>&nbsp;通过一条长度为&nbsp;<code>backward[i]</code>&nbsp;米的道路连接到房屋&nbsp;<code>i - 1</code>。另外，房屋&nbsp;0 通过一条长度为&nbsp;<code>backward[n - 1]</code>&nbsp;米的道路连接回房屋&nbsp;<code>n - 1</code>，形成一个环。</li>
</ul>

<p>你可以以 <strong>1</strong> 米每秒的速度行走。从房屋&nbsp;0 开始，找到按照&nbsp;<code>queries</code>&nbsp;指定的顺序访问每所房屋的 <strong>最小</strong> 时间。</p>

<p>返回访问房屋所需的 <strong>最短</strong> 总时间。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>forward = [1,4,4], backward = [4,1,2], queries = [1,2,0,2]</span></p>

<p><b>输出：</b>12</p>

<p><b>解释：</b></p>

<p>路径如下：<code><u>0</u><sup>(0)</sup> → <u>1</u><sup>(1)</sup> → <u>2</u><sup>(5)</sup> <u>→</u> 1<sup>(7)</sup> <u>→</u> <u>0</u><sup>(8)</sup> <u>→</u> <u>2</u><sup>(12)</sup></code>。</p>

<p><strong>注意：</strong>使用的&nbsp;<code>node<sup>(total time)</sup></code>&nbsp;符号，<code>→</code>&nbsp;表示前向道路，<code><u>→</u></code>&nbsp;表示反向道路。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">forward = [1,1,1,1], backward = [2,2,2,2], queries = [1,2,3,0]</span></p>

<p><span class="example-io"><b>输出：</b>4</span></p>

<p><strong>解释：</strong></p>

<p>经过路径是&nbsp;<code><u>0</u> → <u>1</u> → <u>2</u> →​​​​​​​ <u>3</u> → <u>0</u></code>。每一步都在前向方向，需要 1 秒。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>n == forward.length == backward.length</code></li>
	<li><code>1 &lt;= forward[i], backward[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= queries[i] &lt; n</code></li>
	<li><code>queries[i] != queries[i + 1]</code></li>
	<li><code>queries[0]</code>&nbsp;非 0。</li>
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
