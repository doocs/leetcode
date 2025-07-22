---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3532.Path%20Existence%20Queries%20in%20a%20Graph%20I/README_EN.md
rating: 1658
source: Weekly Contest 447 Q2
tags:
    - Union Find
    - Graph
    - Array
    - Hash Table
    - Binary Search
---

<!-- problem:start -->

# [3532. Path Existence Queries in a Graph I](https://leetcode.com/problems/path-existence-queries-in-a-graph-i)

[中文文档](/solution/3500-3599/3532.Path%20Existence%20Queries%20in%20a%20Graph%20I/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code> representing the number of nodes in a graph, labeled from 0 to <code>n - 1</code>.</p>

<p>You are also given an integer array <code>nums</code> of length <code>n</code> sorted in <strong>non-decreasing</strong> order, and an integer <code>maxDiff</code>.</p>

<p>An <strong>undirected </strong>edge exists between nodes <code>i</code> and <code>j</code> if the <strong>absolute</strong> difference between <code>nums[i]</code> and <code>nums[j]</code> is <strong>at most</strong> <code>maxDiff</code> (i.e., <code>|nums[i] - nums[j]| &lt;= maxDiff</code>).</p>

<p>You are also given a 2D integer array <code>queries</code>. For each <code>queries[i] = [u<sub>i</sub>, v<sub>i</sub>]</code>, determine whether there exists a path between nodes <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code>.</p>

<p>Return a boolean array <code>answer</code>, where <code>answer[i]</code> is <code>true</code> if there exists a path between <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code> in the <code>i<sup>th</sup></code> query and <code>false</code> otherwise.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 2, nums = [1,3], maxDiff = 1, queries = [[0,0],[0,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[true,false]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Query <code>[0,0]</code>: Node 0 has a trivial path to itself.</li>
	<li>Query <code>[0,1]</code>: There is no edge between Node 0 and Node 1 because <code>|nums[0] - nums[1]| = |1 - 3| = 2</code>, which is greater than <code>maxDiff</code>.</li>
	<li>Thus, the final answer after processing all the queries is <code>[true, false]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, nums = [2,5,6,8], maxDiff = 2, queries = [[0,1],[0,2],[1,3],[2,3]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[false,false,true,true]</span></p>

<p><strong>Explanation:</strong></p>

<p>The resulting graph is:</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3500-3599/3532.Path%20Existence%20Queries%20in%20a%20Graph%20I/images/screenshot-2025-03-26-at-122249.png" style="width: 300px; height: 170px;" /></p>

<ul>
	<li>Query <code>[0,1]</code>: There is no edge between Node 0 and Node 1 because <code>|nums[0] - nums[1]| = |2 - 5| = 3</code>, which is greater than <code>maxDiff</code>.</li>
	<li>Query <code>[0,2]</code>: There is no edge between Node 0 and Node 2 because <code>|nums[0] - nums[2]| = |2 - 6| = 4</code>, which is greater than <code>maxDiff</code>.</li>
	<li>Query <code>[1,3]</code>: There is a path between Node 1 and Node 3 through Node 2 since <code>|nums[1] - nums[2]| = |5 - 6| = 1</code> and <code>|nums[2] - nums[3]| = |6 - 8| = 2</code>, both of which are within <code>maxDiff</code>.</li>
	<li>Query <code>[2,3]</code>: There is an edge between Node 2 and Node 3 because <code>|nums[2] - nums[3]| = |6 - 8| = 2</code>, which is equal to <code>maxDiff</code>.</li>
	<li>Thus, the final answer after processing all the queries is <code>[false, false, true, true]</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>nums</code> is sorted in <strong>non-decreasing</strong> order.</li>
	<li><code>0 &lt;= maxDiff &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i] == [u<sub>i</sub>, v<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt; n</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Grouping

According to the problem description, the node indices within the same connected component must be consecutive. Therefore, we can use an array $g$ to record the connected component index for each node and a variable $\textit{cnt}$ to track the current connected component index. As we iterate through the $\textit{nums}$ array, if the difference between the current node and the previous node is greater than $\textit{maxDiff}$, it indicates that the current node and the previous node are not in the same connected component. In this case, we increment $\textit{cnt}$. Then, we assign the current node's connected component index to $\textit{cnt}$.

Finally, for each query $(u, v)$, we only need to check whether $g[u]$ and $g[v]$ are equal. If they are equal, it means $u$ and $v$ are in the same connected component, and the answer for the $i$-th query is $\text{true}$. Otherwise, the answer is $\text{false}$.

The complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the $\textit{nums}$ array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def pathExistenceQueries(
        self, n: int, nums: List[int], maxDiff: int, queries: List[List[int]]
    ) -> List[bool]:
        g = [0] * n
        cnt = 0
        for i in range(1, n):
            if nums[i] - nums[i - 1] > maxDiff:
                cnt += 1
            g[i] = cnt
        return [g[u] == g[v] for u, v in queries]
```

#### Java

```java
class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[] g = new int[n];
        int cnt = 0;
        for (int i = 1; i < n; ++i) {
            if (nums[i] - nums[i - 1] > maxDiff) {
                cnt++;
            }
            g[i] = cnt;
        }

        int m = queries.length;
        boolean[] ans = new boolean[m];
        for (int i = 0; i < m; ++i) {
            int u = queries[i][0];
            int v = queries[i][1];
            ans[i] = g[u] == g[v];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<bool> pathExistenceQueries(int n, vector<int>& nums, int maxDiff, vector<vector<int>>& queries) {
        vector<int> g(n);
        int cnt = 0;
        for (int i = 1; i < n; ++i) {
            if (nums[i] - nums[i - 1] > maxDiff) {
                ++cnt;
            }
            g[i] = cnt;
        }

        vector<bool> ans;
        for (const auto& q : queries) {
            int u = q[0], v = q[1];
            ans.push_back(g[u] == g[v]);
        }
        return ans;
    }
};
```

#### Go

```go
func pathExistenceQueries(n int, nums []int, maxDiff int, queries [][]int) (ans []bool) {
	g := make([]int, n)
	cnt := 0
	for i := 1; i < n; i++ {
		if nums[i]-nums[i-1] > maxDiff {
			cnt++
		}
		g[i] = cnt
	}

	for _, q := range queries {
		u, v := q[0], q[1]
		ans = append(ans, g[u] == g[v])
	}
	return
}
```

#### TypeScript

```ts
function pathExistenceQueries(
    n: number,
    nums: number[],
    maxDiff: number,
    queries: number[][],
): boolean[] {
    const g: number[] = Array(n).fill(0);
    let cnt = 0;

    for (let i = 1; i < n; ++i) {
        if (nums[i] - nums[i - 1] > maxDiff) {
            ++cnt;
        }
        g[i] = cnt;
    }

    return queries.map(([u, v]) => g[u] === g[v]);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
