# [2467. 树上最大得分和路径](https://leetcode.cn/problems/most-profitable-path-in-a-tree)

[English Version](/solution/2400-2499/2467.Most%20Profitable%20Path%20in%20a%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一个 <code>n</code>&nbsp;个节点的无向树，节点编号为&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n - 1</code>&nbsp;，树的根结点是&nbsp;<code>0</code>&nbsp;号节点。给你一个长度为 <code>n - 1</code>&nbsp;的二维整数数组&nbsp;<code>edges</code>&nbsp;，其中&nbsp;<code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code>&nbsp;，表示节点&nbsp;<code>a<sub>i</sub></code> 和&nbsp;<code>b<sub>i</sub></code>&nbsp;在树中有一条边。</p>

<p>在每一个节点&nbsp;<code>i</code>&nbsp;处有一扇门。同时给你一个都是偶数的数组&nbsp;<code>amount</code>&nbsp;，其中&nbsp;<code>amount[i]</code>&nbsp;表示：</p>

<ul>
	<li>如果 <code>amount[i]</code>&nbsp;的值是负数，那么它表示打开节点&nbsp;<code>i</code>&nbsp;处门扣除的分数。</li>
	<li>如果 <code>amount[i]</code>&nbsp;的值是正数，那么它表示打开节点 <code>i</code>&nbsp;处门加上的分数。</li>
</ul>

<p>游戏按照如下规则进行：</p>

<ul>
	<li>一开始，Alice 在节点&nbsp;<code>0</code>&nbsp;处，Bob 在节点&nbsp;<code>bob</code>&nbsp;处。</li>
	<li>每一秒钟，Alice 和 Bob <strong>分别</strong>&nbsp;移动到相邻的节点。Alice 朝着某个&nbsp;<strong>叶子结点</strong>&nbsp;移动，Bob 朝着节点&nbsp;<code>0</code>&nbsp;移动。</li>
	<li>对于他们之间路径上的 <strong>每一个</strong>&nbsp;节点，Alice 和 Bob 要么打开门并扣分，要么打开门并加分。注意：
	<ul>
		<li>如果门 <strong>已经打开</strong>&nbsp;（被另一个人打开），不会有额外加分也不会扣分。</li>
		<li>如果&nbsp;Alice 和 Bob <strong>同时</strong>&nbsp;到达一个节点，他们会共享这个节点的加分或者扣分。换言之，如果打开这扇门扣&nbsp;<code>c</code>&nbsp;分，那么&nbsp;Alice 和 Bob 分别扣&nbsp;<code>c / 2</code>&nbsp;分。如果这扇门的加分为&nbsp;<code>c</code>&nbsp;，那么他们分别加&nbsp;<code>c / 2</code>&nbsp;分。</li>
	</ul>
	</li>
	<li>如果 Alice 到达了一个叶子结点，她会停止移动。类似的，如果&nbsp;Bob 到达了节点&nbsp;<code>0</code>&nbsp;，他也会停止移动。注意这些事件互相 <strong>独立</strong>&nbsp;，不会影响另一方移动。</li>
</ul>

<p>请你返回&nbsp;Alice 朝最优叶子结点移动的 <strong>最大</strong>&nbsp;净得分。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2467.Most%20Profitable%20Path%20in%20a%20Tree/images/eg1.png" style="width: 275px; height: 275px;"></p>

<pre><b>输入：</b>edges = [[0,1],[1,2],[1,3],[3,4]], bob = 3, amount = [-2,4,2,-4,6]
<b>输出：</b>6
<b>解释：</b>
上图展示了输入给出的一棵树。游戏进行如下：
- Alice 一开始在节点 0 处，Bob 在节点 3 处。他们分别打开所在节点的门。
  Alice 得分为 -2 。
- Alice 和 Bob 都移动到节点 1 。
&nbsp; 因为他们同时到达这个节点，他们一起打开门并平分得分。
&nbsp; Alice 的得分变为 -2 + (4 / 2) = 0 。
- Alice 移动到节点 3 。因为 Bob 已经打开了这扇门，Alice 得分不变。
&nbsp; Bob 移动到节点 0 ，并停止移动。
- Alice 移动到节点 4 并打开这个节点的门，她得分变为 0 + 6 = 6 。
现在，Alice 和 Bob 都不能进行任何移动了，所以游戏结束。
Alice 无法得到更高分数。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2467.Most%20Profitable%20Path%20in%20a%20Tree/images/eg2.png" style="width: 250px; height: 78px;"></p>

<pre><b>输入：</b>edges = [[0,1]], bob = 1, amount = [-7280,2350]
<b>输出：</b>-7280
<b>解释：</b>
Alice 按照路径 0-&gt;1 移动，同时 Bob 按照路径 1-&gt;0 移动。
所以 Alice 只打开节点 0 处的门，她的得分为 -7280 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li><code>edges</code>&nbsp;表示一棵有效的树。</li>
	<li><code>1 &lt;= bob &lt; n</code></li>
	<li><code>amount.length == n</code></li>
	<li><code>amount[i]</code>&nbsp;是范围&nbsp;<code>[-10<sup>4</sup>, 10<sup>4</sup>]</code>&nbsp;之间的一个&nbsp;<strong>偶数</strong>&nbsp;。</li>
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

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
