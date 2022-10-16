# [2440. 创建价值相同的连通块](https://leetcode.cn/problems/create-components-with-same-value)

[English Version](/solution/2400-2499/2440.Create%20Components%20With%20Same%20Value/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有一棵&nbsp;<code>n</code>&nbsp;个节点的无向树，节点编号为&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n - 1</code>&nbsp;。</p>

<p>给你一个长度为 <code>n</code>&nbsp;下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;，其中&nbsp;<code>nums[i]</code>&nbsp;表示第&nbsp;<code>i</code>&nbsp;个节点的值。同时给你一个长度为 <code>n - 1</code>&nbsp;的二维整数数组&nbsp;<code>edges</code>&nbsp;，其中&nbsp;<code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code>&nbsp;表示节点&nbsp;<code>a<sub>i</sub></code>&nbsp;与&nbsp;<code>b<sub>i</sub></code>&nbsp;之间有一条边。</p>

<p>你可以 <strong>删除</strong>&nbsp;一些边，将这棵树分成几个连通块。一个连通块的 <strong>价值</strong>&nbsp;定义为这个连通块中 <strong>所有</strong> 节点 <code>i</code>&nbsp;对应的 <code>nums[i]</code>&nbsp;之和。</p>

<p>你需要删除一些边，删除后得到的各个连通块的价值都相等。请返回你可以删除的边数&nbsp;<strong>最多</strong>&nbsp;为多少。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2440.Create%20Components%20With%20Same%20Value/images/diagramdrawio.png" style="width: 441px; height: 351px;"></p>

<pre><b>输入：</b>nums = [6,2,2,2,6], edges = [[0,1],[1,2],[1,3],[3,4]] 
<b>输出：</b>2 
<b>解释：</b>上图展示了我们可以删除边 [0,1] 和 [3,4] 。得到的连通块为 [0] ，[1,2,3] 和 [4] 。每个连通块的价值都为 6 。可以证明没有别的更好的删除方案存在了，所以答案为 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [2], edges = []
<b>输出：</b>0
<b>解释：</b>没有任何边可以删除。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>nums.length == n</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 50</code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= edges[i][0], edges[i][1] &lt;= n - 1</code></li>
	<li><code>edges</code>&nbsp;表示一棵合法的树。</li>
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
