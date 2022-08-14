# [1916. 统计为蚁群构筑房间的不同顺序](https://leetcode.cn/problems/count-ways-to-build-rooms-in-an-ant-colony)

[English Version](/solution/1900-1999/1916.Count%20Ways%20to%20Build%20Rooms%20in%20an%20Ant%20Colony/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你是一只蚂蚁，负责为蚁群构筑 <code>n</code> 间编号从 <code>0</code> 到 <code>n-1</code> 的新房间。给你一个 <strong>下标从 0 开始</strong> 且长度为 <code>n</code> 的整数数组 <code>prevRoom</code> 作为扩建计划。其中，<code>prevRoom[i]</code> 表示在构筑房间 <code>i</code> 之前，你必须先构筑房间 <code>prevRoom[i]</code> ，并且这两个房间必须 <strong>直接</strong> 相连。房间 <code>0</code> 已经构筑完成，所以 <code>prevRoom[0] = -1</code> 。扩建计划中还有一条硬性要求，在完成所有房间的构筑之后，从房间 <code>0</code> 可以访问到每个房间。</p>

<p>你一次只能构筑 <strong>一个</strong> 房间。你可以在 <strong>已经构筑好的</strong> 房间之间自由穿行，只要这些房间是 <strong>相连的</strong> 。如果房间 <code>prevRoom[i]</code> 已经构筑完成，那么你就可以构筑房间 <code>i</code>。</p>

<p>返回你构筑所有房间的 <strong>不同顺序的数目</strong> 。由于答案可能很大，请返回对 <code>10<sup>9</sup> + 7</code> <strong>取余</strong> 的结果。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1916.Count%20Ways%20to%20Build%20Rooms%20in%20an%20Ant%20Colony/images/d1.jpg" style="width: 200px; height: 212px;" />
<pre>
<strong>输入：</strong><code>prevRoom</code> = [-1,0,1]
<strong>输出：</strong>1
<strong>解释：</strong>仅有一种方案可以完成所有房间的构筑：0 → 1 → 2
</pre>

<p><strong>示例 2：</strong></p>
<strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1916.Count%20Ways%20to%20Build%20Rooms%20in%20an%20Ant%20Colony/images/d2.jpg" style="width: 200px; height: 239px;" /></strong>

<pre>
<strong>输入：</strong><code>prevRoom</code> = [-1,0,0,1,2]
<strong>输出：</strong>6
<strong>解释：
</strong>有 6 种不同顺序：
0 → 1 → 3 → 2 → 4
0 → 2 → 4 → 1 → 3
0 → 1 → 2 → 3 → 4
0 → 1 → 2 → 4 → 3
0 → 2 → 1 → 3 → 4
0 → 2 → 1 → 4 → 3
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == prevRoom.length</code></li>
	<li><code>2 <= n <= 10<sup>5</sup></code></li>
	<li><code>prevRoom[0] == -1</code></li>
	<li>对于所有的 <code>1 <= i < n</code> ，都有 <code>0 <= prevRoom[i] < n</code></li>
	<li>题目保证所有房间都构筑完成后，从房间 <code>0</code> 可以访问到每个房间</li>
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
