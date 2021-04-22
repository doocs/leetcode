# [1168. 水资源分配优化](https://leetcode-cn.com/problems/optimize-water-distribution-in-a-village)

[English Version](/solution/1100-1199/1168.Optimize%20Water%20Distribution%20in%20a%20Village/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>村里面一共有 <code>n</code> 栋房子。我们希望通过建造水井和铺设管道来为所有房子供水。</p>

<p>对于每个房子&nbsp;<code>i</code>，我们有两种可选的供水方案：</p>

<ul>
	<li>一种是直接在房子内建造水井，成本为&nbsp;<code>wells[i]</code>；</li>
	<li>另一种是从另一口井铺设管道引水，数组&nbsp;<code>pipes</code>&nbsp;给出了在房子间铺设管道的成本，其中每个&nbsp;<code>pipes[i] = [house1, house2, cost]</code>&nbsp;代表用管道将&nbsp;<code>house1</code>&nbsp;和&nbsp;<code>house2</code>&nbsp;连接在一起的成本。当然，连接是双向的。</li>
</ul>

<p>请你帮忙计算为所有房子都供水的最低总成本。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1168.Optimize%20Water%20Distribution%20in%20a%20Village/images/1359_ex1.png" style="width: 130px;"></strong></p>

<pre><strong>输入：</strong>n = 3, wells = [1,2,2], pipes = [[1,2,1],[2,3,1]]
<strong>输出：</strong>3
<strong>解释： </strong>
上图展示了铺设管道连接房屋的成本。
最好的策略是在第一个房子里建造水井（成本为 1），然后将其他房子铺设管道连起来（成本为 2），所以总成本为 3。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n&nbsp;&lt;= 10000</code></li>
	<li><code>wells.length == n</code></li>
	<li><code>0 &lt;= wells[i] &lt;= 10^5</code></li>
	<li><code>1 &lt;= pipes.length &lt;= 10000</code></li>
	<li><code>1 &lt;= pipes[i][0], pipes[i][1] &lt;= n</code></li>
	<li><code>0 &lt;= pipes[i][2] &lt;= 10^5</code></li>
	<li><code>pipes[i][0] != pipes[i][1]</code></li>
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
