---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4007.Widest%20Possible%20Fence/README_EN.md
---

<!-- problem:start -->

# [4007. Widest Possible Fence](https://leetcode.com/problems/widest-possible-fence)

[中文文档](/solution/4000-4099/4007.Widest%20Possible%20Fence/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>planks</code>, where <code>planks[i]</code> represents the height of the <code>i<sup>th</sup></code> wooden plank. Each plank has a width of 1 unit.</p>

<p>You want to build a fence consisting of planks that all have the <strong>same</strong> height.</p>

<p>You may either use a plank as is, or combine <strong>exactly</strong> two distinct original planks into a single plank whose height <strong>equals</strong> the sum of their heights. Each original plank can be used <strong>at most</strong> once, and not all original planks need to be used.</p>

<p>Return the <strong>maximum possible width</strong> of the fence that can be built.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">planks = [1,3,2,5,7,5,4,2,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>We can have four planks of height 5.</p>

<ul>
	<li><code>planks[3] = 5</code></li>
	<li><code>planks[5] = 5</code></li>
	<li><code>planks[0] + planks[6] = 1 + 4 = 5</code></li>
	<li><code>planks[1] + planks[2] = 3 + 2 = 5</code></li>
</ul>

<p>Hence, the maximum width is 4.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">planks = [2,3,7]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>It is impossible to form two planks of the same height, even after combining two distinct original planks.</li>
	<li>Since not all original planks need to be used, we can choose any one plank as the fence.</li>
	<li>Therefore, the maximum possible width is 1.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= planks.length &lt;= 1000</code></li>
	<li><code>1 &lt;= planks[i] &lt;= 10<sup>9</sup></code></li>
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
