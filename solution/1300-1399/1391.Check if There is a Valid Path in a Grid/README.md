# [1391. 检查网格中是否存在有效路径](https://leetcode-cn.com/problems/check-if-there-is-a-valid-path-in-a-grid)

[English Version](/solution/1300-1399/1391.Check%20if%20There%20is%20a%20Valid%20Path%20in%20a%20Grid/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给你一个 <em>m</em> x <em>n</em> 的网格 <code>grid</code>。网格里的每个单元都代表一条街道。<code>grid[i][j]</code> 的街道可以是：</p>

<ul>
	<li><strong>1</strong> 表示连接左单元格和右单元格的街道。</li>
	<li><strong>2</strong> 表示连接上单元格和下单元格的街道。</li>
	<li><strong>3</strong>&nbsp;表示连接左单元格和下单元格的街道。</li>
	<li><strong>4</strong> 表示连接右单元格和下单元格的街道。</li>
	<li><strong>5</strong> 表示连接左单元格和上单元格的街道。</li>
	<li><strong>6</strong> 表示连接右单元格和上单元格的街道。</li>
</ul>

![](./images/main.png)

<p>你最开始从左上角的单元格 <code>(0,0)</code> 开始出发，网格中的「有效路径」是指从左上方的单元格 <code>(0,0)</code> 开始、一直到右下方的 <code>(m-1,n-1)</code> 结束的路径。<strong>该路径必须只沿着街道走</strong>。</p>

<p><strong>注意：</strong>你 <strong>不能</strong> 变更街道。</p>

<p>如果网格中存在有效的路径，则返回 <code>true</code>，否则返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

![](./images/e1.png)

<pre><strong>输入：</strong>grid = [[2,4,3],[6,5,2]]
<strong>输出：</strong>true
<strong>解释：</strong>如图所示，你可以从 (0, 0) 开始，访问网格中的所有单元格并到达 (m - 1, n - 1) 。
</pre>

<p><strong>示例 2：</strong></p>

![](./images/e2.png)

<pre><strong>输入：</strong>grid = [[1,2,1],[1,2,1]]
<strong>输出：</strong>false
<strong>解释：</strong>如图所示，单元格 (0, 0) 上的街道没有与任何其他单元格上的街道相连，你只会停在 (0, 0) 处。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>grid = [[1,1,2]]
<strong>输出：</strong>false
<strong>解释：</strong>你会停在 (0, 1)，而且无法到达 (0, 2) 。
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>grid = [[1,1,1,1,1,1,3]]
<strong>输出：</strong>true
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>grid = [[2],[2],[2],[2],[2],[2],[6]]
<strong>输出：</strong>true
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 300</code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= 6</code></li>
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