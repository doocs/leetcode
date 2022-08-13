# [199. Binary Tree Right Side View](https://leetcode.com/problems/binary-tree-right-side-view)

[中文文档](/solution/0100-0199/0199.Binary%20Tree%20Right%20Side%20View/README.md)

## Description

<p>Given the <code>root</code> of a binary tree, imagine yourself standing on the <strong>right side</strong> of it, return <em>the values of the nodes you can see ordered from top to bottom</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0199.Binary%20Tree%20Right%20Side%20View/images/tree.jpg" style="width: 401px; height: 301px;" />
<pre>
<strong>Input:</strong> root = [1,2,3,null,5,null,4]
<strong>Output:</strong> [1,3,4]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> root = [1,null,3]
<strong>Output:</strong> [1,3]
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> root = []
<strong>Output:</strong> []
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>The number of nodes in the tree is in the range <code>[0, 100]</code>.</li>
	<li><code>-100 &lt;= Node.val &lt;= 100</code></li>
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
    def rightSideView(self, root: TreeNode) -> List[int]:
        ans = []
        if root is None:
            return ans
        q = deque([root])
        while q:
            ans.append(q[0].val)
            for i in range(len(q), 0, -1):
                node = q.popleft()
                if node.right:
                    q.append(node.right)
                if node.left:
                    q.append(node.left)
        return ans
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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Deque<TreeNode> q = new LinkedList<>();
        q.offerLast(root);
        while (!q.isEmpty()) {
            ans.add(q.peekFirst().val);
            for (int i = q.size(); i > 0; --i) {
                TreeNode node = q.pollFirst();
                if (node.right != null) {
                    q.offerLast(node.right);
                }
                if (node.left != null) {
                    q.offerLast(node.left);
                }
            }
        }
        return ans;
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
    vector<int> rightSideView(TreeNode* root) {
        vector<int> ans;
        if (!root) return ans;
        queue<TreeNode*> q {{root}};
        while (!q.empty()) {
            ans.push_back(q.front()->val);
            for (int i = q.size(); i > 0; --i) {
                TreeNode* node = q.front();
                q.pop();
                if (node->right) q.push(node->right);
                if (node->left) q.push(node->left);
            }
        }
        return ans;
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
func rightSideView(root *TreeNode) []int {
	var ans []int
	if root == nil {
		return ans
	}
	q := []*TreeNode{root}
	for len(q) > 0 {
		ans = append(ans, q[0].Val)
		for i := len(q); i > 0; i-- {
			node := q[0]
			q = q[1:]
			if node.Right != nil {
				q = append(q, node.Right)
			}
			if node.Left != nil {
				q = append(q, node.Left)
			}
		}
	}
	return ans
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

function rightSideView(root: TreeNode | null): number[] {
    const res = [];
    if (root == null) {
        return res;
    }
    const queue = [root];
    while (queue.length !== 0) {
        const n = queue.length;
        res.push(queue[n - 1].val);
        for (let i = 0; i < n; i++) {
            const { left, right } = queue.shift();
            left && queue.push(left);
            right && queue.push(right);
        }
    }
    return res;
}
```

### **Rust**

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
    pub fn right_side_view(root: Option<Rc<RefCell<TreeNode>>>) -> Vec<i32> {
        let mut res = vec![];
        if root.is_none() {
            return res;
        }
        let mut q = VecDeque::new();
        q.push_back(root);
        while !q.is_empty() {
            let n = q.len();
            res.push(q[n - 1].as_ref().unwrap().borrow().val);
            for _ in 0..n {
                if let Some(node) = q.pop_front().unwrap() {
                    let mut node = node.borrow_mut();
                    if node.left.is_some() {
                        q.push_back(node.left.take());
                    }
                    if node.right.is_some() {
                        q.push_back(node.right.take());
                    }
                }
            }
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
