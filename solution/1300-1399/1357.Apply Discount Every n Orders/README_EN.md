# [1357. Apply Discount Every n Orders](https://leetcode.com/problems/apply-discount-every-n-orders)

[中文文档](/solution/1300-1399/1357.Apply%20Discount%20Every%20n%20Orders/README.md)

## Description

<p>There is&nbsp;a sale in a supermarket, there will be a <code>discount</code> every <code>n</code> customer.<br />

There are some products in the supermarket where the id of the <code>i-th</code> product is <code>products[i]</code> and the price per unit of this product is&nbsp;<code>prices[i]</code>.<br />

The system will count the number of customers and when the <code>n-th</code> customer arrive he/she will have a <code>discount</code> on the bill. (i.e if the cost is <code>x</code> the new cost is <code>x - (discount \* x) / 100</code>). Then the system will start counting customers again.<br />

The customer orders a certain amount of each product where <code>product[i]</code> is the id of the <code>i-th</code> product the customer ordered and <code>amount[i]</code> is the number of units the customer ordered of that product.</p>

<p>Implement the <code>Cashier</code> class:</p>

<ul>
	<li><code>Cashier(int n, int discount, int[] products, int[] prices)</code> Initializes the object with <code>n</code>, the <code>discount</code>, the <code>products</code>&nbsp;and their <code>prices</code>.</li>
	<li><code>double&nbsp;getBill(int[] product, int[] amount)</code>&nbsp;returns the value of the bill and apply the discount if needed. Answers within <code>10^-5</code> of the actual value will be accepted as correct.</li>
</ul>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input</strong>

[&quot;Cashier&quot;,&quot;getBill&quot;,&quot;getBill&quot;,&quot;getBill&quot;,&quot;getBill&quot;,&quot;getBill&quot;,&quot;getBill&quot;,&quot;getBill&quot;]

[[3,50,[1,2,3,4,5,6,7],[100,200,300,400,300,200,100]],[[1,2],[1,2]],[[3,7],[10,10]],[[1,2,3,4,5,6,7],[1,1,1,1,1,1,1]],[[4],[10]],[[7,3],[10,10]],[[7,5,3,1,6,4,2],[10,10,10,9,9,9,7]],[[2,3,5],[5,3,2]]]

<strong>Output</strong>

[null,500.0,4000.0,800.0,4000.0,4000.0,7350.0,2500.0]

<strong>Explanation</strong>

Cashier cashier = new Cashier(3,50,[1,2,3,4,5,6,7],[100,200,300,400,300,200,100]);

cashier.getBill([1,2],[1,2]);                        // return 500.0, bill = 1 * 100 + 2 * 200 = 500.

cashier.getBill([3,7],[10,10]);                      // return 4000.0

cashier.getBill([1,2,3,4,5,6,7],[1,1,1,1,1,1,1]);    // return 800.0, The bill was 1600.0 but as this is the third customer, he has a discount of 50% which means his bill is only 1600 - 1600 * (50 / 100) = 800.

cashier.getBill([4],[10]);                           // return 4000.0

cashier.getBill([7,3],[10,10]);                      // return 4000.0

cashier.getBill([7,5,3,1,6,4,2],[10,10,10,9,9,9,7]); // return 7350.0, Bill was 14700.0 but as the system counted three more customers, he will have a 50% discount and the bill becomes 7350.0

cashier.getBill([2,3,5],[5,3,2]);                    // return 2500.0

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10^4</code></li>
	<li><code>0 &lt;= discount &lt;= 100</code></li>
	<li><code>1 &lt;= products.length &lt;= 200</code></li>
	<li><code>1 &lt;= products[i] &lt;= 200</code></li>
	<li>There are <strong>not</strong> repeated elements in the array <code>products</code>.</li>
	<li><code>prices.length == products.length</code></li>
	<li><code>1 &lt;= prices[i] &lt;= 1000</code></li>
	<li><code>1 &lt;= product.length &lt;= products.length</code></li>
	<li><code>product[i]</code> exists in <code>products</code>.</li>
	<li><code>amount.length == product.length</code></li>
	<li><code>1 &lt;= amount[i] &lt;= 1000</code></li>
	<li>At most <code>1000</code> calls will be made to <code>getBill</code>.</li>
	<li>Answers within&nbsp;<code>10^-5</code>&nbsp;of the actual value will be accepted as correct.</li>
</ul>

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
