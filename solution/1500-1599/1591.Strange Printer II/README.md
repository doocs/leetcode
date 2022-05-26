# [1591. 奇怪的打印机 II](https://leetcode.cn/problems/strange-printer-ii)

[English Version](/solution/1500-1599/1591.Strange%20Printer%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个奇怪的打印机，它有如下两个特殊的打印规则：</p>

<ul>
	<li>每一次操作时，打印机会用同一种颜色打印一个矩形的形状，每次打印会覆盖矩形对应格子里原本的颜色。</li>
	<li>一旦矩形根据上面的规则使用了一种颜色，那么 <strong>相同的颜色不能再被使用&nbsp;</strong>。</li>
</ul>

<p>给你一个初始没有颜色的&nbsp;<code>m x n</code>&nbsp;的矩形&nbsp;<code>targetGrid</code>&nbsp;，其中&nbsp;<code>targetGrid[row][col]</code>&nbsp;是位置&nbsp;<code>(row, col)</code>&nbsp;的颜色。</p>

<p>如果你能按照上述规则打印出矩形<em>&nbsp;</em><code>targetGrid</code>&nbsp;，请你返回&nbsp;<code>true</code>&nbsp;，否则返回&nbsp;<code>false</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1591.Strange%20Printer%20II/images/sample_1_1929.png" style="height: 138px; width: 483px;"></p>

<pre><strong>输入：</strong>targetGrid = [[1,1,1,1],[1,2,2,1],[1,2,2,1],[1,1,1,1]]
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1591.Strange%20Printer%20II/images/sample_2_1929.png" style="height: 290px; width: 483px;"></p>

<pre><strong>输入：</strong>targetGrid = [[1,1,1,1],[1,1,3,3],[1,1,3,4],[5,5,1,4]]
<strong>输出：</strong>true
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>targetGrid = [[1,2,1],[2,1,2],[1,2,1]]
<strong>输出：</strong>false
<strong>解释：</strong>没有办法得到 targetGrid ，因为每一轮操作使用的颜色互不相同。</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>targetGrid = [[1,1,1],[3,1,3]]
<strong>输出：</strong>false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == targetGrid.length</code></li>
	<li><code>n == targetGrid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 60</code></li>
	<li><code>1 &lt;= targetGrid[row][col] &lt;= 60</code></li>
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
