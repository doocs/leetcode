# [218. 天际线问题](https://leetcode-cn.com/problems/the-skyline-problem)

## 题目描述
<!-- 这里写题目描述 -->
<p>城市的天际线是从远处观看该城市中所有建筑物形成的轮廓的外部轮廓。现在，假设您获得了城市风光照片（图A）上<strong>显示的所有建筑物的位置和高度</strong>，请编写一个程序以输出由这些建筑物<strong>形成的天际线</strong>（图B）。</p>

<p><a href="/static/images/problemset/skyline1.jpg" target="_blank"><img alt="Buildings" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/22/skyline1.png" style="width: 300px;"> </a> <a href="/static/images/problemset/skyline2.jpg" target="_blank"> <img alt="Skyline Contour" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/22/skyline2.png" style="width: 300px;"> </a></p>

<p>每个建筑物的几何信息用三元组&nbsp;<code>[Li，Ri，Hi]</code> 表示，其中 <code>Li</code> 和 <code>Ri</code> 分别是第 i 座建筑物左右边缘的 x 坐标，<code>Hi</code> 是其高度。可以保证&nbsp;<code>0 &le; Li, Ri &le; INT_MAX</code>,&nbsp;<code>0 &lt; Hi &le; INT_MAX</code> 和 <code>Ri - Li &gt; 0</code>。您可以假设所有建筑物都是在绝对平坦且高度为 0 的表面上的完美矩形。</p>

<p>例如，图A中所有建筑物的尺寸记录为：<code>[ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] </code>。</p>

<p>输出是以&nbsp;<code>[ [x1,y1], [x2, y2], [x3, y3], ... ]</code> 格式的&ldquo;<strong>关键点</strong>&rdquo;（图B中的红点）的列表，它们唯一地定义了天际线。<strong>关键点是水平线段的左端点</strong>。请注意，最右侧建筑物的最后一个关键点仅用于标记天际线的终点，并始终为零高度。此外，任何两个相邻建筑物之间的地面都应被视为天际线轮廓的一部分。</p>

<p>例如，图B中的天际线应该表示为：<code>[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ]</code>。</p>

<p><strong>说明:</strong></p>

<ul>
	<li>任何输入列表中的建筑物数量保证在 <code>[0, 10000]</code>&nbsp;范围内。</li>
	<li>输入列表已经按左&nbsp;<code>x</code> 坐标&nbsp;<code>Li</code>&nbsp; 进行升序排列。</li>
	<li>输出列表必须按 x 位排序。</li>
	<li>输出天际线中不得有连续的相同高度的水平线。例如 <code>[...[2 3], [4 5], [7 5], [11 5], [12 7]...]</code> 是不正确的答案；三条高度为 5 的线应该在最终输出中合并为一个：<code>[...[2 3], [4 5], [12 7], ...]</code></li>
</ul>



## 解法
<!-- 这里可写通用的实现逻辑 -->


### Python3
<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### Java
<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### ...
```

```
