# [2008. Maximum Earnings From Taxi](https://leetcode.com/problems/maximum-earnings-from-taxi)

[中文文档](/solution/2000-2099/2008.Maximum%20Earnings%20From%20Taxi/README.md)

## Description

<p>There are <code>n</code> points on a road you are driving your taxi on. The <code>n</code> points on the road are labeled from <code>1</code> to <code>n</code> in the direction you are going, and you want to drive from point <code>1</code> to point <code>n</code> to make money by picking up passengers. You cannot change the direction of the taxi.</p>

<p>The passengers are represented by a <strong>0-indexed</strong> 2D integer array <code>rides</code>, where <code>rides[i] = [start<sub>i</sub>, end<sub>i</sub>, tip<sub>i</sub>]</code> denotes the <code>i<sup>th</sup></code> passenger requesting a ride from point <code>start<sub>i</sub></code> to point <code>end<sub>i</sub></code> who is willing to give a <code>tip<sub>i</sub></code> dollar tip.</p>

<p>For<strong> each </strong>passenger <code>i</code> you pick up, you <strong>earn</strong> <code>end<sub>i</sub> - start<sub>i</sub> + tip<sub>i</sub></code> dollars. You may only drive <b>at most one </b>passenger at a time.</p>

<p>Given <code>n</code> and <code>rides</code>, return <em>the <strong>maximum</strong> number of dollars you can earn by picking up the passengers optimally.</em></p>

<p><strong>Note:</strong> You may drop off a passenger and pick up a different passenger at the same point.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 5, rides = [<u>[2,5,4]</u>,[1,5,1]]
<strong>Output:</strong> 7
<strong>Explanation:</strong> We can pick up passenger 0 to earn 5 - 2 + 4 = 7 dollars.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 20, rides = [[1,6,1],<u>[3,10,2]</u>,<u>[10,12,3]</u>,[11,12,2],[12,15,2],<u>[13,18,1]</u>]
<strong>Output:</strong> 20
<strong>Explanation:</strong> We will pick up the following passengers:
- Drive passenger 1 from point 3 to point 10 for a profit of 10 - 3 + 2 = 9 dollars.
- Drive passenger 2 from point 10 to point 12 for a profit of 12 - 10 + 3 = 5 dollars.
- Drive passenger 5 from point 13 to point 18 for a profit of 18 - 13 + 1 = 6 dollars.
We earn 9 + 5 + 6 = 20 dollars in total.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= rides.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>rides[i].length == 3</code></li>
	<li><code>1 &lt;= start<sub>i</sub> &lt; end<sub>i</sub> &lt;= n</code></li>
	<li><code>1 &lt;= tip<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
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
