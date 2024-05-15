---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0889.Construct%20Binary%20Tree%20from%20Preorder%20and%20Postorder%20Traversal/README_EN.md
tags:
    - Tree
    - Array
    - Hash Table
    - Divide and Conquer
    - Binary Tree
---

# [889. Construct Binary Tree from Preorder and Postorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal)

[中文文档](/solution/0800-0899/0889.Construct%20Binary%20Tree%20from%20Preorder%20and%20Postorder%20Traversal/README.md)

## Description

<p>Given two integer arrays, <code>preorder</code> and <code>postorder</code> where <code>preorder</code> is the preorder traversal of a binary tree of <strong>distinct</strong> values and <code>postorder</code> is the postorder traversal of the same tree, reconstruct and return <em>the binary tree</em>.</p>

<p>If there exist multiple answers, you can <strong>return any</strong> of them.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0889.Construct%20Binary%20Tree%20from%20Preorder%20and%20Postorder%20Traversal/images/lc-prepost.jpg" style="width: 304px; height: 265px;" />
<pre>
<strong>Input:</strong> preorder = [1,2,4,5,3,6,7], postorder = [4,5,2,6,7,3,1]
<strong>Output:</strong> [1,2,3,4,5,6,7]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> preorder = [1], postorder = [1]
<strong>Output:</strong> [1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= preorder.length &lt;= 30</code></li>
	<li><code>1 &lt;= preorder[i] &lt;= preorder.length</code></li>
	<li>All the values of <code>preorder</code> are <strong>unique</strong>.</li>
	<li><code>postorder.length == preorder.length</code></li>
	<li><code>1 &lt;= postorder[i] &lt;= postorder.length</code></li>
	<li>All the values of <code>postorder</code> are <strong>unique</strong>.</li>
	<li>It is guaranteed that <code>preorder</code> and <code>postorder</code> are the preorder traversal and postorder traversal of the same binary tree.</li>
</ul>

## Solutions

### Solution 1: Recursion

The order of pre-order traversal is: root node -> left subtree -> right subtree, and the order of post-order traversal is: left subtree -> right subtree -> root node.

Therefore, the root node of the binary tree must be the first node of the pre-order traversal and the last node of the post-order traversal.

Next, we need to determine the range of the left and right subtrees of the binary tree.

If the binary tree has a left subtree, then the root node of the left subtree must be the second node of the pre-order traversal; if the binary tree does not have a left subtree, then the second node of the pre-order traversal must be the root node of the right subtree. Since the results of post-order traversal in these two cases are the same, we can take the second node of the pre-order traversal as the root node of the left subtree, and then find its position in the post-order traversal, so as to determine the range of the left subtree.

Specifically, we design a recursive function $dfs(a, b, c, d)$, where $[a, b]$ represents the range of pre-order traversal, and $[c, d]$ represents the range of post-order traversal. The function of this function is to construct the root node of the binary tree based on the pre-order traversal $[a, b]$ and the post-order traversal $[c, d]$. The answer is $dfs(0, n - 1, 0, n - 1)$, where $n$ is the length of the pre-order traversal.

The execution steps of the function $dfs(a, b, c, d)$ are as follows:

1. If $a > b$, the range is empty, return a null node directly.
1. Otherwise, we construct a new node $root$, its value is the value of the first node in the pre-order traversal, which is $preorder[a]$.
1. If $a$ equals $b$, it means that $root$ has neither a left subtree nor a right subtree, return $root$ directly.
1. Otherwise, the value of the root node of the left subtree is $preorder[a + 1]$, we find the position of $preorder[a + 1]$ in the post-order traversal, denoted as $i$. The number of nodes in the left subtree $m = i - c + 1$, from this we know that the range of the left subtree in the pre-order traversal is $[a + 1, a + m]$, the range in the post-order traversal is $[c, i]$, the range of the right subtree in the pre-order traversal is $[a + m + 1, b]$, and the range in the post-order traversal is $[i + 1, d - 1]$.
1. Knowing the range of the left and right subtrees, we can recursively rebuild the left and right subtrees, and then make the root nodes of the left and right subtrees as the left and right child nodes of $root$ respectively. Finally return $root$.

In the function $dfs(a, b, c, d)$, we need to use a hash table $pos$, which stores the position of each node in the post-order traversal. At the beginning of the function, we can calculate this hash table first, so that we can find the position of any node in the post-order traversal in $O(1)$ time.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the number of nodes.

<!-- tabs:start -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def constructFromPrePost(
        self, preorder: List[int], postorder: List[int]
    ) -> Optional[TreeNode]:
        def dfs(a: int, b: int, c: int, d: int) -> Optional[TreeNode]:
            if a > b:
                return None
            root = TreeNode(preorder[a])
            if a == b:
                return root
            i = pos[preorder[a + 1]]
            m = i - c + 1
            root.left = dfs(a + 1, a + m, c, i)
            root.right = dfs(a + m + 1, b, i + 1, d - 1)
            return root

        pos = {x: i for i, x in enumerate(postorder)}
        return dfs(0, len(preorder) - 1, 0, len(postorder) - 1)
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
    private Map<Integer, Integer> pos = new HashMap<>();
    private int[] preorder;

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        this.preorder = preorder;
        for (int i = 0; i < postorder.length; ++i) {
            pos.put(postorder[i], i);
        }
        return dfs(0, preorder.length - 1, 0, postorder.length - 1);
    }

    private TreeNode dfs(int a, int b, int c, int d) {
        if (a > b) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[a]);
        if (a == b) {
            return root;
        }
        int i = pos.get(preorder[a + 1]);
        int m = i - c + 1;
        root.left = dfs(a + 1, a + m, c, i);
        root.right = dfs(a + m + 1, b, i + 1, d - 1);
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
    TreeNode* constructFromPrePost(vector<int>& preorder, vector<int>& postorder) {
        unordered_map<int, int> pos;
        int n = postorder.size();
        for (int i = 0; i < n; ++i) {
            pos[postorder[i]] = i;
        }
        function<TreeNode*(int, int, int, int)> dfs = [&](int a, int b, int c, int d) -> TreeNode* {
            if (a > b) {
                return nullptr;
            }
            TreeNode* root = new TreeNode(preorder[a]);
            if (a == b) {
                return root;
            }
            int i = pos[preorder[a + 1]];
            int m = i - c + 1;
            root->left = dfs(a + 1, a + m, c, i);
            root->right = dfs(a + m + 1, b, i + 1, d - 1);
            return root;
        };
        return dfs(0, n - 1, 0, n - 1);
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
func constructFromPrePost(preorder []int, postorder []int) *TreeNode {
	pos := map[int]int{}
	for i, x := range postorder {
		pos[x] = i
	}
	var dfs func(int, int, int, int) *TreeNode
	dfs = func(a, b, c, d int) *TreeNode {
		if a > b {
			return nil
		}
		root := &TreeNode{Val: preorder[a]}
		if a == b {
			return root
		}
		i := pos[preorder[a+1]]
		m := i - c + 1
		root.Left = dfs(a+1, a+m, c, i)
		root.Right = dfs(a+m+1, b, i+1, d-1)
		return root
	}
	return dfs(0, len(preorder)-1, 0, len(postorder)-1)
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

function constructFromPrePost(preorder: number[], postorder: number[]): TreeNode | null {
    const pos: Map<number, number> = new Map();
    const n = postorder.length;
    for (let i = 0; i < n; ++i) {
        pos.set(postorder[i], i);
    }
    const dfs = (a: number, b: number, c: number, d: number): TreeNode | null => {
        if (a > b) {
            return null;
        }
        const root = new TreeNode(preorder[a]);
        if (a === b) {
            return root;
        }
        const i = pos.get(preorder[a + 1])!;
        const m = i - c + 1;
        root.left = dfs(a + 1, a + m, c, i);
        root.right = dfs(a + m + 1, b, i + 1, d - 1);
        return root;
    };
    return dfs(0, n - 1, 0, n - 1);
}
```

<!-- tabs:end -->

### Solution 2: Another Recursive Approach

We can design a recursive function $dfs(i, j, n)$, where $i$ and $j$ represent the starting points of the pre-order and post-order traversals, respectively, and $n$ represents the number of nodes. This function constructs the root node of the binary tree based on the pre-order traversal $[i, i + n - 1]$ and post-order traversal $[j, j + n - 1]$. The answer is $dfs(0, 0, n)$, where $n$ is the length of the pre-order traversal.

The execution steps of the function $dfs(i, j, n)$ are as follows:

1. If $n = 0$, the range is empty, so return a null node directly.
2. Otherwise, we construct a new node $root$, whose value is the value of the first node in the pre-order traversal, which is $preorder[i]$.
3. If $n = 1$, it means that $root$ has neither a left subtree nor a right subtree, so return $root$ directly.
4. Otherwise, the value of the root node of the left subtree is $preorder[i + 1]$. We find the position of $preorder[i + 1]$ in the post-order traversal, denoted as $k$. Then the number of nodes in the left subtree is $m = k - j + 1$, and the number of nodes in the right subtree is $n - m - 1$. We recursively rebuild the left and right subtrees, and then make the root nodes of the left and right subtrees the left and right child nodes of $root$, respectively. Finally, return $root$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the number of nodes.

<!-- tabs:start -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def constructFromPrePost(
        self, preorder: List[int], postorder: List[int]
    ) -> Optional[TreeNode]:
        def dfs(i: int, j: int, n: int) -> Optional[TreeNode]:
            if n <= 0:
                return None
            root = TreeNode(preorder[i])
            if n == 1:
                return root
            k = pos[preorder[i + 1]]
            m = k - j + 1
            root.left = dfs(i + 1, j, m)
            root.right = dfs(i + m + 1, k + 1, n - m - 1)
            return root

        pos = {x: i for i, x in enumerate(postorder)}
        return dfs(0, 0, len(preorder))
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
    private Map<Integer, Integer> pos = new HashMap<>();
    private int[] preorder;

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        this.preorder = preorder;
        for (int i = 0; i < postorder.length; ++i) {
            pos.put(postorder[i], i);
        }
        return dfs(0, 0, preorder.length);
    }

    private TreeNode dfs(int i, int j, int n) {
        if (n <= 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[i]);
        if (n == 1) {
            return root;
        }
        int k = pos.get(preorder[i + 1]);
        int m = k - j + 1;
        root.left = dfs(i + 1, j, m);
        root.right = dfs(i + m + 1, k + 1, n - m - 1);
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
    TreeNode* constructFromPrePost(vector<int>& preorder, vector<int>& postorder) {
        unordered_map<int, int> pos;
        int n = postorder.size();
        for (int i = 0; i < n; ++i) {
            pos[postorder[i]] = i;
        }
        function<TreeNode*(int, int, int)> dfs = [&](int i, int j, int n) -> TreeNode* {
            if (n <= 0) {
                return nullptr;
            }
            TreeNode* root = new TreeNode(preorder[i]);
            if (n == 1) {
                return root;
            }
            int k = pos[preorder[i + 1]];
            int m = k - j + 1;
            root->left = dfs(i + 1, j, m);
            root->right = dfs(i + m + 1, k + 1, n - m - 1);
            return root;
        };
        return dfs(0, 0, n);
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
func constructFromPrePost(preorder []int, postorder []int) *TreeNode {
	pos := map[int]int{}
	for i, x := range postorder {
		pos[x] = i
	}
	var dfs func(int, int, int) *TreeNode
	dfs = func(i, j, n int) *TreeNode {
		if n <= 0 {
			return nil
		}
		root := &TreeNode{Val: preorder[i]}
		if n == 1 {
			return root
		}
		k := pos[preorder[i+1]]
		m := k - j + 1
		root.Left = dfs(i+1, j, m)
		root.Right = dfs(i+m+1, k+1, n-m-1)
		return root
	}
	return dfs(0, 0, len(preorder))
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

function constructFromPrePost(preorder: number[], postorder: number[]): TreeNode | null {
    const pos: Map<number, number> = new Map();
    const n = postorder.length;
    for (let i = 0; i < n; ++i) {
        pos.set(postorder[i], i);
    }
    const dfs = (i: number, j: number, n: number): TreeNode | null => {
        if (n <= 0) {
            return null;
        }
        const root = new TreeNode(preorder[i]);
        if (n === 1) {
            return root;
        }
        const k = pos.get(preorder[i + 1])!;
        const m = k - j + 1;
        root.left = dfs(i + 1, j, m);
        root.right = dfs(i + 1 + m, k + 1, n - 1 - m);
        return root;
    };
    return dfs(0, 0, n);
}
```

<!-- tabs:end -->

<!-- end -->
