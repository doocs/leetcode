# [1928. 规定时间内到达终点的最小花费](https://leetcode.cn/problems/minimum-cost-to-reach-destination-in-time)

[English Version](/solution/1900-1999/1928.Minimum%20Cost%20to%20Reach%20Destination%20in%20Time/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一个国家有 <code>n</code> 个城市，城市编号为 <code>0</code> 到 <code>n - 1</code> ，题目保证 <strong>所有城市</strong> 都由双向道路 <b>连接在一起</b> 。道路由二维整数数组 <code>edges</code> 表示，其中 <code>edges[i] = [x<sub>i</sub>, y<sub>i</sub>, time<sub>i</sub>]</code> 表示城市 <code>x<sub>i</sub></code> 和 <code>y<sub>i</sub></code> 之间有一条双向道路，耗费时间为 <code>time<sub>i</sub></code> 分钟。两个城市之间可能会有多条耗费时间不同的道路，但是不会有道路两头连接着同一座城市。</p>

<p>每次经过一个城市时，你需要付通行费。通行费用一个长度为 <code>n</code> 且下标从 <strong>0</strong> 开始的整数数组 <code>passingFees</code> 表示，其中 <code>passingFees[j]</code> 是你经过城市 <code>j</code> 需要支付的费用。</p>

<p>一开始，你在城市 <code>0</code> ，你想要在 <code>maxTime</code> <strong>分钟以内</strong> （包含 <code>maxTime</code> 分钟）到达城市 <code>n - 1</code> 。旅行的 <strong>费用</strong> 为你经过的所有城市 <strong>通行费之和</strong> （<strong>包括</strong> 起点和终点城市的通行费）。</p>

<p>给你 <code>maxTime</code>，<code>edges</code> 和 <code>passingFees</code> ，请你返回完成旅行的 <strong>最小费用</strong> ，如果无法在 <code>maxTime</code> 分钟以内完成旅行，请你返回 <code>-1</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1928.Minimum%20Cost%20to%20Reach%20Destination%20in%20Time/images/leetgraph1-1.png" style="width: 371px; height: 171px;" /></p>

<pre>
<b>输入：</b>maxTime = 30, edges = [[0,1,10],[1,2,10],[2,5,10],[0,3,1],[3,4,10],[4,5,15]], passingFees = [5,1,2,20,20,3]
<b>输出：</b>11
<b>解释：</b>最优路径为 0 -> 1 -> 2 -> 5 ，总共需要耗费 30 分钟，需要支付 11 的通行费。
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1928.Minimum%20Cost%20to%20Reach%20Destination%20in%20Time/images/copy-of-leetgraph1-1.png" style="width: 371px; height: 171px;" /></strong></p>

<pre>
<b>输入：</b>maxTime = 29, edges = [[0,1,10],[1,2,10],[2,5,10],[0,3,1],[3,4,10],[4,5,15]], passingFees = [5,1,2,20,20,3]
<b>输出：</b>48
<b>解释：</b>最优路径为 0 -> 3 -> 4 -> 5 ，总共需要耗费 26 分钟，需要支付 48 的通行费。
你不能选择路径 0 -> 1 -> 2 -> 5 ，因为这条路径耗费的时间太长。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>maxTime = 25, edges = [[0,1,10],[1,2,10],[2,5,10],[0,3,1],[3,4,10],[4,5,15]], passingFees = [5,1,2,20,20,3]
<b>输出：</b>-1
<b>解释：</b>无法在 25 分钟以内从城市 0 到达城市 5 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= maxTime <= 1000</code></li>
	<li><code>n == passingFees.length</code></li>
	<li><code>2 <= n <= 1000</code></li>
	<li><code>n - 1 <= edges.length <= 1000</code></li>
	<li><code>0 <= x<sub>i</sub>, y<sub>i</sub> <= n - 1</code></li>
	<li><code>1 <= time<sub>i</sub> <= 1000</code></li>
	<li><code>1 <= passingFees[j] <= 1000</code> </li>
	<li>图中两个节点之间可能有多条路径。</li>
	<li>图中不含有自环。</li>
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
