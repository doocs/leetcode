---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4015.Weighted%20Sum%20of%20a%20Tree/README_EN.md
rating: 1534
source: Weekly Contest 514 Q2
tags:
    - Tree
    - Depth-First Search
    - Array
---

<!-- problem:start -->

# [4015. Weighted Sum of a Tree](https://leetcode.com/problems/weighted-sum-of-a-tree)

[中文文档](/solution/4000-4099/4015.Weighted%20Sum%20of%20a%20Tree/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>parent</code> of length <code>n</code> representing a rooted tree with nodes labeled from 0 to <code>n - 1</code>.</p>

<p>The tree is <strong>rooted</strong> at node 0, so <code>parent[0] = -1</code>. For each node <code>i</code> where <code>1 &lt;= i &lt;= n - 1</code>, <code>parent[i]</code> denotes the parent of node <code>i</code>.</p>

<p>You are also given an integer array <code>nums</code> of length <code>n</code>, where <code>nums[i]</code> denotes the value of node <code>i</code>.</p>

<p>The weight of a node <code>i</code> at depth <code>d</code> is <code>nums[i] * (h - d + 1)</code>, where <code>h</code> is the height of the tree.</p>

<p>Return the <strong>sum</strong> of the weights of all nodes in the tree.</p>

<p>The <strong>depth</strong> of a node is the number of nodes on the path from the root to that node, inclusive, with the root having depth 1.</p>

<p>The <strong>height</strong> of the tree is the maximum depth among all nodes in the tree.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4000-4099/4015.Weighted%20Sum%20of%20a%20Tree/images/t1.png" style="width: 200px; height: 190px;" />​​​​​​​</p>

<p><strong>Input:</strong> <span class="example-io">parent = [-1,0,0,0,2,2], nums = [5,2,3,1,4,6]</span></p>

<p><strong>Output:</strong> <span class="example-io">37</span></p>

<p><strong>Explanation:</strong></p>

<p>The height of the tree is 3.</p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">Node</th>
			<th style="border: 1px solid black;"><code>nums[i]</code></th>
			<th style="border: 1px solid black;">Depth (<code>d</code>)</th>
			<th style="border: 1px solid black;">Weight</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;"><code>5 * (3 - 1 + 1) = 15</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;"><code>2 * (3 - 2 + 1) = 4</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;"><code>3 * (3 - 2 + 1) = 6</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;"><code>1 * (3 - 2 + 1) = 2</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;"><code>4 * (3 - 3 + 1) = 4</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;">6</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;"><code>6 * (3 - 3 + 1) = 6</code></td>
		</tr>
	</tbody>
</table>

<p>The sum of all node weights is <code>15 + 4 + 6 + 2 + 4 + 6 = 37</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4000-4099/4015.Weighted%20Sum%20of%20a%20Tree/images/t2.png" style="width: 250px; height: 56px;" />​​​​​​​​​​​​​​</p>

<p><strong>Input:</strong> <span class="example-io">parent = [-1,0,1,2], nums = [1,2,3,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">20</span></p>

<p><strong>Explanation:</strong></p>

<p>The height of the tree is 4.</p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">Node</th>
			<th style="border: 1px solid black;"><code>nums[i]</code></th>
			<th style="border: 1px solid black;">Depth (<code>d</code>)</th>
			<th style="border: 1px solid black;">Weight</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;"><code>1 * (4 - 1 + 1) = 4</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;"><code>2 * (4 - 2 + 1) = 6</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;"><code>3 * (4 - 3 + 1) = 6</code></td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;"><code>4 * (4 - 4 + 1) = 4</code></td>
		</tr>
	</tbody>
</table>

<p>The sum of all node weights is <code>4 + 6 + 6 + 4 = 20</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>n == parent.length == nums.length</code></li>
	<li><code>parent[0] == -1</code></li>
	<li><code>0 &lt;= parent[i] &lt;= n - 1</code> for all <code>i</code> in <code>[1, n - 1]</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
	<li>The input is generated such that the array <code>parent</code> represents a valid tree rooted at node 0.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: BFS

The weight of node $i$ is $\textit{nums}[i] \times (h - d_i + 1)$, where $d_i$ is the depth of node $i$ and $h$ is the height of the tree. Therefore, the sum of the weights of all nodes is:

$$\sum_{i=0}^{n-1} \textit{nums}[i] \times (h - d_i + 1) = h \times \sum_{i=0}^{n-1} \textit{nums}[i] + \sum_{i=0}^{n-1} \textit{nums}[i] \times (1 - d_i)$$

We can use BFS to traverse the tree level by level. During the traversal, we maintain the current level $d$ (the root is at level $1$) and accumulate $\textit{nums}[i] \times (1 - d)$ for each node. After the traversal finishes, $d$ equals the height $h$ of the tree, and adding $h \times \sum \textit{nums}[i]$ gives the answer.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the number of nodes.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def weightedSum(self, parent: list[int], nums: list[int]) -> int:
        n = len(nums)
        g = [[] for _ in range(n)]
        for i in range(1, n):
            g[parent[i]].append(i)
        ans = 0
        q = [0]
        d = 0
        while q:
            d += 1
            nq = []
            for i in q:
                ans += nums[i] * (1 - d)
                nq.extend(g[i])
            q = nq
        ans += d * sum(nums)
        return ans
```

#### Java

```java
class Solution {
    public long weightedSum(int[] parent, int[] nums) {
        int n = nums.length;

        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<>());

        for (int i = 1; i < n; i++) {
            g[parent[i]].add(i);
        }

        long ans = 0;

        List<Integer> q = new ArrayList<>();
        q.add(0);

        int d = 0;

        while (!q.isEmpty()) {
            d++;

            List<Integer> nq = new ArrayList<>();

            for (int i : q) {
                ans += (long) nums[i] * (1 - d);
                nq.addAll(g[i]);
            }

            q = nq;
        }

        long sum = 0;
        for (int x : nums) {
            sum += x;
        }

        ans += (long) d * sum;

        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long weightedSum(vector<int>& parent, vector<int>& nums) {
        int n = nums.size();

        vector<vector<int>> g(n);

        for (int i = 1; i < n; i++) {
            g[parent[i]].push_back(i);
        }

        long long ans = 0;

        vector<int> q = {0};

        int d = 0;

        while (!q.empty()) {
            d++;

            vector<int> nq;

            for (int i : q) {
                ans += 1LL * nums[i] * (1 - d);
                for (int son : g[i]) {
                    nq.push_back(son);
                }
            }

            q = move(nq);
        }

        long long sum = 0;
        for (int x : nums) {
            sum += x;
        }

        ans += 1LL * d * sum;

        return ans;
    }
};
```

#### Go

```go
func weightedSum(parent []int, nums []int) int64 {
	n := len(nums)

	g := make([][]int, n)

	for i := 1; i < n; i++ {
		g[parent[i]] = append(g[parent[i]], i)
	}

	var ans int64

	q := []int{0}

	d := 0

	for len(q) > 0 {
		d++

		nq := make([]int, 0)

		for _, i := range q {
			ans += int64(nums[i]) * int64(1-d)

			for _, son := range g[i] {
				nq = append(nq, son)
			}
		}

		q = nq
	}

	var sum int64
	for _, x := range nums {
		sum += int64(x)
	}

	ans += int64(d) * sum

	return ans
}
```

#### TypeScript

```ts
function weightedSum(parent: number[], nums: number[]): number {
    const n = nums.length;

    const g: number[][] = Array.from({ length: n }, () => []);

    for (let i = 1; i < n; i++) {
        g[parent[i]].push(i);
    }

    let ans = 0;

    let q: number[] = [0];

    let d = 0;

    while (q.length > 0) {
        d++;

        const nq: number[] = [];

        for (const i of q) {
            ans += nums[i] * (1 - d);

            for (const son of g[i]) {
                nq.push(son);
            }
        }

        q = nq;
    }

    let sum = 0;
    for (const x of nums) {
        sum += x;
    }

    ans += d * sum;

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
