---
comments: true
difficulty: 中等
---

<!-- problem:start -->

# [4122. 信号激活的最早时刻](https://leetcode.cn/problems/earliest-signal-activation-times)

[English Version](/solution/4100-4199/4122.Earliest%20Signal%20Activation%20Times/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数 <code>n</code> 和一张包含 <code>n</code> 个节点的 <strong>有向</strong> 图，节点编号从 0 到 <code>n - 1</code>。该图用二维数组 <code>edges</code> 表示，其中 <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, delay<sub>i</sub>]</code> 表示一条从节点 <code>u<sub>i</sub></code> 指向节点 <code>v<sub>i</sub></code> 的边，信号沿这条边传播需要 <code>delay<sub>i</sub></code> 个时间单位。</p>

<p>如果存在一条从节点 <code>u</code> 指向节点 <code>v</code> 的边，则称节点 <code>u</code> 是节点 <code>v</code> 的一个 <strong>入邻居</strong>。</p>

<p>节点 0 在时刻 0 处于 <strong>激活</strong> 状态，其余节点初始时均未激活。</p>

<p>当一个节点在时刻 <code>t</code> 激活时，它会 <strong>立即</strong> 沿每条 <strong>出边</strong> 各发送一个信号。节点一旦激活，就会保持激活状态，不会再次发送这些信号。沿一条延迟为 <code>d</code> 的边发送的信号会在时刻 <code>t + d</code> 到达。</p>

<p>另给你一个长度为 <code>n</code> 的整数数组 <code>requiredSignals</code>，其中 <code>requiredSignals[i]</code> 表示节点 <code>i</code> 在激活之前 <strong>必须</strong> 从多少个 <strong>不同的入邻居</strong> 收到信号。</p>

<p>对于尚未激活的节点 <code>i</code>，它会在收到来自 <strong>至少</strong> <code>requiredSignals[i]</code> 个 <strong>不同的入邻居</strong> 的信号的 <strong>最早</strong> 时刻 <code>t</code> 激活。</p>

<p>返回一个长度为 <code>n</code> 的数组 <code>ans</code>，其中 <code>ans[i]</code> 是节点 <code>i</code> 激活的 <strong>最早</strong> 时刻。如果节点 <code>i</code> <strong>永远不会</strong> 激活，则 <code>ans[i]</code> 应为 -1。</p>

<p><strong>注意：</strong> <code>requiredSignals[0]</code> 的值不影响结果，因为节点 0 在时刻 0 就已激活，并在该时刻沿出边发送信号。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 3, edges = [[0,1,1],[0,2,1]], requiredSignals = [1,1,2]</span></p>

<p><strong>输出：</strong> <span class="example-io">[0,1,-1]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>节点 0 在时刻 0 处于激活状态，向节点 1 和节点 2 发送信号。</li>
	<li>在时刻 1，两个信号均已到达。节点 1 收到了所需的 1 个信号，因此激活。</li>
	<li>节点 2 需要收到来自 2 个不同入邻居的信号，但它只有一个入邻居，因此永远不会激活。</li>
	<li>因此，<code>ans = [0, 1, -1]</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 4, edges = [[0,1,1],[0,2,1],[1,3,2],[2,3,2]], requiredSignals = [3,1,1,2]</span></p>

<p><strong>输出：</strong> <span class="example-io">[0,1,1,3]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>尽管 <code>requiredSignals[0] = 3</code>，节点 0 在时刻 0 仍处于激活状态，并向节点 1 和节点 2 发送信号。</li>
	<li>在时刻 1，节点 1 和节点 2 各自收到所需的 1 个信号并激活，随后各自立即向节点 3 发送一个信号。</li>
	<li>两个信号都在时刻 <code>1 + 2 = 3</code> 到达节点 3。它们来自两个不同的入邻居，因此节点 3 在时刻 3 激活。</li>
	<li>因此，<code>ans = [0, 1, 1, 3]</code>。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 4, edges = [[0,1,2],[1,2,3],[0,2,5],[2,3,1]], requiredSignals = [3,1,2,1]</span></p>

<p><strong>输出：</strong> <span class="example-io">[0,2,5,6]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>节点 0 在时刻 0 处于激活状态，向节点 1 和节点 2 发送信号。</li>
	<li>节点 1 在时刻 2 收到信号，随即激活并向节点 2 发送一个信号。</li>
	<li>在时刻 5，节点 2 收到来自节点 0 和节点 1 的信号，其中来自节点 1 的信号到达时刻为 <code>2 + 3 = 5</code>。节点 2 已收到来自 2 个不同入邻居的信号，满足激活条件，于是激活并向节点 3 发送一个信号。</li>
	<li>节点 3 在时刻 <code>5 + 1 = 6</code> 收到该信号并激活。</li>
	<li>因此，<code>ans = [0, 2, 5, 6]</code>。</li>
</ul>
</div>

<p><strong class="example">示例 4：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 4, edges = [[0,1,2],[1,0,1],[1,2,1],[2,3,1],[3,2,1]], requiredSignals = [3,1,2,1]</span></p>

<p><strong>输出：</strong> <span class="example-io">[0,2,-1,-1]</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>节点 0 在时刻 0 处于激活状态，向节点 1 发送一个信号。</li>
	<li>节点 1 在时刻 2 激活，并向节点 0 和节点 2 发送信号。这两个信号都在时刻 3 到达。</li>
	<li>节点 0 已经激活，因此收到另一个信号后不会再次发送信号。</li>
	<li>节点 2 必须收到节点 1 和节点 3 各自发出的信号，但它只收到了节点 1 发出的信号。节点 3 正在等待来自节点 2 的信号，因此节点 2 和节点 3 都不会激活。</li>
	<li>因此，<code>ans = [0, 2, -1, -1]</code>。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n == requiredSignals.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= edges.length &lt;= 10<sup>5</sup></code></li>
	<li><code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, delay<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
	<li><code>1 &lt;= delay<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li>从任意节点 <code>u<sub>i</sub></code> 到任意节点 <code>v<sub>i</sub></code> 至多有一条有向边。</li>
	<li><code>1 &lt;= requiredSignals[i] &lt;= n - 1</code></li>
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
