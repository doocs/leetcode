# [465. Optimal Account Balancing](https://leetcode.com/problems/optimal-account-balancing)

[中文文档](/solution/0400-0499/0465.Optimal%20Account%20Balancing/README.md)

## Description

<p>A group of friends went on holiday and sometimes lent each other money. For example, Alice paid for Bill's lunch for $10. Then later Chris gave Alice $5 for a taxi ride. We can model each transaction as a tuple (x, y, z) which means person x gave person y $z. Assuming Alice, Bill, and Chris are person 0, 1, and 2 respectively (0, 1, 2 are the person's ID), the transactions can be represented as <code>[[0, 1, 10], [2, 0, 5]]</code>.</p>

<p>Given a list of transactions between a group of people, return the minimum number of transactions required to settle the debt.</p>

<p><b>Note:</b>
<ol>
<li>A transaction will be given as a tuple (x, y, z). Note that <code>x ≠ y</code> and <code>z > 0</code>.</li>
<li>Person's IDs may not be linear, e.g. we could have the persons 0, 1, 2 or we could also have the persons 0, 2, 6.</li>
</ol>
</p>

<p><b>Example 1:</b>
<pre>
<b>Input:</b>
[[0,1,10], [2,0,5]]

<b>Output:</b>
2

<b>Explanation:</b>
Person #0 gave person #1 $10.
Person #2 gave person #0 $5.

Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 \$5 each.

</pre>
</p>

<p><b>Example 2:</b>
<pre>
<b>Input:</b>
[[0,1,10], [1,0,1], [1,2,5], [2,0,5]]

<b>Output:</b>
1

<b>Explanation:</b>
Person #0 gave person #1 $10.
Person #1 gave person #0 $1.
Person #1 gave person #2 $5.
Person #2 gave person #0 $5.

Therefore, person #1 only need to give person #0 \$4, and all debt is settled.

</pre>
</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **...**

```

```

<!-- tabs:end -->
