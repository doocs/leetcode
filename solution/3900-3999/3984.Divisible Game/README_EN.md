---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3984.Divisible%20Game/README_EN.md
rating: 1944
source: Weekly Contest 509 Q3
---

<!-- problem:start -->

# [3984. Divisible Game](https://leetcode.com/problems/divisible-game)

[中文文档](/solution/3900-3999/3984.Divisible%20Game/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code>.</p>

<p>Alice and Bob are playing a game. Alice chooses:</p>

<ul>
	<li>An integer <code>k</code> such that <code>k &gt; 1</code>.</li>
	<li>Two integers <code>l</code> and <code>r</code> such that <code>0 &lt;= l &lt;= r &lt; n</code>.</li>
</ul>

<p>Initially, both Alice&#39;s and Bob&#39;s scores are 0.</p>

<p>For each index <code>i</code> in the range <code>[l, r]</code> (inclusive):</p>

<ul>
	<li>If <code>nums[i]</code> is divisible by <code>k</code>, Alice&#39;s score <strong>increases</strong> by <code>nums[i]</code>.</li>
	<li>Otherwise, Bob&#39;s score <strong>increases</strong> by <code>nums[i]</code>.</li>
</ul>

<p>The <strong>score difference</strong> is Alice&#39;s score <strong>minus</strong> Bob&#39;s score.</p>

<p>Alice wants to <strong>maximize</strong> the score difference. If there are multiple values of <code>k</code> that achieve the <strong>maximum</strong> score difference, she chooses the <strong>smallest</strong> such <code>k</code>.</p>

<p>Return the <strong>product</strong> of the <strong>maximum</strong> score difference and the chosen value of <code>k</code>. Since the result can be large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,4,6,8]</span></p>

<p><strong>Output:</strong> <span class="example-io">36</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Alice can choose <code>k = 2</code>, <code>l = 1</code>, and <code>r = 3</code>.</li>
	<li>All values in <code>nums[1..3]</code> are divisible by 2, so Alice&#39;s score is <code>4 + 6 + 8 = 18</code>, while Bob&#39;s score is 0.</li>
	<li>The score difference is 18, which is the maximum possible. Among all values of <code>k</code> that achieve this score difference, the smallest is 2.</li>
	<li>Therefore, the answer is <code>18 * 2 = 36</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,1,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Alice can choose <code>k = 2</code>, <code>l = 0</code>, and <code>r = 2</code>.</li>
	<li>The values <code>nums[0]</code> and <code>nums[2]</code> are divisible by 2, so Alice&#39;s score is <code>2 + 2 = 4</code>. The value <code>nums[1]</code> is not divisible by 2, so Bob&#39;s score is 1.</li>
	<li>The score difference is <code>4 - 1 = 3</code>, which is the maximum possible. Among all values of <code>k</code> that achieve this score difference, the smallest is 2.</li>
	<li>Therefore, the answer is <code>3 * 2 = 6</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1]</span></p>

<p><strong>Output:</strong> <span class="example-io">1000000005</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Alice must choose some <code>k &gt; 1</code>. The smallest possible choice is <code>k = 2</code>.</li>
	<li>Since <code>nums[0]</code> is not divisible by 2, Alice&#39;s score is 0, while Bob&#39;s score is 1.</li>
	<li>The score difference is -1, which is the maximum possible.</li>
	<li>Therefore, the answer is <code>-1 * 2 = -2</code>. Modulo <code>10<sup>9</sup> + 7</code>, this equals 1000000005.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
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
