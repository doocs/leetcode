# [1357. 每隔 n 个顾客打折](https://leetcode.cn/problems/apply-discount-every-n-orders)

[English Version](/solution/1300-1399/1357.Apply%20Discount%20Every%20n%20Orders/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>超市里正在举行打折活动，每隔&nbsp;<code>n</code>&nbsp;个顾客会得到 <code>discount</code>&nbsp;的折扣。</p>

<p>超市里有一些商品，第&nbsp;<code>i</code>&nbsp;种商品为&nbsp;<code>products[i]</code>&nbsp;且每件单品的价格为&nbsp;<code>prices[i]</code>&nbsp;。</p>

<p>结账系统会统计顾客的数目，每隔&nbsp;<code>n</code>&nbsp;个顾客结账时，该顾客的账单都会打折，折扣为&nbsp;<code>discount</code>&nbsp;（也就是如果原本账单为&nbsp;<code>x</code>&nbsp;，那么实际金额会变成&nbsp;<code>x - (discount * x) / 100</code>&nbsp;），然后系统会重新开始计数。</p>

<p>顾客会购买一些商品，&nbsp;<code>product[i]</code>&nbsp;是顾客购买的第&nbsp;<code>i</code>&nbsp;种商品，&nbsp;<code>amount[i]</code>&nbsp;是对应的购买该种商品的数目。</p>

<p>请你实现&nbsp;<code>Cashier</code>&nbsp;类：</p>

<ul>
	<li><code>Cashier(int n, int discount, int[] products, int[] prices)</code>&nbsp;初始化实例对象，参数分别为打折频率&nbsp;<code>n</code>&nbsp;，折扣大小 <code>discount</code>&nbsp;，超市里的商品列表 <code>products</code>&nbsp;和它们的价格 <code>prices</code>&nbsp;。</li>
	<li><code>double&nbsp;getBill(int[] product, int[] amount)</code>&nbsp;返回账单的实际金额（如果有打折，请返回打折后的结果）。返回结果与标准答案误差在&nbsp;<code>10^-5</code>&nbsp;以内都视为正确结果。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入</strong>
[&quot;Cashier&quot;,&quot;getBill&quot;,&quot;getBill&quot;,&quot;getBill&quot;,&quot;getBill&quot;,&quot;getBill&quot;,&quot;getBill&quot;,&quot;getBill&quot;]
[[3,50,[1,2,3,4,5,6,7],[100,200,300,400,300,200,100]],[[1,2],[1,2]],[[3,7],[10,10]],[[1,2,3,4,5,6,7],[1,1,1,1,1,1,1]],[[4],[10]],[[7,3],[10,10]],[[7,5,3,1,6,4,2],[10,10,10,9,9,9,7]],[[2,3,5],[5,3,2]]]
<strong>输出</strong>
[null,500.0,4000.0,800.0,4000.0,4000.0,7350.0,2500.0]
<strong>解释</strong>
Cashier cashier = new Cashier(3,50,[1,2,3,4,5,6,7],[100,200,300,400,300,200,100]);
cashier.getBill([1,2],[1,2]);                        // 返回 500.0, 账单金额为 = 1 * 100 + 2 * 200 = 500.
cashier.getBill([3,7],[10,10]);                      // 返回 4000.0
cashier.getBill([1,2,3,4,5,6,7],[1,1,1,1,1,1,1]);    // 返回 800.0 ，账单原本为 1600.0 ，但由于该顾客是第三位顾客，他将得到 50% 的折扣，所以实际金额为 1600 - 1600 * (50 / 100) = 800 。
cashier.getBill([4],[10]);                           // 返回 4000.0
cashier.getBill([7,3],[10,10]);                      // 返回 4000.0
cashier.getBill([7,5,3,1,6,4,2],[10,10,10,9,9,9,7]); // 返回 7350.0 ，账单原本为 14700.0 ，但由于系统计数再次达到三，该顾客将得到 50% 的折扣，实际金额为 7350.0 。
cashier.getBill([2,3,5],[5,3,2]);                    // 返回 2500.0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10^4</code></li>
	<li><code>0 &lt;= discount &lt;= 100</code></li>
	<li><code>1 &lt;= products.length &lt;= 200</code></li>
	<li><code>1 &lt;= products[i] &lt;= 200</code></li>
	<li><code>products</code>&nbsp;列表中&nbsp;<strong>不会</strong>&nbsp;有重复的元素。</li>
	<li><code>prices.length == products.length</code></li>
	<li><code>1 &lt;= prices[i] &lt;= 1000</code></li>
	<li><code>1 &lt;= product.length &lt;= products.length</code></li>
	<li><code>product[i]</code>&nbsp;在&nbsp;<code>products</code>&nbsp;出现过。</li>
	<li><code>amount.length == product.length</code></li>
	<li><code>1 &lt;= amount[i] &lt;= 1000</code></li>
	<li>最多有&nbsp;<code>1000</code> 次对&nbsp;<code>getBill</code>&nbsp;函数的调用。</li>
	<li>返回结果与标准答案误差在&nbsp;<code>10^-5</code>&nbsp;以内都视为正确结果。</li>
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
