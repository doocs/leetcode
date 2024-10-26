---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2458.Height%20of%20Binary%20Tree%20After%20Subtree%20Removal%20Queries/README_EN.md
rating: 2298
source: Weekly Contest 317 Q4
tags:
    - Tree
    - Depth-First Search
    - Breadth-First Search
    - Array
    - Binary Tree
---

<!-- problem:start -->

# [2458. Height of Binary Tree After Subtree Removal Queries](https://leetcode.com/problems/height-of-binary-tree-after-subtree-removal-queries)

[中文文档](/solution/2400-2499/2458.Height%20of%20Binary%20Tree%20After%20Subtree%20Removal%20Queries/README.md)

## Description

<!-- description:start -->

<p>You are given the <code>root</code> of a <strong>binary tree</strong> with <code>n</code> nodes. Each node is assigned a unique value from <code>1</code> to <code>n</code>. You are also given an array <code>queries</code> of size <code>m</code>.</p>

<p>You have to perform <code>m</code> <strong>independent</strong> queries on the tree where in the <code>i<sup>th</sup></code> query you do the following:</p>

<ul>
	<li><strong>Remove</strong> the subtree rooted at the node with the value <code>queries[i]</code> from the tree. It is <strong>guaranteed</strong> that <code>queries[i]</code> will <strong>not</strong> be equal to the value of the root.</li>
</ul>

<p>Return <em>an array </em><code>answer</code><em> of size </em><code>m</code><em> where </em><code>answer[i]</code><em> is the height of the tree after performing the </em><code>i<sup>th</sup></code><em> query</em>.</p>

<p><strong>Note</strong>:</p>

<ul>
	<li>The queries are independent, so the tree returns to its <strong>initial</strong> state after each query.</li>
	<li>The height of a tree is the <strong>number of edges in the longest simple path</strong> from the root to some node in the tree.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2458.Height%20of%20Binary%20Tree%20After%20Subtree%20Removal%20Queries/images/binaryytreeedrawio-1.png" style="width: 495px; height: 281px;" />
<pre>
<strong>Input:</strong> root = [1,3,4,2,null,6,5,null,null,null,null,null,7], queries = [4]
<strong>Output:</strong> [2]
<strong>Explanation:</strong> The diagram above shows the tree after removing the subtree rooted at node with value 4.
The height of the tree is 2 (The path 1 -&gt; 3 -&gt; 2).
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2458.Height%20of%20Binary%20Tree%20After%20Subtree%20Removal%20Queries/images/binaryytreeedrawio-2.png" style="width: 301px; height: 284px;" />
<pre>
<strong>Input:</strong> root = [5,8,9,2,1,3,7,4,6], queries = [3,2,4,8]
<strong>Output:</strong> [3,2,3,2]
<strong>Explanation:</strong> We have the following queries:
- Removing the subtree rooted at node with value 3. The height of the tree becomes 3 (The path 5 -&gt; 8 -&gt; 2 -&gt; 4).
- Removing the subtree rooted at node with value 2. The height of the tree becomes 2 (The path 5 -&gt; 8 -&gt; 1).
- Removing the subtree rooted at node with value 4. The height of the tree becomes 3 (The path 5 -&gt; 8 -&gt; 2 -&gt; 6).
- Removing the subtree rooted at node with value 8. The height of the tree becomes 2 (The path 5 -&gt; 9 -&gt; 3).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is <code>n</code>.</li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= Node.val &lt;= n</code></li>
	<li>All the values in the tree are <strong>unique</strong>.</li>
	<li><code>m == queries.length</code></li>
	<li><code>1 &lt;= m &lt;= min(n, 10<sup>4</sup>)</code></li>
	<li><code>1 &lt;= queries[i] &lt;= n</code></li>
	<li><code>queries[i] != root.val</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Two DFS Traversals

First, we perform a DFS traversal to determine the depth of each node, which we store in a hash table $d$, where $d[x]$ represents the depth of node $x$.

Then we design a function $dfs(root, depth, rest)$, where:

-   `root` represents the current node;
-   `depth` represents the depth of the current node;
-   `rest` represents the height of the tree after deleting the current node.

The function's computation logic is as follows:

If the node is null, return directly. Otherwise, we increment `depth` by $1$, and then store `rest` in `res`.

Next, we recursively traverse the left and right subtrees.

Before recursing into the left subtree, we calculate the depth from the root node to the deepest node in the current node's right subtree, i.e., $depth+d[root.right]$, and then compare it with `rest`, taking the larger value as the `rest` for the left subtree.

Before recursing into the right subtree, we calculate the depth from the root node to the deepest node in the current node's left subtree, i.e., $depth+d[root.left]$, and then compare it with `rest`, taking the larger value as the `rest` for the right subtree.

Finally, we return the result values corresponding to each query node.

The time complexity is $O(n+m)$, and the space complexity is $O(n)$. Here, $n$ and $m$ are the number of nodes in the tree and the number of queries, respectively.

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
    def treeQueries(self, root: Optional[TreeNode], queries: List[int]) -> List[int]:
        def f(root):
            if root is None:
                return 0
            l, r = f(root.left), f(root.right)
            d[root] = 1 + max(l, r)
            return d[root]

        def dfs(root, depth, rest):
            if root is None:
                return
            depth += 1
            res[root.val] = rest
            dfs(root.left, depth, max(rest, depth + d[root.right]))
            dfs(root.right, depth, max(rest, depth + d[root.left]))

        d = defaultdict(int)
        f(root)
        res = [0] * (len(d) + 1)
        dfs(root, -1, 0)
        return [res[v] for v in queries]
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
    private Map<TreeNode, Integer> d = new HashMap<>();
    private int[] res;

    public int[] treeQueries(TreeNode root, int[] queries) {
        f(root);
        res = new int[d.size() + 1];
        d.put(null, 0);
        dfs(root, -1, 0);
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; ++i) {
            ans[i] = res[queries[i]];
        }
        return ans;
    }

    private void dfs(TreeNode root, int depth, int rest) {
        if (root == null) {
            return;
        }
        ++depth;
        res[root.val] = rest;
        dfs(root.left, depth, Math.max(rest, depth + d.get(root.right)));
        dfs(root.right, depth, Math.max(rest, depth + d.get(root.left)));
    }

    private int f(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = f(root.left), r = f(root.right);
        d.put(root, 1 + Math.max(l, r));
        return d.get(root);
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
    vector<int> treeQueries(TreeNode* root, vector<int>& queries) {
        unordered_map<TreeNode*, int> d;
        function<int(TreeNode*)> f = [&](TreeNode* root) -> int {
            if (!root) return 0;
            int l = f(root->left), r = f(root->right);
            d[root] = 1 + max(l, r);
            return d[root];
        };
        f(root);
        vector<int> res(d.size() + 1);
        function<void(TreeNode*, int, int)> dfs = [&](TreeNode* root, int depth, int rest) {
            if (!root) return;
            ++depth;
            res[root->val] = rest;
            dfs(root->left, depth, max(rest, depth + d[root->right]));
            dfs(root->right, depth, max(rest, depth + d[root->left]));
        };
        dfs(root, -1, 0);
        vector<int> ans;
        for (int v : queries) ans.emplace_back(res[v]);
        return ans;
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
func treeQueries(root *TreeNode, queries []int) (ans []int) {
	d := map[*TreeNode]int{}
	var f func(*TreeNode) int
	f = func(root *TreeNode) int {
		if root == nil {
			return 0
		}
		l, r := f(root.Left), f(root.Right)
		d[root] = 1 + max(l, r)
		return d[root]
	}
	f(root)
	res := make([]int, len(d)+1)
	var dfs func(*TreeNode, int, int)
	dfs = func(root *TreeNode, depth, rest int) {
		if root == nil {
			return
		}
		depth++
		res[root.Val] = rest
		dfs(root.Left, depth, max(rest, depth+d[root.Right]))
		dfs(root.Right, depth, max(rest, depth+d[root.Left]))
	}
	dfs(root, -1, 0)
	for _, v := range queries {
		ans = append(ans, res[v])
	}
	return
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: One DFS + Sorting

<!-- tabs:start -->

#### TypeScript

```ts
function treeQueries(root: TreeNode | null, queries: number[]): number[] {
    const ans: number[] = [];
    const levels: Map<number, [number, number][]> = new Map();
    const valToLevel = new Map<number, number>();

    const dfs = (node: TreeNode | null, level = 0): number => {
        if (!node) return level - 1;

        const max = Math.max(dfs(node.left, level + 1), dfs(node.right, level + 1));

        if (!levels.has(level)) {
            levels.set(level, []);
        }
        levels.get(level)?.push([max, node.val]);
        valToLevel.set(node.val, level);

        return max;
    };

    dfs(root, 0);

    for (const [_, l] of levels) {
        l.sort(([a], [b]) => b - a);
    }

    for (const q of queries) {
        const level = valToLevel.get(q)!;
        const maxes = levels.get(level)!;

        if (maxes.length === 1) {
            ans.push(level - 1);
        } else {
            const [val0, max0, max1] = [maxes[0][1], maxes[0][0], maxes[1][0]];
            const max = val0 === q ? max1 : max0;
            ans.push(max);
        }
    }

    return ans;
}
```

#### JavaScript

```js
function treeQueries(root, queries) {
    const ans = [];
    const levels = new Map();
    const valToLevel = new Map();

    const dfs = (node, level = 0) => {
        if (!node) return level - 1;

        const max = Math.max(dfs(node.left, level + 1), dfs(node.right, level + 1));

        if (!levels.has(level)) {
            levels.set(level, []);
        }
        levels.get(level)?.push([max, node.val]);
        valToLevel.set(node.val, level);

        return max;
    };

    dfs(root, 0);

    for (const [_, l] of levels) {
        l.sort(([a], [b]) => b - a);
    }

    for (const q of queries) {
        const level = valToLevel.get(q);
        const maxes = levels.get(level);

        if (maxes.length === 1) {
            ans.push(level - 1);
        } else {
            const [val0, max0, max1] = [maxes[0][1], maxes[0][0], maxes[1][0]];
            const max = val0 === q ? max1 : max0;
            ans.push(max);
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
