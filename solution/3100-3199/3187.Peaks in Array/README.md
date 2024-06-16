---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3187.Peaks%20in%20Array/README.md
---

<!-- problem:start -->

# [3187. 数组中的峰值](https://leetcode.cn/problems/peaks-in-array)

[English Version](/solution/3100-3199/3187.Peaks%20in%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>数组 <code>arr</code>&nbsp;中&nbsp;<strong>大于</strong>&nbsp;前面和后面相邻元素的元素被称为 <strong>峰值</strong>&nbsp;元素。</p>

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;和一个二维整数数组&nbsp;<code>queries</code>&nbsp;。</p>

<p>你需要处理以下两种类型的操作：</p>

<ul>
	<li><code>queries[i] = [1, l<sub>i</sub>, r<sub>i</sub>]</code>&nbsp;，求出子数组&nbsp;<code>nums[l<sub>i</sub>..r<sub>i</sub>]</code>&nbsp;中&nbsp;<strong>峰值</strong>&nbsp;元素的数目。<!-- notionvc: 73b20b7c-e1ab-4dac-86d0-13761094a9ae --></li>
	<li><code>queries[i] = [2, index<sub>i</sub>, val<sub>i</sub>]</code>&nbsp;，将&nbsp;<code>nums[index<sub>i</sub>]</code>&nbsp;变为&nbsp;<code><font face="monospace">val<sub>i</sub></font></code><font face="monospace">&nbsp;。</font></li>
</ul>

<p>请你返回一个数组&nbsp;<code>answer</code>&nbsp;，它依次包含每一个第一种操作的答案。<!-- notionvc: a9ccef22-4061-4b5a-b4cc-a2b2a0e12f30 --></p>

<p><strong>注意：</strong></p>

<ul>
	<li>子数组中 <strong>第一个</strong>&nbsp;和 <strong>最后一个</strong>&nbsp;元素都 <strong>不是</strong>&nbsp;峰值元素。</li>
</ul>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [3,1,4,2,5], queries = [[2,3,4],[1,0,4]]</span></p>

<p><span class="example-io"><b>输出：</b>[0]</span></p>

<p><strong>解释：</strong></p>

<p>第一个操作：我们将&nbsp;<code>nums[3]</code>&nbsp;变为&nbsp;4 ，<code>nums</code>&nbsp;变为&nbsp;<code>[3,1,4,4,5]</code>&nbsp;。</p>

<p>第二个操作：<code>[3,1,4,4,5]</code>&nbsp;中峰值元素的数目为 0 。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [4,1,4,2,1,5], queries = [[2,2,4],[1,0,2],[1,0,4]]</span></p>

<p><span class="example-io"><b>输出：</b>[0,1]</span></p>

<p><strong>解释：</strong></p>

<p>第一个操作：<code>nums[2]</code>&nbsp;变为 4 ，它已经是 4 了，所以保持不变。</p>

<p>第二个操作：<code>[4,1,4]</code>&nbsp;中峰值元素的数目为 0 。</p>

<p>第三个操作：第二个 4 是&nbsp;<code>[4,1,4,2,1]</code>&nbsp;中的峰值元素。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i][0] == 1</code>&nbsp;或者&nbsp;<code>queries[i][0] == 2</code></li>
	<li>对于所有的 <code>i</code>&nbsp;，都有：
	<ul>
		<li><code>queries[i][0] == 1</code>&nbsp;：<code>0 &lt;= queries[i][1] &lt;= queries[i][2] &lt;= nums.length - 1</code></li>
		<li><code>queries[i][0] == 2</code> ：<code>0 &lt;= queries[i][1] &lt;= nums.length - 1</code>, <code>1 &lt;= queries[i][2] &lt;= 10<sup>5</sup></code></li>
	</ul>
	</li>
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
