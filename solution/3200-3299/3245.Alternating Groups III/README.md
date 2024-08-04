---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3245.Alternating%20Groups%20III/README.md
---

<!-- problem:start -->

# [3245. 交替组 III](https://leetcode.cn/problems/alternating-groups-iii)

[English Version](/solution/3200-3299/3245.Alternating%20Groups%20III/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>colors</code> 和一个二维整数数组 <code>queries</code> 。<code>colors</code>表示一个由红色和蓝色瓷砖组成的环，第 <code>i</code>&nbsp;块瓷砖的颜色为&nbsp;<code>colors[i]</code>&nbsp;：</p>

<ul>
	<li><code>colors[i] == 0</code>&nbsp;表示第&nbsp;<code>i</code>&nbsp;块瓷砖的颜色是 <strong>红色</strong>&nbsp;。</li>
	<li><code>colors[i] == 1</code>&nbsp;表示第 <code>i</code>&nbsp;块瓷砖的颜色是 <strong>蓝色</strong>&nbsp;。</li>
</ul>

<p>环中连续若干块瓷砖的颜色如果是 <strong>交替</strong>&nbsp;颜色（也就是说这组瓷砖中除了第一块和最后一块瓷砖以外，中间瓷砖的颜色与它<strong>&nbsp;左边</strong>&nbsp;和 <strong>右边</strong>&nbsp;的颜色都不同），那么它被称为一个 <strong>交替组</strong>。</p>

<p>你需要处理两种类型的查询：</p>

<ul>
	<li><code>queries[i] = [1, size<sub>i</sub>]</code>，确定大小为<code>size<sub>i</sub></code>的<strong> </strong><strong>交替组</strong> 的数量。</li>
	<li><code>queries[i] = [2, index<sub>i</sub>, color<sub>i</sub>]</code>，将<code>colors[index<sub>i</sub>]</code>更改为<code>color<sub>i</sub></code>。</li>
</ul>

<p>返回数组 <code>answer</code>，数组中按顺序包含第一种类型查询的结果。</p>

<p><b>注意</b>&nbsp;，由于&nbsp;<code>colors</code>&nbsp;表示一个 <strong>环</strong>&nbsp;，<strong>第一块</strong>&nbsp;瓷砖和 <strong>最后一块</strong>&nbsp;瓷砖是相邻的。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">colors = [0,1,1,0,1], queries = [[2,1,0],[1,4]]</span></p>

<p><strong>输出：</strong><span class="example-io">[2]</span></p>

<p><strong>解释：</strong></p>

<p>第一次查询：</p>

<p>将 <code>colors[1]</code> 改为 0。</p>

<p><img alt="" data-darkreader-inline-bgcolor="" data-darkreader-inline-bgimage="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3245.Alternating%20Groups%20III/images/screenshot-from-2024-06-03-20-20-25.png" style="width: 150px; height: 150px; padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; --darkreader-inline-bgimage: initial; --darkreader-inline-bgcolor: #181a1b;" /></p>

<p>第二次查询：</p>

<p>统计大小为 4 的交替组的数量：</p>

<p><img alt="" data-darkreader-inline-bgcolor="" data-darkreader-inline-bgimage="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3245.Alternating%20Groups%20III/images/screenshot-from-2024-06-03-20-25-02-2.png" style="width: 150px; height: 150px; padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; --darkreader-inline-bgimage: initial; --darkreader-inline-bgcolor: #181a1b;" /><img alt="" data-darkreader-inline-bgcolor="" data-darkreader-inline-bgimage="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3245.Alternating%20Groups%20III/images/screenshot-from-2024-06-03-20-24-12.png" style="width: 150px; height: 150px; padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; --darkreader-inline-bgimage: initial; --darkreader-inline-bgcolor: #181a1b;" /></p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">colors = [0,0,1,0,1,1], queries = [[1,3],[2,3,0],[1,5]]</span></p>

<p><strong>输出：</strong><span class="example-io">[2,0]</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" data-darkreader-inline-bgcolor="" data-darkreader-inline-bgimage="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3245.Alternating%20Groups%20III/images/screenshot-from-2024-06-03-20-35-50.png" style="width: 150px; height: 150px; padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; --darkreader-inline-bgimage: initial; --darkreader-inline-bgcolor: #181a1b;" /></p>

<p>第一次查询：</p>

<p>统计大小为 3 的交替组的数量。</p>

<p><img alt="" data-darkreader-inline-bgcolor="" data-darkreader-inline-bgimage="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3245.Alternating%20Groups%20III/images/screenshot-from-2024-06-03-20-37-13.png" style="width: 150px; height: 150px; padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; --darkreader-inline-bgimage: initial; --darkreader-inline-bgcolor: #181a1b;" /><img alt="" data-darkreader-inline-bgcolor="" data-darkreader-inline-bgimage="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3245.Alternating%20Groups%20III/images/screenshot-from-2024-06-03-20-36-40.png" style="width: 150px; height: 150px; padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem; --darkreader-inline-bgimage: initial; --darkreader-inline-bgcolor: #181a1b;" /></p>

<p>第二次查询：<code>colors</code>不变。</p>

<p>第三次查询：不存在大小为 5 的交替组。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>4 &lt;= colors.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= colors[i] &lt;= 1</code></li>
	<li><code>1 &lt;= queries.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>queries[i][0] == 1</code> 或 <code>queries[i][0] == 2</code></li>
	<li>对于所有的<code>i</code>：
	<ul>
		<li><code>queries[i][0] == 1</code>： <code>queries[i].length == 2</code>, <code>3 &lt;= queries[i][1] &lt;= colors.length - 1</code></li>
		<li><code>queries[i][0] == 2</code>： <code>queries[i].length == 3</code>, <code>0 &lt;= queries[i][1] &lt;= colors.length - 1</code>, <code>0 &lt;= queries[i][2] &lt;= 1</code></li>
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
