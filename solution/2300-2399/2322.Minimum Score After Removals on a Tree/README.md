---
comments: true
difficulty: 困难
rating: 2391
source: 第 299 场周赛 Q4
tags:
    - 位运算
    - 树
    - 深度优先搜索
    - 数组
---

<!-- problem:start -->

# [2322. 从树中删除边的最小分数](https://leetcode.cn/problems/minimum-score-after-removals-on-a-tree)

[English Version](/solution/2300-2399/2322.Minimum%20Score%20After%20Removals%20on%20a%20Tree/README_EN.md)

## 题目描述

<!-- description:start -->

<p>存在一棵无向连通树，树中有编号从 <code>0</code> 到 <code>n - 1</code> 的 <code>n</code> 个节点， 以及 <code>n - 1</code> 条边。</p>

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>nums</code> ，长度为 <code>n</code> ，其中 <code>nums[i]</code> 表示第 <code>i</code> 个节点的值。另给你一个二维整数数组 <code>edges</code> ，长度为 <code>n - 1</code> ，其中 <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> 表示树中存在一条位于节点 <code>a<sub>i</sub></code> 和 <code>b<sub>i</sub></code> 之间的边。</p>

<p>删除树中两条 <strong>不同</strong> 的边以形成三个连通组件。对于一种删除边方案，定义如下步骤以计算其分数：</p>

<ol>
	<li>分别获取三个组件 <strong>每个</strong> 组件中所有节点值的异或值。</li>
	<li><strong>最大</strong> 异或值和 <strong>最小</strong> 异或值的 <strong>差值</strong> 就是这一种删除边方案的分数。</li>
</ol>

<ul>
	<li>例如，三个组件的节点值分别是：<code>[4,5,7]</code>、<code>[1,9]</code> 和 <code>[3,3,3]</code> 。三个异或值分别是 <code>4 ^ 5 ^ 7 = <em><strong>6</strong></em></code>、<code>1 ^ 9 = <em><strong>8</strong></em></code> 和 <code>3 ^ 3 ^ 3 = <em><strong>3</strong></em></code> 。最大异或值是 <code>8</code> ，最小异或值是 <code>3</code> ，分数是 <code>8 - 3 = 5</code> 。</li>
</ul>

<p>返回在给定树上执行任意删除边方案可能的 <strong>最小</strong> 分数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2322.Minimum%20Score%20After%20Removals%20on%20a%20Tree/images/ex1drawio.png" style="width: 193px; height: 190px;">
<pre><strong>输入：</strong>nums = [1,5,5,4,11], edges = [[0,1],[1,2],[1,3],[3,4]]
<strong>输出：</strong>9
<strong>解释：</strong>上图展示了一种删除边方案。
- 第 1 个组件的节点是 [1,3,4] ，值是 [5,4,11] 。异或值是 5 ^ 4 ^ 11 = 10 。
- 第 2 个组件的节点是 [0] ，值是 [1] 。异或值是 1 = 1 。
- 第 3 个组件的节点是 [2] ，值是 [5] 。异或值是 5 = 5 。
分数是最大异或值和最小异或值的差值，10 - 1 = 9 。
可以证明不存在分数比 9 小的删除边方案。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2322.Minimum%20Score%20After%20Removals%20on%20a%20Tree/images/ex2drawio.png" style="width: 287px; height: 150px;">
<pre><strong>输入：</strong>nums = [5,5,2,4,4,2], edges = [[0,1],[1,2],[5,2],[4,3],[1,3]]
<strong>输出：</strong>0
<strong>解释：</strong>上图展示了一种删除边方案。
- 第 1 个组件的节点是 [3,4] ，值是 [4,4] 。异或值是 4 ^ 4 = 0 。
- 第 2 个组件的节点是 [1,0] ，值是 [5,5] 。异或值是 5 ^ 5 = 0 。
- 第 3 个组件的节点是 [2,5] ，值是 [2,2] 。异或值是 2 ^ 2 = 0 。
分数是最大异或值和最小异或值的差值，0 - 0 = 0 。
无法获得比 0 更小的分数 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>3 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>8</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li><code>edges</code> 表示一棵有效的树</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS + 子树异或和

<!-- thinking:start -->

> **思考**
>
> 删两条边得到三块，分数为三块异或和的极差。 $n \le 1000$，枚举边对再各算一遍异或过于笨重。整树异或 $s$ 固定，一块的异或等于其子树异或。
>
> 先指定一条被删边，得到含根块异或 $s_1$。再在该块内 DFS，每个子树异或 $s_2$ 对应第二条删除。三块即为 $s\oplus s_1$、 $s_2$、 $s_1\oplus s_2$。枚举根与邻边覆盖所有无序边对。

<!-- thinking:end -->

我们记树的异或和为 $s$，即 $s = \text{nums}[0] \oplus \text{nums}[1] \oplus \ldots \oplus \text{nums}[n-1]$。

接下来，枚举 $[0..n)$ 的每个点 $i$ 作为树的根节点，将根节点与某个子节点 $j$ 相连的边作为第一条被删除的边。这样我们就获得了两个连通块，我们记包含根节点 $i$ 的连通块的异或和为 $s_1$，然后我们对包含根节点 $i$ 的连通块进行 DFS，计算出每个子树的异或和，记每次 DFS 计算出的子树异或和为 $s_2$。那么三个连通块的异或和分别为 $s \oplus s_1$, $s_2$ 和 $s_1 \oplus s_2$。我们需要计算这三个异或和的最大值和最小值，记为 $\textit{mx}$ 和 $\textit{mn}$，那么对于枚举的每一种情况，得到的分数为 $\textit{mx} - \textit{mn}$。求所有情况的最小值作为答案。

计算每个子树的异或和可以通过 DFS 实现。定义一个函数 $\text{dfs}(i, fa)$，表示从节点 $i$ 开始 DFS，而 $fa$ 是节点 $i$ 的父节点。函数返回值为以节点 $i$ 为根的子树的异或和。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 是树的节点数。

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

### 方法二：显式栈 + 子树异或和

<!-- thinking:start -->

> **思考**
>
> 删去两条边会得到三个连通块，分数是这三块异或和的极差。$n \le 1000$，若对每对边都重新遍历整棵树，常数会偏大。整树异或 $s$ 是定值，删去一条边后，一侧连通块的异或等于按该边定向后的子树异或。先枚举第一条被删边，求出含当前根的那一块异或 $s_1$，再把该块里每个子树异或 $s_2$ 当作第二条删除，三块就是 $s \oplus s_1$、$s_2$ 和 $s_1 \oplus s_2$。沿一条链递归计算这些子树异或时，调用深度等于节点数，在 $n = 1000$ 时会超出 Python 的递归上限。因此栈中保存 $(节点, 父节点, 状态)$：状态 $0$ 先压入退出标记再压入子节点，状态 $1$ 在子树异或就绪后写回当前节点。第一次栈求出 $s_1$，第二次栈对每个子树 $s_2$ 更新极差。

<!-- thinking:end -->

我们记树的异或和为 $s$，即 $s = \text{nums}[0] \oplus \text{nums}[1] \oplus \ldots \oplus \text{nums}[n-1]$。

接下来，枚举 $[0..n)$ 的每个点 $i$，并把 $i$ 与某个邻接点 $j$ 之间的边当作第一条被删除的边。这样得到两个连通块。记包含 $i$ 的连通块的异或和为 $s_1$，再在这块里面求出每个子树的异或和 $s_2$。三个连通块的异或和分别是 $s \oplus s_1$、$s_2$ 和 $s_1 \oplus s_2$。它们的最大值与最小值之差就是当前删边方案的分数，答案取所有方案的最小值。枚举每个点及其每条邻边，可以覆盖所有无序边对。

子树异或用显式栈按后序计算。栈中元素是 $(节点, 父节点, 状态)$。状态为 $0$ 时先压入退出标记，再压入除父节点以外的邻接点；状态为 $1$ 时把各子树异或与自身值异或，得到以当前节点为根的子树异或。第一次栈只取包含 $i$、且不经过 $j$ 的那一块异或 $s_1$。第二次栈在算出每个子树异或 $s_2$ 后，用上面的三个值更新答案。子节点的处理顺序不影响极差。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 是树的节点数。

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
