---
comments: true
difficulty: 中等
---

<!-- problem:start -->

# [4112. 将子树置零后的最大得分](https://leetcode.cn/problems/maximize-score-after-zeroing-subtrees)

[English Version](/solution/4100-4199/4112.Maximize%20Score%20After%20Zeroing%20Subtrees/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code> 的整数数组 <code>parent</code>，表示一棵节点编号从 0 到 <code>n - 1</code> 的有根树，其中 <code>parent[i]</code> 表示节点 <code>i</code> 的父节点。树的根节点为节点 0，因此 <code>parent[0] = -1</code>。</p>

<p>另给你两个长度均为 <code>n</code> 的整数数组 <code>op</code> 和 <code>score</code>，其中 <code>op[i]</code> 决定节点 <code>i</code> 允许执行的操作，<code>score[i]</code> 表示该节点的分数。</p>

<p>你需要按照以下规则处理每个&nbsp;<strong>非叶节点</strong> <code>i</code>：</p>

<ul>
	<li>如果 <code>op[i] = 0</code>，你可以选择节点 <code>i</code> 的<strong>&nbsp;至多&nbsp;</strong>一个子节点，并将以该子节点为根的&nbsp;<strong>子树</strong>&nbsp;中所有节点的分数设为 0。</li>
	<li>如果 <code>op[i] = 1</code>，你必须选择节点 <code>i</code> 的<strong>&nbsp;恰好&nbsp;</strong>一个子节点，并将以该子节点为根的子树中所有节点的分数设为 0。</li>
</ul>

<p>每个非叶节点都必须恰好处理一次。你可以按任意顺序处理这些节点。</p>

<p>返回执行这些操作后，所有节点分数之和的<strong>&nbsp;最大可能值</strong>。</p>

<p><strong>叶节点&nbsp;</strong>是没有子节点的节点。</p>

<p>以某个节点为根的<strong>&nbsp;子树</strong>&nbsp;由该节点及其所有后代节点组成。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4100-4199/4112.Maximize%20Score%20After%20Zeroing%20Subtrees/images/1794630276-Yulmik-1000061694.gif" style="width: 533px; height: 300px;" />​​​​​​​</p>

<p><strong>输入：</strong> <span class="example-io">parent = [-1,0,0,0,1,2,3,3], op = [1,1,0,0,1,1,0,1], score = [5,6,1,2,3,2,3,-3]</span></p>

<p><strong>输出：</strong> <span class="example-io">16</span></p>

<p><strong>解释：</strong></p>

<p>一种最优方案是按以下顺序处理节点：</p>

<ul>
	<li>节点 4、5、6 和 7<strong>：</strong>它们都是叶节点，因此不对它们执行任何操作。</li>
	<li>节点 1<strong>：</strong>由于 <code>op[1] = 1</code>，我们必须选择它的一个子节点。选择节点 4，将以它为根的子树中所有节点（节点 4）的分数设为 0。</li>
	<li>节点 2：由于 <code>op[2] = 0</code>，我们可以选择一个子节点，也可以不执行任何操作。对于该节点，我们不执行任何操作。</li>
	<li>节点 3：由于 <code>op[3] = 0</code>，我们可以选择一个子节点，也可以不执行任何操作。选择节点 7，将以它为根的子树中所有节点（节点 7）的分数设为 0。</li>
	<li>节点 0：由于 <code>op[0] = 1</code>，我们必须选择它的一个子节点。选择节点 2，将以它为根的子树中所有节点（节点 2 和 5）的分数设为 0。</li>
</ul>

<p>执行这些操作后，各节点的分数为 <code>[5, 6, 0, 2, 0, 0, 3, 0]</code>，总和为 16。</p>

<p>不存在能够得到更大分数总和的合法操作序列。因此，答案为 16。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4100-4199/4112.Maximize%20Score%20After%20Zeroing%20Subtrees/images/1794630276-FcQkDj-1000061692.png" style="width: 220px; height: 180px;" />​​​​​​​</p>

<p><strong>输入：</strong> <span class="example-io">parent = [-1,0,0], op = [0,0,0], score = [1,2,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">6</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>我们只需要处理节点 0，因为节点 1 和节点 2 都是叶节点。</li>
	<li>由于 <code>op[0] = 0</code>，我们可以选择不执行任何操作。</li>
	<li>因此，所有节点的分数都得以保留，得到的最大分数总和为 6。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/4100-4199/4112.Maximize%20Score%20After%20Zeroing%20Subtrees/images/1794630276-xLiVcP-1000061693.png" style="width: 139px; height: 60px;" />​​​​​​​</p>

<p><strong>输入：</strong> <span class="example-io">parent = [-1], op = [1], score = [-10]</span></p>

<p><strong>输出：</strong> <span class="example-io">-10</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>树中唯一的节点是叶节点，因此无法对任何节点执行操作。</li>
	<li>因此，最大分数总和为 -10。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>n == parent.length == op.length == score.length</code></li>
	<li><code>parent[0] == -1</code></li>
	<li>对于所有 <code>i</code> 在 <code>[1, n - 1]</code> 范围内，均有 <code>0 &lt;= parent[i] &lt;= n - 1</code></li>
	<li><code>0 &lt;= op[i] &lt;= 1</code></li>
	<li><code>-10<sup>4</sup> &lt;= score[i] &lt;= 10<sup>4</sup></code></li>
	<li>输入保证数组 <code>parent</code> 表示一棵以节点 0 为根的合法树。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：深度优先搜索

<!-- thinking:start -->

> **思考**
>
> $n$ 达到 $10^5$。每个非叶节点都要决定清零哪一棵子树，把所有组合枚举出来是指数级的，无法通过。
>
> 祖先把一棵子树整体清零之后，子树内部的操作只作用于已经变成 $0$ 的节点。子树若被保留，它能留下的最大分数就只由内部的选择决定。
>
> 因此自底向上定义 $\textit{dfs}(i)$：节点 $i$ 的子树没有被祖先整棵清零时，这份子树能留下的最大分数。叶节点没有操作，只能留下 $\textit{score}[i]$。非叶节点先得到每棵子树的 $\textit{dfs}$ 值，再按 $op[i]$ 决定丢掉其中最小的一份，或只在这份为负时丢掉。
>
> 每个节点只访问一次。递归从叶节点返回后，非叶节点把全部子树分数相加，再减去按 $op[i]$ 丢掉的那一份。

<!-- thinking:end -->

我们按 $\textit{parent}$ 建出每个节点的子节点列表。 $\textit{dfs}(i)$ 表示节点 $i$ 的子树在不被祖先整棵清零时能保留的最大分数。

若 $i$ 是叶节点，返回 $\textit{score}[i]$。

否则令 $\textit{tot}=\textit{score}[i]$。对每个子节点 $j$ 递归得到 $t=\textit{dfs}(j)$，把 $t$ 累加到 $\textit{tot}$，并用 $mn$ 记下其中的最小值。此时 $\textit{tot}$ 是所有子树都保留时的分数。

当 $op[i]=1$ 时，必须清零恰好一棵子树。清掉 $mn$ 对应的那棵，返回 $\textit{tot}-mn$。当 $op[i]=0$ 时，可以不清零，也可以清零一棵。只有 $mn<0$ 时，清掉这棵子树才会比全部保留更大，因此返回 $\textit{tot}-\min(0,mn)$。

答案是 $\textit{dfs}(0)$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是节点个数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxScore(self, parent: list[int], op: list[int], score: list[int]) -> int:
        def dfs(i: int) -> int:
            if not g[i]:
                return score[i]
            tot = score[i]
            mn = inf
            for j in g[i]:
                t = dfs(j)
                mn = min(mn, t)
                tot += t
            if op[i] == 0:
                return tot - min(0, mn)
            return tot - mn

        n = len(parent)
        g = [[] for _ in range(n)]
        for i in range(1, n):
            g[parent[i]].append(i)
        return dfs(0)
```

#### Java

```java
class Solution {
    private List<Integer>[] g;
    private int[] op;
    private int[] score;

    public int maxScore(int[] parent, int[] op, int[] score) {
        this.op = op;
        this.score = score;
        int n = parent.length;
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 1; i < n; i++) {
            g[parent[i]].add(i);
        }
        return dfs(0);
    }

    private int dfs(int i) {
        if (g[i].isEmpty()) {
            return score[i];
        }
        int tot = score[i];
        int mn = Integer.MAX_VALUE;
        for (int j : g[i]) {
            int t = dfs(j);
            mn = Math.min(mn, t);
            tot += t;
        }
        if (op[i] == 0) {
            return tot - Math.min(0, mn);
        }
        return tot - mn;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxScore(vector<int> parent, vector<int> op, vector<int> score) {
        int n = parent.size();
        vector<vector<int>> g(n);
        for (int i = 1; i < n; i++) {
            g[parent[i]].push_back(i);
        }
        auto dfs = [&](this auto&& dfs, int i) -> int {
            if (g[i].empty()) {
                return score[i];
            }
            int tot = score[i];
            int mn = INT_MAX;
            for (int j : g[i]) {
                int t = dfs(j);
                mn = min(mn, t);
                tot += t;
            }
            if (op[i] == 0) {
                return tot - min(0, mn);
            }
            return tot - mn;
        };
        return dfs(0);
    }
};
```

#### Go

```go
func maxScore(parent []int, op []int, score []int) int {
	n := len(parent)
	g := make([][]int, n)
	for i := 1; i < n; i++ {
		g[parent[i]] = append(g[parent[i]], i)
	}
	var dfs func(i int) int
	dfs = func(i int) int {
		if len(g[i]) == 0 {
			return score[i]
		}
		tot := score[i]
		mn := int(^uint(0) >> 1)
		for _, j := range g[i] {
			t := dfs(j)
			if t < mn {
				mn = t
			}
			tot += t
		}
		if op[i] == 0 {
			return tot - min(0, mn)
		}
		return tot - mn
	}
	return dfs(0)
}
```

#### TypeScript

```ts
function maxScore(parent: number[], op: number[], score: number[]): number {
    const n = parent.length;
    const g: number[][] = Array.from({ length: n }, () => []);
    for (let i = 1; i < n; i++) {
        g[parent[i]].push(i);
    }
    const dfs = (i: number): number => {
        if (g[i].length === 0) {
            return score[i];
        }
        let tot = score[i];
        let mn = Infinity;
        for (const j of g[i]) {
            const t = dfs(j);
            mn = Math.min(mn, t);
            tot += t;
        }
        if (op[i] === 0) {
            return tot - Math.min(0, mn);
        }
        return tot - mn;
    };
    return dfs(0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
