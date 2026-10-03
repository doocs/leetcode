---
comments: true
difficulty: 中等
rating: 1711
source: 第 307 场周赛 Q3
tags:
    - 树
    - 深度优先搜索
    - 广度优先搜索
    - 哈希表
    - 二叉树
---

<!-- problem:start -->

# [2385. 感染二叉树需要的总时间](https://leetcode.cn/problems/amount-of-time-for-binary-tree-to-be-infected)

[English Version](/solution/2300-2399/2385.Amount%20of%20Time%20for%20Binary%20Tree%20to%20Be%20Infected/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一棵二叉树的根节点 <code>root</code> ，二叉树中节点的值 <strong>互不相同</strong> 。另给你一个整数 <code>start</code> 。在第 <code>0</code> 分钟，<strong>感染</strong> 将会从值为 <code>start</code> 的节点开始爆发。</p>

<p>每分钟，如果节点满足以下全部条件，就会被感染：</p>

<ul>
	<li>节点此前还没有感染。</li>
	<li>节点与一个已感染节点相邻。</li>
</ul>

<p>返回感染整棵树需要的分钟数<em>。</em></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2385.Amount%20of%20Time%20for%20Binary%20Tree%20to%20Be%20Infected/images/image-20220625231744-1.png" style="width: 400px; height: 306px;">
<pre><strong>输入：</strong>root = [1,5,3,null,4,10,6,9,2], start = 3
<strong>输出：</strong>4
<strong>解释：</strong>节点按以下过程被感染：
- 第 0 分钟：节点 3
- 第 1 分钟：节点 1、10、6
- 第 2 分钟：节点5
- 第 3 分钟：节点 4
- 第 4 分钟：节点 9 和 2
感染整棵树需要 4 分钟，所以返回 4 。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2385.Amount%20of%20Time%20for%20Binary%20Tree%20to%20Be%20Infected/images/image-20220625231812-2.png" style="width: 75px; height: 66px;">
<pre><strong>输入：</strong>root = [1], start = 1
<strong>输出：</strong>0
<strong>解释：</strong>第 0 分钟，树中唯一一个节点处于感染状态，返回 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点的数目在范围 <code>[1, 10<sup>5</sup>]</code> 内</li>
	<li><code>1 &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
	<li>每个节点的值 <strong>互不相同</strong></li>
	<li>树中必定存在值为 <code>start</code> 的节点</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：两次 DFS

<!-- thinking:start -->

> **思考**
>
> 感染沿树边向父子扩散，时间为从 $start$ 出发的最远距离。树至多 $10^5$ 结点，需要显式父边。
>
> 第一次 DFS 建成无向邻接表，第二次从 $start$ 再 DFS 取最深深度。两次均为线性。

<!-- thinking:end -->

我们先通过一次 $\textit{DFS}$ 建图，得到一个邻接表 $g$，其中 $g[node]$ 表示与节点 $node$ 相连的所有节点。

然后，我们以 $start$ 作为起点，通过 $\textit{DFS}$ 搜索整棵树，找到最远距离，即为答案。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为二叉树的节点个数。

<!-- tabs:start -->

#### Python3

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def amountOfTime(self, root: Optional[TreeNode], start: int) -> int:
        def dfs(node: Optional[TreeNode], fa: Optional[TreeNode]):
            if node is None:
                return
            if fa:
                g[node.val].append(fa.val)
                g[fa.val].append(node.val)
            dfs(node.left, node)
            dfs(node.right, node)

        def dfs2(node: int, fa: int) -> int:
            ans = 0
            for nxt in g[node]:
                if nxt != fa:
                    ans = max(ans, 1 + dfs2(nxt, node))
            return ans

        g = defaultdict(list)
        dfs(root, None)
        return dfs2(start, -1)
```

#### Java

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private Map<Integer, List<Integer>> g = new HashMap<>();

    public int amountOfTime(TreeNode root, int start) {
        dfs(root, null);
        return dfs2(start, -1);
    }

    private void dfs(TreeNode node, TreeNode fa) {
        if (node == null) {
            return;
        }
        if (fa != null) {
            g.computeIfAbsent(node.val, k -> new ArrayList<>()).add(fa.val);
            g.computeIfAbsent(fa.val, k -> new ArrayList<>()).add(node.val);
        }
        dfs(node.left, node);
        dfs(node.right, node);
    }

    private int dfs2(int node, int fa) {
        int ans = 0;
        for (int nxt : g.getOrDefault(node, List.of())) {
            if (nxt != fa) {
                ans = Math.max(ans, 1 + dfs2(nxt, node));
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    int amountOfTime(TreeNode* root, int start) {
        unordered_map<int, vector<int>> g;
        function<void(TreeNode*, TreeNode*)> dfs = [&](TreeNode* node, TreeNode* fa) {
            if (!node) {
                return;
            }
            if (fa) {
                g[node->val].push_back(fa->val);
                g[fa->val].push_back(node->val);
            }
            dfs(node->left, node);
            dfs(node->right, node);
        };
        function<int(int, int)> dfs2 = [&](int node, int fa) -> int {
            int ans = 0;
            for (int nxt : g[node]) {
                if (nxt != fa) {
                    ans = max(ans, 1 + dfs2(nxt, node));
                }
            }
            return ans;
        };
        dfs(root, nullptr);
        return dfs2(start, -1);
    }
};
```

#### Go

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func amountOfTime(root *TreeNode, start int) int {
	g := map[int][]int{}
	var dfs func(*TreeNode, *TreeNode)
	dfs = func(node, fa *TreeNode) {
		if node == nil {
			return
		}
		if fa != nil {
			g[node.Val] = append(g[node.Val], fa.Val)
			g[fa.Val] = append(g[fa.Val], node.Val)
		}
		dfs(node.Left, node)
		dfs(node.Right, node)
	}
	var dfs2 func(int, int) int
	dfs2 = func(node, fa int) (ans int) {
		for _, nxt := range g[node] {
			if nxt != fa {
				ans = max(ans, 1+dfs2(nxt, node))
			}
		}
		return
	}
	dfs(root, nil)
	return dfs2(start, -1)
}
```

#### TypeScript

```ts
/**
 * Definition for a binary tree node.
 * class TreeNode {
 *     val: number
 *     left: TreeNode | null
 *     right: TreeNode | null
 *     constructor(val?: number, left?: TreeNode | null, right?: TreeNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.left = (left===undefined ? null : left)
 *         this.right = (right===undefined ? null : right)
 *     }
 * }
 */

function amountOfTime(root: TreeNode | null, start: number): number {
    const g: Map<number, number[]> = new Map();
    const dfs = (node: TreeNode | null, fa: TreeNode | null) => {
        if (!node) {
            return;
        }
        if (fa) {
            if (!g.has(node.val)) {
                g.set(node.val, []);
            }
            g.get(node.val)!.push(fa.val);
            if (!g.has(fa.val)) {
                g.set(fa.val, []);
            }
            g.get(fa.val)!.push(node.val);
        }
        dfs(node.left, node);
        dfs(node.right, node);
    };
    const dfs2 = (node: number, fa: number): number => {
        let ans = 0;
        for (const nxt of g.get(node) || []) {
            if (nxt !== fa) {
                ans = Math.max(ans, 1 + dfs2(nxt, node));
            }
        }
        return ans;
    };
    dfs(root, null);
    return dfs2(start, -1);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：显式栈

<!-- thinking:start -->

> **思考**
>
> 感染沿父子边扩散，整棵树被感染的时间等于从 $start$ 出发的最远距离。先沿树记下这些边，再从起点求深度，在较短的树上是对的。
>
> 结点个数可达 $10^5$。左链使两次遍历都按结点个数递归，调用栈会溢出。
>
> 两次遍历都只依赖当前结点和父结点，而一个结点的深度等于其余邻接点深度的最大值再加一。这些量可以放在显式栈旁边，不必占用返回帧。
>
> 第一趟栈把每条父子边写入无向邻接表，结点值互不相同，直接作为键。第二趟栈进入结点后压入邻接点，退出时用邻接点的深度更新当前深度。$start$ 上记下的深度就是答案。

<!-- thinking:end -->

我们用显式栈先序遍历二叉树。遇到一条父子边，就把两端的结点值互相加入邻接表 $g$。结点值互不相同，可以直接作为图的键。

再从 $start$ 做一次显式栈上的后序遍历。进入结点时压入退出标记以及除父结点以外的邻接点；退出时，当前深度取这些邻接点深度的最大值再加一。$start$ 的深度即为答案。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为二叉树的节点个数。

<!-- tabs:start -->

#### Python3

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def amountOfTime(self, root: Optional[TreeNode], start: int) -> int:
        g = defaultdict(list)
        stk = [(root, None)]
        while stk:
            node, fa = stk.pop()
            if node is None:
                continue
            if fa:
                g[node.val].append(fa.val)
                g[fa.val].append(node.val)
            stk.append((node.right, node))
            stk.append((node.left, node))

        dist = {}
        walk = [(start, -1, 0)]
        while walk:
            node, fa, state = walk.pop()
            nxts = g[node]
            if state == 0:
                walk.append((node, fa, 1))
                for nxt in reversed(nxts):
                    if nxt != fa:
                        walk.append((nxt, node, 0))
                continue
            best = 0
            for nxt in nxts:
                if nxt != fa:
                    best = max(best, 1 + dist[nxt])
            dist[node] = best
        return dist[start]
```

#### Java

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private static class Frame {
        TreeNode node;
        TreeNode fa;

        Frame(TreeNode node, TreeNode fa) {
            this.node = node;
            this.fa = fa;
        }
    }

    public int amountOfTime(TreeNode root, int start) {
        Map<Integer, List<Integer>> g = new HashMap<>();
        Deque<Frame> stk = new ArrayDeque<>();
        if (root != null) {
            stk.push(new Frame(root, null));
        }
        while (!stk.isEmpty()) {
            Frame cur = stk.pop();
            TreeNode node = cur.node;
            TreeNode fa = cur.fa;
            if (fa != null) {
                g.computeIfAbsent(node.val, k -> new ArrayList<>()).add(fa.val);
                g.computeIfAbsent(fa.val, k -> new ArrayList<>()).add(node.val);
            }
            if (node.right != null) {
                stk.push(new Frame(node.right, node));
            }
            if (node.left != null) {
                stk.push(new Frame(node.left, node));
            }
        }
        Map<Integer, Integer> dist = new HashMap<>();
        Deque<int[]> walk = new ArrayDeque<>();
        walk.push(new int[] {start, -1, 0});
        while (!walk.isEmpty()) {
            int[] cur = walk.pop();
            int node = cur[0], fa = cur[1], state = cur[2];
            List<Integer> nxts = g.getOrDefault(node, List.of());
            if (state == 0) {
                walk.push(new int[] {node, fa, 1});
                for (int i = nxts.size() - 1; i >= 0; --i) {
                    int nxt = nxts.get(i);
                    if (nxt != fa) {
                        walk.push(new int[] {nxt, node, 0});
                    }
                }
            } else {
                int best = 0;
                for (int nxt : nxts) {
                    if (nxt != fa) {
                        best = Math.max(best, 1 + dist.get(nxt));
                    }
                }
                dist.put(node, best);
            }
        }
        return dist.get(start);
    }
}
```

#### C++

```cpp
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    int amountOfTime(TreeNode* root, int start) {
        unordered_map<int, vector<int>> g;
        vector<pair<TreeNode*, TreeNode*>> stk{{root, nullptr}};
        while (!stk.empty()) {
            auto [node, fa] = stk.back();
            stk.pop_back();
            if (!node) {
                continue;
            }
            if (fa) {
                g[node->val].push_back(fa->val);
                g[fa->val].push_back(node->val);
            }
            stk.emplace_back(node->right, node);
            stk.emplace_back(node->left, node);
        }
        unordered_map<int, int> dist;
        vector<tuple<int, int, int>> walk{{start, -1, 0}};
        while (!walk.empty()) {
            auto [node, fa, state] = walk.back();
            walk.pop_back();
            auto& nxts = g[node];
            if (state == 0) {
                walk.emplace_back(node, fa, 1);
                for (int i = (int) nxts.size() - 1; i >= 0; --i) {
                    int nxt = nxts[i];
                    if (nxt != fa) {
                        walk.emplace_back(nxt, node, 0);
                    }
                }
            } else {
                int best = 0;
                for (int nxt : nxts) {
                    if (nxt != fa) {
                        best = max(best, 1 + dist[nxt]);
                    }
                }
                dist[node] = best;
            }
        }
        return dist[start];
    }
};
```

#### Go

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func amountOfTime(root *TreeNode, start int) int {
	g := map[int][]int{}
	type frame struct {
		node, fa *TreeNode
	}
	stk := []frame{{root, nil}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		node, fa := cur.node, cur.fa
		if node == nil {
			continue
		}
		if fa != nil {
			g[node.Val] = append(g[node.Val], fa.Val)
			g[fa.Val] = append(g[fa.Val], node.Val)
		}
		stk = append(stk, frame{node.Right, node}, frame{node.Left, node})
	}
	dist := map[int]int{}
	type step struct {
		node, fa, state int
	}
	walk := []step{{start, -1, 0}}
	for len(walk) > 0 {
		cur := walk[len(walk)-1]
		walk = walk[:len(walk)-1]
		node, fa, state := cur.node, cur.fa, cur.state
		nxts := g[node]
		if state == 0 {
			walk = append(walk, step{node, fa, 1})
			for i := len(nxts) - 1; i >= 0; i-- {
				if nxt := nxts[i]; nxt != fa {
					walk = append(walk, step{nxt, node, 0})
				}
			}
			continue
		}
		best := 0
		for _, nxt := range nxts {
			if nxt != fa {
				best = max(best, 1+dist[nxt])
			}
		}
		dist[node] = best
	}
	return dist[start]
}
```

#### TypeScript

```ts
/**
 * Definition for a binary tree node.
 * class TreeNode {
 *     val: number
 *     left: TreeNode | null
 *     right: TreeNode | null
 *     constructor(val?: number, left?: TreeNode | null, right?: TreeNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.left = (left===undefined ? null : left)
 *         this.right = (right===undefined ? null : right)
 *     }
 * }
 */

function amountOfTime(root: TreeNode | null, start: number): number {
    const g: Map<number, number[]> = new Map();
    const stk: [TreeNode | null, TreeNode | null][] = [[root, null]];
    while (stk.length) {
        const [node, fa] = stk.pop()!;
        if (!node) {
            continue;
        }
        if (fa) {
            if (!g.has(node.val)) {
                g.set(node.val, []);
            }
            g.get(node.val)!.push(fa.val);
            if (!g.has(fa.val)) {
                g.set(fa.val, []);
            }
            g.get(fa.val)!.push(node.val);
        }
        stk.push([node.right, node]);
        stk.push([node.left, node]);
    }
    const dist = new Map<number, number>();
    const walk: [number, number, number][] = [[start, -1, 0]];
    while (walk.length) {
        const [node, fa, state] = walk.pop()!;
        const nxts = g.get(node) || [];
        if (state === 0) {
            walk.push([node, fa, 1]);
            for (let i = nxts.length - 1; i >= 0; --i) {
                const nxt = nxts[i];
                if (nxt !== fa) {
                    walk.push([nxt, node, 0]);
                }
            }
            continue;
        }
        let best = 0;
        for (const nxt of nxts) {
            if (nxt !== fa) {
                best = Math.max(best, 1 + dist.get(nxt)!);
            }
        }
        dist.set(node, best);
    }
    return dist.get(start)!;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
