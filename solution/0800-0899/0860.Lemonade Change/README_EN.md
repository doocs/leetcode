# [860. Lemonade Change](https://leetcode.com/problems/lemonade-change)

## Description
<p>At a lemonade stand, each lemonade costs <code>$5</code>.&nbsp;</p>



<p>Customers are standing in a queue to buy from you, and order one at a time (in the order specified by <code>bills</code>).</p>



<p>Each customer will only buy one lemonade and&nbsp;pay with either a <code>$5</code>, <code>$10</code>, or <code>$20</code> bill.&nbsp; You must provide the correct change to each customer, so that the net transaction is that the customer pays $5.</p>



<p>Note that you don&#39;t have any change&nbsp;in hand at first.</p>



<p>Return <code>true</code>&nbsp;if and only if you can provide every customer with correct change.</p>



<p>&nbsp;</p>



<div>

<p><strong>Example 1:</strong></p>



<pre>

<strong>Input: </strong><span id="example-input-1-1">[5,5,5,10,20]</span>

<strong>Output: </strong><span id="example-output-1">true</span>

<strong>Explanation: </strong>

From the first 3 customers, we collect three $5 bills in order.

From the fourth customer, we collect a $10 bill and give back a $5.

From the fifth customer, we give a $10 bill and a $5 bill.

Since all customers got correct change, we output true.

</pre>



<div>

<p><strong>Example 2:</strong></p>



<pre>

<strong>Input: </strong><span id="example-input-2-1">[5,5,10]</span>

<strong>Output: </strong><span id="example-output-2">true</span>

</pre>



<div>

<p><strong>Example 3:</strong></p>



<pre>

<strong>Input: </strong><span id="example-input-3-1">[10,10]</span>

<strong>Output: </strong><span id="example-output-3">false</span>

</pre>



<div>

<p><strong>Example 4:</strong></p>



<pre>

<strong>Input: </strong><span id="example-input-4-1">[5,5,10,10,20]</span>

<strong>Output: </strong><span id="example-output-4">false</span>

<strong>Explanation: </strong>

From the first two customers in order, we collect two $5 bills.

For the next two customers in order, we collect a $10 bill and give back a $5 bill.

For the last customer, we can't give change of $15 back because we only have two $10 bills.

Since not every customer received correct change, the answer is false.

</pre>



<p>&nbsp;</p>



<p><strong><span>Note:</span></strong></p>



<ul>

	<li><code>0 &lt;= bills.length &lt;= 10000</code></li>

	<li><code>bills[i]</code>&nbsp;will be either&nbsp;<code>5</code>, <code>10</code>, or <code>20</code>.</li>

</ul>

</div>

</div>

</div>

</div>




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