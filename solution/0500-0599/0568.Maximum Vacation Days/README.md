# [568. 最大休假天数](https://leetcode.cn/problems/maximum-vacation-days)

[English Version](/solution/0500-0599/0568.Maximum%20Vacation%20Days/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>力扣想让一个最优秀的员工在 <strong>N</strong> 个城市间旅行来收集算法问题。 但只工作不玩耍，聪明的孩子也会变傻，所以您可以在某些特定的城市和星期休假。您的工作就是安排旅行使得最大化你可以休假的天数，但是您需要遵守一些规则和限制。</p>

<p><strong>规则和限制：</strong></p>

<ol>
	<li>您只能在 <strong>N</strong> 个城市之间旅行，用 <code>0</code> 到 <code>n-1</code> 的索引表示。一开始，您在索引为 <code>0</code> 的城市，并且那天是<strong>星期一</strong>。</li>
	<li>这些城市通过航班相连。这些航班用&nbsp;<code>n x n</code>&nbsp;矩阵<strong> flights</strong>（不一定是对称的）表示，<strong>flights[i][j] </strong>代表城市 <code>i</code> 到城市 <code>j</code> 的航空状态。如果没有城市 <code>i</code> 到城市 <code>j</code> 的航班，<code>flights[i][j] = 0</code>&nbsp;；否则，<code>flights[i][j] = 1</code>&nbsp;。同时，对于所有的 <code>i</code> ，<code>flights[i][i] = 0</code>&nbsp;<strong>。</strong></li>
	<li>您总共有 <code>k</code>&nbsp;周（<strong>每周7天</strong>）的时间旅行。您<strong>每天</strong>最多只能乘坐一次航班，并且只能在每周的<strong>星期一</strong>上午乘坐航班。由于飞行时间很短，我们不考虑飞行时间的影响。</li>
	<li>对于每个城市，不同的星期您休假天数是不同的，给定一个 <strong>N*K</strong> 矩阵 <strong>days</strong> 代表这种限制，<strong>days[i][j] </strong>代表您在第j个星期在城市i能休假的最长天数。</li>
	<li>如果您从 <code>A</code> 市飞往 <code>B</code> 市，并在当天休假，扣除的假期天数将计入 <code>B</code> 市当周的休假天数。</li>
	<li>我们不考虑飞行时数对休假天数计算的影响。</li>
</ol>

<p>给定 <code>flights</code> 矩阵和 <code>days</code> 矩阵，您需要输出 <code>k</code>&nbsp;周内可以休假的最长天数。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong>flights = [[0,1,1],[1,0,1],[1,1,0]], days = [[1,3,1],[6,0,3],[3,3,3]]
<strong>输出:</strong> 12
<strong>解释:</strong> 
最好的策略之一：
第一个星期 : 星期一从城市 0 飞到城市 1，玩 6 天，工作 1 天。 
（虽然你是从城市 0 开始，但因为是星期一，我们也可以飞到其他城市。） 
第二个星期 : 星期一从城市 1 飞到城市 2，玩 3 天，工作 4 天。
第三个星期 : 呆在城市 2，玩 3 天，工作 4 天。
Ans = 6 + 3 + 3 = 12. 
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong>flights = [[0,0,0],[0,0,0],[0,0,0]], days = [[1,1,1],[7,7,7],[7,7,7]]
<strong>输出:</strong> 3
<strong>解释:</strong> 
由于没有航班可以让您飞到其他城市，你必须在城市 0 呆整整 3 个星期。 
对于每一个星期，你只有一天时间玩，剩下六天都要工作。 
所以最大休假天数为 3.
Ans = 1 + 1 + 1 = 3. 
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong>flights = [[0,1,1],[1,0,1],[1,1,0]], days = [[7,0,0],[0,7,0],[0,0,7]]
<strong>输出:</strong> 21
<strong>解释:</strong>
最好的策略之一是：
第一个星期 : 呆在城市 0，玩 7 天。 
第二个星期 : 星期一从城市 0 飞到城市 1，玩 7 天。
第三个星期 : 星期一从城市 1 飞到城市 2，玩 7 天。
Ans = 7 + 7 + 7 = 21
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>n == flights.length</code></li>
	<li><code>n == flights[i].length</code></li>
	<li><code>n == days.length</code></li>
	<li><code>k == days[i].length</code></li>
	<li><code>1 &lt;= n, k &lt;= 100</code></li>
	<li><code>flights[i][j]</code>&nbsp;不是&nbsp;<code>0</code>&nbsp;就是&nbsp;<code>1</code></li>
	<li><code>0 &lt;= days[i] &lt;= 7</code></li>
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
