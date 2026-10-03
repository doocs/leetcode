---
comments: true
difficulty: Hard
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

<!-- thinking:start -->

> **Thinking**
>
> Deleting two edges yields three components; the score is the range of their XORs. $n \le 1000$, so pairing edges and recomputing XOR is heavy. The whole-tree XOR $s$ is fixed, and a component XOR equals a subtree XOR after rooting.
>
> Delete one edge first to get the root-side XOR $s_1$. DFS inside that block; each subtree XOR $s_2$ is the second cut. The three values are $s\oplus s_1$, $s_2$, and $s_1\oplus s_2$. Trying every root and neighbor covers all unordered edge pairs.

<!-- thinking:end -->

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

#### C#

```cs
public class Solution {
    public int MinimumScore(int[] nums, int[][] edges) {
        int n = nums.Length;
        List<int>[] g = new List<int>[n];
        for (int i = 0; i < n; i++) {
            g[i] = new List<int>();
        }
        foreach (var e in edges) {
            int a = e[0], b = e[1];
            g[a].Add(b);
            g[b].Add(a);
        }

        int s = 0;
        foreach (int x in nums) {
            s ^= x;
        }

        int ans = int.MaxValue;
        int s1 = 0;

        int Dfs(int i, int fa) {
            int res = nums[i];
            foreach (int j in g[i]) {
                if (j != fa) {
                    res ^= Dfs(j, i);
                }
            }
            return res;
        }

        int Dfs2(int i, int fa) {
            int res = nums[i];
            foreach (int j in g[i]) {
                if (j != fa) {
                    int s2 = Dfs2(j, i);
                    res ^= s2;
                    int mx = Math.Max(Math.Max(s ^ s1, s2), s1 ^ s2);
                    int mn = Math.Min(Math.Min(s ^ s1, s2), s1 ^ s2);
                    ans = Math.Min(ans, mx - mn);
                }
            }
            return res;
        }

        for (int i = 0; i < n; ++i) {
            foreach (int j in g[i]) {
                s1 = Dfs(i, j);
                Dfs2(i, j);
            }
        }

        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Explicit Stack + Subtree XOR Sum

<!-- thinking:start -->

> **Thinking**
>
> Deleting two edges yields three components, and the score is the range of their XOR sums. With $n \le 1000$, pairing every two edges and scanning the whole tree for each pair is heavier than necessary. The XOR $s$ of the whole tree is fixed, and after one edge is removed a component XOR is the subtree XOR under that orientation. Enumerate the first deleted edge to obtain the root-side XOR $s_1$, then treat each subtree XOR $s_2$ inside that component as the second cut. The three values are $s \oplus s_1$, $s_2$, and $s_1 \oplus s_2$. Recursing along a chain uses a call depth equal to the node count and overflows Python at $n = 1000$. The stack therefore stores $(node, parent, state)$: state $0$ pushes the exit marker and then the children, and state $1$ writes the subtree XOR after the children are ready. The first walk produces $s_1$, and the second walk updates the range for every subtree $s_2$.

<!-- thinking:end -->

Denote the XOR sum of the tree by $s$, that is, $s = \text{nums}[0] \oplus \text{nums}[1] \oplus \ldots \oplus \text{nums}[n-1]$.

Enumerate each node $i$ in $[0..n)$ and treat the edge between $i$ and a neighbor $j$ as the first deleted edge. This splits the tree into two components. Let $s_1$ be the XOR sum of the component that contains $i$, and let $s_2$ be the XOR sum of a subtree inside that component. The three component XOR sums are $s \oplus s_1$, $s_2$, and $s_1 \oplus s_2$. The score of this deletion is the difference between their maximum and minimum, and the answer is the minimum score over every deletion. Trying every node and every incident edge covers all unordered pairs of edges.

Subtree XOR sums are filled in postorder on an explicit stack. Each frame is $(node, parent, state)$. State $0$ pushes the exit marker and then every neighbor other than the parent. State $1$ XORs the node value with each finished child subtree. The first walk returns only $s_1$, the XOR of the component that contains $i$ and does not cross $j$. The second walk, after each subtree XOR $s_2$ is known, updates the answer with the three values above. The order of the children does not change the range.

The time complexity is $O(n^2)$, and the space complexity is $O(n)$, where $n$ is the number of nodes in the tree.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumScore(self, nums: List[int], edges: List[List[int]]) -> int:
        n = len(nums)
        g = [[] for _ in range(n)]
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        s = 0
        for x in nums:
            s ^= x

        def component_xor(root: int, ban: int) -> int:
            sub = [0] * n
            stk = [(root, ban, 0)]
            while stk:
                i, fa, state = stk.pop()
                if state == 0:
                    stk.append((i, fa, 1))
                    for j in g[i]:
                        if j != fa:
                            stk.append((j, i, 0))
                else:
                    res = nums[i]
                    for j in g[i]:
                        if j != fa:
                            res ^= sub[j]
                    sub[i] = res
            return sub[root]

        def collect(root: int, ban: int, s1: int) -> None:
            nonlocal ans
            sub = [0] * n
            stk = [(root, ban, 0)]
            while stk:
                i, fa, state = stk.pop()
                if state == 0:
                    stk.append((i, fa, 1))
                    for j in g[i]:
                        if j != fa:
                            stk.append((j, i, 0))
                else:
                    res = nums[i]
                    for j in g[i]:
                        if j != fa:
                            s2 = sub[j]
                            res ^= s2
                            mx = max(s ^ s1, s2, s1 ^ s2)
                            mn = min(s ^ s1, s2, s1 ^ s2)
                            ans = min(ans, mx - mn)
                    sub[i] = res

        ans = inf
        for i in range(n):
            for j in g[i]:
                s1 = component_xor(i, j)
                collect(i, j, s1)
        return ans
```

#### Java

```java
class Solution {
    public int minimumScore(int[] nums, int[][] edges) {
        int n = nums.length;
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        int s = 0;
        for (int x : nums) {
            s ^= x;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            for (int j : g[i]) {
                int s1 = componentXor(nums, g, i, j);
                ans = Math.min(ans, collect(nums, g, i, j, s, s1));
            }
        }
        return ans;
    }

    private int componentXor(int[] nums, List<Integer>[] g, int root, int ban) {
        int n = nums.length;
        int[] sub = new int[n];
        Deque<int[]> stk = new ArrayDeque<>();
        stk.push(new int[] {root, ban, 0});
        while (!stk.isEmpty()) {
            int[] cur = stk.pop();
            int i = cur[0], fa = cur[1], state = cur[2];
            if (state == 0) {
                stk.push(new int[] {i, fa, 1});
                for (int j : g[i]) {
                    if (j != fa) {
                        stk.push(new int[] {j, i, 0});
                    }
                }
            } else {
                int res = nums[i];
                for (int j : g[i]) {
                    if (j != fa) {
                        res ^= sub[j];
                    }
                }
                sub[i] = res;
            }
        }
        return sub[root];
    }

    private int collect(int[] nums, List<Integer>[] g, int root, int ban, int s, int s1) {
        int n = nums.length;
        int ans = Integer.MAX_VALUE;
        int[] sub = new int[n];
        Deque<int[]> stk = new ArrayDeque<>();
        stk.push(new int[] {root, ban, 0});
        while (!stk.isEmpty()) {
            int[] cur = stk.pop();
            int i = cur[0], fa = cur[1], state = cur[2];
            if (state == 0) {
                stk.push(new int[] {i, fa, 1});
                for (int j : g[i]) {
                    if (j != fa) {
                        stk.push(new int[] {j, i, 0});
                    }
                }
            } else {
                int res = nums[i];
                for (int j : g[i]) {
                    if (j != fa) {
                        int s2 = sub[j];
                        res ^= s2;
                        int mx = Math.max(Math.max(s ^ s1, s2), s1 ^ s2);
                        int mn = Math.min(Math.min(s ^ s1, s2), s1 ^ s2);
                        ans = Math.min(ans, mx - mn);
                    }
                }
                sub[i] = res;
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
    int minimumScore(vector<int>& nums, vector<vector<int>>& edges) {
        int n = nums.size();
        vector<vector<int>> g(n);
        for (const auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        int s = 0;
        for (int x : nums) {
            s ^= x;
        }
        int ans = INT_MAX;
        for (int i = 0; i < n; ++i) {
            for (int j : g[i]) {
                int s1 = componentXor(nums, g, i, j);
                ans = min(ans, collect(nums, g, i, j, s, s1));
            }
        }
        return ans;
    }

private:
    int componentXor(vector<int>& nums, vector<vector<int>>& g, int root, int ban) {
        int n = nums.size();
        vector<int> sub(n);
        vector<array<int, 3>> stk{{root, ban, 0}};
        while (!stk.empty()) {
            auto [i, fa, state] = stk.back();
            stk.pop_back();
            if (state == 0) {
                stk.push_back({i, fa, 1});
                for (int j : g[i]) {
                    if (j != fa) {
                        stk.push_back({j, i, 0});
                    }
                }
            } else {
                int res = nums[i];
                for (int j : g[i]) {
                    if (j != fa) {
                        res ^= sub[j];
                    }
                }
                sub[i] = res;
            }
        }
        return sub[root];
    }

    int collect(vector<int>& nums, vector<vector<int>>& g, int root, int ban, int s, int s1) {
        int n = nums.size();
        int ans = INT_MAX;
        vector<int> sub(n);
        vector<array<int, 3>> stk{{root, ban, 0}};
        while (!stk.empty()) {
            auto [i, fa, state] = stk.back();
            stk.pop_back();
            if (state == 0) {
                stk.push_back({i, fa, 1});
                for (int j : g[i]) {
                    if (j != fa) {
                        stk.push_back({j, i, 0});
                    }
                }
            } else {
                int res = nums[i];
                for (int j : g[i]) {
                    if (j != fa) {
                        int s2 = sub[j];
                        res ^= s2;
                        int mx = max({s ^ s1, s2, s1 ^ s2});
                        int mn = min({s ^ s1, s2, s1 ^ s2});
                        ans = min(ans, mx - mn);
                    }
                }
                sub[i] = res;
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
	s := 0
	for _, x := range nums {
		s ^= x
	}
	componentXor := func(root, ban int) int {
		sub := make([]int, n)
		stk := [][3]int{{root, ban, 0}}
		for len(stk) > 0 {
			cur := stk[len(stk)-1]
			stk = stk[:len(stk)-1]
			i, fa, state := cur[0], cur[1], cur[2]
			if state == 0 {
				stk = append(stk, [3]int{i, fa, 1})
				for _, j := range g[i] {
					if j != fa {
						stk = append(stk, [3]int{j, i, 0})
					}
				}
			} else {
				res := nums[i]
				for _, j := range g[i] {
					if j != fa {
						res ^= sub[j]
					}
				}
				sub[i] = res
			}
		}
		return sub[root]
	}
	collect := func(root, ban, s1 int) int {
		ans := math.MaxInt32
		sub := make([]int, n)
		stk := [][3]int{{root, ban, 0}}
		for len(stk) > 0 {
			cur := stk[len(stk)-1]
			stk = stk[:len(stk)-1]
			i, fa, state := cur[0], cur[1], cur[2]
			if state == 0 {
				stk = append(stk, [3]int{i, fa, 1})
				for _, j := range g[i] {
					if j != fa {
						stk = append(stk, [3]int{j, i, 0})
					}
				}
			} else {
				res := nums[i]
				for _, j := range g[i] {
					if j != fa {
						s2 := sub[j]
						res ^= s2
						mx := max(s^s1, s2, s1^s2)
						mn := min(s^s1, s2, s1^s2)
						ans = min(ans, mx-mn)
					}
				}
				sub[i] = res
			}
		}
		return ans
	}
	ans := math.MaxInt32
	for i := 0; i < n; i++ {
		for _, j := range g[i] {
			s1 := componentXor(i, j)
			ans = min(ans, collect(i, j, s1))
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
    const componentXor = (root: number, ban: number): number => {
        const sub = Array(n).fill(0);
        const stk: number[][] = [[root, ban, 0]];
        while (stk.length) {
            const [i, fa, state] = stk.pop()!;
            if (state === 0) {
                stk.push([i, fa, 1]);
                for (const j of g[i]) {
                    if (j !== fa) {
                        stk.push([j, i, 0]);
                    }
                }
            } else {
                let res = nums[i];
                for (const j of g[i]) {
                    if (j !== fa) {
                        res ^= sub[j];
                    }
                }
                sub[i] = res;
            }
        }
        return sub[root];
    };
    const collect = (root: number, ban: number, s1: number): number => {
        let ans = Number.MAX_SAFE_INTEGER;
        const sub = Array(n).fill(0);
        const stk: number[][] = [[root, ban, 0]];
        while (stk.length) {
            const [i, fa, state] = stk.pop()!;
            if (state === 0) {
                stk.push([i, fa, 1]);
                for (const j of g[i]) {
                    if (j !== fa) {
                        stk.push([j, i, 0]);
                    }
                }
            } else {
                let res = nums[i];
                for (const j of g[i]) {
                    if (j !== fa) {
                        const s2 = sub[j];
                        res ^= s2;
                        const mx = Math.max(s ^ s1, s2, s1 ^ s2);
                        const mn = Math.min(s ^ s1, s2, s1 ^ s2);
                        ans = Math.min(ans, mx - mn);
                    }
                }
                sub[i] = res;
            }
        }
        return ans;
    };
    let ans = Number.MAX_SAFE_INTEGER;
    for (let i = 0; i < n; ++i) {
        for (const j of g[i]) {
            const s1 = componentXor(i, j);
            ans = Math.min(ans, collect(i, j, s1));
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
        let s = nums.iter().fold(0, |acc, &x| acc ^ x);

        fn component_xor(root: usize, ban: usize, g: &[Vec<usize>], nums: &[i32]) -> i32 {
            let n = nums.len();
            let mut sub = vec![0; n];
            let mut stk = vec![(root, ban, 0)];
            while let Some((i, fa, state)) = stk.pop() {
                if state == 0 {
                    stk.push((i, fa, 1));
                    for &j in &g[i] {
                        if j != fa {
                            stk.push((j, i, 0));
                        }
                    }
                } else {
                    let mut res = nums[i];
                    for &j in &g[i] {
                        if j != fa {
                            res ^= sub[j];
                        }
                    }
                    sub[i] = res;
                }
            }
            sub[root]
        }

        fn collect(
            root: usize,
            ban: usize,
            g: &[Vec<usize>],
            nums: &[i32],
            s: i32,
            s1: i32,
        ) -> i32 {
            let n = nums.len();
            let mut ans = i32::MAX;
            let mut sub = vec![0; n];
            let mut stk = vec![(root, ban, 0)];
            while let Some((i, fa, state)) = stk.pop() {
                if state == 0 {
                    stk.push((i, fa, 1));
                    for &j in &g[i] {
                        if j != fa {
                            stk.push((j, i, 0));
                        }
                    }
                } else {
                    let mut res = nums[i];
                    for &j in &g[i] {
                        if j != fa {
                            let s2 = sub[j];
                            res ^= s2;
                            let mx = (s ^ s1).max(s2).max(s1 ^ s2);
                            let mn = (s ^ s1).min(s2).min(s1 ^ s2);
                            ans = ans.min(mx - mn);
                        }
                    }
                    sub[i] = res;
                }
            }
            ans
        }

        let mut ans = i32::MAX;
        for i in 0..n {
            for &j in &g[i] {
                let s1 = component_xor(i, j, &g, &nums);
                ans = ans.min(collect(i, j, &g, &nums, s, s1));
            }
        }
        ans
    }
}
```

#### C#

```cs
public class Solution {
    public int MinimumScore(int[] nums, int[][] edges) {
        int n = nums.Length;
        List<int>[] g = new List<int>[n];
        for (int i = 0; i < n; i++) {
            g[i] = new List<int>();
        }
        foreach (var e in edges) {
            int a = e[0], b = e[1];
            g[a].Add(b);
            g[b].Add(a);
        }

        int s = 0;
        foreach (int x in nums) {
            s ^= x;
        }

        int ComponentXor(int root, int ban) {
            int[] sub = new int[n];
            var stk = new Stack<int[]>();
            stk.Push(new int[] { root, ban, 0 });
            while (stk.Count > 0) {
                int[] cur = stk.Pop();
                int i = cur[0], fa = cur[1], state = cur[2];
                if (state == 0) {
                    stk.Push(new int[] { i, fa, 1 });
                    foreach (int j in g[i]) {
                        if (j != fa) {
                            stk.Push(new int[] { j, i, 0 });
                        }
                    }
                } else {
                    int res = nums[i];
                    foreach (int j in g[i]) {
                        if (j != fa) {
                            res ^= sub[j];
                        }
                    }
                    sub[i] = res;
                }
            }
            return sub[root];
        }

        int Collect(int root, int ban, int s1) {
            int best = int.MaxValue;
            int[] sub = new int[n];
            var stk = new Stack<int[]>();
            stk.Push(new int[] { root, ban, 0 });
            while (stk.Count > 0) {
                int[] cur = stk.Pop();
                int i = cur[0], fa = cur[1], state = cur[2];
                if (state == 0) {
                    stk.Push(new int[] { i, fa, 1 });
                    foreach (int j in g[i]) {
                        if (j != fa) {
                            stk.Push(new int[] { j, i, 0 });
                        }
                    }
                } else {
                    int res = nums[i];
                    foreach (int j in g[i]) {
                        if (j != fa) {
                            int s2 = sub[j];
                            res ^= s2;
                            int mx = Math.Max(Math.Max(s ^ s1, s2), s1 ^ s2);
                            int mn = Math.Min(Math.Min(s ^ s1, s2), s1 ^ s2);
                            best = Math.Min(best, mx - mn);
                        }
                    }
                    sub[i] = res;
                }
            }
            return best;
        }

        int ans = int.MaxValue;
        for (int i = 0; i < n; ++i) {
            foreach (int j in g[i]) {
                int s1 = ComponentXor(i, j);
                ans = Math.Min(ans, Collect(i, j, s1));
            }
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
