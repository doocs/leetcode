# [957. N 天后的牢房](https://leetcode-cn.com/problems/prison-cells-after-n-days)

[English Version](/solution/0900-0999/0957.Prison%20Cells%20After%20N%20Days/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>8 间牢房排成一排，每间牢房不是有人住就是空着。</p>

<p>每天，无论牢房是被占用或空置，都会根据以下规则进行更改：</p>

<ul>
	<li>如果一间牢房的两个相邻的房间都被占用或都是空的，那么该牢房就会被占用。</li>
	<li>否则，它就会被空置。</li>
</ul>

<p>（请注意，由于监狱中的牢房排成一行，所以行中的第一个和最后一个房间无法有两个相邻的房间。）</p>

<p>我们用以下方式描述监狱的当前状态：如果第 <code>i</code> 间牢房被占用，则 <code>cell[i]==1</code>，否则 <code>cell[i]==0</code>。</p>

<p>根据监狱的初始状态，在 <code>N</code> 天后返回监狱的状况（和上述 N 种变化）。</p>

<p>&nbsp;</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>cells = [0,1,0,1,1,0,0,1], N = 7
<strong>输出：</strong>[0,0,1,1,0,0,0,0]
<strong>解释：
</strong>下表概述了监狱每天的状况：
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

<pre><strong>输入：</strong>cells = [1,0,0,1,0,0,1,0], N = 1000000000
<strong>输出：</strong>[0,0,1,1,1,1,1,0]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>cells.length == 8</code></li>
	<li><code>cells[i]</code>&nbsp;的值为 <code>0</code> 或 <code>1</code>&nbsp;</li>
	<li><code>1 &lt;= N &lt;= 10^9</code></li>
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
