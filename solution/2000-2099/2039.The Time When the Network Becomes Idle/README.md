# [2039. 网络空闲的时刻](https://leetcode-cn.com/problems/the-time-when-the-network-becomes-idle)

[English Version](/solution/2000-2099/2039.The%20Time%20When%20the%20Network%20Becomes%20Idle/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个有 <code>n</code>&nbsp;个服务器的计算机网络，服务器编号为&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n - 1</code>&nbsp;。同时给你一个二维整数数组&nbsp;<code>edges</code>&nbsp;，其中&nbsp;<code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code>&nbsp;表示服务器&nbsp;<code>u<sub>i</sub></code> 和&nbsp;<code>v<sub>i</sub></code><sub>&nbsp;</sub>之间有一条信息线路，在&nbsp;<strong>一秒</strong>&nbsp;内它们之间可以传输&nbsp;<strong>任意</strong>&nbsp;数目的信息。再给你一个长度为 <code>n</code>&nbsp;且下标从&nbsp;<strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>patience</code>&nbsp;。</p>

<p>题目保证所有服务器都是 <b>相通</b>&nbsp;的，也就是说一个信息从任意服务器出发，都可以通过这些信息线路直接或间接地到达任何其他服务器。</p>

<p>编号为 <code>0</code>&nbsp;的服务器是 <strong>主</strong>&nbsp;服务器，其他服务器为 <strong>数据</strong>&nbsp;服务器。每个数据服务器都要向主服务器发送信息，并等待回复。信息在服务器之间按 <strong>最优</strong>&nbsp;线路传输，也就是说每个信息都会以 <strong>最少时间</strong>&nbsp;到达主服务器。主服务器会处理 <strong>所有</strong>&nbsp;新到达的信息并 <strong>立即</strong>&nbsp;按照每条信息来时的路线 <strong>反方向</strong> 发送回复信息。</p>

<p>在 <code>0</code>&nbsp;秒的开始，所有数据服务器都会发送各自需要处理的信息。从第 <code>1</code>&nbsp;秒开始，<strong>每</strong>&nbsp;一秒最 <strong>开始</strong>&nbsp;时，每个数据服务器都会检查它是否收到了主服务器的回复信息（包括新发出信息的回复信息）：</p>

<ul>
	<li>如果还没收到任何回复信息，那么该服务器会周期性&nbsp;<strong>重发</strong>&nbsp;信息。数据服务器&nbsp;<code>i</code>&nbsp;每&nbsp;<code>patience[i]</code>&nbsp;秒都会重发一条信息，也就是说，数据服务器&nbsp;<code>i</code>&nbsp;在上一次发送信息给主服务器后的 <code>patience[i]</code>&nbsp;秒 <strong>后</strong>&nbsp;会重发一条信息给主服务器。</li>
	<li>否则，该数据服务器&nbsp;<strong>不会重发</strong>&nbsp;信息。</li>
</ul>

<p>当没有任何信息在线路上传输或者到达某服务器时，该计算机网络变为 <strong>空闲</strong>&nbsp;状态。</p>

<p>请返回计算机网络变为 <strong>空闲</strong>&nbsp;状态的&nbsp;<strong>最早秒数</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="example 1" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2039.The%20Time%20When%20the%20Network%20Becomes%20Idle/images/quiet-place-example1.png" style="width: 750px; height: 384px;"></p>

<pre><b>输入：</b>edges = [[0,1],[1,2]], patience = [0,2,1]
<b>输出：</b>8
<strong>解释：</strong>
0 秒最开始时，
- 数据服务器 1 给主服务器发出信息（用 1A 表示）。
- 数据服务器 2 给主服务器发出信息（用 2A 表示）。

1 秒时，
- 信息 1A 到达主服务器，主服务器立刻处理信息 1A 并发出 1A 的回复信息。
- 数据服务器 1 还没收到任何回复。距离上次发出信息过去了 1 秒（1 &lt; patience[1] = 2），所以不会重发信息。
- 数据服务器 2 还没收到任何回复。距离上次发出信息过去了 1 秒（1 == patience[2] = 1），所以它重发一条信息（用 2B 表示）。

2 秒时，
- 回复信息 1A 到达服务器 1 ，服务器 1 不会再重发信息。
- 信息 2A 到达主服务器，主服务器立刻处理信息 2A 并发出 2A 的回复信息。
- 服务器 2 重发一条信息（用 2C 表示）。
...
4 秒时，
- 回复信息 2A 到达服务器 2 ，服务器 2 不会再重发信息。
...
7 秒时，回复信息 2D 到达服务器 2 。

从第 8 秒开始，不再有任何信息在服务器之间传输，也不再有信息到达服务器。
所以第 8 秒是网络变空闲的最早时刻。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="example 2" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2039.The%20Time%20When%20the%20Network%20Becomes%20Idle/images/network_a_quiet_place_2.png" style="width: 100px; height: 85px;"></p>

<pre><b>输入：</b>edges = [[0,1],[0,2],[1,2]], patience = [0,10,10]
<b>输出：</b>3
<b>解释：</b>数据服务器 1 和 2 第 2 秒初收到回复信息。
从第 3 秒开始，网络变空闲。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == patience.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>patience[0] == 0</code></li>
	<li>对于&nbsp;<code>1 &lt;= i &lt; n</code> ，满足&nbsp;<code>1 &lt;= patience[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= edges.length &lt;= min(10<sup>5</sup>, n * (n - 1) / 2)</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt; n</code></li>
	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
	<li>不会有重边。</li>
	<li>每个服务器都直接或间接与别的服务器相连。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **...**

```

```

<!-- tabs:end -->
