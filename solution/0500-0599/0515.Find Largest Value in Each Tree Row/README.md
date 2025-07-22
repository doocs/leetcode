---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0515.Find%20Largest%20Value%20in%20Each%20Tree%20Row/README.md
tags:
    - 树
    - 深度优先搜索
    - 广度优先搜索
    - 二叉树
---

<!-- problem:start -->

# [515. 在每个树行中找最大值](https://leetcode.cn/problems/find-largest-value-in-each-tree-row)

[English Version](/solution/0500-0599/0515.Find%20Largest%20Value%20in%20Each%20Tree%20Row/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一棵二叉树的根节点&nbsp;<code>root</code> ，请找出该二叉树中每一层的最大值。</p>

<p>&nbsp;</p>

<p><strong>示例1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0515.Find%20Largest%20Value%20in%20Each%20Tree%20Row/images/largest_e1.jpg" style="height: 172px; width: 300px;" /></p>

<pre>
<strong>输入: </strong>root = [1,3,2,5,3,null,9]
<strong>输出: </strong>[1,3,9]
</pre>

<p><strong>示例2：</strong></p>

<pre>
<strong>输入: </strong>root = [1,2,3]
<strong>输出: </strong>[1,3]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>二叉树的节点个数的范围是 <code>[0,10<sup>4</sup>]</code></li>
	<li><meta charset="UTF-8" /><code>-2<sup>31</sup>&nbsp;&lt;= Node.val &lt;= 2<sup>31</sup>&nbsp;- 1</code></li>
</ul>

<p>&nbsp;</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：BFS

我们定义一个队列 $q$，将根节点放入队列中。每次从队列中取出当前层的所有节点，找出最大值，然后将下一层的所有节点放入队列中，直到队列为空。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是二叉树的节点个数。

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
    def largestValues(self, root: Optional[TreeNode]) -> List[int]:
        ans = []
        if root is None:
            return ans
        q = deque([root])
        while q:
            x = -inf
            for _ in range(len(q)):
                node = q.popleft()
                x = max(x, node.val)
                if node.left:
                    q.append(node.left)
                if node.right:
                    q.append(node.right)
            ans.append(x)
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
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int t = q.peek().val;
            for (int i = q.size(); i > 0; --i) {
                TreeNode node = q.poll();
                t = Math.max(t, node.val);
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            ans.add(t);
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
    vector<int> largestValues(TreeNode* root) {
        vector<int> ans;
        if (!root) {
            return ans;
        }
        queue<TreeNode*> q{{root}};
        while (q.size()) {
            int x = INT_MIN;
            for (int i = q.size(); i; --i) {
                TreeNode* node = q.front();
                q.pop();
                x = max(x, node->val);
                if (node->left) {
                    q.push(node->left);
                }
                if (node->right) {
                    q.push(node->right);
                }
            }
            ans.push_back(x);
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
func largestValues(root *TreeNode) (ans []int) {
	if root == nil {
		return
	}
	q := []*TreeNode{root}
	for len(q) > 0 {
		x := q[0].Val
		for i := len(q); i > 0; i-- {
			node := q[0]
			q = q[1:]
			x = max(x, node.Val)
			if node.Left != nil {
				q = append(q, node.Left)
			}
			if node.Right != nil {
				q = append(q, node.Right)
			}
		}
		ans = append(ans, x)
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

function largestValues(root: TreeNode | null): number[] {
    const ans: number[] = [];
    if (!root) {
        return ans;
    }
    const q: TreeNode[] = [root];
    while (q.length) {
        const nq: TreeNode[] = [];
        let x = -Infinity;
        for (const { val, left, right } of q) {
            x = Math.max(x, val);
            if (left) {
                nq.push(left);
            }
            if (right) {
                nq.push(right);
            }
        }
        ans.push(x);
        q.length = 0;
        q.push(...nq);
    }
    return ans;
}
```

#### Rust

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
use std::cell::RefCell;
use std::collections::VecDeque;
use std::rc::Rc;
impl Solution {
    pub fn largest_values(root: Option<Rc<RefCell<TreeNode>>>) -> Vec<i32> {
        let mut ans = Vec::new();
        let mut q = VecDeque::new();
        if root.is_some() {
            q.push_back(root.clone());
        }
        while !q.is_empty() {
            let mut x = i32::MIN;
            for _ in 0..q.len() {
                let node = q.pop_front().unwrap();
                let node = node.as_ref().unwrap().borrow();
                x = x.max(node.val);
                if node.left.is_some() {
                    q.push_back(node.left.clone());
                }
                if node.right.is_some() {
                    q.push_back(node.right.clone());
                }
            }
            ans.push(x);
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：DFS

DFS 先序遍历，找每个深度最大的节点值。

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
    def largestValues(self, root: Optional[TreeNode]) -> List[int]:
        def dfs(root, curr):
            if root is None:
                return
            if curr == len(ans):
                ans.append(root.val)
            else:
                ans[curr] = max(ans[curr], root.val)
            dfs(root.left, curr + 1)
            dfs(root.right, curr + 1)

        ans = []
        dfs(root, 0)
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
    private List<Integer> ans = new ArrayList<>();

    public List<Integer> largestValues(TreeNode root) {
        dfs(root, 0);
        return ans;
    }

    private void dfs(TreeNode root, int curr) {
        if (root == null) {
            return;
        }
        if (curr == ans.size()) {
            ans.add(root.val);
        } else {
            ans.set(curr, Math.max(ans.get(curr), root.val));
        }
        dfs(root.left, curr + 1);
        dfs(root.right, curr + 1);
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
    vector<int> ans;

    vector<int> largestValues(TreeNode* root) {
        dfs(root, 0);
        return ans;
    }

    void dfs(TreeNode* root, int curr) {
        if (!root) return;
        if (curr == ans.size())
            ans.push_back(root->val);
        else
            ans[curr] = max(ans[curr], root->val);
        dfs(root->left, curr + 1);
        dfs(root->right, curr + 1);
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
func largestValues(root *TreeNode) []int {
	var ans []int
	var dfs func(*TreeNode, int)
	dfs = func(root *TreeNode, curr int) {
		if root == nil {
			return
		}
		if curr == len(ans) {
			ans = append(ans, root.Val)
		} else {
			ans[curr] = max(ans[curr], root.Val)
		}
		dfs(root.Left, curr+1)
		dfs(root.Right, curr+1)
	}
	dfs(root, 0)
	return ans
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

function largestValues(root: TreeNode | null): number[] {
    const res = [];
    const dfs = (root: TreeNode | null, depth: number) => {
        if (root == null) {
            return;
        }
        const { val, left, right } = root;
        if (res.length == depth) {
            res.push(val);
        } else {
            res[depth] = Math.max(res[depth], val);
        }
        dfs(left, depth + 1);
        dfs(right, depth + 1);
    };
    dfs(root, 0);
    return res;
}
```

#### Rust

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
use std::cell::RefCell;
use std::rc::Rc;
impl Solution {
    fn dfs(root: &Option<Rc<RefCell<TreeNode>>>, depth: usize, res: &mut Vec<i32>) {
        if root.is_none() {
            return;
        }
        let node = root.as_ref().unwrap().borrow();
        if res.len() == depth {
            res.push(node.val);
        } else {
            res[depth] = res[depth].max(node.val);
        }
        Self::dfs(&node.left, depth + 1, res);
        Self::dfs(&node.right, depth + 1, res);
    }

    pub fn largest_values(root: Option<Rc<RefCell<TreeNode>>>) -> Vec<i32> {
        let mut res = Vec::new();
        Self::dfs(&root, 0, &mut res);
        res
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
