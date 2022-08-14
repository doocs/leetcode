# [1627. 带阈值的图连通性](https://leetcode.cn/problems/graph-connectivity-with-threshold)

[English Version](/solution/1600-1699/1627.Graph%20Connectivity%20With%20Threshold/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有 <code>n</code> 座城市，编号从 <code>1</code> 到 <code>n</code> 。编号为 <code>x</code> 和 <code>y</code> 的两座城市直接连通的前提是： <code>x</code> 和 <code>y</code> 的公因数中，至少有一个 <strong>严格大于</strong> 某个阈值 <code>threshold</code> 。更正式地说，如果存在整数 <code>z</code> ，且满足以下所有条件，则编号 <code>x</code> 和 <code>y</code> 的城市之间有一条道路：</p>

<ul>
	<li><code>x % z == 0</code></li>
	<li><code>y % z == 0</code></li>
	<li><code>z > threshold</code></li>
</ul>

<p>给你两个整数 <code>n</code> 和 <code>threshold</code> ，以及一个待查询数组，请你判断每个查询<code> queries[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> 指向的城市 <code>a<sub>i</sub></code> 和 <code>b<sub>i</sub></code> 是否连通（即，它们之间是否存在一条路径）。</p>

<p>返回数组 <code>answer</code> ，其中<code>answer.length == queries.length</code> 。如果第 <code>i</code> 个查询中指向的城市 <code>a<sub>i</sub></code> 和 <code>b<sub>i</sub></code> 连通，则 <code>answer[i]</code> 为 <code>true</code> ；如果不连通，则 <code>answer[i]</code> 为 <code>false</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1627.Graph%20Connectivity%20With%20Threshold/images/ex1.jpg" style="width: 382px; height: 181px;" /></p>

<p> </p>

<pre>
<strong>输入：</strong>n = 6, threshold = 2, queries = [[1,4],[2,5],[3,6]]
<strong>输出：</strong>[false,false,true]
<strong>解释：</strong>每个数的因数如下：
1:   1
2:   1, 2
3:   1, <strong>3</strong>
4:   1, 2, <strong>4</strong>
5:   1, <strong>5</strong>
6:   1, 2, <strong>3</strong>, <strong>6</strong>
所有大于阈值的的因数已经加粗标识，只有城市 3 和 6 共享公约数 3 ，因此结果是： 
[1,4]   1 与 4 不连通
[2,5]   2 与 5 不连通
[3,6]   3 与 6 连通，存在路径 3--6
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1627.Graph%20Connectivity%20With%20Threshold/images/tmp.jpg" style="width: 532px; height: 302px;" /></p>

<p> </p>

<pre>
<strong>输入：</strong>n = 6, threshold = 0, queries = [[4,5],[3,4],[3,2],[2,6],[1,3]]
<strong>输出：</strong>[true,true,true,true,true]
<strong>解释：</strong>每个数的因数与上一个例子相同。但是，由于阈值为 0 ，所有的因数都大于阈值。因为所有的数字共享公因数 1 ，所以所有的城市都互相连通。
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1627.Graph%20Connectivity%20With%20Threshold/images/ex3.jpg" style="width: 282px; height: 282px;" /></p>

<p> </p>

<pre>
<strong>输入：</strong>n = 5, threshold = 1, queries = [[4,5],[4,5],[3,2],[2,3],[3,4]]
<strong>输出：</strong>[false,false,false,false,false]
<strong>解释：</strong>只有城市 2 和 4 共享的公约数 2 严格大于阈值 1 ，所以只有这两座城市是连通的。
注意，同一对节点 [x, y] 可以有多个查询，并且查询 [x，y] 等同于查询 [y，x] 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 <= n <= 10<sup>4</sup></code></li>
	<li><code>0 <= threshold <= n</code></li>
	<li><code>1 <= queries.length <= 10<sup>5</sup></code></li>
	<li><code>queries[i].length == 2</code></li>
	<li><code>1 <= a<sub>i</sub>, b<sub>i</sub> <= cities</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
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
