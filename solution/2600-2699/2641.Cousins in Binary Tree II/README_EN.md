---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2641.Cousins%20in%20Binary%20Tree%20II/README_EN.md
rating: 1676
source: Biweekly Contest 102 Q3
tags:
    - Tree
    - Depth-First Search
    - Breadth-First Search
    - Hash Table
    - Binary Tree
---

<!-- problem:start -->

# [2641. Cousins in Binary Tree II](https://leetcode.com/problems/cousins-in-binary-tree-ii)

[中文文档](/solution/2600-2699/2641.Cousins%20in%20Binary%20Tree%20II/README.md)

## Description

<!-- description:start -->

<p>Given the <code>root</code> of a binary tree, replace the value of each node in the tree with the <strong>sum of all its cousins&#39; values</strong>.</p>

<p>Two nodes of a binary tree are <strong>cousins</strong> if they have the same depth with different parents.</p>

<p>Return <em>the </em><code>root</code><em> of the modified tree</em>.</p>

<p><strong>Note</strong> that the depth of a node is the number of edges in the path from the root node to it.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2641.Cousins%20in%20Binary%20Tree%20II/images/example11.png" style="width: 571px; height: 151px;" />
<pre>
<strong>Input:</strong> root = [5,4,9,1,10,null,7]
<strong>Output:</strong> [0,0,0,7,7,null,11]
<strong>Explanation:</strong> The diagram above shows the initial binary tree and the binary tree after changing the value of each node.
- Node with value 5 does not have any cousins so its sum is 0.
- Node with value 4 does not have any cousins so its sum is 0.
- Node with value 9 does not have any cousins so its sum is 0.
- Node with value 1 has a cousin with value 7 so its sum is 7.
- Node with value 10 has a cousin with value 7 so its sum is 7.
- Node with value 7 has cousins with values 1 and 10 so its sum is 11.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2641.Cousins%20in%20Binary%20Tree%20II/images/diagram33.png" style="width: 481px; height: 91px;" />
<pre>
<strong>Input:</strong> root = [3,1,2]
<strong>Output:</strong> [0,0,0]
<strong>Explanation:</strong> The diagram above shows the initial binary tree and the binary tree after changing the value of each node.
- Node with value 3 does not have any cousins so its sum is 0.
- Node with value 1 does not have any cousins so its sum is 0.
- Node with value 2 does not have any cousins so its sum is 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 10<sup>5</sup>]</code>.</li>
	<li><code>1 &lt;= Node.val &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Two DFS Traversals

We create a list $s$ to record the sum of the node values at each level of the binary tree, where $s[depth]$ represents the sum of the node values at the $depth$-th level (the root node is at level $0$).

Next, we perform a DFS traversal to calculate the values in the array $s$. Then, we perform another DFS traversal to update the values of each node's children. The value of a child node is equal to the sum of the node values at its level minus the value of the child node and its sibling nodes.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the number of nodes in the binary tree.

<!-- tabs:start -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def replaceValueInTree(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        def dfs1(root: Optional[TreeNode], depth: int):
            if root is None:
                return
            if len(s) <= depth:
                s.append(0)
            s[depth] += root.val
            dfs1(root.left, depth + 1)
            dfs1(root.right, depth + 1)

        def dfs2(root: Optional[TreeNode], depth: int):
            sub = (root.left.val if root.left else 0) + (
                root.right.val if root.right else 0
            )
            depth += 1
            if root.left:
                root.left.val = s[depth] - sub
                dfs2(root.left, depth)
            if root.right:
                root.right.val = s[depth] - sub
                dfs2(root.right, depth)

        s = []
        dfs1(root, 0)
        root.val = 0
        dfs2(root, 0)
        return root
```

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
    private List<Integer> s = new ArrayList<>();

    public TreeNode replaceValueInTree(TreeNode root) {
        dfs1(root, 0);
        root.val = 0;
        dfs2(root, 0);
        return root;
    }

    private void dfs1(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        if (s.size() <= depth) {
            s.add(0);
        }
        s.set(depth, s.get(depth) + root.val);
        dfs1(root.left, depth + 1);
        dfs1(root.right, depth + 1);
    }

    private void dfs2(TreeNode root, int depth) {
        int l = root.left == null ? 0 : root.left.val;
        int r = root.right == null ? 0 : root.right.val;
        int sub = l + r;
        ++depth;
        if (root.left != null) {
            root.left.val = s.get(depth) - sub;
            dfs2(root.left, depth);
        }
        if (root.right != null) {
            root.right.val = s.get(depth) - sub;
            dfs2(root.right, depth);
        }
    }
}
```

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
    TreeNode* replaceValueInTree(TreeNode* root) {
        memset(s, 0, sizeof(s));
        dfs1(root, 0);
        root->val = 0;
        dfs2(root, 0);
        return root;
    }

private:
    int s[100010];
    void dfs1(TreeNode* root, int depth) {
        if (!root) {
            return;
        }
        s[depth] += root->val;
        dfs1(root->left, depth + 1);
        dfs1(root->right, depth + 1);
    };

    void dfs2(TreeNode* root, int depth) {
        int l = root->left ? root->left->val : 0;
        int r = root->right ? root->right->val : 0;
        int sub = l + r;
        ++depth;
        if (root->left) {
            root->left->val = s[depth] - sub;
            dfs2(root->left, depth);
        }
        if (root->right) {
            root->right->val = s[depth] - sub;
            dfs2(root->right, depth);
        }
    };
};
```

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func replaceValueInTree(root *TreeNode) *TreeNode {
	s := []int{}
	var dfs1 func(*TreeNode, int)
	dfs1 = func(root *TreeNode, depth int) {
		if root == nil {
			return
		}
		if len(s) <= depth {
			s = append(s, 0)
		}
		s[depth] += root.Val
		dfs1(root.Left, depth+1)
		dfs1(root.Right, depth+1)
	}
	var dfs2 func(*TreeNode, int)
	dfs2 = func(root *TreeNode, depth int) {
		l, r := 0, 0
		if root.Left != nil {
			l = root.Left.Val
		}
		if root.Right != nil {
			r = root.Right.Val
		}
		sub := l + r
		depth++
		if root.Left != nil {
			root.Left.Val = s[depth] - sub
			dfs2(root.Left, depth)
		}
		if root.Right != nil {
			root.Right.Val = s[depth] - sub
			dfs2(root.Right, depth)
		}
	}
	dfs1(root, 0)
	root.Val = 0
	dfs2(root, 0)
	return root
}
```

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

function replaceValueInTree(root: TreeNode | null): TreeNode | null {
    const s: number[] = [];
    const dfs1 = (root: TreeNode | null, depth: number) => {
        if (!root) {
            return;
        }
        if (s.length <= depth) {
            s.push(0);
        }
        s[depth] += root.val;
        dfs1(root.left, depth + 1);
        dfs1(root.right, depth + 1);
    };
    const dfs2 = (root: TreeNode | null, depth: number) => {
        const sub = (root.left?.val || 0) + (root.right?.val || 0);
        ++depth;
        if (root.left) {
            root.left.val = s[depth] - sub;
            dfs2(root.left, depth);
        }
        if (root.right) {
            root.right.val = s[depth] - sub;
            dfs2(root.right, depth);
        }
    };
    dfs1(root, 0);
    root.val = 0;
    dfs2(root, 0);
    return root;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Breadth-First Search (BFS)

First, we update the root node's value to $0$, and use a queue $q$ to store all nodes at each level, initially enqueueing the root node.

Then, we traverse the queue, calculate the sum $s$ of all child nodes' values at each level, then calculate the sum $sub$ of each child node and its sibling nodes' values, and then update each child node's value to $s - sub$.

After the traversal ends, we return the root node.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the number of nodes in the binary tree.

<!-- tabs:start -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def replaceValueInTree(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        root.val = 0
        q = [root]
        while q:
            t = []
            s = 0
            for node in q:
                if node.left:
                    t.append(node.left)
                    s += node.left.val
                if node.right:
                    t.append(node.right)
                    s += node.right.val
            for node in q:
                sub = (node.left.val if node.left else 0) + (
                    node.right.val if node.right else 0
                )
                if node.left:
                    node.left.val = s - sub
                if node.right:
                    node.right.val = s - sub
            q = t
        return root
```

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
    public TreeNode replaceValueInTree(TreeNode root) {
        root.val = 0;
        List<TreeNode> q = List.of(root);
        while (!q.isEmpty()) {
            List<TreeNode> t = new ArrayList<>();
            int s = 0;
            for (TreeNode node : q) {
                if (node.left != null) {
                    t.add(node.left);
                    s += node.left.val;
                }
                if (node.right != null) {
                    t.add(node.right);
                    s += node.right.val;
                }
            }
            for (TreeNode node : q) {
                int sub = (node.left == null ? 0 : node.left.val)
                    + (node.right == null ? 0 : node.right.val);
                if (node.left != null) {
                    node.left.val = s - sub;
                }
                if (node.right != null) {
                    node.right.val = s - sub;
                }
            }
            q = t;
        }
        return root;
    }
}
```

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
    TreeNode* replaceValueInTree(TreeNode* root) {
        root->val = 0;
        vector<TreeNode*> q = {root};
        while (!q.empty()) {
            vector<TreeNode*> t;
            int s = 0;
            for (TreeNode* node : q) {
                if (node->left) {
                    t.emplace_back(node->left);
                    s += node->left->val;
                }
                if (node->right) {
                    t.emplace_back(node->right);
                    s += node->right->val;
                }
            }
            for (TreeNode* node : q) {
                int sub = (node->left ? node->left->val : 0) + (node->right ? node->right->val : 0);
                if (node->left) {
                    node->left->val = s - sub;
                }
                if (node->right) {
                    node->right->val = s - sub;
                }
            }
            q = move(t);
        }
        return root;
    }
};
```

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func replaceValueInTree(root *TreeNode) *TreeNode {
	root.Val = 0
	q := []*TreeNode{root}
	for len(q) > 0 {
		t := []*TreeNode{}
		s := 0
		for _, node := range q {
			if node.Left != nil {
				t = append(t, node.Left)
				s += node.Left.Val
			}
			if node.Right != nil {
				t = append(t, node.Right)
				s += node.Right.Val
			}
		}
		for _, node := range q {
			sub := 0
			if node.Left != nil {
				sub += node.Left.Val
			}
			if node.Right != nil {
				sub += node.Right.Val
			}
			if node.Left != nil {
				node.Left.Val = s - sub
			}
			if node.Right != nil {
				node.Right.Val = s - sub
			}
		}
		q = t
	}
	return root
}
```

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

function replaceValueInTree(root: TreeNode | null): TreeNode | null {
    root.val = 0;
    const q: TreeNode[] = [root];
    while (q.length > 0) {
        const t: TreeNode[] = [];
        let s = 0;
        for (const { left, right } of q) {
            if (left) {
                t.push(left);
                s += left.val;
            }
            if (right) {
                t.push(right);
                s += right.val;
            }
        }
        for (const { left, right } of q) {
            const sub = (left?.val || 0) + (right?.val || 0);
            if (left) {
                left.val = s - sub;
            }
            if (right) {
                right.val = s - sub;
            }
        }
        q.splice(0, q.length, ...t);
    }
    return root;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
