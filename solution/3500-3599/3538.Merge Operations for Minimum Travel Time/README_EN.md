---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3538.Merge%20Operations%20for%20Minimum%20Travel%20Time/README_EN.md
tags:
    - Array
    - Dynamic Programming
    - Prefix Sum
---

<!-- problem:start -->

# [3538. Merge Operations for Minimum Travel Time](https://leetcode.com/problems/merge-operations-for-minimum-travel-time)

[中文文档](/solution/3500-3599/3538.Merge%20Operations%20for%20Minimum%20Travel%20Time/README.md)

## Description

<!-- description:start -->

<p data-end="452" data-start="24">You are given a straight road of length <code>l</code> km, an integer <code>n</code>, an integer <code>k</code><strong data-end="83" data-start="78">, </strong>and <strong>two</strong> integer arrays, <code>position</code> and <code>time</code>, each of length <code>n</code>.</p>

<p data-end="452" data-start="24">The array <code>position</code> lists the positions (in km) of signs in <strong>strictly</strong> increasing order (with <code>position[0] = 0</code> and <code>position[n - 1] = l</code>).</p>

<p data-end="452" data-start="24">Each <code>time[i]</code> represents the time (in minutes) required to travel 1 km between <code>position[i]</code> and <code>position[i + 1]</code>.</p>

<p data-end="593" data-start="454">You <strong>must</strong> perform <strong>exactly</strong> <code>k</code> merge operations. In one merge, you can choose any <strong>two</strong> adjacent signs at indices <code>i</code> and <code>i + 1</code> (with <code>i &gt; 0</code> and <code>i + 1 &lt; n</code>) and:</p>

<ul data-end="701" data-start="595">
	<li data-end="624" data-start="595">Update the sign at index <code>i + 1</code> so that its time becomes <code>time[i] + time[i + 1]</code>.</li>
	<li data-end="624" data-start="595">Remove the sign at index <code>i</code>.</li>
</ul>

<p data-end="846" data-start="703">Return the <strong>minimum</strong> <strong>total</strong> <strong>travel time</strong> (in minutes) to travel from 0 to <code>l</code> after <strong>exactly</strong> <code>k</code> merges.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">l = 10, n = 4, k = 1, position = [0,3,8,10], time = [5,8,3,6]</span></p>

<p><strong>Output:</strong> <span class="example-io">62</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li data-end="121" data-start="11">
	<p data-end="121" data-start="13">Merge the signs at indices 1 and 2. Remove the sign at index 1, and change the time at index 2 to <code>8 + 3 = 11</code>.</p>
	</li>
	<li data-end="144" data-start="15">After the merge:
	<ul>
		<li data-end="214" data-start="145"><code>position</code> array: <code>[0, 8, 10]</code></li>
		<li data-end="214" data-start="145"><code>time</code> array: <code>[5, 11, 6]</code></li>
		<li data-end="214" data-start="145" style="opacity: 0"> </li>
	</ul>
	</li>
	<li data-end="214" data-start="145">
	<table data-end="386" data-start="231" style="border: 1px solid black;">
		<thead data-end="269" data-start="231">
			<tr data-end="269" data-start="231">
				<th data-end="241" data-start="231" style="border: 1px solid black;">Segment</th>
				<th data-end="252" data-start="241" style="border: 1px solid black;">Distance (km)</th>
				<th data-end="260" data-start="252" style="border: 1px solid black;">Time per km (min)</th>
				<th data-end="269" data-start="260" style="border: 1px solid black;">Segment Travel Time (min)</th>
			</tr>
		</thead>
		<tbody data-end="386" data-start="309">
			<tr data-end="347" data-start="309">
				<td style="border: 1px solid black;">0 &rarr; 8</td>
				<td style="border: 1px solid black;">8</td>
				<td style="border: 1px solid black;">5</td>
				<td style="border: 1px solid black;">8 &times; 5 = 40</td>
			</tr>
			<tr data-end="386" data-start="348">
				<td style="border: 1px solid black;">8 &rarr; 10</td>
				<td style="border: 1px solid black;">2</td>
				<td style="border: 1px solid black;">11</td>
				<td style="border: 1px solid black;">2 &times; 11 = 22</td>
			</tr>
		</tbody>
	</table>
	</li>
	<li data-end="214" data-start="145">Total Travel Time: <code>40 + 22 = 62</code>, which is the minimum possible time after exactly 1 merge.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">l = 5, n = 5, k = 1, position = [0,1,2,3,5], time = [8,3,9,3,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">34</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li data-end="567" data-start="438">Merge the signs at indices 1 and 2. Remove the sign at index 1, and change the time at index 2 to <code>3 + 9 = 12</code>.</li>
	<li data-end="755" data-start="568">After the merge:
	<ul>
		<li data-end="755" data-start="568"><code>position</code> array: <code>[0, 2, 3, 5]</code></li>
		<li data-end="755" data-start="568"><code>time</code> array: <code>[8, 12, 3, 3]</code></li>
		<li data-end="755" data-start="568" style="opacity: 0"> </li>
	</ul>
	</li>
	<li data-end="755" data-start="568">
	<table data-end="966" data-start="772" style="border: 1px solid black;">
		<thead data-end="810" data-start="772">
			<tr data-end="810" data-start="772">
				<th data-end="782" data-start="772" style="border: 1px solid black;">Segment</th>
				<th data-end="793" data-start="782" style="border: 1px solid black;">Distance (km)</th>
				<th data-end="801" data-start="793" style="border: 1px solid black;">Time per km (min)</th>
				<th data-end="810" data-start="801" style="border: 1px solid black;">Segment Travel Time (min)</th>
			</tr>
		</thead>
		<tbody data-end="966" data-start="850">
			<tr data-end="888" data-start="850">
				<td style="border: 1px solid black;">0 &rarr; 2</td>
				<td style="border: 1px solid black;">2</td>
				<td style="border: 1px solid black;">8</td>
				<td style="border: 1px solid black;">2 &times; 8 = 16</td>
			</tr>
			<tr data-end="927" data-start="889">
				<td style="border: 1px solid black;">2 &rarr; 3</td>
				<td style="border: 1px solid black;">1</td>
				<td style="border: 1px solid black;">12</td>
				<td style="border: 1px solid black;">1 &times; 12 = 12</td>
			</tr>
			<tr data-end="966" data-start="928">
				<td style="border: 1px solid black;">3 &rarr; 5</td>
				<td style="border: 1px solid black;">2</td>
				<td style="border: 1px solid black;">3</td>
				<td style="border: 1px solid black;">2 &times; 3 = 6</td>
			</tr>
		</tbody>
	</table>
	</li>
	<li data-end="755" data-start="568">Total Travel Time: <code>16 + 12 + 6 = 34</code><b>, </b>which is the minimum possible time after exactly 1 merge.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li data-end="35" data-start="15"><code>1 &lt;= l &lt;= 10<sup>5</sup></code></li>
	<li data-end="52" data-start="36"><code>2 &lt;= n &lt;= min(l + 1, 50)</code></li>
	<li data-end="81" data-start="53"><code>0 &lt;= k &lt;= min(n - 2, 10)</code></li>
	<li data-end="81" data-start="53"><code>position.length == n</code></li>
	<li data-end="81" data-start="53"><code>position[0] = 0</code> and <code>position[n - 1] = l</code></li>
	<li data-end="200" data-start="80"><code>position</code> is sorted in strictly increasing order.</li>
	<li data-end="81" data-start="53"><code>time.length == n</code></li>
	<li data-end="81" data-start="53"><code>1 &lt;= time[i] &lt;= 100​</code></li>
	<li data-end="81" data-start="53"><code>1 &lt;= sum(time) &lt;= 100</code>​​​​​​</li>
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
