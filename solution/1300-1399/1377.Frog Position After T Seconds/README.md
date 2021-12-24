# [1377. T 秒后青蛙的位置](https://leetcode-cn.com/problems/frog-position-after-t-seconds)

[English Version](/solution/1300-1399/1377.Frog%20Position%20After%20T%20Seconds/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一棵由 n 个顶点组成的无向树，顶点编号从 1 到 <code>n</code>。青蛙从 <strong>顶点 1</strong> 开始起跳。规则如下：</p>

<ul>
	<li>在一秒内，青蛙从它所在的当前顶点跳到另一个 <strong>未访问</strong> 过的顶点（如果它们直接相连）。</li>
	<li>青蛙无法跳回已经访问过的顶点。</li>
	<li>如果青蛙可以跳到多个不同顶点，那么它跳到其中任意一个顶点上的机率都相同。</li>
	<li>如果青蛙不能跳到任何未访问过的顶点上，那么它每次跳跃都会停留在原地。</li>
</ul>

<p>无向树的边用数组 <code>edges</code> 描述，其中 <code>edges[i] = [from<sub>i</sub>, to<sub>i</sub>]</code> 意味着存在一条直接连通 <code>from<sub>i</sub></code> 和 <code>to<sub>i</sub></code> 两个顶点的边。</p>

<p>返回青蛙在 <em><code>t</code></em> 秒后位于目标顶点 <em><code>target</code> </em>上的概率。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1377.Frog%20Position%20After%20T%20Seconds/images/frog_2.png" style="height: 236px; width: 350px;"></p>

<pre><strong>输入：</strong>n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 2, target = 4
<strong>输出：</strong>0.16666666666666666 
<strong>解释：</strong>上图显示了青蛙的跳跃路径。青蛙从顶点 1 起跳，第 <strong>1 秒</strong> 有 1/3 的概率跳到顶点 2 ，然后第 <strong>2 秒</strong> 有 1/2 的概率跳到顶点 4，因此青蛙在 2 秒后位于顶点 4 的概率是 1/3 * 1/2 = 1/6 = 0.16666666666666666 。 
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1377.Frog%20Position%20After%20T%20Seconds/images/frog_3.png" style="height: 236px; width: 350px;"></strong></p>

<pre><strong>输入：</strong>n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 1, target = 7
<strong>输出：</strong>0.3333333333333333
<strong>解释：</strong>上图显示了青蛙的跳跃路径。青蛙从顶点 1 起跳，有 1/3 = 0.3333333333333333 的概率能够 <strong>1 秒</strong> 后跳到顶点 7 。 
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 20, target = 6
<strong>输出：</strong>0.16666666666666666
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>edges.length == n-1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>1 &lt;= edges[i][0], edges[i][1] &lt;= n</code></li>
	<li><code>1 &lt;= t&nbsp;&lt;= 50</code></li>
	<li><code>1 &lt;= target&nbsp;&lt;= n</code></li>
	<li>与准确值误差在 <code>10^-5</code> 之内的结果将被判定为正确。</li>
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
