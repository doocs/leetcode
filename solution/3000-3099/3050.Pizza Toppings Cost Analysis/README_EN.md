# [3050. Pizza Toppings Cost Analysis](https://leetcode.com/problems/pizza-toppings-cost-analysis)

[中文文档](/solution/3000-3099/3050.Pizza%20Toppings%20Cost%20Analysis/README.md)

<!-- tags: -->

## Description

<p>Table: <code><font face="monospace">Toppings</font></code></p>

<pre>
+--------------+---------+ 
| Column Name  | Type    | 
+--------------+---------+ 
| topping_name | varchar | 
| cost         | decimal |
+--------------+---------+
topping_name is the primary key for this table.
Each row of this table contains topping name and the cost of the topping. 
</pre>

<p>Write a solution to calculate the <strong>total cost</strong> of <strong>all possible <code>3</code>-topping</strong> pizza combinations from a given list of toppings. The total cost of toppings must be <strong>rounded</strong> to <code>2</code> <strong>decimal</strong> places.</p>

<p><strong>Note:</strong></p>

<ul>
	<li><strong>Do not</strong> include the pizzas where a topping is <strong>repeated</strong>. For example, &lsquo;Pepperoni, Pepperoni, Onion Pizza&rsquo;.</li>
	<li>Toppings <strong>must be</strong> listed in <strong>alphabetical order</strong>. For example, &#39;Chicken, Onions, Sausage&#39;. &#39;Onion, Sausage, Chicken&#39; is not acceptable.</li>
</ul>

<p>Return<em> the result table ordered by total cost in</em> <em><strong>descending</strong></em> <em>order and combination of toppings in <strong>ascending</strong> order.</em></p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
Toppings table:
+--------------+------+
| topping_name | cost |
+--------------+------+
| Pepperoni    | 0.50 |
| Sausage      | 0.70 |
| Chicken      | 0.55 |
| Extra Cheese | 0.40 |
+--------------+------+
<strong>Output:</strong> 
+--------------------------------+------------+
| pizza                          | total_cost | 
+--------------------------------+------------+
| Chicken,Pepperoni,Sausage      | 1.75       |  
| Chicken,Extra Cheese,Sausage   | 1.65       |
| Extra Cheese,Pepperoni,Sausage | 1.60       |
| Chicken,Extra Cheese,Pepperoni | 1.45       | 
+--------------------------------+------------+
<strong>Explanation:</strong> 
There are only four different combinations possible with the three topings:
- Chicken, Pepperoni, Sausage: Total cost is $1.75 (Chicken $0.55, Pepperoni $0.50, Sausage $0.70).
- Chicken, Extra Cheese, Sausage: Total cost is $1.65 (Chicken $0.55, Extra Cheese $0.40, Sausage $0.70).
- Extra Cheese, Pepperoni, Sausage: Total cost is $1.60 (Extra Cheese $0.40, Pepperoni $0.50, Sausage $0.70).
- Chicken, Extra Cheese, Pepperoni: Total cost is $1.45 (Chicken $0.55, Extra Cheese $0.40, Pepperoni $0.50).
Output table is ordered by the total cost in descending order.</pre>

## Solutions

### Solution 1

<!-- tabs:start -->

```sql

```

<!-- tabs:end -->

<!-- end -->
