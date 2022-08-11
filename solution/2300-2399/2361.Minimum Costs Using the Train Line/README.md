# [2361. 乘坐火车路线的最少费用](https://leetcode.cn/problems/minimum-costs-using-the-train-line)

[English Version](/solution/2300-2399/2361.Minimum%20Costs%20Using%20the%20Train%20Line/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>城市中的火车有两条路线，分别是常规路线和特快路线。两条路线经过 <strong>相同 </strong>的 <code>n + 1</code> 个车站，车站编号从 <code>0</code> 到 <code>n</code>。初始时，你位于车站 <code>0</code> 的常规路线。</p>

<p>给你两个<strong> 下标从 1 开始 </strong>、长度均为 <code>n</code> 的两个整数数组 <code>regular</code> 和 <code>express</code> ，其中 <code>regular[i]</code> 表示乘坐常规路线从车站&nbsp;<code>i - 1</code> 到车站&nbsp;<code>i</code> 的费用，<code>express[i]</code> 表示乘坐特快路线从车站&nbsp;<code>i - 1</code> 到车站&nbsp;<code>i</code> 的费用。</p>

<p>另外给你一个整数 <code>expressCost</code>，表示从常规路线转换到特快路线的费用。</p>

<p>注意：</p>

<ul>
	<li>从特快路线转换回常规路线没有费用。</li>
	<li><strong>每次 </strong>从常规路线转换到特快路线，你都需要支付 <code>expressCost</code> 的费用。</li>
	<li>留在特快路线上没有额外费用。</li>
</ul>

<p>返回<strong> 下标从 1 开始</strong> 、长度为 <code>n</code> 的数组 <code>costs</code>，其中 <code>costs[i]</code> 是从车站 <code>0</code> 到车站 <code>i</code> 的最少费用。</p>

<p>注意：每个车站都可以从任意一条路线 <strong>到达 </strong>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2361.Minimum%20Costs%20Using%20the%20Train%20Line/images/ex1drawio.png" style="width: 442px; height: 150px;" />
<pre>
<strong>输入：</strong>regular = [1,6,9,5], express = [5,2,3,10], expressCost = 8
<strong>输出：</strong>[1,7,14,19]
<strong>解释：</strong>上图展示了从车站 0 到车站 4 的最少费用方法。
- 乘坐常规路线从车站 0 到车站 1，费用是 1。
- 乘坐特快路线从车站 1 到车站 2，费用是 8 + 2 = 10。
- 乘坐特快路线从车站 2 到车站 3，费用是 3。
- 乘坐特快路线从车站 3 到车站 4，费用是 5。
总费用是 1 + 10 + 3 + 5 + 19。
注意到达其他车站的最少费用方法可以选择不同的路线。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2361.Minimum%20Costs%20Using%20the%20Train%20Line/images/ex2drawio.png" style="width: 346px; height: 150px;" />
<pre>
<strong>输入：</strong>regular = [11,5,13], express = [7,10,6], expressCost = 3
<strong>输出：</strong>[10,15,24]
<strong>解释：</strong>上图展示了从车站 0 到车站 3 的最少费用方法。
- 乘坐特快路线从车站 0 到车站 1，费用是 3 + 7 = 10。
- 乘坐常规路线从车站 1 到车站 2，费用是 5。
- 乘坐特快路线从车站 2 到车站 3，费用是 3 + 6 = 9。
总费用是 10 + 5 + 9 = 24。
注意转换回特快路线时需要再次支付 expressCost 的费用。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == regular.length == express.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= regular[i], express[i], expressCost &lt;= 10<sup>5</sup></code></li>
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
