---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4029.Elevator%20Requests%20IV/README_EN.md
---

<!-- problem:start -->

# [4029. Elevator Requests IV 🔒](https://leetcode.com/problems/elevator-requests-iv)

[中文文档](/solution/4000-4099/4029.Elevator%20Requests%20IV/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code> denoting the number of floors in a building, where the floors are numbered from 0 to <code>n - 1</code>.</p>

<p>You are also given an integer <code>start</code> and a 2D integer array <code>requests</code>, where <code>requests[i] = [arrival<sub>i</sub>, floor<sub>i</sub>]</code> indicates that a request for <code>floor<sub>i</sub></code> is made at time <code>arrival<sub>i</sub></code>.</p>

<p>At time 0, the elevator is at floor <code>start</code>.</p>

<p>At each second, the elevator may move <strong>up</strong> by 1 floor, move <strong>down</strong> by 1 floor, or <strong>remain</strong> on its current floor.</p>

<p>A request can be fulfilled <strong>only</strong> at or after its arrival time; it is fulfilled <strong>instantly</strong> when the elevator is on its requested floor at any time from its arrival time onward.</p>

<p>Return the <strong>minimum</strong> time needed to fulfill all requests.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 9, start = 0, requests = [[0,8],[6,5]]</span></p>

<p><strong>Output:</strong> <span class="example-io">9</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Move from floor 0 (<code>start</code>) to floor 5 (<code>requests[1][1]</code>) in 5 seconds, reaching at time 5. Since <code>requests[1][0] = 6</code>, wait until time 6 to fulfill it.</li>
	<li>Move from floor 5 to floor 8 (<code>requests[0][1]</code>) in 3 seconds, fulfilling it at time 9.</li>
</ul>

<p>Thus, all requests are fulfilled by time 9.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 8, start = 5, requests = [[1,7],[7,3]]</span></p>

<p><strong>Output:</strong> <span class="example-io">7</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Move from floor 5 (<code>start</code>) to floor 7 (<code>requests[0][1]</code>) in 2 seconds, reaching at time 2. Since <code>requests[0][0] = 1</code> has already passed, floor 7 is fulfilled at time 2.</li>
	<li>Move from floor 7 to floor 3 (<code>requests[1][1]</code>) in 4 seconds, reaching at time 6. Since <code>requests[1][0] = 7</code>, wait until time 7.</li>
</ul>

<p>Thus, all requests are fulfilled by time 7.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 7, start = 3, requests = [[0,5],[0,1],[6,3]]</span></p>

<p><strong>Output:</strong> <span class="example-io">8</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Move from floor 3 (<code>start</code>) to floor 5 (<code>requests[0][1]</code>) in 2 seconds, fulfilling it at time 2.</li>
	<li>Move from floor 5 to floor 1 (<code>requests[1][1]</code>) in 4 seconds, fulfilling it at time 6.</li>
	<li>Move from floor 1 to floor 3 (<code>requests[2][1]</code>) in 2 seconds, reaching at time 8. Its request arrived at <code>requests[2][0] = 6</code>, so floor 3 is fulfilled at time 8.</li>
</ul>

<p>Thus, all requests are fulfilled by time 8.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= requests.length &lt;= 500</code></li>
	<li><code>requests[i] == [arrival<sub>i</sub>, floor<sub>i</sub>]</code></li>
	<li><code>0 &lt;= arrival<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= start, floor<sub>i</sub> &lt;= n - 1</code></li>
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
