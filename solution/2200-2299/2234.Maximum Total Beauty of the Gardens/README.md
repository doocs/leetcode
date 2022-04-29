# [2234. 花园的最大总美丽值](https://leetcode.cn/problems/maximum-total-beauty-of-the-gardens)

[English Version](/solution/2200-2299/2234.Maximum%20Total%20Beauty%20of%20the%20Gardens/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Alice 是&nbsp;<code>n</code>&nbsp;个花园的园丁，她想通过种花，最大化她所有花园的总美丽值。</p>

<p>给你一个下标从 <strong>0</strong>&nbsp;开始大小为 <code>n</code>&nbsp;的整数数组&nbsp;<code>flowers</code>&nbsp;，其中&nbsp;<code>flowers[i]</code>&nbsp;是第 <code>i</code>&nbsp;个花园里已经种的花的数目。已经种了的花 <strong>不能</strong>&nbsp;移走。同时给你&nbsp;<code>newFlowers</code>&nbsp;，表示 Alice 额外可以种花的&nbsp;<strong>最大数目</strong>&nbsp;。同时给你的还有整数&nbsp;<code>target</code>&nbsp;，<code>full</code>&nbsp;和&nbsp;<code>partial</code>&nbsp;。</p>

<p>如果一个花园有 <strong>至少</strong>&nbsp;<code>target</code>&nbsp;朵花，那么这个花园称为 <strong>完善的</strong>&nbsp;，花园的 <strong>总美丽值</strong>&nbsp;为以下分数之 <strong>和</strong> ：</p>

<ul>
	<li><b>完善</b> 花园数目乘以&nbsp;<code>full</code>.</li>
	<li>剩余 <strong>不完善</strong>&nbsp;花园里，花的 <strong>最少数目</strong>&nbsp;乘以&nbsp;<code>partial</code>&nbsp;。如果没有不完善花园，那么这一部分的值为&nbsp;<code>0</code>&nbsp;。</li>
</ul>

<p>请你返回 Alice 种最多 <code>newFlowers</code>&nbsp;朵花以后，能得到的<strong>&nbsp;最大</strong>&nbsp;总美丽值。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>flowers = [1,3,1,1], newFlowers = 7, target = 6, full = 12, partial = 1
<b>输出：</b>14
<b>解释：</b>Alice 可以按以下方案种花
- 在第 0 个花园种 2 朵花
- 在第 1 个花园种 3 朵花
- 在第 2 个花园种 1 朵花
- 在第 3 个花园种 1 朵花
花园里花的数目为 [3,6,2,2] 。总共种了 2 + 3 + 1 + 1 = 7 朵花。
只有 1 个花园是完善的。
不完善花园里花的最少数目是 2 。
所以总美丽值为 1 * 12 + 2 * 1 = 12 + 2 = 14 。
没有其他方案可以让花园总美丽值超过 14 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>flowers = [2,4,5,3], newFlowers = 10, target = 5, full = 2, partial = 6
<b>输出：</b>30
<b>解释：</b>Alice 可以按以下方案种花
- 在第 0 个花园种 3 朵花
- 在第 1 个花园种 0 朵花
- 在第 2 个花园种 0 朵花
- 在第 3 个花园种 2 朵花
花园里花的数目为 [5,4,5,5] 。总共种了 3 + 0 + 0 + 2 = 5 朵花。
有 3 个花园是完善的。
不完善花园里花的最少数目为 4 。
所以总美丽值为 3 * 2 + 4 * 6 = 6 + 24 = 30 。
没有其他方案可以让花园总美丽值超过 30 。
注意，Alice可以让所有花园都变成完善的，但这样她的总美丽值反而更小。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= flowers.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= flowers[i], target &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= newFlowers &lt;= 10<sup>10</sup></code></li>
	<li><code>1 &lt;= full, partial &lt;= 10<sup>5</sup></code></li>
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
