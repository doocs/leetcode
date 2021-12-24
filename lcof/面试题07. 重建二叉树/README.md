# [面试题 07. 重建二叉树](https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/)

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

<!-- tabs:start -->

### **Python3**

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    indexes = {}
    def buildTree(self, preorder: List[int], inorder: List[int]) -> TreeNode:
        def build(preorder, inorder, p1, p2, i1, i2) -> TreeNode:
            if p1 > p2 or i1 > i2:
                return None
            root_val = preorder[p1]
            pos = self.indexes[root_val]
            root = TreeNode(root_val)
            root.left = None if pos == i1 else build(preorder, inorder, p1 + 1, p1 - i1 + pos, i1, pos - 1)
            root.right = None if pos == i2 else build(preorder, inorder, p1 - i1 + pos + 1, p2, pos + 1, i2)
            return root
        n = len(inorder)
        for i in range(n):
            self.indexes[inorder[i]] = i
        return build(preorder, inorder, 0, n - 1, 0, n - 1)
```

### **Java**

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
    private Map<Integer, Integer> indexes = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = inorder.length;
        for (int i = 0; i < n; ++i) {
            indexes.put(inorder[i], i);
        }
        return build(preorder, inorder, 0, n - 1, 0, n - 1);
    }

    private TreeNode build(int[] preorder, int[] inorder, int p1, int p2, int i1, int i2) {
        if (p1 > p2 || i1 > i2) return null;
        int rootVal = preorder[p1];
        int pos = indexes.get(rootVal);
        TreeNode node = new TreeNode(rootVal);
        node.left = pos == i1 ? null : build(preorder, inorder, p1 + 1, pos - i1 + p1, i1, pos - 1);
        node.right = pos == i2 ? null : build(preorder, inorder, pos - i1 + p1 + 1, p2, pos + 1, i2);
        return node;
    }
}
```

### **JavaScript**

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
var buildTree = function (preorder, inorder) {
    if (!preorder || !preorder.length) return null;
    let preIdx = 0;
    let inMap = {};
    for (let i = 0; i < inorder.length; i++) {
        inMap[inorder[i]] = i;
    }
    function func(start, end) {
        if (start > end) {
            return null;
        }
        let preVal = preorder[preIdx];
        preIdx++;
        let inIdx = inMap[preVal];
        let node = new TreeNode(preVal);
        node.left = func(start, inIdx - 1);
        node.right = func(inIdx + 1, end);
        return node;
    }
    return func(0, preorder.length - 1);
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

### **C++**

```cpp
class Solution {
public:
    TreeNode* buildTree(vector<int>& preorder, vector<int>& inorder) {
        return build(preorder, inorder, 0, preorder.size() - 1, 0, inorder.size() - 1);
    }

private:
    TreeNode* build(vector<int>& preorder, vector<int>& inorder, int pre_l, int pre_r, int in_l, int in_r) {
        if (pre_l > pre_r || in_l > in_r) {
            return NULL;
        }
        int root = preorder[pre_l];
        int i = in_l;
        while (i <= in_r && inorder[i] != root) {
            ++i;
        }
        TreeNode* node = new TreeNode(root);
        node->left = build(preorder, inorder, pre_l + 1, pre_l + i - in_l, in_l, i - 1);
        node->right = build(preorder, inorder, pre_l + i - in_l + 1, pre_r, i + 1, in_r);
        return node;
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

function buildTree(preorder: number[], inorder: number[]): TreeNode | null {
    if (preorder.length == 0) return null;
    let val: number = preorder[0];
    let node: TreeNode = new TreeNode(val);
    let index: number = inorder.indexOf(val);
    node.left = buildTree(
        preorder.slice(1, index + 1),
        inorder.slice(0, index)
    );
    node.right = buildTree(preorder.slice(index + 1), inorder.slice(index + 1));
    return node;
}
```

### **...**

```

```

<!-- tabs:end -->
