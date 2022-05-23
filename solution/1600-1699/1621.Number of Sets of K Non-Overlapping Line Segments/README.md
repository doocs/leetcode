# [1621. 大小为 K 的不重叠线段的数目](https://leetcode.cn/problems/number-of-sets-of-k-non-overlapping-line-segments)

[English Version](/solution/1600-1699/1621.Number%20of%20Sets%20of%20K%20Non-Overlapping%20Line%20Segments/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一维空间的 <code>n</code> 个点，其中第 <code>i</code> 个点（编号从 <code>0</code> 到 <code>n-1</code>）位于 <code>x = i</code> 处，请你找到 <strong>恰好</strong> <code>k</code> <strong>个不重叠</strong> 线段且每个线段至少覆盖两个点的方案数。线段的两个端点必须都是 <strong>整数坐标</strong> 。这 <code>k</code> 个线段不需要全部覆盖全部 <code>n</code> 个点，且它们的端点 <strong>可以 </strong>重合。</p>

<p>请你返回 <code>k</code> 个不重叠线段的方案数。由于答案可能很大，请将结果对 <code>10<sup>9</sup> + 7</code> <strong>取余</strong> 后返回。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1621.Number%20of%20Sets%20of%20K%20Non-Overlapping%20Line%20Segments/images/ex1.png" style="width: 179px; height: 222px;" />
<pre>
<b>输入：</b>n = 4, k = 2
<b>输出：</b>5
<strong>解释：
</strong>如图所示，两个线段分别用红色和蓝色标出。
上图展示了 5 种不同的方案 {(0,2),(2,3)}，{(0,1),(1,3)}，{(0,1),(2,3)}，{(1,2),(2,3)}，{(0,1),(1,2)} 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>n = 3, k = 1
<b>输出：</b>3
<strong>解释：</strong>总共有 3 种不同的方案 {(0,1)}, {(0,2)}, {(1,2)} 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>n = 30, k = 7
<b>输出：</b>796297179
<strong>解释：</strong>画 7 条线段的总方案数为 3796297200 种。将这个数对 10<sup>9</sup> + 7 取余得到 796297179 。
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<b>输入：</b>n = 5, k = 3
<b>输出：</b>7
</pre>

<p><strong>示例 5：</strong></p>

<pre>
<b>输入：</b>n = 3, k = 2
<b>输出：</b>1</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 <= n <= 1000</code></li>
	<li><code>1 <= k <= n-1</code></li>
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
