---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3494.Find%20the%20Minimum%20Amount%20of%20Time%20to%20Brew%20Potions/README_EN.md
tags:
    - Array
    - Prefix Sum
    - Simulation
---

<!-- problem:start -->

# [3494. Find the Minimum Amount of Time to Brew Potions](https://leetcode.com/problems/find-the-minimum-amount-of-time-to-brew-potions)

[中文文档](/solution/3400-3499/3494.Find%20the%20Minimum%20Amount%20of%20Time%20to%20Brew%20Potions/README.md)

## Description

<!-- description:start -->

<p>You are given two integer arrays, <code>skill</code> and <code><font face="monospace">mana</font></code>, of length <code>n</code> and <code>m</code>, respectively.</p>

<p>In a laboratory, <code>n</code> wizards must brew <code>m</code> potions <em>in order</em>. Each potion has a mana capacity <code>mana[j]</code> and <strong>must</strong> pass through <strong>all</strong> the wizards sequentially to be brewed properly. The time taken by the <code>i<sup>th</sup></code> wizard on the <code>j<sup>th</sup></code> potion is <code>time<sub>ij</sub> = skill[i] * mana[j]</code>.</p>

<p>Since the brewing process is delicate, a potion <strong>must</strong> be passed to the next wizard immediately after the current wizard completes their work. This means the timing must be <em>synchronized</em> so that each wizard begins working on a potion <strong>exactly</strong> when it arrives. ​</p>

<p>Return the <strong>minimum</strong> amount of time required for the potions to be brewed properly.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">skill = [1,5,2,4], mana = [5,1,4,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">110</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">Potion Number</th>
			<th style="border: 1px solid black;">Start time</th>
			<th style="border: 1px solid black;">Wizard 0 done by</th>
			<th style="border: 1px solid black;">Wizard 1 done by</th>
			<th style="border: 1px solid black;">Wizard 2 done by</th>
			<th style="border: 1px solid black;">Wizard 3 done by</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;">30</td>
			<td style="border: 1px solid black;">40</td>
			<td style="border: 1px solid black;">60</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">52</td>
			<td style="border: 1px solid black;">53</td>
			<td style="border: 1px solid black;">58</td>
			<td style="border: 1px solid black;">60</td>
			<td style="border: 1px solid black;">64</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">54</td>
			<td style="border: 1px solid black;">58</td>
			<td style="border: 1px solid black;">78</td>
			<td style="border: 1px solid black;">86</td>
			<td style="border: 1px solid black;">102</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">86</td>
			<td style="border: 1px solid black;">88</td>
			<td style="border: 1px solid black;">98</td>
			<td style="border: 1px solid black;">102</td>
			<td style="border: 1px solid black;">110</td>
		</tr>
	</tbody>
</table>

<p>As an example for why wizard 0 cannot start working on the 1<sup>st</sup> potion before time <code>t = 52</code>, consider the case where the wizards started preparing the 1<sup>st</sup> potion at time <code>t = 50</code>. At time <code>t = 58</code>, wizard 2 is done with the 1<sup>st</sup> potion, but wizard 3 will still be working on the 0<sup>th</sup> potion till time <code>t = 60</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">skill = [1,1,1], mana = [1,1,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<ol>
	<li>Preparation of the 0<sup>th</sup> potion begins at time <code>t = 0</code>, and is completed by time <code>t = 3</code>.</li>
	<li>Preparation of the 1<sup>st</sup> potion begins at time <code>t = 1</code>, and is completed by time <code>t = 4</code>.</li>
	<li>Preparation of the 2<sup>nd</sup> potion begins at time <code>t = 2</code>, and is completed by time <code>t = 5</code>.</li>
</ol>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">skill = [1,2,3,4], mana = [1,2]</span></p>

<p><strong>Output:</strong> 21</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == skill.length</code></li>
	<li><code>m == mana.length</code></li>
	<li><code>1 &lt;= n, m &lt;= 5000</code></li>
	<li><code>1 &lt;= mana[i], skill[i] &lt;= 5000</code></li>
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
