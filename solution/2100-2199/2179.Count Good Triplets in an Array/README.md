# [2179. 统计数组中好三元组数目](https://leetcode-cn.com/problems/count-good-triplets-in-an-array)

[English Version](/solution/2100-2199/2179.Count%20Good%20Triplets%20in%20an%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个下标从 <strong>0</strong>&nbsp;开始且长度为 <code>n</code>&nbsp;的整数数组&nbsp;<code>nums1</code>&nbsp;和&nbsp;<code>nums2</code>&nbsp;，两者都是&nbsp;<code>[0, 1, ..., n - 1]</code>&nbsp;的&nbsp;<strong>排列</strong>&nbsp;。</p>

<p><strong>好三元组&nbsp;</strong>指的是&nbsp;<code>3</code>&nbsp;个&nbsp;<strong>互不相同</strong>&nbsp;的值，且它们在数组&nbsp;<code>nums1</code> 和&nbsp;<code>nums2</code>&nbsp;中出现顺序保持一致。换句话说，如果我们将&nbsp;<code>pos1<sub>v</sub></code> 记为值&nbsp;<code>v</code>&nbsp;在&nbsp;<code>nums1</code>&nbsp;中出现的位置，<code>pos2<sub>v</sub></code>&nbsp;为值&nbsp;<code>v</code>&nbsp;在&nbsp;<code>nums2</code>&nbsp;中的位置，那么一个好三元组定义为&nbsp;<code>0 &lt;= x, y, z &lt;= n - 1</code>&nbsp;，且&nbsp;<code>pos1<sub>x</sub> &lt; pos1<sub>y</sub> &lt; pos1<sub>z</sub></code> 和&nbsp;<code>pos2<sub>x</sub> &lt; pos2<sub>y</sub> &lt; pos2<sub>z</sub></code>&nbsp;都成立的&nbsp;<code>(x, y, z)</code>&nbsp;。</p>

<p>请你返回好三元组的 <strong>总数目</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums1 = [2,0,1,3], nums2 = [0,1,2,3]
<b>输出：</b>1
<b>解释：</b>
总共有 4 个三元组 (x,y,z) 满足 pos1<sub>x</sub> &lt; pos1<sub>y</sub> &lt; pos1<sub>z&nbsp;</sub>，分别是 (2,0,1) ，(2,0,3) ，(2,1,3) 和 (0,1,3) 。
这些三元组中，只有 (0,1,3) 满足 pos2<sub>x</sub> &lt; pos2<sub>y</sub> &lt; pos2<sub>z</sub>&nbsp;。所以只有 1 个好三元组。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums1 = [4,0,1,3,2], nums2 = [4,1,0,2,3]
<b>输出：</b>4
<b>解释：</b>总共有 4 个好三元组 (4,0,3) ，(4,0,2) ，(4,1,3) 和 (4,1,2) 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums1.length == nums2.length</code></li>
	<li><code>3 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums1[i], nums2[i] &lt;= n - 1</code></li>
	<li><code>nums1</code>&nbsp;和&nbsp;<code>nums2</code>&nbsp;是&nbsp;<code>[0, 1, ..., n - 1]</code> 的排列。</li>
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
