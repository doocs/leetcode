---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3277.Maximum%20XOR%20Score%20Subarray%20Queries/README_EN.md
rating: 2692
source: Weekly Contest 413 Q4
tags:
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [3277. Maximum XOR Score Subarray Queries](https://leetcode.com/problems/maximum-xor-score-subarray-queries)

[中文文档](/solution/3200-3299/3277.Maximum%20XOR%20Score%20Subarray%20Queries/README.md)

## Description

<!-- description:start -->

<p>You are given an array <code>nums</code> of <code>n</code> integers, and a 2D integer array <code>queries</code> of size <code>q</code>, where <code>queries[i] = [l<sub>i</sub>, r<sub>i</sub>]</code>.</p>

<p>For each query, you must find the <strong>maximum XOR score</strong> of any <span data-keyword="subarray">subarray</span> of <code>nums[l<sub>i</sub>..r<sub>i</sub>]</code>.</p>

<p>The <strong>XOR score</strong> of an array <code>a</code> is found by repeatedly applying the following operations on <code>a</code> so that only one element remains, that is the <strong>score</strong>:</p>

<ul>
	<li>Simultaneously replace <code>a[i]</code> with <code>a[i] XOR a[i + 1]</code> for all indices <code>i</code> except the last one.</li>
	<li>Remove the last element of <code>a</code>.</li>
</ul>

<p>Return an array <code>answer</code> of size <code>q</code> where <code>answer[i]</code> is the answer to query <code>i</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,8,4,32,16,1], queries = [[0,2],[1,4],[0,5]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[12,60,60]</span></p>

<p><strong>Explanation:</strong></p>

<p>In the first query, <code>nums[0..2]</code> has 6 subarrays <code>[2]</code>, <code>[8]</code>, <code>[4]</code>, <code>[2, 8]</code>, <code>[8, 4]</code>, and <code>[2, 8, 4]</code> each with a respective XOR score of 2, 8, 4, 10, 12, and 6. The answer for the query is 12, the largest of all XOR scores.</p>

<p>In the second query, the subarray of <code>nums[1..4]</code> with the largest XOR score is <code>nums[1..4]</code> with a score of 60.</p>

<p>In the third query, the subarray of <code>nums[0..5]</code> with the largest XOR score is <code>nums[1..4]</code> with a score of 60.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [0,7,3,2,8,5,1], queries = [[0,3],[1,5],[2,4],[2,6],[5,6]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[7,14,11,14,5]</span></p>

<p><strong>Explanation:</strong></p>

<table height="70" width="472">
	<thead>
		<tr>
			<th>Index</th>
			<th>nums[l<sub>i</sub>..r<sub>i</sub>]</th>
			<th>Maximum XOR Score Subarray</th>
			<th>Maximum Subarray XOR Score</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>0</td>
			<td>[0, 7, 3, 2]</td>
			<td>[7]</td>
			<td>7</td>
		</tr>
		<tr>
			<td>1</td>
			<td>[7, 3, 2, 8, 5]</td>
			<td>[7, 3, 2, 8]</td>
			<td>14</td>
		</tr>
		<tr>
			<td>2</td>
			<td>[3, 2, 8]</td>
			<td>[3, 2, 8]</td>
			<td>11</td>
		</tr>
		<tr>
			<td>3</td>
			<td>[3, 2, 8, 5, 1]</td>
			<td>[2, 8, 5, 1]</td>
			<td>14</td>
		</tr>
		<tr>
			<td>4</td>
			<td>[5, 1]</td>
			<td>[5]</td>
			<td>5</td>
		</tr>
	</tbody>
</table>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 2000</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
	<li><code>1 &lt;= q == queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i].length == 2 </code></li>
	<li><code>queries[i] = [l<sub>i</sub>, r<sub>i</sub>]</code></li>
	<li><code>0 &lt;= l<sub>i</sub> &lt;= r<sub>i</sub> &lt;= n - 1</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

We define $f[i][j]$ to represent the XOR value of $\textit{nums}[i..j]$. According to the problem description, we can derive the state transition equation:

$$
f[i][j] = f[i][j-1] \oplus f[i+1][j]
$$

where $\oplus$ denotes the XOR operation.

We further define $g[i][j]$ to represent the maximum value of $f[i][j]$. The state transition equation is:

$$
g[i][j] = \max(f[i][j], g[i][j-1], g[i+1][j])
$$

Finally, we traverse the query array. For each query $[l, r]$, we add $g[l][r]$ to the answer array.

The time complexity is $O(n^2 + m)$, and the space complexity is $O(n^2)$. Here, $n$ and $m$ are the lengths of the arrays $\textit{nums}$ and $\textit{queries}$, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumSubarrayXor(
        self, nums: List[int], queries: List[List[int]]
    ) -> List[int]:
        n = len(nums)
        f = [[0] * n for _ in range(n)]
        g = [[0] * n for _ in range(n)]
        for i in range(n - 1, -1, -1):
            f[i][i] = g[i][i] = nums[i]
            for j in range(i + 1, n):
                f[i][j] = f[i][j - 1] ^ f[i + 1][j]
                g[i][j] = max(f[i][j], g[i][j - 1], g[i + 1][j])
        return [g[l][r] for l, r in queries]
```

#### Java

```java
class Solution {
    public int[] maximumSubarrayXor(int[] nums, int[][] queries) {
        int n = nums.length;
        int[][] f = new int[n][n];
        int[][] g = new int[n][n];
        for (int i = n - 1; i >= 0; --i) {
            f[i][i] = nums[i];
            g[i][i] = nums[i];
            for (int j = i + 1; j < n; ++j) {
                f[i][j] = f[i][j - 1] ^ f[i + 1][j];
                g[i][j] = Math.max(f[i][j], Math.max(g[i][j - 1], g[i + 1][j]));
            }
        }
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; ++i) {
            int l = queries[i][0], r = queries[i][1];
            ans[i] = g[l][r];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> maximumSubarrayXor(vector<int>& nums, vector<vector<int>>& queries) {
        int n = nums.size();
        vector<vector<int>> f(n, vector<int>(n));
        vector<vector<int>> g(n, vector<int>(n));
        for (int i = n - 1; i >= 0; --i) {
            f[i][i] = nums[i];
            g[i][i] = nums[i];
            for (int j = i + 1; j < n; ++j) {
                f[i][j] = f[i][j - 1] ^ f[i + 1][j];
                g[i][j] = max({f[i][j], g[i][j - 1], g[i + 1][j]});
            }
        }
        vector<int> ans;
        for (const auto& q : queries) {
            int l = q[0], r = q[1];
            ans.push_back(g[l][r]);
        }
        return ans;
    }
};
```

#### Go

```go
func maximumSubarrayXor(nums []int, queries [][]int) (ans []int) {
	n := len(nums)
	f := make([][]int, n)
	g := make([][]int, n)
	for i := 0; i < n; i++ {
		f[i] = make([]int, n)
		g[i] = make([]int, n)
	}
	for i := n - 1; i >= 0; i-- {
		f[i][i] = nums[i]
		g[i][i] = nums[i]
		for j := i + 1; j < n; j++ {
			f[i][j] = f[i][j-1] ^ f[i+1][j]
			g[i][j] = max(f[i][j], max(g[i][j-1], g[i+1][j]))
		}
	}
	for _, q := range queries {
		l, r := q[0], q[1]
		ans = append(ans, g[l][r])
	}
	return
}
```

#### TypeScript

```ts
function maximumSubarrayXor(nums: number[], queries: number[][]): number[] {
    const n = nums.length;
    const f: number[][] = Array.from({ length: n }, () => Array(n).fill(0));
    const g: number[][] = Array.from({ length: n }, () => Array(n).fill(0));
    for (let i = n - 1; i >= 0; i--) {
        f[i][i] = nums[i];
        g[i][i] = nums[i];
        for (let j = i + 1; j < n; j++) {
            f[i][j] = f[i][j - 1] ^ f[i + 1][j];
            g[i][j] = Math.max(f[i][j], Math.max(g[i][j - 1], g[i + 1][j]));
        }
    }
    return queries.map(([l, r]) => g[l][r]);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
