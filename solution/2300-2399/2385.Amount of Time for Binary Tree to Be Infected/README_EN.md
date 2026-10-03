---
comments: true
difficulty: Medium
rating: 1711
source: Weekly Contest 307 Q3
tags:
    - Tree
    - Depth-First Search
    - Breadth-First Search
    - Hash Table
    - Binary Tree
---

<!-- problem:start -->

# [2385. Amount of Time for Binary Tree to Be Infected](https://leetcode.com/problems/amount-of-time-for-binary-tree-to-be-infected)

[中文文档](/solution/2300-2399/2385.Amount%20of%20Time%20for%20Binary%20Tree%20to%20Be%20Infected/README.md)

## Description

<!-- description:start -->

<p>You are given the <code>root</code> of a binary tree with <strong>unique</strong> values, and an integer <code>start</code>. At minute <code>0</code>, an <strong>infection</strong> starts from the node with value <code>start</code>.</p>

<p>Each minute, a node becomes infected if:</p>

<ul>
	<li>The node is currently uninfected.</li>
	<li>The node is adjacent to an infected node.</li>
</ul>

<p>Return <em>the number of minutes needed for the entire tree to be infected.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2385.Amount%20of%20Time%20for%20Binary%20Tree%20to%20Be%20Infected/images/image-20220625231744-1.png" style="width: 400px; height: 306px;" />
<pre>
<strong>Input:</strong> root = [1,5,3,null,4,10,6,9,2], start = 3
<strong>Output:</strong> 4
<strong>Explanation:</strong> The following nodes are infected during:
- Minute 0: Node 3
- Minute 1: Nodes 1, 10 and 6
- Minute 2: Node 5
- Minute 3: Node 4
- Minute 4: Nodes 9 and 2
It takes 4 minutes for the whole tree to be infected so we return 4.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2385.Amount%20of%20Time%20for%20Binary%20Tree%20to%20Be%20Infected/images/image-20220625231812-2.png" style="width: 75px; height: 66px;" />
<pre>
<strong>Input:</strong> root = [1], start = 1
<strong>Output:</strong> 0
<strong>Explanation:</strong> At minute 0, the only node in the tree is infected so we return 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 10<sup>5</sup>]</code>.</li>
	<li><code>1 &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
	<li>Each node has a <strong>unique</strong> value.</li>
	<li>A node with a value of <code>start</code> exists in the tree.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Two DFS

<!-- thinking:start -->

> **Thinking**
>
> Infection travels along tree edges; the time is the eccentricity of $start$. Up to $10^5$ nodes, so parent links must be explicit.
>
> The first DFS builds an undirected adjacency list; the second DFS from $start$ returns the farthest depth. Both are linear.

<!-- thinking:end -->

First, we build a graph through one DFS, and get an adjacency list $g$, where $g[node]$ represents all nodes connected to the node $node$.

Then, we use $start$ as the starting point, and search the entire tree through DFS to find the farthest distance, which is the answer.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the number of nodes in the binary tree.

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

### Solution 2: Explicit Stack

<!-- thinking:start -->

> **Thinking**
>
> Infection spreads along parent and child edges, so the minutes needed are the farthest distance from $start$. Recording those edges and then returning a depth is correct on a short tree.
>
> The tree can contain $10^5$ nodes. A left chain makes both walks recurse once per node, which exceeds the call stack.
>
> Each walk only needs the current node and its parent, and a node's depth is one plus the maximum depth of its other neighbors. Those values can sit beside an explicit stack.
>
> The first stack records every parent edge in an undirected adjacency list, using the distinct node values as keys. The second stack enters a node, pushes its neighbors, and on exit writes one plus the maximum neighbor depth. The depth stored for $start$ is the answer.

<!-- thinking:end -->

We traverse the binary tree with an explicit stack and record each parent-child edge in an undirected adjacency list $g$. Node values are distinct, so they are the graph keys.

A second explicit stack then walks outward from $start$. On entry it pushes an exit marker and every neighbor except the parent; on exit the depth is one plus the maximum depth of those neighbors. The depth stored for $start$ is the answer.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the number of nodes in the binary tree.

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
