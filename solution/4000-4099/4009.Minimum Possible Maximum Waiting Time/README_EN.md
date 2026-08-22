---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4009.Minimum%20Possible%20Maximum%20Waiting%20Time/README_EN.md
rating: 2498
source: Biweekly Contest 188 Q4
tags:
    - Memoization
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [4009. Minimum Possible Maximum Waiting Time](https://leetcode.com/problems/minimum-possible-maximum-waiting-time)

[中文文档](/solution/4000-4099/4009.Minimum%20Possible%20Maximum%20Waiting%20Time/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>demand</code>, where <code>demand[i]</code> is the amount of fuel required by the <code>i<sup>th</sup></code> car.</p>

<p>You are also given an integer array <code>fuel</code> of length 2. There are <strong>exactly</strong> two fuel dispensers, numbered 0 and 1, where <code>fuel[j]</code> is the initial amount of fuel available in dispenser <code>j</code>.</p>

<p>Cars are allowed to start refueling in <strong>increasing</strong> index order. Car 0 becomes allowed at time 0, and for each <code>i &gt; 0</code>, car <code>i</code> becomes allowed <strong>exactly</strong> when car <code>i - 1</code> starts refueling.</p>

<p>The refueling process follows these rules:</p>

<ul>
	<li>Each dispenser can serve <strong>at most</strong> one car at a time.</li>
	<li>When a car becomes allowed, you must choose a dispenser with <strong>at least</strong> <code>demand[i]</code> fuel remaining. If both dispensers have enough fuel remaining, you may choose <strong>either</strong> of them, regardless of when they become free.</li>
	<li>The car waits until the chosen dispenser becomes free and starts refueling <strong>immediately</strong>. It cannot switch dispensers or intentionally wait after the chosen dispenser becomes free.</li>
	<li>When a car starts refueling, the remaining fuel in the chosen dispenser decreases by <code>demand[i]</code>, and the dispenser remains occupied for <code>demand[i]</code> seconds.</li>
	<li>Once started, refueling cannot be interrupted.</li>
	<li>If neither dispenser has at least <code>demand[i]</code> fuel remaining when car <code>i</code> becomes allowed, the process terminates and no further cars can be served.</li>
</ul>

<p>The <strong>waiting time</strong> of a car is the time between when it becomes allowed to start refueling and when it actually starts.</p>

<p>Return the <strong>minimum</strong> possible value of the <strong>maximum</strong> waiting time among all served cars over all assignments that <strong>maximize</strong> the number of served cars. If no car can be served, return -1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">demand = [6,8,4,6,5], fuel = [16,13]</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p>The following assignment serves all five cars:</p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border-collapse:collapse; text-align:center;">
	<tbody>
		<tr>
			<th>Car</th>
			<th>Becomes allowed at</th>
			<th>Starts refueling at</th>
			<th>Dispenser used</th>
			<th>Remaining fuel before start<br />
			(dispenser 0, dispenser 1)</th>
			<th>Waiting time</th>
		</tr>
		<tr>
			<td>0</td>
			<td>0</td>
			<td>0</td>
			<td>0</td>
			<td>(16, 13)</td>
			<td>0</td>
		</tr>
		<tr>
			<td>1</td>
			<td>0</td>
			<td>0</td>
			<td>1</td>
			<td>(10, 13)</td>
			<td>0</td>
		</tr>
		<tr>
			<td>2</td>
			<td>0</td>
			<td>6</td>
			<td>0</td>
			<td>(10, 5)</td>
			<td>6</td>
		</tr>
		<tr>
			<td>3</td>
			<td>6</td>
			<td>10</td>
			<td>0</td>
			<td>(6, 5)</td>
			<td>4</td>
		</tr>
		<tr>
			<td>4</td>
			<td>10</td>
			<td>10</td>
			<td>1</td>
			<td>(0, 5)</td>
			<td>0</td>
		</tr>
	</tbody>
</table>

<p>Thus, all five cars are served, and the maximum waiting time is 6.</p>

<p>To serve all five cars, dispenser 0 must serve the cars with demands 6, 4, and 6, while dispenser 1 must serve the cars with demands 8 and 5. Therefore, car 2 must wait until time 6 for dispenser 0 to become free, so no assignment serving all five cars can have a maximum waiting time less than 6.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">demand = [10,15], fuel = [12,17]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>At time 0, Car 0 becomes allowed and starts refuelling using dispenser 0.</li>
	<li>Car 1 becomes allowed at time 0 (when Car 0 starts) and immediately starts refuelling using dispenser 1.</li>
	<li>Both cars start without waiting, so the maximum waiting time is 0.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">demand = [10,5], fuel = [8,8]</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>At time 0, Car 0 becomes allowed. However, neither dispenser has enough fuel to serve it, so the process terminates immediately.</li>
	<li>No car is served, so the answer is -1.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= demand.length &lt;= 50</code></li>
	<li><code>1 &lt;= demand[i] &lt;= 20</code></li>
	<li><code>fuel.length == 2</code></li>
	<li><code>1 &lt;= fuel[i] &lt;= 50</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python

```

#### Java

```java

```

#### C++

```cpp

```

#### Go

```go

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
