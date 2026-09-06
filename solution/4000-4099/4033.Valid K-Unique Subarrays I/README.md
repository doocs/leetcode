---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4033.Valid%20K-Unique%20Subarrays%20I/README.md
rating: 2314
source: 第 516 场周赛 Q4
---

<!-- problem:start -->

# [4033. 有效 K 个不同元素子数组 I](https://leetcode.cn/problems/valid-k-unique-subarrays-i)

[English Version](/solution/4000-4099/4033.Valid%20K-Unique%20Subarrays%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个整数数组 <code>nums</code> 和一个整数 <code>k</code>。</p>

<p>同时给定一个二维整数数组 <code>queries</code>，其中 <code>queries[i] = [l<sub>i</sub>, r<sub>i</sub>]</code> 表示子数组 <code>nums[l<sub>i</sub>..r<sub>i</sub>]</code>。</p>

<p>对于每个查询，如果子数组 <code>nums[l<sub>i</sub>..r<sub>i</sub>]</code> 满足以下条件，则认为该&nbsp;<strong>子数组&nbsp;</strong>是&nbsp;<strong>有效的</strong>：</p>

<ul>
	<li>它&nbsp;<strong>恰好&nbsp;</strong>包含 <code>k</code> 个&nbsp;<strong>不同&nbsp;</strong>的数字，并且</li>
	<li>子数组中每个数字出现的&nbsp;<strong>频率&nbsp;</strong>都是&nbsp;<strong>偶数</strong>。</li>
</ul>

<p>返回一个布尔数组 <code>ans</code>，其中如果 <code>nums[l<sub>i</sub>..r<sub>i</sub>]</code> 是&nbsp;<strong>有效的</strong>，则 <code>ans[i]</code> 为 <code>true</code>，否则为 <code>false</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2,2,1], k = 2, queries = [[0,1],[0,3],[1,2]]</span></p>

<p><strong>输出：</strong> <span class="example-io">[false,true,false]</span></p>

<p><strong>解释：</strong></p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;"><code>i</code></th>
			<th style="border: 1px solid black;"><code>[l<sub>i</sub>, r<sub>i</sub>]</code></th>
			<th style="border: 1px solid black;">子数组</th>
			<th style="border: 1px solid black;">不同的数字</th>
			<th style="border: 1px solid black;">频率</th>
			<th style="border: 1px solid black;">有效性检查</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">[0, 1]</td>
			<td style="border: 1px solid black;">[1, 2]</td>
			<td style="border: 1px solid black;">{1, 2} → 2</td>
			<td style="border: 1px solid black;">{1: 1, 2: 1}</td>
			<td style="border: 1px solid black;"><code>false</code>：元素出现的次数不是偶数。</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">[0, 3]</td>
			<td style="border: 1px solid black;">[1, 2, 2, 1]</td>
			<td style="border: 1px solid black;">{1, 2} → 2</td>
			<td style="border: 1px solid black;">{1: 2, 2: 2}</td>
			<td style="border: 1px solid black;"><code>true</code>：恰好有 <code>k = 2</code> 个不同的元素，并且所有元素出现的次数都是偶数。</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">[1, 2]</td>
			<td style="border: 1px solid black;">[2, 2]</td>
			<td style="border: 1px solid black;">{2} → 1</td>
			<td style="border: 1px solid black;">{2: 2}</td>
			<td style="border: 1px solid black;"><code>false</code>：不同元素的数量小于 <code>k = 2</code>。</td>
		</tr>
	</tbody>
</table>

<p>因此，<code>ans = [false, true, false]</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [3,3,3], k = 1, queries = [[1,2],[0,2]]</span></p>

<p><strong>输出：</strong> <span class="example-io">[true,false]</span></p>

<p><strong>解释：</strong></p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;"><code>i</code></th>
			<th style="border: 1px solid black;"><code>[l<sub>i</sub>, r<sub>i</sub>]</code></th>
			<th style="border: 1px solid black;">子数组</th>
			<th style="border: 1px solid black;">不同的数字</th>
			<th style="border: 1px solid black;">频率</th>
			<th style="border: 1px solid black;">有效性检查</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">[1, 2]</td>
			<td style="border: 1px solid black;">[3, 3]</td>
			<td style="border: 1px solid black;">{3} → 1</td>
			<td style="border: 1px solid black;">{3: 2}</td>
			<td style="border: 1px solid black;"><code>true</code>：恰好有 <code>k = 1</code> 个不同的元素，并且该元素出现的次数为偶数。</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">[0, 2]</td>
			<td style="border: 1px solid black;">[3, 3, 3]</td>
			<td style="border: 1px solid black;">{3} → 1</td>
			<td style="border: 1px solid black;">{3: 3}</td>
			<td style="border: 1px solid black;"><code>false</code>：数字 3 出现的次数不是偶数。</td>
		</tr>
	</tbody>
</table>

<p>因此，<code>ans = [true, false]</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i] == [l<sub>i</sub>, r<sub>i</sub>]</code></li>
	<li><code>0 &lt;= l<sub>i</sub> &lt; r<sub>i</sub> &lt;= n - 1</code></li>
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
