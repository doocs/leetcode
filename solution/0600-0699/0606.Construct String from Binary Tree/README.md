# [606. 根据二叉树创建字符串](https://leetcode-cn.com/problems/construct-string-from-binary-tree)

[English Version](/solution/0600-0699/0606.Construct%20String%20from%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你需要采用前序遍历的方式，将一个二叉树转换成一个由括号和整数组成的字符串。</p>

<p>空节点则用一对空括号 &quot;()&quot; 表示。而且你需要省略所有不影响字符串与原始二叉树之间的一对一映射关系的空括号对。</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 二叉树: [1,2,3,4]
       1
     /   \
    2     3
   /    
  4     

<strong>输出:</strong> &quot;1(2(4))(3)&quot;

<strong>解释:</strong> 原本将是&ldquo;1(2(4)())(3())&rdquo;，
在你省略所有不必要的空括号对之后，
它将是&ldquo;1(2(4))(3)&rdquo;。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> 二叉树: [1,2,3,null,4]
       1
     /   \
    2     3
     \  
      4 

<strong>输出:</strong> &quot;1(2()(4))(3)&quot;

<strong>解释:</strong> 和第一个示例相似，
除了我们不能省略第一个对括号来中断输入和输出之间的一对一映射关系。
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

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
    def tree2str(self, root: Optional[TreeNode]) -> str:
        def dfs(root):
            if root is None:
                return ''
            if root.left is None and root.right is None:
                return str(root.val)
            if root.right is None:
                return f'{root.val}({dfs(root.left)})'
            return f'{root.val}({dfs(root.left)})({dfs(root.right)})'

        return dfs(root)
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
    public String tree2str(TreeNode root) {
        if (root == null) {
            return "";
        }
        if (root.left == null && root.right == null) {
            return root.val + "";
        }
        if (root.right == null) {
            return root.val + "(" + tree2str(root.left) + ")";
        }
        return root.val + "(" + tree2str(root.left) + ")(" + tree2str(root.right) + ")";
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
    string tree2str(TreeNode* root) {
        if (!root) return "";
        if (!root->left && !root->right) return to_string(root->val);
        if (!root->right) return to_string(root->val) + "(" + tree2str(root->left) + ")";
        return to_string(root->val) + "(" + tree2str(root->left) + ")(" + tree2str(root->right) + ")";
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
func tree2str(root *TreeNode) string {
	if root == nil {
		return ""
	}
	if root.Left == nil && root.Right == nil {
		return strconv.Itoa(root.Val)
	}
	if root.Right == nil {
		return strconv.Itoa(root.Val) + "(" + tree2str(root.Left) + ")"
	}
	return strconv.Itoa(root.Val) + "(" + tree2str(root.Left) + ")(" + tree2str(root.Right) + ")"
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

function tree2str(root: TreeNode | null): string {
    if (root == null) {
        return '';
    }
    if (root.left == null && root.right == null) {
        return `${root.val}`;
    }
    return `${root.val}(${root.left ? tree2str(root.left) : ''})${
        root.right ? `(${tree2str(root.right)})` : ''
    }`;
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
use std::cell::RefCell;
use std::rc::Rc;
impl Solution {
    fn dfs(root: &Option<Rc<RefCell<TreeNode>>>, res: &mut String) {
        if let Some(node) = root {
            let node = node.borrow();
            res.push_str(node.val.to_string().as_str());

            if node.left.is_none() && node.right.is_none() {
                return;
            }
            res.push('(');
            if node.left.is_some() {
                Self::dfs(&node.left, res);
            }
            res.push(')');
            if node.right.is_some() {
                res.push('(');
                Self::dfs(&node.right, res);
                res.push(')');
            }
        }
    }

    pub fn tree2str(root: Option<Rc<RefCell<TreeNode>>>) -> String {
        let mut res = String::new();
        Self::dfs(&root, &mut res);
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
