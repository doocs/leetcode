---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3748.Count%20Stable%20Subarrays/README_EN.md
---

<!-- problem:start -->

# [3748. Count Stable Subarrays](https://leetcode.com/problems/count-stable-subarrays)

[中文文档](/solution/3700-3799/3748.Count%20Stable%20Subarrays/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>A <strong><span data-keyword="subarray-nonempty">subarray</span></strong> of <code>nums</code> is called <strong>stable</strong> if it contains <strong>no inversions</strong>, i.e., there is no pair of indices <code>i &lt; j</code> such that <code>nums[i] &gt; nums[j]</code>.</p>

<p>You are also given a <strong>2D integer array</strong> <code>queries</code> of length <code>q</code>, where each <code>queries[i] = [l<sub>i</sub>, r<sub>i</sub>]</code> represents a query. For each query <code>[l<sub>i</sub>, r<sub>i</sub>]</code>, compute the number of <strong>stable subarrays</strong> that lie entirely within the segment <code>nums[l<sub>i</sub>..r<sub>i</sub>]</code>.</p>

<p>Return an integer array <code>ans</code> of length <code>q</code>, where <code>ans[i]</code> is the answer to the <code>i<sup>th</sup></code> query.​​​​​​​​​​​​​​</p>

<p><strong>Note</strong>:</p>

<ul>
	<li>A single element subarray is considered stable.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,1,2], queries = [[0,1],[1,2],[0,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,3,4]</span></p>

<p><strong>Explanation:</strong>​​​​​</p>

<ul>
	<li>For <code>queries[0] = [0, 1]</code>, the subarray is <code>[nums[0], nums[1]] = [3, 1]</code>.

    <ul>
    	<li>The stable subarrays are <code>[3]</code> and <code>[1]</code>. The total number of stable subarrays is 2.</li>
    </ul>
    </li>
    <li>For <code>queries[1] = [1, 2]</code>, the subarray is <code>[nums[1], nums[2]] = [1, 2]</code>.
    <ul>
    	<li>The stable subarrays are <code>[1]</code>, <code>[2]</code>, and <code>[1, 2]</code>. The total number of stable subarrays is 3.</li>
    </ul>
    </li>
    <li>For <code>queries[2] = [0, 2]</code>, the subarray is <code>[nums[0], nums[1], nums[2]] = [3, 1, 2]</code>.
    <ul>
    	<li>The stable subarrays are <code>[3]</code>, <code>[1]</code>, <code>[2]</code>, and <code>[1, 2]</code>. The total number of stable subarrays is 4.</li>
    </ul>
    </li>

</ul>

<p>Thus, <code>ans = [2, 3, 4]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,2], queries = [[0,1],[0,0]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[3,1]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For <code>queries[0] = [0, 1]</code>, the subarray is <code>[nums[0], nums[1]] = [2, 2]</code>.

    <ul>
    	<li>The stable subarrays are <code>[2]</code>, <code>[2]</code>, and <code>[2, 2]</code>. The total number of stable subarrays is 3.</li>
    </ul>
    </li>
    <li>For <code>queries[1] = [0, 0]</code>, the subarray is <code>[nums[0]] = [2]</code>.
    <ul>
    	<li>The stable subarray is <code>[2]</code>. The total number of stable subarrays is 1.</li>
    </ul>
    </li>

</ul>

<p>Thus, <code>ans = [3, 1]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i] = [l<sub>i</sub>, r<sub>i</sub>]</code></li>
	<li><code>0 &lt;= l<sub>i</sub> &lt;= r<sub>i</sub> &lt;= nums.length - 1</code></li>
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
