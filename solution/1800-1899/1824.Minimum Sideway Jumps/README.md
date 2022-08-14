# [1824. 最少侧跳次数](https://leetcode.cn/problems/minimum-sideway-jumps)

[English Version](/solution/1800-1899/1824.Minimum%20Sideway%20Jumps/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code> 的 <strong>3 跑道道路</strong> ，它总共包含 <code>n + 1</code> 个 <strong>点</strong> ，编号为 <code>0</code> 到 <code>n</code> 。一只青蛙从 <code>0</code> 号点第二条跑道 <strong>出发</strong> ，它想要跳到点 <code>n</code> 处。然而道路上可能有一些障碍。</p>

<p>给你一个长度为 <code>n + 1</code> 的数组 <code>obstacles</code> ，其中 <code>obstacles[i]</code> （<b>取值范围从 0 到 3</b>）表示在点 <code>i</code> 处的 <code>obstacles[i]</code> 跑道上有一个障碍。如果 <code>obstacles[i] == 0</code> ，那么点 <code>i</code> 处没有障碍。任何一个点的三条跑道中 <strong>最多有一个</strong> 障碍。</p>

<ul>
	<li>比方说，如果 <code>obstacles[2] == 1</code> ，那么说明在点 2 处跑道 1 有障碍。</li>
</ul>

<p>这只青蛙从点 <code>i</code> 跳到点 <code>i + 1</code> 且跑道不变的前提是点 <code>i + 1</code> 的同一跑道上没有障碍。为了躲避障碍，这只青蛙也可以在 <strong>同一个</strong> 点处 <strong>侧跳</strong> 到 <strong>另外一条</strong> 跑道（这两条跑道可以不相邻），但前提是跳过去的跑道该点处没有障碍。</p>

<ul>
	<li>比方说，这只青蛙可以从点 3 处的跑道 3 跳到点 3 处的跑道 1 。</li>
</ul>

<p>这只青蛙从点 0 处跑道 <code>2</code> 出发，并想到达点 <code>n</code> 处的 <strong>任一跑道</strong> ，请你返回 <strong>最少侧跳次数</strong> 。</p>

<p><strong>注意</strong>：点 <code>0</code> 处和点 <code>n</code> 处的任一跑道都不会有障碍。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1824.Minimum%20Sideway%20Jumps/images/ic234-q3-ex1.png" style="width: 500px; height: 244px;" />
<pre>
<b>输入：</b>obstacles = [0,1,2,3,0]
<b>输出：</b>2 
<b>解释：</b>最优方案如上图箭头所示。总共有 2 次侧跳（红色箭头）。
注意，这只青蛙只有当侧跳时才可以跳过障碍（如上图点 2 处所示）。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1824.Minimum%20Sideway%20Jumps/images/ic234-q3-ex2.png" style="width: 500px; height: 196px;" />
<pre>
<b>输入：</b>obstacles = [0,1,1,3,3,0]
<b>输出：</b>0
<b>解释：</b>跑道 2 没有任何障碍，所以不需要任何侧跳。
</pre>

<p><strong>示例 3：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1824.Minimum%20Sideway%20Jumps/images/ic234-q3-ex3.png" style="width: 500px; height: 196px;" />
<pre>
<b>输入：</b>obstacles = [0,2,1,0,3,0]
<b>输出：</b>2
<b>解释：</b>最优方案如上图所示。总共有 2 次侧跳。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>obstacles.length == n + 1</code></li>
	<li><code>1 <= n <= 5 * 10<sup>5</sup></code></li>
	<li><code>0 <= obstacles[i] <= 3</code></li>
	<li><code>obstacles[0] == obstacles[n] == 0</code></li>
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
