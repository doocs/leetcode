# [1548. 图中最相似的路径](https://leetcode.cn/problems/the-most-similar-path-in-a-graph)

[English Version](/solution/1500-1599/1548.The%20Most%20Similar%20Path%20in%20a%20Graph/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>我们有&nbsp;<code>n</code>&nbsp;座城市和&nbsp;<code>m</code>&nbsp;条双向道路&nbsp;<code>roads</code>&nbsp;，其中&nbsp;<code>roads[i] = [a<sub>i</sub>, b<sub>i</sub>]</code>&nbsp;连接城市&nbsp;<code>a<sub>i</sub></code>&nbsp;和城市&nbsp;<code>b<sub>i</sub></code>。每个城市的名称由字符串数组&nbsp;<code>names</code>&nbsp;中给出的三个大写英文字母组成。从任意城市&nbsp;<code>x</code>&nbsp;出发，你可以到达任意城市&nbsp;<code>y</code> ，其中&nbsp;<code>y != x</code>&nbsp;（即：城市和道路形成一张无向连通图）。</p>

<p>给定一个字符串数组&nbsp;<code>targetPath</code>，你需要找出图中与&nbsp;<code>targetPath</code>&nbsp;的<strong> 长度相同</strong> 且<strong> 编辑距离</strong><strong>最小</strong> 的路径。</p>

<p>你需要返回<em> </em><strong>编辑距离最小的路径中节点的顺序</strong><em> </em>。该路径应当与&nbsp;<code>targetPath</code>&nbsp;的长度相等，且路径需有效（即：&nbsp;<code>ans[i]</code>&nbsp;和&nbsp;<code>ans[i + 1]</code>&nbsp;间应存在直接连通的道路）。如果有多个答案，返回任意一个。</p>

<p><strong>编辑距离</strong> 的定义如下：</p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1548.The%20Most%20Similar%20Path%20in%20a%20Graph/images/edit.jpg" /></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1548.The%20Most%20Similar%20Path%20in%20a%20Graph/images/e1.jpg" style="height: 300px; width: 213px;" /></p>

<pre>
<strong>输入：</strong>n = 5, roads = [[0,2],[0,3],[1,2],[1,3],[1,4],[2,4]], names = ["ATL","PEK","LAX","DXB","HND"], targetPath = ["ATL","DXB","HND","LAX"]
<strong>输出：</strong>[0,2,4,2]
<strong>解释：</strong>[0,2,4,2], [0,3,0,2] 和 [0,3,1,2] 都是正确答案。
[0,2,4,2] 等价于 ["ATL","LAX","HND","LAX"] ，与 targetPath 的编辑距离 = 1。
[0,3,0,2] 等价于 ["ATL","DXB","ATL","LAX"] ，与 targetPath 的编辑距离 = 1。
[0,3,1,2] 等价于 ["ATL","DXB","PEK","LAX"] ，与 targetPath 的编辑距离 = 1。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1548.The%20Most%20Similar%20Path%20in%20a%20Graph/images/e2.jpg" style="height: 200px; width: 200px;" /></p>

<pre>
<strong>输入：</strong>n = 4, roads = [[1,0],[2,0],[3,0],[2,1],[3,1],[3,2]], names = ["ATL","PEK","LAX","DXB"], targetPath = ["ABC","DEF","GHI","JKL","MNO","PQR","STU","VWX"]
<strong>输出：</strong>[0,1,0,1,0,1,0,1]
<strong>解释：</strong>任意路径与 targetPath 的编辑距离都等于 8。
</pre>

<p><strong>示例 3：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1548.The%20Most%20Similar%20Path%20in%20a%20Graph/images/e3.jpg" style="height: 106px; width: 600px;" /></strong></p>

<pre>
<strong>输入：</strong>n = 6, roads = [[0,1],[1,2],[2,3],[3,4],[4,5]], names = ["ATL","PEK","LAX","ATL","DXB","HND"], targetPath = ["ATL","DXB","HND","DXB","ATL","LAX","PEK"]
<strong>输出：</strong>[3,4,5,4,3,2,1]
<strong>解释：</strong>[3,4,5,4,3,2,1] 是唯一与 targetPath 的编辑距离 = 0 的路径。
该路径等价于 ["ATL","DXB","HND","DXB","ATL","LAX","PEK"]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 100</code></li>
	<li><code>m == roads.length</code></li>
	<li><code>n - 1 &lt;= m &lt;= (n * (n - 1) / 2)</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub>&nbsp;</code></li>
	<li>给定的图保证是<strong>连通</strong>的，任意两个节点<strong>至多有一个</strong>直接连通的道路。</li>
	<li><code>names.length == n</code></li>
	<li><code>names[i].length == 3</code></li>
	<li><code>names[i]</code>&nbsp;包含大写英文字母。</li>
	<li>可能有两个名称<strong>相同</strong>的城市。</li>
	<li><code>1 &lt;= targetPath.length &lt;= 100</code></li>
	<li><code>targetPath[i].length == 3</code></li>
	<li><code>targetPath[i]</code> 由大写英文字母组成。</li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>如果路径中每个节点只可访问一次，你该如何修改你的答案？</p>

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
