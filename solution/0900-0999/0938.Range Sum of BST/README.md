## 二叉搜索树的范围和

### 问题描述

给定二叉搜索树的根结点 root，返回 L 和 R（含）之间的所有结点的值的和。

二叉搜索树保证具有唯一的值。

**示例1:**
```
输入：root = [10,5,15,3,7,null,18], L = 7, R = 15
输出：32
```
**示例2:**
```
输入：root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
输出：23
```
**提示:**
- 树中的结点数量最多为 10000 个。
- 最终的答案保证小于 2^31。

### 解法

需要返回二叉搜索树中所有大于等于 L 且小于等于 R 的节点值的和，则对每个节点进行遍历即可。
- 若节点的值大于等于 L 且小于等于 R，则加入统计中，并继续搜索其子节点。
- 因为是二叉搜索树，所以小于 L 的只需要搜索其右子节点，大于 R 的只需要搜索其左子节点。
- 等于的情况可以提出来单独处理，也可以不单独处理，单独处理只是会减少一些无用的迭代。
节点值等于 L 只需搜索右子节点，等于 R 只需搜索左子节点。

```python
class Solution:
    def rangeSumBST(self, root, L, R):
        def searchBST(node):
            if not node:
                return
            if L <= node.val <= R:
                self.ans += node.val
                searchBST(node.right)
                searchBST(node.left)
            elif node.val < L:
                searchBST(node.right)
            elif node.val > R:
                searchBST(node.left)
        self.ans = 0
        searchBST(root)
        return self.ans
```
