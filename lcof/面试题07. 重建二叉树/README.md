# [面试题07. 重建二叉树](https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/)

## 题目描述
输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。

例如，给出

```
前序遍历 preorder = [3,9,20,15,7]
中序遍历 inorder = [9,3,15,20,7]
```

返回如下的二叉树：

```
    3
   / \
  9  20
    /  \
   15   7
```


**限制：**

- `0 <= 节点个数 <= 5000`

## 解法
### Python3
```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def buildTree(self, preorder: List[int], inorder: List[int]) -> TreeNode:
        if preorder is None or inorder is None or len(preorder) != len(inorder):
            return None
        return self._build_tree(preorder, 0, len(preorder) - 1, inorder, 0, len(inorder) - 1)
        
    def _build_tree(self, preorder, s1, e1, inorder, s2, e2):
        if s1 > e1 or s2 > e2:
            return None
        index = self._find_index(inorder, s2, e2, preorder[s1])
        tree = TreeNode(preorder[s1])
        tree.left = self._build_tree(preorder, s1 + 1, index + s1 - s2, inorder, s2, index - 1)
        tree.right = self._build_tree(preorder, index + s1 - s2 + 1, e1, inorder, index + 1, e2)
        return tree

    def _find_index(self, order, s, e, val):
        for i in range(s, e + 1):
            if order[i] == val:
                return i
        return -1
```

### Java
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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder == null || preorder.length == 0 || preorder.length == 0 || preorder.length != inorder.length) {
            return null;
        }

        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public TreeNode buildTree(int[] preorder, int s1, int e1, int[] inorder, int s2, int e2) {
        if (s1 > e1 || s2 > e2) {
            return null;
        }
        int index = findIndex(inorder, s2, e2, preorder[s1]);
        TreeNode tree = new TreeNode(preorder[s1]);
        tree.left = buildTree(preorder, s1 + 1, index + s1 - s2, inorder, s2, index - 1);
        tree.right = buildTree(preorder, index + s1 - s2 + 1, e1, inorder, index + 1, e2);
        return tree;
    }

    public int findIndex(int[] order, int s, int e, int val) {
        for (int i = s; i <= e; ++i) {
            if (order[i] == val) {
                return i;
            }
        }
        return -1;
    }
}
```

### JavaScript
```js
/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */
/**
 * @param {number[]} preorder
 * @param {number[]} inorder
 * @return {TreeNode}
 */
var buildTree = function(preorder, inorder) {
    if(!preorder || !preorder.length) return null
    let preIdx = 0
    let inMap = {}
    for(let i=0;i<inorder.length;i++) {
        inMap[inorder[i]] = i
    }
    function func(start, end) {
        if(start > end) {
            return null
        }
        let preVal = preorder[preIdx]
        preIdx++
        let inIdx = inMap[preVal]
        let node = new TreeNode(preVal)
        node.left = func(start, inIdx - 1)
        node.right = func(inIdx + 1, end)
        return node
    }
    return func(0, preorder.length - 1)
};
```

### Go

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func buildTree(preorder []int, inorder []int) *TreeNode {
    return helper(preorder, inorder, 0, 0, len(preorder)-1)
}

func helper(preorder, inorder []int, index, start, end int) *TreeNode {
    if start > end {
        return nil
    }
    root := &TreeNode{Val:preorder[index]}
    j := start
    for j < end && preorder[index] != inorder[j] {
        j++
    }
    root.Left = helper(preorder, inorder, index + 1, start, j - 1)
    root.Right = helper(preorder, inorder, index + 1 + j -start, j + 1, end)
    return root
}
```



### ...

```

```
