---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2322.Minimum%20Score%20After%20Removals%20on%20a%20Tree/README_EN.md
rating: 2391
source: Weekly Contest 299 Q4
tags:
    - Bit Manipulation
    - Tree
    - Depth-First Search
    - Array
---

<!-- problem:start -->

# [2322. Minimum Score After Removals on a Tree](https://leetcode.com/problems/minimum-score-after-removals-on-a-tree)

[中文文档](/solution/2300-2399/2322.Minimum%20Score%20After%20Removals%20on%20a%20Tree/README.md)

## Description

<!-- description:start -->

<p>There is an undirected connected tree with <code>n</code> nodes labeled from <code>0</code> to <code>n - 1</code> and <code>n - 1</code> edges.</p>

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> of length <code>n</code> where <code>nums[i]</code> represents the value of the <code>i<sup>th</sup></code> node. You are also given a 2D integer array <code>edges</code> of length <code>n - 1</code> where <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> indicates that there is an edge between nodes <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code> in the tree.</p>

<p>Remove two <strong>distinct</strong> edges of the tree to form three connected components. For a pair of removed edges, the following steps are defined:</p>

<ol>
	<li>Get the XOR of all the values of the nodes for <strong>each</strong> of the three components respectively.</li>
	<li>The <strong>difference</strong> between the <strong>largest</strong> XOR value and the <strong>smallest</strong> XOR value is the <strong>score</strong> of the pair.</li>
</ol>

<ul>
	<li>For example, say the three components have the node values: <code>[4,5,7]</code>, <code>[1,9]</code>, and <code>[3,3,3]</code>. The three XOR values are <code>4 ^ 5 ^ 7 = <u><strong>6</strong></u></code>, <code>1 ^ 9 = <u><strong>8</strong></u></code>, and <code>3 ^ 3 ^ 3 = <u><strong>3</strong></u></code>. The largest XOR value is <code>8</code> and the smallest XOR value is <code>3</code>. The score is then <code>8 - 3 = 5</code>.</li>
</ul>

<p>Return <em>the <strong>minimum</strong> score of any possible pair of edge removals on the given tree</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2322.Minimum%20Score%20After%20Removals%20on%20a%20Tree/images/ex1drawio.png" style="width: 193px; height: 190px;" />
<pre>
<strong>Input:</strong> nums = [1,5,5,4,11], edges = [[0,1],[1,2],[1,3],[3,4]]
<strong>Output:</strong> 9
<strong>Explanation:</strong> The diagram above shows a way to make a pair of removals.
- The 1<sup>st</sup> component has nodes [1,3,4] with values [5,4,11]. Its XOR value is 5 ^ 4 ^ 11 = 10.
- The 2<sup>nd</sup> component has node [0] with value [1]. Its XOR value is 1 = 1.
- The 3<sup>rd</sup> component has node [2] with value [5]. Its XOR value is 5 = 5.
The score is the difference between the largest and smallest XOR value which is 10 - 1 = 9.
It can be shown that no other pair of removals will obtain a smaller score than 9.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2322.Minimum%20Score%20After%20Removals%20on%20a%20Tree/images/ex2drawio.png" style="width: 287px; height: 150px;" />
<pre>
<strong>Input:</strong> nums = [5,5,2,4,4,2], edges = [[0,1],[1,2],[5,2],[4,3],[1,3]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The diagram above shows a way to make a pair of removals.
- The 1<sup>st</sup> component has nodes [3,4] with values [4,4]. Its XOR value is 4 ^ 4 = 0.
- The 2<sup>nd</sup> component has nodes [1,0] with values [5,5]. Its XOR value is 5 ^ 5 = 0.
- The 3<sup>rd</sup> component has nodes [2,5] with values [2,2]. Its XOR value is 2 ^ 2 = 0.
The score is the difference between the largest and smallest XOR value which is 0 - 0 = 0.
We cannot obtain a smaller score than 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>3 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>8</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li><code>edges</code> represents a valid tree.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: DFS + Subtree XOR Sum

We denote the XOR sum of the tree as $s$, i.e., $s = \text{nums}[0] \oplus \text{nums}[1] \oplus \ldots \oplus \text{nums}[n-1]$.

Next, we enumerate each node $i$ in $[0..n)$ as the root of the tree, and treat the edge connecting the root node to some child node $j$ as the first edge to be removed. This gives us two connected components. We denote the XOR sum of the connected component containing root node $i$ as $s_1$, then we perform DFS on the connected component containing root node $i$ to calculate the XOR sum of each subtree, denoting each XOR sum calculated by DFS as $s_2$. The XOR sums of the three connected components are $s \oplus s_1$, $s_2$, and $s_1 \oplus s_2$. We need to calculate the maximum and minimum values of these three XOR sums, denoted as $\textit{mx}$ and $\textit{mn}$. For each enumerated case, the score is $\textit{mx} - \textit{mn}$. We find the minimum value among all cases as the answer.

The XOR sum of each subtree can be calculated through DFS. We define a function $\text{dfs}(i, fa)$, which represents starting DFS from node $i$, where $fa$ is the parent node of node $i$. The function returns the XOR sum of the subtree rooted at node $i$.

The time complexity is $O(n^2)$, and the space complexity is $O(n)$, where $n$ is the number of nodes in the tree.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumScore(self, nums: List[int], edges: List[List[int]]) -> int:
        def dfs(i: int, fa: int) -> int:
            res = nums[i]
            for j in g[i]:
                if j != fa:
                    res ^= dfs(j, i)
            return res

        def dfs2(i: int, fa: int) -> int:
            nonlocal s, s1, ans
            res = nums[i]
            for j in g[i]:
                if j != fa:
                    s2 = dfs2(j, i)
                    res ^= s2
                    mx = max(s ^ s1, s2, s1 ^ s2)
                    mn = min(s ^ s1, s2, s1 ^ s2)
                    ans = min(ans, mx - mn)
            return res

        g = defaultdict(list)
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        s = reduce(lambda x, y: x ^ y, nums)
        n = len(nums)
        ans = inf
        for i in range(n):
            for j in g[i]:
                s1 = dfs(i, j)
                dfs2(i, j)
        return ans
```

#### Java

```java
class Solution {
    private int[] nums;
    private List<Integer>[] g;
    private int ans = Integer.MAX_VALUE;
    private int s;
    private int s1;

    public int minimumScore(int[] nums, int[][] edges) {
        int n = nums.length;
        this.nums = nums;
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        for (int x : nums) {
            s ^= x;
        }
        for (int i = 0; i < n; ++i) {
            for (int j : g[i]) {
                s1 = dfs(i, j);
                dfs2(i, j);
            }
        }
        return ans;
    }

    private int dfs(int i, int fa) {
        int res = nums[i];
        for (int j : g[i]) {
            if (j != fa) {
                res ^= dfs(j, i);
            }
        }
        return res;
    }

    private int dfs2(int i, int fa) {
        int res = nums[i];
        for (int j : g[i]) {
            if (j != fa) {
                int s2 = dfs2(j, i);
                res ^= s2;
                int mx = Math.max(Math.max(s ^ s1, s2), s1 ^ s2);
                int mn = Math.min(Math.min(s ^ s1, s2), s1 ^ s2);
                ans = Math.min(ans, mx - mn);
            }
        }
        return res;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumScore(vector<int>& nums, vector<vector<int>>& edges) {
        int n = nums.size();
        vector<int> g[n];
        for (const auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        int s = 0, s1 = 0;
        int ans = INT_MAX;
        for (int x : nums) {
            s ^= x;
        }
        auto dfs = [&](this auto&& dfs, int i, int fa) -> int {
            int res = nums[i];
            for (int j : g[i]) {
                if (j != fa) {
                    res ^= dfs(j, i);
                }
            }
            return res;
        };
        auto dfs2 = [&](this auto&& dfs2, int i, int fa) -> int {
            int res = nums[i];
            for (int j : g[i]) {
                if (j != fa) {
                    int s2 = dfs2(j, i);
                    res ^= s2;
                    int mx = max({s ^ s1, s2, s1 ^ s2});
                    int mn = min({s ^ s1, s2, s1 ^ s2});
                    ans = min(ans, mx - mn);
                }
            }
            return res;
        };
        for (int i = 0; i < n; ++i) {
            for (int j : g[i]) {
                s1 = dfs(i, j);
                dfs2(i, j);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minimumScore(nums []int, edges [][]int) int {
	n := len(nums)
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	s, s1 := 0, 0
	ans := math.MaxInt32
	for _, x := range nums {
		s ^= x
	}
	var dfs func(i, fa int) int
	dfs = func(i, fa int) int {
		res := nums[i]
		for _, j := range g[i] {
			if j != fa {
				res ^= dfs(j, i)
			}
		}
		return res
	}
	var dfs2 func(i, fa int) int
	dfs2 = func(i, fa int) int {
		res := nums[i]
		for _, j := range g[i] {
			if j != fa {
				s2 := dfs2(j, i)
				res ^= s2
				mx := max(s^s1, s2, s1^s2)
				mn := min(s^s1, s2, s1^s2)
				ans = min(ans, mx-mn)
			}
		}
		return res
	}
	for i := 0; i < n; i++ {
		for _, j := range g[i] {
			s1 = dfs(i, j)
			dfs2(i, j)
		}
	}
	return ans
}
```

#### TypeScript

```ts
function minimumScore(nums: number[], edges: number[][]): number {
    const n = nums.length;
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    const s = nums.reduce((a, b) => a ^ b, 0);
    let s1 = 0;
    let ans = Number.MAX_SAFE_INTEGER;
    function dfs(i: number, fa: number): number {
        let res = nums[i];
        for (const j of g[i]) {
            if (j !== fa) {
                res ^= dfs(j, i);
            }
        }
        return res;
    }
    function dfs2(i: number, fa: number): number {
        let res = nums[i];
        for (const j of g[i]) {
            if (j !== fa) {
                const s2 = dfs2(j, i);
                res ^= s2;
                const mx = Math.max(s ^ s1, s2, s1 ^ s2);
                const mn = Math.min(s ^ s1, s2, s1 ^ s2);
                ans = Math.min(ans, mx - mn);
            }
        }
        return res;
    }
    for (let i = 0; i < n; ++i) {
        for (const j of g[i]) {
            s1 = dfs(i, j);
            dfs2(i, j);
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn minimum_score(nums: Vec<i32>, edges: Vec<Vec<i32>>) -> i32 {
        let n = nums.len();
        let mut g = vec![vec![]; n];
        for e in edges.iter() {
            let a = e[0] as usize;
            let b = e[1] as usize;
            g[a].push(b);
            g[b].push(a);
        }
        let mut s1 = 0;
        let mut ans = i32::MAX;
        let s = nums.iter().fold(0, |acc, &x| acc ^ x);

        fn dfs(i: usize, fa: usize, g: &Vec<Vec<usize>>, nums: &Vec<i32>) -> i32 {
            let mut res = nums[i];
            for &j in &g[i] {
                if j != fa {
                    res ^= dfs(j, i, g, nums);
                }
            }
            res
        }

        fn dfs2(
            i: usize,
            fa: usize,
            g: &Vec<Vec<usize>>,
            nums: &Vec<i32>,
            s: i32,
            s1: i32,
            ans: &mut i32
        ) -> i32 {
            let mut res = nums[i];
            for &j in &g[i] {
                if j != fa {
                    let s2 = dfs2(j, i, g, nums, s, s1, ans);
                    res ^= s2;
                    let mx = (s ^ s1).max(s2).max(s1 ^ s2);
                    let mn = (s ^ s1).min(s2).min(s1 ^ s2);
                    *ans = (*ans).min(mx - mn);
                }
            }
            res
        }

        for i in 0..n {
            for &j in &g[i] {
                s1 = dfs(i, j, &g, &nums);
                dfs2(i, j, &g, &nums, s, s1, &mut ans);
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
