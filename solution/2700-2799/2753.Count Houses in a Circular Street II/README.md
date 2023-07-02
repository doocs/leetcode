# [2753. 计算一个环形街道上的房屋数量 II](https://leetcode.cn/problems/count-houses-in-a-circular-street-ii)

[English Version](/solution/2700-2799/2753.Count%20Houses%20in%20a%20Circular%20Street%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个代表 <strong>环形 </strong>街道的类 <code>Street</code> 和一个正整数 <code>k</code>，表示街道上房屋的最大数量（也就是说房屋数量不超过 <code>k</code>）。每个房屋的门初始时可以是开着的也可以是关着的（至少有一个房屋的门是开着的）。</p>

<p>刚开始，你站在一座房子的门前。你的任务是计算街道上的房屋数量。</p>

<p><code>Street</code> 类包含以下函数：</p>

<ul>
	<li><code>void closeDoor()</code>：关闭当前房屋的门。</li>
	<li><code>boolean isDoorOpen()</code>：如果当前房屋的门是开着的返回 <code>true</code>，否则返回 <code>false</code>。</li>
	<li><code>void moveRight()</code>：向右移动到下一座房屋。</li>
</ul>

<p><strong>注意：</strong>在<strong> 环形 </strong>街道内，如果将房屋从 <code>1</code> 到 <code>n</code> 编号，则当 <code>i &lt; n</code> 时&nbsp;<code>house<sub>i</sub></code> 右边的房子是&nbsp;<code>house<sub>i+1</sub></code>，<code>house<sub>n</sub></code> 右边的房子是&nbsp;<code>house<sub>1</sub></code>。</p>

<p>返回 <code>ans</code>，它表示街道上的房屋数量。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>street = [1,1,1,1], k = 10
<b>输出：</b>4
<b>解释：</b>街道上有 4 座房屋，它们的门都是关着的。
房屋数量小于 k，即 10。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>street = [1,0,1,1,0], k = 5
<b>输出：</b>5
<strong>解释：</strong>街道上有 5 座房屋，向右移动时第 1、3 和 4 座房屋的门是开着的，其余的门都是关着的。
房屋数量等于 k，即 5。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n</code>&nbsp;是房屋数量</li>
	<li><code>1 &lt;= n &lt;= k &lt;= 10<sup>5</sup></code></li>
	<li><code>street</code>&nbsp;是环形的</li>
	<li>输入数据中至少有一扇门是开着的</li>
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

### **C++**

```cpp

```

### **Go**

```go

```

### **...**

```

```

<!-- tabs:end -->
