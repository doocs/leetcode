# [465. 最优账单平衡](https://leetcode.cn/problems/optimal-account-balancing)

[English Version](/solution/0400-0499/0465.Optimal%20Account%20Balancing/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一群朋友在度假期间会相互借钱。比如说，小爱同学支付了小新同学的午餐共计 10 美元。如果小明同学支付了小爱同学的出租车钱共计 5 美元。我们可以用一个三元组 (x, y, z) 表示一次交易，表示 x 借给 y 共计 z 美元。用 0, 1, 2 表示小爱同学、小新同学和小明同学（0, 1, 2 为人的标号），上述交易可以表示为 <code>[[0, 1, 10], [2, 0, 5]]</code>。</p>

<p>给定一群人之间的交易信息列表，计算能够还清所有债务的最小次数。</p>

<p><strong>注意：</strong></p>

<ol>
	<li>一次交易会以三元组 (x, y, z) 表示，并有&nbsp;<code>x &ne; y</code>&nbsp;且&nbsp;<code>z &gt; 0</code>。</li>
	<li>人的标号可能不是按顺序的，例如标号可能为 0, 1, 2 也可能为 0, 2, 6。</li>
</ol>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>
[[0,1,10], [2,0,5]]

<strong>输出：</strong>
2

<strong>解释：</strong>
人 #0 给人 #1 共计 10 美元。
人 #2 给人 #0 共计 5 美元。

需要两次交易。一种方式是人 #1 分别给人 #0 和人 #2 各 5 美元。
</pre>

<p>&nbsp;</p>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>
[[0,1,10], [1,0,1], [1,2,5], [2,0,5]]

<strong>输出：</strong>
1

<strong>解释：</strong>
人 #0 给人 #1 共计 10 美元。Person #0 gave person #1 $10.
人 #1 给人 #0 共计 1 美元。Person #1 gave person #0 $1.
人 #1 给人 #2 共计 5 美元。Person #1 gave person #2 $5.
人 #2 给人 #0 共计 5 美元。Person #2 gave person #0 $5.

因此，人 #1 需要给人 #0 共计 4 美元，所有的债务即可还清。
</pre>

<p>&nbsp;</p>

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
