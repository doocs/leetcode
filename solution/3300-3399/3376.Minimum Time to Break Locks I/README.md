---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3376.Minimum%20Time%20to%20Break%20Locks%20I/README.md
---

<!-- problem:start -->

# [3376. 破解锁的最少时间 I](https://leetcode.cn/problems/minimum-time-to-break-locks-i)

[English Version](/solution/3300-3399/3376.Minimum%20Time%20to%20Break%20Locks%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>Bob 被困在了一个地窖里，他需要破解 <code>n</code>&nbsp;个锁才能逃出地窖，每一个锁都需要一定的 <strong>能量</strong>&nbsp;才能打开。每一个锁需要的能量存放在一个数组&nbsp;<code>strength</code>&nbsp;里，其中&nbsp;<code>strength[i]</code>&nbsp;表示打开第 <code>i</code>&nbsp;个锁需要的能量。</p>

<p>Bob 有一把剑，它具备以下的特征：</p>

<ul>
	<li>一开始剑的能量为 0 。</li>
	<li>剑的能量增加因子&nbsp;<code><font face="monospace">X</font></code>&nbsp;一开始的值为 1 。</li>
	<li>每分钟，剑的能量都会增加当前的&nbsp;<code>X</code>&nbsp;值。</li>
	<li>打开第 <code>i</code>&nbsp;把锁，剑的能量需要到达 <strong>至少</strong>&nbsp;<code>strength[i]</code>&nbsp;。</li>
	<li>打开一把锁以后，剑的能量会变回 0 ，<code>X</code>&nbsp;的值会增加一个给定的值 <code>K</code>&nbsp;。</li>
</ul>

<p>你的任务是打开所有 <code>n</code>&nbsp;把锁并逃出地窖，请你求出需要的 <strong>最少</strong>&nbsp;分钟数。</p>

<p>请你返回 Bob<strong>&nbsp;</strong>打开所有 <code>n</code>&nbsp;把锁需要的 <strong>最少</strong>&nbsp;时间。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>strength = [3,4,1], K = 1</span></p>

<p><span class="example-io"><b>输出：</b>4</span></p>

<p><b>解释：</b></p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">时间</th>
			<th style="border: 1px solid black;">能量</th>
			<th style="border: 1px solid black;">X</th>
			<th style="border: 1px solid black;">操作</th>
			<th style="border: 1px solid black;">更新后的 X</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">什么也不做</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">打开第 3&nbsp;把锁</td>
			<td style="border: 1px solid black;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">什么也不做</td>
			<td style="border: 1px solid black;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">打开第 2 把锁</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">打开第 1 把锁</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
	</tbody>
</table>

<p>无法用少于 4 分钟打开所有的锁，所以答案为 4 。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>strength = [2,5,4], K = 2</span></p>

<p><span class="example-io"><b>输出：</b>5</span></p>

<p><b>解释：</b></p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">时间</th>
			<th style="border: 1px solid black;">能量</th>
			<th style="border: 1px solid black;">X</th>
			<th style="border: 1px solid black;">操作</th>
			<th style="border: 1px solid black;">更新后的 X</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">什么也不做</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">什么也不做</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">打开第 1 把锁</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">什么也不做</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">6</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">打开第 2 把锁</td>
			<td style="border: 1px solid black;">5</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;">打开第 3 把锁</td>
			<td style="border: 1px solid black;">7</td>
		</tr>
	</tbody>
</table>

<p>无法用少于 5 分钟打开所有的锁，所以答案为 5 。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == strength.length</code></li>
	<li><code>1 &lt;= n &lt;= 8</code></li>
	<li><code>1 &lt;= K &lt;= 10</code></li>
	<li><code>1 &lt;= strength[i] &lt;= 10<sup>6</sup></code></li>
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
