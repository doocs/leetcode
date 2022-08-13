# [145. 二叉树的后序遍历](https://leetcode.cn/problems/binary-tree-postorder-traversal)

[English Version](/solution/0100-0199/0145.Binary%20Tree%20Postorder%20Traversal/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一棵二叉树的根节点 <code>root</code> ，返回其节点值的 <strong>后序遍历 </strong>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0145.Binary%20Tree%20Postorder%20Traversal/images/pre1.jpg" style="width: 127px; height: 200px;" />
<pre>
<strong>输入：</strong>root = [1,null,2,3]
<strong>输出：</strong>[3,2,1]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>root = []
<strong>输出：</strong>[]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>root = [1]
<strong>输出：</strong>[1]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点的数目在范围 <code>[0, 100]</code> 内</li>
	<li><code>-100 &lt;= Node.val &lt;= 100</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>递归算法很简单，你可以通过迭代算法完成吗？</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**1. 递归遍历**

先递归左右子树，再访问根节点。

**2. 栈实现非递归遍历**

非递归的思路如下：

先序遍历的顺序是：头、左、右，如果我们改变左右孩子的顺序，就能将顺序变成：头、右、左。

我们先不打印头节点，而是存放到另一个收集栈 ans 中，最后遍历结束，输出收集栈元素，即是后序遍历：左、右、头。收集栈是为了实现结果列表的逆序。我们也可以直接使用链表，每次插入元素时，放在头部，最后直接返回链表即可，无需进行逆序。

**3. Morris 实现后序遍历**

Morris 遍历无需使用栈，空间复杂度为 O(1)。核心思想是：

遍历二叉树节点，

1. 若当前节点 root 的右子树为空，**将当前节点值添加至结果列表 ans** 中，并将当前节点更新为 `root.left`
2. 若当前节点 root 的右子树不为空，找到右子树的最左节点 next（也即是 root 节点在中序遍历下的后继节点）：
    - 若后继节点 next 的左子树为空，**将当前节点值添加至结果列表 ans** 中，然后将后继节点的左子树指向当前节点 root，并将当前节点更新为 `root.right`。
    - 若后继节点 next 的左子树不为空，将后继节点左子树指向空（即解除 next 与 root 的指向关系），并将当前节点更新为 `root.left`。
3. 循环以上步骤，直至二叉树节点为空，遍历结束。
4. 最后返回结果列表的逆序即可。

> Morris 后序遍历跟 Morris 前序遍历思路一致，只是将前序的“根左右”变为“根右左”，最后逆序结果即可变成“左右根”。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

递归：

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def postorderTraversal(self, root: Optional[TreeNode]) -> List[int]:
        def dfs(root):
            if root is None:
                return
            dfs(root.left)
            dfs(root.right)
            nonlocal ans
            ans.append(root.val)

        ans = []
        dfs(root)
        return ans
```

栈实现非递归：

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def postorderTraversal(self, root: Optional[TreeNode]) -> List[int]:
        ans = []
        if root is None:
            return ans
        stk = [root]
        while stk:
            node = stk.pop()
            ans.append(node.val)
            if node.left:
                stk.append(node.left)
            if node.right:
                stk.append(node.right)
        return ans[::-1]
```

Morris 遍历：

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def postorderTraversal(self, root: Optional[TreeNode]) -> List[int]:
        ans = []
        while root:
            if root.right is None:
                ans.append(root.val)
                root = root.left
            else:
                next = root.right
                while next.left and next.left != root:
                    next = next.left
                if next.left != root:
                    ans.append(root.val)
                    next.left = root
                    root = root.right
                else:
                    next.left = None
                    root = root.left
        return ans[::-1]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

递归：

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
    private List<Integer> ans;

    public List<Integer> postorderTraversal(TreeNode root) {
        ans = new ArrayList<>();
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        dfs(root.right);
        ans.add(root.val);
    }
}
```

栈实现非递归：

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
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> ans = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        Deque<TreeNode> stk = new ArrayDeque<>();
        stk.push(root);
        while (!stk.isEmpty()) {
            TreeNode node = stk.pop();
            ans.addFirst(node.val);
            if (node.left != null) {
                stk.push(node.left);
            }
            if (node.right != null) {
                stk.push(node.right);
            }
        }
        return ans;
    }
}
```

Morris 遍历：

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
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> ans = new LinkedList<>();
        while (root != null) {
            if (root.right == null) {
                ans.addFirst(root.val);
                root = root.left;
            } else {
                TreeNode next = root.right;
                while (next.left != null && next.left != root) {
                    next = next.left;
                }
                if (next.left == null) {
                    ans.addFirst(root.val);
                    next.left = root;
                    root = root.right;
                } else {
                    next.left = null;
                    root = root.left;
                }
            }
        }
        return ans;
    }
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

function postorderTraversal(root: TreeNode | null): number[] {
    if (root == null) return [];
    let stack = [];
    let ans = [];
    let prev = null;
    while (root || stack.length) {
        while (root) {
            stack.push(root);
            root = root.left;
        }
        root = stack.pop();
        if (!root.right || root.right == prev) {
            ans.push(root.val);
            prev = root;
            root = null;
        } else {
            stack.push(root);
            root = root.right;
        }
    }
    return ans;
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

function postorderTraversal(root: TreeNode | null): number[] {
    if (root == null) {
        return [];
    }
    const { val, left, right } = root;
    return [...postorderTraversal(left), ...postorderTraversal(right), val];
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

function postorderTraversal(root: TreeNode | null): number[] {
    const res = [];
    while (root != null) {
        const { val, left, right } = root;
        if (right == null) {
            res.push(val);
            root = left;
        } else {
            let next = right;
            while (next.left != null && next.left != root) {
                next = next.left;
            }
            if (next.left == null) {
                res.push(val);
                next.left = root;
                root = right;
            } else {
                next.left = null;
                root = left;
            }
        }
    }
    return res.reverse();
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
    vector<int> postorderTraversal(TreeNode* root) {
        vector<int> ans;
        while (root) {
            if (!root->right) {
                ans.push_back(root->val);
                root = root->left;
            } else {
                TreeNode* next = root->right;
                while (next->left && next->left != root) {
                    next = next->left;
                }
                if (!next->left) {
                    ans.push_back(root->val);
                    next->left = root;
                    root = root->right;
                } else {
                    next->left = nullptr;
                    root = root->left;
                }
            }
        }
        reverse(ans.begin(), ans.end());
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
func postorderTraversal(root *TreeNode) []int {
	var ans []int
	for root != nil {
		if root.Right == nil {
			ans = append([]int{root.Val}, ans...)
			root = root.Left
		} else {
			next := root.Right
			for next.Left != nil && next.Left != root {
				next = next.Left
			}
			if next.Left == nil {
				ans = append([]int{root.Val}, ans...)
				next.Left = root
				root = root.Right
			} else {
				next.Left = nil
				root = root.Left
			}
		}
	}
	return ans
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
    fn dfs(root: &Option<Rc<RefCell<TreeNode>>>, res: &mut Vec<i32>) {
        if root.is_none() {
            return;
        }
        let node = root.as_ref().unwrap().borrow();
        Self::dfs(&node.left, res);
        Self::dfs(&node.right, res);
        res.push(node.val);
    }

    pub fn postorder_traversal(root: Option<Rc<RefCell<TreeNode>>>) -> Vec<i32> {
        let mut res = vec![];
        Self::dfs(&root, &mut res);
        res
    }
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
impl Solution {
    pub fn postorder_traversal(mut root: Option<Rc<RefCell<TreeNode>>>) -> Vec<i32> {
        let mut res = vec![];
        let mut stack = vec![];
        while root.is_some() || !stack.is_empty() {
            if root.is_some() {
                let next = root.as_mut().unwrap().borrow_mut().left.take();
                stack.push(root);
                root = next;
            } else  {
                root = stack.pop().unwrap();
                let next = root.as_mut().unwrap().borrow_mut().right.take();
                if next.is_some() {
                    stack.push(root);
                } else {
                    res.push(root.as_ref().unwrap().borrow().val);
                }
                root = next;
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
