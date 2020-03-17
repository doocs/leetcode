## 二叉搜索树中的插入操作
### 题目描述

给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 保证原始二叉搜索树中不存在新值。

注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回任意有效的结果。

**示例 :**
```
给定二叉搜索树:

        4
       / \
      2   7
     / \
    1   3

和 插入的值: 5
```
你可以返回这个二叉搜索树:
```
          4
        /   \
      2       7
     /  \    /
    1    3  5
```
或者这个树也是有效的:
```
        5
       / \
      2   7
     / \     
    1   3   
         \
          4
```

### 解法一
返回第一种类型。遍历每个节点，若节点值大于插入值，搜索其左子节点，若小于插入值，则搜索其右子节点。若其节点不存在，则该位置为需要插入的值所在节点。使用递归会使运行时间相对增加，而循环语句的运行更快。


```python
class Solution:
    def insertIntoBST(self, root, val):
        if not root:
            return TreeNode(val)
        if root.val < val:
            if root.right:
                self.insertIntoBST(root.right, val)
            else:
                root.right = TreeNode(val)
        if root.val > val:
            if root.left:
                self.insertIntoBST(root.left, val)
            else:
                root.left = TreeNode(val)
        return root
```

### 解法二（无法AC）
返回第二种类型。先将根节点替换为插入值，再将根节点的值放到其左子节点中的最右子节点。但是这种解法无法AC，个人认为是题目并不支持返回第二种类型的结果。

```python
class Solution:
    def insertIntoBST(self, root, val):
        if not root:
            return TreeNode(val)
        elif root.left is None:
            root.left = TreeNode(root.val)
            root.val = val
        root.val, val = val, root.val
        node = root.left
        while node.right:
            node = node.right
        node.right = TreeNode(val)
        return root
```
