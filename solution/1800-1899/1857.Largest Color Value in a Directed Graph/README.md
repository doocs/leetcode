# [1857. 有向图中最大颜色值](https://leetcode-cn.com/problems/largest-color-value-in-a-directed-graph)

[English Version](/solution/1800-1899/1857.Largest%20Color%20Value%20in%20a%20Directed%20Graph/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <strong>有向图</strong> ，它含有 <code>n</code> 个节点和 <code>m</code> 条边。节点编号从 <code>0</code> 到 <code>n - 1</code> 。</p>

<p>给你一个字符串 <code>colors</code> ，其中 <code>colors[i]</code> 是小写英文字母，表示图中第 <code>i</code> 个节点的 <b>颜色</b> （下标从 <strong>0</strong> 开始）。同时给你一个二维数组 <code>edges</code> ，其中 <code>edges[j] = [a<sub>j</sub>, b<sub>j</sub>]</code> 表示从节点 <code>a<sub>j</sub></code> 到节点 <code>b<sub>j</sub></code><sub> </sub>有一条 <strong>有向边</strong> 。</p>

<p>图中一条有效 <strong>路径</strong> 是一个点序列 <code>x<sub>1</sub> -&gt; x<sub>2</sub> -&gt; x<sub>3</sub> -&gt; ... -&gt; x<sub>k</sub></code> ，对于所有 <code>1 &lt;= i &lt; k</code> ，从 <code>x<sub>i</sub></code> 到 <code>x<sub>i+1</sub></code> 在图中有一条有向边。路径的 <strong>颜色值</strong> 是路径中 <strong>出现次数最多</strong> 颜色的节点数目。</p>

<p>请你返回给定图中有效路径里面的 <strong>最大颜色值</strong><strong> 。</strong>如果图中含有环，请返回 <code>-1</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1857.Largest%20Color%20Value%20in%20a%20Directed%20Graph/images/leet1.png" style="width: 400px; height: 182px;"></p>

<pre><b>输入：</b>colors = "abaca", edges = [[0,1],[0,2],[2,3],[3,4]]
<b>输出：</b>3
<b>解释：</b>路径 0 -&gt; 2 -&gt; 3 -&gt; 4 含有 3 个颜色为 <code>"a" 的节点（上图中的红色节点）。</code>
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1857.Largest%20Color%20Value%20in%20a%20Directed%20Graph/images/leet2.png" style="width: 85px; height: 85px;"></p>

<pre><b>输入：</b>colors = "a", edges = [[0,0]]
<b>输出：</b>-1
<b>解释：</b>从 0 到 0 有一个环。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == colors.length</code></li>
	<li><code>m == edges.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= m &lt;= 10<sup>5</sup></code></li>
	<li><code>colors</code> 只含有小写英文字母。</li>
	<li><code>0 &lt;= a<sub>j</sub>, b<sub>j</sub> &lt; n</code></li>
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
