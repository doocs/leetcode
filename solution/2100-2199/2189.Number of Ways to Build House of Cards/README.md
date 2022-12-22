# [2189. 建造纸牌屋的方法数](https://leetcode.cn/problems/number-of-ways-to-build-house-of-cards)

[English Version](/solution/2100-2199/2189.Number%20of%20Ways%20to%20Build%20House%20of%20Cards/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数 <code>n</code>，代表你拥有牌的数量。一个&nbsp;<strong>纸牌屋&nbsp;</strong>满足以下条件:</p>

<ul>
	<li>一个<strong> 纸牌屋&nbsp;</strong>由一行或多行&nbsp;<strong>三角形</strong> 和水平纸牌组成。</li>
	<li><strong>三角形&nbsp;</strong>是由两张卡片相互靠在一起形成的。</li>
	<li>一张卡片必须水平放置在一行中&nbsp;<strong>所有相邻&nbsp;</strong>的三角形之间。</li>
	<li>比第一行高的任何三角形都必须放在前一行的水平牌上。</li>
	<li>每个三角形都被放置在行中&nbsp;<strong>最左边&nbsp;</strong>的可用位置。</li>
</ul>

<p>返回<em>使用所有 <code>n</code> 张卡片可以构建的不同纸牌屋的数量</em>。如果存在一行两个纸牌屋包含不同数量的纸牌，那么两个纸牌屋被认为是不同的。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2189.Number%20of%20Ways%20to%20Build%20House%20of%20Cards/images/image-20220227213243-1.png" style="width: 726px; height: 150px;" />
<pre>
<strong>输入:</strong> n = 16
<strong>输出:</strong> 2
<strong>解释:</strong> 有两种有效的纸牌屋摆法。
图中的第三个纸牌屋无效，因为第一行最右边的三角形没有放在水平纸牌的顶部。
</pre>

<p><strong class="example">Example 2:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2189.Number%20of%20Ways%20to%20Build%20House%20of%20Cards/images/image-20220227213306-2.png" style="width: 96px; height: 80px;" />
<pre>
<strong>输入:</strong> n = 2
<strong>输出:</strong> 1
<strong>解释:</strong> 这是唯一可行的纸牌屋。</pre>

<p><strong class="example">Example 3:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2189.Number%20of%20Ways%20to%20Build%20House%20of%20Cards/images/image-20220227213331-3.png" style="width: 330px; height: 85px;" />
<pre>
<strong>输入:</strong> n = 4
<strong>输出:</strong> 0
<strong>解释:</strong> 图中的三种纸牌都是无效的。
第一个纸牌屋需要在两个三角形之间放置一张水平纸牌。
第二个纸牌屋使用 5 张纸牌。
第三个纸牌屋使用 2 张纸牌。</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 500</code></li>
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
