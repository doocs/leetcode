# [1744. Can You Eat Your Favorite Candy on Your Favorite Day](https://leetcode.com/problems/can-you-eat-your-favorite-candy-on-your-favorite-day)

[中文文档](/solution/1700-1799/1744.Can%20You%20Eat%20Your%20Favorite%20Candy%20on%20Your%20Favorite%20Day/README.md)

## Description

<p>You are given a <strong>(0-indexed)</strong> array of positive integers <code>candiesCount</code> where <code>candiesCount[i]</code> represents the number of candies of the&nbsp;<code>i<sup>th</sup></code>&nbsp;type you have. You are also given a 2D array <code>queries</code> where <code>queries[i] = [favoriteType<sub>i</sub>, favoriteDay<sub>i</sub>, dailyCap<sub>i</sub>]</code>.</p>

<p>You play a game with the following rules:</p>

<ul>
	<li>You start eating candies on day <code><strong>0</strong></code>.</li>
	<li>You <b>cannot</b> eat <strong>any</strong> candy of type <code>i</code> unless you have eaten <strong>all</strong> candies of type <code>i - 1</code>.</li>
	<li>You must eat <strong>at least</strong> <strong>one</strong> candy per day until you have eaten all the candies.</li>
</ul>

<p>Construct a boolean array <code>answer</code> such that <code>answer.length == queries.length</code> and <code>answer[i]</code> is <code>true</code> if you can eat a candy of type <code>favoriteType<sub>i</sub></code> on day <code>favoriteDay<sub>i</sub></code> without eating <strong>more than</strong> <code>dailyCap<sub>i</sub></code> candies on <strong>any</strong> day, and <code>false</code> otherwise. Note that you can eat different types of candy on the same day, provided that you follow rule 2.</p>

<p>Return <em>the constructed array </em><code>answer</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> candiesCount = [7,4,5,3,8], queries = [[0,2,2],[4,2,4],[2,13,1000000000]]
<strong>Output:</strong> [true,false,true]
<strong>Explanation:</strong>
1- If you eat 2 candies (type 0) on day 0 and 2 candies (type 0) on day 1, you will eat a candy of type 0 on day 2.
2- You can eat at most 4 candies each day.
   If you eat 4 candies every day, you will eat 4 candies (type 0) on day 0 and 4 candies (type 0 and type 1) on day 1.
   On day 2, you can only eat 4 candies (type 1 and type 2), so you cannot eat a candy of type 4 on day 2.
3- If you eat 1 candy each day, you will eat a candy of type 2 on day 13.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> candiesCount = [5,2,6,4,1], queries = [[3,1,2],[4,10,3],[3,10,100],[4,100,30],[1,3,1]]
<strong>Output:</strong> [false,true,true,false,false]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= candiesCount.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= candiesCount[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i].length == 3</code></li>
	<li><code>0 &lt;= favoriteType<sub>i</sub> &lt; candiesCount.length</code></li>
	<li><code>0 &lt;= favoriteDay<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= dailyCap<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
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
