# [957. N 天后的牢房](https://leetcode.cn/problems/prison-cells-after-n-days)

[English Version](/solution/0900-0999/0957.Prison%20Cells%20After%20N%20Days/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>监狱中 <code>8</code> 间牢房排成一排，每间牢房可能被占用或空置。</p>

<p>每天，无论牢房是被占用或空置，都会根据以下规则进行变更：</p>

<ul>
	<li>如果一间牢房的两个相邻的房间都被占用或都是空的，那么该牢房就会被占用。</li>
	<li>否则，它就会被空置。</li>
</ul>

<p><strong>注意</strong>：由于监狱中的牢房排成一行，所以行中的第一个和最后一个牢房不存在两个相邻的房间。</p>

<p>给你一个整数数组 <code>cells</code> ，用于表示牢房的初始状态：如果第 <code>i</code> 间牢房被占用，则 <code>cell[i]==1</code>，否则 <code>cell[i]==0</code> 。另给你一个整数 <code>n</code> 。</p>

<p>请你返回 <code>n</code> 天后监狱的状况（即，按上文描述进行 <code>n</code> 次变更）。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>cells = [0,1,0,1,1,0,0,1], n = 7
<strong>输出：</strong>[0,0,1,1,0,0,0,0]
<strong>解释：</strong>下表总结了监狱每天的状况：
Day 0: [0, 1, 0, 1, 1, 0, 0, 1]
Day 1: [0, 1, 1, 0, 0, 0, 0, 0]
Day 2: [0, 0, 0, 0, 1, 1, 1, 0]
Day 3: [0, 1, 1, 0, 0, 1, 0, 0]
Day 4: [0, 0, 0, 0, 0, 1, 0, 0]
Day 5: [0, 1, 1, 1, 0, 1, 0, 0]
Day 6: [0, 0, 1, 0, 1, 1, 0, 0]
Day 7: [0, 0, 1, 1, 0, 0, 0, 0]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>cells = [1,0,0,1,0,0,1,0], n = 1000000000
<strong>输出：</strong>[0,0,1,1,1,1,1,0]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>cells.length == 8</code></li>
	<li><code>cells[i]</code> 为 <code>0</code> 或 <code>1</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
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
