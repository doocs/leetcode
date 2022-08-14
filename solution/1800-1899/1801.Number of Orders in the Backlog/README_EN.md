# [1801. Number of Orders in the Backlog](https://leetcode.com/problems/number-of-orders-in-the-backlog)

[中文文档](/solution/1800-1899/1801.Number%20of%20Orders%20in%20the%20Backlog/README.md)

## Description

<p>You are given a 2D integer array <code>orders</code>, where each <code>orders[i] = [price<sub>i</sub>, amount<sub>i</sub>, orderType<sub>i</sub>]</code> denotes that <code>amount<sub>i</sub></code><sub> </sub>orders have been placed of type <code>orderType<sub>i</sub></code> at the price <code>price<sub>i</sub></code>. The <code>orderType<sub>i</sub></code> is:</p>

<ul>
    <li><code>0</code> if it is a batch of <code>buy</code> orders, or</li>
    <li><code>1</code> if it is a batch of <code>sell</code> orders.</li>
</ul>

<p>Note that <code>orders[i]</code> represents a batch of <code>amount<sub>i</sub></code> independent orders with the same price and order type. All orders represented by <code>orders[i]</code> will be placed before all orders represented by <code>orders[i+1]</code> for all valid <code>i</code>.</p>

<p>There is a <strong>backlog</strong> that consists of orders that have not been executed. The backlog is initially empty. When an order is placed, the following happens:</p>

<ul>

    <li>If the order is a <code>buy</code> order, you look at the <code>sell</code> order with the <strong>smallest</strong> price in the backlog. If that <code>sell</code> order&#39;s price is <strong>smaller than or equal to</strong> the current <code>buy</code> order&#39;s price, they will match and be executed, and that <code>sell</code> order will be removed from the backlog. Else, the <code>buy</code> order is added to the backlog.</li>


    <li>Vice versa, if the order is a <code>sell</code> order, you look at the <code>buy</code> order with the <strong>largest</strong> price in the backlog. If that <code>buy</code> order&#39;s price is <strong>larger than or equal to</strong> the current <code>sell</code> order&#39;s price, they will match and be executed, and that <code>buy</code> order will be removed from the backlog. Else, the <code>sell</code> order is added to the backlog.</li>

</ul>

<p>Return <em>the total <strong>amount</strong> of orders in the backlog after placing all the orders from the input</em>. Since this number can be large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1801.Number%20of%20Orders%20in%20the%20Backlog/images/ex1.png" style="width: 450px; height: 479px;" />

<pre>


<strong>Input:</strong> orders = [[10,5,0],[15,2,1],[25,1,1],[30,4,0]]


<strong>Output:</strong> 6


<strong>Explanation:</strong> Here is what happens with the orders:


- 5 orders of type buy with price 10 are placed. There are no sell orders, so the 5 orders are added to the backlog.


- 2 orders of type sell with price 15 are placed. There are no buy orders with prices larger than or equal to 15, so the 2 orders are added to the backlog.


- 1 order of type sell with price 25 is placed. There are no buy orders with prices larger than or equal to 25 in the backlog, so this order is added to the backlog.


- 4 orders of type buy with price 30 are placed. The first 2 orders are matched with the 2 sell orders of the least price, which is 15 and these 2 sell orders are removed from the backlog. The 3<sup>rd</sup> order is matched with the sell order of the least price, which is 25 and this sell order is removed from the backlog. Then, there are no more sell orders in the backlog, so the 4<sup>th</sup> order is added to the backlog.


Finally, the backlog has 5 buy orders with price 10, and 1 buy order with price 30. So the total number of orders in the backlog is 6.


</pre>

<p><strong>Example 2:</strong></p>

<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1801.Number%20of%20Orders%20in%20the%20Backlog/images/ex2.png" style="width: 450px; height: 584px;" />

<pre>


<strong>Input:</strong> orders = [[7,1000000000,1],[15,3,0],[5,999999995,0],[5,1,1]]


<strong>Output:</strong> 999999984


<strong>Explanation:</strong> Here is what happens with the orders:


- 10<sup>9</sup> orders of type sell with price 7 are placed. There are no buy orders, so the 10<sup>9</sup> orders are added to the backlog.


- 3 orders of type buy with price 15 are placed. They are matched with the 3 sell orders with the least price which is 7, and those 3 sell orders are removed from the backlog.


- 999999995 orders of type buy with price 5 are placed. The least price of a sell order is 7, so the 999999995 orders are added to the backlog.


- 1 order of type sell with price 5 is placed. It is matched with the buy order of the highest price, which is 5, and that buy order is removed from the backlog.


Finally, the backlog has (1000000000-3) sell orders with price 7, and (999999995-1) buy orders with price 5. So the total number of orders = 1999999991, which is equal to 999999984 % (10<sup>9</sup> + 7).


</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>

    <li><code>1 &lt;= orders.length &lt;= 10<sup>5</sup></code></li>


    <li><code>orders[i].length == 3</code></li>


    <li><code>1 &lt;= price<sub>i</sub>, amount<sub>i</sub> &lt;= 10<sup>9</sup></code></li>


    <li><code>orderType<sub>i</sub></code> is either <code>0</code> or <code>1</code>.</li>

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
