# [1001. 网格照明](https://leetcode-cn.com/problems/grid-illumination)

## 题目描述
<!-- 这里写题目描述 -->
<p>在&nbsp;<code>N x N</code>&nbsp;的网格上，每个单元格&nbsp;<code>(x, y)</code>&nbsp;上都有一盏灯，其中&nbsp;<code>0 &lt;= x &lt; N</code>&nbsp;且&nbsp;<code>0 &lt;= y &lt; N</code> 。</p>

<p>最初，一定数量的灯是亮着的。<code>lamps[i]</code>&nbsp;告诉我们亮着的第 <code>i</code> 盏灯的位置。每盏灯都照亮其所在 x 轴、y 轴和两条对角线上的每个正方形（类似于国际象棋中的皇后）。</p>

<p>对于第 <code>i</code> 次查询&nbsp;<code>queries[i] = (x, y)</code>，如果单元格 (x, y) 是被照亮的，则查询结果为 1，否则为 0 。</p>

<p>在每个查询 <code>(x, y)</code> 之后 [按照查询的顺序]，我们关闭位于单元格 (x, y) 上或其相邻 8 个方向上（与单元格 (x, y) 共享一个角或边）的任何灯。</p>

<p>返回答案数组 <code>answer</code>。每个值 <code>answer[i]</code> 应等于第 <code>i</code>&nbsp;次查询&nbsp;<code>queries[i]</code>&nbsp;的结果。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>N = 5, lamps = [[0,0],[4,4]], queries = [[1,1],[1,0]]
<strong>输出：</strong>[1,0]
<strong>解释： </strong>
在执行第一次查询之前，我们位于 [0, 0] 和 [4, 4] 灯是亮着的。
表示哪些单元格亮起的网格如下所示，其中 [0, 0] 位于左上角：
1 1 1 1 1
1 1 0 0 1
1 0 1 0 1
1 0 0 1 1
1 1 1 1 1
然后，由于单元格 [1, 1] 亮着，第一次查询返回 1。在此查询后，位于 [0，0] 处的灯将关闭，网格现在如下所示：
1 0 0 0 1
0 1 0 0 1
0 0 1 0 1
0 0 0 1 1
1 1 1 1 1
在执行第二次查询之前，我们只有 [4, 4] 处的灯亮着。现在，[1, 0] 处的查询返回 0，因为该单元格不再亮着。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= N &lt;= 10^9</code></li>
	<li><code>0 &lt;= lamps.length &lt;= 20000</code></li>
	<li><code>0 &lt;= queries.length &lt;= 20000</code></li>
	<li><code>lamps[i].length == queries[i].length == 2</code></li>
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
<!-- tabs:end -->