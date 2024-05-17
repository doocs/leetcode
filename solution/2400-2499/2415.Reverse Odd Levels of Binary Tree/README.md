---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2415.Reverse%20Odd%20Levels%20of%20Binary%20Tree/README.md
rating: 1431
source: 第 311 场周赛 Q3
tags:
    - 树
    - 深度优先搜索
    - 广度优先搜索
    - 二叉树
---

<!-- problem:start -->

# [2415. 反转二叉树的奇数层](https://leetcode.cn/problems/reverse-odd-levels-of-binary-tree)

[English Version](/solution/2400-2499/2415.Reverse%20Odd%20Levels%20of%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一棵 <strong>完美</strong> 二叉树的根节点 <code>root</code> ，请你反转这棵树中每个 <strong>奇数</strong> 层的节点值。</p>

<ul>
	<li>例如，假设第 3 层的节点值是 <code>[2,1,3,4,7,11,29,18]</code> ，那么反转后它应该变成 <code>[18,29,11,7,4,3,1,2]</code> 。</li>
</ul>

<p>反转后，返回树的根节点。</p>

<p><strong>完美</strong> 二叉树需满足：二叉树的所有父节点都有两个子节点，且所有叶子节点都在同一层。</p>

<p>节点的 <strong>层数</strong> 等于该节点到根节点之间的边数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2415.Reverse%20Odd%20Levels%20of%20Binary%20Tree/images/first_case1.png" style="width: 626px; height: 191px;" />
<pre>
<strong>输入：</strong>root = [2,3,5,8,13,21,34]
<strong>输出：</strong>[2,5,3,8,13,21,34]
<strong>解释：</strong>
这棵树只有一个奇数层。
在第 1 层的节点分别是 3、5 ，反转后为 5、3 。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2415.Reverse%20Odd%20Levels%20of%20Binary%20Tree/images/second_case3.png" style="width: 591px; height: 111px;" />
<pre>
<strong>输入：</strong>root = [7,13,11]
<strong>输出：</strong>[7,11,13]
<strong>解释：</strong> 
在第 1 层的节点分别是 13、11 ，反转后为 11、13 。 
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>root = [0,1,2,0,0,0,0,1,1,1,1,2,2,2,2]
<strong>输出：</strong>[0,2,1,0,0,0,0,2,2,2,2,1,1,1,1]
<strong>解释：</strong>奇数层由非零值组成。
在第 1 层的节点分别是 1、2 ，反转后为 2、1 。
在第 3 层的节点分别是 1、1、1、1、2、2、2、2 ，反转后为 2、2、2、2、1、1、1、1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中的节点数目在范围 <code>[1, 2<sup>14</sup>]</code> 内</li>
	<li><code>0 &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
	<li><code>root</code> 是一棵 <strong>完美</strong> 二叉树</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：BFS

我们可以使用广度优先搜索的方法，用一个队列 $q$ 来存储每一层的节点，用一个变量 $i$ 记录当前层数。若 $i$ 为奇数，则将当前层的节点值反转。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是二叉树的节点数。

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
    def reverseOddLevels(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        q = deque([root])
        i = 0
        while q:
            if i & 1:
                l, r = 0, len(q) - 1
                while l < r:
                    q[l].val, q[r].val = q[r].val, q[l].val
                    l, r = l + 1, r - 1
            for _ in range(len(q)):
                node = q.popleft()
                if node.left:
                    q.append(node.left)
                    q.append(node.right)
            i += 1
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
    public TreeNode reverseOddLevels(TreeNode root) {
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        for (int i = 0; !q.isEmpty(); ++i) {
            List<TreeNode> t = new ArrayList<>();
            for (int k = q.size(); k > 0; --k) {
                var node = q.poll();
                if (i % 2 == 1) {
                    t.add(node);
                }
                if (node.left != null) {
                    q.offer(node.left);
                    q.offer(node.right);
                }
            }
            for (int l = 0, r = t.size() - 1; l < r; ++l, --r) {
                var x = t.get(l).val;
                t.get(l).val = t.get(r).val;
                t.get(r).val = x;
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
    TreeNode* reverseOddLevels(TreeNode* root) {
        queue<TreeNode*> q{{root}};
        for (int i = 0; q.size(); ++i) {
            vector<TreeNode*> t;
            for (int k = q.size(); k; --k) {
                TreeNode* node = q.front();
                q.pop();
                if (i & 1) {
                    t.push_back(node);
                }
                if (node->left) {
                    q.push(node->left);
                    q.push(node->right);
                }
            }
            for (int l = 0, r = t.size() - 1; l < r; ++l, --r) {
                swap(t[l]->val, t[r]->val);
            }
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
func reverseOddLevels(root *TreeNode) *TreeNode {
	q := []*TreeNode{root}
	for i := 0; len(q) > 0; i++ {
		t := []*TreeNode{}
		for k := len(q); k > 0; k-- {
			node := q[0]
			q = q[1:]
			if i%2 == 1 {
				t = append(t, node)
			}
			if node.Left != nil {
				q = append(q, node.Left)
				q = append(q, node.Right)
			}
		}
		for l, r := 0, len(t)-1; l < r; l, r = l+1, r-1 {
			t[l].Val, t[r].Val = t[r].Val, t[l].Val
		}
	}
	return root
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

function reverseOddLevels(root: TreeNode | null): TreeNode | null {
    const q: TreeNode[] = [root];
    for (let i = 0; q.length > 0; ++i) {
        if (i % 2) {
            for (let l = 0, r = q.length - 1; l < r; ++l, --r) {
                [q[l].val, q[r].val] = [q[r].val, q[l].val];
            }
        }
        const nq: TreeNode[] = [];
        for (const { left, right } of q) {
            if (left) {
                nq.push(left);
                nq.push(right);
            }
        }
        q.splice(0, q.length, ...nq);
    }
    return root;
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
use std::collections::VecDeque;
impl Solution {
    fn create_tree(vals: &Vec<Vec<i32>>, i: usize, j: usize) -> Option<Rc<RefCell<TreeNode>>> {
        if i == vals.len() {
            return None;
        }
        Some(
            Rc::new(
                RefCell::new(TreeNode {
                    val: vals[i][j],
                    left: Self::create_tree(vals, i + 1, j * 2),
                    right: Self::create_tree(vals, i + 1, j * 2 + 1),
                })
            )
        )
    }

    pub fn reverse_odd_levels(
        root: Option<Rc<RefCell<TreeNode>>>
    ) -> Option<Rc<RefCell<TreeNode>>> {
        let mut queue = VecDeque::new();
        queue.push_back(root);
        let mut d = 0;
        let mut vals = Vec::new();
        while !queue.is_empty() {
            let mut val = Vec::new();
            for _ in 0..queue.len() {
                let mut node = queue.pop_front().unwrap();
                let mut node = node.as_mut().unwrap().borrow_mut();
                val.push(node.val);
                if node.left.is_some() {
                    queue.push_back(node.left.take());
                }
                if node.right.is_some() {
                    queue.push_back(node.right.take());
                }
            }
            if d % 2 == 1 {
                val.reverse();
            }
            vals.push(val);
            d += 1;
        }
        Self::create_tree(&vals, 0, 0)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
