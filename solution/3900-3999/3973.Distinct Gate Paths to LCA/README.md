---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3973.Distinct%20Gate%20Paths%20to%20LCA/README.md
tags:
    - 位运算
    - 树
    - 深度优先搜索
    - 数组
    - 数学
    - 动态规划
---

<!-- problem:start -->

# [3973. 通往最近公共祖先的不同门径 🔒](https://leetcode.cn/problems/distinct-gate-paths-to-lca)

[English Version](/solution/3900-3999/3973.Distinct%20Gate%20Paths%20to%20LCA/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一棵以节点 0 为根的无向树，共有 <code>n</code> 个节点，编号为 0 到 <code>n - 1</code>。树由数组 <code>parent</code> 表示，其中 <code>parent[i]</code> 表示节点 <code>i</code> 的父节点。</p>

<p>每个节点 <code>i</code> 都拥有三种类型的门，由二维数组 <code>gates</code> 给出，其中 <code>gates[i] = [red<sub>i</sub>, blue<sub>i</sub>, white<sub>i</sub>]</code> 分别表示节点 <code>i</code> 上&nbsp;<strong>红门</strong>、<strong>蓝门&nbsp;</strong>和&nbsp;<strong>白门&nbsp;</strong>的数量。</p>

<ul>
	<li><strong>红门（Red）</strong>：只能使用&nbsp;<strong>红卡&nbsp;</strong>通过。</li>
	<li><strong>蓝门（Blue）</strong>：只能使用&nbsp;<strong>蓝卡&nbsp;</strong>通过。</li>
	<li><strong>白门（White）</strong>：可以使用&nbsp;<strong>任意颜色&nbsp;</strong>的卡通过，但通过后会&nbsp;<strong>翻转&nbsp;</strong>卡片颜色。</li>
</ul>

<p>Alice 和 Bob 分别从给定节点出发，初始持有一张红卡或蓝卡（<code>1</code> 表示红卡，<code>0</code> 表示蓝卡）。两人需要&nbsp;<strong>独立地&nbsp;</strong>沿着树&nbsp;<strong>向上移动</strong>，直到到达他们的&nbsp;<strong>最近公共祖先（LCA）</strong>。</p>

<p>对于每一步移动，只有当当前节点存在至少一个能够被当前卡片使用的门时，才能从该节点移动到父节点。<strong>白门可以被使用任意次，每次都会翻转卡片颜色。</strong></p>

<p><strong>移动规则（一次移动指从 <code>u</code> 移动到 <code>parent[u]</code>）：</strong></p>

<ul>
	<li>只能沿树向上朝根节点移动。</li>
	<li>在节点 <code>u</code>，必须选择&nbsp;<strong>恰好一个&nbsp;</strong>具体的门实例。即使多个门类型相同，它们也视为&nbsp;<strong>不同的门实例</strong>，分别计数。</li>
	<li>若当前持有&nbsp;<strong>红卡</strong>：可以使用一个红门并保持红卡；或使用一个白门，并将卡片变为蓝卡。</li>
	<li>若当前持有&nbsp;<strong>蓝卡</strong>：可以使用一个蓝门并保持蓝卡；或使用一个白门，并将卡片变为红卡。</li>
	<li>若当前节点不存在可使用的门，则移动序列立即终止。</li>
</ul>

<p>同时给定一个二维数组 <code>queries</code>，其中 <code>queries[i] = [aNode<sub>i</sub>, aCard<sub>i</sub>, bNode<sub>i</sub>, bCard<sub>i</sub>]</code>：</p>

<ul>
	<li><code>aNode<sub>i</sub></code>、<code>aCard<sub>i</sub></code>：表示 Alice 的起始节点及初始卡片。</li>
	<li><code>bNode<sub>i</sub></code>、<code>bCard<sub>i</sub></code>：表示 Bob 的起始节点及初始卡片。</li>
</ul>

<p>对于每个查询，计算 Alice 和 Bob 分别成功到达其&nbsp;<strong>最近公共祖先（LCA）</strong>的<strong>不同合法方案数</strong>，结果对 <code>10<sup>9</sup> + 7</code> 取模。</p>

<p>所有查询计算完成后，返回这些结果的&nbsp;<strong>按位异或（bitwise XOR）</strong>值。</p>

<p><strong>注意：</strong></p>

<ul>
	<li>若 Alice 或 Bob 任意一人的门使用方案不同，则认为两种方案不同。</li>
	<li>若某人起始时已经位于 <strong>LCA</strong>，则该人的方案数记为 1。</li>
	<li><strong>最近公共祖先（LCA）</strong>定义为：节点 <code>a</code> 与节点 <code>b</code> 的最低公共祖先（节点本身也视为自己的祖先）。</li>
</ul>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 3, parent = [-1,0,0], gates = [[1,0,1],[0,1,1],[1,1,0]], queries = [[1,0,2,0],[1,1,2,0],[1,0,2,1]]</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
	<thead>
		<tr>
			<th align="center"><code>i</code></th>
			<th align="center">Alice<br />
			[节点, 卡片]</th>
			<th align="center">Bob<br />
			[节点, 卡片]</th>
			<th align="center">LCA</th>
			<th align="center">Alice<br />
			路径</th>
			<th align="center">Bob<br />
			路径</th>
			<th align="center">Alice<br />
			方案数</th>
			<th align="center">Bob<br />
			方案数</th>
			<th align="center">总方案数</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td align="center">0</td>
			<td align="center">[1, 0]：蓝卡</td>
			<td align="center">[2, 0]：蓝卡</td>
			<td align="center">0</td>
			<td align="center">1 → 0</td>
			<td align="center">2 → 0</td>
			<td align="center">2（节点 1 的 1 个蓝门 + 1 个白门）</td>
			<td align="center">1（节点 2 的 1 个蓝门）</td>
			<td align="center">2 × 1 = 2</td>
		</tr>
		<tr>
			<td align="center">1</td>
			<td align="center">[1, 1]：红卡</td>
			<td align="center">[2, 0]：蓝卡</td>
			<td align="center">0</td>
			<td align="center">1 → 0</td>
			<td align="center">2 → 0</td>
			<td align="center">1（节点 1 的 1 个白门）</td>
			<td align="center">1（节点 2 的 1 个蓝门）</td>
			<td align="center">1 × 1 = 1</td>
		</tr>
		<tr>
			<td align="center">2</td>
			<td align="center">[1, 0]：蓝卡</td>
			<td align="center">[2, 1]：红卡</td>
			<td align="center">0</td>
			<td align="center">1 → 0</td>
			<td align="center">2 → 0</td>
			<td align="center">2（节点 1 的 1 个蓝门 + 1 个白门）</td>
			<td align="center">1（节点 2 的 1 个红门）</td>
			<td align="center">2 × 1 = 2</td>
		</tr>
	</tbody>
</table>

<p>因此，所有结果按位异或为：<code>2 XOR 1 XOR 2 = 1</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 3, parent = [-1,0,1], gates = [[0,1,2],[1,0,1],[0,0,3]], queries = [[2,0,1,0],[2,1,0,0],[1,1,2,1]]</span></p>

<p><strong>输出：</strong> <span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<div class="example-block">
<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
	<thead>
		<tr>
			<th align="center"><code>i</code></th>
			<th align="center">Alice<br />
			[节点, 卡片]</th>
			<th align="center">Bob<br />
			[节点, 卡片]</th>
			<th align="center">LCA</th>
			<th align="center">Alice 路径</th>
			<th align="center">Bob 路径</th>
			<th align="center">Alice 方案数</th>
			<th align="center">Bob 方案数</th>
			<th align="center">总方案数</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td align="center">0</td>
			<td align="center">[2, 0]：蓝卡</td>
			<td align="center">[1, 0]：蓝卡</td>
			<td align="center">1</td>
			<td align="center">2 → 1</td>
			<td align="center">1</td>
			<td align="center">3（节点 2 的 3 个白门）</td>
			<td align="center">1（无需移动）</td>
			<td align="center">3 × 1 = 3</td>
		</tr>
		<tr>
			<td align="center">1</td>
			<td align="center">[2, 1]：红卡</td>
			<td align="center">[0, 0]：蓝卡</td>
			<td align="center">0</td>
			<td align="center">2 → 1 → 0</td>
			<td align="center">0</td>
			<td align="center">3（节点 2 的 3 个白门）× 1（节点 1 的 1 个白门）= 3</td>
			<td align="center">1（无需移动）</td>
			<td align="center">3 × 1 = 3</td>
		</tr>
		<tr>
			<td align="center">2</td>
			<td align="center">[1, 1]：红卡</td>
			<td align="center">[2, 1]：红卡</td>
			<td align="center">1</td>
			<td align="center">1</td>
			<td align="center">2 → 1</td>
			<td align="center">1（无需移动）</td>
			<td align="center">3（节点 2 的 3 个白门）</td>
			<td align="center">1 × 3 = 3</td>
		</tr>
	</tbody>
</table>

<p>因此，所有结果按位异或为：<code>3 XOR 3 XOR 3 = 3</code>。</p>

<p>&nbsp;</p>
</div>
</div>

<p><strong>提示</strong>​​​：</p>

<ul>
	<li><code>2 &lt;= n &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>n == parent.length == gates.length</code></li>
	<li><code>parent[0] == -1</code></li>
	<li>对于&nbsp;<code>[1, n - 1]</code>&nbsp;中的 <code>i</code>，<code>0 &lt;= parent[i] &lt; n</code></li>
	<li><code>gates[i] == [red<sub>i</sub>, blue<sub>i</sub>, white<sub>i</sub>]</code></li>
	<li><code>0 &lt;= red<sub>i</sub>, blue<sub>i</sub>, white<sub>i</sub> &lt;= 10</code></li>
	<li><code>1 &lt;= queries.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>queries[i] = [aNode<sub>i</sub>, aCard<sub>i</sub>, bNode<sub>i</sub>, bCard<sub>i</sub>]</code></li>
	<li><code>0 &lt;= aNode<sub>i</sub>, bNode<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>0 &lt;= aCard<sub>i</sub>, bCard<sub>i</sub> &lt;= 1</code></li>
	<li>输入保证数组&nbsp;<code>parent</code>&nbsp;表示一棵合法的树。</li>
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
