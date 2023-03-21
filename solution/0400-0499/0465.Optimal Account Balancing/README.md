# [465. 最优账单平衡](https://leetcode.cn/problems/optimal-account-balancing)

[English Version](/solution/0400-0499/0465.Optimal%20Account%20Balancing/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个表示交易的数组 <code>transactions</code> ，其中 <code>transactions[i] = [from<sub>i</sub>, to<sub>i</sub>, amount<sub>i</sub>]</code> 表示 <code>ID = from<sub>i</sub></code> 的人给&nbsp;<code>ID = to<sub>i</sub></code> 的人共计 <code>amount<sub>i</sub> $</code> 。</p>

<p>请你计算并返回还清所有债务的最小交易笔数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>transactions = [[0,1,10],[2,0,5]]
<strong>输出：</strong>2
<strong>解释：</strong>
#0 给 #1 $10 。
#2 给 #0 $5 。
需要进行两笔交易。一种结清债务的方式是 #1 给 #0 和 #2 各 $5 。</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>transactions = [[0,1,10],[1,0,1],[1,2,5],[2,0,5]]
<strong>输出：</strong>1
<strong>解释：</strong>
#0 给 #1 $10 。
#1 给 #0 $1 。
#1 给 #2 $5 。
#2 给 #0 $5 。
因此，#1 只需要给 #0 $4 ，所有的债务即可还清。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= transactions.length &lt;= 8</code></li>
	<li><code>transactions[i].length == 3</code></li>
	<li><code>0 &lt;= from<sub>i</sub>, to<sub>i</sub> &lt; 12</code></li>
	<li><code>from<sub>i</sub> != to<sub>i</sub></code></li>
	<li><code>1 &lt;= amount<sub>i</sub> &lt;= 100</code></li>
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
