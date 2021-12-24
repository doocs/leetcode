# [871. Minimum Number of Refueling Stops](https://leetcode.com/problems/minimum-number-of-refueling-stops)

[中文文档](/solution/0800-0899/0871.Minimum%20Number%20of%20Refueling%20Stops/README.md)

## Description

<p>A car travels from a starting position to a destination which is <code>target</code> miles east of the starting position.</p>

<p>Along the way, there are gas stations.&nbsp; Each <code>station[i]</code>&nbsp;represents a gas station that is <code>station[i][0]</code> miles east of the starting position, and has <code>station[i][1]</code> liters of gas.</p>

<p>The car starts with an infinite tank of gas, which initially has&nbsp;<code>startFuel</code>&nbsp;liters of fuel in it.&nbsp; It uses 1 liter of gas per 1 mile that it drives.</p>

<p>When the car&nbsp;reaches a gas station, it may stop and refuel, transferring all the gas from the station into the car.</p>

<p>What is the least number of refueling stops the car must make in order to reach its destination?&nbsp; If it cannot reach the destination, return <code>-1</code>.</p>

<p>Note that if the car reaches a gas station with 0 fuel left, the car can still refuel there.&nbsp; If the car reaches the destination with 0 fuel left, it is still considered to have arrived.</p>

<p>&nbsp;</p>

<div>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong>target = <span id="example-input-1-1">1</span>, startFuel = <span id="example-input-1-2">1</span>, stations = <span id="example-input-1-3">[]</span>

<strong>Output: </strong><span id="example-output-1">0</span>

<strong>Explanation: </strong>We can reach the target without refueling.

</pre>

<div>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong>target = <span id="example-input-2-1">100</span>, startFuel = <span id="example-input-2-2">1</span>, stations = <span id="example-input-2-3">[[10,100]]</span>

<strong>Output: </strong><span id="example-output-2">-1</span>

<strong>Explanation: </strong>We can&#39;t reach the target (or even the first gas station).

</pre>

<div>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input: </strong>target = <span id="example-input-3-1">100</span>, startFuel = <span id="example-input-3-2">10</span>, stations = <span id="example-input-3-3">[[10,60],[20,30],[30,30],[60,40]]</span>

<strong>Output: </strong><span id="example-output-3">2</span>

<strong>Explanation: </strong>

We start with 10 liters of fuel.

We drive to position 10, expending 10 liters of fuel.  We refuel from 0 liters to 60 liters of gas.

Then, we drive from position 10 to position 60 (expending 50 liters of fuel),

and refuel from 10 liters to 50 liters of gas.  We then drive to and reach the target.

We made 2 refueling stops along the way, so we return 2.

</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>1 &lt;= target, startFuel, stations[i][1] &lt;= 10^9</code></li>
	<li><code>0 &lt;= stations.length &lt;= 500</code></li>
	<li><code>0 &lt; stations[0][0] &lt; stations[1][0] &lt; ... &lt; stations[stations.length-1][0] &lt; target</code></li>
</ol>

</div>

</div>

</div>

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
