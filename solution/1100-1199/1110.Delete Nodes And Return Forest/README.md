---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1110.Delete%20Nodes%20And%20Return%20Forest/README.md
rating: 1511
source: 第 144 场周赛 Q3
tags:
    - 树
    - 深度优先搜索
    - 数组
    - 哈希表
    - 二叉树
---

<!-- problem:start -->

# [1110. 删点成林](https://leetcode.cn/problems/delete-nodes-and-return-forest)

[English Version](/solution/1100-1199/1110.Delete%20Nodes%20And%20Return%20Forest/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给出二叉树的根节点&nbsp;<code>root</code>，树上每个节点都有一个不同的值。</p>

<p>如果节点值在&nbsp;<code>to_delete</code>&nbsp;中出现，我们就把该节点从树上删去，最后得到一个森林（一些不相交的树构成的集合）。</p>

<p>返回森林中的每棵树。你可以按任意顺序组织答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1110.Delete%20Nodes%20And%20Return%20Forest/images/screen-shot-2019-07-01-at-53836-pm.png" style="height: 150px; width: 237px;" /></strong></p>

<pre>
<strong>输入：</strong>root = [1,2,3,4,5,6,7], to_delete = [3,5]
<strong>输出：</strong>[[1,2,null,4],[6],[7]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>root = [1,2,4,null,3], to_delete = [3]
<strong>输出：</strong>[[1,2,4]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中的节点数最大为&nbsp;<code>1000</code>。</li>
	<li>每个节点都有一个介于&nbsp;<code>1</code> 到&nbsp;<code>1000</code>&nbsp;之间的值，且各不相同。</li>
	<li><code>to_delete.length &lt;= 1000</code></li>
	<li><code>to_delete</code> 包含一些从&nbsp;<code>1</code> 到&nbsp;<code>1000</code>、各不相同的值。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS

我们先用哈希表或者一个长度为 $1001$ 的数组 $s$ 记录所有需要删除的节点。

接下来，设计一个函数 $dfs(root)$，它会返回以 $root$ 为根的子树中，删除所有需要删除的节点后的树的根节点。函数 $dfs(root)$ 的执行逻辑如下：

-   如果 $root$ 为空，那么我们返回空；
-   否则，我们递归执行 $dfs(root.left)$ 和 $dfs(root.right)$，并将返回值分别赋给 $root.left$ 和 $root.right$。如果 $root$ 不需要被删除，那么我们返回 $root$；如果 $root$ 需要被删除，那么我们分别判断 $root.left$ 和 $root.right$ 是否为空，如果它们不为空，那么我们将它们加入答案数组中；最后返回空。

在主函数中，我们调用 $dfs(root)$，如果结果不为空，说明根节点不需要被删除，我们再将根节点加入答案数组中。最后返回答案数组即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是树的节点数。

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
    def delNodes(
        self, root: Optional[TreeNode], to_delete: List[int]
    ) -> List[TreeNode]:
        def dfs(root: Optional[TreeNode]) -> Optional[TreeNode]:
            if root is None:
                return None
            root.left, root.right = dfs(root.left), dfs(root.right)
            if root.val not in s:
                return root
            if root.left:
                ans.append(root.left)
            if root.right:
                ans.append(root.right)
            return None

        s = set(to_delete)
        ans = []
        if dfs(root):
            ans.append(root)
        return ans
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
    private boolean[] s = new boolean[1001];
    private List<TreeNode> ans = new ArrayList<>();

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        for (int x : to_delete) {
            s[x] = true;
        }
        if (dfs(root) != null) {
            ans.add(root);
        }
        return ans;
    }

    private TreeNode dfs(TreeNode root) {
        if (root == null) {
            return null;
        }
        root.left = dfs(root.left);
        root.right = dfs(root.right);
        if (!s[root.val]) {
            return root;
        }
        if (root.left != null) {
            ans.add(root.left);
        }
        if (root.right != null) {
            ans.add(root.right);
        }
        return null;
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
    vector<TreeNode*> delNodes(TreeNode* root, vector<int>& to_delete) {
        bool s[1001];
        memset(s, 0, sizeof(s));
        for (int x : to_delete) {
            s[x] = true;
        }
        vector<TreeNode*> ans;
        function<TreeNode*(TreeNode*)> dfs = [&](TreeNode* root) -> TreeNode* {
            if (!root) {
                return nullptr;
            }
            root->left = dfs(root->left);
            root->right = dfs(root->right);
            if (!s[root->val]) {
                return root;
            }
            if (root->left) {
                ans.push_back(root->left);
            }
            if (root->right) {
                ans.push_back(root->right);
            }
            return nullptr;
        };
        if (dfs(root)) {
            ans.push_back(root);
        }
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
func delNodes(root *TreeNode, to_delete []int) (ans []*TreeNode) {
	s := make([]bool, 1001)
	for _, x := range to_delete {
		s[x] = true
	}
	var dfs func(*TreeNode) *TreeNode
	dfs = func(root *TreeNode) *TreeNode {
		if root == nil {
			return nil
		}
		root.Left = dfs(root.Left)
		root.Right = dfs(root.Right)
		if !s[root.Val] {
			return root
		}
		if root.Left != nil {
			ans = append(ans, root.Left)
		}
		if root.Right != nil {
			ans = append(ans, root.Right)
		}
		return nil
	}
	if dfs(root) != nil {
		ans = append(ans, root)
	}
	return
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

function delNodes(root: TreeNode | null, to_delete: number[]): Array<TreeNode | null> {
    const s: boolean[] = Array(1001).fill(false);
    for (const x of to_delete) {
        s[x] = true;
    }
    const ans: Array<TreeNode | null> = [];
    const dfs = (root: TreeNode | null): TreeNode | null => {
        if (!root) {
            return null;
        }
        root.left = dfs(root.left);
        root.right = dfs(root.right);
        if (!s[root.val]) {
            return root;
        }
        if (root.left) {
            ans.push(root.left);
        }
        if (root.right) {
            ans.push(root.right);
        }
        return null;
    };
    if (dfs(root)) {
        ans.push(root);
    }
    return ans;
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
 * @param {number[]} to_delete
 * @return {TreeNode[]}
 */
var delNodes = function (root, to_delete) {
    const s = Array(1001).fill(false);
    for (const x of to_delete) {
        s[x] = true;
    }
    const ans = [];
    const dfs = root => {
        if (!root) {
            return null;
        }
        root.left = dfs(root.left);
        root.right = dfs(root.right);
        if (!s[root.val]) {
            return root;
        }
        if (root.left) {
            ans.push(root.left);
        }
        if (root.right) {
            ans.push(root.right);
        }
        return null;
    };
    if (dfs(root)) {
        ans.push(root);
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：BFS

<!-- tabs:start -->

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
export function delNodes(root: T, to_delete: number[]): Array<T> {
    if (!root) return [];

    const del = new Set(to_delete);
    const res: T[] = [];
    let q: TreeNode[] = [root];

    while (q.length) {
        const qNext: TreeNode[] = [];

        for (const node of q) {
            if (node.left) {
                qNext.push(node.left);

                if (del.has(node.left.val)) {
                    node.left = null;
                }
            }

            if (node.right) {
                qNext.push(node.right);

                if (del.has(node.right.val)) {
                    node.right = null;
                }
            }

            if (del.has(node.val)) {
                if (node.left) res.push(node.left);
                if (node.right) res.push(node.right);
            }
        }

        q = qNext;
    }

    if (!del.has(root.val)) res.push(root);

    return res;
}

type T = TreeNode | null;
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
 * @param {number[]} to_delete
 * @return {TreeNode[]}
 */
var delNodes = function (root, to_delete) {
    if (!root) return [];

    const del = new Set(to_delete);
    const res = [];
    let q = [root];

    while (q.length) {
        const qNext = [];

        for (const node of q) {
            if (node.left) {
                qNext.push(node.left);

                if (del.has(node.left.val)) {
                    node.left = null;
                }
            }

            if (node.right) {
                qNext.push(node.right);

                if (del.has(node.right.val)) {
                    node.right = null;
                }
            }

            if (del.has(node.val)) {
                if (node.left) res.push(node.left);
                if (node.right) res.push(node.right);
            }
        }

        q = qNext;
    }

    if (!del.has(root.val)) res.push(root);

    return res;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
