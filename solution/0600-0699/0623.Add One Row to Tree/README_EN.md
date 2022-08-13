# [623. Add One Row to Tree](https://leetcode.com/problems/add-one-row-to-tree)

[中文文档](/solution/0600-0699/0623.Add%20One%20Row%20to%20Tree/README.md)

## Description

<p>Given the <code>root</code> of a binary tree and two integers <code>val</code> and <code>depth</code>, add a row of nodes with value <code>val</code> at the given depth <code>depth</code>.</p>

<p>Note that the <code>root</code> node is at depth <code>1</code>.</p>

<p>The adding rule is:</p>

<ul>
	<li>Given the integer <code>depth</code>, for each not null tree node <code>cur</code> at the depth <code>depth - 1</code>, create two tree nodes with value <code>val</code> as <code>cur</code>&#39;s left subtree root and right subtree root.</li>
	<li><code>cur</code>&#39;s original left subtree should be the left subtree of the new left subtree root.</li>
	<li><code>cur</code>&#39;s original right subtree should be the right subtree of the new right subtree root.</li>
	<li>If <code>depth == 1</code> that means there is no depth <code>depth - 1</code> at all, then create a tree node with value <code>val</code> as the new root of the whole original tree, and the original tree is the new root&#39;s left subtree.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0623.Add%20One%20Row%20to%20Tree/images/addrow-tree.jpg" style="width: 500px; height: 231px;" />
<pre>
<strong>Input:</strong> root = [4,2,6,3,1,5], val = 1, depth = 2
<strong>Output:</strong> [4,1,1,2,null,null,6,3,1,5]
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0623.Add%20One%20Row%20to%20Tree/images/add2-tree.jpg" style="width: 500px; height: 277px;" />
<pre>
<strong>Input:</strong> root = [4,2,null,3,1], val = 1, depth = 3
<strong>Output:</strong> [4,2,null,1,1,3,null,null,1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[1, 10<sup>4</sup>]</code>.</li>
	<li>The depth of the tree is in the range <code>[1, 10<sup>4</sup>]</code>.</li>
	<li><code>-100 &lt;= Node.val &lt;= 100</code></li>
	<li><code>-10<sup>5</sup> &lt;= val &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= depth &lt;= the depth of tree + 1</code></li>
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
    def addOneRow(
        self, root: Optional[TreeNode], val: int, depth: int
    ) -> Optional[TreeNode]:
        def dfs(root, d):
            if root is None:
                return
            if d == depth - 1:
                root.left = TreeNode(val, root.left, None)
                root.right = TreeNode(val, None, root.right)
                return
            dfs(root.left, d + 1)
            dfs(root.right, d + 1)

        if depth == 1:
            return TreeNode(val, root)
        dfs(root, 1)
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
    def addOneRow(self, root: Optional[TreeNode], val: int, depth: int) -> Optional[TreeNode]:
        if depth == 1:
            return TreeNode(val, root)
        q = deque([root])
        i = 0
        while q:
            i += 1
            for _ in range(len(q)):
                node = q.popleft()
                if node.left:
                    q.append(node.left)
                if node.right:
                    q.append(node.right)
                if i == depth - 1:
                    node.left = TreeNode(val, node.left, None)
                    node.right = TreeNode(val, None, node.right)
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
    private int val;
    private int depth;

    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            return new TreeNode(val, root, null);
        }
        this.val = val;
        this.depth = depth;
        dfs(root, 1);
        return root;
    }

    private void dfs(TreeNode root, int d) {
        if (root == null) {
            return;
        }
        if (d == depth - 1) {
            TreeNode l = new TreeNode(val, root.left, null);
            TreeNode r = new TreeNode(val, null, root.right);
            root.left = l;
            root.right = r;
            return;
        }
        dfs(root.left, d + 1);
        dfs(root.right, d + 1);
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
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            return new TreeNode(val, root, null);
        }
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        int i = 0;
        while (!q.isEmpty()) {
            ++i;
            for (int k = q.size(); k > 0; --k) {
                TreeNode node = q.pollFirst();
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
                if (i == depth - 1) {
                    node.left = new TreeNode(val, node.left, null);
                    node.right = new TreeNode(val, null, node.right);
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
    int val;
    int depth;

    TreeNode* addOneRow(TreeNode* root, int val, int depth) {
        if (depth == 1) return new TreeNode(val, root, nullptr);
        this->val = val;
        this->depth = depth;
        dfs(root, 1);
        return root;
    }

    void dfs(TreeNode* root, int d) {
        if (!root) return;
        if (d == depth - 1) {
            auto l = new TreeNode(val, root->left, nullptr);
            auto r = new TreeNode(val, nullptr, root->right);
            root->left = l;
            root->right = r;
            return;
        }
        dfs(root->left, d + 1);
        dfs(root->right, d + 1);
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
    TreeNode* addOneRow(TreeNode* root, int val, int depth) {
        if (depth == 1) return new TreeNode(val, root, nullptr);
        queue<TreeNode*> q{{root}};
        int i = 0;
        while (!q.empty())
        {
            ++i;
            for (int k = q.size(); k; --k)
            {
                TreeNode* node = q.front();
                q.pop();
                if (node->left) q.push(node->left);
                if (node->right) q.push(node->right);
                if (i == depth - 1)
                {
                    node->left = new TreeNode(val, node->left, nullptr);
                    node->right = new TreeNode(val, nullptr, node->right);
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
func addOneRow(root *TreeNode, val int, depth int) *TreeNode {
	if depth == 1 {
		return &TreeNode{Val: val, Left: root}
	}
	var dfs func(root *TreeNode, d int)
	dfs = func(root *TreeNode, d int) {
		if root == nil {
			return
		}
		if d == depth-1 {
			l, r := &TreeNode{Val: val, Left: root.Left}, &TreeNode{Val: val, Right: root.Right}
			root.Left, root.Right = l, r
			return
		}
		dfs(root.Left, d+1)
		dfs(root.Right, d+1)
	}
	dfs(root, 1)
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
func addOneRow(root *TreeNode, val int, depth int) *TreeNode {
	if depth == 1 {
		return &TreeNode{val, root, nil}
	}
	q := []*TreeNode{root}
	i := 0
	for len(q) > 0 {
		i++
		for k := len(q); k > 0; k-- {
			node := q[0]
			q = q[1:]
			if node.Left != nil {
				q = append(q, node.Left)
			}
			if node.Right != nil {
				q = append(q, node.Right)
			}
			if i == depth-1 {
				node.Left = &TreeNode{val, node.Left, nil}
				node.Right = &TreeNode{val, nil, node.Right}
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

function addOneRow(
    root: TreeNode | null,
    val: number,
    depth: number,
): TreeNode | null {
    function dfs(root, d) {
        if (!root) {
            return;
        }
        if (d == depth - 1) {
            root.left = new TreeNode(val, root.left, null);
            root.right = new TreeNode(val, null, root.right);
            return;
        }
        dfs(root.left, d + 1);
        dfs(root.right, d + 1);
    }
    if (depth == 1) {
        return new TreeNode(val, root);
    }
    dfs(root, 1);
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

function addOneRow(
    root: TreeNode | null,
    val: number,
    depth: number,
): TreeNode | null {
    if (depth === 1) {
        return new TreeNode(val, root);
    }

    const queue = [root];
    for (let i = 1; i < depth - 1; i++) {
        const n = queue.length;
        for (let j = 0; j < n; j++) {
            const { left, right } = queue.shift();
            left && queue.push(left);
            right && queue.push(right);
        }
    }
    for (const node of queue) {
        node.left = new TreeNode(val, node.left);
        node.right = new TreeNode(val, null, node.right);
    }
    return root;
}
```

### **...**

```

```

<!-- tabs:end -->
