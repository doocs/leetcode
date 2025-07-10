---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3606.Coupon%20Code%20Validator/README_EN.md
---

<!-- problem:start -->

# [3606. Coupon Code Validator](https://leetcode.com/problems/coupon-code-validator)

[中文文档](/solution/3600-3699/3606.Coupon%20Code%20Validator/README.md)

## Description

<!-- description:start -->

<p>You are given three arrays of length <code>n</code> that describe the properties of <code>n</code> coupons: <code>code</code>, <code>businessLine</code>, and <code>isActive</code>. The <code>i<sup>th</sup> </code>coupon has:</p>

<ul>
	<li><code>code[i]</code>: a <strong>string</strong> representing the coupon identifier.</li>
	<li><code>businessLine[i]</code>: a <strong>string</strong> denoting the business category of the coupon.</li>
	<li><code>isActive[i]</code>: a <strong>boolean</strong> indicating whether the coupon is currently active.</li>
</ul>

<p>A coupon is considered <strong>valid</strong> if all of the following conditions hold:</p>

<ol>
	<li><code>code[i]</code> is non-empty and consists only of alphanumeric characters (a-z, A-Z, 0-9) and underscores (<code>_</code>).</li>
	<li><code>businessLine[i]</code> is one of the following four categories: <code>&quot;electronics&quot;</code>, <code>&quot;grocery&quot;</code>, <code>&quot;pharmacy&quot;</code>, <code>&quot;restaurant&quot;</code>.</li>
	<li><code>isActive[i]</code> is <strong>true</strong>.</li>
</ol>

<p>Return an array of the <strong>codes</strong> of all valid coupons, <strong>sorted</strong> first by their <strong>businessLine</strong> in the order: <code>&quot;electronics&quot;</code>, <code>&quot;grocery&quot;</code>, <code>&quot;pharmacy&quot;, &quot;restaurant&quot;</code>, and then by <strong>code</strong> in lexicographical (ascending) order within each category.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">code = [&quot;SAVE20&quot;,&quot;&quot;,&quot;PHARMA5&quot;,&quot;SAVE@20&quot;], businessLine = [&quot;restaurant&quot;,&quot;grocery&quot;,&quot;pharmacy&quot;,&quot;restaurant&quot;], isActive = [true,true,true,true]</span></p>

<p><strong>Output:</strong> <span class="example-io">[&quot;PHARMA5&quot;,&quot;SAVE20&quot;]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>First coupon is valid.</li>
	<li>Second coupon has empty code (invalid).</li>
	<li>Third coupon is valid.</li>
	<li>Fourth coupon has special character <code>@</code> (invalid).</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">code = [&quot;GROCERY15&quot;,&quot;ELECTRONICS_50&quot;,&quot;DISCOUNT10&quot;], businessLine = [&quot;grocery&quot;,&quot;electronics&quot;,&quot;invalid&quot;], isActive = [false,true,true]</span></p>

<p><strong>Output:</strong> <span class="example-io">[&quot;ELECTRONICS_50&quot;]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>First coupon is inactive (invalid).</li>
	<li>Second coupon is valid.</li>
	<li>Third coupon has invalid business line (invalid).</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == code.length == businessLine.length == isActive.length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>0 &lt;= code[i].length, businessLine[i].length &lt;= 100</code></li>
	<li><code>code[i]</code> and <code>businessLine[i]</code> consist of printable ASCII characters.</li>
	<li><code>isActive[i]</code> is either <code>true</code> or <code>false</code>.</li>
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
