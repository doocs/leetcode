# [1326. 灌溉花园的最少水龙头数目](https://leetcode.cn/problems/minimum-number-of-taps-to-open-to-water-a-garden)

[English Version](/solution/1300-1399/1326.Minimum%20Number%20of%20Taps%20to%20Open%20to%20Water%20a%20Garden/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在 x 轴上有一个一维的花园。花园长度为&nbsp;<code>n</code>，从点&nbsp;<code>0</code>&nbsp;开始，到点&nbsp;<code>n</code>&nbsp;结束。</p>

<p>花园里总共有&nbsp;<code>n + 1</code> 个水龙头，分别位于&nbsp;<code>[0, 1, ..., n]</code> 。</p>

<p>给你一个整数&nbsp;<code>n</code>&nbsp;和一个长度为&nbsp;<code>n + 1</code> 的整数数组&nbsp;<code>ranges</code>&nbsp;，其中&nbsp;<code>ranges[i]</code> （下标从 0 开始）表示：如果打开点&nbsp;<code>i</code>&nbsp;处的水龙头，可以灌溉的区域为&nbsp;<code>[i -&nbsp; ranges[i], i + ranges[i]]</code>&nbsp;。</p>

<p>请你返回可以灌溉整个花园的&nbsp;<strong>最少水龙头数目</strong>&nbsp;。如果花园始终存在无法灌溉到的地方，请你返回&nbsp;<strong>-1</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1326.Minimum%20Number%20of%20Taps%20to%20Open%20to%20Water%20a%20Garden/images/1685_example_1.png" /></p>

<pre>
<strong>输入：</strong>n = 5, ranges = [3,4,1,1,0,0]
<strong>输出：</strong>1
<strong>解释：
</strong>点 0 处的水龙头可以灌溉区间 [-3,3]
点 1 处的水龙头可以灌溉区间 [-3,5]
点 2 处的水龙头可以灌溉区间 [1,3]
点 3 处的水龙头可以灌溉区间 [2,4]
点 4 处的水龙头可以灌溉区间 [4,4]
点 5 处的水龙头可以灌溉区间 [5,5]
只需要打开点 1 处的水龙头即可灌溉整个花园 [0,5] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 3, ranges = [0,0,0,0]
<strong>输出：</strong>-1
<strong>解释：</strong>即使打开所有水龙头，你也无法灌溉整个花园。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>ranges.length == n + 1</code></li>
	<li><code>0 &lt;= ranges[i] &lt;= 100</code></li>
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
