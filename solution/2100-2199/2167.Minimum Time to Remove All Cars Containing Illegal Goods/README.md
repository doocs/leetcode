# [2167. 移除所有载有违禁货物车厢所需的最少时间](https://leetcode-cn.com/problems/minimum-time-to-remove-all-cars-containing-illegal-goods)

[English Version](/solution/2100-2199/2167.Minimum%20Time%20to%20Remove%20All%20Cars%20Containing%20Illegal%20Goods/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的二进制字符串 <code>s</code> ，表示一个列车车厢序列。<code>s[i] = '0'</code> 表示第 <code>i</code> 节车厢 <strong>不</strong> 含违禁货物，而 <code>s[i] = '1'</code> 表示第 <code>i</code> 节车厢含违禁货物。</p>

<p>作为列车长，你需要清理掉所有载有违禁货物的车厢。你可以不限次数执行下述三种操作中的任意一个：</p>

<ol>
	<li>从列车 <strong>左</strong> 端移除一节车厢（即移除 <code>s[0]</code>），用去 1 单位时间。</li>
	<li>从列车 <strong>右</strong> 端移除一节车厢（即移除 <code>s[s.length - 1]</code>），用去 1 单位时间。</li>
	<li>从列车车厢序列的 <strong>任意位置</strong> 移除一节车厢，用去 2 单位时间。</li>
</ol>

<p>返回移除所有载有违禁货物车厢所需要的 <strong>最少</strong> 单位时间数。</p>

<p>注意，空的列车车厢序列视为没有车厢含违禁货物。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "<em><strong>11</strong></em>00<em><strong>1</strong></em>0<em><strong>1</strong></em>"
<strong>输出：</strong>5
<strong>解释：</strong>
一种从序列中移除所有载有违禁货物的车厢的方法是：
- 从左端移除一节车厢 2 次。所用时间是 2 * 1 = 2 。
- 从右端移除一节车厢 1 次。所用时间是 1 。
- 移除序列中间位置载有违禁货物的车厢。所用时间是 2 。
总时间是 2 + 1 + 2 = 5 。

一种替代方法是：
- 从左端移除一节车厢 2 次。所用时间是 2 * 1 = 2 。
- 从右端移除一节车厢 3 次。所用时间是 3 * 1 = 3 。
总时间也是 2 + 3 = 5 。

5 是移除所有载有违禁货物的车厢所需要的最少单位时间数。
没有其他方法能够用更少的时间移除这些车厢。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "00<em><strong>1</strong></em>0"
<strong>输出：</strong>2
<strong>解释：</strong>
一种从序列中移除所有载有违禁货物的车厢的方法是：
- 从左端移除一节车厢 3 次。所用时间是 3 * 1 = 3 。
总时间是 3.

另一种从序列中移除所有载有违禁货物的车厢的方法是：
- 移除序列中间位置载有违禁货物的车厢。所用时间是 2 。
总时间是 2.

另一种从序列中移除所有载有违禁货物的车厢的方法是：
- 从右端移除一节车厢 2 次。所用时间是 2 * 1 = 2 。
总时间是 2.

2 是移除所有载有违禁货物的车厢所需要的最少单位时间数。
没有其他方法能够用更少的时间移除这些车厢。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>s[i]</code> 为 <code>'0'</code> 或 <code>'1'</code></li>
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
