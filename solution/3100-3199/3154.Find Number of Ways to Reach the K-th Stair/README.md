---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3154.Find%20Number%20of%20Ways%20to%20Reach%20the%20K-th%20Stair/README.md
---

<!-- problem:start -->

# [3154. 到达第 K 级台阶的方案数](https://leetcode.cn/problems/find-number-of-ways-to-reach-the-k-th-stair)

[English Version](/solution/3100-3199/3154.Find%20Number%20of%20Ways%20to%20Reach%20the%20K-th%20Stair/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你有一个 <strong>非负</strong>&nbsp;整数&nbsp;<code>k</code>&nbsp;。有一个无限长度的台阶，<strong>最低</strong>&nbsp;一层编号为 0 。</p>

<p>虎老师有一个整数&nbsp;<code>jump</code>&nbsp;，一开始值为 0 。虎老师从台阶 1 开始，虎老师可以使用 <strong>任意</strong>&nbsp;次操作，目标是到达第&nbsp;<code>k</code> 级台阶。假设虎老师位于台阶 <code>i</code> ，一次 <strong>操作</strong> 中，虎老师可以：</p>

<ul>
	<li>向下走一级到&nbsp;<code>i - 1</code>&nbsp;，但该操作&nbsp;<strong>不能</strong>&nbsp;连续使用，如果在台阶第 0 级也不能使用。</li>
	<li>向上走到台阶&nbsp;<code>i + 2<sup>jump</sup></code>&nbsp;处，然后&nbsp;<code>jump</code>&nbsp;变为&nbsp;<code>jump + 1</code>&nbsp;。</li>
</ul>

<p>请你返回虎老师到达台阶 <code>k</code>&nbsp;处的总方案数。</p>

<p><b>注意</b>&nbsp;，虎老师可能到达台阶 <code>k</code>&nbsp;处后，通过一些操作重新回到台阶 <code>k</code>&nbsp;处，这视为不同的方案。</p>

<p>&nbsp;</p>

<p><b>示例 1：</b></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>k = 0</span></p>

<p><span class="example-io"><b>输出：</b>2</span></p>

<p><strong>解释：</strong></p>

<p>2 种到达台阶 0 的方案为：</p>

<ul>
	<li>虎老师从台阶&nbsp;1 开始。
	<ul>
		<li>执行第一种操作，从台阶 1 向下走到台阶 0 。</li>
	</ul>
	</li>
	<li>虎老师从台阶 1 开始。
	<ul>
		<li>执行第一种操作，从台阶 1 向下走到台阶 0 。</li>
		<li>执行第二种操作，向上走 2<sup>0</sup>&nbsp;级台阶到台阶 1 。</li>
		<li>执行第一种操作，从台阶 1 向下走到台阶 0 。</li>
	</ul>
	</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>k = 1</span></p>

<p><span class="example-io"><b>输出：</b>4</span></p>

<p><strong>解释：</strong></p>

<p>4 种到达台阶 1 的方案为：</p>

<ul>
	<li>虎老师从台阶 1 开始，已经到达台阶 1 。</li>
	<li>虎老师从台阶 1 开始。
	<ul>
		<li>执行第一种操作，从台阶 1 向下走到台阶 0 。</li>
		<li>执行第二种操作，向上走 2<sup>0</sup>&nbsp;级台阶到台阶 1 。</li>
	</ul>
	</li>
	<li>虎老师从台阶 1 开始。
	<ul>
		<li>执行第二种操作，向上走 2<sup>0</sup>&nbsp;级台阶到台阶 2 。</li>
		<li>执行第一种操作，向下走 1 级台阶到台阶 1 。</li>
	</ul>
	</li>
	<li>虎老师从台阶 1 开始。
	<ul>
		<li>执行第一种操作，从台阶 1 向下走到台阶 0 。</li>
		<li>执行第二种操作，向上走&nbsp;2<sup>0</sup>&nbsp;级台阶到台阶 1 。</li>
		<li>执行第一种操作，向下走 1 级台阶到台阶 0 。</li>
		<li>执行第二种操作，向上走 2<sup>1</sup>&nbsp;级台阶到台阶 2 。</li>
		<li>执行第一种操作，向下走&nbsp;1 级台阶到台阶 1 。</li>
	</ul>
	</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= k &lt;= 10<sup>9</sup></code></li>
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
