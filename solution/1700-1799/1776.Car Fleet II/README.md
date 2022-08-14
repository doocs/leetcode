# [1776. 车队 II](https://leetcode.cn/problems/car-fleet-ii)

[English Version](/solution/1700-1799/1776.Car%20Fleet%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在一条单车道上有 <code>n</code> 辆车，它们朝着同样的方向行驶。给你一个长度为 <code>n</code> 的数组 <code>cars</code> ，其中 <code>cars[i] = [position<sub>i</sub>, speed<sub>i</sub>]</code> ，它表示：</p>

<ul>
	<li><code>position<sub>i</sub></code> 是第 <code>i</code> 辆车和道路起点之间的距离（单位：米）。题目保证 <code>position<sub>i</sub> < position<sub>i+1</sub></code><sub> </sub>。</li>
	<li><code>speed<sub>i</sub></code> 是第 <code>i</code> 辆车的初始速度（单位：米/秒）。</li>
</ul>

<p>简单起见，所有车子可以视为在数轴上移动的点。当两辆车占据同一个位置时，我们称它们相遇了。一旦两辆车相遇，它们会合并成一个车队，这个车队里的车有着同样的位置和相同的速度，速度为这个车队里 <strong>最慢</strong> 一辆车的速度。</p>

<p>请你返回一个数组 <code>answer</code> ，其中 <code>answer[i]</code> 是第 <code>i</code> 辆车与下一辆车相遇的时间（单位：秒），如果这辆车不会与下一辆车相遇，则 <code>answer[i]</code> 为 <code>-1</code> 。答案精度误差需在 <code>10<sup>-5</sup></code> 以内。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>cars = [[1,2],[2,1],[4,3],[7,2]]
<b>输出：</b>[1.00000,-1.00000,3.00000,-1.00000]
<b>解释：</b>经过恰好 1 秒以后，第一辆车会与第二辆车相遇，并形成一个 1 m/s 的车队。经过恰好 3 秒以后，第三辆车会与第四辆车相遇，并形成一个 2 m/s 的车队。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>cars = [[3,4],[5,4],[6,3],[9,1]]
<b>输出：</b>[2.00000,1.00000,1.50000,-1.00000]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= cars.length <= 10<sup>5</sup></code></li>
	<li><code>1 <= position<sub>i</sub>, speed<sub>i</sub> <= 10<sup>6</sup></code></li>
	<li><code>position<sub>i</sub> < position<sub>i+1</sub></code></li>
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
