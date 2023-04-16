# [2641. Cousins in Binary Tree II](https://leetcode.com/problems/cousins-in-binary-tree-ii)

[中文文档](/solution/2600-2699/2641.Cousins%20in%20Binary%20Tree%20II/README.md)

## Description

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

## Solutions

<!-- tabs:start -->

### **Python3**

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def replaceValueInTree(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        def dfs1(root, d):
            if root is None:
                return
            if len(s) <= d:
                s.append(0)
            s[d] += root.val
            dfs1(root.left, d + 1)
            dfs1(root.right, d + 1)

        def dfs2(root, d):
            if root is None:
                return
            t = (root.left.val if root.left else 0) + \
                (root.right.val if root.right else 0)
            if root.left:
                root.left.val = s[d] - t
            if root.right:
                root.right.val = s[d] - t
            dfs2(root.left, d + 1)
            dfs2(root.right, d + 1)

        s = []
        dfs1(root, 0)
        root.val = 0
        dfs2(root, 1)
        return root
```

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
            s = 0
            p = q
            q = []
            for node in p:
                if node.left:
                    q.append(node.left)
                    s += node.left.val
                if node.right:
                    q.append(node.right)
                    s += node.right.val
            for node in p:
                t = (node.left.val if node.left else 0) + (node.right.val if node.right else 0)
                if node.left:
                    node.left.val = s - t
                if node.right:
                    node.right.val = s - t
        return root
```

### **Java**

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
        dfs2(root, 1);
        return root;
    }

    private void dfs1(TreeNode root, int d) {
        if (root == null) {
            return;
        }
        if (s.size() <= d) {
            s.add(0);
        }
        s.set(d, s.get(d) + root.val);
        dfs1(root.left, d + 1);
        dfs1(root.right, d + 1);
    }

    private void dfs2(TreeNode root, int d) {
        if (root == null) {
            return;
        }
        int l = root.left == null ? 0 : root.left.val;
        int r = root.right == null ? 0 : root.right.val;
        if (root.left != null) {
            root.left.val = s.get(d) - l - r;
        }
        if (root.right != null) {
            root.right.val = s.get(d) - l - r;
        }
        dfs2(root.left, d + 1);
        dfs2(root.right, d + 1);
    }
}
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
            List<TreeNode> p = q;
            q = new ArrayList<>();
            int s = 0;
            for (TreeNode node : p) {
                if (node.left != null) {
                    q.add(node.left);
                    s += node.left.val;
                }
                if (node.right != null) {
                    q.add(node.right);
                    s += node.right.val;
                }
            }
            for (TreeNode node : p) {
                int t = (node.left == null ? 0 : node.left.val) + (node.right == null ? 0 : node.right.val);
                if (node.left != null) {
                    node.left.val = s - t;
                }
                if (node.right != null) {
                    node.right.val = s - t;
                }
            }
        }
        return root;
    }
}
```

### **C++**

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
        vector<int> s;
        function<void(TreeNode*, int)> dfs1 = [&](TreeNode* root, int d) {
            if (!root) {
                return;
            }
            if (s.size() <= d) {
                s.push_back(0);
            }
            s[d] += root->val;
            dfs1(root->left, d + 1);
            dfs1(root->right, d + 1);
        };
        function<void(TreeNode*, int)> dfs2 = [&](TreeNode* root, int d) {
            if (!root) {
                return;
            }
            int l = root->left ? root->left->val : 0;
            int r = root->right ? root->right->val : 0;
            if (root->left) {
                root->left->val = s[d] - l - r;
            }
            if (root->right) {
                root->right->val = s[d] - l - r;
            }
            dfs2(root->left, d + 1);
            dfs2(root->right, d + 1);
        };
        dfs1(root, 0);
        root->val = 0;
        dfs2(root, 1);
        return root;
    }
};
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
        vector<TreeNode*> q;
        q.emplace_back(root);
        while (!q.empty()) {
            vector<TreeNode*> p = q;
            q.clear();
            int s = 0;
            for (TreeNode* node : p) {
                if (node->left) {
                    q.emplace_back(node->left);
                    s += node->left->val;
                }
                if (node->right) {
                    q.emplace_back(node->right);
                    s += node->right->val;
                }
            }
            for (TreeNode* node : p) {
                int t = (node->left ? node->left->val : 0) + (node->right ? node->right->val : 0);
                if (node->left) {
                    node->left->val = s - t;
                }
                if (node->right) {
                    node->right->val = s - t;
                }
            }
        }
        return root;
    }
};
```

### **Go**

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
	dfs1 = func(root *TreeNode, d int) {
		if root == nil {
			return
		}
		if len(s) <= d {
			s = append(s, 0)
		}
		s[d] += root.Val
		dfs1(root.Left, d+1)
		dfs1(root.Right, d+1)
	}
	var dfs2 func(*TreeNode, int)
	dfs2 = func(root *TreeNode, d int) {
		if root == nil {
			return
		}
		l, r := 0, 0
		if root.Left != nil {
			l = root.Left.Val
		}
		if root.Right != nil {
			r = root.Right.Val
		}
		if root.Left != nil {
			root.Left.Val = s[d] - l - r
		}
		if root.Right != nil {
			root.Right.Val = s[d] - l - r
		}
		dfs2(root.Left, d+1)
		dfs2(root.Right, d+1)
	}
	dfs1(root, 0)
	root.Val = 0
	dfs2(root, 1)
	return root
}
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
		p := q
		q = []*TreeNode{}
		s := 0
		for _, node := range p {
			if node.Left != nil {
				q = append(q, node.Left)
				s += node.Left.Val
			}
			if node.Right != nil {
				q = append(q, node.Right)
				s += node.Right.Val
			}
		}
		for _, node := range p {
			t := 0
			if node.Left != nil {
				t += node.Left.Val
			}
			if node.Right != nil {
				t += node.Right.Val
			}
			if node.Left != nil {
				node.Left.Val = s - t
			}
			if node.Right != nil {
				node.Right.Val = s - t
			}
		}
	}
	return root
}
```

### **TypeScript**

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
    const dfs1 = (root: TreeNode | null, d: number): void => {
        if (!root) {
            return;
        }
        if (s.length <= d) {
            s.push(0);
        }
        s[d] += root.val;
        dfs1(root.left, d + 1);
        dfs1(root.right, d + 1);
    };
    const dfs2 = (root: TreeNode | null, d: number): void => {
        if (!root) {
            return;
        }
        const t = (root.left?.val ?? 0) + (root.right?.val ?? 0);
        if (root.left) {
            root.left.val = s[d] - t;
        }
        if (root.right) {
            root.right.val = s[d] - t;
        }
        dfs2(root.left, d + 1);
        dfs2(root.right, d + 1);
    };
    dfs1(root, 0);
    root.val = 0;
    dfs2(root, 1);
    return root;
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
    let q: TreeNode[] = [root];
    while (q.length) {
        const p: TreeNode[] = q;
        q = [];
        let s: number = 0;
        for (const { left, right } of p) {
            if (left) {
                q.push(left);
                s += left.val;
            }
            if (right) {
                q.push(right);
                s += right.val;
            }
        }
        for (const { left, right } of p) {
            const t: number = (left?.val ?? 0) + (right?.val ?? 0);
            if (left) {
                left.val = s - t;
            }
            if (right) {
                right.val = s - t;
            }
        }
    }
    return root;
}
```

### **...**

```

```

<!-- tabs:end -->
