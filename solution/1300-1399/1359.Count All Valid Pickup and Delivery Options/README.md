# [1359. 有效的快递序列数目](https://leetcode.cn/problems/count-all-valid-pickup-and-delivery-options)

[English Version](/solution/1300-1399/1359.Count%20All%20Valid%20Pickup%20and%20Delivery%20Options/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你&nbsp;<code>n</code>&nbsp;笔订单，每笔订单都需要快递服务。</p>

<p>请你统计所有有效的 收件/配送 序列的数目，确保第 <code>i</code> 个物品的配送服务&nbsp;<code>delivery(i)</code> 总是在其收件服务&nbsp;<code>pickup(i)</code> 之后。</p>

<p>由于答案可能很大，请返回答案对 <code>10^9 + 7</code> 取余的结果。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 1
<strong>输出：</strong>1
<strong>解释：</strong>只有一种序列 (P1, D1)，物品 1 的配送服务（D1）在物品 1 的收件服务（P1）后。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 2
<strong>输出：</strong>6
<strong>解释：</strong>所有可能的序列包括：
(P1,P2,D1,D2)，(P1,P2,D2,D1)，(P1,D1,P2,D2)，(P2,P1,D1,D2)，(P2,P1,D2,D1) 和 (P2,D2,P1,D1)。
(P1,D2,P2,D1) 是一个无效的序列，因为物品 2 的收件服务（P2）不应在物品 2 的配送服务（D2）之后。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>n = 3
<strong>输出：</strong>90
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

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

### **...**

```

```

<!-- tabs:end -->
