# [144. 二叉树的前序遍历](https://leetcode-cn.com/problems/binary-tree-preorder-traversal)

[English Version](/solution/0100-0199/0144.Binary%20Tree%20Preorder%20Traversal/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你二叉树的根节点 <code>root</code> ，返回它节点值的 <strong>前序</strong><em> </em>遍历。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0144.Binary%20Tree%20Preorder%20Traversal/images/inorder_1.jpg" style="width: 202px; height: 324px;" />
<pre>
<strong>输入：</strong>root = [1,null,2,3]
<strong>输出：</strong>[1,2,3]
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

<p><strong>示例 4：</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0144.Binary%20Tree%20Preorder%20Traversal/images/inorder_5.jpg" style="width: 202px; height: 202px;" />
<pre>
<strong>输入：</strong>root = [1,2]
<strong>输出：</strong>[1,2]
</pre>

<p><strong>示例 5：</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0144.Binary%20Tree%20Preorder%20Traversal/images/inorder_4.jpg" style="width: 202px; height: 202px;" />
<pre>
<strong>输入：</strong>root = [1,null,2]
<strong>输出：</strong>[1,2]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点数目在范围 <code>[0, 100]</code> 内</li>
	<li><code>-100 <= Node.val <= 100</code></li>
</ul>

<p> </p>

<p><strong>进阶：</strong>递归算法很简单，你可以通过迭代算法完成吗？</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**1. 递归遍历**

先访问根节点，接着递归左子树、右子树。

**2. 栈实现非递归遍历**

非递归的思路如下：

1. 定义一个栈，先将根节点压入栈
2. 若栈不为空，每次从栈中弹出一个节点
3. 处理该节点
4. 先把节点右孩子压入栈，接着把节点左孩子压入栈（如果有孩子节点）
5. 重复 2-4
6. 返回结果

**3. Morris 实现前序遍历**

Morris 遍历无需使用栈，空间复杂度为 O(1)。核心思想是：

遍历二叉树节点，

1. 若当前节点 root 的左子树为空，**将当前节点值添加至结果列表 res** 中，并将当前节点更新为 `root.right`
2. 若当前节点 root 的左子树不为空，找到左子树的最右节点 pre（也即是 root 节点在中序遍历下的前驱节点）：
   - 若前驱节点 pre 的右子树为空，**将当前节点值添加至结果列表 res** 中，然后将前驱节点的右子树指向当前节点 root，并将当前节点更新为 `root.left`。
   - 若前驱节点 pre 的右子树不为空，将前驱节点右子树指向空（即解除 pre 与 root 的指向关系），并将当前节点更新为 `root.right`。
3. 循环以上步骤，直至二叉树节点为空，遍历结束。

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
    def preorderTraversal(self, root: TreeNode) -> List[int]:
        res = []

        def preorder(root):
            if root:
                res.append(root.val)
                preorder(root.left)
                preorder(root.right)

        preorder(root)
        return res
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
    def preorderTraversal(self, root: TreeNode) -> List[int]:
        res = []
        if root:
            s = [root]
            while s:
                node = s.pop()
                res.append(node.val)
                if node.right:
                    s.append(node.right)
                if node.left:
                    s.append(node.left)
        return res
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
    def preorderTraversal(self, root: TreeNode) -> List[int]:
        res = []
        while root:
            if root.left is None:
                res.append(root.val)
                root = root.right
            else:
                pre = root.left
                while pre.right and pre.right != root:
                    pre = pre.right
                if pre.right is None:
                    res.append(root.val)
                    pre.right = root
                    root = root.left
                else:
                    pre.right = None
                    root = root.right
        return res
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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preorder(root, res);
        return res;
    }

    private void preorder(TreeNode root, List<Integer> res) {
        if (root != null) {
            res.add(root.val);
            preorder(root.left, res);
            preorder(root.right, res);
        }
    }
}
```

非递归：

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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root != null) {
            Deque<TreeNode> s = new LinkedList<>();
            s.offerLast(root);
            while (!s.isEmpty()) {
                TreeNode node = s.pollLast();
                res.add(node.val);
                if (node.right != null) {
                    s.offerLast(node.right);
                }
                if (node.left != null) {
                    s.offerLast(node.left);
                }
            }
        }
        return res;
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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        while (root != null) {
            if (root.left == null) {
                res.add(root.val);
                root = root.right;
            } else {
                TreeNode pre = root.left;
                while (pre.right != null && pre.right != root) {
                    pre = pre.right;
                }
                if (pre.right == null) {
                    res.add(root.val);
                    pre.right = root;
                    root = root.left;
                } else {
                    pre.right = null;
                    root = root.right;
                }
            }
        }
        return res;
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

function preorderTraversal(root: TreeNode | null): number[] {
    if (root == null) return [];
    let stack = [];
    let ans = [];
    while (root || stack.length) {
        while (root) {
            ans.push(root.val);
            stack.push(root);
            root = root.left;
        }
        root = stack.pop();
        root = root.right;
    }
    return ans;
};
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
    vector<int> preorderTraversal(TreeNode *root) {
        vector<int> res;
        while (root)
        {
            if (root->left == nullptr)
            {
                res.push_back(root->val);
                root = root->right;
            }
            else
            {
                TreeNode *pre = root->left;
                while (pre->right && pre->right != root)
                {
                    pre = pre->right;
                }
                if (pre->right == nullptr)
                {
                    res.push_back(root->val);
                    pre->right = root;
                    root = root->left;
                }
                else
                {
                    pre->right = nullptr;
                    root = root->right;
                }
            }
        }
        return res;
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
func preorderTraversal(root *TreeNode) []int {
	var res []int
	for root != nil {
		if root.Left == nil {
			res = append(res, root.Val)
			root = root.Right
		} else {
			pre := root.Left
			for pre.Right != nil && pre.Right != root {
				pre = pre.Right
			}
			if pre.Right == nil {
				res = append(res, root.Val)
				pre.Right = root
				root = root.Left
			} else {
				pre.Right = nil
				root = root.Right
			}
		}
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
