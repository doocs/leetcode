---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3534.Path%20Existence%20Queries%20in%20a%20Graph%20II/README_EN.md
rating: 2507
source: Weekly Contest 447 Q4
tags:
    - Greedy
    - Bit Manipulation
    - Graph
    - Array
    - Two Pointers
    - Binary Search
    - Dynamic Programming
    - Sorting
---

<!-- problem:start -->

# [3534. Path Existence Queries in a Graph II](https://leetcode.com/problems/path-existence-queries-in-a-graph-ii)

[中文文档](/solution/3500-3599/3534.Path%20Existence%20Queries%20in%20a%20Graph%20II/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code> representing the number of nodes in a graph, labeled from 0 to <code>n - 1</code>.</p>

<p>You are also given an integer array <code>nums</code> of length <code>n</code> and an integer <code>maxDiff</code>.</p>

<p>An <strong>undirected </strong>edge exists between nodes <code>i</code> and <code>j</code> if the <strong>absolute</strong> difference between <code>nums[i]</code> and <code>nums[j]</code> is <strong>at most</strong> <code>maxDiff</code> (i.e., <code>|nums[i] - nums[j]| &lt;= maxDiff</code>).</p>

<p>You are also given a 2D integer array <code>queries</code>. For each <code>queries[i] = [u<sub>i</sub>, v<sub>i</sub>]</code>, find the <strong>minimum</strong> distance between nodes <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code><sub>.</sub> If no path exists between the two nodes, return -1 for that query.</p>

<p>Return an array <code>answer</code>, where <code>answer[i]</code> is the result of the <code>i<sup>th</sup></code> query.</p>

<p><strong>Note:</strong> The edges between the nodes are unweighted.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5, nums = [1,8,3,4,2], maxDiff = 3, queries = [[0,3],[2,4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,1]</span></p>

<p><strong>Explanation:</strong></p>

<p>The resulting graph is:</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3534.Path%20Existence%20Queries%20in%20a%20Graph%20II/images/4149example1drawio.png" style="width: 281px; height: 161px;" /></p>

<table>
	<tbody>
		<tr>
			<th>Query</th>
			<th>Shortest Path</th>
			<th>Minimum Distance</th>
		</tr>
		<tr>
			<td>[0, 3]</td>
			<td>0 &rarr; 3</td>
			<td>1</td>
		</tr>
		<tr>
			<td>[2, 4]</td>
			<td>2 &rarr; 4</td>
			<td>1</td>
		</tr>
	</tbody>
</table>

<p>Thus, the output is <code>[1, 1]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5, nums = [5,3,1,9,10], maxDiff = 2, queries = [[0,1],[0,2],[2,3],[4,3]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,2,-1,1]</span></p>

<p><strong>Explanation:</strong></p>

<p>The resulting graph is:</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3534.Path%20Existence%20Queries%20in%20a%20Graph%20II/images/4149example2drawio.png" style="width: 281px; height: 121px;" /></p>
</div>

<table>
	<tbody>
		<tr>
			<th>Query</th>
			<th>Shortest Path</th>
			<th>Minimum Distance</th>
		</tr>
		<tr>
			<td>[0, 1]</td>
			<td>0 &rarr; 1</td>
			<td>1</td>
		</tr>
		<tr>
			<td>[0, 2]</td>
			<td>0 &rarr; 1 &rarr; 2</td>
			<td>2</td>
		</tr>
		<tr>
			<td>[2, 3]</td>
			<td>None</td>
			<td>-1</td>
		</tr>
		<tr>
			<td>[4, 3]</td>
			<td>3 &rarr; 4</td>
			<td>1</td>
		</tr>
	</tbody>
</table>

<p>Thus, the output is <code>[1, 2, -1, 1]</code>.</p>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, nums = [3,6,1], maxDiff = 1, queries = [[0,0],[0,1],[1,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[0,-1,-1]</span></p>

<p><strong>Explanation:</strong></p>

<p>There are no edges between any two nodes because:</p>

<ul>
	<li>Nodes 0 and 1: <code>|nums[0] - nums[1]| = |3 - 6| = 3 &gt; 1</code></li>
	<li>Nodes 0 and 2: <code>|nums[0] - nums[2]| = |3 - 1| = 2 &gt; 1</code></li>
	<li>Nodes 1 and 2: <code>|nums[1] - nums[2]| = |6 - 1| = 5 &gt; 1</code></li>
</ul>

<p>Thus, no node can reach any other node, and the output is <code>[0, -1, -1]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= maxDiff &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i] == [u<sub>i</sub>, v<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt; n</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting + Binary Lifting

Key observation: an edge exists between two nodes if the absolute difference of their values is at most `maxDiff`. After sorting nodes by value, greedily jumping from a smaller-value node to the largest reachable value at each step yields the shortest path.

The preprocessing steps are as follows:

1. Sort `(nums[i], i)` pairs by value;
2. Use two pointers: for each sorted position `l`, find the rightmost position `r` such that `pairs[r].first - pairs[l].first <= maxDiff`. Set `f[i][0] = j`, meaning from node `i`, one jump reaches node `j`, the largest-value node within `maxDiff`;
3. Build a binary lifting table `f[i][k]`, representing the node reached after $2^k$ jumps from node `i`.

For each query, assume `nums[u] <= nums[v]`:

- If `u == v`, the answer is $0$;
- If `nums[u] == nums[v]`, the answer is $1$;
- Otherwise, use binary lifting to find the minimum number of jumps so that the reached node's value is at least `nums[v]`; if unreachable, return $-1$, otherwise the answer is $d + 1$.

The time complexity is $O(n \log n + (n + q) \log n)$ and the space complexity is $O(n \log n)$, where $n$ is the number of nodes and $q$ is the number of queries.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def pathExistenceQueries(
        self, n: int, nums: List[int], maxDiff: int, queries: List[List[int]]
    ) -> List[int]:
        pairs = sorted((x, i) for i, x in enumerate(nums))
        m = 20
        f = [[0] * m for _ in range(n)]
        r = n - 1
        for l in range(n - 1, -1, -1):
            while pairs[r][0] - pairs[l][0] > maxDiff:
                r -= 1
            i, j = pairs[l][1], pairs[r][1]
            f[i][0] = j
            for k in range(1, m):
                f[i][k] = f[f[i][k - 1]][k - 1]

        ans = []
        for i, j in queries:
            if nums[i] > nums[j]:
                i, j = j, i
            if i == j:
                ans.append(0)
                continue
            if nums[i] == nums[j]:
                ans.append(1)
                continue
            d = 0
            for k in range(m - 1, -1, -1):
                if nums[f[i][k]] < nums[j]:
                    d |= 1 << k
                    i = f[i][k]
            if nums[f[i][0]] < nums[j]:
                ans.append(-1)
            else:
                ans.append(d + 1)
        return ans
```

#### Java

```java
class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i][0] = nums[i];
            pairs[i][1] = i;
        }
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);

        int m = 20;
        int[][] f = new int[n][m];
        int r = n - 1;
        for (int l = n - 1; l >= 0; l--) {
            while (pairs[r][0] - pairs[l][0] > maxDiff) {
                r--;
            }
            int i = pairs[l][1], j = pairs[r][1];
            f[i][0] = j;
            for (int k = 1; k < m; k++) {
                f[i][k] = f[f[i][k - 1]][k - 1];
            }
        }

        int[] ans = new int[queries.length];
        for (int t = 0; t < queries.length; t++) {
            int i = queries[t][0], j = queries[t][1];
            if (nums[i] > nums[j]) {
                int tmp = i;
                i = j;
                j = tmp;
            }
            if (i == j) {
                ans[t] = 0;
                continue;
            }
            if (nums[i] == nums[j]) {
                ans[t] = 1;
                continue;
            }
            int d = 0;
            for (int k = m - 1; k >= 0; k--) {
                if (nums[f[i][k]] < nums[j]) {
                    d |= 1 << k;
                    i = f[i][k];
                }
            }
            if (nums[f[i][0]] < nums[j]) {
                ans[t] = -1;
            } else {
                ans[t] = d + 1;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> pathExistenceQueries(int n, vector<int>& nums, int maxDiff, vector<vector<int>>& queries) {
        vector<pair<int, int>> pairs;
        for (int i = 0; i < n; i++) {
            pairs.emplace_back(nums[i], i);
        }
        sort(pairs.begin(), pairs.end());

        int m = 20;
        vector<vector<int>> f(n, vector<int>(m));
        int r = n - 1;
        for (int l = n - 1; l >= 0; l--) {
            while (pairs[r].first - pairs[l].first > maxDiff) {
                r--;
            }
            int i = pairs[l].second, j = pairs[r].second;
            f[i][0] = j;
            for (int k = 1; k < m; k++) {
                f[i][k] = f[f[i][k - 1]][k - 1];
            }
        }

        vector<int> ans;
        for (auto& q : queries) {
            int i = q[0], j = q[1];
            if (nums[i] > nums[j]) {
                swap(i, j);
            }
            if (i == j) {
                ans.push_back(0);
                continue;
            }
            if (nums[i] == nums[j]) {
                ans.push_back(1);
                continue;
            }
            int d = 0;
            for (int k = m - 1; k >= 0; k--) {
                if (nums[f[i][k]] < nums[j]) {
                    d |= 1 << k;
                    i = f[i][k];
                }
            }
            if (nums[f[i][0]] < nums[j]) {
                ans.push_back(-1);
            } else {
                ans.push_back(d + 1);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func pathExistenceQueries(n int, nums []int, maxDiff int, queries [][]int) []int {
	pairs := make([][2]int, n)
	for i, x := range nums {
		pairs[i] = [2]int{x, i}
	}
	sort.Slice(pairs, func(i, j int) bool {
		return pairs[i][0] < pairs[j][0]
	})

	m := 20
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, m)
	}

	r := n - 1
	for l := n - 1; l >= 0; l-- {
		for pairs[r][0]-pairs[l][0] > maxDiff {
			r--
		}
		i, j := pairs[l][1], pairs[r][1]
		f[i][0] = j
		for k := 1; k < m; k++ {
			f[i][k] = f[f[i][k-1]][k-1]
		}
	}

	ans := make([]int, 0, len(queries))
	for _, q := range queries {
		i, j := q[0], q[1]
		if nums[i] > nums[j] {
			i, j = j, i
		}
		if i == j {
			ans = append(ans, 0)
			continue
		}
		if nums[i] == nums[j] {
			ans = append(ans, 1)
			continue
		}
		d := 0
		for k := m - 1; k >= 0; k-- {
			if nums[f[i][k]] < nums[j] {
				d |= 1 << k
				i = f[i][k]
			}
		}
		if nums[f[i][0]] < nums[j] {
			ans = append(ans, -1)
		} else {
			ans = append(ans, d+1)
		}
	}
	return ans
}
```

#### TypeScript

```ts
function pathExistenceQueries(
    n: number,
    nums: number[],
    maxDiff: number,
    queries: number[][],
): number[] {
    const pairs: number[][] = [];
    for (let i = 0; i < n; i++) {
        pairs.push([nums[i], i]);
    }
    pairs.sort((a, b) => a[0] - b[0]);

    const m = 20;
    const f = Array.from({ length: n }, () => Array(m).fill(0));

    let r = n - 1;
    for (let l = n - 1; l >= 0; l--) {
        while (pairs[r][0] - pairs[l][0] > maxDiff) {
            r--;
        }
        let i = pairs[l][1],
            j = pairs[r][1];
        f[i][0] = j;
        for (let k = 1; k < m; k++) {
            f[i][k] = f[f[i][k - 1]][k - 1];
        }
    }

    const ans: number[] = [];
    for (const q of queries) {
        let i = q[0],
            j = q[1];
        if (nums[i] > nums[j]) {
            [i, j] = [j, i];
        }
        if (i === j) {
            ans.push(0);
            continue;
        }
        if (nums[i] === nums[j]) {
            ans.push(1);
            continue;
        }
        let d = 0;
        for (let k = m - 1; k >= 0; k--) {
            if (nums[f[i][k]] < nums[j]) {
                d |= 1 << k;
                i = f[i][k];
            }
        }
        if (nums[f[i][0]] < nums[j]) {
            ans.push(-1);
        } else {
            ans.push(d + 1);
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn path_existence_queries(
        n: i32,
        nums: Vec<i32>,
        max_diff: i32,
        queries: Vec<Vec<i32>>,
    ) -> Vec<i32> {
        let n = n as usize;
        let mut pairs = Vec::with_capacity(n);
        for (i, &x) in nums.iter().enumerate() {
            pairs.push((x, i));
        }
        pairs.sort_unstable();

        let m = 20;
        let mut f = vec![vec![0; m]; n];

        let mut r = n - 1;
        for l in (0..n).rev() {
            while pairs[r].0 - pairs[l].0 > max_diff {
                r -= 1;
            }
            let (i, j) = (pairs[l].1, pairs[r].1);
            f[i][0] = j;
            for k in 1..m {
                f[i][k] = f[f[i][k - 1]][k - 1];
            }
        }

        let mut ans = Vec::with_capacity(queries.len());
        for q in queries {
            let (mut i, mut j) = (q[0] as usize, q[1] as usize);
            if nums[i] > nums[j] {
                std::mem::swap(&mut i, &mut j);
            }
            if i == j {
                ans.push(0);
                continue;
            }
            if nums[i] == nums[j] {
                ans.push(1);
                continue;
            }
            let mut d = 0;
            for k in (0..m).rev() {
                if nums[f[i][k]] < nums[j] {
                    d |= 1 << k;
                    i = f[i][k];
                }
            }
            if nums[f[i][0]] < nums[j] {
                ans.push(-1);
            } else {
                ans.push(d + 1);
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
