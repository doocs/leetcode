---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3355.Zero%20Array%20Transformation%20I/README_EN.md
rating: 1591
source: Weekly Contest 424 Q2
tags:
    - Array
    - Prefix Sum
---

<!-- problem:start -->

# [3355. Zero Array Transformation I](https://leetcode.com/problems/zero-array-transformation-i)

[中文文档](/solution/3300-3399/3355.Zero%20Array%20Transformation%20I/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code> and a 2D array <code>queries</code>, where <code>queries[i] = [l<sub>i</sub>, r<sub>i</sub>]</code>.</p>

<p>For each <code>queries[i]</code>:</p>

<ul>
	<li>Select a <span data-keyword="subset">subset</span> of indices within the range <code>[l<sub>i</sub>, r<sub>i</sub>]</code> in <code>nums</code>.</li>
	<li>Decrement the values at the selected indices by 1.</li>
</ul>

<p>A <strong>Zero Array</strong> is an array where all elements are equal to 0.</p>

<p>Return <code>true</code> if it is <em>possible</em> to transform <code>nums</code> into a <strong>Zero Array </strong>after processing all the queries sequentially, otherwise return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,0,1], queries = [[0,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><strong>For i = 0:</strong>

    <ul>
    	<li>Select the subset of indices as <code>[0, 2]</code> and decrement the values at these indices by 1.</li>
    	<li>The array will become <code>[0, 0, 0]</code>, which is a Zero Array.</li>
    </ul>
    </li>

</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,3,2,1], queries = [[1,3],[0,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><strong>For i = 0:</strong>

    <ul>
    	<li>Select the subset of indices as <code>[1, 2, 3]</code> and decrement the values at these indices by 1.</li>
    	<li>The array will become <code>[4, 2, 1, 0]</code>.</li>
    </ul>
    </li>
    <li><strong>For i = 1:</strong>
    <ul>
    	<li>Select the subset of indices as <code>[0, 1, 2]</code> and decrement the values at these indices by 1.</li>
    	<li>The array will become <code>[3, 1, 0, 0]</code>, which is not a Zero Array.</li>
    </ul>
    </li>

</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i].length == 2</code></li>
	<li><code>0 &lt;= l<sub>i</sub> &lt;= r<sub>i</sub> &lt; nums.length</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Difference Array

We can use a difference array to solve this problem.

Define an array $d$ of length $n + 1$, with all initial values set to $0$. For each query $[l, r]$, we add $1$ to $d[l]$ and subtract $1$ from $d[r + 1]$.

Then we traverse the array $d$ within the range $[0, n - 1]$, accumulating the prefix sum $s$. If $\textit{nums}[i] > s$, it means $\textit{nums}$ cannot be converted to a zero array, so we return $\textit{false}$.

After traversing, return $\textit{true}$.

The time complexity is $O(n + m)$, and the space complexity is $O(n)$. Here, $n$ and $m$ are the lengths of the array $\textit{nums}$ and the number of queries, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isZeroArray(self, nums: List[int], queries: List[List[int]]) -> bool:
        d = [0] * (len(nums) + 1)
        for l, r in queries:
            d[l] += 1
            d[r + 1] -= 1
        s = 0
        for x, y in zip(nums, d):
            s += y
            if x > s:
                return False
        return True
```

#### Java

```java
class Solution {
    public boolean isZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] d = new int[n + 1];
        for (var q : queries) {
            int l = q[0], r = q[1];
            ++d[l];
            --d[r + 1];
        }
        for (int i = 0, s = 0; i < n; ++i) {
            s += d[i];
            if (nums[i] > s) {
                return false;
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isZeroArray(vector<int>& nums, vector<vector<int>>& queries) {
        int n = nums.size();
        int d[n + 1];
        memset(d, 0, sizeof(d));
        for (const auto& q : queries) {
            int l = q[0], r = q[1];
            ++d[l];
            --d[r + 1];
        }
        for (int i = 0, s = 0; i < n; ++i) {
            s += d[i];
            if (nums[i] > s) {
                return false;
            }
        }
        return true;
    }
};
```

#### Go

```go
func isZeroArray(nums []int, queries [][]int) bool {
	d := make([]int, len(nums)+1)
	for _, q := range queries {
		l, r := q[0], q[1]
		d[l]++
		d[r+1]--
	}
	s := 0
	for i, x := range nums {
		s += d[i]
		if x > s {
			return false
		}
	}
	return true
}
```

#### TypeScript

```ts
function isZeroArray(nums: number[], queries: number[][]): boolean {
    const n = nums.length;
    const d: number[] = Array(n + 1).fill(0);
    for (const [l, r] of queries) {
        ++d[l];
        --d[r + 1];
    }
    for (let i = 0, s = 0; i < n; ++i) {
        s += d[i];
        if (nums[i] > s) {
            return false;
        }
    }
    return true;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
