---
comments: true
difficulty: 困难
rating: 2231
source: 第 124 场双周赛 Q4
tags:
    - 数组
    - 动态规划
    - 排序
---

<!-- problem:start -->

# [3041. 修改数组后最大化数组中的连续元素数目](https://leetcode.cn/problems/maximize-consecutive-elements-in-an-array-after-modification)

[English Version](/solution/3000-3099/3041.Maximize%20Consecutive%20Elements%20in%20an%20Array%20After%20Modification/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始只包含 <strong>正</strong>&nbsp;整数的数组&nbsp;<code>nums</code>&nbsp;。</p>

<p>一开始，你可以将数组中 <strong>任意数量</strong> 元素增加 <strong>至多</strong> <code>1</code> 。</p>

<p>修改后，你可以从最终数组中选择 <strong>一个或者更多</strong>&nbsp;元素，并确保这些元素升序排序后是 <strong>连续</strong>&nbsp;的。比方说，<code>[3, 4, 5]</code> 是连续的，但是&nbsp;<code>[3, 4, 6]</code> 和&nbsp;<code>[1, 1, 2, 3]</code>&nbsp;不是连续的。<!-- notionvc: 312f8c5d-40d0-4cd1-96cc-9e96a846735b --></p>

<p>请你返回 <strong>最多</strong>&nbsp;可以选出的元素数目。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [2,1,5,1,1]
<b>输出：</b>3
<b>解释：</b>我们将下标 0 和 3 处的元素增加 1 ，得到结果数组 nums = [3,1,5,2,1] 。
我们选择元素 [<em><strong>3</strong></em>,<em><strong>1</strong></em>,5,<em><strong>2</strong></em>,1] 并将它们排序得到 [1,2,3] ，是连续元素。
最多可以得到 3 个连续元素。</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [1,4,7,10]
<b>输出：</b>1
<b>解释：</b>我们可以选择的最多元素数目是 1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 每个元素至多加 $1$，再选一个可排成连续整数的最长子序列。$n \le 10^5$，值域到 $10^6$。
>
> 排序后相邻值只可能是相等、相差 $1$ 或更大。加一相当于把值改到 $x$ 或 $x+1$，连续段的转移因此只依赖「当前结尾取原值还是加一」。
>
> 按值从小到大做线性 DP：记录以 $x$ 与 $x+1$ 结尾的最长连续长度，遇到重复或断档时按差值为 $0/1/\ge 2$ 更新。

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
