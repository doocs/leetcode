# [3112. 访问消失节点的最少时间](https://leetcode.cn/problems/minimum-time-to-visit-disappearing-nodes)

[English Version](/solution/3100-3199/3112.Minimum%20Time%20to%20Visit%20Disappearing%20Nodes/README_EN.md)

<!-- tags: -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二维数组 <code>edges</code>&nbsp;表示一个 <code>n</code>&nbsp;个点的无向图，其中&nbsp;<code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, length<sub>i</sub>]</code>&nbsp;表示节点&nbsp;<code>u<sub>i</sub></code> 和节点&nbsp;<code>v<sub>i</sub></code>&nbsp;之间有一条需要&nbsp;<code>length<sub>i</sub></code>&nbsp;单位时间通过的无向边。</p>

<p>同时给你一个数组&nbsp;<code>disappear</code>&nbsp;，其中&nbsp;<code>disappear[i]</code>&nbsp;表示节点 <code>i</code>&nbsp;从图中消失的时间点，在那一刻及以后，你无法再访问这个节点。</p>

<p><strong>注意</strong>，图有可能一开始是不连通的，两个节点之间也可能有多条边。</p>

<p>请你返回数组&nbsp;<code>answer</code>&nbsp;，<code>answer[i]</code>&nbsp;表示从节点 <code>0</code>&nbsp;到节点 <code>i</code>&nbsp;需要的 <strong>最少</strong>&nbsp;单位时间。如果从节点 <code>0</code>&nbsp;出发 <strong>无法</strong> 到达节点 <code>i</code>&nbsp;，那么 <code>answer[i]</code>&nbsp;为 <code>-1</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<p><img 10px="" alt="" padding:="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3112.Minimum%20Time%20to%20Visit%20Disappearing%20Nodes/images/example1.png" style="width: 350px; height: 210px;" /></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p style=""><span class="example-io" style="font-size: 8.75px;"><b>输入：</b></span><span class="example-io" style="font-size: 0.85rem; font-family: Menlo, sans-serif;">n = 3, edges = [[0,1,2],[1,2,1],[0,2,4]], disappear = [1,1,5]</span></p>

<p style=""><span class="example-io" style="font-size: 8.75px;"><b>输出：</b></span><span class="example-io" style="font-size: 0.85rem; font-family: Menlo, sans-serif;">[0,-1,4]</span></p>

<p style="font-size: 0.875rem;"><strong>解释：</strong></p>

<p style="font-size: 0.875rem;">我们从节点 0 出发，目的是用最少的时间在其他节点消失之前到达它们。</p>

<ul style="font-size: 0.875rem;">
	<li>对于节点 0 ，我们不需要任何时间，因为它就是我们的起点。</li>
	<li>对于节点 1 ，我们需要至少 2 单位时间，通过&nbsp;<code>edges[0]</code>&nbsp;到达。但当我们到达的时候，它已经消失了，所以我们无法到达它。</li>
	<li>对于节点 2 ，我们需要至少 4 单位时间，通过&nbsp;<code>edges[2]</code>&nbsp;到达。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<p><img 10px="" alt="" padding:="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3112.Minimum%20Time%20to%20Visit%20Disappearing%20Nodes/images/example2.png" style="width: 350px; height: 210px;" /></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p style=""><span class="example-io" style="font-size: 8.75px;"><b>输入：</b></span><span class="example-io" style="font-size: 0.85rem; font-family: Menlo, sans-serif;">n = 3, edges = [[0,1,2],[1,2,1],[0,2,4]], disappear = [1,3,5]</span></p>

<p style=""><span class="example-io" style="font-size: 8.75px;"><b>输出：</b></span><span class="example-io" style="font-size: 0.85rem; font-family: Menlo, sans-serif;">[0,2,3]</span></p>

<p style="font-size: 0.875rem;"><strong>解释：</strong></p>

<p style="font-size: 0.875rem;">我们从节点 0 出发，目的是用最少的时间在其他节点消失之前到达它们。</p>

<ul style="font-size: 0.875rem;">
	<li>对于节点 0 ，我们不需要任何时间，因为它就是我们的起点。</li>
	<li>对于节点 1 ，我们需要至少 2 单位时间，通过&nbsp;<code>edges[0]</code>&nbsp;到达。</li>
	<li>对于节点 2&nbsp;，我们需要至少 3&nbsp;单位时间，通过&nbsp;<code>edges[0]</code>&nbsp;和 <code>edges[1]</code>&nbsp;到达。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block" style="border-color: var(--border-tertiary); border-left-width: 2px; color: var(--text-secondary); margin-bottom: 1rem; margin-top: 1rem; overflow: visible; padding-left: 1rem;">
<p><span class="example-io"><b>输入：</b>n = 2, edges = [[0,1,1]], disappear = [1,1]</span></p>

<p><span class="example-io"><b>输出：</b>[0,-1]</span></p>

<p><strong>解释：</strong></p>

<p>当我们到达节点 1 的时候，它恰好消失，所以我们无法到达节点 1 。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= edges.length &lt;= 10<sup>5</sup></code></li>
	<li><code>edges[i] == [u<sub>i</sub>, v<sub>i</sub>, length<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>1 &lt;= length<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
	<li><code>disappear.length == n</code></li>
	<li><code>1 &lt;= disappear[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python

```

```java

```

```cpp

```

```go

```

<!-- tabs:end -->

<!-- end -->
