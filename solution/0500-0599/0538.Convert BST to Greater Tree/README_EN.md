---
comments: true
difficulty: Medium
tags:
    - Tree
    - Depth-First Search
    - Binary Search Tree
    - Binary Tree
---

<!-- problem:start -->

# [538. Convert BST to Greater Tree](https://leetcode.com/problems/convert-bst-to-greater-tree)

[中文文档](/solution/0500-0599/0538.Convert%20BST%20to%20Greater%20Tree/README.md)

## Description

<!-- description:start -->

<p>Given the <code>root</code> of a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus the sum of all keys greater than the original key in BST.</p>

<p>As a reminder, a <em>binary search tree</em> is a tree that satisfies these constraints:</p>

<ul>
	<li>The left subtree of a node contains only nodes with keys <strong>less than</strong> the node&#39;s key.</li>
	<li>The right subtree of a node contains only nodes with keys <strong>greater than</strong> the node&#39;s key.</li>
	<li>Both the left and right subtrees must also be binary search trees.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0538.Convert%20BST%20to%20Greater%20Tree/images/tree.png" style="width: 500px; height: 341px;" />
<pre>
<strong>Input:</strong> root = [4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
<strong>Output:</strong> [30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> root = [0,null,1]
<strong>Output:</strong> [1,null,1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[0, 10<sup>4</sup>]</code>.</li>
	<li><code>-10<sup>4</sup> &lt;= Node.val &lt;= 10<sup>4</sup></code></li>
	<li>All the values in the tree are <strong>unique</strong>.</li>
	<li><code>root</code> is guaranteed to be a valid binary search tree.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Note:</strong> This question is the same as 1038: <a href="https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/" target="_blank">https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/</a></p>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- thinking:start -->

> **Thinking**
>
> Each node should become the sum of all keys not smaller than itself. In a BST those keys are exactly the nodes already seen in reverse inorder.
>
> Walk right-root-left, accumulate into $s$, and write $s$ back. The right subtree is processed first, so larger keys are already included. One traversal.

<!-- thinking:end -->

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
    def convertBST(self, root: TreeNode) -> TreeNode:
        def dfs(root):
            nonlocal s
            if root is None:
                return
            dfs(root.right)
            s += root.val
            root.val = s
            dfs(root.left)

        s = 0
        dfs(root)
        return root
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
    private int s;

    public TreeNode convertBST(TreeNode root) {
        dfs(root);
        return root;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.right);
        s += root.val;
        root.val = s;
        dfs(root.left);
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
    int s = 0;

    TreeNode* convertBST(TreeNode* root) {
        dfs(root);
        return root;
    }

    void dfs(TreeNode* root) {
        if (!root) return;
        dfs(root->right);
        s += root->val;
        root->val = s;
        dfs(root->left);
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
func convertBST(root *TreeNode) *TreeNode {
	s := 0
	var dfs func(*TreeNode)
	dfs = func(root *TreeNode) {
		if root == nil {
			return
		}
		dfs(root.Right)
		s += root.Val
		root.Val = s
		dfs(root.Left)
	}
	dfs(root)
	return root
}
```

#### JavaScript

```js
/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {TreeNode} root
 * @return {TreeNode}
 */
var convertBST = function (root) {
    let s = 0;
    function dfs(root) {
        if (!root) {
            return;
        }
        dfs(root.right);
        s += root.val;
        root.val = s;
        dfs(root.left);
    }
    dfs(root);
    return root;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2

<!-- thinking:start -->

> **Thinking**
>
> Recursive reverse inorder uses an $O(h)$ stack. Morris temporarily links a node's inorder predecessor (leftmost of the right subtree) to recover the parent without a stack.
>
> Thread when going right, accumulate and write on the return visit, then unthread and go left. The order is still right-root-left, with constant extra space.

<!-- thinking:end -->

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
    def convertBST(self, root: TreeNode) -> TreeNode:
        s = 0
        node = root
        while root:
            if root.right is None:
                s += root.val
                root.val = s
                root = root.left
            else:
                next = root.right
                while next.left and next.left != root:
                    next = next.left
                if next.left is None:
                    next.left = root
                    root = root.right
                else:
                    s += root.val
                    root.val = s
                    next.left = None
                    root = root.left
        return node
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
    public TreeNode convertBST(TreeNode root) {
        int s = 0;
        TreeNode node = root;
        while (root != null) {
            if (root.right == null) {
                s += root.val;
                root.val = s;
                root = root.left;
            } else {
                TreeNode next = root.right;
                while (next.left != null && next.left != root) {
                    next = next.left;
                }
                if (next.left == null) {
                    next.left = root;
                    root = root.right;
                } else {
                    s += root.val;
                    root.val = s;
                    next.left = null;
                    root = root.left;
                }
            }
        }
        return node;
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
    TreeNode* convertBST(TreeNode* root) {
        int s = 0;
        TreeNode* node = root;
        while (root) {
            if (root->right == nullptr) {
                s += root->val;
                root->val = s;
                root = root->left;
            } else {
                TreeNode* next = root->right;
                while (next->left && next->left != root) {
                    next = next->left;
                }
                if (next->left == nullptr) {
                    next->left = root;
                    root = root->right;
                } else {
                    s += root->val;
                    root->val = s;
                    next->left = nullptr;
                    root = root->left;
                }
            }
        }
        return node;
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
func convertBST(root *TreeNode) *TreeNode {
	s := 0
	node := root
	for root != nil {
		if root.Right == nil {
			s += root.Val
			root.Val = s
			root = root.Left
		} else {
			next := root.Right
			for next.Left != nil && next.Left != root {
				next = next.Left
			}
			if next.Left == nil {
				next.Left = root
				root = root.Right
			} else {
				s += root.Val
				root.Val = s
				next.Left = nil
				root = root.Left
			}
		}
	}
	return node
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 3: Explicit Stack

<!-- thinking:start -->

> **Thinking**
>
> Each node should become the sum of every key not smaller than itself. In a BST those keys are exactly the nodes already seen in reverse inorder. Recursing down the right child and writing the sum back is correct on a short tree.
>
> The tree can contain $10^4$ nodes. A right chain makes this walk recurse once per node and overflow the call stack.
>
> The running sum depends only on the greater keys already visited. A node does not need a value returned from its children, as long as the right child is finished before the node and the left child comes after.
>
> An explicit stack performs that reverse inorder. On entry it pushes an exit marker and the right child; on exit it adds the node value into $s$, writes $s$ back, and pushes the left child.

<!-- thinking:end -->

An explicit stack walks the binary search tree in the order right, root, left. On entry we push an exit marker and the right child. On exit we add the node value into $s$, write $s$ back to the node, and push the left child.

The time complexity is $O(n)$ and the space complexity is $O(n)$, where $n$ is the number of nodes in the binary search tree.

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
    def convertBST(self, root: TreeNode) -> TreeNode:
        s = 0
        stk = [(root, 0)]
        while stk:
            node, state = stk.pop()
            if node is None:
                continue
            if state == 0:
                stk.append((node, 1))
                stk.append((node.right, 0))
                continue
            s += node.val
            node.val = s
            stk.append((node.left, 0))
        return root
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
        int state;

        Frame(TreeNode node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public TreeNode convertBST(TreeNode root) {
        int s = 0;
        if (root == null) {
            return null;
        }
        Deque<Frame> stk = new ArrayDeque<>();
        stk.push(new Frame(root, 0));
        while (!stk.isEmpty()) {
            Frame cur = stk.pop();
            TreeNode node = cur.node;
            if (cur.state == 0) {
                stk.push(new Frame(node, 1));
                if (node.right != null) {
                    stk.push(new Frame(node.right, 0));
                }
                continue;
            }
            s += node.val;
            node.val = s;
            if (node.left != null) {
                stk.push(new Frame(node.left, 0));
            }
        }
        return root;
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
    TreeNode* convertBST(TreeNode* root) {
        int s = 0;
        vector<pair<TreeNode*, int>> stk{{root, 0}};
        while (!stk.empty()) {
            auto [node, state] = stk.back();
            stk.pop_back();
            if (!node) {
                continue;
            }
            if (state == 0) {
                stk.emplace_back(node, 1);
                stk.emplace_back(node->right, 0);
                continue;
            }
            s += node->val;
            node->val = s;
            stk.emplace_back(node->left, 0);
        }
        return root;
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
func convertBST(root *TreeNode) *TreeNode {
	s := 0
	type frame struct {
		node  *TreeNode
		state int
	}
	stk := []frame{{root, 0}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		node, state := cur.node, cur.state
		if node == nil {
			continue
		}
		if state == 0 {
			stk = append(stk, frame{node, 1}, frame{node.Right, 0})
			continue
		}
		s += node.Val
		node.Val = s
		stk = append(stk, frame{node.Left, 0})
	}
	return root
}
```

#### JavaScript

```js
/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {TreeNode} root
 * @return {TreeNode}
 */
var convertBST = function (root) {
    let s = 0;
    const stk = [[root, 0]];
    while (stk.length) {
        const [node, state] = stk.pop();
        if (!node) {
            continue;
        }
        if (state === 0) {
            stk.push([node, 1]);
            stk.push([node.right, 0]);
            continue;
        }
        s += node.val;
        node.val = s;
        stk.push([node.left, 0]);
    }
    return root;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
