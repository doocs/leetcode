# [787. Cheapest Flights Within K Stops](https://leetcode.com/problems/cheapest-flights-within-k-stops)

[中文文档](/solution/0700-0799/0787.Cheapest%20Flights%20Within%20K%20Stops/README.md)

## Description

<p>There are <code>n</code> cities connected by&nbsp;<code>m</code> flights. Each flight starts from city&nbsp;<code>u</code> and arrives at&nbsp;<code>v</code> with a price <code>w</code>.</p>

<p>Now given all the cities and flights, together with starting city <code>src</code> and the destination&nbsp;<code>dst</code>, your task is to find the cheapest price from <code>src</code> to <code>dst</code> with up to <code>k</code> stops. If there is no such route, output <code>-1</code>.</p>

<pre>
<strong>Example 1:</strong>
<strong>Input:</strong> 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 1
<strong>Output:</strong> 200
<strong>Explanation:</strong> 
The graph looks like this:
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0787.Cheapest%20Flights%20Within%20K%20Stops/images/995.png" style="height:180px; width:246px" />

The cheapest price from city <code>0</code> to city <code>2</code> with at most 1 stop costs 200, as marked red in the picture.</pre>

<pre>
<strong>Example 2:</strong>
<strong>Input:</strong> 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 0
<strong>Output:</strong> 500
<strong>Explanation:</strong> 
The graph looks like this:
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0787.Cheapest%20Flights%20Within%20K%20Stops/images/995.png" style="height:180px; width:246px" />

The cheapest price from city <code>0</code> to city <code>2</code> with at most 0 stop costs 500, as marked blue in the picture.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of&nbsp;nodes&nbsp;<code>n</code> will be&nbsp;in range <code>[1, 100]</code>, with nodes labeled from <code>0</code> to <code>n</code><code> - 1</code>.</li>
	<li>The&nbsp;size of <code>flights</code> will be&nbsp;in range <code>[0, n * (n - 1) / 2]</code>.</li>
	<li>The format of each flight will be <code>(src, </code><code>dst</code><code>, price)</code>.</li>
	<li>The price of each flight will be in the range <code>[1, 10000]</code>.</li>
	<li><code>k</code> is in the range of <code>[0, n - 1]</code>.</li>
	<li>There&nbsp;will&nbsp;not&nbsp;be&nbsp;any&nbsp;duplicated&nbsp;flights or&nbsp;self&nbsp;cycles.</li>
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
