---
comments: true
difficulty: Easy
---

<!-- problem:start -->

# [4116. Assign Umbrella on Rainy Days](https://leetcode.com/problems/assign-umbrella-on-rainy-days)

[中文文档](/solution/4100-4199/4116.Assign%20Umbrella%20on%20Rainy%20Days/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>binary array</strong> <code>rain</code> of length <code>n</code>, where <code>rain[i] == 1</code> means it rains on day <code>i</code>, and <code>rain[i] == 0</code> means it does not.</p>

<p>Alice and Bob share a <strong>single umbrella</strong> over these <code>n</code> days. On each day <code>i</code>:</p>

<ul>
	<li>Alice needs the umbrella on day <code>i</code> if <code>rain[i] == rain[i + 1] == 1</code> for <code>0 &lt;= i &lt; n - 1</code>.</li>
	<li>Bob needs the umbrella if <code>rain[i] == 1</code>.</li>
	<li><strong>At most one</strong> of them can receive the umbrella.</li>
	<li>If only one person needs it, that person receives it.</li>
	<li>If <strong>both</strong> need it, it goes to whoever has received it <strong>fewer</strong> times so far. If they are <strong>tied</strong>, it goes to Alice.</li>
	<li>If neither needs it, no one receives it.</li>
</ul>

<p>A person&#39;s count <strong>increases</strong> only on a day they receive the umbrella. Initially, both counts are 0.</p>

<p>Return a string array <code>answer</code> of length <code>n</code>, where <code>answer[i]</code> is <code>&quot;Alice&quot;</code>, <code>&quot;Bob&quot;</code>, or <code>&quot;None&quot;</code>, denoting who receives the umbrella on day <code>i</code>.</p>
A <strong>binary array</strong> is an array that contains only 0 and 1.
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">rain = [1,1,0,1,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">[&quot;Alice&quot;,&quot;Bob&quot;,&quot;None&quot;,&quot;Alice&quot;,&quot;Bob&quot;]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Day 0: <code>rain[0] == 1</code> and <code>rain[1] == 1</code>, so both need it. Counts are tied, so Alice receives it.</li>
	<li>Day 1: <code>rain[1] == 1</code>, so Bob needs it, but <code>rain[2] == 0</code>, so Alice does not need it. Therefore Bob receives it.</li>
	<li>Day 2: No rain, so no one receives it.</li>
	<li>Day 3: <code>rain[3] == 1</code> and <code>rain[4] == 1</code>, so both need it. Counts are tied at 1, so Alice receives it.</li>
	<li>Day 4: Last day, so only Bob needs it, and he receives it.</li>
	<li>Therefore, <code>answer = [&quot;Alice&quot;, &quot;Bob&quot;, &quot;None&quot;, &quot;Alice&quot;, &quot;Bob&quot;]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">rain = [0,1,0,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">[&quot;None&quot;,&quot;Bob&quot;,&quot;None&quot;,&quot;Bob&quot;]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>It never rains on two consecutive days, so Alice never needs the umbrella.</li>
	<li>Bob receives it on days 1 and 3, and no one receives it on days 0 and 2.</li>
	<li>Therefore, <code>answer = [&quot;None&quot;, &quot;Bob&quot;, &quot;None&quot;, &quot;Bob&quot;]</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == rain.length &lt;= 100</code></li>
	<li><code>rain[i]</code> is either 0 or 1.</li>
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
