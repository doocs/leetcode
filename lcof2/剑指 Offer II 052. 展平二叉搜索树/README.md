# [剑指 Offer II 052. 展平二叉搜索树](https://leetcode.cn/problems/NYBBNL)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一棵二叉搜索树，请&nbsp;<strong>按中序遍历</strong> 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20052.%20%E5%B1%95%E5%B9%B3%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91/images/ex1.jpg" style="width: 600px; height: 350px;" /></p>

<pre>
<strong>输入：</strong>root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
<strong>输出：</strong>[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20052.%20%E5%B1%95%E5%B9%B3%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91/images/ex2.jpg" style="width: 300px; height: 114px;" /></p>

<pre>
<strong>输入：</strong>root = [5,1,7]
<strong>输出：</strong>[1,null,5,null,7]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点数的取值范围是 <code>[1, 100]</code></li>
	<li><code>0 &lt;= Node.val &lt;= 1000</code></li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 897&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/increasing-order-search-tree/">https://leetcode.cn/problems/increasing-order-search-tree/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

由于二叉搜索树的性质，可以利用中序遍历得到递增序列

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def increasingBST(self, root: TreeNode) -> TreeNode:
        head, tail = None, None
        stack = []
        cur = root
        while stack or cur:
            while cur:
                stack.append(cur)
                cur = cur.left
            cur = stack.pop()
            if not head:
                head = cur
            else:
                tail.right = cur
            tail = cur
            cur.left = None
            cur = cur.right
        return head
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
    public TreeNode increasingBST(TreeNode root) {
        TreeNode head = null, tail = null;
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if (head == null) {
                head = cur;
            } else {
                tail.right = cur;
            }
            tail = cur;
            cur.left = null;
            cur = cur.right;
        }
        return head;
    }
}
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
func increasingBST(root *TreeNode) *TreeNode {
	var head, tail *TreeNode
	stack := make([]*TreeNode, 0)
	cur := root
	for len(stack) > 0 || cur != nil {
		for cur != nil {
			stack = append(stack, cur)
			cur = cur.Left
		}
		cur = stack[len(stack)-1]
		stack = stack[:len(stack)-1]
		if head == nil {
			head = cur
		} else {
			tail.Right = cur
		}
		tail = cur
		cur.Left = nil
		cur = cur.Right
	}
	return head
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
    TreeNode* increasingBST(TreeNode* root) {
        TreeNode *head = nullptr, *tail = nullptr;
        stack<TreeNode*> stk;
        TreeNode* cur = root;
        while (!stk.empty() || cur != nullptr) {
            while (cur != nullptr) {
                stk.push(cur);
                cur = cur->left;
            }
            cur = stk.top();
            stk.pop();
            if (head == nullptr) {
                head = cur;
            } else {
                tail->right = cur;
            }
            tail = cur;
            cur->left = nullptr;
            cur = cur->right;
        }
        return head;
    }
};
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

function increasingBST(root: TreeNode | null): TreeNode | null {
    const dummy = new TreeNode();
    let cur = dummy;
    const dfs = (root: TreeNode | null) => {
        if (root == null) {
            return;
        }
        dfs(root.left);
        cur.right = new TreeNode(root.val);
        cur = cur.right;
        dfs(root.right);
    };
    dfs(root);
    return dummy.right;
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
impl Solution {
    fn dfs(root: &Option<Rc<RefCell<TreeNode>>>, vals: &mut Vec<i32>) {
        if root.is_none() {
            return;
        }
        let node = root.as_ref().unwrap().borrow();
        Self::dfs(&node.left, vals);
        vals.push(node.val);
        Self::dfs(&node.right, vals);
    }

    pub fn increasing_bst(root: Option<Rc<RefCell<TreeNode>>>) -> Option<Rc<RefCell<TreeNode>>> {
        let mut vals = Vec::new();
        Self::dfs(&root, &mut vals);
        let mut dummy = Rc::new(RefCell::new(TreeNode::new(0)));
        for &val in vals.iter().rev() {
            let mut dummy = dummy.as_ref().borrow_mut();
            dummy.right = Some(Rc::new(RefCell::new(TreeNode {
                val,
                left: None,
                right: dummy.right.take(),
            })));
        }
        let ans = dummy.as_ref().borrow_mut().right.take();
        ans
    }
}
```

### **C**

```c
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */

struct TreeNode *dfs(struct TreeNode *root, struct TreeNode *cur) {
    if (!root) {
        return cur;
    }
    cur = dfs(root->left, cur);
    cur->right = malloc(sizeof(struct TreeNode));
    cur->right->val = root->val;
    cur->right->left = NULL;
    cur->right->right = NULL;
    cur = cur->right;
    return dfs(root->right, cur);
}

struct TreeNode *increasingBST(struct TreeNode *root) {
    struct TreeNode *dummy = malloc(sizeof(struct TreeNode));
    dfs(root, dummy);
    return dummy->right;
}
```

### **...**

```

```

<!-- tabs:end -->
