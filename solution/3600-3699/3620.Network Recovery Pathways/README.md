---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3620.Network%20Recovery%20Pathways/README.md
tags:
    - 图
    - 拓扑排序
    - 数组
    - 二分查找
    - 动态规划
    - 最短路
    - 堆（优先队列）
---

<!-- problem:start -->

# [3620. 恢复网络路径](https://leetcode.cn/problems/network-recovery-pathways)

[English Version](/solution/3600-3699/3620.Network%20Recovery%20Pathways/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个包含 <code>n</code> 个节点（编号从 0 到 <code>n - 1</code>）的有向无环图。图由长度为 <code>m</code> 的二维数组 <code>edges</code> 表示，其中 <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, cost<sub>i</sub>]</code> 表示从节点 <code>u<sub>i</sub></code> 到节点 <code>v<sub>i</sub></code> 的单向通信，恢复成本为 <code>cost<sub>i</sub></code>。</p>

<p>一些节点可能处于离线状态。给定一个布尔数组 <code>online</code>，其中 <code>online[i] = true</code> 表示节点 <code>i</code> 在线。节点 0 和 <code>n - 1</code> 始终在线。</p>

<p>从 0 到 <code>n - 1</code> 的路径如果满足以下条件，那么它是&nbsp;<strong>有效&nbsp;</strong>的：</p>

<ul>
	<li>路径上的所有中间节点都在线。</li>
	<li>路径上所有边的总恢复成本不超过 <code>k</code>。</li>
</ul>

<p>对于每条有效路径，其&nbsp;<strong>分数&nbsp;</strong>定义为该路径上的最小边成本。</p>

<p>返回所有有效路径中的&nbsp;<strong>最大&nbsp;</strong>路径分数（即最大&nbsp;<strong>最小&nbsp;</strong>边成本）。如果没有有效路径，则返回 -1。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">edges = [[0,1,5],[1,3,10],[0,2,3],[2,3,4]], online = [true,true,true,true], k = 10</span></p>

<p><strong>输出:</strong> <span class="example-io">3</span></p>

<p><strong>解释:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3620.Network%20Recovery%20Pathways/images/graph-10.png" style="width: 239px; height: 267px;" /></p>

<ul>
	<li>
	<p>图中有两条从节点 0 到节点 3 的可能路线：</p>

    <ol>
    	<li>
    	<p>路径 <code>0 → 1 → 3</code></p>

    	<ul>
    		<li>
    		<p>总成本 = <code>5 + 10 = 15</code>，超过了 k (<code>15 &gt; 10</code>)，因此此路径无效。</p>
    		</li>
    	</ul>
    	</li>
    	<li>
    	<p>路径 <code>0 → 2 → 3</code></p>

    	<ul>
    		<li>
    		<p>总成本 = <code>3 + 4 = 7 &lt;= k</code>，因此此路径有效。</p>
    		</li>
    		<li>
    		<p>此路径上的最小边成本为 <code>min(3, 4) = 3</code>。</p>
    		</li>
    	</ul>
    	</li>
    </ol>
    </li>
    <li>
    <p>没有其他有效路径。因此，所有有效路径分数中的最大值为 3。</p>
    </li>

</ul>
</div>

<p><strong class="example">示例 2:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">edges = [[0,1,7],[1,4,5],[0,2,6],[2,3,6],[3,4,2],[2,4,6]], online = [true,true,true,false,true], k = 12</span></p>

<p><strong>输出:</strong> <span class="example-io">6</span></p>

<p><strong>解释:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3600-3699/3620.Network%20Recovery%20Pathways/images/graph-11.png" style="width: 343px; height: 194px;" /></p>

<ul>
	<li>
	<p>节点 3 离线，因此任何通过 3 的路径都是无效的。</p>
	</li>
	<li>
	<p>考虑从 0 到 4 的其余路线：</p>

    <ol>
    	<li>
    	<p>路径 <code>0 → 1 → 4</code></p>

    	<ul>
    		<li>
    		<p>总成本 = <code>7 + 5 = 12 &lt;= k</code>，因此此路径有效。</p>
    		</li>
    		<li>
    		<p>此路径上的最小边成本为 <code>min(7, 5) = 5</code>。</p>
    		</li>
    	</ul>
    	</li>
    	<li>
    	<p>路径 <code>0 → 2 → 3 → 4</code></p>

    	<ul>
    		<li>
    		<p>节点 3 离线，因此无论成本多少，此路径无效。</p>
    		</li>
    	</ul>
    	</li>
    	<li>
    	<p>路径 <code>0 → 2 → 4</code></p>

    	<ul>
    		<li>
    		<p>总成本 = <code>6 + 6 = 12 &lt;= k</code>，因此此路径有效。</p>
    		</li>
    		<li>
    		<p>此路径上的最小边成本为 <code>min(6, 6) = 6</code>。</p>
    		</li>
    	</ul>
    	</li>
    </ol>
    </li>
    <li>
    <p>在两条有效路径中，它们的分数分别为 5 和 6。因此，答案是 6。</p>
    </li>

</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>n == online.length</code></li>
	<li><code>2 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= m == edges.length &lt;= min(10<sup>5</sup>, n * (n - 1) / 2)</code></li>
	<li><code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, cost<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt; n</code></li>
	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
	<li><code>0 &lt;= cost<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= k &lt;= 5 * 10<sup>13</sup></code></li>
	<li><code>online[i]</code> 是 <code>true</code> 或 <code>false</code>，且 <code>online[0]</code> 和 <code>online[n - 1]</code> 均为 <code>true</code>。</li>
	<li>给定的图是一个有向无环图。</li>
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
