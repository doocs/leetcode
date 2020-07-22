# [1334. 阈值距离内邻居最少的城市](https://leetcode-cn.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance)

## 题目描述
<!-- 这里写题目描述 -->
<p>有 <code>n</code>&nbsp;个城市，按从 <code>0</code> 到 <code>n-1</code>&nbsp;编号。给你一个边数组&nbsp;<code>edges</code>，其中 <code>edges[i] = [from<sub>i</sub>, to<sub>i</sub>, weight<sub>i</sub>]</code>&nbsp;代表&nbsp;<code>from<sub>i</sub></code>&nbsp;和&nbsp;<code>to<sub>i</sub></code><sub>&nbsp;</sub>两个城市之间的双向加权边，距离阈值是一个整数&nbsp;<code>distanceThreshold</code>。</p>

<p>返回能通过某些路径到达其他城市数目最少、且路径距离 <strong>最大</strong> 为&nbsp;<code>distanceThreshold</code>&nbsp;的城市。如果有多个这样的城市，则返回编号最大的城市。</p>

<p>注意，连接城市 <em><strong>i</strong></em> 和 <em><strong>j</strong></em> 的路径的距离等于沿该路径的所有边的权重之和。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/01/26/find_the_city_01.png" style="height: 225px; width: 300px;"></p>

<pre><strong>输入：</strong>n = 4, edges = [[0,1,3],[1,2,1],[1,3,4],[2,3,1]], distanceThreshold = 4
<strong>输出：</strong>3
<strong>解释：</strong>城市分布图如上。
每个城市阈值距离 distanceThreshold = 4 内的邻居城市分别是：
城市 0 -&gt; [城市 1, 城市 2]&nbsp;
城市 1 -&gt; [城市 0, 城市 2, 城市 3]&nbsp;
城市 2 -&gt; [城市 0, 城市 1, 城市 3]&nbsp;
城市 3 -&gt; [城市 1, 城市 2]&nbsp;
城市 0 和 3 在阈值距离 4 以内都有 2 个邻居城市，但是我们必须返回城市 3，因为它的编号最大。
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/01/26/find_the_city_02.png" style="height: 225px; width: 300px;"></strong></p>

<pre><strong>输入：</strong>n = 5, edges = [[0,1,2],[0,4,8],[1,2,3],[1,4,2],[2,3,1],[3,4,1]], distanceThreshold = 2
<strong>输出：</strong>0
<strong>解释：</strong>城市分布图如上。&nbsp;
每个城市阈值距离 distanceThreshold = 2 内的邻居城市分别是：
城市 0 -&gt; [城市 1]&nbsp;
城市 1 -&gt; [城市 0, 城市 4]&nbsp;
城市 2 -&gt; [城市 3, 城市 4]&nbsp;
城市 3 -&gt; [城市 2, 城市 4]
城市 4 -&gt; [城市 1, 城市 2, 城市 3]&nbsp;
城市 0 在阈值距离 4 以内只有 1 个邻居城市。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= edges.length &lt;= n * (n - 1) / 2</code></li>
	<li><code>edges[i].length == 3</code></li>
	<li><code>0 &lt;= from<sub>i</sub> &lt; to<sub>i</sub> &lt; n</code></li>
	<li><code>1 &lt;= weight<sub>i</sub>,&nbsp;distanceThreshold &lt;= 10^4</code></li>
	<li>所有 <code>(from<sub>i</sub>, to<sub>i</sub>)</code>&nbsp;都是不同的。</li>
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