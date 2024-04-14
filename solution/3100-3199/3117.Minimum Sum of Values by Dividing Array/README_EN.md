# [3117. Minimum Sum of Values by Dividing Array](https://leetcode.com/problems/minimum-sum-of-values-by-dividing-array)

[中文文档](/solution/3100-3199/3117.Minimum%20Sum%20of%20Values%20by%20Dividing%20Array/README.md)

<!-- tags: -->

## Description

<p>You are given two arrays <code>nums</code> and <code>andValues</code> of length <code>n</code> and <code>m</code> respectively.</p>

<p>The <strong>value</strong> of an array is equal to the <strong>last</strong> element of that array.</p>

<p>You have to divide <code>nums</code> into <code>m</code> <strong>disjoint contiguous</strong> <span data-keyword="subarray-nonempty">subarrays</span> such that for the <code>i<sup>th</sup></code> subarray <code>[l<sub>i</sub>, r<sub>i</sub>]</code>, the bitwise <code>AND</code> of the subarray elements is equal to <code>andValues[i]</code>, in other words, <code>nums[l<sub>i</sub>] &amp; nums[l<sub>i</sub> + 1] &amp; ... &amp; nums[r<sub>i</sub>] == andValues[i]</code> for all <code>1 &lt;= i &lt;= m</code>, where <code>&amp;</code> represents the bitwise <code>AND</code> operator.</p>

<p>Return <em>the <strong>minimum</strong> possible sum of the <strong>values</strong> of the </em><code>m</code><em> subarrays </em><code>nums</code><em> is divided into</em>. <em>If it is not possible to divide </em><code>nums</code><em> into </em><code>m</code><em> subarrays satisfying these conditions, return</em> <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,4,3,3,2], andValues = [0,3,3,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">12</span></p>

<p><strong>Explanation:</strong></p>

<p>The only possible way to divide <code>nums</code> is:</p>

<ol>
	<li><code>[1,4]</code> as <code>1 &amp; 4 == 0</code>.</li>
	<li><code>[3]</code> as the bitwise <code>AND</code> of a single element subarray is that element itself.</li>
	<li><code>[3]</code> as the bitwise <code>AND</code> of a single element subarray is that element itself.</li>
	<li><code>[2]</code> as the bitwise <code>AND</code> of a single element subarray is that element itself.</li>
</ol>

<p>The sum of the values for these subarrays is <code>4 + 3 + 3 + 2 = 12</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,3,5,7,7,7,5], andValues = [0,7,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">17</span></p>

<p><strong>Explanation:</strong></p>

<p>There are three ways to divide <code>nums</code>:</p>

<ol>
	<li><code>[[2,3,5],[7,7,7],[5]]</code> with the sum of the values <code>5 + 7 + 5 == 17</code>.</li>
	<li><code>[[2,3,5,7],[7,7],[5]]</code> with the sum of the values <code>7 + 7 + 5 == 19</code>.</li>
	<li><code>[[2,3,5,7,7],[7],[5]]</code> with the sum of the values <code>7 + 7 + 5 == 19</code>.</li>
</ol>

<p>The minimum possible sum of the values is <code>17</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4], andValues = [2]</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>The bitwise <code>AND</code> of the entire array <code>nums</code> is <code>0</code>. As there is no possible way to divide <code>nums</code> into a single subarray to have the bitwise <code>AND</code> of elements <code>2</code>, return <code>-1</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= m == andValues.length &lt;= min(n, 10)</code></li>
	<li><code>1 &lt;= nums[i] &lt; 10<sup>5</sup></code></li>
	<li><code>0 &lt;= andValues[j] &lt; 10<sup>5</sup></code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python

```

```java

```

```cpp

```

```go

```

<!-- tabs:end -->

<!-- end -->
