# [1956. 感染 K 种病毒所需的最短时间](https://leetcode-cn.com/problems/minimum-time-for-k-virus-variants-to-spread)

[English Version](/solution/1900-1999/1956.Minimum%20Time%20For%20K%20Virus%20Variants%20to%20Spread/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在无限大的2维平面上有 <code>n</code> 种不同的病毒。给定二维数组 <code>points</code> ，<span style="">第 </span><code>i</code><span style=""> 项 </span><code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> 说明第 <code>0</code> 天有一种病毒在点 <code>(x<sub>i</sub>, y<sub>i</sub>)</code> 。注意初始状态下，可能有多种病毒在同一点上。</p>

<p>每天，被感染的点会把它感染的病毒传播到上下左右四个邻居点。</p>

<p>现给定一个整数<code>k</code> ，问最少需要多少天，方能找到一点感染至少 <code>k</code> 种病毒？</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1956.Minimum%20Time%20For%20K%20Virus%20Variants%20to%20Spread/images/case-1.png" style="width: 421px; height: 256px;" /></strong></p>

<pre>
<strong>输入：</strong>points = [[1,1],[6,1]], k = 2
<b>输出：</b>3
<strong>解释：</strong>在第3天，点 (3,1) 与 (4,1) 将感染所有2种病毒。
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1956.Minimum%20Time%20For%20K%20Virus%20Variants%20to%20Spread/images/case-2.png" style="width: 416px; height: 257px;" /></strong></p>

<pre>
<strong>输入：</strong>points = [[3,3],[1,2],[9,2]], k = 2
<b>输出：</b>2
<b>解释：</b>在第2天, 点(1,2), (1,3), (2,1), (2,2), (3,1) 和 (3,3) 将会感染前两种病毒。
</pre>

<p><strong>示例 3：</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1956.Minimum%20Time%20For%20K%20Virus%20Variants%20to%20Spread/images/case-2.png" style="width: 416px; height: 257px;" /></strong></p>

<pre>
<b>输入：</b>points = [[3,3],[1,2],[9,2]], k = 3
<b>输出：</b> 4
<strong>解释：</strong>在第4天，点 (5,2) 会感染所有3种病毒。
</pre>

<p> </p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>n == points.length</code></li>
	<li><code>2 <= n <= 16</code></li>
	<li><code>1 <= x<sub>i</sub>, y<sub>i</sub> <= 10<sup>9</sup></code></li>
	<li><code>2 <= k <= n</code></li>
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
