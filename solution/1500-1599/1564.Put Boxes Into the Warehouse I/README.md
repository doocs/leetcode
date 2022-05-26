# [1564. 把箱子放进仓库里 I](https://leetcode.cn/problems/put-boxes-into-the-warehouse-i)

[English Version](/solution/1500-1599/1564.Put%20Boxes%20Into%20the%20Warehouse%20I/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个正整数数组&nbsp;<code>boxes</code>&nbsp;和&nbsp;<code>warehouse</code>&nbsp;，分别包含单位宽度的箱子的高度，以及仓库中 <code>n</code> 个房间各自的高度。仓库的房间分别从&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n - 1</code>&nbsp;自左向右编号，&nbsp;<code>warehouse[i]</code>&nbsp;（索引从 0 开始）是第&nbsp;<code>i</code>&nbsp;个房间的高度。</p>

<p>箱子放进仓库时遵循下列规则：</p>

<ul>
	<li>箱子不可叠放。</li>
	<li>你可以重新调整箱子的顺序。</li>
	<li>箱子只能从左向右推进仓库中。</li>
	<li>如果仓库中某房间的高度小于某箱子的高度，则这个箱子和之后的箱子都会停在这个房间的前面。</li>
</ul>

<p>你最多可以在仓库中放进多少个箱子？</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1564.Put%20Boxes%20Into%20the%20Warehouse%20I/images/11.png" style="height: 242px; width: 400px;" /></strong></p>

<pre>
<strong>输入：</strong>boxes = [4,3,4,1], warehouse = [5,3,3,4,1]
<strong>输出：</strong>3
<strong>解释：
</strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1564.Put%20Boxes%20Into%20the%20Warehouse%20I/images/12.png" style="height: 242px; width: 280px;" />
我们可以先把高度为 1 的箱子放入 4 号房间，然后再把高度为 3 的箱子放入 1 号、 2 号或 3 号房间，最后再把高度为 4 的箱子放入 0 号房间。
我们不可能把所有 4 个箱子全部放进仓库里。</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1564.Put%20Boxes%20Into%20the%20Warehouse%20I/images/21.png" style="height: 202px; width: 400px;" /></strong></p>

<pre>
<strong>输入：</strong>boxes = [1,2,2,3,4], warehouse = [3,4,1,2]
<strong>输出：</strong>3
<strong>解释：
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1564.Put%20Boxes%20Into%20the%20Warehouse%20I/images/22.png" style="height: 202px; width: 280px;" />
</strong>我们注意到，不可能把高度为 4 的箱子放入仓库中，因为它不能通过高度为 3 的房间。
而且，对于最后两个房间 2 号和 3 号来说，只有高度为 1 的箱子可以放进去。
我们最多可以放进 3 个箱子，如上图所示。黄色的箱子也可以放入 2 号房间。
交换橙色和绿色箱子的位置，或是将这两个箱子与红色箱子交换位置，也是可以的。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>boxes = [1,2,3], warehouse = [1,2,3,4]
<strong>输出：</strong>1
<strong>解释：</strong>由于第一个房间的高度为 1，我们只能放进高度为 1 的箱子。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == warehouse.length</code></li>
	<li><code>1 &lt;= boxes.length, warehouse.length &lt;= 10^5</code></li>
	<li><code>1 &lt;= boxes[i], warehouse[i] &lt;= 10^9</code></li>
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
