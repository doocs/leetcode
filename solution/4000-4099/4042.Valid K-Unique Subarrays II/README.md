---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4042.Valid%20K-Unique%20Subarrays%20II/README.md
---

<!-- problem:start -->

# [4042. 有效 K 个不同元素子数组 II 🔒](https://leetcode.cn/problems/valid-k-unique-subarrays-ii)

[English Version](/solution/4000-4099/4042.Valid%20K-Unique%20Subarrays%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个长度为 <code>n</code> 的整数数组 <code>nums</code> 和一个整数 <code>k</code>。</p>

<p>同时给定整数 <code>l0</code> 和 <code>r0</code>，它们定义了第一个查询，以及一个整数 <code>q</code>，表示需要处理的查询总数。</p>

<p>如果一个&nbsp;<strong><span data-keyword="subarray-nonempty">子数组</span></strong> <code>nums[l<sub>i</sub>..r<sub>i</sub>]</code> 满足以下条件，则称其为&nbsp;<strong>有效&nbsp;</strong>子数组：</p>

<ul>
	<li>它恰好包含 <code>k</code> 个<strong>不同</strong>的数字，并且</li>
	<li>其中每个不同数字出现的次数都是&nbsp;<strong>偶数</strong>。</li>
</ul>

<p>对于查询 0，令 <code>l<sub>0</sub> = l0</code>，<code>r<sub>0</sub> = r0</code>。</p>

<p>令 <code>ans<sub>i</sub></code> 表示第 <code>i</code> 个查询的结果，其中：</p>

<ul>
	<li>如果 <code>nums[l<sub>i</sub>..r<sub>i</sub>]</code> 是有效子数组，则 <code>ans<sub>i</sub> = 1</code>；</li>
	<li>否则 <code>ans<sub>i</sub> = 0</code>。</li>
</ul>

<p>对于每个 <code>i &gt; 0</code>，按照以下方式生成下一个查询：</p>

<ul>
	<li>如果 <code>ans<sub>i-1</sub> = 1</code>，则令 <code>g<sub>i-1</sub> = l<sub>i-1</sub> + r<sub>i-1</sub></code>；否则令 <code>g<sub>i-1</sub> = r<sub>i-1</sub> - l<sub>i-1</sub></code>。</li>
	<li>计算 <code>l<sub>i</sub> = (l<sub>i-1</sub> XOR g<sub>i-1</sub>) % n</code>，以及 <code>r<sub>i</sub> = (r<sub>i-1</sub> XOR g<sub>i-1</sub>) % n</code>。</li>
	<li>如果 <code>l<sub>i</sub> &gt; r<sub>i</sub></code>，则交换二者。</li>
</ul>

<p>返回一个布尔数组 <code>ans</code>，其中 <code>ans[i]</code> 在 <code>ans<sub>i</sub> = 1</code> 时为 <code>true</code>，否则为 <code>false</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2,2,1], k = 2, l0 = 1, r0 = 2, q = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">[false,true]</span></p>

<p><strong>解释：</strong></p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
	<thead>
		<tr>
			<th><code>i</code></th>
			<th><code>[l<sub>i</sub>, r<sub>i</sub>]</code></th>
			<th>子数组</th>
			<th>不同数字</th>
			<th>出现次数</th>
			<th>有效性判断</th>
			<th><code>ans[i]</code></th>
			<th><code>[l<sub>i+1</sub>, r<sub>i+1</sub>]</code></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>0</td>
			<td>[1, 2]</td>
			<td>[2, 2]</td>
			<td>{2} → 1</td>
			<td>{2:2}</td>
			<td><code>false</code>：该子数组包含的不同数字少于 <code>k</code> 个。</td>
			<td><code>ans<sub>0</sub> = 0</code></td>
			<td><code>g<sub>0</sub> = 2 - 1 = 1<br />
			l<sub>1</sub> = (1 XOR 1) % 4 = 0<br />
			r<sub>1</sub> = (2 XOR 1) % 4 = 3 </code></td>
		</tr>
		<tr>
			<td>1</td>
			<td>[0, 3]</td>
			<td>[1, 2, 2, 1]</td>
			<td>{1,2} → 2</td>
			<td>{1:2,2:2}</td>
			<td><code>true</code>：该子数组恰好包含 <code>k</code> 个不同数字，并且每个数字出现的次数都是偶数。</td>
			<td><code>ans<sub>1</sub> = 1</code></td>
			<td>-</td>
		</tr>
	</tbody>
</table>

<p>因此，<code>ans = [false, true]</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2,3,3,4], k = 1, l0 = 2, r0 = 3, q = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">[true,false]</span></p>

<p><strong>解释：</strong></p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
	<thead>
		<tr>
			<th><code>i</code></th>
			<th><code>[l<sub>i</sub>, r<sub>i</sub>]</code></th>
			<th>子数组</th>
			<th>不同数字</th>
			<th>出现次数</th>
			<th>有效性判断</th>
			<th><code>ans[i]</code></th>
			<th><code>[l<sub>i+1</sub>, r<sub>i+1</sub>]</code></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>0</td>
			<td>[2, 3]</td>
			<td>[3, 3]</td>
			<td>{3} → 1</td>
			<td>{3:2}</td>
			<td><code>true</code>：该子数组恰好包含 <code>k</code> 个不同数字，并且每个数字出现的次数都是偶数。</td>
			<td><code>ans<sub>0</sub> = 1</code></td>
			<td><code>g<sub>0</sub> = 2 + 3 = 5<br />
			l<sub>1</sub> = (2 XOR 5) % 5 = 7 % 5 = 2<br />
			r<sub>1</sub> = (3 XOR 5) % 5 = 6 % 5 = 1 </code><br />
			由于 <code>l<sub>1</sub> &gt; r<sub>1</sub></code>，交换二者，得到 <code>[l<sub>1</sub>, r<sub>1</sub>] = [1, 2]</code>。</td>
		</tr>
		<tr>
			<td>1</td>
			<td>[1, 2]</td>
			<td>[2, 3]</td>
			<td>{2,3} → 2</td>
			<td>{2:1,3:1}</td>
			<td><code>false</code>：该子数组包含 2 个不同数字，而不是恰好 <code>k = 1</code> 个。</td>
			<td><code>ans<sub>1</sub> = 0</code></td>
			<td>-</td>
		</tr>
	</tbody>
</table>

<p>因此，<code>ans = [true, false]</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n == nums.length &lt;= 5 × 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 5 × 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
	<li><code>0 &lt;= l<sub>0</sub> &lt; r<sub>0</sub> &lt;= n - 1</code></li>
	<li><code>1 &lt;= q &lt;= 5 × 10<sup>5</sup></code></li>
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
