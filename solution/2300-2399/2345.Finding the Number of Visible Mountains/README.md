# [2345. 寻找可见山的数量](https://leetcode.cn/problems/finding-the-number-of-visible-mountains)

[English Version](/solution/2300-2399/2345.Finding%20the%20Number%20of%20Visible%20Mountains/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个 <strong>下标从 0 开始&nbsp;</strong>的二维整数数组 <code>peaks</code>，其中 <code>peaks[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> 表示山 <code>i</code> 在坐标 <code>(x<sub>i</sub>, y<sub>i</sub>)</code>&nbsp;处有一个峰值。山可以被描述为一个直角等腰三角形，它的底部沿着 <code>x</code>&nbsp;轴，山峰处有一个直角。更正式地说，上山和下山的&nbsp;<strong>梯度&nbsp;</strong>分别为 <code>1</code>&nbsp;和 <code>-1</code>。</p>

<p>一座山如果它的顶峰不在另一座山 (包括其他山的边界) 之内，那么它被认为是&nbsp;<strong>可见&nbsp;</strong>的。</p>

<p data-group="1-1">返回<em>可见山的数量。</em></p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2345.Finding%20the%20Number%20of%20Visible%20Mountains/images/ex1.png" style="width: 402px; height: 210px;" />
<pre>
<strong>输入:</strong> peaks = [[2,2],[6,3],[5,4]]
<strong>输出:</strong> 2
<strong>解释:</strong> 上面的图表显示了山脉。
- 山 0 是可见的，因为它的峰值不在另一座山的里面或另一座山的边界。
- 山 1 是不可见的，因为它的顶峰在山 2 的边界。
- 山 2 是可见的，因为它的峰值不在另一座山的里面或另一座山的边界。
有 2 座山是可见的。</pre>

<p><strong class="example">示例 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2345.Finding%20the%20Number%20of%20Visible%20Mountains/images/ex2new1.png" style="width: 300px; height: 180px;" />
<pre>
<strong>输入:</strong> peaks = [[1,3],[1,3]]
<strong>输出:</strong> 0
<strong>解释:</strong> 上面的图表显示了这些山 (它们完全重叠)。
两座山都看不见，因为它们的山峰在彼此里面。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= peaks.length &lt;= 10<sup>5</sup></code></li>
	<li><code>peaks[i].length == 2</code></li>
	<li><code>1 &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
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

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
