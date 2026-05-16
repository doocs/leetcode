---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3930.Power%20Update%20After%20K-th%20Largest%20Insertion%20II/README.md
---

<!-- problem:start -->

# [3930. 插入后第 K 大更新的幂 II 🔒](https://leetcode.cn/problems/power-update-after-k-th-largest-insertion-ii)

[English Version](/solution/3900-3999/3930.Power%20Update%20After%20K-th%20Largest%20Insertion%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个整数数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>p</code>。</p>

<p>同时给定一个二维整数数组&nbsp;<code>queries</code>，其中每个&nbsp;<code>queries[i] = [val<sub>i</sub>, k<sub>i</sub>]</code>。</p>

<p>对于每次查询：</p>

<ul>
	<li>将&nbsp;<code>val<sub>i</sub></code>&nbsp;插入到&nbsp;<code>nums</code>。</li>
	<li>令&nbsp;<code>x</code>&nbsp;为当前&nbsp;<code>nums</code>&nbsp;中第&nbsp;<code>k<sub>i</sub></code>&nbsp;个 <strong>最大</strong> 的元素<b>。</b></li>
	<li>将 <code>p</code>&nbsp;<strong>更新</strong> 为&nbsp;<code>p<sup>x</sup> % (10<sup>9</sup> + 7)</code>。</li>
</ul>

<p>返回数组&nbsp;<code>ans</code>，其中&nbsp;<code>ans[i]</code>&nbsp;表示在第 <code>i</code> 次查询后&nbsp;<code>p</code>&nbsp;的值。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [2], p = 4, queries = [[3,1],[1,2]]</span></p>

<p><span class="example-io"><b>输出：</b>[64,4096]</span></p>

<p><strong>解释：</strong></p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
	<thead>
		<tr>
			<th><code>i</code></th>
			<th><code>val<sub>i</sub></code></th>
			<th>当前<br />
			<code>nums</code></th>
			<th><code>k<sub>i</sub></code></th>
			<th>第&nbsp;<code>k<sub>i</sub></code><br />
			大</th>
			<th>p</th>
			<th>新的&nbsp;<code>p = p<sup>k</sup> % (10<sup>9</sup> + 7)</code></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>0</td>
			<td>3</td>
			<td>[2, 3]</td>
			<td>1</td>
			<td>3</td>
			<td>4</td>
			<td>4<sup>3</sup> % (10<sup>9</sup> + 7) = 64</td>
		</tr>
		<tr>
			<td>1</td>
			<td>1</td>
			<td>[2, 3, 1]</td>
			<td>2</td>
			<td>2</td>
			<td>64</td>
			<td>64<sup>2</sup> % (10<sup>9</sup> + 7) = 4096</td>
		</tr>
	</tbody>
</table>

<p>因此，<code>ans = [64, 4096]</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [7,5], p = 6, queries = [[4,3],[7,2]]</span></p>

<p><span class="example-io"><b>输出：</b>[1296,220296870]</span></p>

<p><strong>解释：</strong></p>

<div class="example-block">
<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
	<thead>
		<tr>
			<th><code>i</code></th>
			<th><code>val<sub>i</sub></code></th>
			<th>当前<br />
			<code>nums</code></th>
			<th><code>k<sub>i</sub></code></th>
			<th>第&nbsp;<code>k<sub>i</sub></code><br />
			大</th>
			<th><code>p</code></th>
			<th>新的&nbsp;<code>p = p<sup>k</sup> % (10<sup>9</sup> + 7)</code></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>0</td>
			<td>4</td>
			<td>[7, 5, 4]</td>
			<td>3</td>
			<td>4</td>
			<td>6</td>
			<td>6<sup>4</sup> % (10<sup>9</sup> + 7) = 1296</td>
		</tr>
		<tr>
			<td>1</td>
			<td>7</td>
			<td>[7, 5, 4, 7]</td>
			<td>2</td>
			<td>7</td>
			<td>1296</td>
			<td>1296<sup>7</sup> % (10<sup>9</sup> + 7) = 220296870</td>
		</tr>
	</tbody>
</table>

<p>因此，<code>ans = [1296, 220296870]</code>。</p>
</div>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 *&nbsp;10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= p &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 2 *&nbsp;10<sup>4</sup></code></li>
	<li><code>1 &lt;= val<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k<sub>i</sub> &lt;= n + i + 1</code></li>
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
