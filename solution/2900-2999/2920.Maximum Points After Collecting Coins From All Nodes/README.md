# [2920. 收集所有金币可获得的最大积分](https://leetcode.cn/problems/maximum-points-after-collecting-coins-from-all-nodes)

[English Version](/solution/2900-2999/2920.Maximum%20Points%20After%20Collecting%20Coins%20From%20All%20Nodes/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>节点 <code>0</code> 处现有一棵由 <code>n</code> 个节点组成的无向树，节点编号从 <code>0</code> 到 <code>n - 1</code> 。给你一个长度为 <code>n - 1</code> 的二维 <strong>整数</strong> 数组 <code>edges</code> ，其中 <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> 表示在树上的节点 <code>a<sub>i</sub></code> 和 <code>b<sub>i</sub></code> 之间存在一条边。另给你一个下标从 <strong>0</strong> 开始、长度为 <code>n</code> 的数组 <code>coins</code> 和一个整数 <code>k</code> ，其中 <code>coins[i]</code> 表示节点 <code>i</code> 处的金币数量。</p>

<p>从根节点开始，你必须收集所有金币。要想收集节点上的金币，必须先收集该节点的祖先节点上的金币。</p>

<p>节点 <code>i</code> 上的金币可以用下述方法之一进行收集：</p>

<ul>
	<li>收集所有金币，得到共计 <code>coins[i] - k</code> 点积分。如果 <code>coins[i] - k</code> 是负数，你将会失去 <code>abs(coins[i] - k)</code> 点积分。</li>
	<li>收集所有金币，得到共计 <code>floor(coins[i] / 2)</code> 点积分。如果采用这种方法，节点 <code>i</code> 子树中所有节点 <code>j</code> 的金币数 <code>coins[j]</code> 将会减少至 <code>floor(coins[j] / 2)</code> 。</li>
</ul>

<p>返回收集 <strong>所有</strong> 树节点的金币之后可以获得的最大积分。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2920.Maximum%20Points%20After%20Collecting%20Coins%20From%20All%20Nodes/ex1-copy.png" style="width: 60px; height: 316px; padding: 10px; background: rgb(255, 255, 255); border-radius: 0.5rem;" />
<pre>
<strong>输入：</strong>edges = [[0,1],[1,2],[2,3]], coins = [10,10,3,3], k = 5
<strong>输出：</strong>11                        
<strong>解释：</strong>
使用第一种方法收集节点 0 上的所有金币。总积分 = 10 - 5 = 5 。
使用第一种方法收集节点 1 上的所有金币。总积分 = 5 + (10 - 5) = 10 。
使用第二种方法收集节点 2 上的所有金币。所以节点 3 上的金币将会变为 floor(3 / 2) = 1 ，总积分 = 10 + floor(3 / 2) = 11 。
使用第二种方法收集节点 3 上的所有金币。总积分 =  11 + floor(1 / 2) = 11.
可以证明收集所有节点上的金币能获得的最大积分是 11 。 
</pre>

<p><strong class="example">示例 2：</strong></p>
<strong class="example"> <img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2920.Maximum%20Points%20After%20Collecting%20Coins%20From%20All%20Nodes/ex2.png" style="width: 140px; height: 147px; padding: 10px; background: #fff; border-radius: .5rem;" /></strong>

<pre>
<strong>输入：</strong>edges = [[0,1],[0,2]], coins = [8,4,4], k = 0
<strong>输出：</strong>16
<strong>解释：</strong>
使用第一种方法收集所有节点上的金币，因此，总积分 = (8 - 0) + (4 - 0) + (4 - 0) = 16 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == coins.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code><font face="monospace">0 &lt;= coins[i] &lt;= 10<sup>4</sup></font></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code><font face="monospace">0 &lt;= edges[i][0], edges[i][1] &lt; n</font></code></li>
	<li><code><font face="monospace">0 &lt;= k &lt;= 10<sup>4</sup></font></code></li>
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

### **C++**

```cpp

```

### **Go**

```go

```

### **...**

```

```

<!-- tabs:end -->
