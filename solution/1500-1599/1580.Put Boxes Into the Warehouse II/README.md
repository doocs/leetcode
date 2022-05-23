# [1580. 把箱子放进仓库里 II](https://leetcode.cn/problems/put-boxes-into-the-warehouse-ii)

[English Version](/solution/1500-1599/1580.Put%20Boxes%20Into%20the%20Warehouse%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个正整数数组 <code>boxes</code> 和 <code>warehouse</code> ，分别包含单位宽度的箱子的高度，以及仓库中<code>n</code>个房间各自的高度。仓库的房间分别从<code>0</code> 到 <code>n - 1</code>自左向右编号，<code>warehouse[i]</code>（索引从 0 开始）是第 <code>i</code> 个房间的高度。</p>

<p>箱子放进仓库时遵循下列规则：</p>

<ul>
	<li>箱子不可叠放。</li>
	<li>你可以重新调整箱子的顺序。</li>
	<li>箱子可以从任意方向（左边或右边）推入仓库中。</li>
	<li>如果仓库中某房间的高度小于某箱子的高度，则这个箱子和之后的箱子都会停在这个房间的前面。</li>
</ul>

<p>你最多可以在仓库中放进多少个箱子？</p>

<p> </p>

<p><strong>示例 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1580.Put%20Boxes%20Into%20the%20Warehouse%20II/images/22.png" style="width: 401px; height: 202px;" />
<pre>
<strong>输入:</strong> boxes = [1,2,2,3,4], warehouse = [3,4,1,2]
<strong>输出:</strong> 4
<strong>解释:
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1580.Put%20Boxes%20Into%20the%20Warehouse%20II/images/22-1.png" style="width: 240px; height: 202px;" />
</strong>我们可以按如下顺序推入箱子:
1- 从左边或右边把黄色箱子推入2号房间；
2- 从右边把橙色箱子推入3号房间；
3- 从左边把绿色箱子推入1号房间；
4- 从左边把红色箱子推入0号房间；
还有其他方式推入4个箱子，比如交换红色与绿色箱子，或者交换红色与橙色箱子。
</pre>

<p><strong>示例 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1580.Put%20Boxes%20Into%20the%20Warehouse%20II/images/22-2.png" style="width: 401px; height: 242px;" />
<pre>
<strong>输入:</strong> boxes = [3,5,5,2], warehouse = [2,1,3,4,5]
<strong>输出:</strong> 3
<strong>解释:
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1580.Put%20Boxes%20Into%20the%20Warehouse%20II/images/22-3.png" style="width: 280px; height: 242px;" />
</strong>因为只有一个高度大于等于5的房间，所以无法将两个高度为5的箱子都推入仓库。
还有其他方式推入箱子，比如将绿色箱子推入2号房间，或者在绿色及红色箱子之前将橙色箱子推入2号房间。
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> boxes = [1,2,3], warehouse = [1,2,3,4]
<strong>输出:</strong> 3
</pre>

<p><strong>示例 4:</strong></p>

<pre>
<strong>输入:</strong> boxes = [4,5,6], warehouse = [3,3,3,3,3]
<strong>输出:</strong> 0
</pre>

<p> </p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>n == warehouse.length</code></li>
	<li><code>1 <= boxes.length, warehouse.length <= 10<sup>5</sup></code></li>
	<li><code>1 <= boxes[i], warehouse[i] <= 10<sup>9</sup></code></li>
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
