---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3145.Find%20Products%20of%20Elements%20of%20Big%20Array/README.md
rating: 2859
source: 第 130 场双周赛 Q4
tags:
    - 位运算
    - 数组
    - 二分查找
---

<!-- problem:start -->

# [3145. 大数组元素的乘积](https://leetcode.cn/problems/find-products-of-elements-of-big-array)

[English Version](/solution/3100-3199/3145.Find%20Products%20of%20Elements%20of%20Big%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>一个整数 <code>x</code>&nbsp;的 <strong>强数组</strong>&nbsp;指的是满足和为 <code>x</code> 的二的幂的最短有序数组。比方说，11 的强数组为&nbsp;<code>[1, 2, 8]</code>&nbsp;。</p>

<p>我们将每一个正整数 <code>i</code>&nbsp;（即1，2，3等等）的 <strong>强数组</strong>&nbsp;连接得到数组&nbsp;<code>big_nums</code>&nbsp;，<code>big_nums</code>&nbsp;开始部分为&nbsp;<code>[<u>1</u>, <u>2</u>, <u>1, 2</u>, <u>4</u>, <u>1, 4</u>, <u>2, 4</u>, <u>1, 2, 4</u>, <u>8</u>, ...]</code>&nbsp;。</p>

<p>给你一个二维整数数组&nbsp;<code>queries</code>&nbsp;，其中&nbsp;<code>queries[i] = [from<sub>i</sub>, to<sub>i</sub>, mod<sub>i</sub>]</code>&nbsp;，你需要计算&nbsp;<code>(big_nums[from<sub>i</sub>] * big_nums[from<sub>i</sub> + 1] * ... * big_nums[to<sub>i</sub>]) % mod<sub>i</sub></code>&nbsp;。</p>

<p>请你返回一个整数数组&nbsp;<code>answer</code>&nbsp;，其中&nbsp;<code>answer[i]</code>&nbsp;是第 <code>i</code>&nbsp;个查询的答案。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>queries = [[1,3,7]]</span></p>

<p><span class="example-io"><b>输出：</b>[4]</span></p>

<p><strong>解释：</strong></p>

<p>只有一个查询。</p>

<p><code>big_nums[1..3] = [2,1,2]</code>&nbsp;。它们的乘积为 4 ，4 对 7 取余数得到 4 。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>queries = [[2,5,3],[7,7,4]]</span></p>

<p><span class="example-io"><b>输出：</b>[2,2]</span></p>

<p><strong>解释：</strong></p>

<p>有两个查询。</p>

<p>第一个查询：<code>big_nums[2..5] = [1,2,4,1]</code>&nbsp;。它们的乘积为 8 ，8 对 3 取余数得到 2 。</p>

<p>第二个查询：<code>big_nums[7] = 2</code>&nbsp;，2 对 4 取余数得到 2 。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= queries.length &lt;= 500</code></li>
	<li><code>queries[i].length == 3</code></li>
	<li><code>0 &lt;= queries[i][0] &lt;= queries[i][1] &lt;= 10<sup>15</sup></code></li>
	<li><code>1 &lt;= queries[i][2] &lt;= 10<sup>5</sup></code></li>
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
