# [1169. 查询无效交易](https://leetcode.cn/problems/invalid-transactions)

[English Version](/solution/1100-1199/1169.Invalid%20Transactions/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>如果出现下述两种情况，交易 <strong>可能无效</strong>：</p>

<ul>
	<li>交易金额超过<meta charset="UTF-8" />&nbsp;<code>$1000</code></li>
	<li>或者，它和&nbsp;<strong>另一个城市</strong>&nbsp;中 <strong>同名</strong> 的另一笔交易相隔不超过 <code>60</code> 分钟（包含 60 分钟整）</li>
</ul>

<p>给定字符串数组交易清单<meta charset="UTF-8" />&nbsp;<code>transaction</code>&nbsp;。每个交易字符串&nbsp;<code>transactions[i]</code>&nbsp;由一些用逗号分隔的值组成，这些值分别表示交易的名称，时间（以分钟计），金额以及城市。</p>

<p>返回&nbsp;<code>transactions</code>，返回可能无效的交易列表。你可以按 <strong>任何顺序</strong> 返回答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>transactions = ["alice,20,800,mtv","alice,50,100,beijing"]
<strong>输出：</strong>["alice,20,800,mtv","alice,50,100,beijing"]
<strong>解释：</strong>第一笔交易是无效的，因为第二笔交易和它间隔不超过 60 分钟、名称相同且发生在不同的城市。同样，第二笔交易也是无效的。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>transactions = ["alice,20,800,mtv","alice,50,1200,mtv"]
<strong>输出：</strong>["alice,50,1200,mtv"]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>transactions = ["alice,20,800,mtv","bob,50,1200,mtv"]
<strong>输出：</strong>["bob,50,1200,mtv"]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>transactions.length &lt;= 1000</code></li>
	<li>每笔交易&nbsp;<code>transactions[i]</code>&nbsp;按&nbsp;<code>"{name},{time},{amount},{city}"</code>&nbsp;的格式进行记录</li>
	<li>每个交易名称&nbsp;<code>{name}</code>&nbsp;和城市&nbsp;<code>{city}</code>&nbsp;都由小写英文字母组成，长度在&nbsp;<code>1</code>&nbsp;到&nbsp;<code>10</code>&nbsp;之间</li>
	<li>每个交易时间&nbsp;<code>{time}</code>&nbsp;由一些数字组成，表示一个&nbsp;<code>0</code>&nbsp;到&nbsp;<code>1000</code>&nbsp;之间的整数</li>
	<li>每笔交易金额&nbsp;<code>{amount}</code>&nbsp;由一些数字组成，表示一个&nbsp;<code>0</code> 到&nbsp;<code>2000</code>&nbsp;之间的整数</li>
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
