---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4004.Minimum%20Moves%20to%20Balance%20Circular%20Array%20II/README.md
---

<!-- problem:start -->

# [4004. 使循环数组余额非负的最少移动次数 II 🔒](https://leetcode.cn/problems/minimum-moves-to-balance-circular-array-ii)

[English Version](/solution/4000-4099/4004.Minimum%20Moves%20to%20Balance%20Circular%20Array%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个长度为 <code>n</code> 的 <span data-keyword="circular-array">环形数组</span> <code>balance</code>，其中 <code>balance[i]</code>&nbsp;是第 <code>i</code> 个人的净余额。</p>

<p>在一次操作中，一个人可以向其左侧或右侧的相邻人员转移&nbsp;<strong>恰好</strong> 1 单位的余额。</p>

<p>返回使每个人的余额都变为&nbsp;<strong>非负&nbsp;</strong>所需的&nbsp;<strong>最少&nbsp;</strong>操作次数。如果无法做到，则返回 -1。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">balance = [-1,2,-1]</span></p>

<p><strong>输出：</strong><span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p>一种最优的操作序列如下：</p>

<ul>
	<li>从 <code>i = 1</code> 向 <code>i = 0</code> 转移 1 单位余额，得到 <code>balance = [0, 1, -1]</code></li>
	<li>从 <code>i = 1</code> 向 <code>i = 2</code> 转移 1 单位余额，得到 <code>balance = [0, 0, 0]</code></li>
</ul>

<p>因此，所需的最少操作次数为 2。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">balance = [4,-1,-2]</span></p>

<p><strong>输出：</strong><span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<p>一种最优的操作序列如下：</p>

<ul>
	<li>从 <code>i = 0</code> 向 <code>i = 1</code> 转移 1 单位余额，得到 <code>balance = [3, 0, -2]</code></li>
	<li>从 <code>i = 0</code> 向 <code>i = 2</code> 转移 1 单位余额，得到 <code>balance = [2, 0, -1]</code></li>
	<li>从 <code>i = 0</code> 向 <code>i = 2</code> 再转移 1 单位余额，得到 <code>balance = [1, 0, 0]</code></li>
</ul>

<p>因此，所需的最少操作次数为 3。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">balance = [-3,-3,5]</span></p>

<p><strong>输出：</strong><span class="example-io">-1</span></p>

<p><strong>解释：</strong></p>

<p>对于 <code>balance = [-3, -3, 5]</code>，无法使所有人的余额都变为非负，因此答案为 -1。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == balance.length &lt;= 1000</code></li>
	<li><code>-10<sup>5</sup> &lt;= balance[i] &lt;= 10<sup>5</sup></code></li>
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
