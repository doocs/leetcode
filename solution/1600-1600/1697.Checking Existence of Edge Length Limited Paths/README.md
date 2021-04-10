# [1697. 检查边长度限制的路径是否存在](https://leetcode-cn.com/problems/checking-existence-of-edge-length-limited-paths)

[English Version](/solution/1600-1600/1697.Checking%20Existence%20of%20Edge%20Length%20Limited%20Paths/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <code>n</code> 个点组成的无向图边集 <code>edgeList</code> ，其中 <code>edgeList[i] = [u<sub>i</sub>, v<sub>i</sub>, dis<sub>i</sub>]</code> 表示点 <code>u<sub>i</sub></code> 和点 <code>v<sub>i</sub></code> 之间有一条长度为 <code>dis<sub>i</sub></code> 的边。请注意，两个点之间可能有 <strong>超过一条边 </strong>。</p>

<p>给你一个查询数组<code>queries</code> ，其中 <code>queries[j] = [p<sub>j</sub>, q<sub>j</sub>, limit<sub>j</sub>]</code> ，你的任务是对于每个查询 <code>queries[j]</code> ，判断是否存在从 <code>p<sub>j</sub></code> 到 <code>q<sub>j</sub></code><sub> </sub>的路径，且这条路径上的每一条边都 <strong>严格小于</strong> <code>limit<sub>j</sub></code> 。</p>

<p>请你返回一个 <b>布尔数组</b><em> </em><code>answer</code><em> </em>，其中<em> </em><code>answer.length == queries.length</code> ，当 <code>queries[j]</code> 的查询结果为 <code>true</code> 时， <code>answer</code> 第<em> </em><code>j</code> 个值为<em> </em><code>true</code><em> </em>，否则为 <code>false</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

![](./images/h.png)

<pre>
<b>输入：</b>n = 3, edgeList = [[0,1,2],[1,2,4],[2,0,8],[1,0,16]], queries = [[0,1,2],[0,2,5]]
<b>输出：</b>[false,true]
<b>解释：</b>上图为给定的输入数据。注意到 0 和 1 之间有两条重边，分别为 2 和 16 。
对于第一个查询，0 和 1 之间没有小于 2 的边，所以我们返回 false 。
对于第二个查询，有一条路径（0 -> 1 -> 2）两条边都小于 5 ，所以这个查询我们返回 true 。
</pre>

<p><strong>示例 2：</strong></p>

![](./images/q.png)

<pre>
<b>输入：</b>n = 5, edgeList = [[0,1,10],[1,2,5],[2,3,9],[3,4,13]], queries = [[0,4,14],[1,4,13]]
<b>输出：</b>[true,false]
<b>解释：</b>上图为给定数据。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 <= n <= 10<sup>5</sup></code></li>
	<li><code>1 <= edgeList.length, queries.length <= 10<sup>5</sup></code></li>
	<li><code>edgeList[i].length == 3</code></li>
	<li><code>queries[j].length == 3</code></li>
	<li><code>0 <= u<sub>i</sub>, v<sub>i</sub>, p<sub>j</sub>, q<sub>j</sub> <= n - 1</code></li>
	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
	<li><code>p<sub>j</sub> != q<sub>j</sub></code></li>
	<li><code>1 <= dis<sub>i</sub>, limit<sub>j</sub> <= 10<sup>9</sup></code></li>
	<li>两个点之间可能有 <strong>多条</strong> 边。</li>
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
