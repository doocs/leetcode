---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3179.Find%20the%20N-th%20Value%20After%20K%20Seconds/README.md
---

<!-- problem:start -->

# [3179. K 秒后第 N 个元素的值](https://leetcode.cn/problems/find-the-n-th-value-after-k-seconds)

[English Version](/solution/3100-3199/3179.Find%20the%20N-th%20Value%20After%20K%20Seconds/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个整数 <code>n</code> 和 <code>k</code>。</p>

<p>最初，你有一个长度为 <code>n</code> 的整数数组 <code>a</code>，对所有 <code>0 &lt;= i &lt;= n - 1</code>，都有 <code>a[i] = 1</code> 。每过一秒，你会同时更新每个元素为其前面所有元素的和加上该元素本身。例如，一秒后，<code>a[0]</code> 保持不变，<code>a[1]</code> 变为 <code>a[0] + a[1]</code>，<code>a[2]</code> 变为 <code>a[0] + a[1] + a[2]</code>，以此类推。</p>

<p>返回 <code>k</code> 秒后 <code>a[n - 1]</code> 的<strong>值</strong>。</p>

<p>由于答案可能非常大，返回其对 <code>10<sup>9</sup> + 7</code> <strong>取余 </strong>后的结果。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">n = 4, k = 5</span></p>

<p><strong>输出：</strong><span class="example-io">56</span></p>

<p><strong>解释：</strong></p>

<table border="1">
	<tbody>
		<tr>
			<th>时间（秒）</th>
			<th>数组状态</th>
		</tr>
		<tr>
			<td>0</td>
			<td>[1,1,1,1]</td>
		</tr>
		<tr>
			<td>1</td>
			<td>[1,2,3,4]</td>
		</tr>
		<tr>
			<td>2</td>
			<td>[1,3,6,10]</td>
		</tr>
		<tr>
			<td>3</td>
			<td>[1,4,10,20]</td>
		</tr>
		<tr>
			<td>4</td>
			<td>[1,5,15,35]</td>
		</tr>
		<tr>
			<td>5</td>
			<td>[1,6,21,56]</td>
		</tr>
	</tbody>
</table>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">n = 5, k = 3</span></p>

<p><strong>输出：</strong><span class="example-io">35</span></p>

<p><strong>解释：</strong></p>

<table border="1">
	<tbody>
		<tr>
			<th>时间（秒）</th>
			<th>数组状态</th>
		</tr>
		<tr>
			<td>0</td>
			<td>[1,1,1,1,1]</td>
		</tr>
		<tr>
			<td>1</td>
			<td>[1,2,3,4,5]</td>
		</tr>
		<tr>
			<td>2</td>
			<td>[1,3,6,10,15]</td>
		</tr>
		<tr>
			<td>3</td>
			<td>[1,4,10,20,35]</td>
		</tr>
	</tbody>
</table>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n, k &lt;= 1000</code></li>
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
