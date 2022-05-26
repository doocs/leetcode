# [1049. Last Stone Weight II](https://leetcode.com/problems/last-stone-weight-ii)

[中文文档](/solution/1000-1099/1049.Last%20Stone%20Weight%20II/README.md)

## Description

<p>We have a collection of rocks, each rock has a positive integer weight.</p>

<p>Each turn, we choose <strong>any two rocks</strong>&nbsp;and smash them together.&nbsp; Suppose the stones have weights <code>x</code> and <code>y</code> with <code>x &lt;= y</code>.&nbsp; The result of this smash is:</p>

<ul>
    <li>If <code>x == y</code>, both stones are totally destroyed;</li>
    <li>If <code>x != y</code>, the stone of weight <code>x</code> is totally destroyed, and the stone of weight <code>y</code> has new weight <code>y-x</code>.</li>
</ul>

<p>At the end, there is at most 1 stone left.&nbsp; Return the <strong>smallest possible</strong> weight of this stone (the weight is&nbsp;0 if there are no stones left.)</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong>[2,7,4,1,8,1]

<strong>Output: </strong>1

<strong>Explanation: </strong>

We can combine 2 and 4 to get 2 so the array converts to [2,7,1,8,1] then,

we can combine 7 and 8 to get 1 so the array converts to [2,1,1,1] then,

we can combine 2 and 1 to get 1 so the array converts to [1,1,1] then,

we can combine 1 and 1 to get 0 so the array converts to [1] then that&#39;s the optimal value.

</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
    <li><code>1 &lt;= stones.length &lt;= 30</code></li>
    <li><code>1 &lt;= stones[i] &lt;= 100</code></li>
</ol>

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
