# [1357. Apply Discount Every n Orders](https://leetcode.com/problems/apply-discount-every-n-orders)

[中文文档](/solution/1300-1399/1357.Apply%20Discount%20Every%20n%20Orders/README.md)

## Description

<p>There is a supermarket that is frequented by many customers. The products sold at the supermarket are represented as two parallel integer arrays <code>products</code> and <code>prices</code>, where the <code>i<sup>th</sup></code> product has an ID of <code>products[i]</code> and a price of <code>prices[i]</code>.</p>

<p>When a customer is paying, their bill is represented as two parallel integer arrays <code>product</code> and <code>amount</code>, where the <code>j<sup>th</sup></code> product they purchased has an ID of <code>product[j]</code>, and <code>amount[j]</code> is how much of the product they bought. Their subtotal is calculated as the sum of each <code>amount[j] * (price of the j<sup>th</sup> product)</code>.</p>

<p>The supermarket decided to have a sale. Every <code>n<sup>th</sup></code> customer paying for their groceries will be given a <strong>percentage discount</strong>. The discount amount is given by <code>discount</code>, where they will be given <code>discount</code> percent off their subtotal. More formally, if their subtotal is <code>bill</code>, then they would actually pay <code>bill * ((100 - discount) / 100)</code>.</p>

<p>Implement the <code>Cashier</code> class:</p>

<ul>
	<li><code>Cashier(int n, int discount, int[] products, int[] prices)</code> Initializes the object with <code>n</code>, the <code>discount</code>, and the <code>products</code> and their <code>prices</code>.</li>
	<li><code>double getBill(int[] product, int[] amount)</code> Returns the final total of the bill with the discount applied (if any). Answers within <code>10<sup>-5</sup></code> of the actual value will be accepted.</li>
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
cashier.getBill([1,2],[1,2]);                        // return 500.0. 1<sup>st</sup> customer, no discount.
                                                     // bill = 1 * 100 + 2 * 200 = 500.
cashier.getBill([3,7],[10,10]);                      // return 4000.0. 2<sup>nd</sup> customer, no discount.
                                                     // bill = 10 * 300 + 10 * 100 = 4000.
cashier.getBill([1,2,3,4,5,6,7],[1,1,1,1,1,1,1]);    // return 800.0. 3<sup>rd</sup> customer, 50% discount.
                                                     // Original bill = 1600
                                                     // Actual bill = 1600 * ((100 - 50) / 100) = 800.
cashier.getBill([4],[10]);                           // return 4000.0. 4<sup>th</sup> customer, no discount.
cashier.getBill([7,3],[10,10]);                      // return 4000.0. 5<sup>th</sup> customer, no discount.
cashier.getBill([7,5,3,1,6,4,2],[10,10,10,9,9,9,7]); // return 7350.0. 6<sup>th</sup> customer, 50% discount.
                                                     // Original bill = 14700, but with
                                                     // Actual bill = 14700 * ((100 - 50) / 100) = 7350.
cashier.getBill([2,3,5],[5,3,2]);                    // return 2500.0.  6<sup>th</sup> customer, no discount.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= discount &lt;= 100</code></li>
	<li><code>1 &lt;= products.length &lt;= 200</code></li>
	<li><code>prices.length == products.length</code></li>
	<li><code>1 &lt;= products[i] &lt;= 200</code></li>
	<li><code>1 &lt;= prices[i] &lt;= 1000</code></li>
	<li>The elements in <code>products</code> are <strong>unique</strong>.</li>
	<li><code>1 &lt;= product.length &lt;= products.length</code></li>
	<li><code>amount.length == product.length</code></li>
	<li><code>product[j]</code> exists in <code>products</code>.</li>
	<li><code>1 &lt;= amount[j] &lt;= 1000</code></li>
	<li>The elements of <code>product</code> are <strong>unique</strong>.</li>
	<li>At most <code>1000</code> calls will be made to <code>getBill</code>.</li>
	<li>Answers within <code>10<sup>-5</sup></code> of the actual value will be accepted.</li>
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
