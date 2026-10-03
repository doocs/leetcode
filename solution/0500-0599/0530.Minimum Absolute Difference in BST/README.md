---
comments: true
difficulty: 简单
tags:
    - 树
    - 深度优先搜索
    - 广度优先搜索
    - 二叉搜索树
    - 二叉树
---

<!-- problem:start -->

# [530. 二叉搜索树的最小绝对差](https://leetcode.cn/problems/minimum-absolute-difference-in-bst)

[English Version](/solution/0500-0599/0530.Minimum%20Absolute%20Difference%20in%20BST/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个二叉搜索树的根节点 <code>root</code> ，返回 <strong>树中任意两不同节点值之间的最小差值</strong> 。</p>

<p>差值是一个正数，其数值等于两值之差的绝对值。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0530.Minimum%20Absolute%20Difference%20in%20BST/images/bst1.jpg" style="width: 292px; height: 301px;" />
<pre>
<strong>输入：</strong>root = [4,2,6,1,3]
<strong>输出：</strong>1
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0530.Minimum%20Absolute%20Difference%20in%20BST/images/bst2.jpg" style="width: 282px; height: 301px;" />
<pre>
<strong>输入：</strong>root = [1,0,48,null,null,12,49]
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点的数目范围是 <code>[2, 10<sup>4</sup>]</code></li>
	<li><code>0 &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
</ul>

<p>&nbsp;</p>

<p><strong>注意：</strong>本题与 783 <a href="https://leetcode.cn/problems/minimum-distance-between-bst-nodes/">https://leetcode.cn/problems/minimum-distance-between-bst-nodes/</a> 相同</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：中序遍历

<!-- thinking:start -->

> **思考**
>
> 任意两点差的最小值，在无序树上要比较所有点对。BST 的中序是递增序列，最小差只可能出现在相邻两项之间。
>
> 中序遍历时维护前驱，用当前值减前驱更新答案。前驱初值取负无穷，避免第一个节点参与减法。一次中序即可。

<!-- thinking:end -->

题目需要我们求任意两个节点值之间的最小差值，而二叉搜索树的中序遍历是一个递增序列，因此我们只需要求中序遍历中相邻两个节点值之间的最小差值即可。

我们可以使用递归的方法来实现中序遍历，过程中用一个变量 $\textit{pre}$ 来保存前一个节点的值，这样我们就可以在遍历的过程中求出相邻两个节点值之间的最小差值。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为二叉搜索树的节点个数。

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
    def getMinimumDifference(self, root: Optional[TreeNode]) -> int:
        def dfs(root: Optional[TreeNode]):
            if root is None:
                return
            dfs(root.left)
            nonlocal pre, ans
            ans = min(ans, root.val - pre)
            pre = root.val
            dfs(root.right)

        pre = -inf
        ans = inf
        dfs(root)
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
    private final int inf = 1 << 30;
    private int ans = inf;
    private int pre = -inf;

    public int getMinimumDifference(TreeNode root) {
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        ans = Math.min(ans, root.val - pre);
        pre = root.val;
        dfs(root.right);
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
    int getMinimumDifference(TreeNode* root) {
        const int inf = 1 << 30;
        int ans = inf, pre = -inf;
        auto dfs = [&](this auto&& dfs, TreeNode* root) -> void {
            if (!root) {
                return;
            }
            dfs(root->left);
            ans = min(ans, root->val - pre);
            pre = root->val;
            dfs(root->right);
        };
        dfs(root);
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
func getMinimumDifference(root *TreeNode) int {
	const inf int = 1 << 30
	ans, pre := inf, -inf
	var dfs func(*TreeNode)
	dfs = func(root *TreeNode) {
		if root == nil {
			return
		}
		dfs(root.Left)
		ans = min(ans, root.Val-pre)
		pre = root.Val
		dfs(root.Right)
	}
	dfs(root)
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

function getMinimumDifference(root: TreeNode | null): number {
    let [ans, pre] = [Infinity, -Infinity];
    const dfs = (root: TreeNode | null) => {
        if (!root) {
            return;
        }
        dfs(root.left);
        ans = Math.min(ans, root.val - pre);
        pre = root.val;
        dfs(root.right);
    };
    dfs(root);
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
use std::rc::Rc;
use std::cell::RefCell;
impl Solution {
    pub fn get_minimum_difference(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
        const inf: i32 = 1 << 30;
        let mut ans = inf;
        let mut pre = -inf;

        fn dfs(node: Option<Rc<RefCell<TreeNode>>>, ans: &mut i32, pre: &mut i32) {
            if let Some(n) = node {
                let n = n.borrow();
                dfs(n.left.clone(), ans, pre);
                *ans = (*ans).min(n.val - *pre);
                *pre = n.val;
                dfs(n.right.clone(), ans, pre);
            }
        }

        dfs(root, &mut ans, &mut pre);
        ans
    }
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
 * @return {number}
 */
var getMinimumDifference = function (root) {
    let [ans, pre] = [Infinity, -Infinity];
    const dfs = root => {
        if (!root) {
            return;
        }
        dfs(root.left);
        ans = Math.min(ans, root.val - pre);
        pre = root.val;
        dfs(root.right);
    };
    dfs(root);
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：显式栈中序遍历

<!-- thinking:start -->

> **思考**
>
> 任意两点差的最小值，在无序树上要比较所有点对。二叉搜索树的中序是递增序列，最小差只可能出现在相邻两项之间。先递归中序、用当前值减去前驱，在较短的树上是对的。
>
> 结点个数可达 $10^4$。左链使这次中序按结点个数递归，调用栈会溢出。
>
> 每个结点只需要前一个值，不需要子树的返回值，只要先走完左子树、再看自己、再走右子树。
>
> 因此用显式栈做中序遍历。进入结点时压入退出标记和左孩子；退出时用当前值减去前驱更新答案，再压入右孩子。前驱初值取负无穷，第一个结点不会成为答案。

<!-- thinking:end -->

题目要求任意两个结点值的最小差。二叉搜索树的中序遍历是递增序列，所以最小差只会出现在中序相邻的两个值之间。

我们用显式栈完成这次中序遍历，并用 $\textit{pre}$ 保存前一个结点的值，初值为负无穷。进入结点时压入退出标记和左孩子；退出时用当前值减去 $\textit{pre}$ 更新答案，然后把 $\textit{pre}$ 改成当前值，再压入右孩子。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为二叉搜索树的节点个数。

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
    def getMinimumDifference(self, root: Optional[TreeNode]) -> int:
        pre = -inf
        ans = inf
        stk = [(root, 0)]
        while stk:
            node, state = stk.pop()
            if node is None:
                continue
            if state == 0:
                stk.append((node, 1))
                stk.append((node.left, 0))
                continue
            ans = min(ans, node.val - pre)
            pre = node.val
            stk.append((node.right, 0))
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
    private static class Frame {
        TreeNode node;
        int state;

        Frame(TreeNode node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public int getMinimumDifference(TreeNode root) {
        final int inf = 1 << 30;
        int ans = inf;
        int pre = -inf;
        if (root == null) {
            return ans;
        }
        Deque<Frame> stk = new ArrayDeque<>();
        stk.push(new Frame(root, 0));
        while (!stk.isEmpty()) {
            Frame cur = stk.pop();
            TreeNode node = cur.node;
            if (cur.state == 0) {
                stk.push(new Frame(node, 1));
                if (node.left != null) {
                    stk.push(new Frame(node.left, 0));
                }
                continue;
            }
            ans = Math.min(ans, node.val - pre);
            pre = node.val;
            if (node.right != null) {
                stk.push(new Frame(node.right, 0));
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
    int getMinimumDifference(TreeNode* root) {
        const int inf = 1 << 30;
        int ans = inf, pre = -inf;
        vector<pair<TreeNode*, int>> stk{{root, 0}};
        while (!stk.empty()) {
            auto [node, state] = stk.back();
            stk.pop_back();
            if (!node) {
                continue;
            }
            if (state == 0) {
                stk.emplace_back(node, 1);
                stk.emplace_back(node->left, 0);
                continue;
            }
            ans = min(ans, node->val - pre);
            pre = node->val;
            stk.emplace_back(node->right, 0);
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
func getMinimumDifference(root *TreeNode) int {
	const inf int = 1 << 30
	ans, pre := inf, -inf
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
			stk = append(stk, frame{node, 1}, frame{node.Left, 0})
			continue
		}
		ans = min(ans, node.Val-pre)
		pre = node.Val
		stk = append(stk, frame{node.Right, 0})
	}
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

function getMinimumDifference(root: TreeNode | null): number {
    let ans = Infinity;
    let pre = -Infinity;
    const stk: [TreeNode | null, number][] = [[root, 0]];
    while (stk.length) {
        const [node, state] = stk.pop()!;
        if (!node) {
            continue;
        }
        if (state === 0) {
            stk.push([node, 1]);
            stk.push([node.left, 0]);
            continue;
        }
        ans = Math.min(ans, node.val - pre);
        pre = node.val;
        stk.push([node.right, 0]);
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
use std::rc::Rc;

impl Solution {
    pub fn get_minimum_difference(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
        const INF: i32 = 1 << 30;
        let mut ans = INF;
        let mut pre = -INF;
        let mut stk = vec![(root, 0)];
        while let Some((node, state)) = stk.pop() {
            if let Some(node) = node {
                if state == 0 {
                    let left = node.borrow().left.clone();
                    stk.push((Some(node), 1));
                    if left.is_some() {
                        stk.push((left, 0));
                    }
                } else {
                    let right = {
                        let b = node.borrow();
                        ans = ans.min(b.val - pre);
                        pre = b.val;
                        b.right.clone()
                    };
                    if right.is_some() {
                        stk.push((right, 0));
                    }
                }
            }
        }
        ans
    }
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
 * @return {number}
 */
var getMinimumDifference = function (root) {
    let ans = Infinity;
    let pre = -Infinity;
    const stk = [[root, 0]];
    while (stk.length) {
        const [node, state] = stk.pop();
        if (!node) {
            continue;
        }
        if (state === 0) {
            stk.push([node, 1]);
            stk.push([node.left, 0]);
            continue;
        }
        ans = Math.min(ans, node.val - pre);
        pre = node.val;
        stk.push([node.right, 0]);
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
