# [1333. Filter Restaurants by Vegan-Friendly, Price and Distance](https://leetcode.com/problems/filter-restaurants-by-vegan-friendly-price-and-distance)

[中文文档](/solution/1300-1399/1333.Filter%20Restaurants%20by%20Vegan-Friendly%2C%20Price%20and%20Distance/README.md)

## Description

<p>Given the array <code>restaurants</code> where &nbsp;<code>restaurants[i] = [id<sub>i</sub>, rating<sub>i</sub>, veganFriendly<sub>i</sub>, price<sub>i</sub>, distance<sub>i</sub>]</code>. You have to filter the restaurants using three filters.</p>

<p>The <code>veganFriendly</code> filter will be either <em>true</em> (meaning you should only include restaurants with <code>veganFriendly<sub>i</sub></code> set to true)&nbsp;or <em>false</em>&nbsp;(meaning you can include any restaurant). In addition, you have the filters&nbsp;<code>maxPrice</code> and <code>maxDistance</code>&nbsp;which&nbsp;are the maximum value for price and distance of restaurants you should consider respectively.</p>

<p>Return the array of restaurant <em><strong>IDs</strong></em> after filtering, ordered by <strong>rating</strong> from highest to lowest. For restaurants with the same rating, order them by <em><strong>id</strong></em> from highest to lowest. For simplicity <code>veganFriendly<sub>i</sub></code> and <code>veganFriendly</code> take value <em>1</em> when it is <em>true</em>, and <em>0</em> when it is <em>false</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> restaurants = [[1,4,1,40,10],[2,8,0,50,5],[3,8,1,30,4],[4,10,0,10,3],[5,1,1,15,1]], veganFriendly = 1, maxPrice = 50, maxDistance = 10
<strong>Output:</strong> [3,1,5] 
<strong>Explanation: 
</strong>The restaurants are:
Restaurant 1 [id=1, rating=4, veganFriendly=1, price=40, distance=10]
Restaurant 2 [id=2, rating=8, veganFriendly=0, price=50, distance=5]
Restaurant 3 [id=3, rating=8, veganFriendly=1, price=30, distance=4]
Restaurant 4 [id=4, rating=10, veganFriendly=0, price=10, distance=3]
Restaurant 5 [id=5, rating=1, veganFriendly=1, price=15, distance=1] 
After filter restaurants with veganFriendly = 1, maxPrice = 50 and maxDistance = 10 we have restaurant 3, restaurant 1 and restaurant 5 (ordered by rating from highest to lowest). 
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> restaurants = [[1,4,1,40,10],[2,8,0,50,5],[3,8,1,30,4],[4,10,0,10,3],[5,1,1,15,1]], veganFriendly = 0, maxPrice = 50, maxDistance = 10
<strong>Output:</strong> [4,3,2,1,5]
<strong>Explanation:</strong> The restaurants are the same as in example 1, but in this case the filter veganFriendly = 0, therefore all restaurants are considered.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> restaurants = [[1,4,1,40,10],[2,8,0,50,5],[3,8,1,30,4],[4,10,0,10,3],[5,1,1,15,1]], veganFriendly = 0, maxPrice = 30, maxDistance = 3
<strong>Output:</strong> [4,5]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;=&nbsp;restaurants.length &lt;= 10^4</code></li>
	<li><code>restaurants[i].length == 5</code></li>
	<li><code>1 &lt;=&nbsp;id<sub>i</sub>, rating<sub>i</sub>, price<sub>i</sub>, distance<sub>i </sub>&lt;= 10^5</code></li>
	<li><code>1 &lt;=&nbsp;maxPrice,&nbsp;maxDistance &lt;= 10^5</code></li>
	<li><code>veganFriendly<sub>i</sub></code> and&nbsp;<code>veganFriendly</code>&nbsp;are&nbsp;0 or 1.</li>
	<li>All <code>id<sub>i</sub></code> are distinct.</li>
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
