# [2137. 通过倒水操作让所有的水桶所含水量相等](https://leetcode.cn/problems/pour-water-between-buckets-to-make-water-levels-equal)

[English Version](/solution/2100-2199/2137.Pour%20Water%20Between%20Buckets%20to%20Make%20Water%20Levels%20Equal/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你有 <code>n</code> 个水桶，每个水桶中所含的水量用一个 <b>下标从 0 开始</b>&nbsp;的数组 <code>buckets</code> 给出，第 <code>i</code> 个水桶中有 <code>buckets[i]</code> 升水。</p>

<p>你想让所有的水桶中所含的水量相同。你可以从一个水桶向其它任意一个水桶倒任意数量的水（可以不是整数）。但是，你每倒 <code>k</code> 升水，<strong>百分之</strong> <code>loss</code> 的水会洒掉。</p>

<p>请返回经过倒水操作，所有水桶中的水量相同时，每个水桶中的 <strong>最大</strong> 水量。如果你的答案和标准答案的误差不超过 <code>10<sup>-5</sup></code>，那么答案将被通过。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> buckets = [1,2,7], loss = 80
<strong>输出:</strong> 2.00000
<strong>解释:</strong> 从水桶 2 向水桶 0 倒 5 升水。
5 * 80% = 4 升水会洒掉，水桶 0 只会获得 5 - 4 = 1 升水。
此时所有的水桶中都含有 2 升水，所以返回 2。</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> buckets = [2,4,6], loss = 50
<strong>输出:</strong> 3.50000
<strong>解释:</strong> 从水桶 1 向水桶 0 倒 0.5 升水。
0.5 * 50% = 0.25 升水会洒掉，水桶 0 只会获得 0.5 - 0.25 = 0.25 升水。
此时, buckets = [2.25, 3.5, 6].

从水桶 2 向水桶 0 倒 2.5 升水。
2.5 * 50% = 1.25 升水会洒掉，水桶 0 只会获得 2.5 - 1.25 = 1.25 升水。
此时所有的水桶中都含有 3.5 升水，所以返回 3.5。
</pre>

<p><strong>示例 3:</strong></p>

<pre><strong>输入:</strong> buckets = [3,3,3,3], loss = 40
<strong>输出:</strong> 3.00000
<strong>解释:</strong> 所有的水桶已经含有相同的水量。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= buckets.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= buckets[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= loss &lt;= 99</code></li>
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

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts

```

### **...**

```

```

<!-- tabs:end -->
