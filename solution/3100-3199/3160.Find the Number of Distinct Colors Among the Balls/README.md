---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3160.Find%20the%20Number%20of%20Distinct%20Colors%20Among%20the%20Balls/README.md
---

<!-- problem:start -->

# [3160. 所有球里面不同颜色的数目](https://leetcode.cn/problems/find-the-number-of-distinct-colors-among-the-balls)

[English Version](/solution/3100-3199/3160.Find%20the%20Number%20of%20Distinct%20Colors%20Among%20the%20Balls/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数&nbsp;<code>limit</code>&nbsp;和一个大小为 <code>n x 2</code>&nbsp;的二维数组&nbsp;<code>queries</code>&nbsp;。</p>

<p>总共有&nbsp;<code>limit + 1</code>&nbsp;个球，每个球的编号为&nbsp;<code>[0, limit]</code>&nbsp;中一个&nbsp;<strong>互不相同</strong>&nbsp;的数字。一开始，所有球都没有颜色。<code>queries</code>&nbsp;中每次操作的格式为&nbsp;<code>[x, y]</code>&nbsp;，你需要将球&nbsp;<code>x</code>&nbsp;染上颜色&nbsp;<code>y</code>&nbsp;。每次操作之后，你需要求出所有球中&nbsp;<strong>不同</strong>&nbsp;颜色的数目。</p>

<p>请你返回一个长度为 <code>n</code>&nbsp;的数组&nbsp;<code>result</code>&nbsp;，其中&nbsp;<code>result[i]</code>&nbsp;是第 <code>i</code>&nbsp;次操作以后不同颜色的数目。</p>

<p><strong>注意</strong>&nbsp;，没有染色的球不算作一种颜色。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>limit = 4, queries = [[1,4],[2,5],[1,3],[3,4]]</span></p>

<p><span class="example-io"><b>输出：</b>[1,2,2,3]</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3160.Find%20the%20Number%20of%20Distinct%20Colors%20Among%20the%20Balls/images/ezgifcom-crop.gif" style="width: 455px; height: 145px;" /></p>

<ul>
	<li>操作 0&nbsp;后，球 1 颜色为 4 。</li>
	<li>操作 1 后，球 1 颜色为&nbsp;4 ，球 2 颜色为 5 。</li>
	<li>操作 2 后，球 1 颜色为 3 ，球 2 颜色为 5 。</li>
	<li>操作 3 后，球 1 颜色为 3 ，球 2 颜色为 5 ，球 3 颜色为 4 。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>limit = 4, queries = [[0,1],[1,2],[2,2],[3,4],[4,5]]</span></p>

<p><span class="example-io"><b>输出：</b>[1,2,2,3,4]</span></p>

<p><strong>解释：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3160.Find%20the%20Number%20of%20Distinct%20Colors%20Among%20the%20Balls/images/ezgifcom-crop2.gif" style="width: 457px; height: 144px;" /></strong></p>

<ul>
	<li>操作 0&nbsp;后，球 0&nbsp;颜色为 1&nbsp;。</li>
	<li>操作 1&nbsp;后，球 0&nbsp;颜色为 1 ，球 1 颜色为 2 。</li>
	<li>操作 2&nbsp;后，球 0&nbsp;颜色为 1 ，球 1 和 2&nbsp;颜色为 2 。</li>
	<li>操作 3 后，球 0&nbsp;颜色为 1 ，球 1 和 2&nbsp;颜色为 2 ，球 3 颜色为 4 。</li>
	<li>操作 4&nbsp;后，球 0&nbsp;颜色为 1 ，球 1 和 2&nbsp;颜色为 2 ，球 3 颜色为 4 ，球 4 颜色为 5 。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= limit &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= n == queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i].length == 2</code></li>
	<li><code>0 &lt;= queries[i][0] &lt;= limit</code></li>
	<li><code>1 &lt;= queries[i][1] &lt;= 10<sup>9</sup></code></li>
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
