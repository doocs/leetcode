---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0105.Construct%20Binary%20Tree%20from%20Preorder%20and%20Inorder%20Traversal/README_EN.md
tags:
    - Tree
    - Array
    - Hash Table
    - Divide and Conquer
    - Binary Tree
---

<!-- problem:start -->

# [105. Construct Binary Tree from Preorder and Inorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal)

[中文文档](/solution/0100-0199/0105.Construct%20Binary%20Tree%20from%20Preorder%20and%20Inorder%20Traversal/README.md)

## Description

<p>Given two integer arrays <code>preorder</code> and <code>inorder</code> where <code>preorder</code> is the preorder traversal of a binary tree and <code>inorder</code> is the inorder traversal of the same tree, construct and return <em>the binary tree</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0105.Construct%20Binary%20Tree%20from%20Preorder%20and%20Inorder%20Traversal/images/tree.jpg" style="width: 277px; height: 302px;" />
<pre>
<strong>Input:</strong> preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
<strong>Output:</strong> [3,9,20,null,null,15,7]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> preorder = [-1], inorder = [-1]
<strong>Output:</strong> [-1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= preorder.length &lt;= 3000</code></li>
	<li><code>inorder.length == preorder.length</code></li>
	<li><code>-3000 &lt;= preorder[i], inorder[i] &lt;= 3000</code></li>
	<li><code>preorder</code> and <code>inorder</code> consist of <strong>unique</strong> values.</li>
	<li>Each value of <code>inorder</code> also appears in <code>preorder</code>.</li>
	<li><code>preorder</code> is <strong>guaranteed</strong> to be the preorder traversal of the tree.</li>
	<li><code>inorder</code> is <strong>guaranteed</strong> to be the inorder traversal of the tree.</li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table + Recursion

The first node $preorder[0]$ in the pre-order sequence is the root node. We find the position $k$ of the root node in the in-order sequence, which can divide the in-order sequence into the left subtree $inorder[0..k]$ and the right subtree $inorder[k+1..]$.

Through the intervals of the left and right subtrees, we can calculate the number of nodes in the left and right subtrees, assumed to be $a$ and $b$. Then in the pre-order nodes, the $a$ nodes after the root node are the left subtree, and the $b$ nodes after that are the right subtree.

Therefore, we design a function $dfs(i, j, n)$, where $i$ and $j$ represent the starting positions of the pre-order sequence and the in-order sequence, respectively, and $n$ represents the number of nodes. The return value of the function is the binary tree constructed with $preorder[i..i+n-1]$ as the pre-order sequence and $inorder[j..j+n-1]$ as the in-order sequence.

The execution process of the function $dfs(i, j, n)$ is as follows:

-   If $n \leq 0$, it means there are no nodes, return a null node.
-   Take out the first node $v = preorder[i]$ of the pre-order sequence as the root node, and then use the hash table $d$ to find the position $k$ of the root node in the in-order sequence. Then the number of nodes in the left subtree is $k - j$, and the number of nodes in the right subtree is $n - k + j - 1$.
-   Recursively construct the left subtree $l = dfs(i + 1, j, k - j)$ and the right subtree $r = dfs(i + 1 + k - j, k + 1, n - k + j - 1)$.
-   Finally, return the binary tree with $v$ as the root node and $l$ and $r$ as the left and right subtrees, respectively.

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
    def buildTree(self, preorder: List[int], inorder: List[int]) -> Optional[TreeNode]:
        def dfs(i: int, j: int, n: int) -> Optional[TreeNode]:
            if n <= 0:
                return None
            v = preorder[i]
            k = d[v]
            l = dfs(i + 1, j, k - j)
            r = dfs(i + 1 + k - j, k + 1, n - k + j - 1)
            return TreeNode(v, l, r)

        d = {v: i for i, v in enumerate(inorder)}
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
    private int[] preorder;
    private Map<Integer, Integer> d = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        this.preorder = preorder;
        for (int i = 0; i < n; ++i) {
            d.put(inorder[i], i);
        }
        return dfs(0, 0, n);
    }

    private TreeNode dfs(int i, int j, int n) {
        if (n <= 0) {
            return null;
        }
        int v = preorder[i];
        int k = d.get(v);
        TreeNode l = dfs(i + 1, j, k - j);
        TreeNode r = dfs(i + 1 + k - j, k + 1, n - 1 - (k - j));
        return new TreeNode(v, l, r);
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
    TreeNode* buildTree(vector<int>& preorder, vector<int>& inorder) {
        int n = preorder.size();
        unordered_map<int, int> d;
        for (int i = 0; i < n; ++i) {
            d[inorder[i]] = i;
        }
        function<TreeNode*(int, int, int)> dfs = [&](int i, int j, int n) -> TreeNode* {
            if (n <= 0) {
                return nullptr;
            }
            int v = preorder[i];
            int k = d[v];
            TreeNode* l = dfs(i + 1, j, k - j);
            TreeNode* r = dfs(i + 1 + k - j, k + 1, n - 1 - (k - j));
            return new TreeNode(v, l, r);
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
func buildTree(preorder []int, inorder []int) *TreeNode {
	d := map[int]int{}
	for i, x := range inorder {
		d[x] = i
	}
	var dfs func(i, j, n int) *TreeNode
	dfs = func(i, j, n int) *TreeNode {
		if n <= 0 {
			return nil
		}
		v := preorder[i]
		k := d[v]
		l := dfs(i+1, j, k-j)
		r := dfs(i+1+k-j, k+1, n-1-(k-j))
		return &TreeNode{v, l, r}
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

function buildTree(preorder: number[], inorder: number[]): TreeNode | null {
    const d: Map<number, number> = new Map();
    const n = inorder.length;
    for (let i = 0; i < n; ++i) {
        d.set(inorder[i], i);
    }
    const dfs = (i: number, j: number, n: number): TreeNode | null => {
        if (n <= 0) {
            return null;
        }
        const v = preorder[i];
        const k = d.get(v)!;
        const l = dfs(i + 1, j, k - j);
        const r = dfs(i + 1 + k - j, k + 1, n - 1 - (k - j));
        return new TreeNode(v, l, r);
    };
    return dfs(0, 0, n);
}
```

```rust
// Definition for a binary tree node.
// #[derive(Debug, PartialEq, Eq)]
// pub struct TreeNode {
//   pub val: i32,
//   pub left: Option<Rc<RefCell<TreeNode>>>,
//   pub right: Option<Rc<RefCell<TreeNode>>>,
// }
//
// impl TreeNode {
//   #[inline]
//   pub fn new(val: i32) -> Self {
//     TreeNode {
//       val,
//       left: None,
//       right: None
//     }
//   }
// }
use std::rc::Rc;
use std::cell::RefCell;
use std::collections::HashMap;
impl Solution {
    pub fn build_tree(preorder: Vec<i32>, inorder: Vec<i32>) -> Option<Rc<RefCell<TreeNode>>> {
        let mut d = HashMap::new();
        for (i, &x) in inorder.iter().enumerate() {
            d.insert(x, i);
        }
        Self::dfs(&preorder, &d, 0, 0, preorder.len())
    }

    pub fn dfs(
        preorder: &Vec<i32>,
        d: &HashMap<i32, usize>,
        i: usize,
        j: usize,
        n: usize
    ) -> Option<Rc<RefCell<TreeNode>>> {
        if n <= 0 {
            return None;
        }
        let v = preorder[i];
        let k = d[&v];
        let mut root = TreeNode::new(v);
        root.left = Self::dfs(preorder, d, i + 1, j, k - j);
        root.right = Self::dfs(preorder, d, i + k - j + 1, k + 1, n - k + j - 1);
        Some(Rc::new(RefCell::new(root)))
    }
}
```

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
 * @param {number[]} preorder
 * @param {number[]} inorder
 * @return {TreeNode}
 */
var buildTree = function (preorder, inorder) {
    const d = new Map();
    const n = inorder.length;
    for (let i = 0; i < n; ++i) {
        d.set(inorder[i], i);
    }
    const dfs = (i, j, n) => {
        if (n <= 0) {
            return null;
        }
        const v = preorder[i];
        const k = d.get(v);
        const l = dfs(i + 1, j, k - j);
        const r = dfs(i + 1 + k - j, k + 1, n - 1 - (k - j));
        return new TreeNode(v, l, r);
    };
    return dfs(0, 0, n);
};
```

<!-- tabs:end -->

If the node values given in the problem have duplicates, then we only need to record all the positions where each node value appears, and then recursively construct the tree.

<!-- tabs:start -->

```python
class Solution:
    def getBinaryTrees(self, preOrder: List[int], inOrder: List[int]) -> List[TreeNode]:
        def dfs(i: int, j: int, n: int) -> List[TreeNode]:
            if n <= 0:
                return [None]
            v = preOrder[i]
            ans = []
            for k in d[v]:
                if j <= k < j + n:
                    for l in dfs(i + 1, j, k - j):
                        for r in dfs(i + 1 + k - j, k + 1, n - 1 - (k - j)):
                            ans.append(TreeNode(v, l, r))
            return ans

        d = defaultdict(list)
        for i, x in enumerate(inOrder):
            d[x].append(i)
        return dfs(0, 0, len(preOrder))
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
    private int[] preorder;
    private Map<Integer, Integer> d = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        this.preorder = preorder;
        for (int i = 0; i < n; ++i) {
            d.put(inorder[i], i);
        }
        return dfs(0, 0, n);
    }

    private TreeNode dfs(int i, int j, int n) {
        if (n <= 0) {
            return null;
        }
        int v = preorder[i];
        int k = d.get(v);
        TreeNode l = dfs(i + 1, j, k - j);
        TreeNode r = dfs(i + 1 + k - j, k + 1, n - 1 - (k - j));
        return new TreeNode(v, l, r);
    }
}
```

```cpp
/**
 * struct TreeNode {
 *	int val;
 *	struct TreeNode *left;
 *	struct TreeNode *right;
 *	TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 * };
 */
class Solution {
public:
    vector<TreeNode*> getBinaryTrees(vector<int>& preOrder, vector<int>& inOrder) {
        int n = inOrder.size();
        unordered_map<int, vector<int>> d;
        for (int i = 0; i < n; ++i) {
            d[inOrder[i]].push_back(i);
        }
        function<vector<TreeNode*>(int, int, int)> dfs = [&](int i, int j, int n) -> vector<TreeNode*> {
            vector<TreeNode*> ans;
            if (n <= 0) {
                ans.push_back(nullptr);
                return ans;
            }
            int v = preOrder[i];
            for (int k : d[v]) {
                if (k >= j && k < j + n) {
                    auto lefts = dfs(i + 1, j, k - j);
                    auto rights = dfs(i + 1 + k - j, k + 1, n - 1 - (k - j));
                    for (TreeNode* l : lefts) {
                        for (TreeNode* r : rights) {
                            TreeNode* node = new TreeNode(v);
                            node->left = l;
                            node->right = r;
                            ans.push_back(node);
                        }
                    }
                }
            }
            return ans;
        };
        return dfs(0, 0, n);
    }
};
```

```go
func getBinaryTrees(preOrder []int, inOrder []int) []*TreeNode {
	n := len(preOrder)
	d := map[int][]int{}
	for i, x := range inOrder {
		d[x] = append(d[x], i)
	}
	var dfs func(i, j, n int) []*TreeNode
	dfs = func(i, j, n int) []*TreeNode {
		ans := []*TreeNode{}
		if n <= 0 {
			ans = append(ans, nil)
			return ans
		}
		v := preOrder[i]
		for _, k := range d[v] {
			if k >= j && k < j+n {
				lefts := dfs(i+1, j, k-j)
				rights := dfs(i+1+k-j, k+1, n-1-(k-j))
				for _, left := range lefts {
					for _, right := range rights {
						ans = append(ans, &TreeNode{v, left, right})
					}
				}
			}
		}
		return ans
	}
	return dfs(0, 0, n)
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
