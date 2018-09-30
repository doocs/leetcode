## 二叉树的前序遍历
### 题目描述

给定一个二叉树，返回它的 前序 遍历。

 示例:
```
输入: [1,null,2,3]  
   1
    \
     2
    /
   3 

输出: [1,2,3]
```

进阶: 递归算法很简单，你可以通过迭代算法完成吗？

### 解法

- 递归算法

先访问根结点，再递归左子树，最后递归右子树。

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorderTraversal(root, list);
        return list;
    }
    
    private void preorderTraversal(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        preorderTraversal(root.left, list);
        preorderTraversal(root.right, list);
    }
}
```

- 非递归算法
循环：先访问根结点，接着判断是否有右孩子，有则压入栈中；再判断是否有左孩子，有则压入栈中。判断栈是否为空，若是，跳出循环。否则弹出栈顶元素，继续循环。

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null) {
            list.add(root.val);
            if (root.right != null) {
                stack.push(root.right);
            }
            if (root.left != null) {
                stack.push(root.left);
            }
            if (!stack.isEmpty()) {
                root = stack.pop();
            } else {
                break;
            }
        }
        
        return list;
    }
}
```