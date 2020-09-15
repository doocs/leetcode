# [1135. 最低成本联通所有城市](https://leetcode-cn.com/problems/connecting-cities-with-minimum-cost)

[English Version](/solution/1100-1199/1135.Connecting%20Cities%20With%20Minimum%20Cost/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
<p>想象一下你是个城市基建规划者，地图上有 <code>N</code> 座城市，它们按以 <code>1</code> 到 <code>N</code> 的次序编号。</p>

<p>给你一些可连接的选项 <code>conections</code>，其中每个选项 <code>conections[i] = [city1, city2, cost]</code> 表示将城市 <code>city1</code> 和城市 <code>city2</code> 连接所要的成本。（<strong>连接是双向的</strong>，也就是说城市 <code>city1</code> 和城市 <code>city2</code> 相连也同样意味着城市 <code>city2</code> 和城市 <code>city1</code> 相连）。</p>

<p>返回使得每对城市间都存在将它们连接在一起的连通路径（可能长度为 1 的）最小成本。该最小成本应该是所用全部连接代价的综合。如果根据已知条件无法完成该项任务，则请你返回 -1。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

![](./images/1314_ex2.png)

<pre><strong>输入：</strong>N = 3, conections = [[1,2,5],[1,3,6],[2,3,1]]
<strong>输出：</strong>6
<strong>解释：</strong>
选出任意 2 条边都可以连接所有城市，我们从中选取成本最小的 2 条。
</pre>

<p><strong>示例 2：</strong></p>

![](./images/1314_ex1.png)

<pre><strong>输入：</strong>N = 4, conections = [[1,2,3],[3,4,4]]
<strong>输出：</strong>-1
<strong>解释： </strong>
即使连通所有的边，也无法连接所有城市。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 <= N <= 10000</code></li>
	<li><code>1 <= conections.length <= 10000</code></li>
	<li><code>1 <= conections[i][0], conections[i][1] <= N</code></li>
	<li><code>0 <= conections[i][2] <= 10^5</code></li>
	<li><code>conections[i][0] != conections[i][1]</code></li>
</ol>



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