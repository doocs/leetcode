# [2093. Minimum Cost to Reach City With Discounts](https://leetcode.com/problems/minimum-cost-to-reach-city-with-discounts)

[中文文档](/solution/2000-2099/2093.Minimum%20Cost%20to%20Reach%20City%20With%20Discounts/README.md)

## Description

<p>A series of highways connect <code>n</code> cities numbered from <code>0</code> to <code>n - 1</code>. You are given a 2D integer array <code>highways</code> where <code>highways[i] = [city1<sub>i</sub>, city2<sub>i</sub>, toll<sub>i</sub>]</code> indicates that there is a highway that connects <code>city1<sub>i</sub></code> and <code>city2<sub>i</sub></code>, allowing a car to go from <code>city1<sub>i</sub></code> to <code>city2<sub>i</sub></code> <strong>and vice versa</strong> for a cost of <code>toll<sub>i</sub></code>.</p>

<p>You are also given an integer <code>discounts</code> which represents the number of discounts you have. You can use a discount to travel across the <code>i<sup>th</sup></code> highway for a cost of <code>toll<sub>i</sub> / 2</code> (<strong>integer</strong> <strong>division</strong>). Each discount may only be used <strong>once</strong>, and you can only use at most <strong>one</strong> discount per highway.</p>

<p>Return <em>the <strong>minimum total cost</strong> to go from city </em><code>0</code><em> to city </em><code>n - 1</code><em>, or </em><code>-1</code><em> if it is not possible to go from city </em><code>0</code><em> to city </em><code>n - 1</code><em>.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong><br />
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2093.Minimum%20Cost%20to%20Reach%20City%20With%20Discounts/images/image-20211129222429-1.png" style="height: 250px; width: 404px;" /></p>

<pre>
<strong>Input:</strong> n = 5, highways = [[0,1,4],[2,1,3],[1,4,11],[3,2,3],[3,4,2]], discounts = 1
<strong>Output:</strong> 9
<strong>Explanation:</strong>
Go from 0 to 1 for a cost of 4.
Go from 1 to 4 and use a discount for a cost of 11 / 2 = 5.
The minimum cost to go from 0 to 4 is 4 + 5 = 9.
</pre>

<p><strong>Example 2:</strong><br />
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2093.Minimum%20Cost%20to%20Reach%20City%20With%20Discounts/images/image-20211129222650-4.png" style="width: 284px; height: 250px;" /></p>

<pre>
<strong>Input:</strong> n = 4, highways = [[1,3,17],[1,2,7],[3,2,5],[0,1,6],[3,0,20]], discounts = 20
<strong>Output:</strong> 8
<strong>Explanation:</strong>
Go from 0 to 1 and use a discount for a cost of 6 / 2 = 3.
Go from 1 to 2 and use a discount for a cost of 7 / 2 = 3.
Go from 2 to 3 and use a discount for a cost of 5 / 2 = 2.
The minimum cost to go from 0 to 3 is 3 + 3 + 2 = 8.
</pre>

<p><strong>Example 3:</strong><br />
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2093.Minimum%20Cost%20to%20Reach%20City%20With%20Discounts/images/image-20211129222531-3.png" style="width: 275px; height: 250px;" /></p>

<pre>
<strong>Input:</strong> n = 4, highways = [[0,1,3],[2,3,2]], discounts = 0
<strong>Output:</strong> -1
<strong>Explanation:</strong>
It is impossible to go from 0 to 3 so return -1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= highways.length &lt;= 1000</code></li>
	<li><code>highways[i].length == 3</code></li>
	<li><code>0 &lt;= city1<sub>i</sub>, city2<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>city1<sub>i</sub> != city2<sub>i</sub></code></li>
	<li><code>0 &lt;= toll<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= discounts &lt;= 500</code></li>
	<li>There are no duplicate highways.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
