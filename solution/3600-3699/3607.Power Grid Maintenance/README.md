---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3607.Power%20Grid%20Maintenance/README.md
rating: 1699
source: 第 457 场周赛 Q2
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 并查集
    - 图
    - 数组
    - 哈希表
    - 有序集合
    - 堆（优先队列）
---

<!-- problem:start -->

# [3607. 电网维护](https://leetcode.cn/problems/power-grid-maintenance)

[English Version](/solution/3600-3699/3607.Power%20Grid%20Maintenance/README_EN.md)

## 题目描述

<!-- description:start -->

<p data-end="401" data-start="120">给你一个整数 <code data-end="194" data-start="191">c</code>，表示 <code data-end="211" data-start="208">c</code> 个电站，每个电站有一个唯一标识符 <code>id</code>，从 1 到 <code>c</code>&nbsp;编号。</p>

<p data-end="401" data-start="120">这些电站通过 <code data-end="295" data-start="292">n</code> 条&nbsp;<strong>双向&nbsp;</strong>电缆互相连接，表示为一个二维数组 <code data-end="357" data-start="344">connections</code>，其中每个元素 <code data-end="430" data-start="405">connections[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> 表示电站 <code>u<sub>i</sub></code> 和电站 <code>v<sub>i</sub></code> 之间的连接。直接或间接连接的电站组成了一个&nbsp;<strong>电网&nbsp;</strong>。</p>

<p data-end="626" data-start="586">最初，<strong>所有&nbsp;</strong>电站均处于在线（正常运行）状态。</p>

<p data-end="720" data-start="628">另给你一个二维数组 <code data-end="667" data-start="658">queries</code>，其中每个查询属于以下&nbsp;<strong>两种类型之一&nbsp;</strong>：</p>

<ul data-end="995" data-start="722">
	<li data-end="921" data-start="722">
	<p data-end="921" data-start="724"><code data-end="732" data-start="724">[1, x]</code>：请求对电站 <code data-end="782" data-start="779">x</code> 进行维护检查。如果电站 <code>x</code> 在线，则它自行解决检查。如果电站 <code>x</code> 已离线，则检查由与 <code>x</code> 同一&nbsp;<strong>电网&nbsp;</strong>中&nbsp;<strong>编号最小&nbsp;</strong>的在线电站解决。如果该电网中&nbsp;<strong>不存在&nbsp;</strong>任何&nbsp;<strong>在线&nbsp;</strong>电站，则返回 -1。</p>
	</li>
	<li data-end="995" data-start="923">
	<p data-end="995" data-start="925"><code data-end="933" data-start="925">[2, x]</code>：电站 <code data-end="946" data-start="943">x</code> 离线（即变为非运行状态）。</p>
	</li>
</ul>

<p data-end="1106" data-start="997">返回一个整数数组，表示按照查询中出现的顺序，所有类型为 <code data-end="1080" data-start="1072">[1, x]</code> 的查询结果。</p>

<p data-end="1106" data-start="997"><strong>注意：</strong>电网的结构是固定的；离线（非运行）的节点仍然属于其所在的电网，且离线操作不会改变电网的连接性。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">c = 5, connections = [[1,2],[2,3],[3,4],[4,5]], queries = [[1,3],[2,1],[1,1],[2,2],[1,2]]</span></p>

<p><strong>输出：</strong> <span class="example-io">[3,2,3]</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3607.Power%20Grid%20Maintenance/images/powergrid.jpg" style="width: 361px; height: 42px;" /></p>

<ul>
	<li data-end="223" data-start="143">最初，所有电站 <code>{1, 2, 3, 4, 5}</code> 都在线，并组成一个电网。</li>
	<li data-end="322" data-start="226">查询 <code>[1,3]</code>：电站 3 在线，因此维护检查由电站 3 自行解决。</li>
	<li data-end="402" data-start="325">查询 <code>[2,1]</code>：电站 1 离线。剩余在线电站为 <code>{2, 3, 4, 5}</code>。</li>
	<li data-end="557" data-start="405">查询 <code>[1,1]</code>：电站 1 离线，因此检查由电网中编号最小的在线电站解决，即电站 2。</li>
	<li data-end="641" data-start="560">查询 <code>[2,2]</code>：电站 2 离线。剩余在线电站为 <code>{3, 4, 5}</code>。</li>
	<li data-end="800" data-start="644">查询 <code>[1,2]</code>：电站 2 离线，因此检查由电网中编号最小的在线电站解决，即电站 3。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">c = 3, connections = [], queries = [[1,1],[2,1],[1,1]]</span></p>

<p><strong>输出：</strong> <span class="example-io">[1,-1]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li data-end="976" data-start="909">没有连接，因此每个电站是一个独立的电网。</li>
	<li data-end="1096" data-start="979">查询 <code>[1,1]</code>：电站 1 在线，且属于其独立电网，因此维护检查由电站 1 自行解决。</li>
	<li data-end="1135" data-start="1099">查询 <code>[2,1]</code>：电站 1 离线。</li>
	<li data-end="1237" data-start="1138">查询 <code>[1,1]</code>：电站 1 离线，且其电网中没有其他电站，因此结果为 -1。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li data-end="155" data-start="139"><code>1 &lt;= c &lt;= 10<sup>5</sup></code></li>
	<li data-end="213" data-start="158"><code>0 &lt;= n == connections.length &lt;= min(10<sup>5</sup>, c * (c - 1) / 2)</code></li>
	<li data-end="244" data-start="216"><code>connections[i].length == 2</code></li>
	<li data-end="295" data-start="247"><code>1 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= c</code></li>
	<li data-end="338" data-start="298"><code>u<sub>i</sub> != v<sub>i</sub></code></li>
	<li data-end="374" data-start="341"><code>1 &lt;= queries.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li data-end="401" data-start="377"><code>queries[i].length == 2</code></li>
	<li data-end="436" data-start="404"><code>queries[i][0]</code> 为 1 或 2。</li>
	<li data-end="462" data-start="439"><code>1 &lt;= queries[i][1] &lt;= c</code></li>
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
