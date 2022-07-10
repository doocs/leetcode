# [2332. The Latest Time to Catch a Bus](https://leetcode.com/problems/the-latest-time-to-catch-a-bus)

[中文文档](/solution/2300-2399/2332.The%20Latest%20Time%20to%20Catch%20a%20Bus/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>buses</code> of length <code>n</code>, where <code>buses[i]</code> represents the departure time of the <code>i<sup>th</sup></code> bus. You are also given a <strong>0-indexed</strong> integer array <code>passengers</code> of length <code>m</code>, where <code>passengers[j]</code> represents the arrival time of the <code>j<sup>th</sup></code> passenger. All bus departure times are unique. All passenger arrival times are unique.</p>

<p>You are given an integer <code>capacity</code>, which represents the <strong>maximum</strong> number of passengers that can get on each bus.</p>

<p>The passengers will get on the next available bus. You can get on a bus that will depart at <code>x</code> minutes if you arrive at <code>y</code> minutes where <code>y &lt;= x</code>, and the bus is not full. Passengers with the <strong>earliest</strong> arrival times get on the bus first.</p>

<p>Return <em>the latest time you may arrive at the bus station to catch a bus</em>. You <strong>cannot</strong> arrive at the same time as another passenger.</p>

<p><strong>Note: </strong>The arrays <code>buses</code> and <code>passengers</code> are not necessarily sorted.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> buses = [10,20], passengers = [2,17,18,19], capacity = 2
<strong>Output:</strong> 16
<strong>Explanation:</strong> 
The 1<sup>st</sup> bus departs with the 1<sup>st</sup> passenger. 
The 2<sup>nd</sup> bus departs with you and the 2<sup>nd</sup> passenger.
Note that you must not arrive at the same time as the passengers, which is why you must arrive before the 2<sup>nd</sup><sup> </sup>passenger to catch the bus.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> buses = [20,30,10], passengers = [19,13,26,4,25,11,21], capacity = 2
<strong>Output:</strong> 20
<strong>Explanation:</strong> 
The 1<sup>st</sup> bus departs with the 4<sup>th</sup> passenger. 
The 2<sup>nd</sup> bus departs with the 6<sup>th</sup>&nbsp;and 2<sup>nd</sup><sup> </sup>passengers.
The 3<sup>rd</sup> bus departs with the 1<sup>s</sup><sup>t</sup> passenger and you.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == buses.length</code></li>
	<li><code>m == passengers.length</code></li>
	<li><code>1 &lt;= n, m, capacity &lt;= 10<sup>5</sup></code></li>
	<li><code>2 &lt;= buses[i], passengers[i] &lt;= 10<sup>9</sup></code></li>
	<li>Each element in <code>buses</code> is <strong>unique</strong>.</li>
	<li>Each element in <code>passengers</code> is <strong>unique</strong>.</li>
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
