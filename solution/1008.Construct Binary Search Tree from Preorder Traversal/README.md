## 先序遍历构造二叉树

### 问题描述

返回与给定先序遍历 **preorder** 相匹配的二叉搜索树（binary **search** tree）的根结点。

**示例1:**

```
输入：[8,5,1,7,10,12]
输出：[8,5,10,1,7,null,12]
```

![示例1](/img/Construct-Binary-Search-Tree-from-Preorder-Traversal.png)

**提示:**

- `1 <= preorder.length <= 100`
- The values of `preorder` are distinct.

### 解法

二叉树类的题目可以考虑使用递归中的分治法，让本次递归的根节点（sub-root）来管理自身子树的生成方式。而本题使用的是**前序遍历法**所生成的数组，则先检查了根节点，再检查左子树，再检查右子树。因此每层递归我们需要确定的是：

* 本层递归的根节点是什么？
* 根节点确定后，本层递归之后的左子树范围是什么，右子树的范围是什么？

对于第一个问题，我们知道前序遍历法的根节点一定是当前范围内的第一个元素；而对于第二个问题，我们知道右子树开始于**第一个比当前根节点大的元素**，而左子树结束于该元素的前面一个元素。在解决了这两个问题后，答案已经比较明确了，在每一层递归中，我们需要一个 start 和一个 end 来表示当前的递归所涉及的元素范围：

* 确定当前的递归是否结束（start > end || start >= end）
* 确定当前递归层的根节点（start）
* 确定左子树的范围（start + 1, leftEnd - 1）和右子树的范围（leftEnd, end）

因此有如下的递归解法：

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
    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        // 进入分治法的递归
        return helper(preorder, 0, preorder.length - 1);
    }
    
    private TreeNode helper(int[] preorder, int start, int end) {
        // System.out.println("start: " + start + " end: " + end);
        // 确认递归结束的标志，当 start == end 时，表示该区间只剩下一个 subRoot 节点
        if (start > end) {
            return null;
        }
        if (start == end) {
            return new TreeNode(preorder[start]);
        }
        // 前序遍历，首先遍历到的为根
        TreeNode root = new TreeNode(preorder[start]);
        int leftEnd = start;
        while (leftEnd <= end) {
            if (preorder[leftEnd] > preorder[start]) {
                break;
            }
            leftEnd++;
        }
        // System.out.println("leftEnd:" + leftEnd + " num: " + preorder[leftEnd]);
        root.left = helper(preorder, start + 1, leftEnd - 1);
        root.right = helper(preorder, leftEnd, end);
        return root;
    }
}
```

